package uco.doo.rugrats.uconnect.data.dao.factory.relational.sqlserver;

import uco.doo.rugrats.uconnect.data.dao.*;
import uco.doo.rugrats.uconnect.data.dao.factory.DAOFactory;
import uco.doo.rugrats.uconnect.data.dao.relational.sqlserver.EstadoSqlServerDAO;
import uco.doo.rugrats.uconnect.data.dao.relational.sqlserver.TipoEstadoSqlServerDAO;

import java.sql.Connection;


public final class SqlServerDaoFactory extends DAOFactory{

	private Connection connection;

	public SqlServerDaoFactory() {
		openConnection();
	}

	@Override
	protected final void openConnection() {

		//	:C

	}

	@Override
	public final void closeConnection() {

		//:C
	}

	@Override
	public final void initTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public final void commitTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public final void rollbackTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public final TipoEstadoDAO getTipoEstadoDAO() {

		return new TipoEstadoSqlServerDAO(connection);
	}

	@Override
	public EstadoDAO getEstadoDAO() {
		// TODO Auto-generated method stub
		return new EstadoSqlServerDAO(connection);
	}

	@Override
	public AdministradorEstructuraDAO getAdministradorEstructuraDAO() {
		return null;
	}

	@Override
	public AdministradorOrganizacionDAO getAdministradorOrganizacionDAO() {
		return null;
	}

	@Override
	public AgendaDAO getAgendaDAO() {
		return null;
	}

	@Override
	public CausaReporteDAO getCausaReporteDAO() {
		return null;
	}

	@Override
	public ChatDAO getChatDAO() {
		return null;
	}

	@Override
	public ComentarioDAO getComentarioDAO() {
		return null;
	}

	@Override
	public EstructuraAdministradorEstructuraDAO getEstructuraAdministradorEstructuraDAO() {
		return null;
	}

	@Override
	public EstructuraDAO getEstructuraDAO() {
		return null;
	}

	@Override
	public EventoDAO getEventoDAO() {
		return null;
	}

	@Override
	public GrupoDAO getGrupoDAO() {
		return null;
	}

	@Override
	public HistorialChatGrupoDAO getHistorialChatGrupoDAO() {
		return null;
	}

	@Override
	public HistorialLecturaDAO getHistorialLecturaDAO() {
		return null;
	}

	@Override
	public MensajeDAO getMensajeDAO() {
		return null;
	}

	@Override
	public OrganizacionAdministradorOrganizacionDAO getOrganizacionAdministradorOrganizacionDAO() {
		return null;
	}

	@Override
	public OrganizacionDAO getOrganizacionDAO() {
		return null;
	}

	@Override
	public PaisDAO getPaisDAO() {
		return null;
	}

	@Override
	public ParticipanteDAO getParticipanteDAO() {
		return null;
	}

	@Override
	public ParticipanteGrupoDAO getParticipanteGrupoDAO() {
		return null;
	}

	@Override
	public PersonaDAO getPersonaDAO() {
		return null;
	}

	@Override
	public PublicacionDAO getPublicacionDAO() {
		return null;
	}

	@Override
	public ReaccionDAO getReaccionDAO() {
		return null;
	}

	@Override
	public ReporteComentarioDAO getReporteComentarioDAO() {
		return null;
	}

	@Override
	public ReporteMensajeDAO getReporteMensajeDAO() {
		return null;
	}

	@Override
	public ReportePublicacionDAO getReportePublicacionDAO() {
		return null;
	}

	@Override
	public RespuestaReporteComentarioDAO getRespuestaReporteComentarioDAO() {
		return null;
	}

	@Override
	public RespuestaReporteMensajeDAO getRespuestaReporteMensajeDAO() {
		return null;
	}

	@Override
	public RespuestaReportePublicacionDAO getRespuestaReportePublicacionDAO() {
		return null;
	}

	@Override
	public TipoEventoDAO getTipoEventoDAO() {
		return null;
	}

	@Override
	public TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return null;
	}

	@Override
	public TipoOrganizacionDAO getTipoOrganizacionDAO() {
		return null;
	}

	@Override
	public TipoReaccionDAO getTipoReaccionDAO() {
		return null;
	}


}
