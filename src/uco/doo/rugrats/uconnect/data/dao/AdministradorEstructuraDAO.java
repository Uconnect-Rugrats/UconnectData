package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.AdministradorEstructuraEntity;

import java.util.List;
import java.util.UUID;

public interface AdministradorEstructuraDAO {
    void create(AdministradorEstructuraEntity entity);

    List<AdministradorEstructuraEntity> read(AdministradorEstructuraEntity entity);

    void update(AdministradorEstructuraEntity entity);

    void delete(UUID entity);
}
