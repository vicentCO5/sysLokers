package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Articulo;

public class ArticuloDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int getArticulobyId(String codigo) {
		String sql = "SELECT ID FROM MAEARTICULO WHERE NUMART='" + codigo + "'";
		List<String> certs = template.queryForList(sql, String.class);
           System.out.println(sql);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	public boolean save(Articulo a) {
		String sql = "INSERT INTO `maearticulo`(  `EMPRESA_ID`, `NUMART`, `NUMARTALTERNO`, `NOMART`, `UNIDAD`, `PESO`, `VOLUMEN`, `COLOR`, `ALTO`, `LARGO`, `ANCHO`, `CLASIFICACION_ID`,`ESTATUS`, `TIMECREATE`, `TIMECHANGE`, `USUARIO_ID`) VALUES";
		
		sql += "('" + a.getEmpresa_id() + "','" + a.getNumart() + "','" + a.getNumartAlterno() + "','" + a.getNomart() + "','" + a.getUniart()
				+ "','" + a.getPeso() + "'," + a.getVolumen() + ",'"+a.getColor() + "'," + a.getAlto()
				+ "," + a.getLargo() + ",'" + a.getAncho() + "','" + a.getClasificacion_id() + "'," + a.getEstatus() + ","
						+ "'"+a.getTimecreate()+"','"+a.getTimechange()+"','"+a.getUsuario_id()+"' )";
		//System.out.println(sql);
		return template.update(sql) > 0;
	}

	public boolean update(Articulo a) {
		
		String sql = "UPDATE MAEARTICULO SET NUMARTALTERNO='" + a.getNumartAlterno() + "',NOMART='" + a.getNomart()
				+ "',UNIART='" + a.getUniart() + "',PESO ='"+a.getPeso()+"', `VOLUMEN`='"+a.getVolumen()+"', `COLOR`='"+a.getColor()+"', "
						+ " `ALTO`='"+a.getAlto()+"', `LARGO`='"+a.getLargo()+"', `ANCHO`='"+a.getAncho()+"', `CLASIFICACION_ID`='"+a.getClasificacion_id()+"',"
								+ " `ESTATUS`='"+a.getEstatus()+"', `TIMECHANGE` = '"+a.getTimechange()+"', `USUARIO_ID`='"+a.getUsuario_id()+"' "
						+ " WHERE NUMART='"+a.getNumart()+"' ";
		return template.update(sql) > 0;
	}
	/**
	 * lista de getlistArticulos
	 * */
	public List<Object> getlistArticulos() {
		List<Object> deptos = new ArrayList<Object>();
		String sql = "SELECT a.*,c.CLAVE,c.DESCRIPCION,C.ESTATUS AS ESTATUSCLASS FROM `maearticulo` a\r\n" + 
				"INNER JOIN config_clasificacion c ON a.CLASIFICACION_ID = C.ID\r\n" + 
				"WHERE a.EMPRESA_ID='1' ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("numart", row.get("NUMART"));
			mapa.put("numartalterno", row.get("NUMARTALTERNO"));
			mapa.put("nomart", row.get("NOMART"));
			mapa.put("uniart", row.get("UNIDAD"));
			
			mapa.put("color", row.get("COLOR"));
			mapa.put("peso", row.get("PESO"));
			mapa.put("volumen", row.get("VOLUMEN"));
			
			mapa.put("alto", row.get("ALTO"));
			mapa.put("largo", row.get("LARGO"));
			mapa.put("ancho", row.get("ANCHO"));
			String medidas = "Alto : "+row.get("ALTO")+"<br>"+"Largo : "+row.get("LARGO")+"<br>"+"Ancho : "+row.get("ANCHO");
			String timecreate = row.get("TIMECREATE")+"";
			mapa.put("medidas", medidas );
			mapa.put("estatus", row.get("ESTATUS"));
			mapa.put("timecreate", timecreate );
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("descripcion", row.get("DESCRIPCION"));
			mapa.put("estatusclass", row.get("ESTATUSCLASS"));
			
			String htmlAcciones = "<button onclick=\"deleteArticulo("+row.get("ID")+")\" class='btn btn-danger btn-circle btn-sm' title='Eliminar'><i class='fa fa-trash-o bigger-130'></i></button>";
			mapa.put("acciones", htmlAcciones);	
			deptos.add(mapa);
		}
		return deptos;
	}
	/***/
	public List<Map<String, Object>> getlistArticulosActivos(int empresa_id) {

		String sql = "SELECT a.*,c.CLAVE,c.DESCRIPCION,C.ESTATUS AS ESTATUSCLASS FROM `maearticulo` a\r\n" + 
				"INNER JOIN config_clasificacion c ON a.CLASIFICACION_ID = C.ID\r\n" + 
				"WHERE a.EMPRESA_ID='"+empresa_id+"' AND a.ESTATUS='0' ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		return rows;
	}
	/*
	 * @param id
	 * */
	public int deleteArticulo(int id) throws SQLException  {
		String sql = "DELETE FROM maearticulo WHERE ID="+id+" ";
		return template.update(sql);
	}
	
	

}
