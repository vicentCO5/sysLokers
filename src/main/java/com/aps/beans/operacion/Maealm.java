package com.aps.beans.operacion;

public class Maealm {

	private int NUMART;
	private int NUMALM;
	private double EXUALM;
	private String UNIART;
	private double CRPART;
	private double CPRART;
	private String NUMAGT;
	private String MESANIO;
	private String PERIODODIA;
	private String 	TIMENOW;
	private int TIPOMOV;
	/**
	 * @return the nUMART
	 */
	public int getNUMART() {
		return NUMART;
	}
	/**
	 * @param nUMART the nUMART to set
	 */
	public void setNUMART(int nUMART) {
		NUMART = nUMART;
	}
	/**
	 * @return the nUMALM
	 */
	public int getNUMALM() {
		return NUMALM;
	}
	/**
	 * @param nUMALM the nUMALM to set
	 */
	public void setNUMALM(int nUMALM) {
		NUMALM = nUMALM;
	}
	/**
	 * @return the eXUALM
	 */
	public double getEXUALM() {
		return EXUALM;
	}
	/**
	 * @param eXUALM the eXUALM to set
	 */
	public void setEXUALM(double eXUALM) {
		EXUALM = eXUALM;
	}
	/**
	 * @return the uNIART
	 */
	public String getUNIART() {
		return UNIART;
	}
	/**
	 * @param uNIART the uNIART to set
	 */
	public void setUNIART(String uNIART) {
		UNIART = uNIART;
	}
	/**
	 * @return the cRPART
	 */
	public double getCRPART() {
		return CRPART;
	}
	/**
	 * @param cRPART the cRPART to set
	 */
	public void setCRPART(double cRPART) {
		CRPART = cRPART;
	}
	/**
	 * @return the cPRART
	 */
	public double getCPRART() {
		return CPRART;
	}
	/**
	 * @param cPRART the cPRART to set
	 */
	public void setCPRART(double cPRART) {
		CPRART = cPRART;
	}
	/**
	 * @return the nUMAGT
	 */
	public String getNUMAGT() {
		return NUMAGT;
	}
	/**
	 * @param nUMAGT the nUMAGT to set
	 */
	public void setNUMAGT(String nUMAGT) {
		NUMAGT = nUMAGT;
	}
	/**
	 * @return the mESANIO
	 */
	public String getMESANIO() {
		return MESANIO;
	}
	/**
	 * @param mESANIO the mESANIO to set
	 */
	public void setMESANIO(String mESANIO) {
		MESANIO = mESANIO;
	}
	/**
	 * @return the pERIODODIA
	 */
	public String getPERIODODIA() {
		return PERIODODIA;
	}
	/**
	 * @param pERIODODIA the pERIODODIA to set
	 */
	public void setPERIODODIA(String pERIODODIA) {
		PERIODODIA = pERIODODIA;
	}
	/**
	 * @return the tIMENOW
	 */
	public String getTIMENOW() {
		return TIMENOW;
	}
	/**
	 * @param tIMENOW the tIMENOW to set
	 */
	public void setTIMENOW(String tIMENOW) {
		TIMENOW = tIMENOW;
	}
	/**
	 * @return the tIPOMOV
	 */
	public int getTIPOMOV() {
		return TIPOMOV;
	}
	/**
	 * @param tIPOMOV the tIPOMOV to set
	 */
	public void setTIPOMOV(int tIPOMOV) {
		TIPOMOV = tIPOMOV;
	}
	/**
	 * 
	 */
	public Maealm() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param nUMART
	 * @param nUMALM
	 * @param eXUALM
	 * @param uNIART
	 * @param cRPART
	 * @param cPRART
	 * @param nUMAGT
	 * @param mESANIO
	 * @param pERIODODIA
	 * @param tIMENOW
	 * @param tIPOMOV
	 */
	public Maealm(int nUMART, int nUMALM, double eXUALM, String uNIART, double cRPART, double cPRART, String nUMAGT,
			String mESANIO, String pERIODODIA, String tIMENOW, int tIPOMOV) {
		super();
		NUMART = nUMART;
		NUMALM = nUMALM;
		EXUALM = eXUALM;
		UNIART = uNIART;
		CRPART = cRPART;
		CPRART = cPRART;
		NUMAGT = nUMAGT;
		MESANIO = mESANIO;
		PERIODODIA = pERIODODIA;
		TIMENOW = tIMENOW;
		TIPOMOV = tIPOMOV;
	}
	
}
