package com.aps.controllers.catalogos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aps.beans.catalogos.Almacen;
import com.aps.beans.catalogos.Articulo;
import com.aps.beans.catalogos.Clasificacion;
import com.aps.beans.catalogos.Empresa;
import com.aps.beans.seguridad.Usuario;
import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.catalogos.ArtxempresaDao;
import com.aps.dao.catalogos.ClasificacionDao;
import com.aps.dao.catalogos.PrecioDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class ArticulosController {
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	ArtxempresaDao daoartxempresa;
	@Autowired
	ClasificacionDao daoclasificacion;
	@Autowired
	PrecioDao daoprecio;

	@Transactional
	boolean save(Articulo a) {
		if (daoarticulo.save(a)) {
			return true;
		} else {
			return false;
		}

	}

	@Transactional
	public boolean update(Articulo a) {
		if (daoarticulo.update(a)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * METODOS para importation de ALMACENES
	 */
//	@RequestMapping("/formTemplateSKU")
//	public ModelAndView viewFormArticulos() {
//		return new ModelAndView("articulo/formArticulos");
//	}
	
	@RequestMapping("/formTemplateSKU")
	public ModelAndView viewformedit() {		
		int empresa_id = 1;
		Map<String, Map<String, Object>> clasificaciones  =  daoclasificacion.getByListClasificacion(empresa_id);
		System.out.println(clasificaciones);
		Map<String,Object> datos = null;
		for( String clave : clasificaciones.keySet() ) {
			datos = clasificaciones.get(clave);
			System.out.println(datos.get("clave") + " "+ datos.get("id"));
			
		}
		
		return  new ModelAndView("articulo/formArticulos","clasificaciones", clasificaciones);
	}
	
	/*
	 * METODOS para mostar formulario
	 */
	@RequestMapping("/getimportarticulos")
	public ModelAndView viewimportcatArticulos() {
		return new ModelAndView("articulo/formarticulo");
	}
	@RequestMapping(value = "/guardarArticulo",produces="application/json; charset=UTF-8")
	public @ResponseBody Map<String,Object>  saveArticulo(@RequestParam ("formvalues") String form){
		Map<String, Object> mapa = new HashMap<String, Object>();		
		ArrayList<String> errores = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		try {
			
			Object formvalues = parser.parse(form);
			JSONObject arrayFormvalues = (JSONObject)formvalues;
			System.out.println( "arrayFormvalues "+arrayFormvalues);
			int empresa_id = 1; // empresa por default por la session
			int usuario_id = 1; // empresa por default por la session
		
			Date date = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timecreate = formateador.format(date);	
	
			ArrayList<String> registrosError = new ArrayList<String>();
			Map<String, Object> mapaExito = new HashMap<String, Object>();
			List<Object> objectexito    = new ArrayList<Object>();
			int idarticulo = daoarticulo.getArticulobyId(arrayFormvalues.get("codigo").toString());
			int estatus = 0;
			System.out.println(" idarticulo "+idarticulo);
			if(idarticulo == 0) {
				
				mapaExito.put("empresa_id",empresa_id);
				mapaExito.put("codigo", arrayFormvalues.get("codigo").toString());
				mapaExito.put("codalterno", arrayFormvalues.get("codigo").toString());
				mapaExito.put("nombre", arrayFormvalues.get("nombre").toString());
				mapaExito.put("unidad", arrayFormvalues.get("unidad").toString());				
				mapaExito.put("clave_classid", arrayFormvalues.get("clasificacionSKU").toString());
				mapaExito.put("color", arrayFormvalues.get("color").toString());
				mapaExito.put("peso", arrayFormvalues.get("peso").toString());				
				mapaExito.put("volumen", arrayFormvalues.get("volumen").toString());								
										
				mapaExito.put("alto", arrayFormvalues.get("alto").toString() );				
				mapaExito.put("largo", arrayFormvalues.get("largo").toString());				
				mapaExito.put("ancho", arrayFormvalues.get("ancho").toString());			
				mapaExito.put("estatus", estatus);			
				mapaExito.put("timecreate", timecreate);
				mapaExito.put("timechange", timecreate);
				mapaExito.put("usuario_id", usuario_id);
				
				objectexito.add(mapaExito);
				System.out.println(" hola :"+mapaExito );
			}else {
				registrosError.add("El articulo ya existe "+arrayFormvalues.get("codigo").toString()+"  \n");
			}
			System.out.println("registrosError "+registrosError);
			
			if (registrosError.size() > 0) {
				mapa.put("exito", "false");
				mapa.put("error", registrosError);
				
			} else {
				System.out.println("mapaExito : "+mapaExito);
				
				for (Iterator<Object> it = objectexito.iterator(); it.hasNext();) {
					@SuppressWarnings("unchecked")
					Map<String, Object> o = (Map<String, Object>) it.next();						
					System.out.println(" OK"+ o.get("codigo").toString() );	
//					  Integer.parseInt(o.get("empresa_id").toString())
						Articulo articulo = new Articulo(Integer.parseInt(o.get("empresa_id").toString()), o.get("codigo").toString(), 
								o.get("codalterno").toString(),
								o.get("nombre").toString(), 
								o.get("unidad").toString(),
								Integer.parseInt(o.get("clave_classid").toString() ), 
								o.get("color").toString(), 
								Double.parseDouble(o.get("peso").toString()),
								Double.parseDouble(o.get("volumen").toString()) ,
								Double.parseDouble(o.get("alto").toString()), 
								Double.parseDouble(o.get("largo").toString()), 
								Double.parseDouble(o.get("ancho").toString()) ,
								Integer.parseInt( o.get("estatus").toString()),
								o.get("timecreate").toString(), o.get("timechange").toString() ,
								Integer.parseInt(o.get("usuario_id").toString())
								);
	//					 Articulo(int empresa_id,String numart, String numartAlterno, String nomart, String uniart, 
	//							 int clasificacion_id,String color, double peso,double volumenn,double alto,double largo,double ancho, int estatus,String timecreate, String timechage, int usuario_id ) {
						System.out.println(" OK"+ o.get("codigo").toString() );	
						if (save(articulo)) {
							System.out.println("Guardado OK"+ o.get("codigo").toString() );		
							mapa.put("exito", "true");
							mapa.put("error", "Guardado OK"+ o.get("codigo").toString() );
							/*al crear el articulo se crea la lista de precios en base 
							 * las diferentes clasificaciones que hay */
							int numart_id = daoarticulo.getArticulobyId(o.get("codigo").toString());
							Map<String,Map<String,Object>> clasificacion= daoclasificacion.getByListClasificacion(empresa_id);
							
							daoprecio.crearPrecios(empresa_id, numart_id,  o.get("codigo").toString(), clasificacion,  timecreate, usuario_id);
							System.out.println("Precios generados correctamente");
					    }
				}
				
			}			

		}catch(Exception e) {
			mapa.put("exito", "false");			
			errores.add(e.getMessage());
			mapa.put("error",errores);
		}
			
		return mapa;
	}

	@RequestMapping(value = "/doUploadarticulo", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileHandler(@RequestParam("filearticulo") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> objectexito = new ArrayList<Object>();
		if (!file.isEmpty()) {
			int empresa_id = 1;
			int usuario_id = 0;
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				
				String codigo = "";// codigoERP
				String codalterno = "";
				String nombre = "";
				String unidad = "";
				String clave_class = "";
				double peso = 0;
				double volumen = 0;
				String color = "";
				double alto = 0;
				double largo = 0;
				double ancho = 0;
				int estatus = 0;

				for (int row = 1; row < sheet.getRows(); row++) {
					
					codigo = sheet.getCell(0, row).getContents().toString();
					codalterno = sheet.getCell(1, row).getContents().toString();
					nombre = sheet.getCell(2, row).getContents().toString();
					unidad = sheet.getCell(3, row).getContents().toString();
					clave_class = sheet.getCell(4, row).getContents().toString();
					peso = Double.parseDouble(sheet.getCell(5, row).getContents());
					volumen = Double.parseDouble(sheet.getCell(6, row).getContents().toString());
					color = sheet.getCell(7, row).getContents().toString();
					alto = Double.parseDouble(sheet.getCell(8, row).getContents().toString());
					largo = Double.parseDouble(sheet.getCell(9, row).getContents().toString());
					ancho = Double.parseDouble(sheet.getCell(10, row).getContents().toString() );					
										
					int clave_classid = daoclasificacion.findByClave(clave_class);					
					Date date = new Date();
					SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String timecreate = formateador.format(date);
					Map<String, Object> mapaExito = new HashMap<String, Object>();
					
					int idarticulo = daoarticulo.getArticulobyId(codigo);
					if(idarticulo < 0) {
						mapaExito.put("empresa_id",empresa_id);
						mapaExito.put("codigo", codigo);
						mapaExito.put("codalterno", codalterno);
						mapaExito.put("nombre", nombre);
						mapaExito.put("unidad", unidad);
						mapaExito.put("clave_classid", clave_classid);
						mapaExito.put("peso", peso);
						mapaExito.put("volumen", volumen);								
						mapaExito.put("color", color);						
						mapaExito.put("alto", alto);
						mapaExito.put("largo", largo);
						mapaExito.put("ancho", ancho);
						mapaExito.put("estatus", estatus);						
						mapaExito.put("timecreate", timecreate);
						mapaExito.put("timechange", timecreate);
						mapaExito.put("usuario_id", usuario_id);
						
						objectexito.add(mapaExito);
					}else {
						registrosError.add("Fila:" + (row + 1) + "- el articulo ya existe"+codigo+"  \n");
					}															
				}
				//-----------------------------------------------------------------
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				} else {
					for (Iterator<Object> it = objectexito.iterator(); it.hasNext();) {
						@SuppressWarnings("unchecked")
						Map<String, Object> o = (Map<String, Object>) it.next();						
						
							Articulo articulo = new Articulo(Integer.parseInt(o.get("empresa_id").toString()), o.get("codigo").toString(), 
									o.get("codalterno").toString(),
									o.get("nombre").toString(), o.get("unidad").toString(),
									Integer.parseInt(o.get("clave_classid").toString() ), 
									o.get("color").toString(), 
									Double.parseDouble(o.get("peso").toString()),
									Double.parseDouble(o.get("volumen").toString()) ,
									Double.parseDouble(o.get("alto").toString()), Double.parseDouble(o.get("largo").toString()), Double.parseDouble(o.get("ancho").toString()) ,
									Integer.parseInt( o.get("estatus").toString()),
									o.get("timecreate").toString(), o.get("timechange").toString() ,
									Integer.parseInt(o.get("usuario_id").toString())
									);
//							 Articulo(int empresa_id,String numart, String numartAlterno, String nomart, String uniart, 
//									 int clasificacion_id,String color, double peso,double volumenn,double alto,double largo,double ancho, int estatus,String timecreate, String timechage, int usuario_id ) {
//							
							if (save(articulo)) {
								System.out.println("llego aca");
								int idarticulo = daoarticulo.getArticulobyId(o.get("codigo").toString());																																		
						  }
					}
					mapa.put("exito", "true");
					mapa.put("error", "Guardado correctamente");
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
	@RequestMapping("/dataarticulos")
	public ModelAndView viewtablearticulos() {
		return new ModelAndView("articulo/listarticulos");
	}
	
	@RequestMapping(value="/getlistarticulos",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> viewalms() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoarticulo.getlistArticulos();
		mapa.put("data",list);
		return mapa;
	}
	/*eliminar articulos*/
	@RequestMapping(value="/deleteArticulo")
	@ResponseBody Map<String,Object> deleteArticulo(@RequestParam("idfolio")int id, @RequestParam("codsku") String numart ){
		Map<String, Object> mapa = new HashMap<String, Object>();		
		ArrayList<String> errores = new ArrayList<String>();
		try {
			// validar que la serie no este ocupada en algun movimiento
			 daoarticulo.deleteArticulo(id);
			 mapa.put("exito", "true");
			 mapa.put("error","Eliminado correctamente");
			}catch(Exception e) {
				mapa.put("exito", "false");			
				errores.add(e.getMessage());
				mapa.put("error",errores);
			}
		return mapa;
	}
	

}
