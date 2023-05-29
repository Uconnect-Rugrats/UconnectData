package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.EstructuraDAO;
import uco.doo.rugrats.uconnect.entities.EstructuraEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class EstructuraPostgreSqlDAO implements EstructuraDAO {
    public EstructuraPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(EstructuraEntity entity) {

    }

    @Override
    public List<EstructuraEntity> read(EstructuraEntity entity) {
        return null;
    }

    @Override
    public void update(EstructuraEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
