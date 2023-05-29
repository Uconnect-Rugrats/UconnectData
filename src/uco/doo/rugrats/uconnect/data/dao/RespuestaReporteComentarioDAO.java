package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.RespuestaReporteComentarioEntity;

import java.util.List;
import java.util.UUID;

public interface RespuestaReporteComentarioDAO {
    void create(RespuestaReporteComentarioEntity entity);

    List<RespuestaReporteComentarioEntity> read(RespuestaReporteComentarioEntity entity);

    void update(RespuestaReporteComentarioEntity entity);

    void delete(UUID entity);
}
