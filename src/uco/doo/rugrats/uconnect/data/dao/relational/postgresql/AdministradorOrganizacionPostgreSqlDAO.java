package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.AdministradorOrganizacionDAO;
import uco.doo.rugrats.uconnect.entities.AdministradorOrganizacionEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class AdministradorOrganizacionPostgreSqlDAO implements AdministradorOrganizacionDAO {
    public AdministradorOrganizacionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(AdministradorOrganizacionEntity entity) {

    }

    @Override
    public List<AdministradorOrganizacionEntity> read(AdministradorOrganizacionEntity entity) {
        return null;
    }

    @Override
    public void update(AdministradorOrganizacionEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
