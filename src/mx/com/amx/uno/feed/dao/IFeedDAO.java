package mx.com.amx.uno.feed.dao;

import java.util.List;

import mx.com.amx.uno.feed.dto.CategoriaDTO;
import mx.com.amx.uno.feed.dto.NoticiaFeedDTO;
import mx.com.amx.uno.feed.dto.SeccionDTO;
import mx.com.amx.uno.feed.dto.TipoSeccionDTO;
import mx.com.amx.uno.feed.exception.DAOException;

public interface IFeedDAO {
	/*
	 * Método que devuelve una lista de Categorias activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: Lista de Categorias activas
	 * 
	*/
	
	List<CategoriaDTO> getCategorias() throws DAOException;
	
	/*Método que devuelve una lista de Secciones activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: List<CategoriaDTO>
	 * @throws: DAOException
	*/
	List<SeccionDTO> getSecciones() throws DAOException;
	
	/*
	 * Método que devuelve una lista de Tipo de Secciones activas
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @return: List<TipoSeccionDTO>
	 * @throws: DAOException
	*/
	List<TipoSeccionDTO> getTipoSecciones() throws DAOException;
	
	/*
	 * Método que devuelve una lista de Noticias filtradas por Categoria
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @param: String idCategoria
	 * @param: String numNotas
	 * @param: String fecha
	 * @return: List<NoticiaFeedDTO>
	 * @throws: DAOException
	*/
	List<NoticiaFeedDTO> getNotasByCategoria(String idCategoria, String numNotas, String fecha) throws DAOException;
	/*
	 * Método que devuelve una lista de Noticias filtradas por Seccion
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @param: String idSeccion
	 * @param: String numNotas
	 * @param: String fecha
	 * @return: List<NoticiaFeedDTO>
	 * @throws: DAOException
	*/
	List<NoticiaFeedDTO> getNotasBySeccion(String idSeccion, String numNotas, String fecha) throws DAOException;
	/*
	 * Método que devuelve una lista de Noticias filtradas por Tipo de Seccion
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @param: String idTipoSeccion
	 * @param: String numNotas
	 * @param: String fecha
	 * @return: List<NoticiaFeedDTO>
	 * @throws: DAOException
	*/
	List<NoticiaFeedDTO> getNotasByTipoSeccion(String idTipoSeccion, String numNotas, String fecha) throws DAOException;
	/*
	 * Método que devuelve una lista de Noticias filtradas por el id del magazine
	 *@author: Jesús Vicuña 
	 * @version: 1.0
	 * @param: String idMgazine
	 * @return: List<NoticiaFeedDTO>
	 * @throws: DAOException
	*/
	List<NoticiaFeedDTO> getNotasByIdMagazine(String idMagazine) throws DAOException;
	
}
