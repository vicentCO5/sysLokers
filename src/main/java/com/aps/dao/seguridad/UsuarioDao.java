package com.aps.dao.seguridad;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import com.aps.beans.seguridad.Usuario;

public class UsuarioDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public int save(String nombre,String username,String password,int empresa,int almacen,boolean isactivo) {
		String passwordencoded = encodePassword(password);
		String sql = "INSERT INTO USUARIO (ALMACEN_ID,EMPRESA_ID,ENABLED,NOMBRE,PASSWORD,USERNAME) VALUES("+empresa+","+almacen+","+isactivo+",'"+nombre+"','"+passwordencoded+"','"+username+"')";
		return template.update(sql);
	}
	public int update(int id,String nombre,String username,String password,int empresa,int almacen,boolean isactivo) {
		String passwordencoded = encodePassword(password);
		String sql = "UPDATE USUARIO SET ALMACEN_ID="+almacen+",EMPRESA_ID="+empresa+",ENABLED="+isactivo+",NOMBRE='"+nombre+"',PASSWORD='"+passwordencoded+"',USERNAME='"+username+"' WHERE ID="+id+"";
		return template.update(sql);
	}
	protected String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		//System.out.println(encodedPassword);
		return encodedPassword;
	}
	
	//con ncoder.matches(password, user.getPassword())Verifique que la contrase�a codificada obtenida del almacenamiento coincida con la contrase�a sin procesar enviada una vez que tambi�n est� codificada

	public List<Object> getUsers() {
		List<Object> users = new ArrayList<Object>();
		String sql="SELECT u.*,p.nombre as perfil,p.tipo from USUARIO u LEFT JOIN PERFIL p on(u.perfil_id=p.id)";		      
		List<Map<String, Object>> rows = template.queryForList(sql);		
		 for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			 if(!row.get("username").equals("admin")) {
			 String it= "<i "+(row.get("ENABLED").equals(true) ? "title='Activo' class='glyphicon glyphicon-ok-sign blue'" : "title='Inactivo' class='glyphicon glyphicon-info-sign yellow'")+"></i>"+row.get("USERNAME")+"";
			 		mapa.put("DT_RowId","usrid_"+row.get("ID"));
			        mapa.put("username", it);
			 		mapa.put("nombre", row.get("nombre").toString().equals("")?"Sin Asignar":row.get("nombre"));
			 		mapa.put("perfil", row.get("perfil_id")==(null)?"Sin Asignar":row.get("perfil"));
			 		mapa.put("tipo", row.get("perfil_id")==(null) ? "Sin Asignar" : row.get("tipo"));
			 		mapa.put("roles", row.get("roles")==(null)?"":row.get("roles"));
			 		mapa.put("id", row.get("id"));
			 		mapa.put("name", row.get("username"));
			 		String html="<span class='btn btn-success btn-circle btn-sm'><i class='fa fa-pencil-square'></i> </span></button> <button class='btn btn-danger btn-circle btn-sm' onclick='eliminarusuario("+row.get("id")+")' title='Eliminar usuario'><span class='fa fa-trash-o'></span></button>";
		            
			 		mapa.put("accion", html);
			 		users.add(mapa);
			 }
		   }
		  return users;
	}
	
	public List<Object> list() {
		List<Object> users = new ArrayList<Object>();
		String sql="SELECT u.* from USUARIO u";		      
		List<Map<String, Object>> rows = template.queryForList(sql);		
		 for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			 if(!row.get("username").equals("admin")) {			 
			 		mapa.put("DT_RowId","usrid_"+row.get("ID"));
			 		mapa.put("username", row.get("username"));
			 		mapa.put("name", row.get("username"));
			 		mapa.put("activo", row.get("enabled").equals(true)?"SI":"NO");
			 		mapa.put("id", row.get("id"));			 		
			 		users.add(mapa);
			 }
		   }
		  return users;
	}
	
	public boolean delete(int usuario) {
		String sql = "DELETE FROM USUARIO WHERE ID="+usuario;
		return template.update(sql)>0;
	}
	
	public Usuario getUserById(int id) {
		String sql = "SELECT * FROM USUARIO WHERE ID=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Usuario>(Usuario.class));
	}
	
	public Usuario findByUsername(String id) {
		String sql = "SELECT * FROM USUARIO WHERE USERNAME=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Usuario>(Usuario.class));
	}
	public List<Object> findByUsersByPerfil(int id) {
		List<Object> users = new ArrayList<Object>();  
		String sql = "SELECT * FROM USUARIO WHERE perfil_id="+id;		    
		List<Map<String, Object>> rows = template.queryForList(sql);		
		 for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			 if(!row.get("username").equals("admin")) {			 
			 		mapa.put("DT_RowId","usrid_"+row.get("ID"));
			 		mapa.put("username", row.get("username"));
			 		mapa.put("name", row.get("username"));
			 		mapa.put("activo", row.get("enabled").equals(true)?"SI":"NO");
			 		mapa.put("id", row.get("id"));			 		
			 		users.add(mapa);
			 }
		   }
		  return users;
	}
	
	public void updateperfilUsuario(JSONArray arrayusuarios,int i) {		
		String sql = "";
		for(int j=0;j<arrayusuarios.size();j++) {
			sql = "UPDATE USUARIO SET PERFIL_ID="+i+" WHERE ID="+arrayusuarios.get(j);
			template.update(sql);
		}		
	}
	
	public Usuario validateUser(String username,String password) {
		String sql = "select * from usuario where username='" + username + "'";
		
		List<Usuario> users= template.query(sql, new RowMapper<Usuario>() {
			public Usuario mapRow(ResultSet rs, int row) throws SQLException {
				Usuario e = new Usuario();
				e.setAlmacen_id(rs.getInt(5));
				e.setEmpresa_id(rs.getInt(6));
				e.setUsername(rs.getString(13));
				e.setPassword(rs.getString(9));
				return e;
			}
		}); 
		//System.out.println(users+":"+users.size());
		return users.size() > 0 ? users.get(0) : null;
	}
	public boolean validapassword(String passwordinput,String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean band = passwordEncoder.matches(passwordinput, password);
		System.out.println(band);
		return band;
	}
	
}
