package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.EstructuraEntity;

import java.util.List;
import java.util.UUID;

public interface EstructuraDAO {
    void create(EstructuraEntity entity);

    List<EstructuraEntity> read(EstructuraEntity entity);

    void update(EstructuraEntity entity);

    void delete(UUID entity);
}
