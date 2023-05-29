package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ParticipanteEntity;

import java.util.List;
import java.util.UUID;

public interface ParticipanteDAO {
    void create(ParticipanteEntity entity);

    List<ParticipanteEntity> read(ParticipanteEntity entity);

    void update(ParticipanteEntity entity);

    void delete(UUID entity);
}
