package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Location;
import org.springframework.stereotype.Service;

@Service
public class DefaultLocationService extends DefaultBaseModelService<Location> implements LocationService{
    @Override
    public String getCollectionName() {
        return "locations/";
    }
}
