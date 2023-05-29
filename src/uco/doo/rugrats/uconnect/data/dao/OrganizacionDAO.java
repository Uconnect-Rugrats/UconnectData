package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.OrganizacionEntity;

import java.util.List;
import java.util.UUID;

public interface OrganizacionDAO {
    void create(OrganizacionEntity entity);

    List<OrganizacionEntity> read(OrganizacionEntity entity);

    void update(OrganizacionEntity entity);

    void delete(UUID entity);
}
