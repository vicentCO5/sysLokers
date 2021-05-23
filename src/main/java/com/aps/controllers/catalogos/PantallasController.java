package com.aps.controllers.catalogos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PantallasController {
	@RequestMapping("/getindexarticulos")
	public ModelAndView viewimportArticulos() {
		return new ModelAndView("articulo/index");
	}
	@RequestMapping("/getindexalmacenes")
	public ModelAndView viewalmacenes() {
		return new ModelAndView("almacen/viewalmacen");
	}
	@RequestMapping("/getindexmarca")
	public ModelAndView viewmarca() {
		return new ModelAndView("marca/viewmarca");
	}
	@RequestMapping("/getindexlinea")
	public ModelAndView viewlinea() {
		return new ModelAndView("linea/viewlinea");
	}
	@RequestMapping("/getindexdepto")
	public ModelAndView viewdepto() {
		return new ModelAndView("departamento/viewdepto");
	}
	@RequestMapping("/getindexproveedor")
	public ModelAndView viewprov() {
		return new ModelAndView("proveedor/index");
	}
	@RequestMapping("/getindexunidadmedida")
	public ModelAndView viewundM() {
		return new ModelAndView("unidadmedida/index");
	}
	@RequestMapping("/getindexclasificador")
	public ModelAndView viewclasificadores() {
		return new ModelAndView("clasificador/index");
	}
	@RequestMapping("/getindexventas")
	public ModelAndView viewVentas() {
		return new ModelAndView("ventas/index");
	}
	@RequestMapping("/getindexinventario")
	public ModelAndView viewMaealm() {
		return new ModelAndView("maealm/index");
	}
	@RequestMapping("/indexconfigalmacenes")
	public ModelAndView viewconfigalmacenes() {
		return new ModelAndView("config/config_almacenes");
	}
	@RequestMapping("/indexconfigperiodos")
	public ModelAndView viewconfigperiodos() {
		return new ModelAndView("config/config_periodos");
	}
	@RequestMapping("/indexpycgeneral")
	public ModelAndView viewforecasting() {
		return new ModelAndView("forecastgeneral/index");
	}
	@RequestMapping("/getindextraspaso")
	public ModelAndView viewtraspaso() {
		return new ModelAndView("traspaso/index");
	}
	@RequestMapping("/getindextransito")
	public ModelAndView viewtransito() {
		return new ModelAndView("transito/index");
	}
	@RequestMapping("/indexretail")
	public ModelAndView viewreatail() {
		return new ModelAndView("reatail/index");
	}
}
