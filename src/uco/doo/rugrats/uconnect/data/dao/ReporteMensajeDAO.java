package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ReporteMensajeEntity;

import java.util.List;
import java.util.UUID;

public interface ReporteMensajeDAO {
    void create(ReporteMensajeEntity entity);

    List<ReporteMensajeEntity> read(ReporteMensajeEntity entity);

    void update(ReporteMensajeEntity entity);

    void delete(UUID entity);
}
