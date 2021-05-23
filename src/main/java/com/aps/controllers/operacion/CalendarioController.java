package com.aps.controllers.operacion;

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

import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.operacion.CalendarioDao;

@Controller
public class CalendarioController {
	
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	CalendarioDao daocalendario;
	
	@RequestMapping(value = "/listaeventos",produces = "application/json; charset=UTF-8")
	public @ResponseBody 
	List<Object> listEvents(){
		List<Object> listEvent=daocalendario.listaEventos();	 
		return listEvent;
				
	}
	@RequestMapping("/formprogramar")
	public ModelAndView getFormprogramaro(@RequestParam("fechaEvent") String fechaEvent,@RequestParam("tipocalendario") String tipocalendario) {
		ModelAndView model = new ModelAndView();
		model.addObject("fechaEvent", fechaEvent);
		model.addObject("tipocalendario", tipocalendario);
		model.setViewName("/calendario/form_programar");
		return model;
	}
	@RequestMapping("/listNotselectAlmacen")
	public @ResponseBody
	Map <String, Object> notSelectAlmacen(@RequestParam("fechaEvent") String fechaEvent,@RequestParam("tipocalendario") String tipocalendario){
		/* * @empresa_id * */
		int empresa_id = 1;
		Map<String, Object> mapAlmacen = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		List<Map<String,Object>> rows = daocalendario.listaNotAlmacenSeleccionado(empresa_id, fechaEvent, tipocalendario);
		for(int i = 0; i < rows.size(); i++) {
			Map<String,Object> mapa = new HashMap<String,Object>();
			Map<?,?> row = rows.get(i);
			
			mapa.put("indice","<input type='checkbox' class='' value='"+row.get("CLAVE")+"' data-almacenid='"+row.get("ID")+"' checked >" );
			mapa.put("almacen_id", row.get("ID"));
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("nombre", row.get("NOMBRE"));
			
			list.add(mapa);
		}
		mapAlmacen.put("data", list);
		return mapAlmacen;
	}
	
	@RequestMapping("/listYesselectAlmacen")
	public @ResponseBody
	Map <String, Object> yesSelectAlmacen(@RequestParam("fechaEvent") String fechaEvent, @RequestParam("tipocalendario") String tipocalendario){
		/*
		 * @empresa_id
		 * */
		int empresa_id = 1;
		Map<String, Object> mapAlmacen = new HashMap<String,Object>();
		List<Object> list = new ArrayList<Object>();
		
		List<Map<String,Object>> rows = daocalendario.listaYesAlmacenSeleccionado(empresa_id, fechaEvent, tipocalendario);
		
		for(int i = 0; i< rows.size(); i++) {
			Map<String,Object> mapa = new HashMap<String,Object>();
			Map<?,?> row =  rows.get(i);
			
			mapa.put("indice","<input type='checkbox' value='"+row.get("CLAVE")+"' data-almacenid='"+row.get("ID")+"'>" );
			mapa.put("almacen_id", row.get("ID"));
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("nombre", row.get("NOMBRE"));
			
			list.add(mapa);
		}
		mapAlmacen.put("data", list);
		return mapAlmacen;
	}
	
	
}
