package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ReporteComentarioDAO;
import uco.doo.rugrats.uconnect.entities.ReporteComentarioEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ReporteComentarioPostgreSqlDAO implements ReporteComentarioDAO {
    public ReporteComentarioPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ReporteComentarioEntity entity) {

    }

    @Override
    public List<ReporteComentarioEntity> read(ReporteComentarioEntity entity) {
        return null;
    }

    @Override
    public void update(ReporteComentarioEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
