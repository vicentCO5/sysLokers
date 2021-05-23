package com.aps.controllers.catalogos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aps.dao.catalogos.ArticuloDao;
import com.aps.dao.catalogos.ArtxempresaDao;
import com.aps.dao.catalogos.ClasificacionDao;
import com.aps.dao.catalogos.PrecioDao;

@Controller
public class PrecioController {
	@Autowired
	ArticuloDao daoarticulo;
	@Autowired
	ArtxempresaDao daoartxempresa;
	@Autowired
	ClasificacionDao daoclasificacion;
	@Autowired
	PrecioDao daoprecio;
	
	@RequestMapping("/listPrecios")
	public ModelAndView viewimportcatArticulos() {
		return new ModelAndView("precio/listaprecios");
	}
	
	@RequestMapping(value="/datalistprecios")
	public @ResponseBody
	Map<String,Object> viewprecios() {
		int empresa_id = 1;
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Object> list = daoprecio.getlistaArticuloPrecio(empresa_id );
		mapa.put("data",list);
		return mapa;
	}
	/*modificar precio */
	@RequestMapping("/changePrecio")
	@ResponseBody String changePrecio(@RequestParam("idchanged")int id,@RequestParam("skuchaged")String numart,@RequestParam("numprechaged")int numprecio,@RequestParam("newprice") double newpricex ){
		String mensaje = "";
		int usuario_id = 1;
		System.out.println(id + numart+ numprecio + newpricex);
   
		try {
			Date date = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeChange = formateador.format(date);
			// validar que la serie no este ocupada en algun movimiento
			 daoprecio.changedPrecio(id,numart,numprecio,newpricex, timeChange, usuario_id);
			 mensaje = "Precio Actualizado";			
			}catch(Exception e) {
				mensaje = e.getMessage() ;
			}
		return mensaje;
	}
	/* modificar estatus */
	@RequestMapping("/changedEstatusPrecio")
	@ResponseBody String changedEstatus(@RequestParam("idchanged")int id,@RequestParam("skuchaged")String numart,@RequestParam("numprechaged")int numprecio,@RequestParam("estatus") int status ){
		// data:{idchanged:idchanged,skuchaged:skuchaged,numprechaged:numprechaged,estatus:estatus },
		String mensaje = "";
		int usuario_id = 1;
		System.out.println(id + numart+ numprecio + status);
		try {
			Date date = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String timeChange = formateador.format(date);
			// validar que la serie no este ocupada en algun movimiento
			 daoprecio.changedEstatusPrecio(id,numart,numprecio,status, timeChange, usuario_id);
			 mensaje = "Estatus modificado";			
			}catch(Exception e) {
				mensaje = e.getMessage() ;
			}
		return mensaje;
	}
	
}
