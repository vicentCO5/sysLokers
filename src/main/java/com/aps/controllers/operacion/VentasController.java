package com.aps.controllers.operacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import com.aps.beans.operacion.Ventas;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.operacion.VentasDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class VentasController {
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	VentasDao daoventas;

	@RequestMapping("/formrangevtas")
	public ModelAndView viewimportAlms() {
		return new ModelAndView("ventas/form_daterange");
	}

	@RequestMapping(value = "/searchalmacenes", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Object> viewalms() {
		List<Object> list = daoalmacen.getAlmacenes();
		return list;
	}
	
	
	@Transactional
	void saveVenta(Ventas v) {
		daoventas.save(v);
	}
	/////////import ventas/////////////
	@RequestMapping("/getimportventas")
	public ModelAndView viewimportvtas() {
		return new ModelAndView("ventas/formventas");
	}

	@RequestMapping(value = "/doUploadventas", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileHandler(@RequestParam("fileventas") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		List<Ventas> ventas = new ArrayList<Ventas>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		 System.out.println("holaaaaaaaaaa");
		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				String sku = "";
				String codnumalm = "";
				String sumqty = "";
				String sumavent = "";
				String unidad = "";
				String periododia = "";
				String cliente = "";
				String canal = "";
				String precio = "";
				String porcendesc = "";
				String descuento = "";
				String precioneto = "";
				String ieps = "";
				String preciobruto = "";
				String iva = "";
				String preciofinal = "";
				String factura = "";
				String tipomov = "";
				for (int row = 1; row < sheet.getRows(); row++) {
					
					sku = sheet.getCell(0, row).getContents().toString();
					codnumalm = sheet.getCell(1, row).getContents().toString();
					sumqty = sheet.getCell(2, row).getContents().toString();
					sumavent = (sheet.getCell(14, row).getContents().toString()).equals("") ? "0":sheet.getCell(14, row).getContents().toString();
					unidad = sheet.getCell(3, row).getContents().toString();
					periododia = sheet.getCell(4, row).getContents().toString();
					cliente = sheet.getCell(5, row).getContents().toString();
					canal = sheet.getCell(6, row).getContents().toString();
					precio = (sheet.getCell(7, row).getContents().toString()).equals("") ? "0":sheet.getCell(7, row).getContents().toString();
					porcendesc = (sheet.getCell(8, row).getContents().toString()).equals("") ? "0":sheet.getCell(8, row).getContents().toString();
					descuento = (sheet.getCell(9, row).getContents().toString()).equals("") ? "0":sheet.getCell(9, row).getContents().toString();
					precioneto = (sheet.getCell(10, row).getContents().toString()).equals("") ? "0":sheet.getCell(10, row).getContents().toString();
					ieps = (sheet.getCell(11, row).getContents().toString()).equals("")? "0":sheet.getCell(11, row).getContents().toString();
					preciobruto = (sheet.getCell(12, row).getContents().toString()).equals("") ? "0":sheet.getCell(12, row).getContents().toString();
					iva = (sheet.getCell(13, row).getContents().toString()).equals("") ? "0":sheet.getCell(13, row).getContents().toString();
					preciofinal = (sheet.getCell(14, row).getContents().toString()).equals("") ? "0":sheet.getCell(14, row).getContents().toString();
					factura = sheet.getCell(15, row).getContents().toString();
					tipomov = sheet.getCell(16, row).getContents().toString();
					Date fechacambio = new Date();
					SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String fecha = formateador.format(fechacambio);
					Integer estatus = null;
					String anio = periododia.substring(0,4);
					String mes= periododia.substring(4,6);
					String dia = periododia.substring(6);
					String aniomes=anio+mes;
					/*@ numero de semana de al venta */
					Calendar calendar = Calendar.getInstance();
					calendar.setFirstDayOfWeek( Calendar.MONDAY);
					calendar.setMinimalDaysInFirstWeek( 4 );
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					calendar.setTime( sdf.parse(dia+"/"+mes+"/"+anio ));
		            int semana =  calendar.get( Calendar.WEEK_OF_YEAR );  // imprime 1
		            System.out.println(semana);
					
					int idalmacen = daoalmacen.findByid(codnumalm);
					if (idalmacen != 0) {
						int idarticulo = daoarticulo.getArticulobyId(sku);
						if (idarticulo != 0) {
							Ventas venta = new Ventas(idarticulo, idalmacen, Double.valueOf(sumqty),Double.valueOf(sumavent),
									 unidad, dia, mes, anio, aniomes,
									periododia, semana,cliente, canal, "", Double.valueOf(precio),
									Double.parseDouble(porcendesc), Double.parseDouble(descuento),
									Double.parseDouble(precioneto), Double.parseDouble(ieps),
									Double.parseDouble(preciobruto), Double.parseDouble(iva),
									Double.parseDouble(preciofinal), factura, fecha, Integer.parseInt(tipomov),
									estatus);
							
							ventas.addAll(Arrays.asList(venta));

						} else {
							registrosError.add("Fila:" + (row + 1) + "- No existe Articulo [" + sku + "] \n");
						}

					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Almacen [" + codnumalm + "] \n");
					}
				}
				
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				}else {
					for(int i=0;i<ventas.size();i++) {
						saveVenta(ventas.get(i));
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
	
	/*/////////////////////////////*/
	@RequestMapping("/listventas")
	public ModelAndView viewtableventas() {
		return new ModelAndView("ventas/listventas");
	}
	@RequestMapping(value="/getdataventas",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> listventas(@RequestParam("almacen") String opalmacen,@RequestParam("finicio") String finicio,@RequestParam("ffin") String ffin) {
		//System.out.println(ffin);
		String[] fechainicio = finicio.split("-");
		String anioi = fechainicio[0]; // yyyy
		String mesi = fechainicio[1]; // mm
		String diai = fechainicio[2]; // dd
		
		String[] fechafin = ffin.split("-");
		String aniof = fechafin[0]; // yyyy
		String mesf = fechafin[1]; // mm
		String diaf = fechafin[2]; // dd
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoventas.ventasbyalmacen_fechas(opalmacen,anioi+mesi+diai,aniof+mesf+diaf);
		mapa.put("data",list);
		return mapa;
	}
	
	/**
	 * @param almacen
	 * @return
	 */
	@RequestMapping(value="/configperiodos",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> getlistperiodosvtas(@RequestParam("almacen") String almacen) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = null;
		String tipoperiodo = daoventas.gettipoperiodo();
		list = daoventas.getlistperiodosvtas(almacen,tipoperiodo);		
		mapa.put("data",list);
		return mapa;
	}
	/**
	 * @param almacen
	 * @return
	 */
	@RequestMapping(value="/periodosselected",produces="application/json; charset=UTF-8")
	public @ResponseBody
	Map<String,Object> getlistperiodosvtasSelected(@RequestParam("almacen") String almacen) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoventas.getlistperiodosselect(almacen);		
		mapa.put("data",list);
		return mapa;
	}
	/**
	 * @param almacen
	 * @param periodo
	 * @return
	 */
	@RequestMapping("/addperiodo")
	public @ResponseBody String addPeriodo(@RequestParam("almacen") String almacen,@RequestParam("periodo")String periodo) {
		String cad="";
		try {
			int periodosSelect = daoventas.periodosSelected(almacen);
			int numperiodos = daoventas.getnumperiodos("NUMPERIODOSGENERAL");
			if( periodosSelect < numperiodos ){
				boolean band = daoventas.searchPeriodo(almacen, periodo);
				if (band) {
					cad = "El periodo ya existe..!";
				}else {
					if(daoventas.addPeriodo(almacen, periodo)) {
						cad = "Agregado correctamente ..!";
					}else {
						cad = "No se ha agregado ..!";
					}
				}
			}else {
				cad = "Solo se permite "+numperiodos+" numero de periodos..!";
			}
			
			
		} catch (Exception e) {
			cad=e.getMessage();
			
		}
		return cad;
	}
	/**
	 * @param almacen
	 * @param periodo
	 * @return
	 */
	@RequestMapping("/deleteperiodo")
	public @ResponseBody String deletePeriodo(@RequestParam("almacen") String almacen,@RequestParam("periodo")String periodo) {
		String cad="";
		try {
			if(daoventas.deletePeriodo(almacen,periodo)) {
				cad = "Eliminado correctamente..!";
			}
			
		} catch (Exception e) {
			cad=e.getMessage();
			
		}
		return cad;
	}
	@RequestMapping("/configperiodosRe")
	public ModelAndView configperiodosRe() {
		return new ModelAndView("config/listperiodosresurt");
	}
	@RequestMapping("/configperiodosPv")
	public ModelAndView configperiodosPv() {
		return new ModelAndView("config/listperiodospv");
	}

}
