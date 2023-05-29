package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.ReaccionDAO;
import uco.doo.rugrats.uconnect.entities.ReaccionEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class ReaccionPostgreSqlDAO implements ReaccionDAO {
    public ReaccionPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(ReaccionEntity entity) {

    }

    @Override
    public List<ReaccionEntity> read(ReaccionEntity entity) {
        return null;
    }

    @Override
    public void update(ReaccionEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
