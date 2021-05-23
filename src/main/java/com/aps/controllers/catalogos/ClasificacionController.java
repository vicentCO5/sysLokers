package com.aps.controllers.catalogos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aps.dao.catalogos.ClasificacionDao;
import com.aps.dao.catalogos.EmpresaDao;

@Controller
public class ClasificacionController {
	@Autowired
	EmpresaDao daoempresa;
	@Autowired
	ClasificacionDao daoclasificacion;

	@RequestMapping("/getindexclass")
	public ModelAndView viewIndexClasificacion() {
		return new ModelAndView("clasificacion/index");
	}

	@RequestMapping("/newClasificacion")
	public ModelAndView viewFormClasificacion() {
		return new ModelAndView("clasificacion/formClasificacion");
	}

	@RequestMapping(value = "/guardarClasificacion", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> save(@RequestParam("formvalues") String form) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> errores = new ArrayList<String>();

		
		try {
			JSONParser parser = new JSONParser();
			Object formvalues = parser.parse(form);
			JSONObject arrayFormvalues = (JSONObject) formvalues;
			
			int empresa_id = 1; // empresa por default por la session
			int usuario_id = 1; // empresa por default por la session
			int estatus = 0;
			if (arrayFormvalues.get("estatus") == "on") {
				estatus = 1;
			} else {
				estatus = 0;
			}
			// validar la clave y descripcion
			if ( daoclasificacion.getByClave(empresa_id, arrayFormvalues.get("clave").toString()) ) {
				if ( daoclasificacion.getByDescripcion(empresa_id, arrayFormvalues.get("descripcion").toString()) ) {
					try {
						Date date = new Date();
						SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String timecreate = formateador.format(date);
						daoclasificacion.saveClasificacion(empresa_id, arrayFormvalues.get("clave").toString(),
								arrayFormvalues.get("descripcion").toString(), estatus, timecreate, usuario_id);
						mapa.put("exito", "true");
						mapa.put("error", "Guardado correctamente");
					} catch (Exception e) {
						mapa.put("exito", "false");
						errores.add(e.getMessage());
						mapa.put("error", errores);
					}

				} else {
					mapa.put("exito", "false");
					mapa.put("error", "La descripción ya existe");
				}
			} else {
				mapa.put("exito", "false");
				mapa.put("error", "Ya existe la Clave");
			}
		} catch (Exception e) {
			mapa.put("exito", "false");
			errores.add(e.getMessage());
			mapa.put("error", errores);
		}
		return mapa;
	}

	/* lista de listClasificacion */
	@RequestMapping("/listClasificacion")
	@ResponseBody
	Map<String, Object> listaClasificacion() {
		Map<String, Object> mapaClass = new HashMap<String, Object>();
		int empresa_id = 1;
		ArrayList<Object> list = new ArrayList<Object>();
		List<Map<String, Object>> rows = daoclasificacion.listCalsificacion(empresa_id);
		System.out.println(rows);
		String htmlestatus = "";
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);

			mapa.put("id", row.get("ID"));
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("descripcion", row.get("DESCRIPCION"));
			mapa.put("fechaCreacion", row.get("TIMECREATE").toString());
			if (Integer.parseInt(row.get("ESTATUS").toString()) == 0) { // 0 activo 1 inactivo
				htmlestatus = "                     <div class=\"switch\"  >\r\n"
						+ "                         <label>OFF\r\n"
						+ "                             <input type=\"checkbox\"  class='changeEstatusClass' data-idchaged='"
						+ row.get("ID") + "' checked=\"\" id=\"estatus\"  ><span class=\"lever\"></span>ON</label>\r\n"
						+ "                      </div> ";
			} else {
				htmlestatus = "                     <div class=\"switch\" >\r\n"
						+ "                         <label>ON\r\n"
						+ "                             <input type=\"checkbox\"  class='changeEstatusClass' data-idchaged='"
						+ row.get("ID") + "' id=\"estatus\"  ><span class=\"lever\"></span>OFF</label>\r\n"
						+ "                      </div> ";
			}
			mapa.put("estatus", htmlestatus);
			String htmlAcciones = "<button onclick=\"deleteClasificacion(" + row.get("ID")
					+ ")\" class='btn btn-danger btn-circle btn-sm' title='Eliminar'><i class='fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", htmlAcciones);
			mapa.put("sts", row.get("ESTATUS"));

			list.add(mapa);
		}
		mapaClass.put("data", list);
		return mapaClass;
	}
	/*
	 * Eliminar
	 */

	@RequestMapping(value = "/deleteClasificacion")
	@ResponseBody
	Map<String, Object> deleteClasificacion(@RequestParam("idclass") int id) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> errores = new ArrayList<String>();
		try {
			// validar que la serie no este ocupada en algun movimiento
			daoclasificacion.deleteCalsificacion(id);
			mapa.put("exito", "true");
			mapa.put("error", "Eliminado correctamente");
		} catch (Exception e) {
			mapa.put("exito", "false");
			errores.add(e.getMessage());
			mapa.put("error", errores);
		}
		return mapa;
	}

	/* moficicar estatus */
	@RequestMapping(value = "/changedEstatusClass")
	@ResponseBody
	String changedEstatus(@RequestParam("idchanged") int id, @RequestParam("estatus") int status) {
		String mensaje = "";
		try {
			// validar que la serie no este ocupada en algun movimiento
			daoclasificacion.changedEstatusClasificacion(id, status);
			mensaje = "Estatus modificado";
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

}
