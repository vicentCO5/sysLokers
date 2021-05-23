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

import com.aps.beans.operacion.Traspaso;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.operacion.TraspasoDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller 
public class TraspasoController {
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	TraspasoDao traspasodao;
	
	/////////import traspasos/////////////
	@RequestMapping("/getimportartraspaso")
	public ModelAndView viewimportartraspaso() {
		return new ModelAndView("traspaso/formtraspaso");
	}
	
	/* importar traspaso */
	@RequestMapping(value = "/doUploadtraspaso", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileHandler(@RequestParam("filetraspaso") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		List<Traspaso> traspasos = new ArrayList<Traspaso>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		 System.out.println("holaaaaaaaaaa");
		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				int codEmpresa = 1;
				String numeroFolio = "";
				String codAlmacenOrigen = "";
				String codAlmacenDestino = "";			
				String codSku = "";
				String cantidad ="";
				String unidad = "";
				String fechaMovimiento = "";
				String timeCreate = "";
				String estatus = "";
				String codAgente = null;
								
				for (int row = 1; row < sheet.getRows(); row++) {
					
					numeroFolio      = sheet.getCell(0, row).getContents().toString();
					codAlmacenOrigen = sheet.getCell(1, row).getContents().toString();
					codAlmacenDestino = sheet.getCell(2, row).getContents().toString();
					codSku            = sheet.getCell(3, row).getContents().toString();
					cantidad          = (sheet.getCell(4, row).getContents().toString()).equals("") ? "0":sheet.getCell(4, row).getContents().toString();
					unidad            = sheet.getCell(5, row).getContents().toString();
					fechaMovimiento   = sheet.getCell(6, row).getContents().toString(); // format yyyy-mm-dd h:m:s
					estatus =  sheet.getCell(7, row).getContents().toString();
											
					// Obtener el tiempo actual de movimiento 
					Date date = new Date();
					SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					timeCreate = formateador.format(date);		            		            
					
					int idalmacenOrigen = daoalmacen.findByid(codAlmacenOrigen);
					int idalmacenDestino = daoalmacen.findByid(codAlmacenDestino);
					
					if (idalmacenOrigen != 0 || idalmacenDestino != 0 ) {						
						int idarticulo = daoarticulo.getArticulobyId(codSku);
						
						if (idarticulo != 0) {
							Traspaso traspaso = new Traspaso( codEmpresa, numeroFolio, idalmacenOrigen, idalmacenDestino, idarticulo, Double.parseDouble(cantidad),unidad,
									fechaMovimiento,timeCreate,estatus,codAgente );								
							
							traspasos.addAll(Arrays.asList(traspaso));

						} else {
							registrosError.add("Fila:" + (row + 1) + "- No existe Articulo [" + codSku + "] \n");
						}

					} else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Almacen [" + idalmacenDestino + "] \n");
					}
				}
				
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				}else {
					for(int i=0;i<traspasos.size();i++) {
						saveTraspaso(traspasos.get(i));
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
	 * @ guardar traspaso */
	@Transactional
	void saveTraspaso(Traspaso t) {
		traspasodao.save(t);
	}
	
	/*
	 * Mostrar la lista de tras
	 * listaTraspasos */
	@RequestMapping("/listtraspasos")
	public ModelAndView viewTabletraspasos() {
		return new ModelAndView("traspaso/listtraspasos");
	}
	/*
	 * Datos de tranferencia */
	@RequestMapping("/getdatatraspaso")
	public @ResponseBody
	Map <String, Object> listTraspasos(){
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = traspasodao.listaTraspasos();
		mapa.put("data", list);
		return mapa;		 
	}
	/*
	 *Eliminar de traspasos 
	 **/
	@RequestMapping(value ="/deletetraspasos", produces = "application/json; charset=UTF-8")
	public @ResponseBody  Map<String, Object> deleteall() {
		Map <String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError =  new ArrayList<String>();
				
		try {
			// agregar empresa 
			int empresa_id = 1;
			traspasodao.deleteTraspaso(empresa_id);
			mapa.put("exito", "true");
			mapa.put("mensaje", "Eliminado correctamente");
		}catch(Exception e) {
			// TODO Auto-generated catch block
			mapa.put("exito", "false");
			registrosError.add( e.getMessage());
			mapa.put("error", registrosError);
		}
		return mapa;
	}
		
	
	
	
}
