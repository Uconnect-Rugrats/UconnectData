package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.GrupoDAO;
import uco.doo.rugrats.uconnect.entities.GrupoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class GrupoPostgreSqlDAO implements GrupoDAO {
    public GrupoPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(GrupoEntity entity) {

    }

    @Override
    public List<GrupoEntity> read(GrupoEntity entity) {
        return null;
    }

    @Override
    public void update(GrupoEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
