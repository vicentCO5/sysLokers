package com.aps.dao.operacion;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class ControlDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	public int getIDTramite() {
		int idtramite = 0;
		String sql = "SELECT MAX(IDTRAMITE) AS IDTRAMITE  FROM cabcontrol ";		
		List<String> certs = template.queryForList(sql, String.class);
		/*System.out.println(" certs : "+certs);
		System.out.println(" certs : "+certs.isEmpty());
		System.out.println(" certs : "+certs.size() );
		System.out.println(" certs : "+certs.get(0) ); */
		if (certs.get(0) == null) {
			idtramite = 0;
		} else {
			idtramite = Integer.parseInt(certs.get(0));
		} 
		idtramite = idtramite + 1 ;	
		// actualiza el id tramite 
		sql = "INSERT INTO `cabcontrol`( `IDTRAMITE`) VALUES ('"+idtramite+"')";
		template.update(sql);
		return idtramite;
	}
	public double getConfigTiempoMinimo(int empresa_id ) {
		double tiempoMinimo = 0;
		String sql = "SELECT valor FROM `config` where parametro = 'TIEMPOMINIMO'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.get(0) == null) {
			tiempoMinimo = 0;
		} else {
			tiempoMinimo = Double.parseDouble(certs.get(0));			
		} 
		return tiempoMinimo;
	}
	/*
	 * Guardar header control
	 * */
	public int saveHeadControl(int idTramite, int empresa_id, int almacen_id, String serie, int folio, String serieBoleto, int folioBoleto, 
			    int cliente_id, String identificacion,String cveIdentificacion,int numitems, String fecha, String hora, int usuario_id, String timecreate ,double tiempoMinimo ,String obs  ) {
		String sql = "UPDATE `cabcontrol` SET `EMPRESA_ID`='"+empresa_id+"',`ALMACEN_ID`='"+almacen_id+"',`SERIE`='"+serie+"',`FOLIO`='"+folio+"',"
				+ "`SERIEBOLETO`='"+serieBoleto+"',`FOLIOBOLETO`='"+folioBoleto+"',`CLIENTE_ID`='"+cliente_id+"',`CLIENTECOD`='"+identificacion+"', `IDENTIFICACION`='"+cveIdentificacion+"',`NUMITEMS`='"+numitems+"',"
			    + "`FECHAIN`='"+fecha+"',`HORAIN`='"+hora+"',`USUARIO_IDIN`='"+usuario_id+"',`TIMECREATE`='"+timecreate+"', TIEMPOTOTALOUT='"+tiempoMinimo+"' ,`OBSERVACION`='"+obs+"' WHERE `IDTRAMITE` ='"+idTramite+"' ";
		return template.update(sql);
	}
	/* insertar detalle */
	public int saveBodyControl(int idTramite,int almacen_id,String serie,int folio, int numart_id, String numart, int cantidad, String color, int class_id ,String class_clave,int numprecio, 
			 double precio, double tiempo, double subtotal , String tipoDescuento, double porcDescuento, double descuento, double precioNeto, double iva, double montoIVA, double precioFinal, String timecreate  ){
		String sql = "INSERT INTO `detcontrol`(`IDTRAMITE`, `ALMACEN_ID`, `SERIE`, `FOLIO`, `ARTICULO_ID`, `NUMART`, `CANTIDAD`, `COLOR`, `CLASIFICACION_ID`, `CLAVE_CLASS`, `NUMPRECIO`, `PRECIO`, `TIEMPOTOTAL`, `SUBTOTAL`, `TIPODESCUENTO`, `PORCDESCUENTO`, `DESCUENTO`, `PRECIONETO`, `IVA`, `MONTOIVA`, `PRECIOFINAL`, `TIMECREATE`) VALUES "
				+ "    ('"+idTramite+"','"+almacen_id+"','"+serie+"','"+folio+"','"+numart_id+"','"+numart+"','"+cantidad+"','"+color+"','"+class_id+"','"+class_clave+"','"+numprecio+"','"+precio+"','"+tiempo+"','"+subtotal+"','"+tipoDescuento+"','"+porcDescuento+"','"+descuento+"','"+precioNeto+"','"+iva+"','"+montoIVA+"','"+precioFinal+"','"+timecreate+"')";
		System.out.println(sql);
		return template.update(sql);
	}
	/*Subtotal
	 * @param precio
	 * @param tiempo
	 * @param numMaletas
	 * */
	public double subtotal ( double precio,double tiempo ,int numMaletas ) {
		double result = 0;
		result =  precio * tiempo * numMaletas;
		return result;
	}
	
	public double descuento(double subtotal, double porcDescuento) {
		double result = 0;
		result =  (subtotal * porcDescuento) / 100;
		return result;
	}
	public double precioNeto(double subtotal, double descuento) {
		double result = 0;
		result =  (subtotal - descuento);
		return result;
	}
	public double montoIVA(double precioNeto, double iva) {
		double result = 0;
		result =  (precioNeto * iva) / 100;
		return result;
	}
	public double precioFinal(double precioNeto, double montoIVA ) {
		double result = 0;
		result =  (precioNeto - montoIVA);
		return result;
	}
	
	public List<Map<String, Object>> listatks( int empresa_id, int idtramite){
		String sql = "SELECT cl.*,cl.TIMECREATE AS TIMECREATE_HEAD,d.*,a.NOMART,c.CODIGO,c.NOMBRE,c.APELLIDOS,c.DIRECCION,c.TELEFONO,class.DESCRIPCION AS NOMBRECLASS  FROM `cabcontrol` cl \r\n" + 
				"INNER JOIN detcontrol d ON d.IDTRAMITE = cl.IDTRAMITE "
				+ "INNER JOIN maearticulo a ON d.ARTICULO_ID = a.ID \r\n" + 
				"INNER JOIN cliente c on cl.CLIENTE_ID = c.IDC \r\n" +
				"INNER JOIN config_clasificacion class on d.CLAVE_CLASS = class.CLAVE \r\n" +
				"WHERE   cl.IDTRAMITE = '"+idtramite+"' ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		return rows;
	}
	public List<Object>  listaCobrados(int almacen_id, int usuario_id){
		List<Object> lineas = new ArrayList<Object>();
		String sql = "SELECT cl.*,cl.TIMECREATE AS TIMECREATE_HEAD,d.*,a.NOMART,c.CODIGO,c.NOMBRE,c.APELLIDOS,c.DIRECCION,c.TELEFONO,class.DESCRIPCION AS NOMBRECLASS  \r\n" + 
				"FROM `cabcontrol` cl \r\n" + 
				"INNER JOIN detcontrol d ON d.IDTRAMITE = cl.IDTRAMITE INNER JOIN maearticulo a ON d.ARTICULO_ID = a.ID \r\n" + 
				"INNER JOIN cliente c on cl.CLIENTE_ID = c.IDC\r\n" + 
				"INNER JOIN config_clasificacion class on d.CLAVE_CLASS = class.CLAVE\r\n" + 
				"WHERE cl.ESTATUS_MOV='1' AND cl.USUARIO_IDOUT='"+usuario_id+"' AND cl.ALMACEN_ID='"+almacen_id+"'";
		List<Map<String, Object>> rows = template.queryForList(sql);
		
		DecimalFormat df = new DecimalFormat("#.00");
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			
			mapa.put("indice", (i+1));			
			mapa.put("numart", row.get("NUMART"));
			mapa.put("nombreart", row.get("NOMART"));
			mapa.put("tamanio", row.get("NOMBRECLASS"));			
			mapa.put("color", row.get("COLOR"));
			mapa.put("cantidad", row.get("CANTIDAD"));			
			mapa.put("precio", "$ "+row.get("PRECIO"));
			mapa.put("tiempototal", "$ "+ row.get("TIEMPOTOTAL"));
			 
			double preciofinal = Double.parseDouble(df.format(row.get("PRECIOFINAL")));
			
			mapa.put("preciofinal", "$ "+ preciofinal );
			lineas.add(mapa);
		}
		
		return lineas;
	}
	
	public  double totalCobroTiket(int almacen_id, int usuario_id) {
		double total = 0;
		String sql = "SELECT SUM(d.PRECIOFINAL) AS TOTALCOBRO\r\n" + 
				"				FROM `cabcontrol` cl\r\n" + 
				"				INNER JOIN detcontrol d ON d.IDTRAMITE = cl.IDTRAMITE                \r\n" + 
				"				WHERE cl.ESTATUS_MOV='1' AND cl.USUARIO_IDOUT='"+usuario_id+"' AND cl.ALMACEN_ID='"+almacen_id+"'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.get(0) == null) {
			total = 0;
		} else {
			total = Double.parseDouble(certs.get(0));			
		} 
		return total;
	}
	/*
	 * @param idtramite
	 * */
	public  double totalTiket(int idtramite) {
		double total = 0;
		String sql = "SELECT SUM(PRECIOFINAL) AS PRECIOFINAL  FROM `cabcontrol` cl \r\n" + 
				"INNER JOIN detcontrol d ON d.IDTRAMITE = cl.IDTRAMITE \r\n" + 			
				"WHERE cl.IDTRAMITE ="+idtramite+"  ";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.get(0) == null) {
			total = 0;
		} else {
			total = Double.parseDouble(certs.get(0));			
		} 
		return total;
	}
	/* corroborar tiempo y actualizar detalle */
	public int actualizarTiempo(int empresa_id,int idtramite, int usuario_id) throws ParseException {
		int update = 0;
		
		Date date = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeNow = formateador.format(date);
		SimpleDateFormat formateador2 = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = formateador2.format(date);
		SimpleDateFormat formateador3 = new SimpleDateFormat("hh:mm:ss");
		String hora = formateador3.format(date);
		System.out.println(timeNow +" "+ fecha+" "+hora );
		
		 List<Map<String, Object>> rows = listatks(empresa_id, idtramite);	
		 System.out.println(rows);
		 if(rows.size() > 0 ) {
			 String timeCreate = rows.get(0).get("TIMECREATE_HEAD").toString();
			 System.out.println(" timeCreate " +timeCreate+" timeNow: "+ timeNow );
			 Date fechaInicio =  formateador.parse(timeCreate);  // Date
			 Date fechaFinalizo = formateador.parse(timeNow);  //Date
			 long horasFechas =(long)((fechaInicio.getTime()- fechaFinalizo.getTime())/3600000);
			 System.out.println(" horasFechas "+ horasFechas);
			   long diferenciaMils = fechaFinalizo.getTime() - fechaInicio.getTime();
			   //obtenemos los segundos
			   long segundos = diferenciaMils / 1000;			 
			   //obtenemos las horas
			   long horas = segundos / 3600;			 
			   //restamos las horas para continuar con minutos
			   segundos -= horas*3600;			 
			   //igual que el paso anterior
			   long minutos = segundos /60;
			   segundos -= minutos*60;			   
			   System.out.println(" horas "+ horas +" min"+ minutos+ " seg "+ segundos );
			 double tiempoTotal = Double.parseDouble(horas+"."+minutos);
			 // actualizar cabecera 
			 updateHeaderOut( idtramite, fecha, hora, tiempoTotal, usuario_id);
			 for (Map<?, ?> row : rows) {
				 Map<String, Object> mapa = new HashMap<String, Object>();
				int idd =  Integer.parseInt(row.get("IDD").toString());
				int idtramite_d =  Integer.parseInt(row.get("IDTRAMITE").toString());
				int cantidaMaletas = Integer.parseInt(row.get("CANTIDAD").toString());
				double precio = Double.parseDouble(row.get("PRECIO").toString());				
				double subtotal = subtotal(precio, tiempoTotal, cantidaMaletas);
				String tipoDescuento = "";
				double porcDescuento = 0;
				double descuento = descuento(subtotal, porcDescuento);
				double precioNeto = precioNeto(subtotal, descuento);
				double iva = 0;
				double montoIVA = montoIVA(precioNeto, iva);
				double precioFinal = precioFinal(precioNeto, montoIVA);
				//actualizar detalle
				updateBodyOut( idd, idtramite_d, tiempoTotal , subtotal, tipoDescuento, porcDescuento, descuento, precioNeto, iva, montoIVA, precioFinal );
			 }
			 
		 }
		return update;
	}
	public int updateHeaderOut(int idtramite, String fecha, String hora,double tiempoTotal, int usuario_id) {
		String sql = "UPDATE cabcontrol SET FECHAOUT = '"+fecha+"', HORAOUT='"+hora+"', TIEMPOTOTALOUT='"+tiempoTotal+"', USUARIO_IDOUT = '"+usuario_id+"'  WHERE IDTRAMITE='"+idtramite+"' ";
		System.out.println(sql);
		return template.update(sql);
	}
	
	public int updateBodyOut(int idd, int idtramite, double tiempoTotal , double subtotal, String tipoDescuento, double porcDescuento, double descuento, double precioNeto, double iva, double montoIva, double precioFinal ) {
		String sql = "UPDATE detcontrol SET TIEMPOTOTAL = '"+tiempoTotal+"', SUBTOTAL='"+subtotal+"', TIPODESCUENTO='"+tipoDescuento+"', PORCDESCUENTO = '"+porcDescuento+"' , 	DESCUENTO = '"+descuento+"', "
				+ " PRECIONETO='"+precioNeto+"' , IVA='"+iva+"' , MONTOIVA='"+montoIva+"', PRECIOFINAL='"+precioFinal+"'  "
				+ "WHERE IDTRAMITE='"+idtramite+"' AND IDD='"+idd+"' ";
		System.out.println(sql);
		return template.update(sql);
	}
	
	/*
	 * @param idtramite
	 * @param formaPago
	 * @param timeout
	 * @param usuario_id
	 * */
	public int updateEquipajeOut(int idtramite, String formaPago, String timeOut, int usuario_id) {
		String sql = "UPDATE cabcontrol SET FORMAPAGO = '"+formaPago+"', USUARIO_IDOUT='"+usuario_id+"', TIMEOUT = '"+timeOut+"', ESTATUS_MOV='1' WHERE IDTRAMITE='"+idtramite+"' ";
		System.out.println(sql);
		return template.update(sql);
	}
	/*
	 * @param idtramite
	 * @param formaPago
	 * @param totalPayup
	 * @param montoPago
	 * @param cambio
	 * @param timeOut
	 * @param  usuario_id*/
	public int insertControlpago(int idtramite, String formaPago, double totalPayup, double montoPago, double cambio, String timeOut, int usuario_id ) {
		String sql = "INSERT INTO `control_pago`(`IDTRAMITE`, `FORMAPAGO`, `TOTAL`, `PAGO`, `CAMBIO`, `TIMECREATE`, `USUARIO_ID`) VALUES"
				+ " ('"+idtramite+"','"+formaPago+"','"+totalPayup+"','"+montoPago+"','"+cambio+"','"+timeOut+"','"+usuario_id+"');";
		System.out.println(sql);
		return template.update(sql);
	}
	
	
}
