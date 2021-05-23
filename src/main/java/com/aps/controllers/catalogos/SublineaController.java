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

import com.aps.beans.catalogos.Sublinea;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.SublineaDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class SublineaController {
	@Autowired
	SublineaDao daosublinea;
	@Autowired
	AlmacenDao daoalmacen;

	
	@RequestMapping(value = "/showSublineas", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> viewSublineas() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daosublinea.getSubLineas();
		mapa.put("data", list);
		return mapa;
	}
	
	@RequestMapping(value = "/deleteallSublineas", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallSublineas() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			daosublinea.deleteAllSublineas();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
		}
		return mapa;
	}
	@Transactional
	void saveSublinea(Sublinea sublinea) {
		daosublinea.save(sublinea);
	}
	@Transactional
	void updateSublinea(Sublinea sublinea) {
		daosublinea.update(sublinea);
	}
	@RequestMapping("/deleteSublinea")
	public @ResponseBody String deleteSublinea(@RequestParam("id") int id) {
		String cad = "";
		try {
			daosublinea.delete(id);
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
	@RequestMapping("/getimportSublineas")
	public ModelAndView viewimportAlms() {
		return new ModelAndView("sublinea/formsublinea");
	}
	@RequestMapping(value = "/doUploadSubLinea", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileSubLinea(@RequestParam("fileSublinea") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Sublinea> sublineasinsert = new ArrayList<Sublinea>();
		List<Sublinea> sublineasupdate = new ArrayList<Sublinea>();
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
						int idsublinea = daosublinea.getSublineabyidclaveempresa(Integer.parseInt(clave), idempresa);
						if (idsublinea == 0) { // no existe y guardamos para insert
							Sublinea sublinea = new Sublinea(Integer.parseInt(clave), descripcion, idempresa);
							sublineasinsert.addAll(Arrays.asList(sublinea));
						} else {//  existe y guardamos para update
							Sublinea sublinea = new Sublinea(Integer.parseInt(clave), descripcion, idempresa);
							sublineasupdate.addAll(Arrays.asList(sublinea));
						}
					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Empresa [" + claveEmpresa + "] \n");
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				}else {
					for(int i=0;i<sublineasinsert.size();i++) {
						saveSublinea(sublineasinsert.get(i));
					}
					for(int j=0;j<sublineasupdate.size();j++) {
						updateSublinea(sublineasupdate.get(j));
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
