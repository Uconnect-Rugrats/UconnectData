package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.ReportePublicacionEntity;

import java.util.List;
import java.util.UUID;

public interface ReportePublicacionDAO {
    void create(ReportePublicacionEntity entity);

    List<ReportePublicacionEntity> read(ReportePublicacionEntity entity);

    void update(ReportePublicacionEntity entity);

    void delete(UUID entity);
}
