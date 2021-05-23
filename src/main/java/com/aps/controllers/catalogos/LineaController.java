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

import com.aps.beans.catalogos.Linea;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.LineaDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class LineaController {
	@Autowired
	LineaDao daolinea;
	@Autowired
	AlmacenDao daoalmacen;

	@RequestMapping(value = "/showlineas", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> vielineas() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daolinea.getLineas();
		mapa.put("data", list);
		return mapa;
	}

	@RequestMapping(value = "/deletealllineas", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallLineas() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			daolinea.deleteAllLineas();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
		}
		return mapa;
	}
	
	/**
	 * @param Linea
	 * */
	@Transactional
	void saveLinea(Linea linea) {
		daolinea.save(linea);
	}
	/**
	 * @param Linea
	 * */
	@Transactional
	void updateLinea(Linea linea) {
		daolinea.update(linea);
	}
	@RequestMapping("/deletelinea")
	public @ResponseBody String delete(@RequestParam("id") int id) {
		String cad = "";
		try {
			daolinea.delete(id);
			cad = "Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad = e.getMessage();

		}
		return cad;
	}
	/*
	 * METODOS para importacion de Marcas
	 */
	@RequestMapping("/getimportlineas")
	public ModelAndView viewimportAlms() {
		return new ModelAndView("linea/formlinea");
	}

	@RequestMapping(value = "/doUploadLinea", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileLinea(@RequestParam("filelinea") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Linea> lineasinsert = new ArrayList<Linea>();
		List<Linea> lineasupdate = new ArrayList<Linea>();
		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				String clave = "";
				String descripcion = "";
				String claveEmpresa = "";
				for (int row = 1; row < sheet.getRows(); row++) {
					claveEmpresa = sheet.getCell(0, row).getContents().toString();
					clave = sheet.getCell(1, row).getContents().toString();
					descripcion = sheet.getCell(2, row).getContents().toString();
					int idempresa = daoalmacen.getidempresa(claveEmpresa);
					if (idempresa != 0) {
						int idlinea = daolinea.getLineabyempresaid_clave(Integer.parseInt(clave), idempresa);
						if (idlinea == 0) { // no existe y guardamos para insert
							Linea linea = new Linea(Integer.parseInt(clave), descripcion, idempresa);
							lineasinsert.addAll(Arrays.asList(linea));
						} else {//  existe y guardamos para update
							Linea linea = new Linea(Integer.parseInt(clave), descripcion, idempresa);
							lineasupdate.addAll(Arrays.asList(linea));
						}
					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Empresa [" + claveEmpresa + "] \n");
					}

				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				}else {
					for(int i=0;i<lineasinsert.size();i++) {
						saveLinea(lineasinsert.get(i));
					}
					for(int j=0;j<lineasupdate.size();j++) {
						updateLinea(lineasupdate.get(j));
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
