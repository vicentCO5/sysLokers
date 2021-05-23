package com.aps.controllers.seguridad;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	
	@RequestMapping("/logout")
	public ModelAndView  logout(HttpServletRequest request, HttpServletResponse response)  {
		ModelAndView model = new ModelAndView();
		    Object Name = request.getSession().getAttribute("usuario");
		    Object session = request.getSession().getAttribute("session");		    
		    System.out.println("Hola"+Name+":"+session);    
		    HttpSession sessionistrue = request.getSession(false);
		            if (session != null) {
		            	sessionistrue.invalidate();
		            }
		            SecurityContextHolder.clearContext();
		    model.setViewName("redirect:/");
		    return model;
		
}
}
