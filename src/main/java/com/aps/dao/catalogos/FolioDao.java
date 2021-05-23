package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class FolioDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	/*
	 * Guardar Folio
	 * @param empresa_id
	 * @param almacen_id
	 * @param serie
	 * @param folio
	 * @param documento
	 * @param estatus
	 * @param timecreate
	 * @param usuario_id
	 * */
	public  void saveFolio(int empresa_id, int almacen_id, String serie, int folio, String documento, int estatus,String timecreate, int usuario_id ) {
		String sql = "INSERT INTO `folios`( `EMPRESA_ID`, `ALMACEN_ID`, `SERIE`, `FOLIO`, `FOLIOINICIAL`, `DOCUMENTO`, `ESTATUS`, `TIMECREATE`, `TIMECHANGE`, `USUARIO_ID`)"
				+ "VALUES("+empresa_id+","+almacen_id+",'"+serie+"',"+folio+","+folio+",'"+documento+"',"+estatus+",'"+timecreate+"','"+timecreate+"','"+usuario_id+"' )";
		template.update(sql);
		
	}
	/* Lista de folios
	 * @param empresa_id
	*/
	public List<Map<String, Object>> listFolios(int empresa_id){
		String sql = "SELECT f.*,a.NOMBRE,a.CLAVE FROM `folios` f\r\n" + 
				"INNER JOIN almacen a ON f.ALMACEN_ID = a.ID\r\n" + 
				"WHERE f.EMPRESA_ID = '"+empresa_id+"'  ";
		System.out.println(sql);
		List<Map<String,Object>> rows = template.queryForList(sql);				
	  return rows;
	}
	/*Eliminar folios
	 * @param id
	 * */
	public int deleteFolio(int id) throws SQLException  {
		String sql = "DELETE FROM folios WHERE ID="+id+" ";
		return template.update(sql);
	}
	/*
	 * Modificar estatus
	 * @param id folio
	 * @param status 
	 * */
	public int changedEstatusfolio(int id,int status, String timeChange) {
		String sql = "UPDATE folios SET ESTATUS="+status+", TIMECHANGE='"+timeChange+"'  WHERE ID="+id+" ";
		return template.update(sql);
	}
	/*
	 *serie y folio de almacen y empresa */
	public Map<String,Object> serieFolioMax(int empresa_id, int almacen_id, String documento){
		Map<String,Object> mapa = new HashMap<String,Object>();
		String sql = "SELECT ID,SERIE, FOLIO FROM `folios` WHERE EMPRESA_ID ="+empresa_id+" AND ALMACEN_ID = "+almacen_id+" AND DOCUMENTO='"+documento+"' AND ESTATUS = 0";
		  List<Map<String,Object>> rows = template.queryForList(sql);
		  int folioNew = Integer.parseInt(rows.get(0).get("FOLIO").toString()) + 1;
		  int idf = Integer.parseInt(rows.get(0).get("ID").toString());
		  		  
		  mapa.put("SERIE_ID", rows.get(0).get("ID"));
		  mapa.put("SERIE", rows.get(0).get("SERIE"));
		  mapa.put("FOLIO", folioNew );
		  
		  sql ="UPDATE folios SET FOLIO='"+folioNew+"' WHERE ID="+idf+"  "; 
		  template.update(sql);
		  
		return mapa;
	}
	public Map<String,Object> getSerieFolio(int empresa_id, int almacen_id, String documento){
		Map<String,Object> mapa = new HashMap<String,Object>();
		String sql = "SELECT ID,SERIE, FOLIO FROM `folios` WHERE EMPRESA_ID ="+empresa_id+" AND ALMACEN_ID = "+almacen_id+" AND DOCUMENTO='"+documento+"' AND ESTATUS = 0";
		  List<Map<String,Object>> rows = template.queryForList(sql);
		  if(rows.isEmpty() ) {
			  mapa.put("SERIE_ID", 0);
			  mapa.put("SERIE","NO EXISTE SERIE");
			  mapa.put("FOLIO", 0 );
			  
		  }else {
			  mapa.put("SERIE_ID", rows.get(0).get("ID"));
			  mapa.put("SERIE", rows.get(0).get("SERIE"));
			  mapa.put("FOLIO", rows.get(0).get("FOLIO") );
			  
		  }		  
		 
		return mapa;
	}
	
}
