package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.UserHealthInfoDTO;
import co.edu.udea.covapi.dto.response.UserResponseDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.UserHealthInfo;
import org.springframework.stereotype.Component;

@Component
public class UserHealthInfoPopulator implements Populator<UserHealthInfo, UserResponseDTO, UserHealthInfoDTO> {

    @Override
    public void populate(UserHealthInfo source, UserResponseDTO target){
        throw new UnsupportedOperationException("The method is not implemented yet");
    }

    @Override
    public void inverselyPopulate(UserHealthInfoDTO source, UserHealthInfo target) throws PopulatorException{
        target.setWeight(source.getWeight());
        target.setHeight(source.getHeight());
        target.setHasHighBloodPressure(source.isHasHighBloodPressure());
        target.setHasHeartDisease(source.isHasHeartDisease());
        target.setHasHighCholesterol(source.isHasHighCholesterol());
        target.setHaskidneyDisease(source.isHaskidneyDisease());
        target.setHasDiabetes(source.isHasDiabetes());
        target.setHasEPOC(source.isHasEPOC());
        target.setHasAsthma(source.isHasAsthma());
        target.setHasAlterationImmunityDisease(source.isHasAlterationImmunityDisease());
        target.setHasCancer(source.isHasCancer());
        target.setUseOralSteroids(source.isUseOralSteroids());
        target.setHasHepaticDisease(source.isHasHepaticDisease());
        target.setHasObesity(source.isHasObesity());
        target.setSmoker(source.isSmoker());
        target.setHasStrangeDisease(source.isHasStrangeDisease());
        target.setPregnant(source.isPregnant());
        target.setHasChildBirthRecently(source.isHasChildBirthRecently());
        target.setHasPermanentDisability(source.getHasPermanentDisability());
        target.setBloodType(source.getBloodType());
        target.setHasRoomates(source.isHasRoomates());
        target.setRoomatesConditions(source.getRoomatesConditions());
    }
}
