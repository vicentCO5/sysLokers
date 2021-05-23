package com.aps.controllers.catalogos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aps.beans.catalogos.Clasificador;
import com.aps.dao.catalogos.ClasificadorDao;

@Controller
public class ClasificadorController {
	@Autowired 
	ClasificadorDao daoclasificador;
	 
	@RequestMapping(value = "/getlistclasificadores", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> getClasificadores() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoclasificador.getClasificadores();
		mapa.put("data", list);
		return mapa;
	}
	
	@RequestMapping("/updatelevel")
	public @ResponseBody String updatelevel(@RequestParam("clave") String clave,@RequestParam("newvalue") int newvalue) {
		String cad = "";
		try {
			daoclasificador.updatelevel(clave,newvalue);
			cad = "Nivel actualizado";
		} catch (Exception e) {
			cad = e.getMessage();

		}
		return cad;
	}
	@RequestMapping("/updateclasificador")
	public @ResponseBody String updateclasificador(@RequestParam("clave") String clave,@RequestParam("oldvalue") boolean oldvalue,@RequestParam("modulo") String modulo) {
		String cad = "";
		try {
			daoclasificador.updateClasificador(clave,oldvalue,modulo);
			cad = "Nivel actualizado";
		} catch (Exception e) {
			cad = e.getMessage();

		}
		return cad;
	}
	
	@RequestMapping("/createclasificador")
	public ModelAndView createclasificador() {
		return new ModelAndView("clasificador/form","command",new Clasificador());
	}
	
	
}
