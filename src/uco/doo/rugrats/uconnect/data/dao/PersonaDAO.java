package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.PersonaEntity;

import java.util.List;
import java.util.UUID;

public interface PersonaDAO {
    void create(PersonaEntity entity);

    List<PersonaEntity> read(PersonaEntity entity);

    void update(PersonaEntity entity);

    void delete(UUID entity);
}
