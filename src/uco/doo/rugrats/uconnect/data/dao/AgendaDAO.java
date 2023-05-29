package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.AgendaEntity;

import java.util.List;
import java.util.UUID;

public interface AgendaDAO {
    void create(AgendaEntity entity);

    List<AgendaEntity> read(AgendaEntity entity);

    void update(AgendaEntity entity);

    void delete(UUID entity);
}
