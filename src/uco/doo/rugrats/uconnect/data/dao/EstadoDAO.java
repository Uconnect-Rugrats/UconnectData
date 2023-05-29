package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.EstadoEntity;

import java.util.List;

public interface EstadoDAO {

	List<EstadoEntity> read(EstadoEntity entity);
}
