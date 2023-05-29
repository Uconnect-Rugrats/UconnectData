package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.RespuestaReporteComentarioDAO;
import uco.doo.rugrats.uconnect.entities.RespuestaReporteComentarioEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class RespuestaReporteComentarioPostgreSqlDAO implements RespuestaReporteComentarioDAO {
    public RespuestaReporteComentarioPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(RespuestaReporteComentarioEntity entity) {

    }

    @Override
    public List<RespuestaReporteComentarioEntity> read(RespuestaReporteComentarioEntity entity) {
        return null;
    }

    @Override
    public void update(RespuestaReporteComentarioEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
