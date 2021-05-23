package com.aps.controllers.operacion;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.aps.beans.catalogos.Folio;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.catalogos.ClasificacionDao;
import com.aps.dao.catalogos.ClienteDao;
import com.aps.dao.catalogos.FolioDao;
import com.aps.dao.catalogos.PrecioDao;
import com.aps.dao.operacion.ControlDao;
import com.aps.dao.operacion.CorteDao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.tool.xml.XMLWorkerHelper;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ControlController {
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	PrecioDao daoprecio;
	@Autowired
	ClasificacionDao daoclasificacion;
	@Autowired
	FolioDao daofolio;
	@Autowired
	ClienteDao daocliente;
	@Autowired
	ControlDao daocontrol;
	@Autowired
	CorteDao daocorte;
	@Autowired
	private ResourceLoader resourceLoader;
	

	@RequestMapping("/indexcontrol")
	public ModelAndView showViewControl() {
		return new ModelAndView("control/index");
	}

	@RequestMapping(value = "/cllistArticulos", produces = "application/json; charset=UTF-8")
	public ModelAndView showListaArticulo(@RequestParam("contador") int contador) {
		return new ModelAndView("control/listArticulos", "contador", contador);
	}
	/*
	 * Seleccionar el precio del articulo seleccionado articulo
	 */

//	console.log(contador + " "+ numart+" "+valoroption);
	@RequestMapping(value = "/cloptionskuprecio", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> opcionArticuloPrecio(@RequestParam("numart") String numart,
			@RequestParam("valoroption") String optionClass) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> errores = new ArrayList<String>();

		System.out.println("---" + numart + " " + optionClass);
		try {
			// validar que la serie no este ocupada en algun movimiento
			List<Map<String, Object>> rows = daoprecio.selectPrecioArticuloClasificacion(numart, optionClass);
			System.out.println("---" + rows);
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					Map<?, ?> row = rows.get(i);
					if (Double.parseDouble(row.get("PRECIO").toString()) <= 0) {
						mapa.put("exito", "true");
						mapa.put("error", "0");
					} else {
						mapa.put("exito", "true");
						mapa.put("error", row.get("PRECIO"));
					}
				}
			} else {
				mapa.put("exito", "true");
				mapa.put("error", "0");
			}

		} catch (Exception e) {
			mapa.put("exito", "false");
			errores.add(e.getMessage());
			mapa.put("error", "Parametro no encontrado");
		}
		System.out.println("--- mapa " + mapa);
		return mapa;
	}

	@RequestMapping("/getlistarticuloscl")
	public @ResponseBody Map<String, Object> viewArticulosActivos() {

		int empresa_id = 1;
		List<Map<String, Object>> rows = daoarticulo.getlistArticulosActivos(empresa_id);
		List<Object> deptos = new ArrayList<Object>();
		Map<String, Object> mapa2 = new HashMap<String, Object>();
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("numart", row.get("NUMART"));
			mapa.put("nomart", row.get("NOMART"));
			mapa.put("unidad", row.get("UNIDAD"));

			String htmlAcciones = "<center><button data-idselect = '" + row.get("ID") + "' data-numart='"
					+ row.get("NUMART") + "' data-nombrex='" + row.get("NOMART") + "' data-unidad='" + row.get("UNIDAD")
					+ "'  class='btn btn-success btn-circle btn-sm clselectArticulo' title='Agregar'><i class='fa  fa-check-circle'></i></button></center>";
			mapa.put("acciones", htmlAcciones);
			deptos.add(mapa);
		}
		mapa2.put("data", deptos);

		return mapa2;
	}

	/*
	 *
	 * */
	@RequestMapping("/cloptionClasificacion")
	public @ResponseBody String opcionesClasificacion() {
		String options = "<option value=''>-- Seleccione -- </option>";
		int empresa_id = 1;
		Map<String, Map<String, Object>> listClass = daoclasificacion.getByListClasificacion(empresa_id);

		Map<String, Object> datos = null;
		for (String clave : listClass.keySet()) {
			datos = listClass.get(clave);
			options += "<option value='" + datos.get("clave") + "'>" + datos.get("descripcion") + "</option>";
			System.out.println(datos.get("clave") + " " + datos.get("id") + " " + datos.get("descripcion"));

		}
		return options;
	}

	@RequestMapping("/cloptionIdentificacion")
	public @ResponseBody String cloptionsIdentificacion() {
		String options = "<option value=''>-- Seleccione -- </option>";
		Map<String, Map<String, Object>> listClass = daoclasificacion.getByListIdentificacion();

		Map<String, Object> datos = null;
		for (String clave : listClass.keySet()) {
			datos = listClass.get(clave);
			options += "<option value='" + datos.get("clave") + "'>" + datos.get("descripcion") + "</option>";

			System.out.println(datos.get("clave") + " " + datos.get("id") + " " + datos.get("descripcion"));

		}
		return options;
	}

	/* guardar datos */
	@RequestMapping(value = "/clguardarEquipajex", produces = "application/json; charset=UTF-8")
	public ModelAndView generarTK(@RequestParam("varjson") String json) {
		String mensaje = "";
		int idTramite = 0;
		JSONParser parser = new JSONParser();
		try {
			Object data = parser.parse(json);
			JSONObject arrayData = (JSONObject) data;
			System.out.println("arrayData " + arrayData);
			/*
			 * arrayData {"PEDIDO":"NUEVO","IDENTIFICACION":"PASAPORTE","APELLIDOS":"cruz ojeda","ESTATUS":"CONFIRMADO","TELEFONO":"","EMAIL":"","DESCRIPCION":"nadaa","NEWPEDIDO":[{"PRECIO":"8","COLOR":"rojo","CODIGOSKU":"SKU2","CONTADOR":"1","TIPOCLASS":"M","CANTIDAD":"1","NOMBRESKU":"sd"}],"SERIE":"N","NOMBRE":"vicente","NUMEROIDENTIFICACION":"G16465231"} */
			/* *************************** */
			int empresa_id = 1;
			int usuario_id = 1;
			int almacen_id = 1;
			String documento = "TICKET";
			Date date = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timecreate = formateador.format(date);
			SimpleDateFormat formateador2 = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = formateador2.format(date);
			SimpleDateFormat formateador3 = new SimpleDateFormat("hh:mm:ss");
			String hora = formateador3.format(date);
			/* *************************** */
			// verificar la serie y folio en base al almacen ..!
			// Aumentar la serie y folio .. y retornar los numeros de folios..
			// preparar insert objetc
			// Un folio por cliente y numero de maletas DE 3 O DE N Y EN BASE A ELLO SE
			// GENERA LOS TIKETS

			Map<String, Object> serieFolio = daofolio.serieFolioMax(empresa_id, almacen_id, documento);
			System.out.println(" serieFolio: " + serieFolio);
			String serie = serieFolio.get("SERIE").toString();
			int folio = Integer.parseInt(serieFolio.get("FOLIO").toString());

			idTramite = daocontrol.getIDTramite();
			System.out.println(" IDTRAMITE :" + idTramite);

			// verificar si existe cliente si no se inserta
			int validCliente = daocliente.getClientebyId(arrayData.get("NUMEROIDENTIFICACION").toString());
			int cliente_id = 0;
			if (validCliente == 0) { // se inserta
				daocliente.insert(empresa_id, arrayData.get("NUMEROIDENTIFICACION").toString(),
						arrayData.get("NOMBRE").toString(), arrayData.get("APELLIDOS").toString(), "", "", "", "", "",
						arrayData.get("TELEFONO").toString(), arrayData.get("EMAIL").toString(), timecreate);
				System.out.println("Cliente guardado");
				cliente_id = daocliente.getClientebyId(arrayData.get("NUMEROIDENTIFICACION").toString());
			} else {
				cliente_id = validCliente;
			}
			String serieBoleto = "";
			int folioBoleto = 0;
			Map<String, Object> mapa = null;
			ArrayList<Object> equipaje = (ArrayList<Object>) arrayData.get("NEWPEDIDO");
			System.out.println(" ******* equipaje " + equipaje);
			int numitems = equipaje.size();
			int numart_id = 0;
			int cantidad = 0;
			double tiempoMinimo = daocontrol.getConfigTiempoMinimo(empresa_id );
			/* Guardar header
			 * lista de cabecera */
			  
//			   int idTramite, int empresa_id, int almacen_id, String serie, int folio, String serieBoleto, int folioBoleto, int cliente_id, String identificacion, String fecha, String hora, int usuario_id, String timecreate
			daocontrol.saveHeadControl(idTramite, empresa_id, almacen_id, serie, folio, serieBoleto, folioBoleto,
					cliente_id, arrayData.get("NUMEROIDENTIFICACION").toString(),arrayData.get("IDENTIFICACION").toString(), numitems, fecha, hora, usuario_id,
					timecreate, tiempoMinimo,arrayData.get("DESCRIPCION").toString());

			Map<String, Object> precioclass = null;
//			 [{"PRECIO":"20","COLOR":"roja","CODIGOSKU":"SKU5","CONTADOR":"1","TIPOCLASS":"S","CANTIDAD":"1","NOMBRESKU":"MORRAL"}
			for (int i = 0; i <= equipaje.size(); i++) {

				mapa = (Map<String, Object>) equipaje.get(i);
				numart_id = daoarticulo.getArticulobyId(mapa.get("CODIGOSKU").toString());

//				 {descripcion=CHICA, numprecio=1, clave=S, precio=20.0, clasificacionid=2, idp=6}
				precioclass = daoprecio.getBySKUClasificacionPrecio(empresa_id, mapa.get("CODIGOSKU").toString(),
						mapa.get("TIPOCLASS").toString());
				System.out.println("precioclass" + precioclass);
				if (precioclass.size() <= 0) {
					mensaje = " El " + mapa.get("CODIGOSKU").toString() + " No tiene configurado el PRECIO";
					break;
				} else {

					cantidad = Integer.parseInt(mapa.get("CANTIDAD").toString());
					String codigoSKU = mapa.get("CODIGOSKU").toString();
					String color = mapa.get("COLOR").toString();
					double precio = Double.parseDouble(precioclass.get("precio").toString());
					int cantidaMaletas = Integer.parseInt(mapa.get("CANTIDAD").toString());
					double subtotal = daocontrol.subtotal(precio, tiempoMinimo, cantidaMaletas);
					String tipoDescuento = "";
					double porcDescuento = 0;
					double descuento = daocontrol.descuento(subtotal, porcDescuento);
					double precioNeto = daocontrol.precioNeto(subtotal, descuento);
					double iva = 0;
					double montoIVA = daocontrol.montoIVA(precioNeto, iva);
					double precioFinal = daocontrol.precioFinal(precioNeto, montoIVA);
					daocontrol.saveBodyControl(idTramite, almacen_id, serie, folio, numart_id, codigoSKU, cantidad,
							color, Integer.parseInt(precioclass.get("clasificacionid").toString()),
							precioclass.get("clave").toString(),
							Integer.parseInt(precioclass.get("numprecio").toString()), precio, tiempoMinimo, subtotal,
							tipoDescuento, porcDescuento, descuento, precioNeto, iva, montoIVA, precioFinal,
							timecreate);
				}
			}

		} catch (Exception e) {
			mensaje = "Cadena JSON erronea " + e.getMessage();
		}
		ModelAndView model = new ModelAndView();
		
		model.addObject("idtramite", idTramite);
		model.addObject("mensaje", mensaje);			
		model.setViewName("control/pdfTK");
	  return model;			
	}
    /* consultar y busqueda maleta  */

	@RequestMapping("/getconsultar")
	public ModelAndView showViewconsultarTK() {
		return new ModelAndView("control/consultartk");
	}
	
	/*buscar boleto o tikect */
	@RequestMapping(value="/clSearchTK")
	@ResponseBody
	 public String clDetalleTk(@RequestParam("idtramite") int idtramite) throws ParseException{
		 System.out.print("idtramite" +idtramite);		 
		 int empresa_id = 1;
		 int usuario_id = 1;
		 String htmlResponse ="";
		 /* 
		  * actualizar tiempo real de consulta 
		  * */
		 daocontrol.actualizarTiempo(empresa_id, idtramite, usuario_id);
		 /* 
		  * Lista de tiked actualizada 
		  * */
		 List<Map<String, Object>> rows = daocontrol.listatks(empresa_id, idtramite);
		 System.out.println("-------> rows"+rows);		
		 System.out.println("-------> rows"+rows.size());
		 
		 if(rows.size() <= 0 ) {
			 htmlResponse = "<h4 class=\"card-title\">Datos de tiket</h4> \r\n" + 
				 		"                       <div class=\"alert alert-danger alert-rounded align-items-center flex-row m-t-10\"> \r\n" + 
				 		"                      <h3 class=\"m-b-0\"> No existe Tiket ! </h3> <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"> <span aria-hidden=\"true\">×</span> </button> "+     
				 		"                       </div><hr>";
		 }else {
			   
		    
			 int estatus_mov = Integer.parseInt(rows.get(0).get("ESTATUS_MOV").toString()); // 0 no pagado : 1 pago efectuado
			 
			 htmlResponse = "<h4 class=\"card-title\">Datos de tiket</h4> \r\n" + 
			 		"                       <div class=\"d-flex align-items-center flex-row m-t-10\"> \r\n" + 
			 		"                           <div class=\"p-2 display-6 text-info\"><i class=\"fa fa fa-user-circle\"></i> <span>"+rows.get(0).get("NOMBRE")+" "+rows.get(0).get("APELLIDOS")+"</span></div> \r\n" +
			 		"                           <div class=\"p-2\"><h3 class=\"font-medium m-b-0\">"+rows.get(0).get("SERIE")+"-"+rows.get(0).get("folio")+"</h3><small>Serie-Folio</small></div> \r\n" + 
			 		"                           <div class=\"p-2\"><h3 class=\"m-b-0\">"+rows.get(0).get("NUMITEMS")+"</h3><small>Equipajes</small></div> \r\n" + 
			 		"                           <div class=\"p-2\"><h3 class=\"m-b-0\">"+rows.get(0).get("FECHAIN")+"</h3><small>Fecha</small></div> \r\n" + 
			 		"                           <div class=\"p-2\"><h3 class=\"m-b-0\">"+rows.get(0).get("HORAIN")+"</h3><small>Hora</small></div> \r\n" +
			 		"                           <div class=\"p-2\"><h3 class=\"m-b-0\">"+rows.get(0).get("IDENTIFICACION")+"</h3><small>Identificación</small></div> \r\n" +
			 		"                           <div class=\"p-2\"><h3 class=\"m-b-0\">"+rows.get(0).get("CODIGO")+"</h3><small>Codigo</small></div> \r\n" +
			 		"                       </div>";
			 htmlResponse += "<hr><table class='table no-border'>";
			 htmlResponse += "<thead><tr>";
			 htmlResponse +="<th>#</th> <th>CANTIDAD</th> <th>DESCRIPCION</th> <th>TAMAÑO</th> <th>PRECIO</th> <th><i class='fa fa-clock-o'></i> TIEMPO hr</th> <th>TOTAL</th>";
			 htmlResponse +="</tr></thead><body>";
			 int indice = 1;
			 int totalEquipaje = 0;
			 double totalMoney =0;
			 
			 NumberFormat formatter;
			 formatter = new DecimalFormat("#,###.##");		 		        
	
			  for (Map<?, ?> row : rows) {
				 Map<String, Object> mapa = new HashMap<String, Object>();
				       mapa.put("idtramite", row.get("idtramite"));
				       mapa.put("empresa_id", row.get("EMPRESA_ID"));
				  htmlResponse +="<tr><td>"+indice+"</td> <td class='font-medium'><h4>"+row.get("CANTIDAD")+"</h4></td><td>"+row.get("NOMART")+"</td><td>"+row.get("NOMBRECLASS")+"</td> <td>"+row.get("PRECIO")+"</td> <td class='font-medium'>"+row.get("TIEMPOTOTAL")+"</td> <td class='font-medium'><h4 class'font-medium'>$ "+formatter.format(row.get("SUBTOTAL"))+"</h4></td></tr>";
				  totalEquipaje = totalEquipaje + Integer.parseInt(row.get("CANTIDAD").toString()); 
				  totalMoney    = totalMoney + Double.parseDouble(row.get("SUBTOTAL").toString());
				  indice++;
			 }
			  htmlResponse +="<tr><td><h4 class=\"font-medium\">TOTAL<h4></td> <td><h4 class=\"font-medium\">"+totalEquipaje+"</h4></td> <td></td> <td></td> <td></td> <td></td> <td class='font-medium'><h4 class=\"font-medium\">$ "+formatter.format(totalMoney)+"</h4></td></tr>";
			  htmlResponse +="<tbody> </table>";
			  
			  if(estatus_mov == 0)
			  {
				  htmlResponse +="<hr><div><button type=\"button\" class=\"btn waves-effect waves-light btn-rounded btn-danger\" onclick=\"payUp("+idtramite+")\" ><i class='fa fa-dollar'></i> Pagar</button></div>"; 
			  }else {
				  String timeOut = rows.get(0).get("TIMEOUT").toString();
				  htmlResponse +="<hr> <div class='alert alert-info'><h2 class=\"font-medium m-b-0\">"+timeOut+"</h2><small>Hora de entrega o salida</small></div>";
			  }
		 } // end else 	  
		 return htmlResponse;
	 }
	  
	@RequestMapping("/formPayUp")
	public ModelAndView showViewFormPayUp(@RequestParam("idtramite") int idtramite) {
		 System.out.print("formulario pago idtramite" +idtramite);
		 ModelAndView model = new ModelAndView();
		 
		    double total = daocontrol.totalTiket(idtramite);
		    
		    DecimalFormat df = new DecimalFormat("#.00");
		    total = Double.parseDouble(df.format(total));
			model.addObject("idtramite", idtramite);
			model.addObject("total", total);			
			model.setViewName("control/formPayUp");
		return model;		
	}
//	data:{idtramite:idtramite,totalPayup :totalPayup,montoPago :montoPago,cambio:cambio},
	/* idtramite:1
		totalPayup:168.3
		montoPago:200
		cambio:31.70 */
	@RequestMapping("/clplayPayUp")
    @ResponseBody
	public ModelAndView ejecutarPago(@RequestParam("idtramite") int idtramite, @RequestParam("totalPayup") double totalPayup, @RequestParam("montoPago") double montoPago, @RequestParam("cambio") double cambio ) {
		String respuesta = "";
		String accion = "";
		int usuario_id = 1;
		DecimalFormat df = new DecimalFormat("#.00");	 	
		 double total = daocontrol.totalTiket(idtramite);
		 total = Double.parseDouble(df.format(total));
		 Date date = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeOut = formateador.format(date);
		if( montoPago >= total ) {
			String formaPago = "EFECTIVO";
			daocontrol.updateEquipajeOut(idtramite, formaPago, timeOut, usuario_id);
			daocontrol.insertControlpago(idtramite, formaPago, totalPayup,montoPago,cambio,timeOut,usuario_id );
			respuesta = "Imprimir comprobante de pago ..!";
			accion = "true";
		}else {
			respuesta = "Valores incorrectos, verifique el monto de pago total a pagar= "+total+"..!";
			accion = "false";
		}
      ModelAndView model = new ModelAndView();
        model.addObject("accion", accion);
		model.addObject("idtramite", idtramite);
		model.addObject("mensaje", respuesta);			
		model.setViewName("control/pdfPagoTK");
	  return model;
	}
	 
	/*buscar boleto o tikect */
	@RequestMapping(value = "/forecastpdf", produces = "application/pdf;charset=UTF-8")
	public @ResponseBody ModelAndView report(@RequestParam("idtramite") int idtramite, HttpServletResponse response) throws JRException, IOException {
		//AGREGAR LA EMPRESA EN LA BUSQUEDA que va a salir de la sesion
		int empresa = 1;	
		System.out.println("idtramite****"+idtramite);
		int empresa_id =1;
		int almacen_id= 1;
		int usuario_id =1;
				
		//-------------------------------------------------------------------------------------
		List<Map<String, Object>> datostk = daocontrol.listatks(empresa_id, idtramite);
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("EMPRESA", "EASIPRUEBA");
		parametros.put("IDTRAMITE", datostk.get(0).get("IDTRAMITE").toString() );
		parametros.put("SERIE", datostk.get(0).get("SERIE").toString() );
		parametros.put("FOLIO", datostk.get(0).get("FOLIO").toString() );
		parametros.put("SERIEFOLIO", datostk.get(0).get("SERIE").toString()+"-"+datostk.get(0).get("FOLIO").toString() );
		parametros.put("CLIENTE", datostk.get(0).get("NOMBRE")+" "+ datostk.get(0).get("APELLIDOS") );
		parametros.put("IDENTIFICACION", datostk.get(0).get("IDENTIFICACION").toString() );
		parametros.put("CLIENTECOD", datostk.get(0).get("CLIENTECOD").toString() );
		//-------------------------------------------------------------------------------------
		
		List<Object> deptos = new ArrayList<Object>();
        
		for (int i = 0; i < datostk.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = datostk.get(i);
			mapa.put("INDICE", (i + 1));
			mapa.put("NUMART", row.get("NUMART").toString());
			mapa.put("NOMART", row.get("NOMART").toString());
			mapa.put("CANTIDAD", row.get("CANTIDAD").toString());
			mapa.put("COLOR", row.get("COLOR").toString());
			mapa.put("NOMBRECLASS", row.get("NOMBRECLASS").toString());
						
			System.out.println("mapa-----> "+mapa);
			deptos.add(mapa);
		}
				
		System.out.println("deptos --->"+deptos);
		//-------------------------------------------------------------------------------------
		List<Map<String, Object>> corteh = daocorte.sessionHome(empresa_id, almacen_id, usuario_id);
		parametros.put("USUARIO", corteh.get(0).get("nombreusuario"));
		parametros.put("CLAVEALM", corteh.get(0).get("CLAVE"));
		parametros.put("NOMBREALM", corteh.get(0).get("NOMBRE"));
		parametros.put("DIRECCIONALM", corteh.get(0).get("DIRECCION"));
		//-------------------------------------------------------------------------------------
		String path       = resourceLoader.getResource("classpath:ejemplo.jrxml").getURI().getPath();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		//lista de datos 
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(deptos);
		// parametros para el reporte
		
        byte[] reporte = null;
        try {
        	System.out.println(parametros);
            reporte = JasperRunManager.runReportToPdf(jasperReport, parametros,beanColDataSource);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setContentLength(reporte.length);
        ServletOutputStream out = response.getOutputStream();
        out.write(reporte, 0, reporte.length);
        out.flush();
        out.close();
 
        return new ModelAndView("index");
	}
	/*COMPROBANTE DE PAGOO*/
	@RequestMapping(value = "/comprobaantePayUppdf", produces = "application/pdf;charset=UTF-8")
	public @ResponseBody ModelAndView reportPayUp(@RequestParam("idtramite") int idtramite, HttpServletResponse response) throws JRException, IOException {
		//AGREGAR LA EMPRESA EN LA BUSQUEDA que va a salir de la sesion
		int empresa = 1;
		
		System.out.println("idtramite****"+idtramite);
		
		int empresa_id =1;
		int almacen_id= 1;
		int usuario_id =1;
		DecimalFormat df2 = new DecimalFormat("#,###.00");
		double total = daocontrol.totalTiket(idtramite);	
		 
		//-------------------------------------------------------------------------------------
		List<Map<String, Object>> datostk = daocontrol.listatks(empresa_id, idtramite);
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("EMPRESA", "EASIPRUEBA");
		parametros.put("IDTRAMITE", datostk.get(0).get("IDTRAMITE").toString() );
		parametros.put("SERIE", datostk.get(0).get("SERIE").toString() );
		parametros.put("FOLIO", datostk.get(0).get("FOLIO").toString() );
		parametros.put("SERIEFOLIO", datostk.get(0).get("SERIE").toString()+"-"+datostk.get(0).get("FOLIO").toString() );
		parametros.put("CLIENTE", datostk.get(0).get("NOMBRE")+" "+ datostk.get(0).get("APELLIDOS") );
		parametros.put("IDENTIFICACION", datostk.get(0).get("IDENTIFICACION").toString() );
		parametros.put("CLIENTECOD", datostk.get(0).get("CLIENTECOD").toString() );
		parametros.put("TOTAL", df2.format(total) );
		//-------------------------------------------------------------------------------------
		ArrayList<Object> mapa2 = new ArrayList<Object>();
		List<Object> deptos = new ArrayList<Object>();
        
		for (int i = 0; i < datostk.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = datostk.get(i);
			mapa.put("INDICE", (i + 1));
			mapa.put("CODSKU", row.get("NUMART").toString());
			mapa.put("NOMBRE", row.get("NOMART").toString());
			mapa.put("CANTIDAD", row.get("CANTIDAD").toString());
			mapa.put("COLOR", row.get("COLOR").toString());
			mapa.put("TAMANIO", row.get("NOMBRECLASS").toString());
			mapa.put("PRECIO", df2.format(row.get("PRECIO").toString()));
			mapa.put("TIEMPO", df2.format(row.get("TIEMPOTOTAL").toString()));
			mapa.put("TOTALFINAL", df2.format(row.get("PRECIOFINAL").toString()));
			deptos.add(mapa);
		}
		mapa2.add(deptos);		
		System.out.println("mapa2---"+mapa2);
		//-------------------------------------------------------------------------------------
		List<Map<String, Object>> corteh = daocorte.sessionHome(empresa_id, almacen_id, usuario_id);
		parametros.put("USUARIO", corteh.get(0).get("nombreusuario"));
		parametros.put("CLAVEALM", corteh.get(0).get("CLAVE"));
		parametros.put("NOMBREALM", corteh.get(0).get("NOMBRE"));
		parametros.put("DIRECCIONALM", corteh.get(0).get("DIRECCION"));
		//-------------------------------------------------------------------------------------
		String path       = resourceLoader.getResource("classpath:comprobantePago.jrxml").getURI().getPath();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		//lista de datos 
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(deptos);
		// parametros para el reporte
		
        byte[] reporte = null;
        try {
        	System.out.println(parametros);
            reporte = JasperRunManager.runReportToPdf(jasperReport, parametros,beanColDataSource);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setContentLength(reporte.length);
        ServletOutputStream out = response.getOutputStream();
        out.write(reporte, 0, reporte.length);
        out.flush();
        out.close();
 
        return new ModelAndView("index");
	}
	
	/* @RequestMapping(value="/clSearchTK", produces = "application/json; charset=UTF-8" )
	 public String clDetalleTk(@RequestParam("idtramite") int idtramite, Model model, RedirectAttributes flash, Locale locale){
		int empresa_id = 1;
//		Folio folio = (Folio) daofolio.listFolios(empresa_id);
//		System.out.println(folio);
//		model.addAttribute("folio", folio);
		model.addAttribute("titulo", "holaaa" );
		 return "control/pdfTK";
	 }  */
	 
	
	/* @RequestMapping(value = "/clSearchTK")
	public @ResponseBody void ReorderPdftBySupplier(@RequestParam("idtramite") int idtramite) {
		 try
	        {
	            OutputStream file = new FileOutputStream(new File("C:/Users/deil_vco/DownloadsHTMLtoPDF.pdf"));
	            Document document = new Document();
	            PdfWriter writer = PdfWriter.getInstance(document, file);
	            StringBuilder htmlString = new StringBuilder();
	            htmlString.append(new String("<html><body> This is HMTL to PDF conversion Example<table border='2' align='center'> "));
	            htmlString.append(new String("<tr><td>JavaCodeGeeks</td><td><a href='examples.javacodegeeks.com'>JavaCodeGeeks</a> </td></tr>"));              
	            htmlString.append(new String("<tr> <td> Google Here </td> <td><a href='www.google.com'>Google</a> </td> </tr></table></body></html>"));
	                             
	            document.open();
	            InputStream is = new ByteArrayInputStream(htmlString.toString().getBytes());
	            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
	            document.close();
	            file.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }	        
	}
	**/
	
}
