package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Articulo;
import com.aps.beans.catalogos.Cliente;

public class ClienteDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Map<String,Object>> listaClientes(int empresa_id) {
		String sql = "SELECT * FROM CLIENTE WHERE EMPRESA_ID='"+empresa_id+"'";
		System.out.println(sql);
		List<Map<String,Object>> rows = template.queryForList(sql);				
	  return rows;		
	}
	/*Eliminar cliente
	 *@param id cliente */
	public int deleteCliente(int id) throws SQLException  {
		String sql = "DELETE FROM cliente WHERE IDC=" + id + "";		
		return template.update(sql);
	}
	/*verificar si existe cliente */
	public int getClientebyId(String codigo) {
		String sql = "SELECT IDC FROM cliente WHERE CODIGO ='" + codigo + "'";
		List<String> certs = template.queryForList(sql, String.class);
           System.out.println(sql);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}
	/**/
	
	public boolean save(Cliente c) {
		String sql = "INSERT INTO `cliente`(`EMPRESA_ID`, `CODIGO`, `NOMBRE`, `APELLIDOS`, `DIRECCION`, `COLONIA`, `MUNICIPIO`, `ESTADO`, `PAIS`, `TELEFONO`, `EMAIL`, `TIMECREATE`) VALUES ";
		
		sql += "('" + c.getEmpresa_id() + "','" + c.getCodigo() +"','" + c.getNombre() + "','" +c.getApellidos() + "','" +c.getDireccion() + "','" + c.getColonia()
				+ "','" + c.getMunicipio() + "','" + c.getEstado() + "','"+c.getPais()+ "','" + c.getTelefono()
				+ "','" + c.getEmail() + "','" + c.getTimecreate() +"')";

		return template.update(sql) > 0;
	}
	public boolean insert(int empresa_id, String codigo, String nombre, String apellidos, String direccion, String colonia, String municipio, String estado, String pais, String telefono, String email, String timecreate) {
		String sql = "INSERT INTO `cliente`(`EMPRESA_ID`, `CODIGO`, `NOMBRE`, `APELLIDOS`, `DIRECCION`, `COLONIA`, `MUNICIPIO`, `ESTADO`, `PAIS`, `TELEFONO`, `EMAIL`, `TIMECREATE`) VALUES ";
		sql += "('" + empresa_id + "','" + codigo +"','" + nombre + "','" +apellidos + "','" + direccion + "','" + colonia
		+ "','" + municipio + "','" + estado + "','"+pais+ "','" + telefono
		+ "','" + email + "','" + timecreate +"')";
		return template.update(sql) > 0;
	}
	
	
}
