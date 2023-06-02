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
import uco.doo.rugrats.uconnect.entities.EstructuraEntity;
import uco.doo.rugrats.uconnect.entities.GrupoEntity;
import uco.doo.rugrats.uconnect.entities.OrganizacionEntity;
import uco.doo.rugrats.uconnect.entities.PaisEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteEntity;
import uco.doo.rugrats.uconnect.entities.ParticipanteGrupoEntity;
import uco.doo.rugrats.uconnect.entities.PersonaEntity;
import uco.doo.rugrats.uconnect.entities.PublicacionEntity;
import uco.doo.rugrats.uconnect.entities.TipoEstadoEntity;
import uco.doo.rugrats.uconnect.entities.TipoIdentificacionEntity;
import uco.doo.rugrats.uconnect.entities.TipoOrganizacionEntity;
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
		return "SELECT com.identificador comid,  com.fecha comfecha,  com.publicacion compub, pub.\"fechaPublicacion\" pubfecha, pub.titulo pubtitulo, pub.autor pubautor, pubprtgrp.grupo pubprtgrpgrp, pubprtgrpgrp.estructura pubprtgrpgrpstr, pubprtgrpgrpstr.nombre pubprtgrpgrpstrnm, pubprtgrpgrpstr.\"tienePadre\" pubprtgrpgrpstrtnpd, pubprtgrpgrpstr.\"estructuraPadre\" pubprtgrpgrpstrpd, pubprtgrpgrpstr.organizacion pubprtgrpgrpstrorg, pubprtgrpgrpstrorg.nombre pubprtgrpgrpstrorgnm, pubprtgrpgrpstrorg.descripcion pubprtgrpgrpstrorgdesc, pubprtgrpgrpstrorg.\"tipoOrganizacion\" pubprtgrpgrpstrorgtp, pubprtgrpgrpstrorgtp.nombre pubprtgrpgrpstrorgtpnm, pubprtgrpgrpstrorgtp.descripcion pubprtgrpgrpstrorgtpdesc, pubprtgrpgrpstrorg.estado pubprtgrpgrpstrorgest, pubprtgrpgrpstrorgest.nombre pubprtgrpgrpstrorgestnm, pubprtgrpgrpstrorgest.descripcion pubprtgrpgrpstrorgestdesc, pubprtgrpgrpstrorgest.\"tipoEstado\" pubprtgrpgrpstrorgteest, pubprtgrpgrpstrorgteest.nombre pubprtgrpgrpstrorgteestnm, pubprtgrpgrpstrorgteest.descripcion pubprtgrpgrpstrorgteestdesc, pubprtgrpgrpstr.estado pubprtgrpgrpstrest, pubprtgrpgrpstrest.nombre pubprtgrpgrpstrestnm, pubprtgrpgrpstrest.descripcion pubprtgrpgrpstrestdesc, pubprtgrpgrpstrest.\"tipoEstado\" pubprtgrpgrpstrteest, pubprtgrpgrpstrteest.nombre pubprtgrpgrpstrteestnm, pubprtgrpgrpstrteest.descripcion pubprtgrpgrpstrteestdesc, pubprtgrpgrp.nombre pubprtgrpgrpnm, pubprtgrpgrp.estado pubprtgrpgrpest, pubprtgrpgrpest.nombre pubprtgrpgrpestnm, pubprtgrpgrpest.descripcion pubprtgrpgrpestdesc, pubprtgrpgrpest.\"tipoEstado\" pubprtgrpgrpteest, pubprtgrpgrpteest.nombre pubprtgrpgrpteestnm, pubprtgrpgrpteest.descripcion pubprtgrpgrpteestdesc, pubprtgrp.participante pubprtgrpprt, pubprtgrpprt.persona pubprtgrpprtip, pubprtgrpprtip.\"tipoIdentificacion\" pubprtgrpprtiptpid, pubprtgrpprtip.\"numeroIdentificacion\" pubprtgrpprtipid, pubprtgrpprtipid.nombre pubprtgrpprtipidnm, pubprtgrpprtipid.descripcion pubprtgrpprtipiddesc, pubprtgrpprtip.\"primerNombre\" pubprtgrpprtipn1, pubprtgrpprtip.\"segundoNombre\" pubprtgrpprtipn2, pubprtgrpprtip.\"primerApellido\" pubprtgrpprtipa1, pubprtgrpprtip.\"segundoApellido\" pubprtgrpprtipa2, pubprtgrpprtip.\"correoElectronico\" pubprtgrpprtipmail, pubprtgrpprtip.\"paisTelefono\" pubprtgrpprtipps, pubprtgrpprtipps.nombre pubprtgrpprtippsnm, pubprtgrpprtip.\"numeroTelefono\" pubprtgrpprtipphone, pubprtgrpprtip.estado pubprtgrpprtipest, pubprtgrpprtipest.nombre pubprtgrpprtipestnm, pubprtgrpprtipest.descripcion pubprtgrpprtipestdesc, pubprtgrpprtipest.\"tipoEstado\" pubprtgrpprtipteest, pubprtgrpprtipteest.nombre pubprtgrpprtipteestnm, pubprtgrpprtipteest.descripcion pubprtgrpprtipteestdesc, pubprtgrp.\"puedePublicar\" pubprtgrpgrppdpbc, pub.contenido pubcont, pub.estado pubestado, pubest.nombre pubestnm, pubest.descripcion pubestdesc, pubest.\"tipoEstado\" pubteest, pubteest.nombre pubteestnm, pubteest.descripcion pubteestdesc, com.\"tienePadre\" comtnpdr, com.\"comentarioPadre\" compadre,  com.autor comautor, comprtgrp.grupo comprtgrpgrp, comprtgrpgrp.estructura comprtgrpgrpstr, comprtgrpgrpstr.nombre comprtgrpgrpstrnm, comprtgrpgrpstr.\"tienePadre\" comprtgrpgrpstrtnpd, comprtgrpgrpstr.\"estructuraPadre\" comprtgrpgrpstrpd, comprtgrpgrpstr.organizacion comprtgrpgrpstrorg, comprtgrpgrpstrorg.nombre comprtgrpgrpstrorgnm, comprtgrpgrpstrorg.descripcion comprtgrpgrpstrorgdesc, comprtgrpgrpstrorg.\"tipoOrganizacion\" comprtgrpgrpstrorgtp, comprtgrpgrpstrorgtp.nombre comprtgrpgrpstrorgtpnm, comprtgrpgrpstrorgtp.descripcion comprtgrpgrpstrorgtpdesc, comprtgrpgrpstrorg.estado comprtgrpgrpstrorgest, comprtgrpgrpstrorgest.nombre comprtgrpgrpstrorgestnm, comprtgrpgrpstrorgest.descripcion comprtgrpgrpstrorgestdesc, comprtgrpgrpstrorgest.\"tipoEstado\" comprtgrpgrpstrorgteest, comprtgrpgrpstrorgteest.nombre comprtgrpgrpstrorgteestnm, comprtgrpgrpstrorgteest.descripcion comprtgrpgrpstrorgteestdesc, comprtgrpgrpstr.estado comprtgrpgrpstrest, comprtgrpgrpstrest.nombre comprtgrpgrpstrestnm, comprtgrpgrpstrest.descripcion comprtgrpgrpstrestdesc, comprtgrpgrpstrest.\"tipoEstado\" comprtgrpgrpstrteest, comprtgrpgrpstrteest.nombre comprtgrpgrpstrteestnm, comprtgrpgrpstrteest.descripcion comprtgrpgrpstrteestdesc, comprtgrpgrp.nombre comprtgrpgrpnm, comprtgrpgrp.estado comprtgrpgrpest, comprtgrpgrpest.nombre comprtgrpgrpestnm, comprtgrpgrpest.descripcion comprtgrpgrpestdesc, comprtgrpgrpest.\"tipoEstado\" comprtgrpgrpteest, comprtgrpgrpteest.nombre comprtgrpgrpteestnm, comprtgrpgrpteest.descripcion comprtgrpgrpteestdesc, comprtgrp.participante comprtgrpprt, comprtgrpprt.persona comprtgrpprtip, comprtgrpprtip.\"tipoIdentificacion\" comprtgrpprtiptpid, comprtgrpprtip.\"numeroIdentificacion\" comprtgrpprtipid, comprtgrpprtipid.nombre comprtgrpprtipidnm, comprtgrpprtipid.descripcion comprtgrpprtipiddesc, comprtgrpprtip.\"primerNombre\" comprtgrpprtipn1, comprtgrpprtip.\"segundoNombre\" comprtgrpprtipn2, comprtgrpprtip.\"primerApellido\" comprtgrpprtipa1, comprtgrpprtip.\"segundoApellido\" comprtgrpprtipa2, comprtgrpprtip.\"correoElectronico\" comprtgrpprtipmail, comprtgrpprtip.\"paisTelefono\" comprtgrpprtipps, comprtgrpprtipps.nombre comprtgrpprtippsnm, comprtgrpprtip.\"numeroTelefono\" comprtgrpprtipphone, comprtgrpprtip.estado comprtgrpprtipest, comprtgrpprtipest.nombre comprtgrpprtipestnm, comprtgrpprtipest.descripcion comprtgrpprtipestdesc, comprtgrpprtipest.\"tipoEstado\" comprtgrpprtipteest, comprtgrpprtipteest.nombre comprtgrpprtipteestnm, comprtgrpprtipteest.descripcion comprtgrpprtipteestdesc, comprtgrp.\"puedePublicar\" comprtgrpgrppdpbc, com.contenido comcont,  com.estado comest, comest.nombre comestnm, comest.descripcion comestdesc, comest.\"tipoEstado\" comteest, comteest.nombre comteestnm,  comteest.descripcion comteestdesc ";
	}

	@Override
	protected String preparedFrom() {
		return "FROM \"Comentario\" com JOIN \"Publicacion\" pub ON pub.identificador = com.publicacion JOIN \"ParticipanteGrupo\" pubprtgrp ON pubprtgrp.identificador = pub.autor JOIN \"Participante\" pubprtgrpprt ON pubprtgrp.participante = pubprtgrpprt.identificador JOIN \"Persona\" pubprtgrpprtip ON  pubprtgrpprt.persona = pubprtgrpprtip.identificador JOIN \"TipoIdentificacion\" pubprtgrpprtipid ON pubprtgrpprtipid.identificador = pubprtgrpprtip.\"tipoIdentificacion\" JOIN \"Pais\" pubprtgrpprtipps ON pubprtgrpprtipps.identificador = pubprtgrpprtip.\"paisTelefono\" JOIN \"Estado\" pubprtgrpprtipest ON pubprtgrpprtipest.identificador = pubprtgrpprtip.estado JOIN \"TipoEstado\" pubprtgrpprtipteest ON pubprtgrpprtipteest.identificador = pubprtgrpprtipest.\"tipoEstado\" JOIN \"Estado\" pubprtgrpprtest ON pubprtgrpprt.estado = pubprtgrpprtest.identificador JOIN \"Grupo\" pubprtgrpgrp ON pubprtgrpgrp.identificador = pubprtgrp.grupo JOIN \"Estado\" pubprtgrpgrpest ON pubprtgrpgrpest.identificador = pubprtgrpgrp.estado JOIN \"TipoEstado\" pubprtgrpgrpteest ON pubprtgrpgrpest.\"tipoEstado\" = pubprtgrpgrpteest.identificador JOIN \"Estructura\" pubprtgrpgrpstr ON pubprtgrpgrpstr.identificador = pubprtgrpgrp.estructura JOIN \"Estado\" pubprtgrpgrpstrest ON pubprtgrpgrpstrest.identificador = pubprtgrpgrpstr.estado JOIN \"TipoEstado\" pubprtgrpgrpstrteest ON pubprtgrpgrpstrteest.identificador = pubprtgrpgrpstrest.\"tipoEstado\" JOIN \"Organizacion\" pubprtgrpgrpstrorg ON pubprtgrpgrpstrorg.identificador = pubprtgrpgrpstr.organizacion JOIN \"TipoOrganizacion\" pubprtgrpgrpstrorgtp ON pubprtgrpgrpstrorgtp.identificador = pubprtgrpgrpstrorg.\"tipoOrganizacion\" JOIN \"Estado\" pubprtgrpgrpstrorgest ON pubprtgrpgrpstrorgest.identificador = pubprtgrpgrpstrorg.estado JOIN \"TipoEstado\" pubprtgrpgrpstrorgteest ON pubprtgrpgrpstrorgteest.identificador = pubprtgrpgrpstrorgest.\"tipoEstado\" JOIN \"Estado\" pubest ON pubest.identificador = pub.estado JOIN \"TipoEstado\" pubteest ON pubteest.identificador = pubest.\"tipoEstado\" JOIN \"ParticipanteGrupo\" comprtgrp ON comprtgrp.identificador = com.autor JOIN \"Participante\" comprtgrpprt ON comprtgrp.participante = comprtgrpprt.identificador JOIN \"Persona\" comprtgrpprtip ON  comprtgrpprt.persona = comprtgrpprtip.identificador JOIN \"TipoIdentificacion\" comprtgrpprtipid ON comprtgrpprtipid.identificador = comprtgrpprtip.\"tipoIdentificacion\" JOIN \"Pais\" comprtgrpprtipps ON comprtgrpprtipps.identificador = comprtgrpprtip.\"paisTelefono\" JOIN \"Estado\" comprtgrpprtipest ON comprtgrpprtipest.identificador = comprtgrpprtip.estado JOIN \"TipoEstado\" comprtgrpprtipteest ON comprtgrpprtipteest.identificador = comprtgrpprtipest.\"tipoEstado\" JOIN \"Estado\" comprtgrpprtest ON comprtgrpprt.estado = comprtgrpprtest.identificador JOIN \"Grupo\" comprtgrpgrp ON comprtgrpgrp.identificador = comprtgrp.grupo JOIN \"Estado\" comprtgrpgrpest ON comprtgrpgrpest.identificador = comprtgrpgrp.estado JOIN \"TipoEstado\" comprtgrpgrpteest ON comprtgrpgrpest.\"tipoEstado\" = comprtgrpgrpteest.identificador JOIN \"Estructura\" comprtgrpgrpstr ON comprtgrpgrpstr.identificador = comprtgrpgrp.estructura JOIN \"Estado\" comprtgrpgrpstrest ON comprtgrpgrpstrest.identificador = comprtgrpgrpstr.estado JOIN \"TipoEstado\" comprtgrpgrpstrteest ON comprtgrpgrpstrteest.identificador = comprtgrpgrpstrest.\"tipoEstado\" JOIN \"Organizacion\" comprtgrpgrpstrorg ON comprtgrpgrpstrorg.identificador = comprtgrpgrpstr.organizacion JOIN \"TipoOrganizacion\" comprtgrpgrpstrorgtp ON comprtgrpgrpstrorgtp.identificador = comprtgrpgrpstrorg.\"tipoOrganizacion\" JOIN \"Estado\" comprtgrpgrpstrorgest ON comprtgrpgrpstrorgest.identificador = comprtgrpgrpstrorg.estado JOIN \"TipoEstado\" comprtgrpgrpstrorgteest ON comprtgrpgrpstrorgteest.identificador = comprtgrpgrpstrorgest.\"tipoEstado\" JOIN \"Estado\" comest ON comest.identificador = com.estado JOIN \"TipoEstado\" comteest ON comteest.identificador = comest.\"tipoEstado\" ";
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
				System.out.println(UtilObject.getInstanceInString(entity.getAutor()));
				System.out.println(UtilObject.getInstanceInString(ParticipanteGrupoEntity.create()));
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
				final ComentarioEntity entityTmp = ComentarioEntity.create()
						.setIdentificador(resultSet.getObject("comid", UUID.class))
						.setFechaComentario(resultSet.getTimestamp("comfecha").toLocalDateTime())
						.setPublicacion(PublicacionEntity.create()
								.setIdentificador(resultSet.getObject("compub", UUID.class))
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
																.setIdentificador(resultSet.getObject("pubprtgrpgrpest",
																		UUID.class))
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
																				"pubprtgrpprtipteestdesc")))))
												.setEstado(EstadoEntity.create()
														.setIdentificador(resultSet.getObject("comid", UUID.class))
														.setNombre(resultSet.getString("pubprtgrpgrpnm"))
														.setDescripcion(resultSet.getString("pubprtgrpgrpnm"))
														.setTipoEstado(TipoEstadoEntity.create()
																.setIdentificador(
																		resultSet.getObject("comid", UUID.class))
																.setNombre(resultSet.getString("pubprtgrpgrpnm"))
																.setDescripcion(
																		resultSet.getString("pubprtgrpgrpnm")))))
										.setPuedePublicar(resultSet.getBoolean("pubprtgrpgrppdpbc"))
										.setEstado(EstadoEntity.create()
												.setIdentificador(resultSet.getObject("comid", UUID.class))
												.setNombre(resultSet.getString("pubprtgrpgrpnm"))
												.setDescripcion(resultSet.getString("pubprtgrpgrpnm"))
												.setTipoEstado(TipoEstadoEntity.create()
														.setIdentificador(resultSet.getObject("comid", UUID.class))
														.setNombre(resultSet.getString("pubprtgrpgrpnm"))
														.setDescripcion(resultSet.getString("pubprtgrpgrpnm")))))
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
						.setTienePadre(resultSet.getBoolean("comtnpdr"))
						.setComentarioPadre(
								resultSet.getBoolean("comtnpdr")
										? ComentarioEntity.create()
												.setIdentificador(resultSet.getObject("compadre", UUID.class))
										: null)
						.setAutor(ParticipanteGrupoEntity.create()
								.setIdentificador(resultSet.getObject("comautor", UUID.class))
								.setGrupo(GrupoEntity.create()
										.setIdentificador(resultSet.getObject("comprtgrpgrp", UUID.class))
										.setNombre(resultSet.getString("comprtgrpgrpnm"))
										.setEstructura(EstructuraEntity.create()
												.setIdentificador(resultSet.getObject("comprtgrpgrpstr", UUID.class))
												.setNombre((resultSet.getString("comprtgrpgrpstrnm")))
												.setTienePadre(resultSet.getBoolean("comprtgrpgrpstrtnpd"))
												.setEstructuraPadre(
														resultSet.getBoolean("comprtgrpgrpstrtnpd")
																? EstructuraEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"comprtgrpgrpstrpd", UUID.class))
																: null)
												.setOrganizacion(OrganizacionEntity.create()
														.setIdentificador(
																resultSet.getObject("comprtgrpgrpstrorg", UUID.class))
														.setNombre((resultSet.getString("comprtgrpgrpstrorgnm")))
														.setDescripcion((resultSet.getString("comprtgrpgrpstrorgdesc")))
														.setTipo(TipoOrganizacionEntity.create()
																.setIdentificador(resultSet
																		.getObject("comprtgrpgrpstrorgtp", UUID.class))
																.setNombre(
																		(resultSet.getString("comprtgrpgrpstrorgtpnm")))
																.setDescripcion((resultSet
																		.getString("comprtgrpgrpstrorgtpdesc"))))
														.setEstado(EstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("comprtgrpgrpstrorgest", UUID.class))
																.setNombre((resultSet
																		.getString("comprtgrpgrpstrorgestnm")))
																.setDescripcion((resultSet
																		.getString("comprtgrpgrpstrorgestdesc")))
																.setTipoEstado(TipoEstadoEntity.create()
																		.setIdentificador(resultSet.getObject(
																				"comprtgrpgrpstrorgteest", UUID.class))
																		.setNombre((resultSet.getString(
																				"comprtgrpgrpstrorgteestnm")))
																		.setDescripcion((resultSet.getString(
																				"comprtgrpgrpstrorgteestdesc"))))))
												.setEstado(EstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("comprtgrpgrpstrest", UUID.class))
														.setNombre((resultSet.getString("comprtgrpgrpstrestnm")))
														.setDescripcion((resultSet.getString("comprtgrpgrpstrestdesc")))
														.setTipoEstado(TipoEstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("comprtgrpgrpstrteest", UUID.class))
																.setNombre(
																		(resultSet.getString("comprtgrpgrpstrteestnm")))
																.setDescripcion((resultSet
																		.getString("comprtgrpgrpstrteestdesc"))))))
										.setEstado(EstadoEntity.create()
												.setIdentificador(resultSet.getObject("comprtgrpgrpest", UUID.class))
												.setNombre((resultSet.getString("comprtgrpgrpestnm")))
												.setDescripcion((resultSet.getString("comprtgrpgrpestdesc")))
												.setTipoEstado(TipoEstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("comprtgrpgrpteest", UUID.class))
														.setNombre((resultSet.getString("comprtgrpgrpteestnm")))
														.setDescripcion(
																(resultSet.getString("comprtgrpgrpteestdesc"))))))
								.setParticipante(ParticipanteEntity.create()
										.setIdentificador(resultSet.getObject("comprtgrpprt", UUID.class))
										.setPersona(PersonaEntity.create()
												.setIdentificador(resultSet.getObject("comprtgrpprtip", UUID.class))
												.setTipoIdentificacion(TipoIdentificacionEntity.create()
														.setIdentificador(
																resultSet.getObject("comprtgrpprtiptpid", UUID.class))
														.setNombre((resultSet.getString("comprtgrpprtipidnm")))
														.setIndicador((resultSet.getString("comprtgrpprtipiddesc"))))
												.setNumeroIdentificacion((resultSet.getString("comprtgrpprtipid")))
												.setPrimerNombre((resultSet.getString("comprtgrpprtipn1")))
												.setSegundoNombre((resultSet.getString("comprtgrpprtipn2")))
												.setPrimerApellido((resultSet.getString("comprtgrpprtipa1")))
												.setSegundoApellido((resultSet.getString("comprtgrpprtipa2")))
												.setCorreo((resultSet.getString("comprtgrpprtipmail")))
												.setPaisTelefono(PaisEntity.create()
														.setIdentificador(
																resultSet.getObject("comprtgrpprtipps", UUID.class))
														.setNombre((resultSet.getString("comprtgrpprtippsnm")))
												// .setIndicador((resultSet.getString("")))
												).setNumeroTelefono((resultSet.getString("comprtgrpprtipphone")))
												.setEstado(EstadoEntity.create()
														.setIdentificador(
																resultSet.getObject("comprtgrpprtipest", UUID.class))
														.setNombre((resultSet.getString("comprtgrpprtipestnm")))
														.setDescripcion((resultSet.getString("comprtgrpprtipestdesc")))
														.setTipoEstado(TipoEstadoEntity.create()
																.setIdentificador(resultSet
																		.getObject("comprtgrpprtipteest", UUID.class))
																.setNombre(
																		(resultSet.getString("comprtgrpprtipteestnm")))
																.setDescripcion((resultSet
																		.getString("comprtgrpprtipteestdesc"))))))
										.setEstado(EstadoEntity.create()
//												.setIdentificador(resultSet.getObject("", UUID.class))
//												.setNombre((resultSet.getString("")))
//												.setDescripcion((resultSet.getString("")))
//												.setTipoEstado(
//														TipoEstadoEntity.create()
//														.setIdentificador(resultSet.getObject("", UUID.class))
//														.setNombre((resultSet.getString("")))
//														.setDescripcion((resultSet.getString("")))
//														)
										)).setPuedePublicar(resultSet.getBoolean("comprtgrpgrppdpbc"))
								.setEstado(
										EstadoEntity.create().setIdentificador(resultSet.getObject("comid", UUID.class))
												.setNombre((resultSet.getString("pubprtgrpgrpnm")))
												.setDescripcion((resultSet.getString("pubprtgrpgrpnm")))
												.setTipoEstado(TipoEstadoEntity.create().setIdentificador(null)
														.setNombre((resultSet.getString("pubprtgrpgrpnm")))
														.setDescripcion((resultSet.getString("pubprtgrpgrpnm"))))))
						.setContenido((resultSet.getString("comcont")))
						.setEstado(EstadoEntity.create().setIdentificador(resultSet.getObject("comest", UUID.class))
								.setNombre((resultSet.getString("comestnm")))
								.setDescripcion((resultSet.getString("comestdesc")))
								.setTipoEstado(TipoEstadoEntity.create()
										.setIdentificador(resultSet.getObject("comteest", UUID.class))
										.setNombre((resultSet.getString("comteestnm")))
										.setDescripcion((resultSet.getString("comteestdesc")))));
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
