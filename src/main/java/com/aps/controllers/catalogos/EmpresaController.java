package com.aps.controllers.catalogos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aps.beans.catalogos.Empresa;
import com.aps.dao.catalogos.EmpresaDao;
@Controller
public class EmpresaController {
	//private String upload_folder=".//src//main//webapp//resources//images//imagesempresa//";
	
	@Autowired
	EmpresaDao daoempresa;
	ServletContext context; 

	
	@RequestMapping("/getindexempresa")
	public ModelAndView viewempresas() {
		//System.out.println("Hola estoy aca");
		List<Empresa> list = daoempresa.getEmpresas();
		return new ModelAndView("empresa/indexempresa","list",list);
	}
	
	@RequestMapping("/createempresa")
	public ModelAndView viewformempresa() {
		return new ModelAndView("empresa/formempresa","command",new Empresa());
	}
	@Transactional
	void savee(Empresa empresa) {
		daoempresa.save(empresa);
	}
	@Transactional
	void updatee(Empresa empresa) {
		daoempresa.update(empresa);
	}
	
	
	@RequestMapping(value="/saveempresa",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object>  save(@ModelAttribute("empresa")Empresa empresa,BindingResult result) {
		
		 Map<String, Object> mapa = new HashMap<String, Object>();
		if (result.hasErrors()) {
			ArrayList<String> errores = new ArrayList<String>();
			List<ObjectError> list=result.getAllErrors();
			for(int i=0;i<=list.size();i++) {
				errores.add(result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage());
			}
			
			mapa.put("exito", "false");
			mapa.put("error",errores);
        }else {
		savee(empresa);
		mapa.put("exito", "true");
        }
		 return mapa;
		
	}

	@RequestMapping("/editempresa")
	public ModelAndView edit(@RequestParam ("id")int id) {
		Empresa empresa = daoempresa.getEmpresaById(id);
		return new ModelAndView("empresa/edit", "command", empresa);
	}
	
	@RequestMapping(value="/updateempresa",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object>  updateempresa(@ModelAttribute("empresa")Empresa empresa,BindingResult result) {
		
		 Map<String, Object> mapa = new HashMap<String, Object>();
		if (result.hasErrors()) {
			ArrayList<String> errores = new ArrayList<String>();
			List<ObjectError> list=result.getAllErrors();
			for(int i=0;i<=list.size();i++) {
				errores.add(result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage());
			}
			
			mapa.put("exito", "false");
			mapa.put("error",errores);
        }else {
        	updatee(empresa);
		mapa.put("exito", "true");
        }
		 return mapa;
		
	}
	
	@RequestMapping("/deleteempresa")
	public @ResponseBody String delete(@RequestParam("id")int id) {
		String cad="";
		try {
			daoempresa.delete(id);
			cad="Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad=e.getMessage();
			
		}
		return cad;
	}
	@RequestMapping(value = "/searchempresas", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Object> viewalms() {
		List<Object> list = daoempresa.getempresas();
		return list;
	}
	

	
}
