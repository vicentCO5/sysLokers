package com.aps.controllers.seguridad;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.aps.beans.seguridad.Perfil;
import com.aps.dao.seguridad.PerfilDao;
import com.aps.dao.seguridad.UsuarioDao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Controller
public class PerfilController {
	@Autowired
	PerfilDao daoperfil;
	@Autowired
	UsuarioDao daousuario;
	
	@RequestMapping("/listperfiles")
	public ModelAndView viewindexuser() {
		return new ModelAndView("perfil/index");
	}
	@RequestMapping(value = "/listJSONPerfil", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> listPerfiles() {		 
		Map<String, Object> mapa = new HashMap<String, Object>();		
		List<Object> list = daoperfil.finfAll();
		mapa.put("data", list);		
		return mapa;
	}
	
	@RequestMapping("/createPerfil")
	public ModelAndView viewformcreate() {
		return new ModelAndView("perfil/create");
	}
	
	@RequestMapping("/form2")
	public ModelAndView viewform2() {
		return new ModelAndView("perfil/form2");
	}
	@RequestMapping(value="/savePerfil",produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object> save(@RequestParam("formvalues")String form,
			 @RequestParam("jsonvalues")String jsonvalues,
			 @RequestParam("jsonusuarios")String jsonusuarios) {
		 JSONParser parser = new JSONParser();
		 Map<String, Object> mapa = new HashMap<String, Object>();
		 List<Object> errores=new ArrayList<Object>();
		try {
			Object values = parser.parse(jsonvalues);
			Object usuarios = parser.parse(jsonusuarios);
			Object formvalues = parser.parse(form);
			JSONArray arrayvalues = (JSONArray)values;
			JSONArray arrayusuarios = (JSONArray)usuarios;
			JSONObject arrayformvalues = (JSONObject)formvalues;
			Perfil pe = new Perfil(arrayformvalues.get("nombrep").toString(),arrayformvalues.get("descripcionp").toString(),arrayformvalues.get("tipo").toString());
			int insert = daoperfil.saveperfil(pe);
			Perfil perfilInstance = daoperfil.findByName(arrayformvalues.get("nombrep").toString());
			if(insert==1) {
				daoperfil.saveperfilUrl(perfilInstance.getId(),arrayvalues);
				daousuario.updateperfilUsuario(arrayusuarios, perfilInstance.getId());
				mapa.put("exito", "true");
			}
//			System.out.println(arrayvalues);
//			System.out.println(arrayusuarios);
//			System.out.println(arrayformvalues.get("nombrep"));
		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}
		 return mapa;		
	}
	
	@RequestMapping("/editPerfil")
	public ModelAndView viewformedit(@RequestParam ("id")int id) {
		Perfil perfilInstance = daoperfil.findById(id);		
		ModelAndView model = new ModelAndView();
		model.addObject("idperfil", id);
		model.addObject("nombre", perfilInstance.getNombre());
		model.addObject("tipo", perfilInstance.getTipo());
		model.addObject("descripcion", perfilInstance.getDescripcion());
		List<Object> listausers = daousuario.findByUsersByPerfil(id);
		String jsonA = JSONArray.toJSONString(listausers);
		model.addObject("lista", jsonA);
		model.setViewName("perfil/edit");
		return model;
	}
	
	@RequestMapping(value="/updatePerfil",produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object> update(@RequestParam ("id")int id,
			@RequestParam("formvalues")String form,
			@RequestParam("jsonvalues")String jsonvalues,
			@RequestParam("jsonusuarios")String jsonusuarios) {
		 JSONParser parser = new JSONParser();
		 Map<String, Object> mapa = new HashMap<String, Object>();
		 List<Object> errores=new ArrayList<Object>();
		try {
			Object values = parser.parse(jsonvalues);
			Object usuarios = parser.parse(jsonusuarios);
			Object formvalues = parser.parse(form);
			JSONArray arrayvalues = (JSONArray)values;
			JSONArray arrayusuarios = (JSONArray)usuarios;
			JSONObject arrayformvalues = (JSONObject)formvalues;
			boolean clearurl = daoperfil.deleteurlperfil(id);
			daoperfil.update(id,arrayformvalues.get("nombrep").toString(),arrayformvalues.get("descripcionp").toString(),arrayformvalues.get("tipo").toString());
			System.out.println(clearurl);
			if(clearurl) {
				daoperfil.saveperfilUrl(id,arrayvalues);
				daousuario.updateperfilUsuario(arrayusuarios, id);
				mapa.put("exito", "true");
			}
//			System.out.println(arrayvalues);
//			System.out.println(arrayusuarios);
//			System.out.println(arrayformvalues.get("nombrep"));
		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}
		 return mapa;		
	}
}
