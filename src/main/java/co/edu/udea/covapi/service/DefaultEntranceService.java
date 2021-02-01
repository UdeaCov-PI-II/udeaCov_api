package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.Entrance;
import org.springframework.stereotype.Service;

@Service
public class DefaultEntranceService extends DefaultBaseModelService<Entrance> implements EntranceService {
    @Override
    public String getCollectionName() {
        return "entrances/";
    }
}
