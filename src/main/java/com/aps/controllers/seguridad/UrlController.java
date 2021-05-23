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

//import com.aps.beans.catalogos.Url_old;
import com.aps.beans.seguridad.Url;
import com.aps.dao.catalogos.UrlDao;
@Controller
public class UrlController {
	@Autowired
	UrlDao dao;
	@RequestMapping("/getviewurl")
	public @ResponseBody
    String geturl() {
		List<Url> list = dao.getUrls();
		//System.out.println(list.size());
		String name = dao.nameAps();
		String menu = "";
		String icon = "";
		int i=0; int j=0;int z=0;
		menu += "<li class=\"nav-small-cap\">--- MENU</li>";
		for (i = 0; i < list.size(); i++) {
			icon = "";
			if (list.get(i).getNombre().equals("Configuracion"))
				icon = "menu-icon fa fa-cog";
			else if (list.get(i).getNombre().equals("Catalogo Operacion"))
				icon = " sl-icon-list";
			else if (list.get(i).getNombre().equals("Clientes"))
				icon = "fa fa-user-circle";
			else if (list.get(i).getNombre().equals("Control Equipaje"))
				icon = "ti-bag";
			else if (list.get(i).getNombre().equals("Reportes"))
				icon = "fa fa-file-text";
			else
				icon = "";
			
			
			menu += "<li class=\"\"><a href='#' class=\"has-arrow waves-effect waves-dark\" aria-expanded=\"false\"><i class='"+ icon + "'></i> <span class='hide-menu'> " + list.get(i).getNombre() + "</span></a>";
			menu += "<ul aria-expanded=\"false\" class=\"collapse\">";
			List<Url> sublist = dao.getSubUrls(list.get(i).getTipo());

//			System.out.println("1 nombre " + list.get(i).getNombre());

			for (j = 0; j < sublist.size(); j++) {
				String url = sublist.get(j).getUrl();

//				System.out.println("getUrl : " + url);

				if (url == null) {
//					System.out.println( "------- treelist -- " + sublist.get(j).getTipo() +" - "+ sublist.get(j).getNombre());
					menu += "<li class=\"\">";
					menu += "<a  href='#'  >"+ sublist.get(j).getNombre() + "</a>";
//					menu += "<ul aria-expanded=\"false\" class=\"collapse\">";
//					List<Url> treelist = dao.getSubUrls(sublist.get(j).getTipo());					
//					for (z = 0; z < treelist.size(); z++) {
//						String suburl = treelist.get(z).getUrl();
//
//						if (suburl == null) {
//							menu += "<li class=\"\" >";
//							menu += "<a class=\"has-arrow\"  href='#'>"+ treelist.get(z).getNombre() + "</a>";
//							List<Url> fourlist = dao.getSubUrls(treelist.get(z).getTipo());
//							if( fourlist.size() > 0 ) {
//								menu += "<ul aria-expanded=\"false\" class=\"collapse\">";							
//								for (int q = 0; q < fourlist.size(); q++) {
//									menu += "<li class=\"\">";
//									menu += "<a href=\"#\" onclick=\"loader_open(); jQuery.ajax({type:\'POST\',url:\'/"
//											+ name + fourlist.get(q).getUrl()
//											+ "',beforeSend: function() {},success:function(data,textStatus){jQuery(\'#divprincipal\').html(data);loader_close();},error:function(XMLHttpRequest,textStatus,errorThrown){alert(\'Error: \'+errorThrown );}});return false;\"><i class='menu-icon fa fa-plus black'></i>"
//											+ fourlist.get(q).getNombre() + "</a>";
//									menu += "</li>";
//								}
//								menu += "</ul>";
//							}							
//							menu += "</li>";
//						} else {
//							menu += "<li class=\"\" >";
//							menu += "<a href=\"#\" onclick=\"loader_open(); jQuery.ajax({type:\'POST\',url:\'/" + name
//									+ treelist.get(z).getUrl()
//									+ "',beforeSend: function() {},success:function(data,textStatus){jQuery(\'#divprincipal\').html(data);loader_close();},error:function(XMLHttpRequest,textStatus,errorThrown){alert(\'Error: \'+errorThrown );}});return false;\"><i class='menu-icon fa fa-circle black'></i>"
//									+ treelist.get(z).getNombre() + "</a>";
//							menu += "</li>";
//						}
//					}
//					menu += "</ul>";
					menu += "</li>";
				} else {

					menu += "<li class=\"\">";
					menu += "<a href=\"#\" onclick=\"jQuery.ajax({type:\'POST\',url:\'/" + name + url
							+ "',beforeSend: function() {},success:function(data,textStatus){ jQuery(\'#divprincipal\').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){  alert(\'Error: \'+errorThrown );}});return false;\"><i class='menu-icon fa fa-caret-right'></i>"
							+ sublist.get(j).getNombre() + "</a>";
					menu += "</li>";
				}
			}
			menu += "</ul>";
			menu += "</li>";

		}
		
		 //System.out.println(menu);
		 return menu;
	}
	
	@RequestMapping(value = "/listJSONUrl", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Object> listPerfiles() {
		List<Object> uil = new ArrayList<Object>();
		List<Url> list = dao.getUrls();
		int i=0; int j=0;int z=0;
		 for (i=0; i<list.size();i++){
			 Map<String, Object> menu1 = new HashMap<String, Object>();
			 menu1.put("id", list.get(i).getId());
			 menu1.put("nombre", list.get(i).getNombre());
			 menu1.put("url", list.get(i).getUrl());
			 
			 List<Url> sublist = dao.getSubUrls(list.get(i).getTipo());
			 List<Object> menus = new ArrayList<Object>();
			 
			 for (j=0; j<sublist.size();j++){
				Map<String, Object> menu2 = new HashMap<String, Object>();
				String url=sublist.get(j).getUrl();	
				List<Object> submenus = new ArrayList<Object>();
				if(url==null) {
					List<Url> treelist = dao.getSubUrls(sublist.get(j).getTipo());					
					for (z=0; z<treelist.size();z++){
						Map<String, Object> menu3 = new HashMap<String, Object>();
						String suburl = treelist.get(z).getUrl();
						List<Object> submenus2 = new ArrayList<Object>();
						if(suburl==null) {
							List<Url> fourlist = dao.getSubUrls(treelist.get(z).getTipo());
							for (int q=0; q<fourlist.size();q++){
								Map<String, Object> menu4 = new HashMap<String, Object>();
								menu4.put("id", fourlist.get(q).getId());
								menu4.put("nombre", fourlist.get(q).getNombre());
								menu4.put("url", fourlist.get(q).getUrl());
								submenus2.add(menu4);
							}
							
						}
							menu3.put("id", treelist.get(z).getId());
							menu3.put("nombre", treelist.get(z).getNombre());
							menu3.put("url", treelist.get(z).getUrl());
						
						menu3.put("submenus2", submenus2);
						submenus.add(menu3);
					}
				}
					menu2.put("id", sublist.get(j).getId());
					menu2.put("nombre", sublist.get(j).getNombre());
					menu2.put("url", sublist.get(j).getUrl());
				
				menu2.put("submenus", submenus);
				menus.add(menu2);
				
			 }
			 //System.out.println(menus);
			 menu1.put("menus", menus);
			 uil.add(menu1);
	     }
		// System.out.println(uil);
		 return uil;

	}
	
	@RequestMapping(value = "/jsonByPerfilEdit", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Object> jsonByPerfilEdit(@RequestParam("id")int id) {
		List<Object> uil = dao.getByPerfil(id);		
		 return uil;
	}
	

}
