package uco.doo.rugrats.uconnect.data.dao.relational.sqlserver;

import uco.doo.rugrats.uconnect.data.dao.TipoEstadoDAO;
import uco.doo.rugrats.uconnect.entities.TipoEstadoEntity;

import java.sql.Connection;
import java.util.List;


public final class TipoEstadoSqlServerDAO implements TipoEstadoDAO {

	public TipoEstadoSqlServerDAO(final Connection connection) {
		//aaa
	}


	@Override
	public List<TipoEstadoEntity> read(TipoEstadoEntity entity) {
		return null;
	}

}
