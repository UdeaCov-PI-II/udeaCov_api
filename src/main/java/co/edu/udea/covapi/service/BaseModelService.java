package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.FirebaseModel;
import com.google.cloud.firestore.DocumentReference;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BaseModelService<T extends FirebaseModel>{
    String save(T user) throws InterruptedException, ExecutionException;
    T get(String id, Class<T> entityClass) throws ExecutionException, InterruptedException;
    void update(String id, T model) throws ExecutionException, InterruptedException;
    void delete(String id) throws ExecutionException, InterruptedException;
    DocumentReference getReference(String id) throws ExecutionException, InterruptedException;
    List<T> getAll(Class<T> entityClass) throws ExecutionException, InterruptedException;
    List<T> getByAttribute(Class<T> entityClass, String field, Object value) throws ExecutionException, InterruptedException;
    T getModel(DocumentReference reference, Class<T> entityClass) throws ExecutionException, InterruptedException;

}
