package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.response.LocationResponseDTO;
import co.edu.udea.covapi.dto.response.PermissionResponseDTO;
import co.edu.udea.covapi.model.Location;
import org.springframework.stereotype.Component;


@Component
public class LocationPopulator implements Populator<Location, LocationResponseDTO, PermissionResponseDTO> {
    @Override
    public void populate(Location source, LocationResponseDTO target){
        target.setId(source.getModelId());
        target.setName(source.getName());

    }

    @Override
    public void inverselyPopulate(PermissionResponseDTO source, Location target) {
        throw new UnsupportedOperationException("The method is not implemented yet");
    }
}
