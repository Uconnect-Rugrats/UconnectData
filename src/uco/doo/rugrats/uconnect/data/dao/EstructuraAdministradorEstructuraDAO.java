package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.EstructuraAdministradorEstructuraEntity;

import java.util.List;
import java.util.UUID;

public interface EstructuraAdministradorEstructuraDAO {
    void create(EstructuraAdministradorEstructuraEntity entity);

    List<EstructuraAdministradorEstructuraEntity> read(EstructuraAdministradorEstructuraEntity entity);

    void update(EstructuraAdministradorEstructuraEntity entity);

    void delete(UUID entity);
}
