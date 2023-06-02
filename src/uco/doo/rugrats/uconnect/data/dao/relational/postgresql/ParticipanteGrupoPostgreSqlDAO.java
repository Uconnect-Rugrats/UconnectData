package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.ParticipanteGrupoDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.SqlDAO;
import uco.doo.rugrats.uconnect.entities.EstadoEntity;
import uco.doo.rugrats.uconnect.entities.EstructuraEntity;
import uco.doo.rugrats.uconnect.entities.GrupoEntity;
import uco.doo.rugrats.uconnect.entities.OrganizacionEntity;
import uco.doo.rugrats.uconnect.entities.PaisEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteGrupoEntity;
import uco.doo.rugrats.uconnect.entities.PersonaEntity;
import uco.doo.rugrats.uconnect.entities.TipoEstadoEntity;
import uco.doo.rugrats.uconnect.entities.TipoIdentificacionEntity;
import uco.doo.rugrats.uconnect.entities.TipoOrganizacionEntity;
import uco.doo.rugrats.uconnect.utils.UtilObject;
import uco.doo.rugrats.uconnect.utils.messages.UconnectDataMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ParticipanteGrupoPostgreSqlDAO extends SqlDAO<ParticipanteGrupoEntity>
		implements ParticipanteGrupoDAO {
	public ParticipanteGrupoPostgreSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(ParticipanteGrupoEntity entity) {
		var sqlStatement = "";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_CREATE,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_CREATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_CREATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_CREATE, exception);
		}
	}

	@Override
	public List<ParticipanteGrupoEntity> read(ParticipanteGrupoEntity entity) {
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
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_READ,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_READ, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_READ, exception);
		}
	}

	@Override
	public void update(ParticipanteGrupoEntity entity) {
		final var sqlStatement = "";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_UPDATE,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_UPDATE, exception);

		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_UPDATE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_UPDATE, exception);
		}
	}

	@Override
	public void delete(UUID entityId) {
		var sqlStatement = "";

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_DELETE,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_DELETE, exception);
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_DELETE_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_DELETE, exception);
		}
	}

	@Override
	protected String preparedSelect() {
		return "SELECT pubprtgrp.identificador pubprtgrp, pubprtgrp.grupo pubprtgrpgrp, pubprtgrpgrp.estructura pubprtgrpgrpstr, pubprtgrpgrpstr.nombre pubprtgrpgrpstrnm, pubprtgrpgrpstr.\"tienePadre\" pubprtgrpgrpstrtnpd, pubprtgrpgrpstr.\"estructuraPadre\" pubprtgrpgrpstrpd, pubprtgrpgrpstr.organizacion pubprtgrpgrpstrorg, pubprtgrpgrpstrorg.nombre pubprtgrpgrpstrorgnm, pubprtgrpgrpstrorg.descripcion pubprtgrpgrpstrorgdesc, pubprtgrpgrpstrorg.\"tipoOrganizacion\" pubprtgrpgrpstrorgtp, pubprtgrpgrpstrorgtp.nombre pubprtgrpgrpstrorgtpnm, pubprtgrpgrpstrorgtp.descripcion pubprtgrpgrpstrorgtpdesc, pubprtgrpgrpstrorg.estado pubprtgrpgrpstrorgest, pubprtgrpgrpstrorgest.nombre pubprtgrpgrpstrorgestnm, pubprtgrpgrpstrorgest.descripcion pubprtgrpgrpstrorgestdesc, pubprtgrpgrpstrorgest.\"tipoEstado\" pubprtgrpgrpstrorgteest, pubprtgrpgrpstrorgteest.nombre pubprtgrpgrpstrorgteestnm, pubprtgrpgrpstrorgteest.descripcion pubprtgrpgrpstrorgteestdesc, pubprtgrpgrpstr.estado pubprtgrpgrpstrest, pubprtgrpgrpstrest.nombre pubprtgrpgrpstrestnm, pubprtgrpgrpstrest.descripcion pubprtgrpgrpstrestdesc, pubprtgrpgrpstrest.\"tipoEstado\" pubprtgrpgrpstrteest, pubprtgrpgrpstrteest.nombre pubprtgrpgrpstrteestnm, pubprtgrpgrpstrteest.descripcion pubprtgrpgrpstrteestdesc, pubprtgrpgrp.nombre pubprtgrpgrpnm, pubprtgrpgrp.estado pubprtgrpgrpest, pubprtgrpgrpest.nombre pubprtgrpgrpestnm, pubprtgrpgrpest.descripcion pubprtgrpgrpestdesc, pubprtgrpgrpest.\"tipoEstado\" pubprtgrpgrpteest, pubprtgrpgrpteest.nombre pubprtgrpgrpteestnm, pubprtgrpgrpteest.descripcion pubprtgrpgrpteestdesc, pubprtgrp.participante pubprtgrpprt, pubprtgrpprt.persona pubprtgrpprtip, pubprtgrpprtip.\"tipoIdentificacion\" pubprtgrpprtiptpid, pubprtgrpprtip.\"numeroIdentificacion\" pubprtgrpprtipid, pubprtgrpprtipid.nombre pubprtgrpprtipidnm, pubprtgrpprtipid.descripcion pubprtgrpprtipiddesc, pubprtgrpprtip.\"primerNombre\" pubprtgrpprtipn1, pubprtgrpprtip.\"segundoNombre\" pubprtgrpprtipn2, pubprtgrpprtip.\"primerApellido\" pubprtgrpprtipa1, pubprtgrpprtip.\"segundoApellido\" pubprtgrpprtipa2, pubprtgrpprtip.\"correoElectronico\" pubprtgrpprtipmail, pubprtgrpprtip.\"paisTelefono\" pubprtgrpprtipps, pubprtgrpprtipps.nombre pubprtgrpprtippsnm, pubprtgrpprtip.\"numeroTelefono\" pubprtgrpprtipphone, pubprtgrpprtip.estado pubprtgrpprtipest, pubprtgrpprtipest.nombre pubprtgrpprtipestnm, pubprtgrpprtipest.descripcion pubprtgrpprtipestdesc, pubprtgrpprtipest.\"tipoEstado\" pubprtgrpprtipteest, pubprtgrpprtipteest.nombre pubprtgrpprtipteestnm, pubprtgrpprtipteest.descripcion pubprtgrpprtipteestdesc, pubprtgrp.\"puedePublicar\" pubprtgrpgrppdpbc ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM \"ParticipanteGrupo\" pubprtgrp JOIN \"Participante\" pubprtgrpprt ON pubprtgrp.participante = pubprtgrpprt.identificador JOIN \"Persona\" pubprtgrpprtip ON pubprtgrpprt.persona = pubprtgrpprtip.identificador JOIN \"TipoIdentificacion\" pubprtgrpprtipid ON pubprtgrpprtipid.identificador = pubprtgrpprtip.\"tipoIdentificacion\" JOIN \"Pais\" pubprtgrpprtipps ON pubprtgrpprtipps.identificador = pubprtgrpprtip.\"paisTelefono\" JOIN \"Estado\" pubprtgrpprtipest ON pubprtgrpprtipest.identificador = pubprtgrpprtip.estado JOIN \"TipoEstado\" pubprtgrpprtipteest ON pubprtgrpprtipteest.identificador = pubprtgrpprtipest.\"tipoEstado\" JOIN \"Estado\" pubprtgrpprtest ON pubprtgrpprt.estado = pubprtgrpprtest.identificador JOIN \"Grupo\" pubprtgrpgrp ON pubprtgrpgrp.identificador = pubprtgrp.grupo JOIN \"Estado\" pubprtgrpgrpest ON pubprtgrpgrpest.identificador = pubprtgrpgrp.estado JOIN \"TipoEstado\" pubprtgrpgrpteest ON pubprtgrpgrpest.\"tipoEstado\" = pubprtgrpgrpteest.identificador JOIN \"Estructura\" pubprtgrpgrpstr ON pubprtgrpgrpstr.identificador = pubprtgrpgrp.estructura JOIN \"Estado\" pubprtgrpgrpstrest ON pubprtgrpgrpstrest.identificador = pubprtgrpgrpstr.estado JOIN \"TipoEstado\" pubprtgrpgrpstrteest ON pubprtgrpgrpstrteest.identificador = pubprtgrpgrpstrest.\"tipoEstado\" JOIN \"Organizacion\" pubprtgrpgrpstrorg ON pubprtgrpgrpstrorg.identificador = pubprtgrpgrpstr.organizacion JOIN \"TipoOrganizacion\" pubprtgrpgrpstrorgtp ON pubprtgrpgrpstrorgtp.identificador = pubprtgrpgrpstrorg.\"tipoOrganizacion\" JOIN \"Estado\" pubprtgrpgrpstrorgest ON pubprtgrpgrpstrorgest.identificador = pubprtgrpgrpstrorg.estado JOIN \"TipoEstado\" pubprtgrpgrpstrorgteest ON pubprtgrpgrpstrorgteest.identificador = pubprtgrpgrpstrorgest.\"tipoEstado\" ";
	}

	@Override
	protected String preparedWhere(ParticipanteGrupoEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY pubprtgrp.identificador ASC ";
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
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		} catch (final Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.TECHNICAL_MESSAGE_SET_PARAMETERS_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.ParticipanteGrupoSqlDao.USER_MESSAGE_SET_PARAMETERS, exception);

		}
	}

	@Override
	protected List<ParticipanteGrupoEntity> executeQuery(PreparedStatement preparedStatement) {
		final List<ParticipanteGrupoEntity> result = new ArrayList<>();

		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				ParticipanteGrupoEntity entityTmp = ParticipanteGrupoEntity.create()
						.setIdentificador(resultSet.getObject("pubprtgrp", UUID.class))
						.setGrupo(GrupoEntity.create().setIdentificador(resultSet.getObject("pubprtgrpgrp", UUID.class))
								.setNombre(resultSet.getString("pubprtgrpgrpnm"))
								.setEstructura(EstructuraEntity.create()
										.setIdentificador(resultSet.getObject("pubprtgrpgrpstr", UUID.class))
										.setNombre(resultSet.getString("pubprtgrpgrpstrnm"))
										.setTienePadre(resultSet.getBoolean("pubprtgrpgrpstrtnpd"))
										.setEstructuraPadre(resultSet.getBoolean("pubprtgrpgrpstrtnpd")
												? EstructuraEntity.create().setIdentificador(
														resultSet.getObject("pubprtgrpgrpstrpd", UUID.class))
												: null)
										.setOrganizacion(OrganizacionEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpgrpstrorg", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpgrpstrorgnm"))
												.setDescripcion(resultSet.getString("pubprtgrpgrpstrorgdesc"))
												.setTipo(TipoOrganizacionEntity.create()
														.setIdentificador(
																resultSet.getObject("pubprtgrpgrpstrorgtp", UUID.class))
														.setNombre(resultSet.getString("pubprtgrpgrpstrorgtpnm"))
														.setDescripcion(
																resultSet.getString("pubprtgrpgrpstrorgtpdesc")))
												.setEstado(EstadoEntity.create()
														.setIdentificador(resultSet.getObject("pubprtgrpgrpstrorgest",
																UUID.class))
														.setNombre(resultSet.getString("pubprtgrpgrpstrorgestnm"))
														.setDescripcion(
																resultSet.getString("pubprtgrpgrpstrorgestdesc"))
														.setTipoEstado(TipoEstadoEntity.create()
																.setIdentificador(resultSet.getObject(
																		"pubprtgrpgrpstrorgteest", UUID.class))
																.setNombre(resultSet
																		.getString("pubprtgrpgrpstrorgteestnm"))
																.setDescripcion(resultSet
																		.getString("pubprtgrpgrpstrorgteestdesc")))))
										.setEstado(EstadoEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpgrpstrest", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpgrpstrestnm"))
												.setDescripcion(resultSet.getString("pubprtgrpgrpstrestdesc"))
												.setTipoEstado(TipoEstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("pubprtgrpgrpstrteest", UUID.class))
														.setNombre(resultSet.getString("pubprtgrpgrpstrteestnm"))
														.setDescripcion(
																resultSet.getString("pubprtgrpgrpstrteestdesc")))))
								.setEstado(EstadoEntity.create()
										.setIdentificador(resultSet.getObject("pubprtgrpgrpest", UUID.class))
										.setNombre(resultSet.getString("pubprtgrpgrpestnm"))
										.setDescripcion(resultSet.getString("pubprtgrpgrpestdesc"))
										.setTipoEstado(TipoEstadoEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpgrpteest", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpgrpteestnm"))
												.setDescripcion(resultSet.getString("pubprtgrpgrpteestdesc")))))
						.setParticipante(ParticipanteEntity.create()
								.setIdentificador(resultSet.getObject("pubprtgrpprt", UUID.class))
								.setPersona(PersonaEntity.create()
										.setIdentificador(resultSet.getObject("pubprtgrpprtip", UUID.class))
										.setTipoIdentificacion(TipoIdentificacionEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpprtiptpid", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpprtipiddesc"))
												.setIndicador(resultSet.getString("pubprtgrpprtipidnm")))
										.setNumeroIdentificacion(resultSet.getString("pubprtgrpprtipid"))
										.setPrimerNombre(resultSet.getString("pubprtgrpprtipn1"))
										.setSegundoNombre(resultSet.getString("pubprtgrpprtipn2"))
										.setPrimerApellido(resultSet.getString("pubprtgrpprtipa1"))
										.setSegundoApellido(resultSet.getString("pubprtgrpprtipa2"))
										.setCorreo(resultSet.getString("pubprtgrpprtipmail"))
										.setPaisTelefono(PaisEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpprtipps", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpprtippsnm"))
										// .setIndicador(resultSet.getString(""))
										).setNumeroTelefono(resultSet.getString("pubprtgrpprtipphone"))
										.setEstado(EstadoEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpprtipest", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpprtipestnm"))
												.setDescripcion(resultSet.getString("pubprtgrpprtipestdesc"))
												.setTipoEstado(TipoEstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("pubprtgrpprtipteest", UUID.class))
														.setNombre(resultSet.getString("pubprtgrpprtipteestnm"))
														.setDescripcion(
																resultSet.getString("pubprtgrpprtipteestdesc"))))))
						.setPuedePublicar(resultSet.getBoolean("pubprtgrpgrppdpbc"));
				result.add(entityTmp);
			}
			return result;
		} catch (Exception exception) {
			throw UconnectDataException.create(
					UconnectDataMessages.SqlDAO.PublicacionSqlDao.TECHNICAL_MESSAGE_READ_JAVA_EXCEPTION,
					UconnectDataMessages.SqlDAO.PublicacionSqlDao.USER_MESSAGE_READ, exception);
		}
	}
}
