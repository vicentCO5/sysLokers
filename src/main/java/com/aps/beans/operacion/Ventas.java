package com.aps.beans.operacion;

public class Ventas {
	private int articulo;
	private int almacen;
	private double SUMQTY;
	// private double SUMDEVQTY;
	private double SUMAVENT;
	//private double SUMADEV;
	//private double SUMVENT2;
	//private double SUMDEV2;
	private String unidad;
	private String DIA;
	private String MESS;
	private String ANIO;
	private String ANIOMES;
	private String PERIODODIA;
	private int SEMANA;
	private String INVOICEACCOUNT;
	private String TIPO_CLIENTE;
	private String NUMAGT;
	private double PRECIOUNITARIO;
	private double DESCUENTO_PORCENT;
	private double DESCUENTO;
	private double PRECIONETO;
	private double IEPS;
	private double PRECIOBRUTO;
	private double IVA;
	private double PRECIOFINAL;
	private String FACTURA;
	private String TIMENOW;
	private int TIPOMOV;
	private Integer estatus;
	/**
	 * @return the articulo
	 */
	public int getArticulo() {
		return articulo;
	}
	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(int articulo) {
		this.articulo = articulo;
	}
	/**
	 * @return the almacen
	 */
	public int getAlmacen() {
		return almacen;
	}
	/**
	 * @param almacen the almacen to set
	 */
	public void setAlmacen(int almacen) {
		this.almacen = almacen;
	}
	/**
	 * @return the sUMQTY
	 */
	public double getSUMQTY() {
		return SUMQTY;
	}
	/**
	 * @param sUMQTY the sUMQTY to set
	 */
	public void setSUMQTY(double sUMQTY) {
		SUMQTY = sUMQTY;
	}
	/**
	 * @return the sUMDEVQTY
	 */
	/*public double getSUMDEVQTY() {
		return SUMDEVQTY;
	} */
	/**
	 * @param sUMDEVQTY the sUMDEVQTY to set
	 */
	/* public void setSUMDEVQTY(double sUMDEVQTY) {
		SUMDEVQTY = sUMDEVQTY;
	}*/
	/**
	 * @return the sUMAVENT
	 */
	public double getSUMAVENT() {
		return SUMAVENT;
	}
	/**
	 * @param sUMAVENT the sUMAVENT to set
	 */
	public void setSUMAVENT(double sUMAVENT) {
		SUMAVENT = sUMAVENT;
	}
	/**
	 * @return the sUMADEV
	 */
	/* public double getSUMADEV() {
		return SUMADEV;
	} */
	/**
	 * @param sUMADEV the sUMADEV to set
	 */
	/* public void setSUMADEV(double sUMADEV) {
		SUMADEV = sUMADEV;
	} */
	/**
	 * @return the sUMVENT2
	 */
	/* public double getSUMVENT2() {
		return SUMVENT2;
	} */
	/**
	 * @param sUMVENT2 the sUMVENT2 to set
	 */
	/* public void setSUMVENT2(double sUMVENT2) {
		SUMVENT2 = sUMVENT2;
	} */
	/**
	 * @return the sUMDEV2
	 */
	/* public double getSUMDEV2() {
		return SUMDEV2;
	} */
	/**
	 * @param sUMDEV2 the sUMDEV2 to set
	 */
	/* public void setSUMDEV2(double sUMDEV2) {
		SUMDEV2 = sUMDEV2;
	} */
	/**
	 * @return the unidad
	 */
	public String getUnidad() {
		return unidad;
	}
	/**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	/**
	 * @return the dIA
	 */
	public String getDIA() {
		return DIA;
	}
	/**
	 * @param dIA the dIA to set
	 */
	public void setDIA(String dIA) {
		DIA = dIA;
	}
	/**
	 * @return the mESS
	 */
	public String getMESS() {
		return MESS;
	}
	/**
	 * @param mESS the mESS to set
	 */
	public void setMESS(String mESS) {
		MESS = mESS;
	}
	/**
	 * @return the aNIO
	 */
	public String getANIO() {
		return ANIO;
	}
	/**
	 * @param aNIO the aNIO to set
	 */
	public void setANIO(String aNIO) {
		ANIO = aNIO;
	}
	/**
	 * @return the aNIOMES
	 */
	public String getANIOMES() {
		return ANIOMES;
	}
	/**
	 * @param aNIOMES the aNIOMES to set
	 */
	public void setANIOMES(String aNIOMES) {
		ANIOMES = aNIOMES;
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
	 * @return the SEMANA
	 */
	public int getSEMANA() {
		return SEMANA;
	}
	/**
	 * @param pERIODODIA the pERIODODIA to set
	 */
	public void setSEMANA(String sEMANA) {
		PERIODODIA = sEMANA;
	}
	
	/**
	 * @return the iNVOICEACCOUNT
	 */
	public String getINVOICEACCOUNT() {
		return INVOICEACCOUNT;
	}
	/**
	 * @param iNVOICEACCOUNT the iNVOICEACCOUNT to set
	 */
	public void setINVOICEACCOUNT(String iNVOICEACCOUNT) {
		INVOICEACCOUNT = iNVOICEACCOUNT;
	}
	/**
	 * @return the tIPO_CLIENTE
	 */
	public String getTIPO_CLIENTE() {
		return TIPO_CLIENTE;
	}
	/**
	 * @param tIPO_CLIENTE the tIPO_CLIENTE to set
	 */
	public void setTIPO_CLIENTE(String tIPO_CLIENTE) {
		TIPO_CLIENTE = tIPO_CLIENTE;
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
	 * @return the pRECIOUNITARIO
	 */
	public double getPRECIOUNITARIO() {
		return PRECIOUNITARIO;
	}
	/**
	 * @param pRECIOUNITARIO the pRECIOUNITARIO to set
	 */
	public void setPRECIOUNITARIO(double pRECIOUNITARIO) {
		PRECIOUNITARIO = pRECIOUNITARIO;
	}
	/**
	 * @return the dESCUENTO_PORCENT
	 */
	public double getDESCUENTO_PORCENT() {
		return DESCUENTO_PORCENT;
	}
	/**
	 * @param dESCUENTO_PORCENT the dESCUENTO_PORCENT to set
	 */
	public void setDESCUENTO_PORCENT(double dESCUENTO_PORCENT) {
		DESCUENTO_PORCENT = dESCUENTO_PORCENT;
	}
	/**
	 * @return the dESCUENTO
	 */
	public double getDESCUENTO() {
		return DESCUENTO;
	}
	/**
	 * @param dESCUENTO the dESCUENTO to set
	 */
	public void setDESCUENTO(double dESCUENTO) {
		DESCUENTO = dESCUENTO;
	}
	/**
	 * @return the pRECIONETO
	 */
	public double getPRECIONETO() {
		return PRECIONETO;
	}
	/**
	 * @param pRECIONETO the pRECIONETO to set
	 */
	public void setPRECIONETO(double pRECIONETO) {
		PRECIONETO = pRECIONETO;
	}
	/**
	 * @return the iEPS
	 */
	public double getIEPS() {
		return IEPS;
	}
	/**
	 * @param iEPS the iEPS to set
	 */
	public void setIEPS(double iEPS) {
		IEPS = iEPS;
	}
	/**
	 * @return the pRECIOBRUTO
	 */
	public double getPRECIOBRUTO() {
		return PRECIOBRUTO;
	}
	/**
	 * @param pRECIOBRUTO the pRECIOBRUTO to set
	 */
	public void setPRECIOBRUTO(double pRECIOBRUTO) {
		PRECIOBRUTO = pRECIOBRUTO;
	}
	/**
	 * @return the iVA
	 */
	public double getIVA() {
		return IVA;
	}
	/**
	 * @param iVA the iVA to set
	 */
	public void setIVA(double iVA) {
		IVA = iVA;
	}
	/**
	 * @return the pRECIOFINAL
	 */
	public double getPRECIOFINAL() {
		return PRECIOFINAL;
	}
	/**
	 * @param pRECIOFINAL the pRECIOFINAL to set
	 */
	public void setPRECIOFINAL(double pRECIOFINAL) {
		PRECIOFINAL = pRECIOFINAL;
	}
	/**
	 * @return the fACTURA
	 */
	public String getFACTURA() {
		return FACTURA;
	}
	/**
	 * @param fACTURA the fACTURA to set
	 */
	public void setFACTURA(String fACTURA) {
		FACTURA = fACTURA;
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
	 * @return the estatus
	 */
	public Integer getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	/**
	 * 
	 */
	public Ventas() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param articulo
	 * @param almacen
	 * @param sUMQTY
	     * @param sUMDEVQTY
	 * @param sUMAVENT
		 * @param sUMADEV
		 * @param sUMVENT2
		 * @param sUMDEV2
	 * @param unidad
	 * @param dIA
	 * @param mESS
	 * @param aNIO
	 * @param aNIOMES
	 * @param pERIODODIA
	 * @param sEMANA
	 * @param iNVOICEACCOUNT
	 * @param tIPO_CLIENTE
	 * @param nUMAGT
	 * @param pRECIOUNITARIO
	 * @param dESCUENTO_PORCENT
	 * @param dESCUENTO
	 * @param pRECIONETO
	 * @param iEPS
	 * @param pRECIOBRUTO
	 * @param iVA
	 * @param pRECIOFINAL
	 * @param fACTURA
	 * @param tIMENOW
	 * @param tIPOMOV
	 * @param estatus
	 */
	public Ventas(int articulo, int almacen, double sUMQTY,  double sUMAVENT,
			String unidad, String dIA, String mESS, String aNIO, String aNIOMES,
			String pERIODODIA, int sEMANA, String iNVOICEACCOUNT, String tIPO_CLIENTE, String nUMAGT, double pRECIOUNITARIO,
			double dESCUENTO_PORCENT, double dESCUENTO, double pRECIONETO, double iEPS, double pRECIOBRUTO, double iVA,
			double pRECIOFINAL, String fACTURA, String tIMENOW, int tIPOMOV, Integer estatus) {
		super();
		this.articulo = articulo;
		this.almacen = almacen;
		SUMQTY = sUMQTY;
//		SUMDEVQTY = sUMDEVQTY;
		SUMAVENT = sUMAVENT;
//		SUMADEV = sUMADEV;
//		SUMVENT2 = sUMVENT2;
//		SUMDEV2 = sUMDEV2;
		this.unidad = unidad;
		DIA  = dIA;
		MESS = mESS;
		ANIO = aNIO;
		ANIOMES = aNIOMES;
		PERIODODIA = pERIODODIA;
		SEMANA = sEMANA;
		INVOICEACCOUNT = iNVOICEACCOUNT;
		TIPO_CLIENTE = tIPO_CLIENTE;
		NUMAGT = nUMAGT;
		PRECIOUNITARIO = pRECIOUNITARIO;
		DESCUENTO_PORCENT = dESCUENTO_PORCENT;
		DESCUENTO = dESCUENTO;
		PRECIONETO = pRECIONETO;
		IEPS = iEPS;
		PRECIOBRUTO = pRECIOBRUTO;
		IVA = iVA;
		PRECIOFINAL = pRECIOFINAL;
		FACTURA = fACTURA;
		TIMENOW = tIMENOW;
		TIPOMOV = tIPOMOV;
		this.estatus = estatus;
	}
	
}