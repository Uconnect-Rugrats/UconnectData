package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.RespuestaReportePublicacionDAO;
import uco.doo.rugrats.uconnect.entities.RespuestaReportePublicacionEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class RespuestaReportePublicacionPostgreSqlDAO implements RespuestaReportePublicacionDAO {
    public RespuestaReportePublicacionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(RespuestaReportePublicacionEntity entity) {

    }

    @Override
    public List<RespuestaReportePublicacionEntity> read(RespuestaReportePublicacionEntity entity) {
        return null;
    }

    @Override
    public void update(RespuestaReportePublicacionEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
