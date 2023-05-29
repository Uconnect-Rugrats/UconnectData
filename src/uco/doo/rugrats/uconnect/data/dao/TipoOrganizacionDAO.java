package uco.doo.rugrats.uconnect.data.dao;


import uco.doo.rugrats.uconnect.entities.TipoOrganizacionEntity;

import java.util.List;

public interface TipoOrganizacionDAO {

    List<TipoOrganizacionEntity> read(TipoOrganizacionEntity entity);

}
