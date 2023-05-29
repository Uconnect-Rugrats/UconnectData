package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ReaccionEntity;

import java.util.List;
import java.util.UUID;

public interface ReaccionDAO {
    void create(ReaccionEntity entity);

    List<ReaccionEntity> read(ReaccionEntity entity);

    void update(ReaccionEntity entity);

    void delete(UUID entity);
}
