package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.TipoEventoEntity;

import java.util.List;

public interface TipoEventoDAO {

    List<TipoEventoEntity> read(TipoEventoEntity entity);
}
