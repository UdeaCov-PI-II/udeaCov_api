package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.response.UserInfoResponseDTO;
import co.edu.udea.covapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserInfoPopulator implements Populator<User, UserInfoResponseDTO, UserInfoResponseDTO> {

    @Override
    public void populate(User source, UserInfoResponseDTO target){
        target.setId(source.getModelId());
        target.setFullName(source.getFullName());
        target.setDocumentType(source.getDocumentType());
        target.setDocumentNumber(source.getDocumentNumber());
    }

    @Override
    public void inverselyPopulate(UserInfoResponseDTO source, User target) {
        throw new UnsupportedOperationException("The method is not implemented yet");

    }
}
