package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ReporteComentarioEntity;

import java.util.List;
import java.util.UUID;

public interface ReporteComentarioDAO {
    void create(ReporteComentarioEntity entity);

    List<ReporteComentarioEntity> read(ReporteComentarioEntity entity);

    void update(ReporteComentarioEntity entity);

    void delete(UUID entity);
}
