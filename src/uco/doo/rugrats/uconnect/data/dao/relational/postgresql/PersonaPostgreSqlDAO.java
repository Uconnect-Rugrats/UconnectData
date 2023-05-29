package uco.doo.rugrats.uconnect.data.dao.relational.postgresql;

import uco.doo.rugrats.uconnect.data.dao.PersonaDAO;
import uco.doo.rugrats.uconnect.entities.PersonaEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class PersonaPostgreSqlDAO implements PersonaDAO {
    public PersonaPostgreSqlDAO(final Connection connection){

    }
    @Override
    public void create(PersonaEntity entity) {

    }

    @Override
    public List<PersonaEntity> read(PersonaEntity entity) {
        return null;
    }

    @Override
    public void update(PersonaEntity entity) {

    }

    @Override
    public void delete(UUID entity) {

    }
}
