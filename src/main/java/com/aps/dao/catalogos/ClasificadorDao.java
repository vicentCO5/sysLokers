package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class ClasificadorDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public List<Object> getClasificadores() {
		List<Object> lineas = new ArrayList<Object>();
		String sql = "SELECT * FROM CLASIFICADOR ORDER BY NIVEL ASC  ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i+1));
			mapa.put("clave", row.get("CODIGO"));
			mapa.put("descripcion", row.get("DESCRIPCION"));
			mapa.put("nivel", row.get("NIVEL"));
			String inputlevel = "<div class='form-group has-info'><input type='text' id='level"+row.get("IDC")+"' data-id='level"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("NIVEL")+"' value='"+ row.get("NIVEL")+"' class='form-control input-sm updatelevel' maxlength='4' size='4' /></div>";
			mapa.put("nivel", inputlevel);
			String checkedForecast = "";
			String checkedForecastPV = "";
			String checkedReabasto = "";
			String checkedResurtido = "";
			String checkedExtraordinario = "";
			if(row.get("FORECAST").equals(true)) {
				 checkedForecast = "<button class='btn btn-success btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("FORECAST")+"'  data-modulo='FORECASTING' ><i class='fa fa-check-circle'></i></button>";
			}else
				checkedForecast = "<button class='btn btn-default btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("FORECASTING")+"'  data-modulo='FORECASTING' ><i class='fa fa-circle-o'></i></button>";
			if(row.get("FORECASTPV").equals(true)) {
				checkedForecastPV = "<button class='btn btn-success btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"'  data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("FORECASTPV")+"'  data-modulo='FORECASTPV' ><i class='fa fa-check-circle'></i></button>";
			}else
				checkedForecastPV = "<button class='btn btn-default btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("FORECASTPV")+"' data-modulo='FORECASTPV' ><i class='fa fa-circle-o'></i></button>";
			if(row.get("REABASTO").equals(true)) {
				checkedReabasto = "<button class='btn btn-success btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("REABASTO")+"' data-modulo='REABASTO' ><i class='fa fa-check-circle'></i></button>";
			}else
				checkedReabasto = "<button class='btn btn-default btn-xs updateclasificador'  id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("REABASTO")+"' data-modulo='REABASTO' ><i class='fa fa-circle-o'></i></button>";
			if(row.get("RESURTIDO").equals(true)) {
				checkedResurtido = "<button class='btn btn-success btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("RESURTIDO")+"' data-modulo='RESURTIDO' ><i class='fa fa-check-circle'></i></button>";
			}else
				checkedResurtido = "<button class='btn btn-default btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("RESURTIDO")+"'data-modulo='RESURTIDO' ><i class='fa fa-circle-o'></i></button>";
			if(row.get("EXTRAORDINARIO").equals(true)) {
				checkedExtraordinario = "<button class='btn btn-success btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("EXTRAORDINARIO")+"' data-modulo='EXTRAORDINARIO' ><i class='fa fa-check-circle'></i></button>";
			}else
				checkedExtraordinario = "<button class='btn btn-default btn-xs updateclasificador' id='id"+row.get("IDC")+"' data-id='id"+row.get("IDC")+"' data-clave='"+row.get("CODIGO")+"' data-value='"+ row.get("EXTRAORDINARIO")+"'  data-modulo='EXTRAORDINARIO' ><i class='fa fa-circle-o'></i></button>";
			
			mapa.put("forecast", checkedForecast);
			mapa.put("forecastpv", checkedForecastPV);
			mapa.put("reabasto", checkedReabasto);
			mapa.put("resurtido", checkedResurtido);
			mapa.put("extraordinario", checkedExtraordinario);
			lineas.add(mapa);
		}
		return lineas;
	}
	public int updatelevel(String clave,int newvalue) throws SQLException {
		String sql = "UPDATE CLASIFICADOR SET NIVEL="+newvalue+" WHERE CODIGO='" + clave + "'";
		return template.update(sql);
	}
	public int updateClasificador(String clave,boolean oldvalue, String modulo) throws SQLException {
		//System.out.println(clave+":"+oldvalue+":"+modulo);
		int value=0;
		if(oldvalue == true) {
			value=0;
		}else {
			value=1;
		}		
		
		String sql = "UPDATE CLASIFICADOR SET "+modulo+"="+value+" WHERE CODIGO='" + clave + "'";
		//System.out.println(sql);
		return template.update(sql);
	}

}
