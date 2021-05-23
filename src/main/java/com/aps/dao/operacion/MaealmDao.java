package com.aps.dao.operacion;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.operacion.Maealm;

public class MaealmDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Maealm m) {
		String sql = "INSERT INTO MAEALM(NUMART_ID, NUMALM_ID, EXUALM, UNIART, CRPART, CPRART, NUMAGT, MESANIO, PERIODODIA, TIMENOW, TIPOMOV) VALUES";
		sql += "(" + m.getNUMART() + "," + m.getNUMALM() + "," + m.getEXUALM() + ",'" + m.getUNIART() + "',"
				+ m.getCRPART() + "," + m.getCPRART() + ",'" + m.getNUMAGT() + "','" + m.getMESANIO() + "','"
				+ m.getPERIODODIA() + "','" + m.getTIMENOW() + "'," + m.getTIPOMOV() + ")";
		System.out.println(sql);
		return template.update(sql);
	}
}
