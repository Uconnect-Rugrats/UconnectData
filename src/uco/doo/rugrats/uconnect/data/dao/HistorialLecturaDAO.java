package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.HistorialLecturaEntity;

import java.util.List;
import java.util.UUID;

public interface HistorialLecturaDAO {
    void create(HistorialLecturaEntity entity);

    List<HistorialLecturaEntity> read(HistorialLecturaEntity entity);

    void update(HistorialLecturaEntity entity);

    void delete(UUID entity);
}
