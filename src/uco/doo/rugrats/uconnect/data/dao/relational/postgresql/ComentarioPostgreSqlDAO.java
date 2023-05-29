package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ComentarioDAO;
import uco.doo.rugrats.uconnect.entities.ComentarioEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ComentarioPostgreSqlDAO implements ComentarioDAO {
    public ComentarioPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ComentarioEntity entity) {

    }

    @Override
    public List<ComentarioEntity> read(ComentarioEntity entity) {
        return null;
    }

    @Override
    public void update(ComentarioEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
