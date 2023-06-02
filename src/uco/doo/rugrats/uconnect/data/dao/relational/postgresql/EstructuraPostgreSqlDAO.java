package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.EstructuraDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.SqlDAO;
import uco.doo.rugrats.uconnect.entities.EstadoEntity;
import uco.doo.rugrats.uconnect.entities.EstructuraEntity;
import uco.doo.rugrats.uconnect.entities.OrganizacionEntity;
import uco.doo.rugrats.uconnect.entities.TipoEstadoEntity;
import uco.doo.rugrats.uconnect.entities.TipoOrganizacionEntity;
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

public final class EstructuraPostgreSqlDAO extends SqlDAO<EstructuraEntity> implements EstructuraDAO {
	public EstructuraPostgreSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(EstructuraEntity entity) {
		var sqlStatement = "";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_CREATE,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_CREATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_CREATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_CREATE, exception);
		}
	}

	@Override
	public List<EstructuraEntity> read(EstructuraEntity entity) {
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
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_READ,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_READ, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_READ, exception);
		}
	}

	@Override
	public void update(EstructuraEntity entity) {
		final var sqlStatement = "";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_UPDATE,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_UPDATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_UPDATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_UPDATE, exception);
		}
	}

	@Override
	public void delete(UUID entityId) {
		var sqlStatement = "";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_DELETE,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_DELETE, exception);
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_DELETE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_DELETE, exception);
		}
	}

	@Override
	protected String preparedSelect() {
		return "SELECT pubprtgrpgrpstr.identificador pubprtgrpgrp, pubprtgrpgrpstr.nombre pubprtgrpgrpstrnm, pubprtgrpgrpstr.\"tienePadre\" pubprtgrpgrpstrtnpd, pubprtgrpgrpstr.\"estructuraPadre\" pubprtgrpgrpstrpd, pubprtgrpgrpstr.organizacion pubprtgrpgrpstrorg, pubprtgrpgrpstrorg.nombre pubprtgrpgrpstrorgnm, pubprtgrpgrpstrorg.descripcion pubprtgrpgrpstrorgdesc, pubprtgrpgrpstrorg.\"tipoOrganizacion\" pubprtgrpgrpstrorgtp, pubprtgrpgrpstrorgtp.nombre pubprtgrpgrpstrorgtpnm, pubprtgrpgrpstrorgtp.descripcion pubprtgrpgrpstrorgtpdesc, pubprtgrpgrpstrorg.estado pubprtgrpgrpstrorgest, pubprtgrpgrpstrorgest.nombre pubprtgrpgrpstrorgestnm, pubprtgrpgrpstrorgest.descripcion pubprtgrpgrpstrorgestdesc, pubprtgrpgrpstrorgest.\"tipoEstado\" pubprtgrpgrpstrorgteest, pubprtgrpgrpstrorgteest.nombre pubprtgrpgrpstrorgteestnm, pubprtgrpgrpstrorgteest.descripcion pubprtgrpgrpstrorgteestdesc, pubprtgrpgrpstr.estado pubprtgrpgrpstrest, pubprtgrpgrpstrest.nombre pubprtgrpgrpstrestnm, pubprtgrpgrpstrest.descripcion pubprtgrpgrpstrestdesc, pubprtgrpgrpstrest.\"tipoEstado\" pubprtgrpgrpstrteest, pubprtgrpgrpstrteest.nombre pubprtgrpgrpstrteestnm, pubprtgrpgrpstrteest.descripcion pubprtgrpgrpstrteestdesc ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM \"Estructura\" pubprtgrpgrpstr JOIN \"Estado\" pubprtgrpgrpstrest ON pubprtgrpgrpstrest.identificador = pubprtgrpgrpstr.estado JOIN \"TipoEstado\" pubprtgrpgrpstrteest ON pubprtgrpgrpstrteest.identificador = pubprtgrpgrpstrest.\"tipoEstado\" JOIN \"Organizacion\" pubprtgrpgrpstrorg ON pubprtgrpgrpstrorg.identificador = pubprtgrpgrpstr.organizacion JOIN \"TipoOrganizacion\" pubprtgrpgrpstrorgtp ON pubprtgrpgrpstrorgtp.identificador = pubprtgrpgrpstrorg.\"tipoOrganizacion\" JOIN \"Estado\" pubprtgrpgrpstrorgest ON pubprtgrpgrpstrorgest.identificador = pubprtgrpgrpstrorg.estado JOIN \"TipoEstado\" pubprtgrpgrpstrorgteest ON pubprtgrpgrpstrorgteest.identificador = pubprtgrpgrpstrorgest.\"tipoEstado\" ";
	}

	@Override
	protected String preparedWhere(EstructuraEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		boolean setWhere = true;
		
		if (!UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE pubprtgrpgrpstr.identificador = ? ");
				setWhere = false;
			}
			if (!UtilText.isNull(entity.getNombre()) && !UtilText.isEmpty(entity.getNombre())) {
				parameters.add(entity.getNombre());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append(" pubprtgrpgrpstr.nombre = ? ");
			}
		}
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY pubprtgrpgrpstr.identificador ASC ";
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
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		} catch (final Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		}
	}

	@Override
	protected List<EstructuraEntity> executeQuery(PreparedStatement preparedStatement) {
		final List<EstructuraEntity> result = new ArrayList<>();

		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				EstructuraEntity entityTmp = EstructuraEntity.create()
						.setIdentificador(resultSet.getObject("pubprtgrpgrp", UUID.class))
						.setNombre(resultSet.getString("pubprtgrpgrpstrnm"))
						.setTienePadre(resultSet.getBoolean("pubprtgrpgrpstrtnpd"))
						.setEstructuraPadre(
								resultSet.getBoolean("pubprtgrpgrpstrtnpd")
										? read(EstructuraEntity.create().setIdentificador(resultSet.getObject("pubprtgrpgrpstrpd", UUID.class))).get(0)
										: null)
						.setOrganizacion(OrganizacionEntity.create()
								.setIdentificador(resultSet.getObject("pubprtgrpgrpstrorg", UUID.class))
								.setNombre(resultSet.getString("pubprtgrpgrpstrorgnm"))
								.setDescripcion(resultSet.getString("pubprtgrpgrpstrorgdesc"))
								.setTipo(TipoOrganizacionEntity.create()
										.setIdentificador(resultSet.getObject("pubprtgrpgrpstrorgtp", UUID.class))
										.setNombre(resultSet.getString("pubprtgrpgrpstrorgtpnm"))
										.setDescripcion(resultSet.getString("pubprtgrpgrpstrorgtpdesc")))
								.setEstado(EstadoEntity.create()
										.setIdentificador(resultSet.getObject("pubprtgrpgrpstrorgest", UUID.class))
										.setNombre(resultSet.getString("pubprtgrpgrpstrorgestnm"))
										.setDescripcion(resultSet.getString("pubprtgrpgrpstrorgestdesc"))
										.setTipoEstado(TipoEstadoEntity.create()
												.setIdentificador(
														resultSet.getObject("pubprtgrpgrpstrorgteest", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpgrpstrorgteestnm"))
												.setDescripcion(resultSet.getString("pubprtgrpgrpstrorgteestdesc")))))
						.setEstado(EstadoEntity.create()
								.setIdentificador(resultSet.getObject("pubprtgrpgrpstrest", UUID.class))
								.setNombre(resultSet.getString("pubprtgrpgrpstrestnm"))
								.setDescripcion(resultSet.getString("pubprtgrpgrpstrestdesc"))
								.setTipoEstado(TipoEstadoEntity.create()
										.setIdentificador(resultSet.getObject("pubprtgrpgrpstrteest", UUID.class))
										.setNombre(resultSet.getString("pubprtgrpgrpstrteestnm"))
										.setDescripcion(resultSet.getString("pubprtgrpgrpstrteestdesc"))));
				result.add(entityTmp);
			}
			return result;
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.EstructuraSqlDao.USER_MESSAGE_READ, exception);
		}
	}
}
