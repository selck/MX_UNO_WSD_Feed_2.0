package mx.com.amx.uno.feed.dao;

import java.util.List;

import mx.com.amx.uno.feed.dto.NoticiaRssDTO;
import mx.com.amx.uno.feed.exception.DAOException;

public interface IRssDAO {
	
	/*
	 * Método que devuelve una lista de NoticiaRssDTO activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: Lista de Categorias activas
	 * 
	*/
	
	List<NoticiaRssDTO> getNotesByCategory(String idCategory, int numNotes, String date) throws DAOException;
	/*
	 * Método que devuelve una lista de NoticiaRssDTO activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: Lista de Categorias activas
	 * 
	*/
	
	List<NoticiaRssDTO> getNotesBySection(String idSection, int numNotes, String date) throws DAOException;
	
	/*
	 * Método que devuelve una lista de NoticiaRssDTO activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: Lista de Categorias activas
	 * 
	*/
	
	List<NoticiaRssDTO> getNotesBySectionType(String idTypeSection, int numNotes, String date ) throws DAOException;
	
	/*
	 * Método que devuelve una lista de NoticiaRssDTO activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: Lista de Categorias activas
	 * 
	*/
	
	List<NoticiaRssDTO> getNotesByViralesNoTeLoPierdas(String viralNoTeLoPierdas, int numNotes, String date) throws DAOException;
	
	/*
	 * Método que devuelve una lista de NoticiaRssDTO activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: Lista de Categorias activas
	 * 
	*/
	
	List<NoticiaRssDTO> getNotesMagazine(String idMagazine) throws DAOException;
	/*
	 * Método que devuelve una lista de NoticiaRssDTO activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: Lista de Categorias activas
	 * 
	*/
	
	List<NoticiaRssDTO> getNotesHome(String idMagazineHome) throws DAOException;
	
	
	
}
