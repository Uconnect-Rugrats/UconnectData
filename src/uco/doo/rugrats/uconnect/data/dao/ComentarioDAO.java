package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ComentarioEntity;

import java.util.List;
import java.util.UUID;

public interface ComentarioDAO {
    void create(ComentarioEntity entity);

    List<ComentarioEntity> read(ComentarioEntity entity);

    void update(ComentarioEntity entity);

    void delete(UUID entity);
}
