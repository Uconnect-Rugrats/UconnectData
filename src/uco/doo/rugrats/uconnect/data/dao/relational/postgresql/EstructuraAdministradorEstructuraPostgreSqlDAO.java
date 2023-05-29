package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.EstructuraAdministradorEstructuraDAO;
import uco.doo.rugrats.uconnect.entities.EstructuraAdministradorEstructuraEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class EstructuraAdministradorEstructuraPostgreSqlDAO implements EstructuraAdministradorEstructuraDAO {
    public EstructuraAdministradorEstructuraPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(EstructuraAdministradorEstructuraEntity entity) {

    }

    @Override
    public List<EstructuraAdministradorEstructuraEntity> read(EstructuraAdministradorEstructuraEntity entity) {
        return null;
    }

    @Override
    public void update(EstructuraAdministradorEstructuraEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
