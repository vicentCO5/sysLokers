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

import com.aps.beans.catalogos.Departamento;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.DepartamentoDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class DepartamentoController {
	@Autowired
	DepartamentoDao daodepto;
	@Autowired
	AlmacenDao daoalmacen;
	
	@RequestMapping(value = "/showdeptos", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> viewDeptos() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daodepto.getDepartamentos();
		mapa.put("data", list);
		return mapa;
	}
	@RequestMapping(value = "/deletealldeptos", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallDepto() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			daodepto.deleteAllDeptos();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
			mapa.put("error", registrosError);
		}
		return mapa;
	}
	@RequestMapping("/deletedepto")
	public @ResponseBody String deleteSublineados(@RequestParam("id") int id,
			@RequestParam("idempresa") int idempresa) {
		String cad = "";
		try {
			daodepto.delete(id, idempresa);
			cad = "Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad = e.getMessage();

		}
		return cad;
	}
	@Transactional
	void saveDepto(Departamento d) {
		daodepto.save(d);
	}
	@Transactional
	void updateDepto(Departamento d) {
		daodepto.update(d);
	}
	/*
	 * METODOS para importacion de Departamentos xls
	 */
	@RequestMapping("/getimportdeptos")
	public ModelAndView viewimportDepto() {
		return new ModelAndView("departamento/formdepto");
	}
	@RequestMapping(value = "/doUploadDepto", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileSubLineados(
			@RequestParam("filedepto") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Departamento> deptoinsert = new ArrayList<Departamento>();
		List<Departamento> deptoupdate = new ArrayList<Departamento>();
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
					String ordenpresentacion = "";
					
					int idempresa = daoalmacen.getidempresa(claveEmpresa);
					if (idempresa != 0) {
						int iddepto = daodepto.getDeptobyempresaid_clave(Integer.parseInt(clave), idempresa);
						if (iddepto == 0) { // no existe y guardamos para insert
							Departamento depto = new Departamento(clave,descripcion,ordenpresentacion,idempresa);
							deptoinsert.addAll(Arrays.asList(depto));
							
						}else {
							Departamento depto = new Departamento(clave,descripcion,ordenpresentacion,idempresa);
							deptoupdate.addAll(Arrays.asList(depto));
						}
					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Empresa [" + claveEmpresa + "] \n");
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				} else {
					for (int i = 0; i < deptoinsert.size(); i++) {
						saveDepto(deptoinsert.get(i));
					}
					for (int j = 0; j < deptoupdate.size(); j++) {
						updateDepto(deptoupdate.get(j));
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
		
	
}
