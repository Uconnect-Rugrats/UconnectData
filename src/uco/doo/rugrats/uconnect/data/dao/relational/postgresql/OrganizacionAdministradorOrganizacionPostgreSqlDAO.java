package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.OrganizacionAdministradorOrganizacionDAO;
import uco.doo.rugrats.uconnect.entities.OrganizacionAdministradorOrganizacionEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class OrganizacionAdministradorOrganizacionPostgreSqlDAO implements OrganizacionAdministradorOrganizacionDAO {
    public OrganizacionAdministradorOrganizacionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(OrganizacionAdministradorOrganizacionEntity entity) {

    }

    @Override
    public List<OrganizacionAdministradorOrganizacionEntity> read(OrganizacionAdministradorOrganizacionEntity entity) {
        return null;
    }

    @Override
    public void update(OrganizacionAdministradorOrganizacionEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
