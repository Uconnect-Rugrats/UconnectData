package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.OrganizacionAdministradorOrganizacionEntity;

import java.util.List;
import java.util.UUID;

public interface OrganizacionAdministradorOrganizacionDAO {
    void create(OrganizacionAdministradorOrganizacionEntity entity);

    List<OrganizacionAdministradorOrganizacionEntity> read(OrganizacionAdministradorOrganizacionEntity entity);

    void update(OrganizacionAdministradorOrganizacionEntity entity);

    void delete(UUID entity);
}
