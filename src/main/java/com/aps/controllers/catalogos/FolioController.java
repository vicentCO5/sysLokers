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

import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.EmpresaDao;
import com.aps.dao.catalogos.FolioDao;

@Controller
public class FolioController {
	
	@Autowired
	EmpresaDao daoempresa;
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	FolioDao daofolio;
	
	@RequestMapping("/getindexfolios")
	public ModelAndView viewIndexFolios() {
		return new ModelAndView("folio/index");
	}
	@RequestMapping("/newFolio")
	public ModelAndView viewFormFolios() {
		return new ModelAndView("folio/formFolio");
	}
	@RequestMapping(value="/guardarFolio",produces="application/json; charset=UTF-8" )
	public @ResponseBody Map<String,Object>  save(@RequestParam ("formvalues") String form){
		Map<String, Object> mapa = new HashMap<String, Object>();		
		ArrayList<String> errores = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		try {
		Object formvalues = parser.parse(form);
		JSONObject arrayFormvalues = (JSONObject)formvalues;
		
//			System.out.print(arrayFormvalues.get("mostrador"));
		int empresa_id = 1; // empresa por default por la session
		int usuario_id = 1; // empresa por default por la session
		int almacen = Integer.parseInt((String) arrayFormvalues.get("mostrador"));
		int almacen_id = almacen ;// daoalmacen.findByid(almacen );
		int estatus = 0;
		if( arrayFormvalues.get("estatus") == "on") {
			estatus = 1;
		}else {
			estatus = 0;
		}
		Date date = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timecreate = formateador.format(date);			
		 daofolio.saveFolio(empresa_id, almacen_id, arrayFormvalues.get("serie").toString(), Integer.parseInt((String) arrayFormvalues.get("folio")), arrayFormvalues.get("documento").toString(), estatus, timecreate, usuario_id);
		 mapa.put("exito", "true");
		 mapa.put("error","Guardado correctamente");
		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}
			
		return mapa;
	}
	
	/*lista de folios*/
	@RequestMapping("/listFolios")
	@ResponseBody
	Map <String,Object> listaFolios(){
		Map<String,Object> mapaFolios = new HashMap<String,Object>();
		int empresa_id = 1;
		ArrayList<Object> list = new ArrayList<Object>();
		List<Map<String,Object>> rows = daofolio.listFolios(empresa_id);
		System.out.println(rows);
		String htmlestatus = "";
			for(int i = 0; i < rows.size(); i++) {
				Map<String,Object> mapa = new HashMap<String,Object>();
				Map<?,?> row = rows.get(i);
								
				mapa.put("id", row.get("ID"));
				mapa.put("claveAlmacen", row.get("CLAVE"));
				mapa.put("nombreAlmacen", row.get("NOMBRE"));
				mapa.put("serie", row.get("SERIE").toString());
				mapa.put("folio", row.get("FOLIO"));
				mapa.put("folioInicial", row.get("FOLIOINICIAL"));
				mapa.put("documento", row.get("DOCUMENTO"));
				mapa.put("fechaCreacion", row.get("TIMECREATE").toString());
				mapa.put("fechaModificacion", row.get("TIMECHANGE").toString());
				if( Integer.parseInt( row.get("ESTATUS").toString() ) == 0) {  // 0  activo 1 inactivo
					htmlestatus = "                     <div class=\"switch\"  >\r\n" + 
							"                         <label>OFF\r\n" + 
							"                             <input type=\"checkbox\"  class='changedestatus' data-idchaged='"+row.get("ID")+"' checked=\"\" id=\"estatus\"  ><span class=\"lever\"></span>ON</label>\r\n" + 
							"                      </div> ";
				}else {
					htmlestatus = "                     <div class=\"switch\" >\r\n" + 
							"                         <label>ON\r\n" + 
							"                             <input type=\"checkbox\"  class='changedestatus' data-idchaged='"+row.get("ID")+"' id=\"estatus\"  ><span class=\"lever\"></span>OFF</label>\r\n" + 
							"                      </div> ";
				}
				mapa.put("estatus", htmlestatus );
				String htmlAcciones = "<button onclick=\"deleteFolio("+row.get("ID")+")\" class='btn btn-danger btn-circle btn-sm' title='Eliminar'><i class='fa fa-trash-o bigger-130'></i></button>"; 
				mapa.put("accion", htmlAcciones);
				mapa.put("sts", row.get("ESTATUS") );
				
				list.add(mapa);
			}
		mapaFolios.put("data", list);
		return mapaFolios;		
	}
	/*Eliminar */
	@RequestMapping(value="/deleteFolio")
	@ResponseBody Map<String,Object> deleteFolios(@RequestParam("idfolio")int id ){
		Map<String, Object> mapa = new HashMap<String, Object>();		
		ArrayList<String> errores = new ArrayList<String>();
		try {
			// validar que la serie no este ocupada en algun movimiento
			 daofolio.deleteFolio(id);
			 mapa.put("exito", "true");
			 mapa.put("error","Eliminado correctamente");
			}catch(Exception e) {
				mapa.put("exito", "false");			
				errores.add(e.getMessage());
				mapa.put("error",errores);
			}
		return mapa;
	}
	/*moficicar estatus */
	
	@RequestMapping(value="/changedEstatus")
	@ResponseBody String changedEstatus(@RequestParam("idchanged")int id,@RequestParam("estatus") int status ){
		String mensaje = "";
		try {
			Date date = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeChange = formateador.format(date);
			// validar que la serie no este ocupada en algun movimiento
			 daofolio.changedEstatusfolio(id,status, timeChange);
			 mensaje = "Estatus modificado";			
			}catch(Exception e) {
				mensaje = e.getMessage() ;
			}
		return mensaje;
	}

	
	
}
