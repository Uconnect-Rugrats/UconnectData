package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.OrganizacionDAO;
import uco.doo.rugrats.uconnect.entities.OrganizacionEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class OrganizacionPostgreSqlDAO implements OrganizacionDAO {
    public OrganizacionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(OrganizacionEntity entity) {

    }

    @Override
    public List<OrganizacionEntity> read(OrganizacionEntity entity) {
        return null;
    }

    @Override
    public void update(OrganizacionEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
