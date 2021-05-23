package com.aps.controllers.operacion;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aps.dao.operacion.ControlDao;
import com.aps.dao.operacion.CorteDao;

@Controller
public class CorteController {
	@Autowired
	CorteDao daocorte;
	@Autowired
	ControlDao daocontrol;
	
	@RequestMapping("/indexcorte")
	public ModelAndView showViewControl() {
		int empresa_id = 1;
		int almacen_id = 1;
		int usuario_id = 1;
		DecimalFormat df = new DecimalFormat("#.00");
		DecimalFormat df2 = new DecimalFormat("#,###.00");
	   
		List<Map<String, Object>> corteh = daocorte.sessionHome(empresa_id, almacen_id, usuario_id);
		System.out.println(corteh);
		double totalCobro =  daocontrol.totalCobroTiket(almacen_id, usuario_id);
		double totalCaja =  daocorte.montoCaja(empresa_id, almacen_id);
		double totalRetiros =  daocorte.totalRetiros(empresa_id, almacen_id);
		totalRetiros = Double.parseDouble(df.format(totalRetiros));
		double total =  totalCobro + totalCaja - totalRetiros;
		total = Double.parseDouble(df.format(total));
		ModelAndView model = new ModelAndView();		        
		model.addObject("idusuario", corteh.get(0).get("USUARIO_ID"));
        model.addObject("usuario", corteh.get(0).get("nombreusuario"));
        model.addObject("fecha", corteh.get(0).get("FECHA"));
		model.addObject("horainicio", corteh.get(0).get("TURNOIN"));
		model.addObject("horafin", corteh.get(0).get("TURNOOUT"));		
		model.addObject("clave", corteh.get(0).get("CLAVE"));
		model.addObject("nombre", corteh.get(0).get("NOMBRE"));
		model.addObject("direccion", corteh.get(0).get("DIRECCION"));
		model.addObject("totalcobro", df2.format(totalCobro));
		model.addObject("totalCaja", df2.format(totalCaja));
		model.addObject("totalRetiro", totalRetiros);
		model.addObject("total", df2.format(total));
		
		model.setViewName("corte/index");
	   return model;
	}
	@RequestMapping(value = "/listaCobrado", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> getListaCobrado() {
		int almacen_id = 1;
		int usuario_id = 1;
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daocontrol.listaCobrados(almacen_id, usuario_id);
		mapa.put("data", list);
		return mapa;
	}
	
}
