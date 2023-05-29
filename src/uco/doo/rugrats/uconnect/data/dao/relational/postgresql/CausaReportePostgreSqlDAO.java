package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.CausaReporteDAO;
import uco.doo.rugrats.uconnect.entities.CausaReporteEntity;

import java.sql.Connection;
import java.util.List;

public final class CausaReportePostgreSqlDAO implements CausaReporteDAO{
    public CausaReportePostgreSqlDAO(final Connection connection){

    }


    @Override
    public List<CausaReporteEntity> read(CausaReporteEntity entity) {
        return null;
    }
}
