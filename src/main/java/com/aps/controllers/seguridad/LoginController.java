package com.aps.controllers.seguridad;





import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aps.beans.seguridad.Usuario;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.EmpresaDao;
import com.aps.dao.seguridad.UsuarioDao;

@Controller
public class LoginController {
	@Autowired
	UsuarioDao daousuario;
	@Autowired
	EmpresaDao daoempresa;
	@Autowired
	AlmacenDao daoalmacen;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req, @RequestParam("password") String pass,
			@RequestParam("username") String user) throws ServletException {
		ModelAndView model = new ModelAndView();
		// String Mensaje = "";
		String usuario = user;
		String password = pass;
		System.out.println(usuario + ":" + password);

		Usuario userr = daousuario.validateUser(usuario, password);
		if (null != userr) {
			if (daousuario.validapassword(password, userr.getPassword())) {
				HttpSession session = req.getSession(true);
				if (userr.getAlmacen_id() != 0)
					session.setAttribute("empresa", daoempresa.getEmpresaById(userr.getEmpresa_id()));
				if (userr.getAlmacen_id() != 0)
					session.setAttribute("almacen", daoalmacen.getAlmacenById(userr.getAlmacen_id()));
				req.getSession().setAttribute("session", session);
				req.getSession().setAttribute("usuario", usuario);
				model.setViewName("inicio");
			}
		} else {

			model.setViewName("inicio/denied");
		}
		return model;
	}

	

 
}
