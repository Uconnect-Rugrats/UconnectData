package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.TipoIdentificacionDAO;
import uco.doo.rugrats.uconnect.entities.TipoIdentificacionEntity;

import java.sql.Connection;
import java.util.List;

public final class TipoIdentificacionPostgreSqlDAO implements TipoIdentificacionDAO {
    public TipoIdentificacionPostgreSqlDAO(final Connection connection){

    }

    @Override
    public List<TipoIdentificacionEntity> read(TipoIdentificacionEntity entity) {
        return null;
    }
}
