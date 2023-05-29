package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ChatDAO;
import uco.doo.rugrats.uconnect.entities.ChatEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ChatPostgreSqlDAO implements ChatDAO {
    public ChatPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ChatEntity entity) {

    }

    @Override
    public List<ChatEntity> read(ChatEntity entity) {
        return null;
    }

    @Override
    public void update(ChatEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
