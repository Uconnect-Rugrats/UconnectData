package uco.doo.rugrats.uconnect.data.dao.factory.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectDataException;
import uco.doo.rugrats.uconnect.data.dao.*;
import uco.doo.rugrats.uconnect.data.dao.factory.DAOFactory;
import uco.doo.rugrats.uconnect.data.dao.relational.postgresql.*;
import uco.doo.rugrats.uconnect.utils.Messages;
import uco.doo.rugrats.uconnect.utils.UtilSql;


public class PostgresSqlDaoFactory extends DAOFactory {

	private static Connection connection;
	private static String JDBCURL = "jdbc:postgresql://mahmud.db.elephantsql.com:5432/chiwqhoc";
	private static String USERNAME = "chiwqhoc";
	private static String PASSWORD = "JnwYqKUX1N9Skfs1WfetoscUE431ULfU";	
	
	public PostgresSqlDaoFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		connection = UtilSql.openConnection(JDBCURL, USERNAME, PASSWORD);
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
            }  catch (SQLException exception) {
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
			ps = connection.prepareStatement("SELECT \"TipoEstado\".nombre FROM \"Estado\" JOIN \"TipoEstado\" ON \"TipoEstado\".identificador = \"Estado\".tipo;");
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


}
