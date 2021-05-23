package com.aps.dao.catalogos;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.aps.beans.catalogos.Almacen;

public class AlmacenDao {
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	/**
	 * lista de almacenes
	 * */
	public List<Object> getAlmacenes() {
		List<Object> almacenes = new ArrayList<Object>();
		String sql="select * from Almacen";		      
		List<Map<String, Object>> rows = template.queryForList(sql);		
		 for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			       mapa.put("empresa_id", row.get("EMPRESA_ID"));
			 		mapa.put("clave", row.get("CLAVE"));
			 		mapa.put("nombre", row.get("NOMBRE"));
			 		mapa.put("codigoPostal", row.get("CP"));
			 		mapa.put("direccion", row.get("DIRECCION"));	
			 		mapa.put("pais", row.get("PAIS"));
//			 		row.get("ESTATUS")
			 		String htmlStatus = "<div class='switchery-demo'><input type=\"checkbox\" checked=\"\" class=\"js-switch\" data-color=\"#009efb\" style=\"display: none;\" data-switchery=\"true\"><span class=\"switchery switchery-default\" style=\"background-color: rgb(0, 158, 251); border-color: rgb(0, 158, 251); box-shadow: rgb(0, 158, 251) 0px 0px 0px 16px inset; transition: border 0.4s, box-shadow 0.4s, background-color 1.2s;\"><small style=\"left: 20px; background-color: rgb(255, 255, 255); transition: background-color 0.4s, left 0.2s;\"></small></span></div>";
			 		mapa.put("estatus", htmlStatus );
			 		String html="<button onclick=\"editalmacen("+row.get("clave")+")\" class='btn btn-info btn-circle btn-sm' title='Editar'><i class='fa fa-pencil bigger-130'></i></button>";
		            html+="&nbsp;&nbsp;<button onclick=\"deletealmacen("+row.get("clave")+")\" class='btn btn-danger btn-circle btn-sm' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			 		mapa.put("accion", html);
			 		almacenes.add(mapa);
		
		        }
		        return almacenes;
	}
	/**
	 * select Almacen by id
	 * */
	public Almacen getAlmacenById(int id) {
		String sql = "select * from Almacen where clave=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Almacen>(Almacen.class));
	}
	
	/**
	 * actualizar almacen
	 * */
	
	public int update(Almacen p) {
		String sql = "update ALMACEN set nombre='" + p.getNombre() + "', pais='" + p.getPais() + "'  where CLAVE=" + p.getClave()+ "";
		//System.out.println(sql);
		return template.update(sql);
	}
	/**
	 * eliminar almacen
	 * */
	public int delete(int id) throws SQLException  {
		String sql = "DELETE FROM ALMACEN WHERE CLAVE=" + id + "";
		
		return template.update(sql);
	}
	
	public int getidempresa(String clave) {
		String sql = "SELECT ID FROM EMPRESA WHERE clave='"+clave+"'";		
		  List<String> certs = template.queryForList(sql, String.class); 
		    if (certs.isEmpty()) {
		    	return 0;
		    } else {
		    	return Integer.parseInt(certs.get(0));
		    }
	}
	
	public int findByid(String clave) {
		String sql = "SELECT ID FROM ALMACEN WHERE CLAVE='"+clave+"'";		
		  List<String> certs = template.queryForList(sql, String.class); 
		    if (certs.isEmpty()) {
		    	return 0;
		    } else {
		    	return Integer.parseInt(certs.get(0));
		    }
	}
	
	public int insert(Almacen p) {		
		String sql = "INSERT INTO ALMACEN( EMPRESA_ID, CLAVE, NOMBRE, CP, DIRECCION,COLONIA,  NUMEXTERIOR, NUMINTERIOR, PAIS ) VALUES";
		sql += "( '"+p.getEmpresa_id()+"','"+p.getClave()+"','"+p.getNombre()+"','"+p.getCp()+"','"+p.getDireccion()+"','"+p.getColonia()+"','"+p.getNumExterior()+"','"+p.getNumInterior()+"','"+p.getPais()+"')";
		System.out.println(sql);
		return template.update(sql);		
	}
	public int save(Almacen p) {		
		String sql = "INSERT INTO ALMACEN( clave, colonia, direccion, empresa_id, estado, municipio, nombre, NUMEXTERIOR, NUMINTERIOR, pais, poblacion, status) VALUES";
		sql += "("+p.getClave()+",'"+p.getColonia()+"','"+p.getDireccion()+"',"+p.getEmpresa_id()+",'"+p.getEstado()+"','"+p.getMunicipio()+"','"+p.getNombre()+"','"+p.getNumExterior()+"','"+p.getNumInterior()+"','"+p.getPais()+"','"+p.getPoblacion()+"',"+p.getEstatus()+")";
		//System.out.println(sql);
		return template.update(sql);		
	}
	public List<Object> getAlmacenesConfig() {
		List<Object> almacenes = new ArrayList<Object>();
		String sql = "SELECT * FROM ALMACEN ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i+1));
			mapa.put("clave", row.get("clave"));
			mapa.put("nombre", row.get("nombre"));
			String checkedForecast = "";
			String checkedForecastPV = "";
			String checkedReabasto = "";
			String checkedResurtido = "";
			//System.out.println(row.get("forecast"));
			if(row.get("forecast").equals(true)) {
				checkedForecast = "<input checked='checked' type='checkbox'  id='idca"+row.get("ID")+"' data-id='idca"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("forecast")+"'  data-modulo='forecast' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			}else
				checkedForecast = "<input type='checkbox' id='idca"+row.get("ID")+"' data-id='idca"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("forecast")+"'  data-modulo='forecast' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			if(row.get("forecastpv").equals(true)) {
				checkedForecastPV = "<input checked='checked' type='checkbox'  id='idca"+row.get("ID")+"' data-id='id"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("forecastpv")+"'  data-modulo='forecastpv' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			}else
				checkedForecastPV = "<input  type='checkbox'  id='idca"+row.get("ID")+"' data-id='idca"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("forecastpv")+"'  data-modulo='forecastpv' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			if(row.get("reabasto").equals(true)) {
				checkedReabasto = "<input checked='checked' type='checkbox'  id='idca"+row.get("ID")+"' data-id='idca"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("reabasto")+"'  data-modulo='reabasto' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			}else
				checkedReabasto = "<input  type='checkbox'  id='idca"+row.get("ID")+"' data-id='idca"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("reabasto")+"'  data-modulo='reabasto' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			if(row.get("resurtido").equals(true)) {
				checkedResurtido = "<input checked='checked' type='checkbox'  id='idca"+row.get("ID")+"' data-id='idca"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("resurtido")+"'  data-modulo='resurtido' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			}else
				checkedResurtido = "<input  type='checkbox'  id='idca"+row.get("ID")+"' data-id='idca"+row.get("ID")+"' data-clave='"+row.get("clave")+"' data-value='"+ row.get("resurtido")+"'  data-modulo='resurtido' class='ace ace-switch ace-switch-5 updatemodulo' /><span class='lbl middle'></span>";
			
			mapa.put("forecast", checkedForecast);
			mapa.put("forecastpv", checkedForecastPV);
			mapa.put("reabasto", checkedReabasto);
			mapa.put("resurtido", checkedResurtido);
			almacenes.add(mapa);
		}
		return almacenes;
	}
	public int updateEstatusModulo(String clave,boolean oldvalue, String modulo) {
		int value=0;
		if(oldvalue == true) {
			value=0;
		}else {
			value=1;
		}		
		
		String sql = "UPDATE ALMACEN SET "+modulo+"="+value+" WHERE CLAVE='" + clave + "'";
		return template.update(sql);
	}
	
	/**
	 * lista de almacenes
	 * */
	public List<Almacen> getByEmpresa(int idempresa) {
		String sql="select * from ALMACEN WHERE EMPRESA_ID="+idempresa + " AND STATUS=0";	
		return template.query(sql, new RowMapper<Almacen>() {
			public Almacen mapRow(ResultSet rs, int row) throws SQLException {
				Almacen e = new Almacen();
				e.setId(rs.getInt(1));
				e.setNombre(rs.getString(10));
				return e;
			}
		});
	}
	
	public List<Map<String,Object>> getByEmpresa_2(int idempresa) {
		Map<String,Object> mapa = new HashMap<String,Object>();
		String sql="select * from ALMACEN WHERE EMPRESA_ID="+idempresa + " AND STATUS=0";		
		List<Map<String, Object>> rows = template.queryForList(sql); 
		return 	rows;
	}
}
