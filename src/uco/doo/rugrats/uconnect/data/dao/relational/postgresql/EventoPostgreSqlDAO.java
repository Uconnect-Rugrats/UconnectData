package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.EventoDAO;
import uco.doo.rugrats.uconnect.entities.EventoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class EventoPostgreSqlDAO implements EventoDAO {
    public EventoPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(EventoEntity entity) {

    }

    @Override
    public List<EventoEntity> read(EventoEntity entity) {
        return null;
    }

    @Override
    public void update(EventoEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
