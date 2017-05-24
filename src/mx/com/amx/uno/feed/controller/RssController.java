package mx.com.amx.uno.feed.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import mx.com.amx.uno.feed.dao.IRssDAO;
import mx.com.amx.uno.feed.dto.NoticiaFeedDTO;
import mx.com.amx.uno.feed.dto.NoticiaRssDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("rssController")
public class RssController {
	
	private static Logger logger=Logger.getLogger(RssController.class);
	private IRssDAO rssDaoImpl;
	
	@RequestMapping(value={"getNotesByCategory"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaRssDTO> getNotasByCategoria(@RequestParam("idCategory") String idCategory, @RequestParam("numNotes") String numNotes, 
			@RequestParam("date") String date, HttpServletResponse response) throws Exception{
		logger.info("getNotesByCategory [Controller]");
		logger.debug("idCategory: "+idCategory);
		logger.debug("numNotes: "+numNotes);
		logger.debug("date: "+date);
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaRssDTO> list=null;
		try {
			
			int numNotas=(numNotes == null || numNotes.equals(""))?Integer.parseInt("10"):Integer.parseInt(numNotes);
			list= rssDaoImpl.getNotesByCategory(idCategory, numNotas, date);
		} catch (Exception e) {
			logger.error("Error getNotesByCategory [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;			
			throw new Exception(e.getMessage());
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotesBySection"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaRssDTO> getNotesBySection(@RequestParam("idSection") String idSection, @RequestParam("numNotes") String numNotes, 
			@RequestParam("date") String date, HttpServletResponse response) throws Exception{
		logger.info("getNotesBySection [Controller]");
		logger.debug("idSection: "+idSection);
		logger.debug("numNotes: "+numNotes);
		logger.debug("date: "+date);
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaRssDTO> list=null;
		try {
			
			int numNotas=(numNotes == null || numNotes.equals(""))?Integer.parseInt("10"):Integer.parseInt(numNotes);
			list= rssDaoImpl.getNotesBySection(idSection, numNotas, date);
		} catch (Exception e) {
			logger.error("Error getNotesBySection [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;			
			throw new Exception(e.getMessage());
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotesBySectionType"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaRssDTO> getNotesBySectionType(@RequestParam("idTypeSection") String idTypeSection, @RequestParam("numNotes") String numNotes, 
			@RequestParam("date") String date, HttpServletResponse response) throws Exception{
		logger.info("getNotesBySectionType [Controller]");
		logger.debug("idTypeSection: "+idTypeSection);
		logger.debug("numNotes: "+numNotes);
		logger.debug("date: "+date);
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaRssDTO> list=null;
		try {
			
			int numNotas=(numNotes == null || numNotes.equals(""))?Integer.parseInt("10"):Integer.parseInt(numNotes);
			list= rssDaoImpl.getNotesBySectionType(idTypeSection, numNotas, date);
		} catch (Exception e) {
			logger.error("Error getNotesBySectionType [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;			
			throw new Exception(e.getMessage());
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotesByViralesNoTeLoPierdas"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaRssDTO> getNotesByViralesNoTeLoPierdas(@RequestParam("viralNoTeLoPierdas") String viralNoTeLoPierdas, @RequestParam("numNotes") String numNotes, 
			@RequestParam("date") String date, HttpServletResponse response) throws Exception{
		logger.info("getNotesByViralesNoTeLoPierdas [Controller]");
		logger.debug("viralNoTeLoPierdas: "+viralNoTeLoPierdas);
		logger.debug("numNotes: "+numNotes);
		logger.debug("date: "+date);
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaRssDTO> list=null;
		try {
			
			int numNotas=(numNotes == null || numNotes.equals(""))?Integer.parseInt("10"):Integer.parseInt(numNotes);
			list= rssDaoImpl.getNotesByViralesNoTeLoPierdas(viralNoTeLoPierdas, numNotas, date);
		} catch (Exception e) {
			logger.error("Error getNotesByViralesNoTeLoPierdas [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;			
			throw new Exception(e.getMessage());
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotesMagazine"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaRssDTO> getNotesMagazine(@RequestParam("idMagazine") String idMagazine, HttpServletResponse response) throws Exception{
		logger.info("getNotesMagazine [Controller]");
		logger.debug("idMagazine: "+idMagazine);
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaRssDTO> list=null;
		try {
			list= rssDaoImpl.getNotesMagazine(idMagazine);
		} catch (Exception e) {
			logger.error("Error getNotesMagazine [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;			
			throw new Exception(e.getMessage());
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	@RequestMapping(value={"getNotesHome"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
	@ResponseBody
	List<NoticiaRssDTO> getNotesHome(@RequestBody String idMagazineHome, HttpServletResponse response) throws Exception{
		logger.info("getNotesHome [Controller]");
		logger.debug("idMagazineHome: "+idMagazineHome);
		String msj="OK";
		String codigo="0";
		String causa_error="";
		int status_peticion=HttpServletResponse.SC_OK;
		List<NoticiaRssDTO> list=null;
		try {
			list= rssDaoImpl.getNotesHome(idMagazineHome);
		} catch (Exception e) {
			logger.error("Error getNotesHome [Controller]: ",e);
			codigo="-1";
			msj=e.getMessage();
			causa_error=e.toString();
			status_peticion=HttpServletResponse.SC_INTERNAL_SERVER_ERROR;			
			throw new Exception(e.getMessage());
		}
		response.setHeader("codigo", codigo);
		response.setHeader("mensaje", msj);
		response.setHeader("causa_error", causa_error);
		response.setStatus(status_peticion);
		return list;
	}
	
	
	/**
	 * @return the rssDaoImpl
	 */
	public IRssDAO getRssDaoImpl() {
		return rssDaoImpl;
	}
	/**
	 * @param rssDaoImpl the rssDaoImpl to set
	 */
	@Autowired
	public void setRssDaoImpl(IRssDAO rssDaoImpl) {
		this.rssDaoImpl = rssDaoImpl;
	}
	
	
	
	
}
