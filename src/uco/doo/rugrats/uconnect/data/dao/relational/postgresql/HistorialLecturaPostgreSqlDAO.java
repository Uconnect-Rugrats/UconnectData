package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.HistorialLecturaDAO;
import uco.doo.rugrats.uconnect.entities.HistorialLecturaEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class HistorialLecturaPostgreSqlDAO implements HistorialLecturaDAO {
    public HistorialLecturaPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(HistorialLecturaEntity entity) {

    }

    @Override
    public List<HistorialLecturaEntity> read(HistorialLecturaEntity entity) {
        return null;
    }

    @Override
    public void update(HistorialLecturaEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
