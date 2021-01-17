package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Unit;
import org.springframework.stereotype.Service;

@Service
public class DefaultUnitService extends DefaultBaseModelService<Unit> implements UnitService{

    @Override
    public String getCollectionName() {
        return "units/";
    }
}
