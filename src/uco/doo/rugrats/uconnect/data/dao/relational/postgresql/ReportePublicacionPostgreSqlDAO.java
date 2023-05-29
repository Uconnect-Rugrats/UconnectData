package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ReportePublicacionDAO;
import uco.doo.rugrats.uconnect.entities.ReportePublicacionEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ReportePublicacionPostgreSqlDAO implements ReportePublicacionDAO {
    public ReportePublicacionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ReportePublicacionEntity entity) {

    }

    @Override
    public List<ReportePublicacionEntity> read(ReportePublicacionEntity entity) {
        return null;
    }

    @Override
    public void update(ReportePublicacionEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
