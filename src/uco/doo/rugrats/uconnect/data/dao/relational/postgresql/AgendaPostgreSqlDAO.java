package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.AgendaDAO;
import uco.doo.rugrats.uconnect.entities.AgendaEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class AgendaPostgreSqlDAO implements AgendaDAO {
    public AgendaPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(AgendaEntity entity) {

    }

    @Override
    public List<AgendaEntity> read(AgendaEntity entity) {
        return null;
    }

    @Override
    public void update(AgendaEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
