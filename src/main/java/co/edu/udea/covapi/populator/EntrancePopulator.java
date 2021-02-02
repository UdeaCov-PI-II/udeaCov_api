package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.request.EntranceRequestDTO;
import co.edu.udea.covapi.dto.response.EntranceResponseDTO;
import co.edu.udea.covapi.model.Entrance;
import co.edu.udea.covapi.service.PermissionService;
import co.edu.udea.covapi.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Component
public class EntrancePopulator implements Populator<Entrance, EntranceResponseDTO, EntranceRequestDTO> {

    @Autowired
    private PermissionService permissionService;


    @Override
    public void populate(Entrance source, EntranceResponseDTO target){
        target.setId(source.getModelId());
        target.setPermissionId(source.getPermission().getId());
        target.setEntryTimeStr(source.getEntryTime() == null ? StringUtils.EMPTY : DateUtil.convert(source.getEntryTime()));
        target.setEntryTemperature(source.getEntryTemperature());
        target.setDepartureTimeStr(source.getDepartureTime()== null ? StringUtils.EMPTY : DateUtil.convert(source.getDepartureTime()));
        target.setDepartureTemperature(source.getDepartureTemperature());
    }

    @Override
    public void inverselyPopulate(EntranceRequestDTO source, Entrance target) throws ExecutionException, InterruptedException {
        target.setPermission(permissionService.getReference(source.getPermissionId()));
        if(source.isEntry()){
            target.setEntryTime(new Date());
            target.setEntryTemperature(source.getTemperature());
        }else{
            target.setDepartureTime(new Date());
            target.setDepartureTemperature(source.getTemperature());
        }
    }
}
