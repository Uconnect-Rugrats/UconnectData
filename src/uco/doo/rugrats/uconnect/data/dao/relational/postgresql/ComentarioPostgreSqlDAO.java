package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.ComentarioDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.SqlDAO;
import uco.doo.rugrats.uconnect.entities.ComentarioEntity;
import uco.doo.rugrats.uconnect.entities.EstadoEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteGrupoEntity;
import uco.doo.rugrats.uconnect.entities.PersonaEntity;
import uco.doo.rugrats.uconnect.entities.PublicacionEntity;
import uco.doo.rugrats.uconnect.utils.UtilObject;
import uco.doo.rugrats.uconnect.utils.UtilSql;
import uco.doo.rugrats.uconnect.utils.UtilUUID;
import uco.doo.rugrats.uconnect.utils.messages.UconnectDataMessages;

public final class ComentarioPostgreSqlDAO extends SqlDAO<ComentarioEntity> implements ComentarioDAO {
	public ComentarioPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(ComentarioEntity entity) {
		var sqlStatement = "INSERT INTO public.\"Comentario\"(identificador, fecha, publicacion, \"tienePadre\", \"comentarioPadre\", autor, contenido, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.setObject(1, entity.getIdentificador());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getFechaComentario()));
			preparedStatement.setObject(3, entity.getPublicacion().getIdentificador());
			preparedStatement.setBoolean(4, entity.isTienePadre());
			preparedStatement.setObject(5,
					entity.isTienePadre() ? entity.getComentarioPadre().getIdentificador() : null);
			preparedStatement.setObject(6, entity.getAutor().getIdentificador());
			preparedStatement.setString(7, entity.getContenido());
			preparedStatement.setObject(8, entity.getEstado().getIdentificador());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_CREATE,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_CREATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_CREATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_CREATE, exception);
		}
	}

	@Override
	public List<ComentarioEntity> read(ComentarioEntity entity) {
		var sqlStatement = new StringBuilder();
		var parameters = new ArrayList<>();

		sqlStatement.append(preparedSelect());
		sqlStatement.append(preparedFrom());
		sqlStatement.append(preparedWhere(entity, parameters));
		sqlStatement.append(preparedOrderBy());

		try (var preparedstatement = getConnection().prepareStatement(sqlStatement.toString())) {
			setParameters(preparedstatement, parameters);

			return executeQuery(preparedstatement);

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_READ,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_READ, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_READ, exception);
		}
	}

	@Override
	public void update(ComentarioEntity entity) {
		final var sqlStatement = "UPDATE public.\"Comentario\" SET estado=? WHERE \"Comentario\".identificador = ?;";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.setObject(1, entity.getEstado().getIdentificador());
			preparedStatement.setObject(2, entity.getIdentificador());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_UPDATE,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_UPDATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_UPDATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_UPDATE, exception);
		}
	}

	@Override
	public void delete(UUID entityId) {
		var sqlStatement = "DELETE FROM public.\"Comentario\" WHERE \"Comentario\".identificador = ?;";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.setObject(1, entityId);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_DELETE,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_DELETE, exception);
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_DELETE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_DELETE, exception);
		}
	}

	@Override
	protected String preparedSelect() {
		return " SELECT comentario.identificador AS comentario_identificador, comentario.fecha AS comentario_fecha, publicacion.publicacion_identificador AS comentario_publicacion, autor.participante_correoelectronico AS autor_correoelectronico, autor.participante_primernombre AS autor_primernombre, autor.participante_segundonombre AS autor_segundonomrbre, autor.participante_primerapellido AS autor_primerapellido, autor.participante_segundoapellido AS autor_segundoapellido, comentario.\"tienePadre\" AS comentario_tienepadre, comentario.\"comentarioPadre\" AS comentario_comentariopadre, comentario.contenido AS comentario_contenido, estado.estado_nombre AS comentario_estado, CASE WHEN autor.participantegrupo_estadoreal = true AND publicacion.publicacion_estadoreal = true AND estado.estado_nombre::text = 'Publicado'::text THEN true ELSE false END AS comentario_estadoreal ";
	}

	@Override
	protected String preparedFrom() {
		return " FROM \"Comentario\" COMENTARIO JOIN view_publicacion publicacion ON publicacion.publicacion_identificador = COMENTARIO.publicacion JOIN view_participantegrupo autor ON autor.participantegrupo_identificador = COMENTARIO.autor JOIN view_estado ESTADO ON ESTADO.estado_identificador = COMENTARIO.estado ";
	}

	@Override
	protected String preparedWhere(ComentarioEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;

		if (!UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE comentario.identificador = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getAutor().getIdentificador())) {
				parameters.add(entity.getAutor().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("comentario.autor = ? ");
				setWhere = false;
			}
//			if (!UtilDate.isNull(entity.getFechaComentario())) {
//				parameters.add(entity.getFechaComentario());
//				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("comentario.fecha = ? ");
//				setWhere = false;
//			}
			if (!UtilUUID.isDefault(entity.getPublicacion().getIdentificador())) {
				parameters.add(entity.getPublicacion().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("comentario.publicacion = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getComentarioPadre().getIdentificador())) {
				parameters.add(entity.getComentarioPadre().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("comentario.\"comentarioPadre\" = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getEstado().getIdentificador())) {
				parameters.add(entity.getEstado().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("comentario.estado = ? ");
			}

		}
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY comentario.identificador;";
	}

	@Override
	protected void setParameters(PreparedStatement preparedStatement, List<Object> parameters) {
		try {
			if (!UtilObject.isNull(parameters) && !UtilObject.isNull(preparedStatement)) {
				for (int index = 0; index < parameters.size(); index++) {
					preparedStatement.setObject(index + 1, parameters.get(index));
				}
			}
		} catch (final SQLException exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		} catch (final Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		}
	}

	@Override
	protected List<ComentarioEntity> executeQuery(final PreparedStatement preparedStatement) {
		final List<ComentarioEntity> result = new ArrayList<>();

		try (final var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				ComentarioEntity entityTmp = ComentarioEntity.create()
						.setIdentificador(resultSet.getObject("comentario_identificador", UUID.class))
						.setFechaComentario(resultSet.getTimestamp("comentario_fecha").toLocalDateTime())
						.setPublicacion(
								PublicacionEntity.create()
							.setIdentificador(resultSet.getObject("comentario_publicacion", UUID.class))
								)
						.setAutor(
								ParticipanteGrupoEntity.create()
								.setParticipante(
										ParticipanteEntity.create()
										.setPersona(
												PersonaEntity.create()
												.setCorreo(resultSet.getString("autor_correoelectronico"))
												.setPrimerNombre(resultSet.getString("autor_primernombre"))
												.setSegundoNombre(resultSet.getString("autor_segundonomrbre"))
												.setPrimerApellido(resultSet.getString("autor_primerapellido"))
												.setSegundoApellido(resultSet.getString("autor_segundoapellido"))
												)
										)
								)
						.setTienePadre(resultSet.getBoolean("comentario_tienepadre"))
						.setComentarioPadre(
								resultSet.getBoolean("comentario_tienepadre") ? 
								ComentarioEntity.create()
								.setIdentificador(resultSet.getObject("comentario_comentariopadre", UUID.class))
								: null
								)
						.setContenido(resultSet.getString("comentario_contenido"))
						.setEstado(
								EstadoEntity.create()
								.setNombre(resultSet.getString("comentario_estado"))
								)
						//.setEstadoReal(resultSet.getBoolean("comentario_estadoreal"))
						;
						
				result.add(entityTmp);
			}
			return result;

		} catch (final SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_EXECUTE,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_EXECUTE, exception);

		} catch (final Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.TECHNICAL_MESSAGE_EXECUTE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ComentarioSqlDao.USER_MESSAGE_EXECUTE, exception);

		}
	}

}
