package uco.doo.rugrats.uconnect.data.dao;

import uco.doo.rugrats.uconnect.entities.CausaReporteEntity;

import java.util.List;


public interface CausaReporteDAO {

    List<CausaReporteEntity> read(CausaReporteEntity entity);
}
