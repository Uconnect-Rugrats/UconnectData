package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.AdministradorEstructuraDAO;
import uco.doo.rugrats.uconnect.entities.AdministradorEstructuraEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class AdministradorEstructuraPostgreSqlDAO implements AdministradorEstructuraDAO {
    public AdministradorEstructuraPostgreSqlDAO(final Connection connection){

    }

    @Override
    public void create(AdministradorEstructuraEntity entity) {

    }

    @Override
    public List<AdministradorEstructuraEntity> read(AdministradorEstructuraEntity entity) {
        return null;
    }

    @Override
    public void update(AdministradorEstructuraEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
