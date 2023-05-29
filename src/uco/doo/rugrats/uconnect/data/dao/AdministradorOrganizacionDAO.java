package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.AdministradorOrganizacionEntity;

import java.util.List;
import java.util.UUID;

public interface AdministradorOrganizacionDAO {
    void create(AdministradorOrganizacionEntity entity);

    List<AdministradorOrganizacionEntity> read(AdministradorOrganizacionEntity entity);

    void update(AdministradorOrganizacionEntity entity);

    void delete(UUID entity);
}
