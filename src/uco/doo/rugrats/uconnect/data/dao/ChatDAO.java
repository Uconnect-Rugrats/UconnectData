package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ChatEntity;

import java.util.List;
import java.util.UUID;

public interface ChatDAO {
    void create(ChatEntity entity);

    List<ChatEntity> read(ChatEntity entity);

    void update(ChatEntity entity);

    void delete(UUID entity);
}
