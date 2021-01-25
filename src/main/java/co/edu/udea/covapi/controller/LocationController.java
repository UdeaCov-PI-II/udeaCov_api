package co.edu.udea.covapi.controller;

import co.edu.udea.covapi.dto.response.LocationResponseDTO;
import co.edu.udea.covapi.model.Location;
import co.edu.udea.covapi.populator.LocationPopulator;
import co.edu.udea.covapi.service.LocationService;
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
@RequestMapping(value = "/locations")
public class LocationController {

    private static final Logger LOG = LoggerFactory.getLogger(PermissionController.class);


    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationPopulator locationPopulator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<LocationResponseDTO>> getAllLocations() throws ExecutionException, InterruptedException {
        List<Location> locationsModelList = locationService.getAll(Location.class);
        List<LocationResponseDTO> locations = locationsModelList.stream().map(location ->{
            LocationResponseDTO locationResponse = new LocationResponseDTO();
            try {
                locationPopulator.populate(location,locationResponse);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
            return locationResponse;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}
