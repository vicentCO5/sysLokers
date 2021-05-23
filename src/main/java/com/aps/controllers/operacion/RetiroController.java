package com.aps.controllers.operacion;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.aps.beans.catalogos.Cliente;
import com.aps.dao.catalogos.FolioDao;
import com.aps.dao.operacion.ControlDao;
import com.aps.dao.operacion.CorteDao;
import com.aps.dao.operacion.RetiroDao;

@Controller
public class RetiroController {

    @Autowired
	CorteDao daocorte;
	@Autowired
	ControlDao daocontrol;	
	@Autowired
	RetiroDao daoretiro;
	@Autowired
	FolioDao daofolio;
	
	@RequestMapping("/indexretiro")
	public ModelAndView showViewControl() {
		int empresa_id = 1;
		int almacen_id = 1;
		int usuario_id = 1;
		
		DecimalFormat df = new DecimalFormat("#,###.00");
	   
		List<Map<String, Object>> corteh = daocorte.sessionHome(empresa_id, almacen_id, usuario_id);
		System.out.println(corteh);
		double totalCobro =  daocontrol.totalCobroTiket(almacen_id, usuario_id);
		double totalCaja =  daocorte.montoCaja(empresa_id, almacen_id);
		double totalRetiros =  daocorte.totalRetiros(empresa_id, almacen_id);
		totalRetiros = Double.parseDouble(df.format(totalRetiros));
		double total =  totalCobro + totalCaja - totalRetiros;
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("idusuario", corteh.get(0).get("USUARIO_ID"));
        model.addObject("usuario", corteh.get(0).get("nombreusuario"));
        model.addObject("fecha", corteh.get(0).get("FECHA"));
		model.addObject("horainicio", corteh.get(0).get("TURNOIN"));
		model.addObject("horafin", corteh.get(0).get("TURNOOUT"));		
		model.addObject("clave", corteh.get(0).get("CLAVE"));
		model.addObject("nombre", corteh.get(0).get("NOMBRE"));
		model.addObject("direccion", corteh.get(0).get("DIRECCION"));
		model.addObject("totalcobro", df.format(totalCobro));
		model.addObject("totalCaja", df.format(totalCaja));
		model.addObject("totalRetiro", df.format(totalRetiros));
		model.addObject("total", df.format(total));
		
		model.setViewName("retiro/index");
	   return model;
	}
   
	@RequestMapping("/listaRetiros")
	public @ResponseBody Map<String, Object> getListaRetiros() {
		int almacen_id = 1;
		int usuario_id = 1;
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoretiro.listaRetiros(almacen_id, usuario_id);
		mapa.put("data", list);
		return mapa;
	}
	@RequestMapping("/formRetiro")
	public ModelAndView formRetiros() {
		int empresa_id = 1;
		int almacen_id = 1;
		String documento = "RETIRO";
		Map<String, Object> dataFolio = daofolio.getSerieFolio(empresa_id, almacen_id, documento);
		String serie = dataFolio.get("SERIE").toString();
		String consepto="<option value=''>--Seleccione--</option>";
		
		List<Map<String, Object>> rows = daoretiro.getOptionsRetiros();
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			
			mapa.put("indice", (i+1));			
			mapa.put("idc", row.get("IDC"));
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("descripcion", row.get("DESCRIPCION"));			
			mapa.put("estatus", row.get("ESTATUS"));			
			 consepto +="<option value='"+row.get("CLAVE")+"'>"+row.get("DESCRIPCION")+"</option>";
		}
		
		ModelAndView model = new ModelAndView();
		model.addObject("serie", serie);
		model.addObject("options", consepto);
		model.setViewName("retiro/formRetiro");
		return model;		
	}
	 /* guardar Retiro**/
	@RequestMapping(value="/guardarRetiro", produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object>  saveArticulo(@RequestParam ("formvalues") String form){
		Map<String, Object> mapa = new HashMap<String, Object>();		
		ArrayList<String> errores = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		try {
			
			Object formvalues = parser.parse(form);
			JSONObject arrayFormvalues = (JSONObject)formvalues;
			System.out.println( "arrayFormvalues "+arrayFormvalues);
			int empresa_id = 1; // empresa por default por la session
			int almacen_id = 1; // empresa por default por la session
			int usuario_id = 1; // empresa por default por la session
			String documento = "RETIRO";
		
			Date date = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeCreate = formateador.format(date);			   
			SimpleDateFormat formateador2 = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = formateador2.format(date);	
			
			if(arrayFormvalues.get("serie").toString() !="" && Double.parseDouble(arrayFormvalues.get("importe").toString()) > 0
					&&  arrayFormvalues.get("receptor").toString() !="" ) {				
				Map<String,Object> serieFolio = daofolio.serieFolioMax(empresa_id, almacen_id, documento);	
				String serie = arrayFormvalues.get("serie").toString(); 
				int folio = Integer.parseInt(serieFolio.get("FOLIO").toString());
				double importe = Double.parseDouble(arrayFormvalues.get("importe").toString());
				String motivo = arrayFormvalues.get("consepto").toString(); 
				String receptor = arrayFormvalues.get("receptor").toString(); 
				daoretiro.saveRetiro(serie, folio, fecha, importe, motivo, receptor, usuario_id, almacen_id, empresa_id, timeCreate);
				mapa.put("exito", "true");
				mapa.put("error", "Aplicado correctamente" );
			}else {
				mapa.put("exito", "false");
				mapa.put("error", "Datos incorrectos");
			}

					
		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}			
		return mapa;
	}
	
}
