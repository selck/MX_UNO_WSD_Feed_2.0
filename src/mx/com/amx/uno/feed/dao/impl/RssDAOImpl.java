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

import mx.com.amx.uno.feed.dao.IRssDAO;
import mx.com.amx.uno.feed.dto.NoticiaRssDTO;
import mx.com.amx.uno.feed.exception.DAOException;

@Component
@Qualifier("rssDaoImpl")
public class RssDAOImpl implements IRssDAO {
	
	private Logger logger=Logger.getLogger(RssDAOImpl.class);
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<NoticiaRssDTO> getNotesByCategory(String idCategory, int numNotes, String date)
			throws DAOException {
		
		List<NoticiaRssDTO> resultado = new ArrayList<NoticiaRssDTO>();
		List<Object> listObjects=new ArrayList<Object>();
		try {
			StringBuffer query=new StringBuffer();
			
			query.append(" SELECT");
			query.append(" N.FC_TITULO as titulo_nota,"); 
			query.append(" N.FC_DESCRIPCION as descripcion_nota,");
			query.append(" N.FD_FECHA_PUBLICACION as fecha_publicacion_nota,"); 
			query.append(" C.FC_DESCRIPCION AS descripcion_categoria, ");
			query.append(" C.FC_DESCRIPCION AS titulo_rss,");
			query.append(" CASE  ");
			query.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN");   
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" ELSE");  
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" END as url_nota,");
			query.append(" N.CL_RTF_CONTENIDO AS contenido_nota,");
			query.append(" COALESCE(N.FC_ESCRIBIO, '') AS autor_nota,");
			query.append(" N.FC_IMAGEN_PRINCIPAL AS imagen_nota,");
			query.append(" COALESCE(N.FC_IMAGEN_PIE, '') AS pie_imagen_nota,");
			query.append(" COALESCE(N.CL_GALERIA_IMAGENES, '') AS galeria_nota, ");
			query.append(" COALESCE(N.FC_VIDEO_YOUTUBE, '') AS id_video_youtube,"); 
			query.append(" COALESCE(N.FC_ID_VIDEO_CONTENT, '') AS id_video_ooyala,");
			query.append(" COALESCE(N.FC_ID_VIDEO_PLAYER, '') AS id_player_ooyala");
			query.append(" FROM WPDB2INS.UNO_MX_N_NOTA N,"); 
			query.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, ");
			query.append(" WPDB2INS.UNO_MX_C_SECCION S, ");
			query.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS");  
			query.append(" WHERE N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA"); 
			query.append(" AND C.FC_ID_SECCION = S.FC_ID_SECCION ");
			query.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION"); 
			query.append(" AND N.FC_ID_CATEGORIA = ? ");
			listObjects.add(idCategory);
			if(date!= null && !date.trim().equals("")){
				query.append(" AND N.FD_FECHA_PUBLICACION < ? ");
				listObjects.add(date);
			}
			query.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC ");
			query.append(" FETCH FIRST "+numNotes+" ROWS ONLY OPTIMIZE FOR "+numNotes+" ROWS"); 
			
			//int types[]={Types.VARCHAR};
			
			resultado = (ArrayList<NoticiaRssDTO>) this.jdbcTemplate.query(
					query.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaRssDTO>(NoticiaRssDTO.class) );
		} catch (Exception e) {
			logger.error("Error getNotesByCategory [DAO]: ",e);
			throw new DAOException(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public List<NoticiaRssDTO> getNotesBySection(String idSection, int numNotes, String date)
			throws DAOException {
		List<NoticiaRssDTO> resultado = new ArrayList<NoticiaRssDTO>();
		List<Object> listObjects=new ArrayList<Object>();
		try {
			StringBuffer query=new StringBuffer();
			
			query.append(" SELECT");
			query.append(" N.FC_TITULO as titulo_nota,"); 
			query.append(" N.FC_DESCRIPCION as descripcion_nota,");
			query.append(" N.FD_FECHA_PUBLICACION as fecha_publicacion_nota,");
			query.append(" C.FC_DESCRIPCION AS descripcion_categoria, ");
			query.append(" S.FC_DESCRIPCION AS titulo_rss,");
			query.append(" CASE  ");
			query.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN");   
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" ELSE");  
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" END as url_nota,");
			query.append(" N.CL_RTF_CONTENIDO AS contenido_nota,");
			query.append(" COALESCE(N.FC_ESCRIBIO, '') AS autor_nota,");
			query.append(" N.FC_IMAGEN_PRINCIPAL AS imagen_nota,");
			query.append(" COALESCE(N.FC_IMAGEN_PIE, '') AS pie_imagen_nota,");
			query.append(" COALESCE(N.CL_GALERIA_IMAGENES, '') AS galeria_nota, ");
			query.append(" COALESCE(N.FC_VIDEO_YOUTUBE, '') AS id_video_youtube,"); 
			query.append(" COALESCE(N.FC_ID_VIDEO_CONTENT, '') AS id_video_ooyala,");
			query.append(" COALESCE(N.FC_ID_VIDEO_PLAYER, '') AS id_player_ooyala");
			query.append(" FROM WPDB2INS.UNO_MX_N_NOTA N,"); 
			query.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, ");
			query.append(" WPDB2INS.UNO_MX_C_SECCION S, ");
			query.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS");  
			query.append(" WHERE N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA"); 
			query.append(" AND C.FC_ID_SECCION = S.FC_ID_SECCION ");
			query.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION"); 
			query.append(" AND S.FC_ID_SECCION = ? ");
			listObjects.add(idSection);
			if(date!= null && !date.trim().equals("")){
				query.append(" AND N.FD_FECHA_PUBLICACION < ? ");
				listObjects.add(date);
			}
			query.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC ");
			query.append(" FETCH FIRST "+numNotes+" ROWS ONLY OPTIMIZE FOR "+numNotes+" ROWS"); 
			
			//int types[]={Types.VARCHAR};
			
			resultado = (ArrayList<NoticiaRssDTO>) this.jdbcTemplate.query(
					query.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaRssDTO>(NoticiaRssDTO.class) );
		} catch (Exception e) {
			logger.error("Error getNotesBySection [DAO]: ",e);
			throw new DAOException(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public List<NoticiaRssDTO> getNotesBySectionType(String idTypeSection, int numNotes, String fecha)
			throws DAOException {
		List<NoticiaRssDTO> resultado = new ArrayList<NoticiaRssDTO>();
		List<Object> listObjects=new ArrayList<Object>();
		try {
			StringBuffer query=new StringBuffer();
			
			query.append(" SELECT");
			query.append(" N.FC_TITULO as titulo_nota,"); 
			query.append(" N.FC_DESCRIPCION as descripcion_nota,");
			query.append(" N.FD_FECHA_PUBLICACION as fecha_publicacion_nota,");
			query.append(" S.FC_DESCRIPCION AS descripcion_categoria, ");
			query.append(" TS.FC_DESCRIPCION AS titulo_rss,");
			query.append(" CASE  ");
			query.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN");   
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" ELSE");  
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" END as url_nota,");
			query.append(" N.CL_RTF_CONTENIDO AS contenido_nota,");
			query.append(" COALESCE(N.FC_ESCRIBIO, '') AS autor_nota,");
			query.append(" N.FC_IMAGEN_PRINCIPAL AS imagen_nota,");
			query.append(" COALESCE(N.FC_IMAGEN_PIE, '') AS pie_imagen_nota,");
			query.append(" COALESCE(N.CL_GALERIA_IMAGENES, '') AS galeria_nota, ");
			query.append(" COALESCE(N.FC_VIDEO_YOUTUBE, '') AS id_video_youtube,"); 
			query.append(" COALESCE(N.FC_ID_VIDEO_CONTENT, '') AS id_video_ooyala,");
			query.append(" COALESCE(N.FC_ID_VIDEO_PLAYER, '') AS id_player_ooyala");
			query.append(" FROM WPDB2INS.UNO_MX_N_NOTA N,"); 
			query.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, ");
			query.append(" WPDB2INS.UNO_MX_C_SECCION S, ");
			query.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS");  
			query.append(" WHERE N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA"); 
			query.append(" AND C.FC_ID_SECCION = S.FC_ID_SECCION ");
			query.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION"); 
			query.append(" AND S.FC_ID_TIPO_SECCION= ? ");
			listObjects.add(idTypeSection);
			if(fecha!= null && !fecha.trim().equals("")){
				query.append(" AND N.FD_FECHA_PUBLICACION < ? ");
				listObjects.add(fecha);
			}
			query.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC ");
			query.append(" FETCH FIRST "+numNotes+" ROWS ONLY OPTIMIZE FOR "+numNotes+" ROWS"); 
			
			//int types[]={Types.VARCHAR};
			
			resultado = (ArrayList<NoticiaRssDTO>) this.jdbcTemplate.query(
					query.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaRssDTO>(NoticiaRssDTO.class) );
		} catch (Exception e) {
			logger.error("Error getNotesBySectionType [DAO]: ",e);
			throw new DAOException(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public List<NoticiaRssDTO> getNotesByViralesNoTeLoPierdas(
			String viralNoTeLoPierdas, int numNotes, String date) throws DAOException {
		List<NoticiaRssDTO> resultado = new ArrayList<NoticiaRssDTO>();
		List<Object> listObjects=new ArrayList<Object>();
		try {
			StringBuffer query=new StringBuffer();
			
			query.append(" SELECT");
			query.append(" N.FC_TITULO as titulo_nota,"); 
			query.append(" N.FC_DESCRIPCION as descripcion_nota,");
			query.append(" N.FD_FECHA_PUBLICACION as fecha_publicacion_nota,");
			query.append(" C.FC_DESCRIPCION AS descripcion_categoria, ");
			if(viralNoTeLoPierdas.equalsIgnoreCase("virales"))
				query.append(" 'Videos virales' AS titulo_rss, ");
			else if(viralNoTeLoPierdas.equalsIgnoreCase("no-te-lo-pierdas"))
				query.append(" 'No te lo pierdas' AS titulo_rss, ");
			query.append(" CASE  ");
			query.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN");   
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" ELSE");  
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" END as url_nota,");
			query.append(" N.CL_RTF_CONTENIDO AS contenido_nota,");
			query.append(" COALESCE(N.FC_ESCRIBIO, '') AS autor_nota,");
			query.append(" N.FC_IMAGEN_PRINCIPAL AS imagen_nota,");
			query.append(" COALESCE(N.FC_IMAGEN_PIE, '') AS pie_imagen_nota,");
			query.append(" COALESCE(N.CL_GALERIA_IMAGENES, '') AS galeria_nota, ");
			query.append(" COALESCE(N.FC_VIDEO_YOUTUBE, '') AS id_video_youtube,"); 
			query.append(" COALESCE(N.FC_ID_VIDEO_CONTENT, '') AS id_video_ooyala,");
			query.append(" COALESCE(N.FC_ID_VIDEO_PLAYER, '') AS id_player_ooyala"); 
			query.append(" FROM WPDB2INS.UNO_MX_N_NOTA N,"); 
			query.append(" WPDB2INS.UNO_MX_C_CATEGORIA C, ");
			query.append(" WPDB2INS.UNO_MX_C_SECCION S, ");
			query.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS");  
			query.append(" WHERE N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA"); 
			query.append(" AND C.FC_ID_SECCION = S.FC_ID_SECCION ");
			query.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION"); 
			if(viralNoTeLoPierdas.equalsIgnoreCase("virales"))
				query.append(" AND N.FI_BAN_VIDEO_VIRAL = 1 ");
			else if(viralNoTeLoPierdas.equalsIgnoreCase("no-te-lo-pierdas"))
				query.append(" AND N.FI_BAN_NO_TE_LO_PIERDAS = 1 ");
			
			if(date!= null && !date.trim().equals("")){
				query.append(" AND N.FD_FECHA_PUBLICACION < ? ");
				listObjects.add(date);
			}
			query.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC ");
			query.append(" FETCH FIRST "+numNotes+" ROWS ONLY OPTIMIZE FOR "+numNotes+" ROWS"); 
			
			resultado = (ArrayList<NoticiaRssDTO>) this.jdbcTemplate.query(
					query.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaRssDTO>(NoticiaRssDTO.class) );
		} catch (Exception e) {
			logger.error("Error getNotesByViralesNoTeLoPierdas [DAO]: ",e);
			throw new DAOException(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public List<NoticiaRssDTO> getNotesMagazine(String idMagazine)
			throws DAOException {
		List<NoticiaRssDTO> resultado = new ArrayList<NoticiaRssDTO>();
		List<Object> listObjects=new ArrayList<Object>();
		try {
			StringBuffer query=new StringBuffer();
			
			query.append(" SELECT ");
			query.append(" N.FC_TITULO as titulo_nota,"); 
			query.append(" N.FC_DESCRIPCION as descripcion_nota,");
			query.append(" N.FD_FECHA_PUBLICACION as fecha_publicacion_nota,");
			query.append(" C.FC_DESCRIPCION AS descripcion_categoria,  ");
			query.append(" 'Magazine' AS titulo_rss, ");
			query.append(" CASE  ");
			query.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN");   
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" ELSE");  
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')");  
			query.append(" END as url_nota,");
			query.append(" N.CL_RTF_CONTENIDO AS contenido_nota,");
			query.append(" COALESCE(N.FC_ESCRIBIO, '') AS autor_nota,");
			query.append(" N.FC_IMAGEN_PRINCIPAL AS imagen_nota,");
			query.append(" COALESCE(N.FC_IMAGEN_PIE, '') AS pie_imagen_nota,");
			query.append(" COALESCE(N.CL_GALERIA_IMAGENES, '') AS galeria_nota, ");
			query.append(" COALESCE(N.FC_VIDEO_YOUTUBE, '') AS id_video_youtube,"); 
			query.append(" COALESCE(N.FC_ID_VIDEO_CONTENT, '') AS id_video_ooyala,");
			query.append(" COALESCE(N.FC_ID_VIDEO_PLAYER, '') AS id_player_ooyala");  
			query.append(" FROM WPDB2INS.UNO_MX_C_CATEGORIA C, "); 
			query.append(" WPDB2INS.UNO_MX_C_SECCION S,  ");
			query.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS, "); 
			query.append(" WPDB2INS.UNO_MX_N_NOTA N     ");
			query.append(" LEFT OUTER JOIN WPDB2INS.UNO_MX_I_NOTA_MAGAZINE INM ON N.FC_ID_CONTENIDO = INM.FC_ID_CONTENIDO ");  
			query.append(" WHERE  FC_ID_MAGAZINE = ?  ");
			query.append(" AND N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA  ");
			query.append(" AND C.FC_ID_SECCION = S.FC_ID_SECCION   ");
			query.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION "); 
			query.append(" ORDER BY INM.FI_ORDEN ASC ");
					
			listObjects.add(idMagazine);
			//int types[]={Types.VARCHAR};
			
			resultado = (ArrayList<NoticiaRssDTO>) this.jdbcTemplate.query(
					query.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaRssDTO>(NoticiaRssDTO.class) );
		} catch (Exception e) {
			logger.error("Error getNotesBySectionType [DAO]: ",e);
			throw new DAOException(e.getMessage());
		}
		
		return resultado;
	}
	
	@Override
	public List<NoticiaRssDTO> getNotesHome(String idMagazineHome) 
			throws DAOException {
		List<NoticiaRssDTO> resultado = new ArrayList<NoticiaRssDTO>();
		List<Object> listObjects=new ArrayList<Object>();
		try {
			StringBuffer query=new StringBuffer();
			
			query.append(" (SELECT ");
			query.append(" N.FC_TITULO as titulo_nota, ");
			query.append(" N.FC_DESCRIPCION as descripcion_nota,");
			query.append(" N.FD_FECHA_PUBLICACION as fecha_publicacion_nota,");
			query.append(" C.FC_DESCRIPCION AS descripcion_categoria,  ");
			query.append(" 'HomePage' AS titulo_rss, ");
			query.append(" CASE  ");
			query.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN   ");
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')  ");
			query.append(" ELSE  ");
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')  ");
			query.append(" END as url_nota,");
			query.append(" N.CL_RTF_CONTENIDO AS contenido_nota,");
			query.append(" COALESCE(N.FC_ESCRIBIO, '') AS autor_nota,");
			query.append(" N.FC_IMAGEN_PRINCIPAL AS imagen_nota,");
			query.append(" COALESCE(N.FC_IMAGEN_PIE, '') AS pie_imagen_nota,");
			query.append(" COALESCE(N.CL_GALERIA_IMAGENES, '') AS galeria_nota,"); 
			query.append(" COALESCE(N.FC_VIDEO_YOUTUBE, '') AS id_video_youtube, ");
			query.append(" COALESCE(N.FC_ID_VIDEO_CONTENT, '') AS id_video_ooyala,");
			query.append(" COALESCE(N.FC_ID_VIDEO_PLAYER, '') AS id_player_ooyala  ");
			query.append(" FROM WPDB2INS.UNO_MX_C_CATEGORIA C,  ");
			query.append(" WPDB2INS.UNO_MX_C_SECCION S,  ");
			query.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS,  ");
			query.append(" WPDB2INS.UNO_MX_N_NOTA N     ");
			query.append(" WHERE N.FI_BAN_INFINITO_HOME=1");
			query.append(" AND N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA  ");
			query.append(" AND C.FC_ID_SECCION = S.FC_ID_SECCION   ");
			query.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION  ");
			query.append(" AND N.FC_ID_CONTENIDO NOT IN (");
				query.append(" SELECT FC_ID_CONTENIDO");
				query.append(" FROM WPDB2INS.UNO_MX_I_NOTA_MAGAZINE");
				query.append(" WHERE FC_ID_MAGAZINE=?");
				listObjects.add(idMagazineHome);
			query.append(" )");
			query.append(" ORDER BY N.FD_FECHA_PUBLICACION DESC");
			query.append(" FETCH FIRST 30 ROWS ONLY)");
			query.append(" UNION ALL");
			query.append(" (SELECT ");
			query.append(" N.FC_TITULO as titulo_nota, ");
			query.append(" N.FC_DESCRIPCION as descripcion_nota,");
			query.append(" N.FD_FECHA_PUBLICACION as fecha_publicacion_nota,");
			query.append(" C.FC_DESCRIPCION AS descripcion_categoria,  ");
			query.append(" 'HomePage' AS titulo_rss, ");
			query.append(" CASE  ");
			query.append(" WHEN TS.FC_ID_TIPO_SECCION ='especiales' THEN   ");
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')  ");
			query.append(" ELSE  ");
			query.append(" ('http://www.unotv.com/'||COALESCE(TS.FC_ID_TIPO_SECCION,'')||'s/'||COALESCE(S.FC_FRIENDLY_URL,'')||'/'||COALESCE(C.FC_FRIENDLY_URL,'')||'/detalle/'|| COALESCE(N.FC_NOMBRE,'')||'/')  ");
			query.append(" END as url_nota,");
			query.append(" N.CL_RTF_CONTENIDO AS contenido_nota,");
			query.append(" COALESCE(N.FC_ESCRIBIO, '') AS autor_nota,");
			query.append(" N.FC_IMAGEN_PRINCIPAL AS imagen_nota,");
			query.append(" COALESCE(N.FC_IMAGEN_PIE, '') AS pie_imagen_nota,");
			query.append(" COALESCE(N.CL_GALERIA_IMAGENES, '') AS galeria_nota,"); 
			query.append(" COALESCE(N.FC_VIDEO_YOUTUBE, '') AS id_video_youtube, ");
			query.append(" COALESCE(N.FC_ID_VIDEO_CONTENT, '') AS id_video_ooyala,");
			query.append(" COALESCE(N.FC_ID_VIDEO_PLAYER, '') AS id_player_ooyala  ");
			query.append(" FROM WPDB2INS.UNO_MX_C_CATEGORIA C,  ");
			query.append(" WPDB2INS.UNO_MX_C_SECCION S,  ");
			query.append(" WPDB2INS.UNO_MX_C_TIPO_SECCION TS,");  
			query.append(" WPDB2INS.UNO_MX_N_NOTA N     ");
			query.append(" LEFT OUTER JOIN WPDB2INS.UNO_MX_I_NOTA_MAGAZINE INM ON N.FC_ID_CONTENIDO = INM.FC_ID_CONTENIDO   ");
			query.append(" WHERE  FC_ID_MAGAZINE = ? ");
			listObjects.add(idMagazineHome);
			query.append(" AND N.FC_ID_CATEGORIA = C.FC_ID_CATEGORIA  ");
			query.append(" AND C.FC_ID_SECCION = S.FC_ID_SECCION   ");
			query.append(" AND S.FC_ID_TIPO_SECCION = TS.FC_ID_TIPO_SECCION  ");
			query.append(" ORDER BY INM.FI_ORDEN ASC )");
			
			//int types[]={Types.VARCHAR};
			
			resultado = (ArrayList<NoticiaRssDTO>) this.jdbcTemplate.query(
					query.toString(),listObjects.toArray(), new BeanPropertyRowMapper<NoticiaRssDTO>(NoticiaRssDTO.class) );
		} catch (Exception e) {
			logger.error("Error getNotesHome [DAO]: ",e);
			throw new DAOException(e.getMessage());
		}
		
		return resultado;
	}
	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	
}
