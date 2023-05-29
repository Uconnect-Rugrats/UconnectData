package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.TipoReaccionDAO;
import uco.doo.rugrats.uconnect.entities.TipoReaccionEntity;

import java.sql.Connection;
import java.util.List;

public final class TipoReaccionPostgreSqlDAO implements TipoReaccionDAO {
    public TipoReaccionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public List<TipoReaccionEntity> read(TipoReaccionEntity entity) {
        return null;
    }
}
