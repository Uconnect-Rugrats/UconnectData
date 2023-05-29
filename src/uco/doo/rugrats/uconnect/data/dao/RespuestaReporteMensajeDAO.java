package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.RespuestaReporteMensajeEntity;

import java.util.List;
import java.util.UUID;

public interface RespuestaReporteMensajeDAO {
    void create(RespuestaReporteMensajeEntity entity);

    List<RespuestaReporteMensajeEntity> read(RespuestaReporteMensajeEntity entity);

    void update(RespuestaReporteMensajeEntity entity);

    void delete(UUID entity);
}
