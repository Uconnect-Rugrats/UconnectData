package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.PaisDAO;
import uco.doo.rugrats.uconnect.entities.PaisEntity;

import java.sql.Connection;
import java.util.List;

public final class PaisPostgreSqlDAO implements PaisDAO {
    public PaisPostgreSqlDAO(final Connection connection){

    }

    @Override
    public List<PaisEntity> read(PaisEntity entity) {
        return null;
    }
}
