package uco.doo.rugrats.uconnect.data.dao.factory;


import uco.doo.rugrats.uconnect.data.dao.*;
import uco.doo.rugrats.uconnect.data.dao.factory.relational.postgresql.PostgresSqlDaoFactory;
import uco.doo.rugrats.uconnect.data.dao.factory.relational.sqlserver.SqlServerDaoFactory;

public abstract class DAOFactory {
	
	public static DAOFactory getFactory(final Factory factory) {
		
		DAOFactory daoFactory;
		switch (factory) {			
			case SQLSERVER: {
				daoFactory = new SqlServerDaoFactory();
				break;
			}
			case POSTGRESQL: {
				daoFactory = new PostgresSqlDaoFactory();
				break;
			}
			default: {
				throw new IllegalArgumentException("Unexpected value: " + factory);
			}
		}
		
		return daoFactory;
	}
	
	protected abstract void openConnection();
	
	public abstract void closeConnection();
	
	public abstract void initTransaction();
	
	public abstract void commitTransaction();
	
	public abstract void rollbackTransaction();
	
	public abstract TipoEstadoDAO getTipoEstadoDAO();
	
	public abstract EstadoDAO getEstadoDAO();
	public abstract AdministradorEstructuraDAO getAdministradorEstructuraDAO();
	public abstract AdministradorOrganizacionDAO getAdministradorOrganizacionDAO();
	public abstract AgendaDAO getAgendaDAO();
	public abstract CausaReporteDAO getCausaReporteDAO();
	public abstract ChatDAO getChatDAO();
	public abstract ComentarioDAO getComentarioDAO();
	public abstract EstructuraAdministradorEstructuraDAO getEstructuraAdministradorEstructuraDAO();
	public abstract EstructuraDAO getEstructuraDAO();
	public abstract EventoDAO getEventoDAO();
	public abstract GrupoDAO getGrupoDAO();
	public abstract HistorialChatGrupoDAO getHistorialChatGrupoDAO();
	public abstract HistorialLecturaDAO getHistorialLecturaDAO();
	public abstract MensajeDAO getMensajeDAO();
	public abstract OrganizacionAdministradorOrganizacionDAO getOrganizacionAdministradorOrganizacionDAO();
	public abstract OrganizacionDAO getOrganizacionDAO();
	public abstract PaisDAO getPaisDAO();
	public abstract ParticipanteDAO getParticipanteDAO();
	public abstract ParticipanteGrupoDAO getParticipanteGrupoDAO();
	public abstract PersonaDAO getPersonaDAO();
	public abstract PublicacionDAO getPublicacionDAO();
	public abstract ReaccionDAO getReaccionDAO();
	public abstract ReporteComentarioDAO getReporteComentarioDAO();
	public abstract ReporteMensajeDAO getReporteMensajeDAO();
	public abstract ReportePublicacionDAO getReportePublicacionDAO();
	public abstract RespuestaReporteComentarioDAO getRespuestaReporteComentarioDAO();
	public abstract RespuestaReporteMensajeDAO getRespuestaReporteMensajeDAO();
	public abstract RespuestaReportePublicacionDAO getRespuestaReportePublicacionDAO();
	public abstract TipoEventoDAO getTipoEventoDAO();
	public abstract TipoIdentificacionDAO getTipoIdentificacionDAO();
	public abstract TipoOrganizacionDAO getTipoOrganizacionDAO();
	public abstract TipoReaccionDAO getTipoReaccionDAO();
	
}
