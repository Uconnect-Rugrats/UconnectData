package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.EstadoDAO;
import uco.doo.rugrats.uconnect.entities.EstadoEntity;

import java.sql.Connection;
import java.util.List;


public final class EstadoPostgreSqlDAO implements EstadoDAO {

	public EstadoPostgreSqlDAO(final Connection connection){
		
	}

	@Override
	public List<EstadoEntity> read(EstadoEntity entity) {
		return null;
	}


}
