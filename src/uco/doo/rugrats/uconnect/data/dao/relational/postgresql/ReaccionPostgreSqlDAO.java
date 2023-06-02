package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.ReaccionDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.SqlDAO;
import uco.doo.rugrats.uconnect.entities.EstadoEntity;
import uco.doo.rugrats.uconnect.entities.EstructuraEntity;
import uco.doo.rugrats.uconnect.entities.GrupoEntity;
import uco.doo.rugrats.uconnect.entities.OrganizacionEntity;
import uco.doo.rugrats.uconnect.entities.PaisEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteGrupoEntity;
import uco.doo.rugrats.uconnect.entities.PersonaEntity;
import uco.doo.rugrats.uconnect.entities.PublicacionEntity;
import uco.doo.rugrats.uconnect.entities.ReaccionEntity;
import uco.doo.rugrats.uconnect.entities.TipoEstadoEntity;
import uco.doo.rugrats.uconnect.entities.TipoIdentificacionEntity;
import uco.doo.rugrats.uconnect.entities.TipoOrganizacionEntity;
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
		return "SELECT r.identificador rid, r.fecha rfecha, r.publicacion rpub, pub.\"fechaPublicacion\" pubfecha, pub.titulo pubtitulo, pub.autor pubautor, pubprtgrp.grupo pubprtgrpgrp, pubprtgrpgrp.estructura pubprtgrpgrpstr, pubprtgrpgrpstr.nombre pubprtgrpgrpstrnm, pubprtgrpgrpstr.\"tienePadre\" pubprtgrpgrpstrtnpd, pubprtgrpgrpstr.\"estructuraPadre\" pubprtgrpgrpstrpd, pubprtgrpgrpstr.organizacion pubprtgrpgrpstrorg, pubprtgrpgrpstrorg.nombre pubprtgrpgrpstrorgnm, pubprtgrpgrpstrorg.descripcion pubprtgrpgrpstrorgdesc, pubprtgrpgrpstrorg.\"tipoOrganizacion\" pubprtgrpgrpstrorgtp, pubprtgrpgrpstrorgtp.nombre pubprtgrpgrpstrorgtpnm, pubprtgrpgrpstrorgtp.descripcion pubprtgrpgrpstrorgtpdesc, pubprtgrpgrpstrorg.estado pubprtgrpgrpstrorgest, pubprtgrpgrpstrorgest.nombre pubprtgrpgrpstrorgestnm, pubprtgrpgrpstrorgest.descripcion pubprtgrpgrpstrorgestdesc, pubprtgrpgrpstrorgest.\"tipoEstado\" pubprtgrpgrpstrorgteest, pubprtgrpgrpstrorgteest.nombre pubprtgrpgrpstrorgteestnm, pubprtgrpgrpstrorgteest.descripcion pubprtgrpgrpstrorgteestdesc, pubprtgrpgrpstr.estado pubprtgrpgrpstrest, pubprtgrpgrpstrest.nombre pubprtgrpgrpstrestnm, pubprtgrpgrpstrest.descripcion pubprtgrpgrpstrestdesc, pubprtgrpgrpstrest.\"tipoEstado\" pubprtgrpgrpstrteest, pubprtgrpgrpstrteest.nombre pubprtgrpgrpstrteestnm, pubprtgrpgrpstrteest.descripcion pubprtgrpgrpstrteestdesc, pubprtgrpgrp.nombre pubprtgrpgrpnm, pubprtgrpgrp.estado pubprtgrpgrpest, pubprtgrpgrpest.nombre pubprtgrpgrpestnm, pubprtgrpgrpest.descripcion pubprtgrpgrpestdesc, pubprtgrpgrpest.\"tipoEstado\" pubprtgrpgrpteest, pubprtgrpgrpteest.nombre pubprtgrpgrpteestnm, pubprtgrpgrpteest.descripcion pubprtgrpgrpteestdesc, pubprtgrp.participante pubprtgrpprt, pubprtgrpprt.persona pubprtgrpprtip, pubprtgrpprtip.\"tipoIdentificacion\" pubprtgrpprtiptpid, pubprtgrpprtip.\"numeroIdentificacion\" pubprtgrpprtipid, pubprtgrpprtipid.nombre pubprtgrpprtipidnm, pubprtgrpprtipid.descripcion pubprtgrpprtipiddesc, pubprtgrpprtip.\"primerNombre\" pubprtgrpprtipn1, pubprtgrpprtip.\"segundoNombre\" pubprtgrpprtipn2, pubprtgrpprtip.\"primerApellido\" pubprtgrpprtipa1, pubprtgrpprtip.\"segundoApellido\" pubprtgrpprtipa2, pubprtgrpprtip.\"correoElectronico\" pubprtgrpprtipmail, pubprtgrpprtip.\"paisTelefono\" pubprtgrpprtipps, pubprtgrpprtipps.nombre pubprtgrpprtippsnm, pubprtgrpprtip.\"numeroTelefono\" pubprtgrpprtipphone, pubprtgrpprtip.estado pubprtgrpprtipest, pubprtgrpprtipest.nombre pubprtgrpprtipestnm, pubprtgrpprtipest.descripcion pubprtgrpprtipestdesc, pubprtgrpprtipest.\"tipoEstado\" pubprtgrpprtipteest, pubprtgrpprtipteest.nombre pubprtgrpprtipteestnm, pubprtgrpprtipteest.descripcion pubprtgrpprtipteestdesc, pubprtgrp.\"puedePublicar\" pubprtgrpgrppdpbc, pub.contenido pubcont, pub.estado pubestado, pubest.nombre pubestnm, pubest.descripcion pubestdesc, pubest.\"tipoEstado\" pubteest, pubteest.nombre pubteestnm, pubteest.descripcion pubteestdesc, r.autor rautor, aprtgrp.grupo aprtgrpgrp, aprtgrpgrp.estructura aprtgrpgrpstr, aprtgrpgrpstr.nombre aprtgrpgrpstrnm, aprtgrpgrpstr.\"tienePadre\" aprtgrpgrpstrtnpd, aprtgrpgrpstr.\"estructuraPadre\" aprtgrpgrpstrpd, aprtgrpgrpstr.organizacion aprtgrpgrpstrorg, aprtgrpgrpstrorg.nombre aprtgrpgrpstrorgnm, aprtgrpgrpstrorg.descripcion aprtgrpgrpstrorgdesc, aprtgrpgrpstrorg.\"tipoOrganizacion\" aprtgrpgrpstrorgtp, aprtgrpgrpstrorgtp.nombre aprtgrpgrpstrorgtpnm, aprtgrpgrpstrorgtp.descripcion aprtgrpgrpstrorgtpdesc, aprtgrpgrpstrorg.estado aprtgrpgrpstrorgest, aprtgrpgrpstrorgest.nombre aprtgrpgrpstrorgestnm, aprtgrpgrpstrorgest.descripcion aprtgrpgrpstrorgestdesc, aprtgrpgrpstrorgest.\"tipoEstado\" aprtgrpgrpstrorgteest, aprtgrpgrpstrorgteest.nombre aprtgrpgrpstrorgteestnm, aprtgrpgrpstrorgteest.descripcion aprtgrpgrpstrorgteestdesc, aprtgrpgrpstr.estado aprtgrpgrpstrest, aprtgrpgrpstrest.nombre aprtgrpgrpstrestnm, aprtgrpgrpstrest.descripcion aprtgrpgrpstrestdesc, aprtgrpgrpstrest.\"tipoEstado\" aprtgrpgrpstrteest, aprtgrpgrpstrteest.nombre aprtgrpgrpstrteestnm, aprtgrpgrpstrteest.descripcion aprtgrpgrpstrteestdesc, aprtgrpgrp.nombre aprtgrpgrpnm, aprtgrpgrp.estado aprtgrpgrpest, aprtgrpgrpest.nombre aprtgrpgrpestnm, aprtgrpgrpest.descripcion aprtgrpgrpestdesc, aprtgrpgrpest.\"tipoEstado\" aprtgrpgrpteest, aprtgrpgrpteest.nombre aprtgrpgrpteestnm, aprtgrpgrpteest.descripcion aprtgrpgrpteestdesc, aprtgrp.participante aprtgrpprt, aprtgrpprt.persona aprtgrpprtip, aprtgrpprtip.\"tipoIdentificacion\" aprtgrpprtiptpid, aprtgrpprtip.\"numeroIdentificacion\" aprtgrpprtipid, aprtgrpprtipid.nombre aprtgrpprtipidnm, aprtgrpprtipid.descripcion aprtgrpprtipiddesc, aprtgrpprtip.\"primerNombre\" aprtgrpprtipn1, aprtgrpprtip.\"segundoNombre\" aprtgrpprtipn2, aprtgrpprtip.\"primerApellido\" aprtgrpprtipa1, aprtgrpprtip.\"segundoApellido\" aprtgrpprtipa2, aprtgrpprtip.\"correoElectronico\" aprtgrpprtipmail, aprtgrpprtip.\"paisTelefono\" aprtgrpprtipps, aprtgrpprtipps.nombre aprtgrpprtippsnm, aprtgrpprtip.\"numeroTelefono\" aprtgrpprtipphone, aprtgrpprtip.estado aprtgrpprtipest, aprtgrpprtipest.nombre aprtgrpprtipestnm, aprtgrpprtipest.descripcion aprtgrpprtipestdesc, aprtgrpprtipest.\"tipoEstado\" aprtgrpprtipteest, aprtgrpprtipteest.nombre aprtgrpprtipteestnm, aprtgrpprtipteest.descripcion aprtgrpprtipteestdesc, aprtgrp.\"puedePublicar\" aprtgrpgrppdpbc, r.tipo rtp, tr.nombre rtpnm, tr.descripcion rtpdesc ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM \"Reaccion\" r JOIN \"Publicacion\" pub ON pub.identificador = r.publicacion JOIN \"ParticipanteGrupo\" pubprtgrp ON pubprtgrp.identificador = pub.autor JOIN \"Participante\" pubprtgrpprt ON pubprtgrp.participante = pubprtgrpprt.identificador JOIN \"Persona\" pubprtgrpprtip ON pubprtgrpprt.persona = pubprtgrpprtip.identificador JOIN \"TipoIdentificacion\" pubprtgrpprtipid ON pubprtgrpprtipid.identificador = pubprtgrpprtip.\"tipoIdentificacion\" JOIN \"Pais\" pubprtgrpprtipps ON pubprtgrpprtipps.identificador = pubprtgrpprtip.\"paisTelefono\" JOIN \"Estado\" pubprtgrpprtipest ON pubprtgrpprtipest.identificador = pubprtgrpprtip.estado JOIN \"TipoEstado\" pubprtgrpprtipteest ON pubprtgrpprtipteest.identificador = pubprtgrpprtipest.\"tipoEstado\" JOIN \"Estado\" pubprtgrpprtest ON pubprtgrpprt.estado = pubprtgrpprtest.identificador JOIN \"Grupo\" pubprtgrpgrp ON pubprtgrpgrp.identificador = pubprtgrp.grupo JOIN \"Estado\" pubprtgrpgrpest ON pubprtgrpgrpest.identificador = pubprtgrpgrp.estado JOIN \"TipoEstado\" pubprtgrpgrpteest ON pubprtgrpgrpest.\"tipoEstado\" = pubprtgrpgrpteest.identificador JOIN \"Estructura\" pubprtgrpgrpstr ON pubprtgrpgrpstr.identificador = pubprtgrpgrp.estructura JOIN \"Estado\" pubprtgrpgrpstrest ON pubprtgrpgrpstrest.identificador = pubprtgrpgrpstr.estado JOIN \"TipoEstado\" pubprtgrpgrpstrteest ON pubprtgrpgrpstrteest.identificador = pubprtgrpgrpstrest.\"tipoEstado\" JOIN \"Organizacion\" pubprtgrpgrpstrorg ON pubprtgrpgrpstrorg.identificador = pubprtgrpgrpstr.organizacion JOIN \"TipoOrganizacion\" pubprtgrpgrpstrorgtp ON pubprtgrpgrpstrorgtp.identificador = pubprtgrpgrpstrorg.\"tipoOrganizacion\" JOIN \"Estado\" pubprtgrpgrpstrorgest ON pubprtgrpgrpstrorgest.identificador = pubprtgrpgrpstrorg.estado JOIN \"TipoEstado\" pubprtgrpgrpstrorgteest ON pubprtgrpgrpstrorgteest.identificador = pubprtgrpgrpstrorgest.\"tipoEstado\" JOIN \"Estado\" pubest ON pubest.identificador = pub.estado JOIN \"TipoEstado\" pubteest ON pubteest.identificador = pubest.\"tipoEstado\" JOIN \"ParticipanteGrupo\" aprtgrp ON aprtgrp.identificador = r.autor JOIN \"Participante\" aprtgrpprt ON aprtgrp.participante = aprtgrpprt.identificador JOIN \"Persona\" aprtgrpprtip ON aprtgrpprt.persona = aprtgrpprtip.identificador JOIN \"TipoIdentificacion\" aprtgrpprtipid ON aprtgrpprtipid.identificador = aprtgrpprtip.\"tipoIdentificacion\" JOIN \"Pais\" aprtgrpprtipps ON aprtgrpprtipps.identificador = aprtgrpprtip.\"paisTelefono\" JOIN \"Estado\" aprtgrpprtipest ON aprtgrpprtipest.identificador = aprtgrpprtip.estado JOIN \"TipoEstado\" aprtgrpprtipteest ON aprtgrpprtipteest.identificador = aprtgrpprtipest.\"tipoEstado\" JOIN \"Estado\" aprtgrpprtest ON aprtgrpprt.estado = aprtgrpprtest.identificador JOIN \"Grupo\" aprtgrpgrp ON aprtgrpgrp.identificador = aprtgrp.grupo JOIN \"Estado\" aprtgrpgrpest ON aprtgrpgrpest.identificador = aprtgrpgrp.estado JOIN \"TipoEstado\" aprtgrpgrpteest ON aprtgrpgrpest.\"tipoEstado\" = aprtgrpgrpteest.identificador JOIN \"Estructura\" aprtgrpgrpstr ON aprtgrpgrpstr.identificador = aprtgrpgrp.estructura JOIN \"Estado\" aprtgrpgrpstrest ON aprtgrpgrpstrest.identificador = aprtgrpgrpstr.estado JOIN \"TipoEstado\" aprtgrpgrpstrteest ON aprtgrpgrpstrteest.identificador = aprtgrpgrpstrest.\"tipoEstado\" JOIN \"Organizacion\" aprtgrpgrpstrorg ON aprtgrpgrpstrorg.identificador = aprtgrpgrpstr.organizacion JOIN \"TipoOrganizacion\" aprtgrpgrpstrorgtp ON aprtgrpgrpstrorgtp.identificador = aprtgrpgrpstrorg.\"tipoOrganizacion\" JOIN \"Estado\" aprtgrpgrpstrorgest ON aprtgrpgrpstrorgest.identificador = aprtgrpgrpstrorg.estado JOIN \"TipoEstado\" aprtgrpgrpstrorgteest ON aprtgrpgrpstrorgteest.identificador = aprtgrpgrpstrorgest.\"tipoEstado\" JOIN \"TipoReaccion\" tr ON tr.identificador = r.tipo ";
	}

	@Override
	protected String preparedWhere(ReaccionEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;

		if (!UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE r.identificador = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getAutor().getIdentificador())) {
				System.out.println(UtilObject.getInstanceInString(entity.getAutor()));
				System.out.println(UtilObject.getInstanceInString(ParticipanteGrupoEntity.create()));
				parameters.add(entity.getAutor().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("r.autor = ? ");
				setWhere = false;
			}
			if (!UtilUUID.isDefault(entity.getPublicacion().getIdentificador())) {
				parameters.add(entity.getPublicacion().getIdentificador());
				where.append(UtilSql.appendWhereOrAnd(setWhere)).append("r.publicacion = ? ");
				setWhere = false;
			}
		}
		return where.toString();
	}

	@Override
	protected String preparedOrderBy() {
		return "ORDER BY r.identificador ASC ";
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
						.setIdentificador(resultSet.getObject("rid", UUID.class))
						.setFechaReaccion(resultSet.getTimestamp("rfecha").toLocalDateTime())
						.setPublicacion(PublicacionEntity.create()
								.setIdentificador(resultSet.getObject("rpub", UUID.class))
								.setFechaPublicacion(resultSet.getTimestamp("pubfecha").toLocalDateTime())
								// .setGrupo(null)
								.setAutor(ParticipanteGrupoEntity.create()
										.setIdentificador(resultSet.getObject("pubautor", UUID.class))
										.setGrupo(GrupoEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpgrp", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpgrpnm"))
												.setEstructura(EstructuraEntity.create()
														.setIdentificador(
																resultSet.getObject("pubprtgrpgrpstr", UUID.class))
														.setNombre(resultSet.getString("pubprtgrpgrpstrnm"))
														.setTienePadre(resultSet.getBoolean("pubprtgrpgrpstrtnpd"))
														.setEstructuraPadre(resultSet.getBoolean("pubprtgrpgrpstrtnpd")
																? EstructuraEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"pubprtgrpgrpstrpd", UUID.class))
																: null)
														.setOrganizacion(OrganizacionEntity.create()
																.setIdentificador(resultSet
																		.getObject("pubprtgrpgrpstrorg", UUID.class))
																.setNombre(resultSet.getString("pubprtgrpgrpstrorgnm"))
																.setDescripcion(
																		resultSet.getString("pubprtgrpgrpstrorgdesc"))
																.setTipo(TipoOrganizacionEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"pubprtgrpgrpstrorgtp", UUID.class))
																		.setNombre(resultSet
																				.getString("pubprtgrpgrpstrorgtpnm"))
																		.setDescripcion(resultSet
																				.getString("pubprtgrpgrpstrorgtpdesc")))
																.setEstado(EstadoEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"pubprtgrpgrpstrorgest", UUID.class))
																		.setNombre(resultSet
																				.getString("pubprtgrpgrpstrorgestnm"))
																		.setDescripcion(resultSet
																				.getString("pubprtgrpgrpstrorgestdesc"))
																		.setTipoEstado(TipoEstadoEntity.create()
																				.setIdentificador(resultSet.getObject(
																						"pubprtgrpgrpstrorgteest",
																						UUID.class))
																				.setNombre(resultSet.getString(
																						"pubprtgrpgrpstrorgteestnm"))
																				.setDescripcion(resultSet.getString(
																						"pubprtgrpgrpstrorgteestdesc")))))
														.setEstado(EstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("pubprtgrpgrpstrest", UUID.class))
																.setNombre(resultSet.getString("pubprtgrpgrpstrestnm"))
																.setDescripcion(
																		resultSet.getString("pubprtgrpgrpstrestdesc"))
																.setTipoEstado(TipoEstadoEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"pubprtgrpgrpstrteest", UUID.class))
																		.setNombre(resultSet
																				.getString("pubprtgrpgrpstrteestnm"))
																		.setDescripcion(resultSet.getString(
																				"pubprtgrpgrpstrteestdesc")))))
												.setEstado(EstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("pubprtgrpgrpest", UUID.class))
														.setNombre(resultSet.getString("pubprtgrpgrpestnm"))
														.setDescripcion(resultSet.getString("pubprtgrpgrpestdesc"))
														.setTipoEstado(TipoEstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("pubprtgrpgrpteest", UUID.class))
																.setNombre(resultSet.getString("pubprtgrpgrpteestnm"))
																.setDescripcion(
																		resultSet.getString("pubprtgrpgrpteestdesc")))))
										.setParticipante(ParticipanteEntity.create()
												.setIdentificador(resultSet.getObject("pubprtgrpprt", UUID.class))
												.setPersona(PersonaEntity.create()
														.setIdentificador(
																resultSet.getObject("pubprtgrpprtip", UUID.class))
														.setTipoIdentificacion(TipoIdentificacionEntity.create()
																.setIdentificador(resultSet
																		.getObject("pubprtgrpprtiptpid", UUID.class))
																.setNombre(resultSet.getString("pubprtgrpprtipiddesc"))
																.setIndicador(
																		resultSet.getString("pubprtgrpprtipidnm")))
														.setNumeroIdentificacion(
																resultSet.getString("pubprtgrpprtipid"))
														.setPrimerNombre(resultSet.getString("pubprtgrpprtipn1"))
														.setSegundoNombre(resultSet.getString("pubprtgrpprtipn2"))
														.setPrimerApellido(resultSet.getString("pubprtgrpprtipa1"))
														.setSegundoApellido(resultSet.getString("pubprtgrpprtipa2"))
														.setCorreo(resultSet.getString("pubprtgrpprtipmail"))
														.setPaisTelefono(PaisEntity.create()
																.setIdentificador(resultSet
																		.getObject("pubprtgrpprtipps", UUID.class))
																.setNombre(resultSet.getString("pubprtgrpprtippsnm"))
														// .setIndicador(resultSet.getString(""))
														).setNumeroTelefono(resultSet.getString("pubprtgrpprtipphone"))
														.setEstado(EstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("pubprtgrpprtipest", UUID.class))
																.setNombre(resultSet.getString("pubprtgrpprtipestnm"))
																.setDescripcion(
																		resultSet.getString("pubprtgrpprtipestdesc"))
																.setTipoEstado(TipoEstadoEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"pubprtgrpprtipteest", UUID.class))
																		.setNombre(resultSet
																				.getString("pubprtgrpprtipteestnm"))
																		.setDescripcion(resultSet.getString(
																				"pubprtgrpprtipteestdesc"))))))
										.setPuedePublicar(resultSet.getBoolean("pubprtgrpgrppdpbc")))
								.setTitulo(resultSet.getString("pubtitulo"))
								.setContenido(resultSet.getString("pubcont"))
								.setEstado(EstadoEntity.create()
										.setIdentificador(resultSet.getObject("pubestado", UUID.class))
										.setNombre(resultSet.getString("pubestnm"))
										.setDescripcion(resultSet.getString("pubestdesc"))
										.setTipoEstado(TipoEstadoEntity.create()
												.setIdentificador(resultSet.getObject("pubteest", UUID.class))
												.setNombre(resultSet.getString("pubteestnm"))
												.setDescripcion(resultSet.getString("pubteestdesc")))))
						.setAutor(ParticipanteGrupoEntity.create()
								.setIdentificador(resultSet.getObject("rautor", UUID.class))
								.setGrupo(GrupoEntity.create()
										.setIdentificador(resultSet.getObject("aprtgrpgrp", UUID.class))
										.setNombre(resultSet.getString("aprtgrpgrpnm"))
										.setEstructura(EstructuraEntity.create()
												.setIdentificador(resultSet.getObject("aprtgrpgrpstr", UUID.class))
												.setNombre(resultSet.getString("aprtgrpgrpnm"))
												.setTienePadre(resultSet.getBoolean("aprtgrpgrpstrtnpd"))
												.setEstructuraPadre(
														resultSet.getBoolean("aprtgrpgrpstrtnpd")
																? EstructuraEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"aprtgrpgrpstrpd", UUID.class))
																: null)
												.setOrganizacion(OrganizacionEntity.create()
														.setIdentificador(
																resultSet.getObject("aprtgrpgrpstrorg", UUID.class))
														.setNombre(resultSet.getString("aprtgrpgrpstrorgnm"))
														.setDescripcion(resultSet.getString("aprtgrpgrpstrorgdesc"))
														.setTipo(TipoOrganizacionEntity.create()
																.setIdentificador(resultSet
																		.getObject("aprtgrpgrpstrorgtp", UUID.class))
																.setNombre(resultSet.getString("aprtgrpgrpstrorgtpnm"))
																.setDescripcion(
																		resultSet.getString("aprtgrpgrpstrorgtpdesc")))
														.setEstado(EstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("aprtgrpgrpstrorgest", UUID.class))
																.setNombre(resultSet.getString("aprtgrpgrpstrorgestnm"))
																.setDescripcion(
																		resultSet.getString("aprtgrpgrpstrorgestdesc"))
																.setTipoEstado(TipoEstadoEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"aprtgrpgrpstrorgteest", UUID.class))
																		.setNombre(resultSet
																				.getString("aprtgrpgrpstrorgteestnm"))
																		.setDescripcion(resultSet.getString(
																				"aprtgrpgrpstrorgteestdesc")))))
												.setEstado(EstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("aprtgrpgrpstrest", UUID.class))
														.setNombre(resultSet.getString("aprtgrpgrpstrestnm"))
														.setDescripcion(resultSet.getString("aprtgrpgrpstrestdesc"))
														.setTipoEstado(TipoEstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("aprtgrpgrpstrteest", UUID.class))
																.setNombre(resultSet.getString("aprtgrpgrpstrteestnm"))
																.setDescripcion(resultSet
																		.getString("aprtgrpgrpstrteestdesc")))))
										.setEstado(EstadoEntity.create()
												.setIdentificador(resultSet.getObject("aprtgrpgrpest", UUID.class))
												.setNombre(resultSet.getString("aprtgrpgrpestnm"))
												.setDescripcion(resultSet.getString("aprtgrpgrpestdesc"))
												.setTipoEstado(TipoEstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("aprtgrpgrpteest", UUID.class))
														.setNombre(resultSet.getString("aprtgrpgrpteestnm"))
														.setDescripcion(resultSet.getString("aprtgrpgrpteestdesc")))))
								.setParticipante(ParticipanteEntity.create()
										.setIdentificador(resultSet.getObject("aprtgrpprt", UUID.class))
										.setPersona(PersonaEntity.create()
												.setIdentificador(resultSet.getObject("aprtgrpprtip", UUID.class))
												.setTipoIdentificacion(TipoIdentificacionEntity.create()
														.setIdentificador(
																resultSet.getObject("aprtgrpprtiptpid", UUID.class))
														.setNombre(resultSet.getString("aprtgrpprtipidnm"))
														.setIndicador(resultSet.getString("aprtgrpprtipiddesc")))
												.setNumeroIdentificacion(resultSet.getString("aprtgrpprtipid"))
												.setPrimerNombre(resultSet.getString("aprtgrpprtipn1"))
												.setSegundoNombre(resultSet.getString("aprtgrpprtipn2"))
												.setPrimerApellido(resultSet.getString("aprtgrpprtipa1"))
												.setSegundoApellido(resultSet.getString("aprtgrpprtipa2"))
												.setCorreo(resultSet.getString("aprtgrpprtipmail"))
												.setPaisTelefono(PaisEntity.create()
														.setIdentificador(
																resultSet.getObject("aprtgrpprtipps", UUID.class))
														.setNombre(resultSet.getString("aprtgrpprtippsnm"))
												// .setIndicador(resultSet.getString(""))
												).setNumeroTelefono(resultSet.getString("aprtgrpprtipphone"))
												.setEstado(EstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("aprtgrpprtipest", UUID.class))
														.setNombre(resultSet.getString("aprtgrpprtipestnm"))
														.setDescripcion(resultSet.getString("aprtgrpprtipestdesc"))
														.setTipoEstado(TipoEstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("aprtgrpprtipteest", UUID.class))
																.setNombre(resultSet.getString("aprtgrpprtipteestnm"))
																.setDescripcion(resultSet
																		.getString("aprtgrpprtipteestdesc"))))))
								.setPuedePublicar(resultSet.getBoolean("aprtgrpgrppdpbc")))
						.setTipo(TipoReaccionEntity.create().setIdentificador(resultSet.getObject("rtp", UUID.class))
								.setNombre(resultSet.getString("rtpnm")).setDescripcion(resultSet.getString("rtpnm")));
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
