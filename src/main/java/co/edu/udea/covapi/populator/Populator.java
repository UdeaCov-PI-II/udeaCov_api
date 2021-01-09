package co.edu.udea.covapi.populator;

import co.edu.udea.covapi.dto.BaseResponseDTO;
import co.edu.udea.covapi.exception.PopulatorException;
import co.edu.udea.covapi.model.FirebaseModel;

import java.util.concurrent.ExecutionException;

public interface Populator <T extends FirebaseModel,S extends BaseResponseDTO,V> {

    void populate(T source, S target) throws ExecutionException, InterruptedException;
    void inverselyPopulate(V source, T target) throws PopulatorException, ExecutionException, InterruptedException;
}
