package com.aps.controllers.catalogos;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.aps.beans.catalogos.Marca;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.MarcaDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class MarcaController {
	@Autowired
	MarcaDao daomarca;
	@Autowired
	AlmacenDao daoalmacen;

	@RequestMapping(value = "/showmarcas", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> viewmarcas() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daomarca.getMarcas();
		mapa.put("data", list);
		return mapa;
	}

	@RequestMapping("/deletemarca")
	public @ResponseBody String delete(@RequestParam("id") int id) {
		String cad = "";
		try {
			daomarca.delete(id);
			cad = "Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad = e.getMessage();

		}
		return cad;
	}
	/**
	 * @param Marca
	 * */
	@Transactional
	void saveMarca(Marca marca) {
		daomarca.save(marca);
	}
	/**
	 * @param Marca
	 * **/
	@Transactional
	void updateMarca(Marca marca) {
		daomarca.update(marca);
	}
	

	/*
	 * METODOS para importacion de Marcas
	 */
	@RequestMapping("/getimportmarcas")
	public ModelAndView viewimportAlms() {
		return new ModelAndView("marca/formmarca");
	}

	@RequestMapping(value = "/doUploadmarca", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileHandler(@RequestParam("filemarca") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Marca> marcasinsert = new ArrayList<Marca>();
		List<Marca> marcasupdate = new ArrayList<Marca>();

		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				String claveEmpresa = "";
				String codigo = "";
				String nombre = "";
				String clasificacion = "";
				for (int row = 1; row < sheet.getRows(); row++) {

					claveEmpresa = sheet.getCell(0, row).getContents().toString();
					codigo = sheet.getCell(1, row).getContents().toString();
					nombre = sheet.getCell(2, row).getContents().toString();
					clasificacion = sheet.getCell(3, row).getContents().toString();
					int idempresa = daoalmacen.getidempresa(claveEmpresa);

					if (idempresa != 0) {
						int idmarca = daomarca.getmarcaById_Idemp(idempresa, codigo);
						if (idmarca == 0) { //no existe y guardamos para insert
							Marca marca = new Marca(Integer.parseInt(codigo), nombre, clasificacion, idempresa);
							marcasinsert.addAll(Arrays.asList(marca));

						} else {// ya existe y guardamos para update
							Marca marca = new Marca(Integer.parseInt(codigo), nombre, clasificacion, idempresa);
							marcasupdate.addAll(Arrays.asList(marca));
						}

					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Empresa [" + claveEmpresa + "] \n");
					}
				}

				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				}else {
					for(int i=0;i<marcasinsert.size();i++) {
						saveMarca(marcasinsert.get(i));
					}
					for(int j=0;j<marcasupdate.size();j++) {
						updateMarca(marcasupdate.get(j));
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
	
	@RequestMapping(value = "/deleteall", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteall(){
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			daomarca.deleteAll();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mapa.put("exito", "false");
			registrosError.add( e.getMessage());

		}
		return mapa;
		
	}
}
