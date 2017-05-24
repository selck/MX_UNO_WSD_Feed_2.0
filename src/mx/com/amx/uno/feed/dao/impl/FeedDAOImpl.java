package mx.com.amx.uno.feed.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import mx.com.amx.uno.feed.dao.IFeedDAO;
import mx.com.amx.uno.feed.dto.CategoriaDTO;
import mx.com.amx.uno.feed.dto.NoticiaFeedDTO;
import mx.com.amx.uno.feed.dto.SeccionDTO;
import mx.com.amx.uno.feed.dto.TipoSeccionDTO;
import mx.com.amx.uno.feed.exception.DAOException;

@Component
@Qualifier("feedDAO")
public class FeedDAO implements IFeedDAO {
	
	private Logger logger=Logger.getLogger(FeedDAO.class);
	private JdbcTemplate jdbc;
	
	@Override
	public List<CategoriaDTO> getCategorias() throws DAOException {
		StringBuffer sql=new StringBuffer();
		List<CategoriaDTO> listCategorias=new ArrayList<CategoriaDTO>();
		try {
			sql.append(" SELECT ");
			sql.append(" FC_ID_CATEGORIA as idCategoria, ");
			sql.append(" FC_FRIENDLY_URL as friendlyURL ");
			sql.append(" from WPDB2INS.UNO_MX_C_CATEGORIA ");
			
			listCategorias=jdbc.query(sql.toString(), new BeanPropertyRowMapper<CategoriaDTO>(CategoriaDTO.class));
			
		} catch (Exception e) {
			logger.error("Error getCategorias [DAO]: ",e);
		}
		
		return listCategorias;
	}

	@Override
	public List<SeccionDTO> getSecciones() throws DAOException {
		StringBuffer sql=new StringBuffer();
		List<SeccionDTO> listSecciones=new ArrayList<SeccionDTO>();
		try {
			sql.append(" SELECT ");
			sql.append(" FC_ID_SECCION as idSeccion, ");
			sql.append(" FC_FRIENDLY_URL as friendlyURL ");
			sql.append(" from WPDB2INS.UNO_MX_C_SECCION ");
			
			listSecciones=jdbc.query(sql.toString(), new BeanPropertyRowMapper<SeccionDTO>(SeccionDTO.class));
			
		} catch (Exception e) {
			logger.error("Error getSecciones [DAO]: ",e);
		}
		
		return listSecciones;
	}

	@Override
	public List<TipoSeccionDTO> getTipoSecciones() throws DAOException {
		StringBuffer sql=new StringBuffer();
		List<TipoSeccionDTO> listTipoSecciones=new ArrayList<TipoSeccionDTO>();
		try {
			sql.append(" SELECT ");
			sql.append(" FC_ID_TIPO_SECCION as idTipoSeccion, ");
			sql.append(" FC_FRIENDLY_URL as friendlyURL ");
			sql.append(" from WPDB2INS.UNO_MX_C_TIPO_SECCION ");
			
			listTipoSecciones=jdbc.query(sql.toString(), new BeanPropertyRowMapper<TipoSeccionDTO>(TipoSeccionDTO.class));
			
		} catch (Exception e) {
			logger.error("Error getTipoSecciones [DAO]: ",e);
		}
		
		return listTipoSecciones;
	}

	@Override
	public List<NoticiaFeedDTO> getNotasByCategoria(String idCategoria, String numNotas, String fecha) throws DAOException {
		StringBuffer sql=new StringBuffer();
		List<NoticiaFeedDTO> listNoticias=new ArrayList<NoticiaFeedDTO>();
		List<Object> listObjects = new ArrayList<Object>();
		try {
			
			sql.append(" SELECT"); 
			sql.append(" N.FC_ID_CONTENIDO as idContenido, "); 
			sql.append(" N.FC_ID_CATEGORIA as idCategoria, "); 
			sql.append(" N.FC_NOMBRE as nombre, "); 
			sql.append(" N.FC_TITULO as titulo, "); 
			sql.append(" N.FC_DESCRIPCION as descripcion, "); 
			sql.append(" N.FC_ESCRIBIO as escribio, "); 
			sql.append(" N.FC_LUGAR as lugar, "); 
			sql.append(" N.FC_FUENTE as fuente, "); 
			sql.append(" N.FC_ID_TIPO_NOTA as idTipoNota,"); 
			sql.append(" N.FC_IMAGEN_PRINCIPAL as imagenPrincipal, "); 
			sql.append(" N.FC_IMAGEN_PIE as imagenPie, "); 
			sql.append(" N.FC_VIDEO_YOUTUBE as videoYouTube,"); 
			sql.append(" N.FC_ID_VIDEO_CONTENT as idVideoContentOoyala, "); 
			sql.append(" N.FC_ID_VIDEO_PLAYER as idVideoPlayerOoyala,"); 
			sql.append(" N.CL_GALERIA_IMAGENES as galeriaImagenes, "); 
			sql.append(" N.FC_INFOGRAFIA as infografia,"); 
			sql.append(" N.CL_RTF_CONTENIDO as rtfContenido, "); 
			sql.append(" TO_CHAR(N.FD_FECHA_PUBLICACION, 'MM/DD/YY HH:MI AM') AS fechaPublicacion, "); 
			sql.append(" N.FD_FECHA_MODIFICACION as fechaModificacion,"); 
			sql.append(" N.FC_TAGS as tags, "); 
			sql.append(" N.FC_KEYWORDS as keywords, "); 
			sql.append(" CASE   "); 
			sql.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN    "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" ELSE   "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" END as linkNota,   "); 
			sql.append(" COALESCE(N.FC_SOURCE_VIDEO,'')  AS sourceVideo,");
			sql.append(" COALESCE(N.FC_ALTERNATE_TEXT, '') AS alternateTextVideo,");
			sql.append(" COALESCE(N.FC_DURATION, '') AS durationVideo,");
			sql.append(" COALESCE(N.FC_FILE_SIZE, '') AS sizeVideo,"); 	
			sql.append(" C.FC_DESCRIPCION AS descripcionCategoria, "); 
			sql.append(" C.FC_DESCRIPCION AS tituloFeed "); 
			sql.append(" FROM "); 
			sql.append(" WPDB2INS.UNO_MX_N_NOTA N, "); 
			sql.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, "); 
			sql.append(" WPDB2INS.UNO_MX_C_SECCION S, "); 
			sql.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS"); 
			sql.append(" WHERE N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA AND C.FC_ID_SECCION = S.FC_ID_SECCION "); 
			sql.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION "); 
			sql.append(" AND N.FC_ID_TIPO_NOTA IN ('imagen', 'video') ");
			sql.append(" AND N.FC_VIDEO_YOUTUBE ='' ");
			sql.append(" AND N.FC_ID_CATEGORIA = ? ");
			listObjects.add(idCategoria);
			if(fecha!= null && !fecha.trim().equals("")){
				sql.append(" AND N.FD_FECHA_PUBLICACION < ? ");
				listObjects.add(fecha);
			}
			sql.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC");
			sql.append(" FETCH FIRST "+numNotas+" ROWS ONLY OPTIMIZE FOR "+numNotas+" ROWS ");
			
			listNoticias=jdbc.query(sql.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaFeedDTO>(NoticiaFeedDTO.class));
			
		} catch (Exception e) {
			logger.error("Error getNotasByCategoria [DAO]: ",e);
		}
		
		return listNoticias;
	}

	@Override
	public List<NoticiaFeedDTO> getNotasBySeccion(String idSeccion, String numNotas, String fecha) throws DAOException {
		StringBuffer sql=new StringBuffer();
		List<NoticiaFeedDTO> listNoticias=new ArrayList<NoticiaFeedDTO>();
		List<Object> listObjects = new ArrayList<Object>();
		try {
			
			sql.append(" SELECT"); 
			sql.append(" N.FC_ID_CONTENIDO as idContenido, "); 
			sql.append(" N.FC_ID_CATEGORIA as idCategoria, "); 
			sql.append(" N.FC_NOMBRE as nombre, "); 
			sql.append(" N.FC_TITULO as titulo, "); 
			sql.append(" N.FC_DESCRIPCION as descripcion, "); 
			sql.append(" N.FC_ESCRIBIO as escribio, "); 
			sql.append(" N.FC_LUGAR as lugar, "); 
			sql.append(" N.FC_FUENTE as fuente, "); 
			sql.append(" N.FC_ID_TIPO_NOTA as idTipoNota,"); 
			sql.append(" N.FC_IMAGEN_PRINCIPAL as imagenPrincipal, "); 
			sql.append(" N.FC_IMAGEN_PIE as imagenPie, "); 
			sql.append(" N.FC_VIDEO_YOUTUBE as videoYouTube,"); 
			sql.append(" N.FC_ID_VIDEO_CONTENT as idVideoContentOoyala, "); 
			sql.append(" N.FC_ID_VIDEO_PLAYER as idVideoPlayerOoyala,"); 
			sql.append(" N.CL_GALERIA_IMAGENES as galeriaImagenes, "); 
			sql.append(" N.FC_INFOGRAFIA as infografia,"); 
			sql.append(" N.CL_RTF_CONTENIDO as rtfContenido, "); 
			sql.append(" TO_CHAR(N.FD_FECHA_PUBLICACION, 'MM/DD/YY HH:MI AM') AS fechaPublicacion, "); 
			sql.append(" N.FD_FECHA_MODIFICACION as fechaModificacion,"); 
			sql.append(" N.FC_TAGS as tags, "); 
			sql.append(" N.FC_KEYWORDS as keywords, "); 
			sql.append(" CASE   "); 
			sql.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN    "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" ELSE   "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" END as linkNota,   "); 
			sql.append(" COALESCE(N.FC_SOURCE_VIDEO,'')  AS sourceVideo,");
			sql.append(" COALESCE(N.FC_ALTERNATE_TEXT, '') AS alternateTextVideo,");
			sql.append(" COALESCE(N.FC_DURATION, '') AS durationVideo,");
			sql.append(" COALESCE(N.FC_FILE_SIZE, '') AS sizeVideo,");
			sql.append(" C.FC_DESCRIPCION AS descripcionCategoria, "); 
			sql.append(" S.FC_DESCRIPCION AS tituloFeed "); 
			sql.append(" FROM "); 
			sql.append(" WPDB2INS.UNO_MX_N_NOTA N, "); 
			sql.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, "); 
			sql.append(" WPDB2INS.UNO_MX_C_SECCION S, "); 
			sql.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS"); 
			sql.append(" WHERE N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA AND C.FC_ID_SECCION = S.FC_ID_SECCION "); 
			sql.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION "); 
			sql.append(" AND N.FC_ID_TIPO_NOTA IN ('imagen', 'video') ");
			sql.append(" AND N.FC_VIDEO_YOUTUBE ='' ");
			sql.append(" AND S.FC_ID_SECCION= ? "); 
			listObjects.add(idSeccion);
			if(fecha!= null && !fecha.trim().equals("")){
				sql.append(" AND N.FD_FECHA_PUBLICACION < ? ");
				listObjects.add(fecha);
			}
			sql.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC"); 
			sql.append(" FETCH FIRST "+numNotas+" ROWS ONLY OPTIMIZE FOR "+numNotas+" ROWS ");
			
			listNoticias=jdbc.query(sql.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaFeedDTO>(NoticiaFeedDTO.class));
			
		} catch (Exception e) {
			logger.error("Error getNotasBySeccion [DAO]: ",e);
		}
		
		return listNoticias;
	}

	@Override
	public List<NoticiaFeedDTO> getNotasByTipoSeccion(String idTipoSeccion, String numNotas, String fecha) throws DAOException {
		StringBuffer sql=new StringBuffer();
		List<NoticiaFeedDTO> listNoticias=new ArrayList<NoticiaFeedDTO>();
		List<Object> listObjects = new ArrayList<Object>();
		try {
			
			sql.append(" SELECT"); 
			sql.append(" N.FC_ID_CONTENIDO as idContenido, "); 
			sql.append(" N.FC_ID_CATEGORIA as idCategoria, "); 
			sql.append(" N.FC_NOMBRE as nombre, "); 
			sql.append(" N.FC_TITULO as titulo, "); 
			sql.append(" N.FC_DESCRIPCION as descripcion, "); 
			sql.append(" N.FC_ESCRIBIO as escribio, "); 
			sql.append(" N.FC_LUGAR as lugar, "); 
			sql.append(" N.FC_FUENTE as fuente, "); 
			sql.append(" N.FC_ID_TIPO_NOTA as idTipoNota,"); 
			sql.append(" N.FC_IMAGEN_PRINCIPAL as imagenPrincipal, "); 
			sql.append(" N.FC_IMAGEN_PIE as imagenPie, "); 
			sql.append(" N.FC_VIDEO_YOUTUBE as videoYouTube,"); 
			sql.append(" N.FC_ID_VIDEO_CONTENT as idVideoContentOoyala, "); 
			sql.append(" N.FC_ID_VIDEO_PLAYER as idVideoPlayerOoyala,"); 
			sql.append(" N.CL_GALERIA_IMAGENES as galeriaImagenes, "); 
			sql.append(" N.FC_INFOGRAFIA as infografia,"); 
			sql.append(" N.CL_RTF_CONTENIDO as rtfContenido, "); 
			sql.append(" TO_CHAR(N.FD_FECHA_PUBLICACION, 'MM/DD/YY HH:MI AM') AS fechaPublicacion, "); 
			sql.append(" N.FD_FECHA_MODIFICACION as fechaModificacion,"); 
			sql.append(" N.FC_TAGS as tags, "); 
			sql.append(" N.FC_KEYWORDS as keywords, "); 
			sql.append(" CASE   "); 
			sql.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN    "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" ELSE   "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" END as linkNota,   "); 
			sql.append(" COALESCE(N.FC_SOURCE_VIDEO,'')  AS sourceVideo,");
			sql.append(" COALESCE(N.FC_ALTERNATE_TEXT, '') AS alternateTextVideo,");
			sql.append(" COALESCE(N.FC_DURATION, '') AS durationVideo,");
			sql.append(" COALESCE(N.FC_FILE_SIZE, '') AS sizeVideo,");
			sql.append(" S.FC_DESCRIPCION AS descripcionCategoria, "); 
			sql.append(" TS.FC_DESCRIPCION AS tituloFeed "); 
			sql.append(" FROM "); 
			sql.append(" WPDB2INS.UNO_MX_N_NOTA N, "); 
			sql.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, "); 
			sql.append(" WPDB2INS.UNO_MX_C_SECCION S, "); 
			sql.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS"); 
			sql.append(" WHERE N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA AND C.FC_ID_SECCION = S.FC_ID_SECCION "); 
			sql.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION "); 
			sql.append(" AND N.FC_ID_TIPO_NOTA IN ('imagen', 'video') ");
			sql.append(" AND N.FC_VIDEO_YOUTUBE ='' ");
			sql.append(" AND S.FC_ID_TIPO_SECCION= ?"); 
			listObjects.add(idTipoSeccion);
			if(fecha!= null && !fecha.trim().equals("")){
				sql.append(" AND N.FD_FECHA_PUBLICACION < ? ");
				listObjects.add(fecha);
			}
			sql.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC"); 
			sql.append(" FETCH FIRST "+numNotas+" ROWS ONLY OPTIMIZE FOR "+numNotas+" ROWS ");
			
			listNoticias=jdbc.query(sql.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaFeedDTO>(NoticiaFeedDTO.class));
			
		} catch (Exception e) {
			logger.error("Error getNotasByTipoSeccion [DAO]: ",e);
		}
		
		return listNoticias;
	}
	
	@Override
	public List<NoticiaFeedDTO> getNotasByIdMagazine(String idMagazine) throws DAOException {
		StringBuffer sql=new StringBuffer();
		List<NoticiaFeedDTO> listNoticias=new ArrayList<NoticiaFeedDTO>();
		
		try {
			
			sql.append(" SELECT"); 
			sql.append(" N.FC_ID_CONTENIDO as idContenido, "); 
			sql.append(" N.FC_ID_CATEGORIA as idCategoria, "); 
			sql.append(" N.FC_NOMBRE as nombre, "); 
			sql.append(" N.FC_TITULO as titulo, "); 
			sql.append(" N.FC_DESCRIPCION as descripcion, "); 
			sql.append(" N.FC_ESCRIBIO as escribio, "); 
			sql.append(" N.FC_LUGAR as lugar, "); 
			sql.append(" N.FC_FUENTE as fuente, "); 
			sql.append(" N.FC_ID_TIPO_NOTA as idTipoNota,"); 
			sql.append(" N.FC_IMAGEN_PRINCIPAL as imagenPrincipal, "); 
			sql.append(" N.FC_IMAGEN_PIE as imagenPie, "); 
			sql.append(" N.FC_VIDEO_YOUTUBE as videoYouTube,"); 
			sql.append(" N.FC_ID_VIDEO_CONTENT as idVideoContentOoyala, "); 
			sql.append(" N.FC_ID_VIDEO_PLAYER as idVideoPlayerOoyala,"); 
			sql.append(" N.CL_GALERIA_IMAGENES as galeriaImagenes, "); 
			sql.append(" N.FC_INFOGRAFIA as infografia,"); 
			sql.append(" N.CL_RTF_CONTENIDO as rtfContenido, "); 
			sql.append(" TO_CHAR(N.FD_FECHA_PUBLICACION, 'MM/DD/YY HH:MI AM') AS fechaPublicacion, "); 
			sql.append(" N.FD_FECHA_MODIFICACION as fechaModificacion,"); 
			sql.append(" N.FC_TAGS as tags, "); 
			sql.append(" N.FC_KEYWORDS as keywords, "); 
			sql.append(" CASE   "); 
			sql.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN    "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" ELSE   "); 
			sql.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'') ||'/')   "); 
			sql.append(" END as linkNota,   "); 
			sql.append(" COALESCE(N.FC_SOURCE_VIDEO,'')  AS sourceVideo,");
			sql.append(" COALESCE(N.FC_ALTERNATE_TEXT, '') AS alternateTextVideo,");
			sql.append(" COALESCE(N.FC_DURATION, '') AS durationVideo,");
			sql.append(" COALESCE(N.FC_FILE_SIZE, '') AS sizeVideo,");
			sql.append(" C.FC_DESCRIPCION AS descripcionCategoria, "); 
			sql.append(" 'Magazine' AS tituloFeed "); 
			sql.append(" FROM "); 
			sql.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, "); 
			sql.append(" WPDB2INS.UNO_MX_C_SECCION S, "); 
			sql.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS, ");
			sql.append(" WPDB2INS.UNO_MX_N_NOTA N ");
			sql.append(" LEFT OUTER JOIN WPDB2INS.UNO_MX_I_NOTA_MAGAZINE INM ON N.FC_ID_CONTENIDO = INM.FC_ID_CONTENIDO ");
			sql.append(" WHERE FC_ID_MAGAZINE= ? ");
			sql.append(" AND N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA AND C.FC_ID_SECCION = S.FC_ID_SECCION "); 
			sql.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION "); 
			sql.append(" ORDER BY INM.FI_ORDEN ASC "); 
			
			Object [] arrayObject={idMagazine};
			
			int [] argTypes={Types.VARCHAR};
			
			listNoticias=jdbc.query(sql.toString(),arrayObject,argTypes, new BeanPropertyRowMapper<NoticiaFeedDTO>(NoticiaFeedDTO.class));
			
		} catch (Exception e) {
			logger.error("Error getNotasByTipoSeccion [DAO]: ",e);
		}
		
		return listNoticias;
	}
	
	/*Get and set*/
	/**
	 * @return the jdbc
	 */
	public JdbcTemplate getJdbc() {
		return jdbc;
	}

	/**
	 * @param jdbc the jdbc to set
	 */
	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
}
