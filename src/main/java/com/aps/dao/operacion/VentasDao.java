package com.aps.dao.operacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.operacion.Ventas;

public class VentasDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Ventas v) {
		String sql = "INSERT INTO MOVVENTAS (NUMART_ID, NUMALM_ID, SUMQTY, SUMAVENT, UNIDAD, DIA, MESS, ANIO, ANIOMES, PERIODODIA,SEMANA, INVOICEACCOUNT, TIPO_CLIENTE, NUMAGT, PRECIOUNITARIO, DESCUENTO_PORCENT, DESCUENTO, PRECIONETO, IEPS, PRECIOBRUTO, IVA, PRECIOFINAL, FACTURA, TIMENOW, TIPOMOV, STATUS) VALUES";
		sql += "(" + v.getArticulo() + "," + v.getAlmacen() + "," + v.getSUMQTY() + ","
				+ v.getSUMAVENT() + ",'"
				+ v.getUnidad() + "','" + v.getDIA() + "','" + v.getMESS() + "','" + v.getANIO() + "','"
				+ v.getANIOMES() + "','" + v.getPERIODODIA() + "','"+v.getSEMANA()+"','" + v.getINVOICEACCOUNT() + "','"
				+ v.getTIPO_CLIENTE() + "','" + v.getNUMAGT() + "'," + v.getPRECIOUNITARIO() + ","
				+ v.getDESCUENTO_PORCENT() + "," + v.getDESCUENTO() + "," + v.getPRECIONETO() + "," + v.getIEPS() + ","
				+ v.getPRECIOBRUTO() + "," + v.getIVA() + "," + v.getPRECIOFINAL() + ",'" + v.getFACTURA() + "','"
				+ v.getTIMENOW() + "','" + v.getTIPOMOV() + "'," + v.getEstatus() + ")";
		System.out.println(sql);
		return template.update(sql);
	}
	public List<Object> ventasbyalmacen_fechas(String almacen, String finicio, String ffin) {
		//System.out.println(almacen+":"+finicio+":"+ffin);
		List<Object> deptos = new ArrayList<Object>();
		String sql = "SELECT v.*,a.NUMART,a.NOMART,al.NOMBRE,al.CLAVE FROM `MOVVENTAS` v inner join maearticulo a on(v.NUMART_ID=a.ID) INNER JOIN ALMACEN al on(v.NUMALM_ID=al.ID) AND al.CLAVE='"+almacen+"' WHERE v.PERIODODIA BETWEEN '"+finicio+"' AND '"+ffin+"'";
		System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("codalmacen", row.get("CLAVE"));
			mapa.put("nomalmacen", row.get("NOMBRE"));
			mapa.put("numart", row.get("NUMART"));
			mapa.put("descripcion", row.get("NOMART"));			
			mapa.put("tipomov", row.get("TIPOMOV"));			
			mapa.put("venta", row.get("SUMQTY"));
			mapa.put("ventaValor", row.get("SUMAVENT"));
			mapa.put("unidad", row.get("UNIDAD"));
			mapa.put("periododia", row.get("PERIODODIA"));
			mapa.put("semana", row.get("SEMANA"));
			mapa.put("cliente", row.get("INVOICEACCOUNT"));
			mapa.put("canalcliente", row.get("TIPO_CLIENTE"));
			mapa.put("precio", row.get("PRECIOUNITARIO"));
			mapa.put("pdescuento", row.get("DESCUENTO_PORCENT"));
			mapa.put("descuento", row.get("DESCUENTO"));
			mapa.put("precioneto", row.get("PRECIONETO"));
			mapa.put("ieps", row.get("IEPS"));
			mapa.put("preciobruto", row.get("PRECIOBRUTO"));
			mapa.put("iva", row.get("IVA"));
			mapa.put("preciofinal", row.get("PRECIOFINAL"));
			mapa.put("factura", row.get("FACTURA"));
			deptos.add(mapa);
		}
		return deptos;
	}
	/**
	 * 
	 * tipo de periodo MES | DIA
	 * @return
	 */
	public String gettipoperiodo() {
		String sql = "SELECT VALOR FROM CONFIG WHERE PARAMETRO = 'TIPOPERIODO'";		
		  List<String> certs = template.queryForList(sql, String.class); 
		    if (certs.isEmpty()) {
		    	return null;
		    } else {
		    	return certs.get(0);
		    }
	}
	/**
	 * NUMERO DE PERIODOS PARA CALCULO
	 * @return
	 */
	public int getnumperiodos(String parametro) {
		String sql = "SELECT VALOR FROM CONFIG WHERE PARAMETRO = '"+parametro+"'";		
		  List<String> certs = template.queryForList(sql, String.class); 
		    if (certs.isEmpty()) {
		    	return 0;
		    } else {
		    	return Integer.parseInt(certs.get(0));
		    }
	}
	
	/**
	 * lista de Periodos Ventas 
	 * 	 
	 */
	public List<Object> getlistperiodosvtas( String numalm, String tipoperiodo) {
		String sql_limit = "";
		String sql_numalm = "";
		String sql_agrupado = "";
		String sqltipoperiodo ="";
		String almacen = "";
		List<Object> almacenes = new ArrayList<Object>();
		
		int numperiodos = getnumperiodos("NUMPERIODOSGENERAL");
		if( numperiodos > 0){
            sql_limit =" LIMIT "+numperiodos;
        }
		// tipo de periodos de forma general o por almacen o punto de venta
        if(numalm.equals("GENERAL")||numalm.equals("RESURTIDO")){
            sql_numalm = "";
            sql_agrupado = "";
            
        }else{
            sql_numalm = " WHERE A.CLAVE = '"+numalm+"' ";
            sql_agrupado = " , A.CLAVE AS ALMACEN, A.NOMBRE AS NOMBRE";
            
        }
        /* selecccion de lista de periodos por diaa ..! */
        if(tipoperiodo.equals("DIA")) {
        	sqltipoperiodo ="DISTINCT(v.PERIODODIA) as PERIODO ";
        }else {
        	/* selecccion de lista de periodos aniomes */
        	sqltipoperiodo = "DISTINCT(v.ANIOMES) as PERIODO ";
        }
		String sql="SELECT "+sqltipoperiodo+sql_agrupado+" FROM MOVVENTAS v inner join ALMACEN A ON (v.NUMALM_ID=A.ID)"+sql_numalm+"ORDER BY PERIODO DESC "+sql_limit;
		//System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
		int i = 1;
		 for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			 if(numalm.equals("GENERAL")||numalm.equals("RESURTIDO")){
				 almacen = numalm;
			 }else {
				 almacen = String.valueOf(row.get("NOMBRE"));
			 }
			 		mapa.put("DT_RowId","almid_"+numalm+"_"+i);
			 		mapa.put("indice", i);
			 		mapa.put("nombre", numalm);
			 		mapa.put("descripcion", almacen);
			 		mapa.put("periodo", row.get("PERIODO"));
			 		almacenes.add(mapa);
			 		i++;
		       }
		 return almacenes;
	}
	/**
	 * @param numalm
	 * @return
	 */
	public List<Object> getlistperiodosselect( String numalm) {
		List<Object> almacenes = new ArrayList<Object>();
		String strnumalm = "";
		if(numalm.equals("GENERAL")||numalm.equals("RESURTIDO")) {
			strnumalm = ",cp.NUMALM AS ALMACEN";
		}else {
			strnumalm = ",(SELECT NOMBRE FROM ALMACEN A WHERE A.CLAVE=cp.NUMALM) AS ALMACEN";
		}
		String sql = "SELECT cp.*"+strnumalm+" FROM CONFIG_PERIODOS cp  WHERE cp.NUMALM='"+numalm+"' ORDER BY PERIODO DESC";
		//System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
		int i = 1;
		for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			       mapa.put("DT_RowId","almselectid_"+numalm+"_"+i);
			 		mapa.put("indice", i);			 		
			 		mapa.put("nombre", row.get("ALMACEN"));
			 		mapa.put("periodo", row.get("PERIODO"));
			 		almacenes.add(mapa);
			 		i++;
		       }
		 return almacenes;
	}
	/**
	 * @param almacen
	 * @param periodo
	 * @return
	 */
	public boolean searchPeriodo(String almacen,String periodo) {
		String sql = "SELECT PERIODO FROM CONFIG_PERIODOS WHERE PERIODO = '"+periodo+"' AND NUMALM='"+almacen+"'";		
		  List<String> certs = template.queryForList(sql, String.class); 
		    if (certs.isEmpty()) {
		    	return false;
		    } else {
		    	return true;
		    }
	}
	/**
	 * @param almacen
	 * @return
	 */
	public int periodosSelected(String almacen) {
		String sql = "SELECT COUNT(PERIODO) AS PERIODO FROM `CONFIG_PERIODOS` WHERE NUMALM='"+almacen+"' ";		
		  List<String> certs = template.queryForList(sql, String.class); 
		    if (certs.isEmpty()) {
		    	return 0;
		    } else {
		    	return Integer.parseInt(certs.get(0));
		    }
	}
	
	/**
	 * @param almacen
	 * @param periodo
	 * @return
	 */
	public boolean addPeriodo(String almacen,String periodo) {
		String sql = "INSERT INTO CONFIG_PERIODOS(PERIODO,NUMALM) VALUES ";
		sql += "('"+periodo+"','"+almacen+"')";
		return template.update(sql)>0;
	}
	
	/**
	 * @param almacen
	 * @param periodo
	 * @return
	 */
	public boolean deletePeriodo(String almacen,String periodo) {
		String sql = "DELETE FROM CONFIG_PERIODOS WHERE NUMALM='"+almacen+"' AND PERIODO ='"+periodo+"'";
		return template.update(sql)>0;
	}
	
}
