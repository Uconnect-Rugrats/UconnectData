package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ParticipanteGrupoEntity;

import java.util.List;
import java.util.UUID;

public interface ParticipanteGrupoDAO {
    void create(ParticipanteGrupoEntity entity);

    List<ParticipanteGrupoEntity> read(ParticipanteGrupoEntity entity);

    void update(ParticipanteGrupoEntity entity);

    void delete(UUID entity);
}
