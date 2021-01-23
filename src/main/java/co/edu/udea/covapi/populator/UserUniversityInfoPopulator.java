package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.response.UserResponseDTO;
import co.edu.udea.covapi.dto.request.UserUniversityInfoDTO;
import co.edu.udea.covapi.model.UserUniversityInfo;
import co.edu.udea.covapi.service.LocationService;
import co.edu.udea.covapi.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class UserUniversityInfoPopulator implements Populator<UserUniversityInfo, UserResponseDTO, UserUniversityInfoDTO> {

    @Autowired
    private UnitService unitService;

    @Autowired
    private LocationService locationService;

    @Override
    public void populate(UserUniversityInfo source, UserResponseDTO target){
        throw new UnsupportedOperationException("The method is not implemented yet");
    }

    @Override
    public void inverselyPopulate(UserUniversityInfoDTO source, UserUniversityInfo target) throws ExecutionException, InterruptedException {
        target.setUniversityRelation(source.getUniversityRelation());
        target.setDetailUniversityRelation(source.getDetailUniversityRelation());
        target.setBuildingAndOffice(source.getBuildingAndOffice());
        target.setBelongToExtensionProject(source.isBelongToExtensionProject());
        target.setExtensionProjectName(source.getExtensionProjectName());
        target.setTransportationMode(source.getTransportationMode());
        target.setLocation(locationService.getReference(source.getLocationId()));
        target.setUnit(unitService.getReference(source.getUnitId()));
        target.setOccupation(source.getOccupation());
    }
}
