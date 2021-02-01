package co.edu.udea.covapi.service;

import co.edu.udea.covapi.model.User;

import java.util.concurrent.ExecutionException;


public interface UserService extends BaseModelService<User>{
    User getByUsername(String username) throws ExecutionException, InterruptedException;
    User getByDocNumberAndDocType(final String docType, final String docNumber) throws ExecutionException, InterruptedException;
}
