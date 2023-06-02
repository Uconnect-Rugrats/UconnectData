package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.EstadoDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.SqlDAO;
import uco.doo.rugrats.uconnect.entities.EstadoEntity;
import uco.doo.rugrats.uconnect.entities.TipoEstadoEntity;
import uco.doo.rugrats.uconnect.utils.UtilObject;
import uco.doo.rugrats.uconnect.utils.UtilSql;
import uco.doo.rugrats.uconnect.utils.UtilText;
import uco.doo.rugrats.uconnect.utils.UtilUUID;
import uco.doo.rugrats.uconnect.utils.messages.UconnectDataMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public final class EstadoPostgreSqlDAO extends SqlDAO<EstadoEntity> implements EstadoDAO {

	public EstadoPostgreSqlDAO(final Connection connection){
		super(connection);
	}

	@Override
	public List<EstadoEntity> read(EstadoEntity entity) {
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
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.EstadoSqlDao.TECHNICAL_MESSAGE_READ,
					UconnectDataMessages.SqlDAO.EstadoSqlDao.USER_MESSAGE_READ, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstadoSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstadoSqlDao.USER_MESSAGE_READ, exception);
		}
	}
	@Override
	protected String preparedSelect() {
		return "SELECT e.identificador eid, e.nombre enm, e.descripcion edesc,\"tipoEstado\" ete, te.nombre etenm, te.descripcion etedesc  ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM public.\"Estado\" e JOIN \"TipoEstado\" te ON te.identificador = e.\"tipoEstado\" ";
	}

	@Override
	protected String preparedWhere(EstadoEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		boolean setWhere = true;
		
		if (!UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE e.identificador = ? ");
				setWhere = false;
			}
			if (!UtilText.isNull(entity.getNombre()) && !UtilText.isEmpty(entity.getNombre())) {
				parameters.add(entity.getNombre());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append(" e.nombre = ? ");
			}
		}
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY e.identificador ASC ";
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
					UconnectDataMessages.SqlDAO.EstadoSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS,
					UconnectDataMessages.SqlDAO.EstadoSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		} catch (final Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstadoSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstadoSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		}
	}

	@Override
	protected List<EstadoEntity> executeQuery(PreparedStatement preparedStatement) {
		final List<EstadoEntity> result = new ArrayList<>();

		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				EstadoEntity entityTmp = EstadoEntity.create()
						.setIdentificador(resultSet.getObject("eid", UUID.class))
						.setNombre(resultSet.getString("enm"))
						.setDescripcion(resultSet.getString("edesc"))
						.setTipoEstado(TipoEstadoEntity.create()
								.setIdentificador(
										resultSet.getObject("ete", UUID.class))
								.setNombre(resultSet.getString("etenm"))
								.setDescripcion(resultSet.getString("etedesc")));
				result.add(entityTmp);
			}
			return result;
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstadoSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstadoSqlDao.USER_MESSAGE_READ, exception);
		}
	}

}
