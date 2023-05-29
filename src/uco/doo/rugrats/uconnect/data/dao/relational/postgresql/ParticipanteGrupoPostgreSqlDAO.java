package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ParticipanteGrupoDAO;
import uco.doo.rugrats.uconnect.entities.ParticipanteGrupoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ParticipanteGrupoPostgreSqlDAO implements ParticipanteGrupoDAO {
    public ParticipanteGrupoPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ParticipanteGrupoEntity entity) {

    }

    @Override
    public List<ParticipanteGrupoEntity> read(ParticipanteGrupoEntity entity) {
        return null;
    }

    @Override
    public void update(ParticipanteGrupoEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
