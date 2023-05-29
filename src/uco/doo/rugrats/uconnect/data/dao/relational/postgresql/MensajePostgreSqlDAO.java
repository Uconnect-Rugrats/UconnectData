package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.MensajeDAO;
import uco.doo.rugrats.uconnect.entities.MensajeEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class MensajePostgreSqlDAO implements MensajeDAO {
    public MensajePostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(MensajeEntity entity) {

    }

    @Override
    public List<MensajeEntity> read(MensajeEntity entity) {
        return null;
    }

    @Override
    public void update(MensajeEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
