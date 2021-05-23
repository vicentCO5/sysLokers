package com.aps.controllers.operacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.operacion.ResurtidoDao;

@Controller
public class ResurtidoController {
	
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	ResurtidoDao daoresurtido;
	
	@RequestMapping("/indexcalendario")
	public ModelAndView getIndexcalendario() {
		return new ModelAndView("/calendario/index");
	}

	
}
