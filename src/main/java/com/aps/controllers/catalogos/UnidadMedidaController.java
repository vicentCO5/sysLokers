package com.aps.controllers.catalogos;

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

import com.aps.beans.catalogos.UnidadMedida;
import com.aps.dao.catalogos.UnidadMedidaDao;

import jxl.Sheet;
import jxl.Workbook;
@Controller
public class UnidadMedidaController {
	@Autowired
	UnidadMedidaDao daoudm;
	@Transactional
	public void saveUnidadMedida(UnidadMedida um) {
		daoudm.save(um);
	}
	@Transactional
	public void updateUnidadMedida(UnidadMedida um) {
		daoudm.update(um);
	}
	/*
	 * METODOS para importation de unidades de medida
	 * */
	@RequestMapping("/getimportunidadmedida")
	public ModelAndView viewimportcatUndMed() {
		return new ModelAndView("unidadmedida/formunidadmedida");
	}
	@RequestMapping(value = "/doUploadUndMed",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String, Object> uploadFileHandler(@RequestParam("fileundm") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		List<UnidadMedida> udminsert = new ArrayList<UnidadMedida>();
		List<UnidadMedida> udmupdate = new ArrayList<UnidadMedida>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				String codigoUnidad = "";
				String descripcion = "";
				String codigoUnidadMinima = "";
				String valorMinimo = "";
				String esUnidadMinima = "";
				String estado = "";
				
				for(int row = 1; row < sheet.getRows(); row++) {
					codigoUnidad = sheet.getCell(0, row).getContents().toString();
					descripcion = sheet.getCell(1, row).getContents().toString();
					codigoUnidadMinima = sheet.getCell(2, row).getContents().toString();
					valorMinimo = sheet.getCell(3, row).getContents().toString();
					esUnidadMinima = sheet.getCell(4, row).getContents().toString();
					estado = sheet.getCell(5, row).getContents().toString();
					int idundmed = daoudm.getundByCodigo(codigoUnidad);
					Date fechacambio = new Date();
					SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String fecha = formateador.format(fechacambio);
					if (idundmed == 0) {// no existe y guardamos para insert
						UnidadMedida undmed = new UnidadMedida(codigoUnidad,descripcion,codigoUnidadMinima,Double.parseDouble(valorMinimo),esUnidadMinima.charAt(0),estado.charAt(0),fecha);
						udminsert.addAll(Arrays.asList(undmed));
					}else {
						UnidadMedida undmed = new UnidadMedida(codigoUnidad,descripcion,codigoUnidadMinima,Double.parseDouble(valorMinimo),esUnidadMinima.charAt(0),estado.charAt(0),fecha);
						udmupdate.addAll(Arrays.asList(undmed));
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				} else {
					for (int i = 0; i < udminsert.size(); i++) {
						saveUnidadMedida(udminsert.get(i));
					}
					for (int j = 0; j < udmupdate.size(); j++) {
						updateUnidadMedida(udmupdate.get(j));
					}
					mapa.put("exito", "true");
					mapa.put("error", registrosError);
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
	@RequestMapping(value = "/showundmed", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> viewundmed() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoudm.getUndMeds();
		mapa.put("data", list);
		return mapa;
	}
	
	@RequestMapping(value = "/deleteallundmed", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallundmed() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			daoudm.deleteAllUndMed();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
			mapa.put("error", registrosError);
		}
		return mapa;
	}
	@RequestMapping("/deleteundMed")
	public @ResponseBody String delete(@RequestParam("id") String id) {
		String cad = "";
		try {
			daoudm.delete(id);
			cad = "Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad = e.getMessage();

		}
		return cad;
	}
}
