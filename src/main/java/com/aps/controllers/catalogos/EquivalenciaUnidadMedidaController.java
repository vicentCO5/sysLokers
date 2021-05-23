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

import com.aps.beans.catalogos.EquivalenciaUnidadMedida;
import com.aps.dao.catalogos.EquivalenciaUnidadMedidaDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class EquivalenciaUnidadMedidaController {
	@Autowired
	EquivalenciaUnidadMedidaDao daoeudm;
	@RequestMapping(value = "/showeundmed", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> vieweundmed() {
		Map<String, Object> mapa = new HashMap<String, Object>(); 
		List<Object> list = daoeudm.getEquivUndMeds();
		mapa.put("data", list);
		return mapa;
	}
	@Transactional
	void saveEquivalencia(EquivalenciaUnidadMedida eum) {
		daoeudm.save(eum);
	}
	@Transactional
	void updateEquivalencia(EquivalenciaUnidadMedida eum) {
		daoeudm.update(eum);
	}
	/*
	 * METODOS para importation de equivalencia unidades de medida
	 * */
	@RequestMapping("/getimportequivalencias")
	public ModelAndView viewimportcatEquivUndMed() {
		return new ModelAndView("equivalenciaunidadmedida/formequivunidadmedida");
	}
	@RequestMapping(value = "/doUploadEquivUndMed",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String, Object> uploadFileHandler(@RequestParam("fileeundm") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<EquivalenciaUnidadMedida> eundmedinsert = new ArrayList<EquivalenciaUnidadMedida>();
		List<EquivalenciaUnidadMedida> eundmedupdate = new ArrayList<EquivalenciaUnidadMedida>();
		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				String codigoUnidadOrigen = "";
				String codigoUnidadDestino = "";
				String factor = "";
				for(int row = 1; row < sheet.getRows(); row++) {
					codigoUnidadOrigen = sheet.getCell(0, row).getContents().toString();
					codigoUnidadDestino = sheet.getCell(1, row).getContents().toString();
					factor = sheet.getCell(2, row).getContents().toString();
					int eundmed = daoeudm.getEundByCodigo(codigoUnidadOrigen, codigoUnidadDestino);
					Date fechacambio = new Date();
					SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String fecha = formateador.format(fechacambio);
					if (eundmed == 0) {// no existe y guardamos para insert
						EquivalenciaUnidadMedida eundmedida = new EquivalenciaUnidadMedida(codigoUnidadOrigen,codigoUnidadDestino,Double.parseDouble(factor),fecha);
						eundmedinsert.addAll(Arrays.asList(eundmedida));
					}else {
						EquivalenciaUnidadMedida eundmedida = new EquivalenciaUnidadMedida(codigoUnidadOrigen,codigoUnidadDestino,Double.parseDouble(factor),fecha);
						eundmedupdate.addAll(Arrays.asList(eundmedida));
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				} else {
					for (int i = 0; i < eundmedinsert.size(); i++) {
						saveEquivalencia(eundmedinsert.get(i));
					}
					for (int j = 0; j < eundmedupdate.size(); j++) {
						updateEquivalencia(eundmedupdate.get(j));
					}
					mapa.put("exito", "true");
					mapa.put("error", registrosError);
				}
			} catch (Exception e) {
				mapa.put("exito", "false");
				registrosError.add(e.getMessage());
				mapa.put("error", registrosError);
			}
		}else {
			String cad = "You failed to upload " + " because the file was empty.";
			mapa.put("exito", "false");
			registrosError.add(cad);
			mapa.put("error", registrosError);
		}
		return mapa;
	}
	
	@RequestMapping(value = "/deleteallEquivalencias", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallundmed() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			daoeudm.deleteAllEquivalencias();
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminados correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
			mapa.put("error", registrosError);
		}
		return mapa;
	}
	@RequestMapping("/deleteEquivundMed")
	public @ResponseBody String delete(@RequestParam("codori") String codori,@RequestParam("coddes")String coddes) {
		String cad = "";
		try {
			daoeudm.delete(codori,coddes);
			cad = "Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad = e.getMessage();

		}
		return cad;
	}
}
