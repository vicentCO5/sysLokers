package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Proveedor;

public class ProveedorDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Object> getProveedores() {
		List<Object> proveedores = new ArrayList<Object>();
		String sql = "SELECT P.*,E.NOMBRE,PE.EMPRESA_ID FROM `PROVEEDOR` P INNER JOIN PROEMPRESA PE ON (P.ID=PE.PROVEEDOR_ID) INNER JOIN EMPRESA E ON (PE.EMPRESA_ID=E.ID) ORDER BY P.ID ASC ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("clave", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("empresa", row.get("NOMBRE"));
			mapa.put("tipo", row.get("tipo"));
			mapa.put("direccion", row.get("direccion"));
			mapa.put("telefono", row.get("telefono"));
			mapa.put("celular", row.get("celular"));
			mapa.put("mail", row.get("mail"));
			mapa.put("pais", row.get("pais"));
			mapa.put("razonsocial", row.get("razon_social"));
			String est="";
			if( row.get("status").equals(1)) {
				est = "ACTIVO";
			}else
				est ="INACTIVO";
			
			mapa.put("estatus", est);
			String html = "&nbsp;&nbsp;<button onclick=\"deleteprov('" + row.get("ID")
					+ "'," + row.get("empresa_id") + ")\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
			proveedores.add(mapa);
		}
		return proveedores;
	}

	public int deleteallproempresa() {
		String sql="TRUNCATE TABLE PROEMPRESA";
		return template.update(sql);
	}
	/**
	 * Eliminar todos los proveedores
	 * 
	 * @return
	 */
	public int deleteAllProveedores() {
		String sql = "DELETE FROM PROVEEDOR";
			return template.update(sql);
	}

	/**
	 * @param idempresa
	 * @param claveprov
	 * @return 0-> if not exist(insert) and return the claveprov TO->(update)
	 */
	public int getProvbyempresaid_clave(String clave, int idempresa) {
		String sql = "SELECT P.ID FROM `PROVEEDOR` P INNER JOIN PROEMPRESA PE ON (P.ID=PE.PROVEEDOR_ID) AND PE.EMPRESA_ID='"+idempresa+"' WHERE P.CLAVE='"+clave+"'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	/**
	 * @param PROVEEDOR
	 * @return true or false
	 */
	public boolean save(Proveedor l) {
		String sql = "INSERT INTO PROVEEDOR( CELULAR, CLAVE , COLONIA , DESCRIPCION , DIRECCION , ESTADO, MAIL, OBSERVACIONES, PAIS, POBLACION , RAZON_SOCIAL, RFC, STATUS, TELEFONO, TIPO) VALUES";
		sql += "('" + l.getCelular() + "','" + l.getClave() + "','" + l.getColonia() + "','" + l.getDescripcion() + "','"
				+ l.getDireccion() + "','" + l.getEstado() + "','" + l.getMail() + "','"
				+ l.getObservaciones() + "','" + l.getPais() + "','" + l.getPoblacion() + "','" + l.getRazonSocial()
				+ "','" + l.getRfc() + "','" + l.getStatus() + "','" + l.getTelefono() + "','" + l.getTipo() + "'"
				+ ")";
		return template.update(sql)>0;
		
	}
	

	/**
	 * @param proveedor
	 * @return
	 */
	public int update(Proveedor l) {
		String sql = "UPDATE PROVEEDOR SET celular='" + l.getCelular() + "',colonia='" + l.getColonia()
				+ "',descripcion='" + l.getDescripcion() + "',direccion='" + l.getDireccion() + "',estado='" + l.getEstado() + "',mail='" + l.getMail() + "',observaciones='"
				+ l.getObservaciones() + "',pais='" + l.getPais() + "',poblacion='" + l.getPoblacion()
				+ "',razon_social='" + l.getRazonSocial() + "',rfc='" + l.getRazonSocial() + "',status='"
				+ l.getStatus() + "',telefono='" + l.getTelefono() + "',tipo='" + l.getTipo() + "'"
				+ " WHERE CLAVE='" + l.getClave() + "'";
		return template.update(sql);
	}

	public boolean deleteproempresa(String idproveedor, int empresaid) {
		String sql = "DELETE FROM PROEMPRESA WHERE proveedor_id='"+idproveedor+"' AND empresa_id="+empresaid+"";
		return template.update(sql)>0;
	}
	/**
	 * @param claveprov
	 * @param empresaid
	 * eliminar PROVEEDOR
	 */
	public boolean delete(String idproveedor) throws SQLException {
		String sql = "DELETE FROM PROVEEDOR WHERE ID='" + idproveedor + "'";		
		
		return template.update(sql)>0;
	}
	/**
	 * @param clave
	 * @return
	 */
	public int getProveedorbyclave(String clave) {
		String sql = "SELECT ID FROM PROVEEDOR WHERE CLAVE='"+ clave +"'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	
	}
}
