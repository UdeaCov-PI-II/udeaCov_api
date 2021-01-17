package co.edu.udea.covapi.controller.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CovApiResponseErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Throwable error = this.getError(webRequest);
        Map<String, Object> errorAttributes =  super.getErrorAttributes(webRequest, includeStackTrace);
        addBindingErrorMessage(errorAttributes, error);
        return errorAttributes;
    }

    private void addBindingErrorMessage(Map<String, Object> errorAttributes,Throwable error) {
        BindingResult result = this.getBindingResult(error);
        if(result != null){
            this.updateBindingResultErrorMessage(errorAttributes, result);
        }
    }

    private BindingResult getBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult)error;
        } else {
            return error instanceof MethodArgumentNotValidException ? ((MethodArgumentNotValidException)error).getBindingResult() : null;
        }
    }

    private void updateBindingResultErrorMessage(Map<String, Object> errorAttributes, BindingResult result) {
        List<String> errorMessages = result.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        errorAttributes.put("message", errorMessages.toString());
    }
}
