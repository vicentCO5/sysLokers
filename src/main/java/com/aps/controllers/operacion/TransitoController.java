package com.aps.controllers.operacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aps.beans.operacion.Transito;

import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.operacion.TransitoDao;

import jxl.Sheet;
import jxl.Workbook;


@Controller
public class TransitoController {
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	TransitoDao daotransito;
	
	/* form importar transito */
	@RequestMapping("/getimportartransito")
	public ModelAndView formimportartransito() {
		return new ModelAndView("/transito/formtransito");
	}
	
	/* Cargar datos de excel para importar */
	@RequestMapping(value="/doUploadtransito",  produces = "application/json; charset=UTF-8")
	public @ResponseBody Map <String, Object> uploadFileHandler(@RequestParam("filetransito") MultipartFile file){
		ArrayList<String> registrosError = new ArrayList<String>();
		List<Transito> transitos = new ArrayList<Transito>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		 System.out.println("holaaaaaaaaaa");
		 if (!file.isEmpty()) {
				try {
					Workbook workbook = Workbook.getWorkbook(file.getInputStream());
					Sheet sheet = workbook.getSheet(0);
					int codEmpresa = 1;
					String numeroFolio = "";
					String codAlmacen = "";							
					String codSku = "";
					String cantidadInicial ="";
					String cantidadFaltante ="";
					String unidad = "";
					String fechaLLegada = "";
					String timeCreate = "";				
					String codAgente = null;
									
					for (int row = 1; row < sheet.getRows(); row++) {
						
						numeroFolio    = sheet.getCell(0, row).getContents().toString();
						codAlmacen     = sheet.getCell(1, row).getContents().toString();						
						codSku           = sheet.getCell(2, row).getContents().toString();
						cantidadInicial  = (sheet.getCell(3, row).getContents().toString()).equals("") ? "0":sheet.getCell(3, row).getContents().toString();
						cantidadFaltante = (sheet.getCell(4, row).getContents().toString()).equals("") ? "0":sheet.getCell(4, row).getContents().toString();
						unidad            = sheet.getCell(5, row).getContents().toString();
						fechaLLegada   = sheet.getCell(6, row).getContents().toString(); // format yyyy-mm-dd						
												
						// Obtener el tiempo actual de movimiento 
						Date date = new Date();
						SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						timeCreate = formateador.format(date);		            		            
						
						 int idcodAlmacen = daoalmacen.findByid(codAlmacen);
						
						
						if (idcodAlmacen != 0 ) {						
							int idarticulo = daoarticulo.getArticulobyId(codSku);
							
							if (idarticulo != 0) {
								Transito transito = new Transito( codEmpresa, numeroFolio,idarticulo, idcodAlmacen,
										Double.parseDouble(cantidadInicial),Double.parseDouble(cantidadFaltante),unidad,
										fechaLLegada,timeCreate,codAgente );								
								
								transitos.addAll(Arrays.asList(transito));

							} else {
								registrosError.add("Fila:" + (row + 1) + "- No existe Articulo [" + codSku + "] \n");
							}

						} else {
							registrosError.add("Fila:" + (row + 1) + "- No existe Almacen [" + idcodAlmacen + "] \n");
						}
					}
					
					if (registrosError.size() > 0) {
						mapa.put("exito", "false");
						mapa.put("error", registrosError);
					}else {
						for(int i=0;i<transitos.size();i++) {
							saveTransito(transitos.get(i));
						}
						mapa.put("exito", "true");
						mapa.put("error",registrosError);
					}
				} catch (Exception e) {
					mapa.put("exito", "false");
					registrosError.add(e.getMessage());
					mapa.put("error", registrosError);
				}
			} else {
				String cad = "You failed to upload " + " because the file was empty.";
				mapa.put("exito", "false");
				registrosError.add(cad);
				mapa.put("error", registrosError);
			}
			return mapa;
	}
	/*
	 * @ guardar transito */
	@Transactional
	void saveTransito(Transito t) {
		daotransito.save(t);
	}
	/*
	 * Mostrar lista de traspasos
	 * */
	@RequestMapping("/listtransito")
	public ModelAndView viewTabletraspasos() {
		return new ModelAndView("transito/listatransitos");
	}
	
	@RequestMapping("/getdatatransito")
	public @ResponseBody
	Map <String, Object> listTransito(){
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daotransito.listaTransito();
		mapa.put("data", list);
		return mapa;
	}		
	
	@RequestMapping( value="/deletetransito", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteTransitoAll(){
		Map <String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String > listaErrores = new ArrayList <String>();
		try {
			// agregar empresa 
			int empresa_id = 1;
			daotransito.deleteTransito(empresa_id);
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminado correctamente");
		}catch(Exception e) {
			// TODO Auto-generated catch block
			mapa.put("exito", "false");
			listaErrores.add( e.getMessage()); 
			mapa.put("error", listaErrores);
		}
		return mapa;
	}
	
	

}
