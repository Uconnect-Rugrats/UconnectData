package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.PaisEntity;

import java.util.List;

public interface PaisDAO {
    List<PaisEntity> read(PaisEntity entity);

}
