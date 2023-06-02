package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.TipoReaccionDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.SqlDAO;
import uco.doo.rugrats.uconnect.entities.TipoReaccionEntity;
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

public final class TipoReaccionPostgreSqlDAO extends SqlDAO<TipoReaccionEntity> implements TipoReaccionDAO {
	public TipoReaccionPostgreSqlDAO(final Connection connection){
		super(connection);
	}

	@Override
	public List<TipoReaccionEntity> read(TipoReaccionEntity entity) {
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
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.TECHNICAL_MESSAGE_READ,
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.USER_MESSAGE_READ, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.USER_MESSAGE_READ, exception);
		}
	}
	@Override
	protected String preparedSelect() {
		return "SELECT identificador eid, nombre enm, descripcion edesc  ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM public.\"TipoReaccion\" ";
	}

	@Override
	protected String preparedWhere(TipoReaccionEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		boolean setWhere = true;
		
		if (!UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador = ? ");
				setWhere = false;
			}
			if (!UtilText.isNull(entity.getNombre()) && !UtilText.isEmpty(entity.getNombre())) {
				parameters.add(entity.getNombre());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append(" nombre = ? ");
			}
		}
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY identificador ASC ";
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
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS,
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		} catch (final Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		}
	}

	@Override
	protected List<TipoReaccionEntity> executeQuery(PreparedStatement preparedStatement) {
		final List<TipoReaccionEntity> result = new ArrayList<>();

		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				TipoReaccionEntity entityTmp = TipoReaccionEntity.create()
						.setIdentificador(resultSet.getObject("eid", UUID.class))
						.setNombre(resultSet.getString("enm"))
						.setDescripcion(resultSet.getString("edesc"));
				result.add(entityTmp);
			}
			return result;
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.TipoReaccionSqlDao.USER_MESSAGE_READ, exception);
		}
	}
}
