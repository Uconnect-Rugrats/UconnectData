package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.PublicacionDAO;
import uco.doo.rugrats.uconnect.entities.PublicacionEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class PublicacionPostgreSqlDAO implements PublicacionDAO {
    public PublicacionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(PublicacionEntity entity) {

    }

    @Override
    public List<PublicacionEntity> read(PublicacionEntity entity) {
        return null;
    }

    @Override
    public void update(PublicacionEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
