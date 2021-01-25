package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.response.UnitResponseDTO;
import co.edu.udea.covapi.model.Unit;
import co.edu.udea.covapi.populator.UnitPopulator;
import co.edu.udea.covapi.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/units")
public class UnitController {

    private static final Logger LOG = LoggerFactory.getLogger(UnitController.class);


    @Autowired
    private UnitService unitService;

    @Autowired
    private UnitPopulator unitPopulator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<UnitResponseDTO>> getAllLocations() throws ExecutionException, InterruptedException {
        List<Unit> unitsModelList = unitService.getAll(Unit.class);
        List<UnitResponseDTO> units = unitsModelList.stream().map(unit ->{
            UnitResponseDTO unitResponse = new UnitResponseDTO();
            try {
                unitPopulator.populate(unit,unitResponse);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
            return unitResponse;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(units, HttpStatus.OK);
    }
}
