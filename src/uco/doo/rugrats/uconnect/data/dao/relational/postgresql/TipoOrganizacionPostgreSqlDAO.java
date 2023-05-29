package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.TipoOrganizacionDAO;
import uco.doo.rugrats.uconnect.entities.TipoOrganizacionEntity;

import java.sql.Connection;
import java.util.List;

public final class TipoOrganizacionPostgreSqlDAO implements TipoOrganizacionDAO {
    public TipoOrganizacionPostgreSqlDAO(final Connection connection){

    }

    @Override
    public List<TipoOrganizacionEntity> read(TipoOrganizacionEntity entity) {
        return null;
    }
}
