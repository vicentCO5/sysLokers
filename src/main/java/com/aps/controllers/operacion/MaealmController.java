package com.aps.controllers.operacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.operacion.MaealmDao;

import jxl.Sheet;
import jxl.Workbook;

import com.aps.beans.operacion.Maealm;

@Controller
public class MaealmController {
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	MaealmDao daomaealm;
	
	@Transactional
	void saveInventario(Maealm m) {
		daomaealm.save(m);
	}
	///////// import ventas/////////////
	@RequestMapping("/importMaealm")
	public ModelAndView viewimportvtas() {
		return new ModelAndView("maealm/formstock");
	}
	@RequestMapping(value = "/doUploadinventario", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileHandler(@RequestParam("filestock") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Maealm> inventario = new ArrayList<Maealm>();
		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				String codarticulo = "";
				String codalmacen = "";
				String exualm = "";
				String uniart = "";
				String periodo = "";
				String tipostock = "";
				for (int row = 1; row < sheet.getRows(); row++) {
					codarticulo = sheet.getCell(0, row).getContents().toString();
					codalmacen = sheet.getCell(1, row).getContents().toString();
					exualm = (sheet.getCell(2, row).getContents().toString()).equals("")? "0":sheet.getCell(2, row).getContents().toString();
					uniart = sheet.getCell(3, row).getContents().toString();
					periodo = sheet.getCell(4, row).getContents().toString();
					tipostock = sheet.getCell(5, row).getContents().toString();
					String anio = periodo.substring(0,4);
					String mes= periodo.substring(4,6);
					//String dia = periodo.substring(6);
					String aniomes=anio+mes;
					Date fechacambio = new Date();
					SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String fecha = formateador.format(fechacambio);
					int idalmacen = daoalmacen.findByid(codalmacen);
					if (idalmacen != 0) {
						int idarticulo = daoarticulo.getArticulobyId(codarticulo);
						if (idarticulo != 0) {
							Maealm stock = new Maealm(idarticulo,idalmacen,Double.valueOf(exualm),uniart,0,0,"",aniomes,periodo,fecha,Integer.parseInt(tipostock));
							inventario.addAll(Arrays.asList(stock));
							
						}else {
							registrosError.add("Fila:" + (row + 1) + "- No existe Articulo [" + codarticulo + "] \n");
						}
					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Almacen [" + codalmacen + "] \n");
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				}else {
					for(int i=0;i<inventario.size();i++) {
						saveInventario(inventario.get(i));
					}
					mapa.put("exito", "true");
					mapa.put("error",registrosError);
				}
			} catch (Exception e) {
				mapa.put("exito", "false");
				registrosError.add(e.getMessage());
				mapa.put("error", registrosError);
			}
		} else {
			String cad = "You failed to upload " + " because the file was empty.";
			mapa.put("exito", "false");
			registrosError.add(cad);
			mapa.put("error", registrosError);
		}
		return mapa;
	}
}
