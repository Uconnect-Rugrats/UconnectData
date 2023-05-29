package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.RespuestaReportePublicacionEntity;

import java.util.List;
import java.util.UUID;

public interface RespuestaReportePublicacionDAO {
    void create(RespuestaReportePublicacionEntity entity);

    List<RespuestaReportePublicacionEntity> read(RespuestaReportePublicacionEntity entity);

    void update(RespuestaReportePublicacionEntity entity);

    void delete(UUID entity);
}
