package com.aps.controllers.catalogos;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.aps.beans.catalogos.Cliente;
import com.aps.dao.catalogos.ArtxempresaDao;
import com.aps.dao.catalogos.ClienteDao;

@Controller
public class ClienteController {
	@Autowired
	ArtxempresaDao daoartxempresa;
	@Autowired
	ClienteDao daocliente;
	
	@RequestMapping("/getindexclientes")
	public ModelAndView viewindexCliente() {
		return new ModelAndView("cliente/index");
	}
	@RequestMapping("/formClientes")
	public ModelAndView viewFormCliente() {
		return new ModelAndView("cliente/formCliente");
	}
	/*Mostrar la lista de clientes */
	@RequestMapping("/dataClientes")
	public @ResponseBody
	Map <String, Object> listaClientes(){
		/* * @empresa_id * */
		int empresa_id = 1;
		Map<String, Object> mapAlmacen = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		List<Map<String,Object>> rows = daocliente.listaClientes(empresa_id); 
		
		for(int i = 0; i < rows.size(); i++) {
			Map<String,Object> mapa = new HashMap<String,Object>();
			Map<?,?> row = rows.get(i);
			
			mapa.put("indice", i +1 );
			mapa.put("codigo", row.get("CODIGO"));
			mapa.put("nombrecompleto", row.get("NOMBRE") +" "+ row.get("NOMBRE") );
			mapa.put("direccion", row.get("DIRECCION"));
			mapa.put("colonia", row.get("COLONIA"));
			mapa.put("telefono", row.get("TELEFONO"));
			mapa.put("email", row.get("EMAIL"));
			
			String html="<button onclick=\"editarCliente("+row.get("IDC")+")\" class='btn btn-info btn-circle btn-sm' title='Editar'><i class='fa fa-pencil bigger-130'></i></button>";
            html+="&nbsp;&nbsp;<button onclick=\"deleteCliente("+row.get("IDC")+")\" class='btn btn-danger btn-circle btn-sm' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("acciones", html);
			
			list.add(mapa);
		}
		mapAlmacen.put("data", list);
		return mapAlmacen;
	}
	
	@RequestMapping("/deleteClientes")
	public @ResponseBody String deleteCliente(@RequestParam("idx")int id) {
		String cad="";
		try {
			daocliente.deleteCliente(id);
			cad="Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad=e.getMessage();
			
		}
		return cad;
	}
	@Transactional
	boolean save(Cliente c) {
		if (daocliente.save(c)) {
			return true;
		} else {
			return false;
		}

	}
	@RequestMapping(value="/guardarCliente",produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object>  saveArticulo(@RequestParam ("formvalues") String form){
		Map<String, Object> mapa = new HashMap<String, Object>();		
		ArrayList<String> errores = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		try {
			
			Object formvalues = parser.parse(form);
			JSONObject arrayFormvalues = (JSONObject)formvalues;
			System.out.println( "arrayFormvalues "+arrayFormvalues);
			int empresa_id = 1; // empresa por default por la session
			int usuario_id = 1; // empresa por default por la session
		
			Date date = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timecreate = formateador.format(date);	
	
			ArrayList<String> registrosError = new ArrayList<String>();
			Map<String, Object> mapaExito = new HashMap<String, Object>();
			List<Object> objectexito    = new ArrayList<Object>();
			int idcliente = daocliente.getClientebyId(arrayFormvalues.get("codigo").toString());
			int estatus = 0;
			System.out.println(" idcliente "+idcliente);
			if(idcliente == 0) {				
				
				mapaExito.put("empresa_id",empresa_id);
				mapaExito.put("codigo", arrayFormvalues.get("codigo").toString());
			
				mapaExito.put("nombre", arrayFormvalues.get("nombre").toString());
				mapaExito.put("apellidos", arrayFormvalues.get("apellidos").toString());				
				mapaExito.put("direccion", arrayFormvalues.get("direccion").toString());
				mapaExito.put("colonia", arrayFormvalues.get("colonia").toString());
				mapaExito.put("municipio", arrayFormvalues.get("municipio").toString());				
				mapaExito.put("estado", arrayFormvalues.get("estado").toString());								
										
				mapaExito.put("pais", arrayFormvalues.get("pais").toString() );				
				mapaExito.put("telefono", arrayFormvalues.get("telefono").toString());				
				mapaExito.put("email", arrayFormvalues.get("email").toString());			
					
				mapaExito.put("timecreate", timecreate);
				mapaExito.put("usuario_id", usuario_id);
				
				objectexito.add(mapaExito);
				System.out.println(" hola :"+mapaExito );
			}else {
				registrosError.add("El Cliente ya existe "+arrayFormvalues.get("codigo").toString()+"  \n");
			}
			System.out.println("registrosError "+registrosError);
			
			if (registrosError.size() > 0) {
				mapa.put("exito", "false");
				mapa.put("error", registrosError);
				
			} else {
				System.out.println("mapaExito : "+mapaExito);
				int codigoDefault = 0;
				for (Iterator<Object> it = objectexito.iterator(); it.hasNext();) {
					@SuppressWarnings("unchecked")
					Map<String, Object> o = (Map<String, Object>) it.next();						
					System.out.println(" OK"+ o.get("codigo").toString() );	
//					  Integer.parseInt(o.get("empresa_id").toString())
						Cliente cliente = new Cliente(empresa_id, 
								o.get("codigo").toString(), 
								o.get("nombre").toString(), 
								o.get("apellidos").toString(),							
								o.get("direccion").toString(), 
								o.get("colonia").toString(),
								o.get("municipio").toString() ,
								o.get("estado").toString(), 
								o.get("pais").toString(), 
								o.get("telefono").toString() ,
								o.get("email").toString(),
								o.get("timecreate").toString()
								);
	//					 Articulo(int empresa_id,String numart, String numartAlterno, String nomart, String uniart, 
	//							 int clasificacion_id,String color, double peso,double volumenn,double alto,double largo,double ancho, int estatus,String timecreate, String timechage, int usuario_id ) {
						System.out.println(" OK"+ o.get("codigo").toString() );	
						if (save(cliente)) {
							System.out.println("Guardado OK"+ o.get("codigo").toString() );		
							mapa.put("exito", "true");
							mapa.put("error", "Guardado OK"+ o.get("codigo").toString() );														
					    }
				}
				
			}			

		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}			
		return mapa;
	}
}
