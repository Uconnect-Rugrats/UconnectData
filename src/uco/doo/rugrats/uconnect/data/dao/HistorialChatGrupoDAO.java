package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.HistorialChatGrupoEntity;

import java.util.List;
import java.util.UUID;

public interface HistorialChatGrupoDAO {
    void create(HistorialChatGrupoEntity entity);

    List<HistorialChatGrupoEntity> read(HistorialChatGrupoEntity entity);


    void delete(UUID entity);
}
