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

import com.aps.beans.catalogos.Subdepartamento;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.SubdepartamentoDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class SubdepartamentoController {
	@Autowired
	SubdepartamentoDao daosubdepto;
	@Autowired
	AlmacenDao daoalmacen;
	@RequestMapping(value = "/showSubdeptos", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> viewDeptos() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daosubdepto.getSubdepartamentos();
		mapa.put("data", list);
		return mapa;
	}
	@RequestMapping(value = "/deleteallSubDeptos", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallSubDepto() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			daosubdepto.deleteAllSubDeptos();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
		}
		return mapa;
	}
	@RequestMapping("/deleteSubdepto")
	public @ResponseBody String deleteSubdeptos(@RequestParam("id") int id,
			@RequestParam("idempresa") int idempresa) {
		String cad = "";
		try {
			daosubdepto.delete(id, idempresa);
			cad = "Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad = e.getMessage();

		}
		return cad;
	}
	@Transactional
	void saveSubdepto(Subdepartamento d) {
		daosubdepto.save(d);
	}
	@Transactional
	void updateSubdepto(Subdepartamento d) {
		daosubdepto.update(d);
	}
	/*
	 * METODOS para importacion de Departamentos xls
	 */
	@RequestMapping("/getimportSubdeptos")
	public ModelAndView viewimportSubDepto() {
		return new ModelAndView("subdepartamento/formsubdepto");
	}
	@RequestMapping(value = "/doUploadSubDepto", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileSubLineados(
			@RequestParam("filesubdepto") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Subdepartamento> subdeptoinsert = new ArrayList<Subdepartamento>();
		List<Subdepartamento> subdeptoupdate = new ArrayList<Subdepartamento>();
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
						int iddepto = daosubdepto.getSubdeptobyempresaid_clave(Integer.parseInt(clave), idempresa);
						if (iddepto == 0) { // no existe y guardamos para insert
							Subdepartamento depto = new Subdepartamento(clave,descripcion,ordenpresentacion,String.valueOf(idempresa));
							subdeptoinsert.addAll(Arrays.asList(depto));
						}else {
							Subdepartamento depto = new Subdepartamento(clave,descripcion,ordenpresentacion,String.valueOf(idempresa));
							subdeptoupdate.addAll(Arrays.asList(depto));
						}
					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Empresa [" + claveEmpresa + "] \n");
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				} else {
					for (int i = 0; i < subdeptoinsert.size(); i++) {
						saveSubdepto(subdeptoinsert.get(i));
					}
					for (int j = 0; j < subdeptoupdate.size(); j++) {
						updateSubdepto(subdeptoupdate.get(j));
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
