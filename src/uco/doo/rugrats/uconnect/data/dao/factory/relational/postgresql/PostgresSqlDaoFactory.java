package uco.doo.rugrats.uconnect.data.dao.factory.relational.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.AdministradorEstructuraDAO;
import uco.doo.rugrats.uconnect.data.dao.AdministradorOrganizacionDAO;
import uco.doo.rugrats.uconnect.data.dao.AgendaDAO;
import uco.doo.rugrats.uconnect.data.dao.CausaReporteDAO;
import uco.doo.rugrats.uconnect.data.dao.ChatDAO;
import uco.doo.rugrats.uconnect.data.dao.ComentarioDAO;
import uco.doo.rugrats.uconnect.data.dao.EstadoDAO;
import uco.doo.rugrats.uconnect.data.dao.EstructuraAdministradorEstructuraDAO;
import uco.doo.rugrats.uconnect.data.dao.EstructuraDAO;
import uco.doo.rugrats.uconnect.data.dao.EventoDAO;
import uco.doo.rugrats.uconnect.data.dao.GrupoDAO;
import uco.doo.rugrats.uconnect.data.dao.HistorialChatGrupoDAO;
import uco.doo.rugrats.uconnect.data.dao.HistorialLecturaDAO;
import uco.doo.rugrats.uconnect.data.dao.MensajeDAO;
import uco.doo.rugrats.uconnect.data.dao.OrganizacionAdministradorOrganizacionDAO;
import uco.doo.rugrats.uconnect.data.dao.OrganizacionDAO;
import uco.doo.rugrats.uconnect.data.dao.PaisDAO;
import uco.doo.rugrats.uconnect.data.dao.ParticipanteDAO;
import uco.doo.rugrats.uconnect.data.dao.ParticipanteGrupoDAO;
import uco.doo.rugrats.uconnect.data.dao.PersonaDAO;
import uco.doo.rugrats.uconnect.data.dao.PublicacionDAO;
import uco.doo.rugrats.uconnect.data.dao.ReaccionDAO;
import uco.doo.rugrats.uconnect.data.dao.ReporteComentarioDAO;
import uco.doo.rugrats.uconnect.data.dao.ReporteMensajeDAO;
import uco.doo.rugrats.uconnect.data.dao.ReportePublicacionDAO;
import uco.doo.rugrats.uconnect.data.dao.RespuestaReporteComentarioDAO;
import uco.doo.rugrats.uconnect.data.dao.RespuestaReporteMensajeDAO;
import uco.doo.rugrats.uconnect.data.dao.RespuestaReportePublicacionDAO;
import uco.doo.rugrats.uconnect.data.dao.TipoEstadoDAO;
import uco.doo.rugrats.uconnect.data.dao.TipoEventoDAO;
import uco.doo.rugrats.uconnect.data.dao.TipoIdentificacionDAO;
import uco.doo.rugrats.uconnect.data.dao.TipoOrganizacionDAO;
import uco.doo.rugrats.uconnect.data.dao.TipoReaccionDAO;
import uco.doo.rugrats.uconnect.data.dao.factory.DAOFactory;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.AdministradorEstructuraPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.AdministradorOrganizacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.AgendaPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.CausaReportePostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ChatPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ComentarioPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.EstadoPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.EstructuraAdministradorEstructuraPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.EstructuraPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.EventoPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.GrupoPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.HistorialChatGrupoPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.HistorialLecturaPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.MensajePostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.OrganizacionAdministradorOrganizacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.OrganizacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.PaisPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ParticipanteGrupoPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ParticipantePostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.PersonaPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.PublicacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ReaccionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ReporteComentarioPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ReporteMensajePostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.ReportePublicacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.RespuestaReporteComentarioPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.RespuestaReporteMensajePostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.RespuestaReportePublicacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.TipoEstadoPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.TipoEventoPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.TipoIdentificacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.TipoOrganizacionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.TipoReaccionPostgreSqlDAO;
import uco.doo.rugrats.uconnect.utils.UtilSql;
import uco.doo.rugrats.uconnect.utils.messages.UconnectDataMessages;

public class PostgresSqlDaoFactory extends DAOFactory {

	private Connection connection;
	private static final HikariDataSource DATASOURCE;

	static {
		final String jdbcUrl = "jdbc:postgresql://mahmud.db.elephantsql.com:5432/chiwqhoc";
		final String usernmame = "chiwqhoc";
		final String password = "JnwYqKUX1N9Skfs1WfetoscUE431ULfU";
		try {
			final HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcUrl);
			config.setUsername(usernmame);
			config.setPassword(password);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

			DATASOURCE = new HikariDataSource(config);
			
		} catch (final HikariPool.PoolInitializationException exception) {
			throw UconnectDataException.create(UconnectDataMessages.PostgreSqlDAO.TECHNICAL_MESSAGE_DATASOURCE_HIKARI_EXCEPTION, UconnectDataMessages.PostgreSqlDAO.USER_MESSAGE_DATASOURCE, exception);
		} catch (final Exception exception) {
			throw UconnectDataException.create(UconnectDataMessages.PostgreSqlDAO.TECHNICAL_MESSAGE_DATASOURCE_EXCEPTION, UconnectDataMessages.PostgreSqlDAO.USER_MESSAGE_DATASOURCE, exception);
		}
	}

	public PostgresSqlDaoFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		try {
			connection = DATASOURCE.getConnection();
		} catch (final HikariPool.PoolInitializationException exception) {
			throw UconnectDataException.create(UconnectDataMessages.PostgreSqlDAO.TECHNICAL_MESSAGE_OPEN_CONNECTION_DS_HIKARI_EXCEPTION, UconnectDataMessages.PostgreSqlDAO.USER_MESSAGE_OPEN_CONNECTION_DS, exception);
		} catch (final SQLException exception) {
			throw UconnectDataException.create(UconnectDataMessages.PostgreSqlDAO.TECHNICAL_MESSAGE_OPEN_CONNECTION_DS_SQL_EXCEPTION, UconnectDataMessages.PostgreSqlDAO.USER_MESSAGE_OPEN_CONNECTION_DS, exception);
		} catch (final Exception exception) {
			throw UconnectDataException.create(UconnectDataMessages.PostgreSqlDAO.TECHNICAL_MESSAGE_OPEN_CONNECTION_DS_EXCEPTION, UconnectDataMessages.PostgreSqlDAO.USER_MESSAGE_OPEN_CONNECTION_DS, exception);
		}
	}

	@Override
	public void closeConnection() {
		UtilSql.closeConnection(connection);
	}

	@Override
	public void initTransaction() {
		if (UtilSql.connectionIsOpen(connection)) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException exception) {
				throw UconnectDataException.create(UconnectDataMessages.UtilSqlMessages.INIT_TRANSACTION_TECHNICAL_MESSAGE, UconnectDataMessages.UtilSqlMessages.INIT_TRANSACTION_USER_MESSAGE, exception);
			}
		}
	}

	@Override
	public void commitTransaction() {
		if (UtilSql.connectionIsOpen(connection)) {
			try {
				connection.commit();
			} catch (SQLException exception) {
				throw UconnectDataException.create(UconnectDataMessages.UtilSqlMessages.COMMIT_TRANSACTION_TECHNICAL_MESSAGE, UconnectDataMessages.UtilSqlMessages.COMMIT_TRANSACTION_USER_MESSAGE, exception);
			}
		}
	}

	@Override
	public void rollbackTransaction() {
		if (UtilSql.connectionIsOpen(connection)) {
			try {
				connection.rollback();
			} catch (SQLException exception) {
				throw UconnectDataException.create(UconnectDataMessages.UtilSqlMessages.ROLLBACK_TRANSACTION_TECHNICAL_MESSAGE, UconnectDataMessages.UtilSqlMessages.ROLLBACK_TRANSACTION_USER_MESSAGE, exception);
			}
		}
	}

	@Override
	public TipoEstadoDAO getTipoEstadoDAO() {
		return new TipoEstadoPostgreSqlDAO(connection);
	}

	@Override
	public EstadoDAO getEstadoDAO() {
		return new EstadoPostgreSqlDAO(connection);
	}

	@Override
	public AdministradorEstructuraDAO getAdministradorEstructuraDAO() {
		return new AdministradorEstructuraPostgreSqlDAO(connection);
	}

	@Override
	public AdministradorOrganizacionDAO getAdministradorOrganizacionDAO() {
		return new AdministradorOrganizacionPostgreSqlDAO(connection);
	}

	@Override
	public AgendaDAO getAgendaDAO() {
		return new AgendaPostgreSqlDAO(connection);
	}

	@Override
	public CausaReporteDAO getCausaReporteDAO() {
		return new CausaReportePostgreSqlDAO(connection);
	}

	@Override
	public ChatDAO getChatDAO() {
		return new ChatPostgreSqlDAO(connection);
	}

	@Override
	public ComentarioDAO getComentarioDAO() {
		return new ComentarioPostgreSqlDAO(connection);
	}

	@Override
	public EstructuraAdministradorEstructuraDAO getEstructuraAdministradorEstructuraDAO() {
		return new EstructuraAdministradorEstructuraPostgreSqlDAO(connection);
	}

	@Override
	public EstructuraDAO getEstructuraDAO() {
		return new EstructuraPostgreSqlDAO(connection);
	}

	@Override
	public EventoDAO getEventoDAO() {
		return new EventoPostgreSqlDAO(connection);
	}

	@Override
	public GrupoDAO getGrupoDAO() {
		return new GrupoPostgreSqlDAO(connection);
	}

	@Override
	public HistorialChatGrupoDAO getHistorialChatGrupoDAO() {
		return new HistorialChatGrupoPostgreSqlDAO(connection);
	}

	@Override
	public HistorialLecturaDAO getHistorialLecturaDAO() {
		return new HistorialLecturaPostgreSqlDAO(connection);
	}

	@Override
	public MensajeDAO getMensajeDAO() {
		return new MensajePostgreSqlDAO(connection);
	}

	@Override
	public OrganizacionAdministradorOrganizacionDAO getOrganizacionAdministradorOrganizacionDAO() {
		return new OrganizacionAdministradorOrganizacionPostgreSqlDAO(connection);
	}

	@Override
	public OrganizacionDAO getOrganizacionDAO() {
		return new OrganizacionPostgreSqlDAO(connection);
	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisPostgreSqlDAO(connection);
	}

	@Override
	public ParticipanteDAO getParticipanteDAO() {
		return new ParticipantePostgreSqlDAO(connection);
	}

	@Override
	public ParticipanteGrupoDAO getParticipanteGrupoDAO() {
		return new ParticipanteGrupoPostgreSqlDAO(connection);
	}

	@Override
	public PersonaDAO getPersonaDAO() {
		return new PersonaPostgreSqlDAO(connection);
	}

	@Override
	public PublicacionDAO getPublicacionDAO() {
		return new PublicacionPostgreSqlDAO(connection);
	}

	@Override
	public ReaccionDAO getReaccionDAO() {
		return new ReaccionPostgreSqlDAO(connection);
	}

	@Override
	public ReporteComentarioDAO getReporteComentarioDAO() {
		return new ReporteComentarioPostgreSqlDAO(connection);
	}

	@Override
	public ReporteMensajeDAO getReporteMensajeDAO() {
		return new ReporteMensajePostgreSqlDAO(connection);
	}

	@Override
	public ReportePublicacionDAO getReportePublicacionDAO() {
		return new ReportePublicacionPostgreSqlDAO(connection);
	}

	@Override
	public RespuestaReporteComentarioDAO getRespuestaReporteComentarioDAO() {
		return new RespuestaReporteComentarioPostgreSqlDAO(connection);
	}

	@Override
	public RespuestaReporteMensajeDAO getRespuestaReporteMensajeDAO() {
		return new RespuestaReporteMensajePostgreSqlDAO(connection);
	}

	@Override
	public RespuestaReportePublicacionDAO getRespuestaReportePublicacionDAO() {
		return new RespuestaReportePublicacionPostgreSqlDAO(connection);
	}

	@Override
	public TipoEventoDAO getTipoEventoDAO() {
		return new TipoEventoPostgreSqlDAO(connection);
	}

	@Override
	public TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return new TipoIdentificacionPostgreSqlDAO(connection);
	}

	@Override
	public TipoOrganizacionDAO getTipoOrganizacionDAO() {
		return new TipoOrganizacionPostgreSqlDAO(connection);
	}

	@Override
	public TipoReaccionDAO getTipoReaccionDAO() {
		return new TipoReaccionPostgreSqlDAO(connection);
	}
	
	
}
