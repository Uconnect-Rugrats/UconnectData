package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.TipoEstadoDAO;
import uco.doo.rugrats.uconnect.entities.TipoEstadoEntity;

import java.sql.Connection;
import java.util.List;


public final class TipoEstadoPostgreSqlDAO implements TipoEstadoDAO {

	public TipoEstadoPostgreSqlDAO(final Connection connection) {

	}


	@Override
	public List<TipoEstadoEntity> read(TipoEstadoEntity entity) {
		return null;
	}

}
