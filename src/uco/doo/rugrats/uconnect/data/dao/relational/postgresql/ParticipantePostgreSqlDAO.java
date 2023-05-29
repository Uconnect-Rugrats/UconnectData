package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ParticipanteDAO;
import uco.doo.rugrats.uconnect.entities.ParticipanteEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ParticipantePostgreSqlDAO implements ParticipanteDAO {
    public ParticipantePostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ParticipanteEntity entity) {

    }

    @Override
    public List<ParticipanteEntity> read(ParticipanteEntity entity) {
        return null;
    }

    @Override
    public void update(ParticipanteEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
