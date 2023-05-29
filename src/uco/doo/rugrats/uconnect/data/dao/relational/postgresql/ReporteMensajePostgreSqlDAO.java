package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ReporteMensajeDAO;
import uco.doo.rugrats.uconnect.entities.ReporteMensajeEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ReporteMensajePostgreSqlDAO implements ReporteMensajeDAO {
    public ReporteMensajePostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ReporteMensajeEntity entity) {

    }

    @Override
    public List<ReporteMensajeEntity> read(ReporteMensajeEntity entity) {
        return null;
    }

    @Override
    public void update(ReporteMensajeEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
