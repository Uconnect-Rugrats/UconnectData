package uco.doo.rugrats.uconnect.data.dao.factory.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
import uco.doo.rugrats.uconnect.utils.Messages;
import uco.doo.rugrats.uconnect.utils.UtilSql;

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
		} catch (Exception e) {
			throw e;
		}
	}

	public PostgresSqlDaoFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		try {
			connection = DATASOURCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch blockJnwYqKUX1N9Skfs1WfetoscUE431ULfU
			e.printStackTrace();
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
				var userMessage = Messages.UtilSqlMessages.BEGIN_TRANSACTION_USER_MESSAGE;
				var technicalMessage = Messages.UtilSqlMessages.BEGIN_TRANSACTION_TECHNICAL_MESSAGE;
				throw UconnectDataException.create(technicalMessage, userMessage, exception);
			}
		}
	}

	@Override
	public void commitTransaction() {
		if (UtilSql.connectionIsOpen(connection)) {
			try {
				connection.commit();
			} catch (SQLException e) {
				rollbackTransaction();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void rollbackTransaction() {
		if (UtilSql.connectionIsOpen(connection)) {
			try {
				connection.rollback();
			} catch (SQLException exception) {
				var userMessage = Messages.UtilSqlMessages.CANCEL_TRANSACTION_USER_MESSAGE;
				var technicalMessage = Messages.UtilSqlMessages.CANCEL_TRANSACTION_TECHNICAL_MESSAGE;
				throw UconnectDataException.create(technicalMessage, userMessage, exception);
			}
		}
	}

	public List<String> getTiposEstados() {
		List<String> tipos = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			initTransaction();
			ps = connection.prepareStatement(
					"SELECT \"TipoEstado\".nombre FROM \"Estado\" JOIN \"TipoEstado\" ON \"TipoEstado\".identificador = \"Estado\".tipo;");
			rs = ps.executeQuery();
			while (rs.next()) {
				tipos.add(rs.getString("nombre"));
			}
			commitTransaction();
		} catch (SQLException ex) {
			rollbackTransaction();
			ex.printStackTrace();
		} finally {
			UtilSql.closeResultSet(rs);
			UtilSql.closePreparedStatement(ps);
		}

		return tipos;
	}

	@Override
	public TipoEstadoDAO getTipoEstadoDAO() {
		return new TipoEstadoPostgreSqlDAO(connection);
	}

	@Override
	public EstadoDAO getEstadoDAO() {
		// TODO Auto-generated method stub
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

	public static void main(String[] args) {
		int i = 0;

		try {
			while (true) {
				DATASOURCE.getConnection();
				System.out.println("conexión " + i++);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(i);

	}

}
