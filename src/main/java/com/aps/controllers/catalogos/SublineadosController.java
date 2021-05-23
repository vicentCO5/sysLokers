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

import com.aps.beans.catalogos.Sublineados;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.SublineadosDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class SublineadosController {
	@Autowired
	SublineadosDao doasublineados;
	@Autowired
	AlmacenDao daoalmacen;

	@RequestMapping(value = "/showSublineasDos", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> viewSublineasdos() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = doasublineados.getSublineasdos();
		mapa.put("data", list);
		return mapa;
	}

	@RequestMapping(value = "/deleteallSublineasDos", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallSublineas() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			doasublineados.deleteAllSublineados();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
		}
		return mapa;
	}

	@Transactional
	void saveSublineados(Sublineados sublineados) {
		doasublineados.save(sublineados);
	}

	@Transactional
	void updateSublineados(Sublineados sublineados) {
		doasublineados.update(sublineados);
	}

	@RequestMapping("/deleteSublineaDos")
	public @ResponseBody String deleteSublineados(@RequestParam("id") int id,
			@RequestParam("idempresa") int idempresa) {
		String cad = "";
		try {
			doasublineados.delete(id, idempresa);
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
	@RequestMapping("/getimportSublineasDos")
	public ModelAndView viewimportAlms() {
		return new ModelAndView("sublineados/formsublineados");
	}

	@RequestMapping(value = "/doUploadSubLineados", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileSubLineados(
			@RequestParam("fileSublineados") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Sublineados> sublineasinsert = new ArrayList<Sublineados>();
		List<Sublineados> sublineasupdate = new ArrayList<Sublineados>();
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
						int idsublineados = doasublineados.getSublineadosbyclave_empresaid(Integer.parseInt(clave),
								idempresa);
						if (idsublineados == 0) { // no existe y guardamos para insert
							Sublineados sublineados = new Sublineados(Integer.parseInt(clave), descripcion, idempresa);
							sublineasinsert.addAll(Arrays.asList(sublineados));
						} else {
							Sublineados sublineados = new Sublineados(Integer.parseInt(clave), descripcion, idempresa);
							sublineasupdate.addAll(Arrays.asList(sublineados));
						}
					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Empresa [" + claveEmpresa + "] \n");
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				} else {
					for (int i = 0; i < sublineasinsert.size(); i++) {
						saveSublineados(sublineasinsert.get(i));
					}
					for (int j = 0; j < sublineasupdate.size(); j++) {
						updateSublineados(sublineasupdate.get(j));
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
