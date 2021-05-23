package com.aps.controllers.catalogos;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.aps.beans.catalogos.Proveedor;
import com.aps.dao.catalogos.AlmacenDao;
import com.aps.dao.catalogos.ProempresaDao;
import com.aps.dao.catalogos.ProveedorDao;

import jxl.Sheet;
import jxl.Workbook;

@Controller
public class ProveedorController {
	@Autowired
	ProveedorDao daoprov;
	@Autowired
	AlmacenDao daoalmacen;
	@Autowired 
	ProempresaDao daoproemp;
	
	@RequestMapping(value = "/showproveedores", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> viewDeptos() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoprov.getProveedores();
		mapa.put("data", list);
		return mapa;
	}
	@RequestMapping(value = "/deleteallProveedores", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> deleteallProveedores() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		ArrayList<String> registrosError = new ArrayList<String>();
		try {
			    daoprov.deleteallproempresa();
				daoprov.deleteAllProveedores();
				mapa.put("exito", "true");
				mapa.put("mensaje", "Eliminados correctamente");
			
		} catch (Exception e) {
			mapa.put("exito", "false");
			registrosError.add(e.getMessage());
		}
		return mapa;
	}
	@RequestMapping("/deleteProveedor")
	public @ResponseBody String deleteprov(@RequestParam("id") String id,
			@RequestParam("idempresa") int idempresa) {
		String cad = "";
		try {
			daoprov.deleteproempresa(id, idempresa);
			daoprov.delete(id);
			cad = "Eliminado correctamente";	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cad = e.getMessage();

		}
		return cad;
	}
	@Transactional
	boolean saveProveedor(Proveedor d) {
		if(daoprov.save(d)) {
		return true;
		}else {
			return false;
		}
	}
	@Transactional
	void updateProveedor(Proveedor d) {
		daoprov.update(d);
	}
	/*
	 * METODOS para importacion de Departamentos xls
	 */
	@RequestMapping("/getimportprov")
	public ModelAndView viewimportDepto() {
		return new ModelAndView("proveedor/importarproveedor");
	}
	@RequestMapping(value = "/doUploadProveedor", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> uploadFileProveedor(@RequestParam("fileproveedor") MultipartFile file) {
		ArrayList<String> registrosError = new ArrayList<String>();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Proveedor> provinsert = new ArrayList<Proveedor>();
		List<Proveedor> provupdate = new ArrayList<Proveedor>();
		if (!file.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheet(0);
				String claveEmpresa = "";
				String codigo = "";
				String nombre = "";			
				String rfc = "";
				String direccion = "";
				String telefono = "";
				String celular = "";
				String email = "";
				String tipo = "";
				String pais = "";
				String estatus= "";
				String descuento = "";
				for (int row = 1; row < sheet.getRows(); row++) {
					claveEmpresa = "";
					codigo = "";
					nombre = "";
					rfc = "";
					direccion = "";
					telefono = "";
					celular = "";
					email = "";
					tipo = "";
					pais = "";
					estatus= "";
					claveEmpresa = sheet.getCell(3, row).getContents().toString();
					codigo = sheet.getCell(0, row).getContents().toString();
					nombre = sheet.getCell(1, row).getContents().toString();
					rfc = sheet.getCell(2, row).getContents().toString();
					direccion = sheet.getCell(4, row).getContents().toString();
					telefono = sheet.getCell(5, row).getContents().toString();
					celular = sheet.getCell(6, row).getContents().toString();
					email = sheet.getCell(7, row).getContents().toString();
					tipo = sheet.getCell(9, row).getContents().toString();
					pais = sheet.getCell(10, row).getContents().toString();
					estatus = sheet.getCell(11, row).getContents().toString();
					descuento = sheet.getCell(12, row).getContents().toString();
					int idempresa = daoalmacen.getidempresa(claveEmpresa);
					if (idempresa != 0) {
						int idprov = daoprov.getProvbyempresaid_clave(codigo, idempresa);
						if (idprov == 0) { // no existe y guardamos para insert							
							Proveedor proveedor = new Proveedor(codigo,nombre,nombre,direccion,"","","",pais,telefono,"",rfc,email,"",tipo,estatus,celular,idempresa,Double.parseDouble(descuento));
							provinsert.addAll(Arrays.asList(proveedor));
							
						}else {
							Proveedor proveedor = new Proveedor(codigo,nombre,nombre,direccion,"","","",pais,telefono,"",rfc,email,"",tipo,estatus,celular,idempresa,Double.parseDouble(descuento));
							provupdate.addAll(Arrays.asList(proveedor));
						}
					}else {
						registrosError.add("Fila:" + (row + 1) + "- No existe Empresa [" + claveEmpresa + "] \n");
					}
				}
				if (registrosError.size() > 0) {
					mapa.put("exito", "false");
					mapa.put("error", registrosError);
				} else {
					for (int i = 0; i < provinsert.size(); i++) {
						if(daoprov.save(provinsert.get(i))) {
							int idprov = daoprov.getProveedorbyclave(provinsert.get(i).getClave());
							daoproemp.save(idprov,provinsert.get(i).getEmpresa_id(),provinsert.get(i).getDescuento());

						}
					}
					for (int j = 0; j < provupdate.size(); j++) {
						updateProveedor(provupdate.get(j));
					}
					mapa.put("exito", "true");
					mapa.put("error", registrosError);
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

}
