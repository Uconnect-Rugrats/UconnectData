package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.TipoEventoDAO;
import uco.doo.rugrats.uconnect.entities.TipoEventoEntity;

import java.sql.Connection;
import java.util.List;

public final class TipoEventoPostgreSqlDAO implements TipoEventoDAO {
    public TipoEventoPostgreSqlDAO(final Connection connection){

    }

    @Override
    public List<TipoEventoEntity> read(TipoEventoEntity entity) {
        return null;
    }
}
