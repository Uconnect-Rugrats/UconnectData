package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.MensajeEntity;

import java.util.List;
import java.util.UUID;

public interface MensajeDAO {
    void create(MensajeEntity entity);

    List<MensajeEntity> read(MensajeEntity entity);

    void update(MensajeEntity entity);

    void delete(UUID entity);
}
