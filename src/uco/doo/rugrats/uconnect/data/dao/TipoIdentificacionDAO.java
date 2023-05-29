package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.TipoIdentificacionEntity;

import java.util.List;

public interface TipoIdentificacionDAO {

    List<TipoIdentificacionEntity> read(TipoIdentificacionEntity entity);

}
