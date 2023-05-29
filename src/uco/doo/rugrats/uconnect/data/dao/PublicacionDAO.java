package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.PublicacionEntity;

import java.util.List;
import java.util.UUID;

public interface PublicacionDAO {
    void create(PublicacionEntity entity);

    List<PublicacionEntity> read(PublicacionEntity entity);

    void update(PublicacionEntity entity);

    void delete(UUID entity);
}
