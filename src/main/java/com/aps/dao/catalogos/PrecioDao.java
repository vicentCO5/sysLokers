package com.aps.dao.catalogos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class PrecioDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public boolean crearPrecios(int empresa_id, int numart_id,String numart, Map<String,Map<String,Object>> listClasificacion, String timecreate,int usuario_id ) { 
		String sqlHeader = "INSERT INTO `precio`(`EMPRESA_ID`, `NUMART_ID`, `NUMART`, `CLASIFICACION_ID`, `CLAVE`, `NUMPRECIO`, `PRECIO`, `ESTATUS`, `TIMECREATE`, `TIMECHANGE`, `USUARIO_ID`) VALUES";
		 String sqlBody = "";
		Map<String,Object> datos = null;
		int i = 1;
		   for(String clave : listClasificacion.keySet()) {									   
			   datos = listClasificacion.get(clave); 																		  									   
										  									   
			   sqlBody += "( '"+empresa_id+"','"+numart_id+"','"+numart+"','"+datos.get("id")+"','"+datos.get("clave")+"','"+i+"','0','0','"+timecreate+"','"+timecreate+"','"+usuario_id+"' ),";
			   i++;
		   }
		// Insertamos cadena 
		   String sql = sqlHeader + sqlBody.substring(0, sqlBody.length()-1 )+";";
			System.out.println(sql);
			template.update(sql);
		
		return true;
	} 
	
	/*Lista de articulos*/
	/**
	 * lista de getlistArticulos
	 * */
	public List<Object> getlistaArticuloPrecio(int empresa_id) {
		List<Object> deptos = new ArrayList<Object>();
		String sql = "SELECT p.*,c.CLAVE,c.DESCRIPCION,c.ESTATUS AS ESTATUSCLASS,a.NUMARTALTERNO,a.NOMART,a.UNIDAD,a.ESTATUS AS ESTATUSSKU   FROM `precio` p\r\n" + 
				"INNER JOIN config_clasificacion c ON p.CLASIFICACION_ID = c.ID\r\n" + 
				"INNER JOIN maearticulo a on p.NUMART_ID = a.ID\r\n" + 
				"WHERE P.EMPRESA_ID='"+empresa_id+"'\r\n" + 
				"ORDER BY a.NUMART,p.NUMPRECIO ";
		String htmlestatus="";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("numart", row.get("NUMART"));
			mapa.put("numartalterno", row.get("NUMARTALTERNO"));
			mapa.put("nomart", row.get("NOMART"));
			mapa.put("uniart", row.get("UNIDAD"));
			mapa.put("estatussku", row.get("ESTATUSSKU"));			
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("descripcion", row.get("DESCRIPCION"));
			mapa.put("estatusclass", row.get("ESTATUSCLASS"));
			mapa.put("numprecio", row.get("NUMPRECIO"));
			String htmlprecio = "<div class='form-group'>\r\n" + 					
					"   <input type='number' class='form-control form-control-line changedPrecio' title=' Precio actual "+row.get("PRECIO")+"' data-idchaged = '"+row.get("IDP")+"' data-skuchaged='"+row.get("NUMART_ID")+"' data-numprechaged='"+row.get("NUMPRECIO")+"' value='"+row.get("PRECIO")+"'> "
					+ "</div>";
			mapa.put("precio", htmlprecio );
			mapa.put("estatusnow", row.get("ESTATUS"));
			if(  Integer.parseInt(row.get("ESTATUS").toString()) == 0 ) {  // 0  activo 1 inactivo
				htmlestatus = "                     <div class=\"switch\"  >\r\n" + 
						"                         <label>OFF\r\n" + 
						"                             <input type=\"checkbox\"  class='changedestatusPrecio' data-idchaged='"+row.get("IDP")+"' data-skuchaged='"+row.get("NUMART_ID")+"' data-numprechaged='"+row.get("NUMPRECIO")+"' checked=\"\" id=\"estatus\"  ><span class=\"lever\"></span>ON</label>\r\n" + 
						"                      </div> ";
			}else {
				htmlestatus = "                     <div class=\"switch\" >\r\n" + 
						"                         <label>ON\r\n" + 
						"                             <input type=\"checkbox\"  class='changedestatusPrecio' data-idchaged='"+row.get("IDP")+"' data-skuchaged='"+row.get("NUMART_ID")+"'  data-numprechaged='"+row.get("NUMPRECIO")+"' id=\"estatus\"  ><span class=\"lever\"></span>OFF</label>\r\n" + 
						"                      </div> ";
			}
			mapa.put("estatusprecio", htmlestatus );		
			
			
			deptos.add(mapa);
		}
		return deptos;
	}
	
	/*SELECCIONAR EL PRECIO DE UN ARTICULO
	 * @param numart
	 * @param clave clasificacion*/

	public List<Map<String, Object>> selectPrecioArticuloClasificacion(String numart, String clave){		
		
		String sql = "SELECT p.*,c.DESCRIPCION FROM `precio` p\r\n" + 
				"	INNER JOIN config_clasificacion c ON p.CLASIFICACION_ID=c.ID\r\n" + 
				"	WHERE p.NUMART='"+numart+"' AND p.CLAVE='"+clave+"'";
		System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
				 		
		return rows;		
	}
	public Map<String,Object> getBySKUClasificacionPrecio(int idempresa, String numart, String clave) {
		String sql = "SELECT p.*,c.DESCRIPCION FROM `precio` p\r\n" + 
				"	INNER JOIN config_clasificacion c ON p.CLASIFICACION_ID=c.ID\r\n" + 
				"	WHERE p.EMPRESA_ID='"+idempresa+"' AND p.NUMART='"+numart+"' AND p.CLAVE='"+clave+"'";
		System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
		Map<String, Object> mapa = new HashMap<String, Object>();
		// inicializar
		mapa.put("idp", "0");
		mapa.put("clasificacionid","");
		mapa.put("clave", "");
		mapa.put("descripcion","");
		mapa.put("numprecio", "0" );
		mapa.put("precio", "0");
		
		for (int i = 0; i < rows.size(); i++) {			
			Map<?, ?> row = rows.get(i);
			mapa.put("idp",  row.get("IDP"));
			mapa.put("clasificacionid", row.get("CLASIFICACION_ID"));
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("descripcion", row.get("DESCRIPCION"));
			mapa.put("numprecio", row.get("NUMPRECIO"));
			mapa.put("precio", row.get("PRECIO"));

		}		
		return mapa;
	}
	
	/*modificar estatus precio*/
	public int changedEstatusPrecio(int id,String numart,int numprecio,int status, String timeChange, int usuario_id) {		
		String sql = "UPDATE  `precio` SET `TIMECHANGE`='"+timeChange+"', ESTATUS='"+status+"', USUARIO_ID = '"+usuario_id+"' WHERE IDP='"+id+"'";
		return template.update(sql);
	}
	
	/*modificar estatus precio
	 * @param id
	 * @param numart id
	 * @param numprecio
	 * @param timechaged
	 * param usuario_id*/
	public int changedPrecio(int id,String numart,int numprecio,double precio, String timeChange, int usuario_id) {		
		String sql = "UPDATE  `precio` SET `TIMECHANGE`='"+timeChange+"', PRECIO='"+precio+"', USUARIO_ID = '"+usuario_id+"' WHERE IDP='"+id+"'";
		return template.update(sql);
	}

	
	
	
}
