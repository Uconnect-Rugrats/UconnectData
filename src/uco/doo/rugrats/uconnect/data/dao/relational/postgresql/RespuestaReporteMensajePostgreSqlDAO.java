package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.RespuestaReporteMensajeDAO;
import uco.doo.rugrats.uconnect.entities.RespuestaReporteMensajeEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class RespuestaReporteMensajePostgreSqlDAO implements RespuestaReporteMensajeDAO {
    public RespuestaReporteMensajePostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(RespuestaReporteMensajeEntity entity) {

    }

    @Override
    public List<RespuestaReporteMensajeEntity> read(RespuestaReporteMensajeEntity entity) {
        return null;
    }

    @Override
    public void update(RespuestaReporteMensajeEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
