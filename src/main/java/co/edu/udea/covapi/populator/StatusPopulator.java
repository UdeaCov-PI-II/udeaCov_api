package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.UserRequestDTO;
import co.edu.udea.covapi.dto.response.StatusResponseDTO;
import co.edu.udea.covapi.model.PermissionStatus;
import org.springframework.stereotype.Component;

@Component
public class StatusPopulator implements Populator<PermissionStatus, StatusResponseDTO, UserRequestDTO>{

    @Override
    public void populate(PermissionStatus source, StatusResponseDTO target){
        target.setStatusId(source.getStatusId());
        target.setDisplayName(source.getDisplayName());
        target.setAction(source.getAction());
    }

    @Override
    public void inverselyPopulate(UserRequestDTO source, PermissionStatus target){
        throw new UnsupportedOperationException("The method is not implemented yet");
    }
}
