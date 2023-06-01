package uco.doo.rugrats.uconnect.data.dao.relational.sqlserver;

import uco.doo.rugrats.uconnect.data.dao.EstadoDAO;
import uco.doo.rugrats.uconnect.entities.EstadoEntity;

import java.sql.Connection;
import java.util.List;


public final class EstadoSqlServerDAO implements EstadoDAO {

	public EstadoSqlServerDAO(final Connection connection){
		
	}


	@Override
	public List<EstadoEntity> read(EstadoEntity entity) {
		return null;
	}


}
