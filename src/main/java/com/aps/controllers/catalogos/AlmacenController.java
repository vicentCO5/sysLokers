package com.aps.controllers.catalogos;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.poi.ss.usermodel.DataFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aps.beans.catalogos.Almacen;
import com.aps.dao.catalogos.AlmacenDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class AlmacenController {
	@Autowired
	AlmacenDao daoalmacen;
	
	@RequestMapping(value="/showalms",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> viewalms() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoalmacen.getAlmacenes();
		mapa.put("data",list);
		return mapa;
	}
	@RequestMapping("/editalmacen")
	public ModelAndView edit(@RequestParam ("id")int id) {
		Almacen almacen = daoalmacen.getAlmacenById(id);
		return new ModelAndView("almacen/editalmacen", "command", almacen);	
	}
	@RequestMapping("/newalmacen")
	public ModelAndView vistaEdit() {
		return new ModelAndView("almacen/newalmacen");
	}
	@RequestMapping(value="/guardaralmacen",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> guardarAlmacen(@ModelAttribute("almacen")Almacen almacen,BindingResult result) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		System.out.println(" result " + result);
		System.out.println(" almacen " + almacen.getClave() );
				
		if (result.hasErrors()) {
			ArrayList<String> errores = new ArrayList<String>();
			List<ObjectError> list=result.getAllErrors();
			for(int i=0;i<=list.size();i++) {
				errores.add(result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage());
			}			
			mapa.put("exito", "false");
			mapa.put("error",errores);
		}else {
			if( daoalmacen.findByid( almacen.getClave())==0 ) {
				daoalmacen.insert(almacen);
			    mapa.put("exito", "true");
			    mapa.put("error","Guardado correctamente");
			}else{				
			    mapa.put("exito", "false");
			    mapa.put("error","La clave "+almacen.getClave()+" ya existe");
			}
        	
        }
		 return mapa;		
	}
	
	@Transactional
	void updatee (Almacen almacen) {
		daoalmacen.update(almacen);
	}
	
	
	@RequestMapping(value="/updatealmacen",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> updatealmacen(@ModelAttribute("almacen")Almacen almacen,BindingResult result) {
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
        	updatee(almacen);
		    mapa.put("exito", "true");
        }
		 return mapa;
		
	}
	@RequestMapping("/deletealmacen")
	public @ResponseBody String delete(@RequestParam("id")int id) {
		String cad="";
		try {
			daoalmacen.delete(id);
			cad="Eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad=e.getMessage();
			
		}
		return cad;
	}
	
	/*
	 * METODOS para importacion de ALMACENES
	 * */
	@RequestMapping("/getimportalms")
	public ModelAndView viewimportAlms() {
		return new ModelAndView("almacen/formalmacen");
	}
	
	@Transactional
	void savee (Almacen almacen) {
		daoalmacen.save(almacen);
	}
	
	@RequestMapping(value = "/doUploadalm",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String, Object> uploadFileHandler(@RequestParam("filealm") MultipartFile file) {
		ArrayList<String> errors = new ArrayList<String>();
		List<Almacen> almacenes = new ArrayList<Almacen>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		if (!file.isEmpty()) {
			try {
				String clavempresa;//A
				String clave = "";//B
				String nombre = "";//C
				String codigoPostal = "";//D
				String direccion = "";//E
				String colonia = "";//F
				String poblacion = "";//canton G
				String municipio = "";//provincia H
				String pais = "";//I
				String numexterior = "";//J
				String numinterior = "";//K
				String estatus; //L
				
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet =  workbook.getSheet(0);
				int idempresa=0;
				for (int row = 1; row < sheet.getRows(); row++) {
					//System.out.println(sheet.getCell(0,row).getContents().toString()+"\t\t");
					
					clavempresa = sheet.getCell(0, row).getContents().toString();
					clave = sheet.getCell(1, row).getContents().toString(); 
					nombre = sheet.getCell(2, row).getContents().toString();
					codigoPostal = sheet.getCell(3, row).getContents().toString();					 
					direccion = sheet.getCell(4, row).getContents().toString();
					colonia = (sheet.getCell(5,row).getContents().toString());
					poblacion = (sheet.getCell(6,row).getContents().toString());
					municipio = (sheet.getCell(7,row).getContents().toString());
					pais = (sheet.getCell(8,row).getContents().toString());
					numexterior = (sheet.getCell(9,row).getContents().toString());
					numinterior = (sheet.getCell(10,row).getContents().toString());
					estatus = sheet.getCell(11,row).getContents().toString();
					char ests = (estatus.charAt(0));
					//System.out.println(clavempresa);		
					idempresa= daoalmacen.getidempresa(clavempresa);
					
					if(idempresa != 0) {					
						Almacen almacen = new Almacen ( idempresa, clave, nombre, Integer.parseInt(codigoPostal),direccion, colonia, poblacion, municipio, pais, numexterior, numinterior, ests );
						//asList tranforma array en arraylist
						almacenes.addAll(Arrays.asList(almacen));
					}else
						errors.add("Fila:"+(row+1)+" No existe Empresa ["+clavempresa+"] \n");
								
				}
				if(errors.size()>0) {
					mapa.put("exito", "false");
					mapa.put("error",errors);
				}else {						
					for (int i=0; i<almacenes.size();i++){
						savee(almacenes.get(i));	
					}
				mapa.put("exito", "true");
				mapa.put("error",errors);
				}

			} catch (Exception e) {
				mapa.put("exito", "false");				
				errors.add(e.getMessage());
				mapa.put("error",errors);
			}
		} else {
			String cad = "You failed to upload "
					+ " because the file was empty.";
			mapa.put("exito", "false");				
			errors.add(cad);
			mapa.put("error",errors);
		}
		return mapa;
	}
	//ALMACENES CONFIG PARA MODULOS
	@RequestMapping(value="/configalmacenes",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> getlistalms() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoalmacen.getAlmacenesConfig();
		mapa.put("data",list);
		return mapa;
	}
	@RequestMapping("/updatemodulo")
	public @ResponseBody String updatemoduloestatus(@RequestParam("clave") String clave,@RequestParam("oldvalue") boolean oldvalue,@RequestParam("modulo") String modulo) {
		String cad = "";
		try {
			daoalmacen.updateEstatusModulo(clave,oldvalue,modulo);
			cad = "Nivel actualizado";
		} catch (Exception e) {
			cad = e.getMessage();

		}
		return cad;
	}

	
}
