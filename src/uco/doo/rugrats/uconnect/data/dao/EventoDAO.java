package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.EventoEntity;

import java.util.List;
import java.util.UUID;

public interface EventoDAO {
    void create(EventoEntity entity);

    List<EventoEntity> read(EventoEntity entity);

    void update(EventoEntity entity);

    void delete(UUID entity);
}
