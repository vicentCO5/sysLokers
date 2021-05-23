package com.aps.controllers.seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aps.beans.catalogos.Almacen;
import com.aps.beans.catalogos.Empresa;
import com.aps.beans.seguridad.Role;
import com.aps.beans.seguridad.Usuario;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.EmpresaDao;
import com.aps.dao.seguridad.RoleDao;
import com.aps.dao.seguridad.UsuarioDao;
import com.aps.dao.seguridad.UsuarioRoleDao;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioDao daousuario;
	@Autowired
	UsuarioRoleDao daousuariorole;
	@Autowired
	EmpresaDao daoempresa;
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	RoleDao daorole;
	
	@RequestMapping("/listusers")
	public ModelAndView viewindexuser() {
		return new ModelAndView("usuario/index");
	}
	@RequestMapping("/createuser")
	public ModelAndView viewformcreate() {
		return new ModelAndView("usuario/create");
	}
	
	@RequestMapping("/editUser")
	public ModelAndView viewformedit(@RequestParam ("id")int id) {
		Usuario usuarioInstance = daousuario.getUserById(id);		
		ModelAndView model = new ModelAndView();
		model.addObject("iduser", id);
		model.addObject("nombre", usuarioInstance.getNombre());
		model.addObject("username", usuarioInstance.getUsername());
		model.addObject("estatus", usuarioInstance.isEnabled());
		List<Empresa> lista =daoempresa.getEmpresas();
		model.addObject("lista", lista);
		model.addObject("empresa_id", usuarioInstance.getEmpresa_id());		
		List<Almacen> almacenes = daoalmacen.getByEmpresa(usuarioInstance.getEmpresa_id());
		model.addObject("listalmacen", almacenes);
		model.addObject("almacen_id", usuarioInstance.getAlmacen_id());
		model.setViewName("usuario/edit");
		//System.out.println(model);
		return model;
	}
	// getempresas
	@RequestMapping("/getEmpresas") 
	public @ResponseBody Map<String, Object> listEmpresas() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		List<Object> list = daoempresa.getempresas();
		mapa.put("data", list);
		return mapa;
	}
	@RequestMapping(value="/getByEmpresa",produces="application/json; charset=UTF-8") 
	public @ResponseBody List<Almacen> listAlmacenEmpresas(@RequestParam("id") int id ) {		
				
		List<Almacen> almacenes = daoalmacen.getByEmpresa(id);
		return  almacenes;
	}
	
	@RequestMapping(value="/save",produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object> save(@RequestParam("nombre")String nombre,@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("empresa")int empresa,@RequestParam("almacen")int almacen,@RequestParam("isactivo")boolean isactivo) {
		
		 Map<String, Object> mapa = new HashMap<String, Object>();
		 List<Object> errores=new ArrayList<Object>();
		try {
			daousuario.save(nombre, username, password, empresa, almacen, isactivo);
			Role role=daorole.findByAuthority("ROLE_ADMIN");
			Usuario usuarioinstance = daousuario.findByUsername(username);
			daousuariorole.create(usuarioinstance.getId(), role.getId());
			mapa.put("exito", "true");
		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}
		 return mapa;		
	}
	
	@RequestMapping(value="/updateUser",produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object> update(@RequestParam("iduser")int id,@RequestParam("nombre")String nombre,@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("empresa")int empresa,@RequestParam("almacen")int almacen,@RequestParam("isactivo")boolean isactivo) {
		
		System.out.println("hOLA LLEGUE"+nombre);
		
		 Map<String, Object> mapa = new HashMap<String, Object>();
		 List<Object> errores=new ArrayList<Object>();
		try {
			Usuario usuarioInstanceBusqueda = daousuario.findByUsername(username);
			Usuario usuarioInstance = daousuario.getUserById(id);
			
			if(!usuarioInstanceBusqueda.getUsername().equals("")) {
				if(usuarioInstanceBusqueda.getId()!=usuarioInstance.getId()) {
					errores.add("Username ya existe"+usuarioInstanceBusqueda.getUsername());
				}
			}
			
			if(errores.size()>0) {
				mapa.put("exito", "false");			
				errores.add(errores);
				mapa.put("error",errores);
			}else {
				daousuario.update(id, nombre, username, password, empresa, almacen, isactivo);
				
				mapa.put("exito", "true");
			}
			
		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}
		 return mapa;		
	}
	
	@RequestMapping(value="/deleteUser",produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object> delete(@RequestParam("id")int id) {		
		 Map<String, Object> mapa = new HashMap<String, Object>();
		 List<Object> errores=new ArrayList<Object>();
		try {
			int UsuarioRoleInstance = daousuariorole.findByUsuario(id);
			if(UsuarioRoleInstance!=0) {
				if(!daousuariorole.delete(id)) {
					mapa.put("exito", "false");
					errores.add("Existen dependencias hacia el usuario");
				}else {
					daousuario.delete(id);
					mapa.put("exito", "true");
				}
			}else {
				daousuario.delete(id);
				mapa.put("exito", "true");
			}
		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}
		 return mapa;		
	}
	
	@RequestMapping(value = "/listJSON", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> listUsers() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		List<Object> list = daousuario.getUsers();
		mapa.put("data", list);
		return mapa;
	}
	
	@RequestMapping(value = "/listJSONM", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Object> listJSONM() {	
		List<Object> list = daousuario.list();
		//System.out.println(list);
		return list;
	}
	
}
