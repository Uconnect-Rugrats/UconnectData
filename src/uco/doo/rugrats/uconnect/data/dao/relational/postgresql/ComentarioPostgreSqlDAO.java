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
		return "SELECT com.identificador comid, com.fecha comfecha, com.autor comprtgrp, comprtgrp.participante comprtgrpprt, comprtgrpprt.persona comprtgrpprtip, comprtgrpprtip.\"primerNombre\" comprtgrpprtipn1, comprtgrpprtip.\"segundoNombre\" comprtgrpprtipn2, comprtgrpprtip.\"primerApellido\" comprtgrpprtipa1, comprtgrpprtip.\"segundoApellido\" comprtgrpprtipa2, comprtgrpprtip.\"correoElectronico\" comprtgrpprtipcorreo, comprtgrpprtip.estado comprtgrpprtipes, comprtgrpprt.estado comprtgrpprtipes, com.publicacion pub, pub.estado pubes, com.\"tienePadre\" comtnpd, com.\"comentarioPadre\" compadre, com.contenido comcont, com.estado comes, comest.nombre comesnm ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM \"Comentario\" com LEFT JOIN \"Publicacion\" pub ON pub.identificador = com.publicacion JOIN \"ParticipanteGrupo\" pubprtgrp ON pubprtgrp.identificador = pub.autor JOIN \"Participante\" pubprtgrpprt ON pubprtgrp.participante = pubprtgrpprt.identificador JOIN \"Persona\" pubprtgrpprtip ON pubprtgrpprt.persona = pubprtgrpprtip.identificador JOIN \"Estado\" pubprtgrpprtest ON pubprtgrpprt.estado = pubprtgrpprtest.identificador JOIN \"Estado\" pubest ON pubest.identificador = pub.estado JOIN \"ParticipanteGrupo\" comprtgrp ON comprtgrp.identificador = com.autor JOIN \"Participante\" comprtgrpprt ON comprtgrp.participante = comprtgrpprt.identificador JOIN \"Persona\" comprtgrpprtip ON comprtgrpprt.persona = comprtgrpprtip.identificador JOIN \"Estado\" comprtgrpprtipest ON comprtgrpprtipest.identificador = comprtgrpprtip.estado JOIN \"Estado\" comprtgrpprtest ON comprtgrpprt.estado = comprtgrpprtest.identificador JOIN \"Estado\" comest ON comest.identificador = com.estado JOIN \"TipoEstado\" comteest ON comteest.identificador = comest.\"tipoEstado\" ";
	}

	@Override
	protected String preparedWhere(ComentarioEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;

		if (!UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE com.identificador = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getAutor().getIdentificador())) {
				parameters.add(entity.getAutor().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("com.autor = ? ");
				setWhere = false;
			}
//			if (!UtilDate.isNull(entity.getFechaComentario())) {
//				parameters.add(entity.getFechaComentario());
//				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("com.fecha = ? ");
//				setWhere = false;
//			}
			if (!UtilUUID.isDefault(entity.getPublicacion().getIdentificador())) {
				parameters.add(entity.getPublicacion().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("com.publicacion = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getComentarioPadre().getIdentificador())) {
				parameters.add(entity.getComentarioPadre().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("com.\"comentarioPadre\" = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getEstado().getIdentificador())) {
				parameters.add(entity.getEstado().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("com.estado = ? ");
			}

		}
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY com.identificador ASC ";
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
						.setIdentificador(resultSet.getObject("comid", UUID.class))
						.setFechaComentario(resultSet.getTimestamp("comfecha").toLocalDateTime())
						.setAutor(
								ParticipanteGrupoEntity.create()
								.setIdentificador(resultSet.getObject("comprtgrp", UUID.class))
								.setParticipante(
										ParticipanteEntity.create()
										.setIdentificador(resultSet.getObject("comprtgrpprt", UUID.class))
										.setPersona(
												PersonaEntity.create()
												.setIdentificador(resultSet.getObject("comprtgrpprtip", UUID.class))
												.setPrimerNombre(resultSet.getString("comprtgrpprtipn1"))
												.setSegundoNombre(resultSet.getString("comprtgrpprtipn2"))
												.setPrimerApellido(resultSet.getString("comprtgrpprtipa1"))
												.setSegundoApellido(resultSet.getString("comprtgrpprtipa2"))
												.setCorreo(resultSet.getString("comprtgrpprtipcorreo"))
												.setEstado(
														EstadoEntity.create()
														.setIdentificador(resultSet.getObject("comprtgrpprtipes", UUID.class))
														)
												)
										.setEstado(
												EstadoEntity.create()
												.setIdentificador(resultSet.getObject("comprtgrpprtipes", UUID.class))
												)
										)
								)
						.setPublicacion(
								PublicacionEntity.create()
								.setIdentificador(resultSet.getObject("pub", UUID.class))
								.setEstado(
										EstadoEntity.create().
										setIdentificador(resultSet.getObject("pubes", UUID.class))
										)
								)
						.setTienePadre(resultSet.getBoolean("comtnpd"))
						.setComentarioPadre(
								resultSet.getBoolean("comtnpd") ?
										ComentarioEntity.create().setIdentificador(resultSet.getObject("compadre", UUID.class))
										: null
								)
						.setContenido(resultSet.getString("comcont"))
						.setEstado(
								EstadoEntity.create()
								.setIdentificador(resultSet.getObject("comes", UUID.class))
								.setNombre(resultSet.getString("comesnm"))
								)
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
