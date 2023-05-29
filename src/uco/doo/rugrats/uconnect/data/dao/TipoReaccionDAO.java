package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.TipoReaccionEntity;

import java.util.List;

public interface TipoReaccionDAO {

    List<TipoReaccionEntity> read(TipoReaccionEntity entity);

}
