package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.HistorialChatGrupoDAO;
import uco.doo.rugrats.uconnect.entities.HistorialChatGrupoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class HistorialChatGrupoPostgreSqlDAO implements HistorialChatGrupoDAO {
    public HistorialChatGrupoPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(HistorialChatGrupoEntity entity) {

    }

    @Override
    public List<HistorialChatGrupoEntity> read(HistorialChatGrupoEntity entity) {
        return null;
    }

    @Override
    public void delete(UUID entity) {

    }
}
