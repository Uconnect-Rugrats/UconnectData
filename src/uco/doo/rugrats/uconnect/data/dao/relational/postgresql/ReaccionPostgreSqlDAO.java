package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.ReaccionDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.SqlDAO;
import uco.doo.rugrats.uconnect.entities.ParticipanteEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteGrupoEntity;
import uco.doo.rugrats.uconnect.entities.PersonaEntity;
import uco.doo.rugrats.uconnect.entities.PublicacionEntity;
import uco.doo.rugrats.uconnect.entities.ReaccionEntity;
import uco.doo.rugrats.uconnect.entities.TipoReaccionEntity;
import uco.doo.rugrats.uconnect.utils.UtilObject;
import uco.doo.rugrats.uconnect.utils.UtilSql;
import uco.doo.rugrats.uconnect.utils.UtilUUID;
import uco.doo.rugrats.uconnect.utils.messages.UconnectDataMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ReaccionPostgreSqlDAO extends SqlDAO<ReaccionEntity> implements ReaccionDAO {

	public ReaccionPostgreSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(ReaccionEntity entity) {
		var sqlStatement = "INSERT INTO public.\"Reaccion\"(identificador, fecha, publicacion, autor, tipo) VALUES (?, ?, ?, ?, ?);";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.setObject(1, entity.getIdentificador());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getFechaReaccion()));
			preparedStatement.setObject(3, entity.getPublicacion().getIdentificador());
			preparedStatement.setObject(4, entity.getAutor().getIdentificador());
			preparedStatement.setObject(5, entity.getTipo().getIdentificador());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_CREATE,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_CREATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_CREATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_CREATE, exception);
		}
	}

	@Override
	public List<ReaccionEntity> read(ReaccionEntity entity) {
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
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_READ,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_READ, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_READ, exception);
		}
	}

	@Override
	public void update(ReaccionEntity entity) {
		final var sqlStatement = "UPDATE public.\"Reaccion\" SET fecha=?, tipo=? WHERE identificador = ?;";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.setTimestamp(1, Timestamp.valueOf(entity.getFechaReaccion()));
			preparedStatement.setObject(2, entity.getTipo().getIdentificador());
			preparedStatement.setObject(3, entity.getIdentificador());

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_UPDATE,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_UPDATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_UPDATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_UPDATE, exception);
		}
	}

	@Override
	public void delete(UUID entityId) {
		var sqlStatement = "DELETE FROM public.\"Reaccion\" WHERE identificador = ?;";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.setObject(1, entityId);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_DELETE,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_DELETE, exception);
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_DELETE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_DELETE, exception);
		}
	}

	@Override
	protected String preparedSelect() {
		return "SELECT reaccion.identificador AS reaccion_identificador, reaccion.fecha AS reaccion_fecha, publicacion.publicacion_identificador AS reaccion_publicacion, autor.participante_correoelectronico AS autor_correoelectronico, autor.participante_primernombre AS autor_primernombre, autor.participante_segundonombre AS autor_segundonomrbre, autor.participante_primerapellido AS autor_primerapellido, autor.participante_segundoapellido AS autor_segundoapellido, tiporeaccion.tiporeaccion_nombre AS reaccion_tipo, CASE WHEN autor.participantegrupo_estadoreal = true AND publicacion.publicacion_estadoreal = true THEN true ELSE false END AS reaccion_estadoreal ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM \"Reaccion\" reaccion JOIN view_publicacion publicacion ON publicacion.publicacion_identificador = reaccion.publicacion JOIN view_participantegrupo autor ON autor.participantegrupo_identificador = reaccion.autor JOIN view_tiporeaccion tiporeaccion ON tiporeaccion.tiporeaccion_identificador = reaccion.tipo ";
	}

	@Override
	protected String preparedWhere(ReaccionEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;

		if (!UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE reaccion.identificador = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getAutor().getIdentificador())) {
				System.out.println(UtilObject.getInstanceInString(entity.getAutor()));
				System.out.println(UtilObject.getInstanceInString(ParticipanteGrupoEntity.create()));
				parameters.add(entity.getAutor().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("reaccion.autor = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getPublicacion().getIdentificador())) {
				parameters.add(entity.getPublicacion().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("reaccion.publicacion = ? ");
			}
		}
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY reaccion_identificador ASC; ";
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
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		} catch (final Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		}
	}

	@Override
	protected List<ReaccionEntity> executeQuery(PreparedStatement preparedStatement) {
		final List<ReaccionEntity> result = new ArrayList<>();

		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				ReaccionEntity entityTmp = ReaccionEntity.create()
						.setIdentificador(resultSet.getObject("reaccion_identificador", UUID.class))
						.setFechaReaccion(resultSet.getTimestamp("reaccion_fecha").toLocalDateTime())
						.setPublicacion(
								PublicacionEntity.create()
								.setIdentificador(resultSet.getObject("reaccion_publicacion", UUID.class))
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
						.setTipo(
								TipoReaccionEntity.create()
								.setNombre(resultSet.getString("reaccion_tipo"))
								)
						//.setEstadoReal(resultSet.getBoolean("reaccion_estadoreal"))
						;
				
				result.add(entityTmp);
			}
			return result;
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ReaccionSqlDao.USER_MESSAGE_READ, exception);
		}
	}
}
