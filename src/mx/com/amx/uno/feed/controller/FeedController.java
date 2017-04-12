package mx.com.amx.uno.feed.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.uno.feed.dao.IFeedDAO;
import mx.com.amx.uno.feed.dto.CategoriaDTO;
import mx.com.amx.uno.feed.dto.NoticiaFeedDTO;
import mx.com.amx.uno.feed.dto.SeccionDTO;
import mx.com.amx.uno.feed.dto.TipoSeccionDTO;

@Component
@RequestMapping("feedController")
public class FeedController {
	private Logger logger=Logger.getLogger(FeedController.class);
	private IFeedDAO feedDAO;
	
	@RequestMapping(value={"getCategorias"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=application/json"})
	@ResponseBody
	List<CategoriaDTO> getCategorias( HttpServletResponse response){
		logger.info("getCategorias [Controller]");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<CategoriaDTO> list=null;
		try {
			list= feedDAO.getCategorias();
		} catch (Exception e) {
			logger.error("Error getCategorias [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getSecciones"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=application/json"})
	@ResponseBody
	List<SeccionDTO> getSecciones( HttpServletResponse response){
		logger.info("getSecciones [Controller]");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<SeccionDTO> list=null;
		try {
			list= feedDAO.getSecciones();
		} catch (Exception e) {
			logger.error("Error getSecciones [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getTipoSecciones"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=application/json"})
	@ResponseBody
	List<TipoSeccionDTO> getTipoSecciones( HttpServletResponse response){
		logger.info("getTipoSecciones [Controller]");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<TipoSeccionDTO> list=null;
		try {
			list= feedDAO.getTipoSecciones();
		} catch (Exception e) {
			logger.error("Error getTipoSecciones [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotasByCategoria"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaFeedDTO> getNotasByCategoria(@RequestParam("idCategoria") String idCategoria, @RequestParam("numNotas") String numNotas, 
			@RequestParam("fecha") String fecha, HttpServletResponse response){
		logger.info("getNotasByCategoria [Controller]");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaFeedDTO> list=null;
		try {
			list= feedDAO.getNotasByCategoria(idCategoria,numNotas,fecha);
		} catch (Exception e) {
			logger.error("Error getNotasByCategoria [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotasBySeccion"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaFeedDTO> getNotasBySeccion(@RequestParam("idSeccion")  String idSeccion, @RequestParam("numNotas") String numNotas, 
			@RequestParam("fecha") String fecha, HttpServletResponse response){
		logger.info("getNotasBySeccion [Controller]");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaFeedDTO> list=null;
		try {
			list= feedDAO.getNotasBySeccion(idSeccion,numNotas, fecha);
		} catch (Exception e) {
			logger.error("Error getNotasBySeccion [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotasByTipoSeccion"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaFeedDTO> getNotasByTipoSeccion(@RequestParam("idTipoSeccion") String idTipoSeccion, @RequestParam("numNotas") String numNotas, 
			@RequestParam("fecha") String fecha, HttpServletResponse response){
		logger.info("getNotasByTipoSeccion [Controller]");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaFeedDTO> list=null;
		try {
			list= feedDAO.getNotasByTipoSeccion(idTipoSeccion,numNotas,fecha);
		} catch (Exception e) {
			logger.error("Error getNotasByTipoSeccion [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotasByIdMagazine"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaFeedDTO> getNotasByIdMagazine(@RequestBody String idMagazine, HttpServletResponse response){
		logger.info("getNotasByIdMagazine [Controller]");
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaFeedDTO> list=null;
		try {
			list= feedDAO.getNotasByIdMagazine(idMagazine);
		} catch (Exception e) {
			logger.error("Error getNotasByIdMagazine [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	/**
	 * @return the feedDAO
	 */
	public IFeedDAO getFeedDAO() {
		return feedDAO;
	}
	/**
	 * @param feedDAO the feedDAO to set
	 */
	@Autowired
	public void setFeedDAO(IFeedDAO feedDAO) {
		this.feedDAO = feedDAO;
	}
	
	
}
