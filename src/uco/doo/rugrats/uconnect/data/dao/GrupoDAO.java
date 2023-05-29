package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.GrupoEntity;

import java.util.List;
import java.util.UUID;

public interface GrupoDAO {
    void create(GrupoEntity entity);

    List<GrupoEntity> read(GrupoEntity entity);

    void update(GrupoEntity entity);

    void delete(UUID entity);
}
