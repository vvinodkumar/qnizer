package com.nizeapps.qnizer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nizeapps.qnizer.dom.Error;
import com.nizeapps.qnizer.dom.MessageType;
import com.nizeapps.qnizer.dom.Nizer;
import com.nizeapps.qnizer.dom.ResponseWrapper;
import com.nizeapps.qnizer.exception.ValidationException;
import com.nizeapps.qnizer.util.DateUtility;

@Controller
public class BaseController {
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String SUCCESS_RESPONSE_CODE="000000";
	private static final String ERROR_RESPONSE_CODE  ="000001";
	private static final String PARTIAL_SUCCESS_RESPONSE_CODE ="000002";

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class) 
    public @ResponseBody ResponseWrapper handleExceptions(Exception anExc) {
        anExc.printStackTrace(); 
        ResponseWrapper wrapper = new ResponseWrapper();
        wrapper.setMessageType(MessageType.FATALERROR);
        Error err = new Error();
        err.setErrorCode("FATAL-ERR");
        err.setErrorDesc("OOPS!! We encountered an error while performing the requested operation. Please contact your administrator with the below details.");
        wrapper.setRootError(err);
        wrapper.setResponseDateTime(DateUtility.getBusinessDateTime());
        wrapper.setTxnRefNo(UUID.randomUUID().toString());
        wrapper.setResponseCode(ERROR_RESPONSE_CODE);
        return wrapper;
    }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class) 
    public @ResponseBody ResponseWrapper handleExceptions(ValidationException aValidationExc) {
        ResponseWrapper wrapper = new ResponseWrapper();
        wrapper.setMessageType(MessageType.USERERROR);
        Error err = new Error();
        err.setErrorCode("VAL-ERR");
        err.setErrorDesc("Please check your form again. Required information is not available or not in the required format.");
        wrapper.setRootError(err);
        wrapper.setResponseDateTime(DateUtility.getBusinessDateTime());
        wrapper.setTxnRefNo(UUID.randomUUID().toString());
        wrapper.setResponseObject(aValidationExc.getFormObj());
        BindingResult result = aValidationExc.getResult();
        List<ObjectError> errors = result.getAllErrors();
        List<Error> errs = new ArrayList<Error>();
        int i = 0;
        for (ObjectError objectError : errors) {
        	Error er = new Error();
        	er.setErrorCode("ERR-" + (++i));
        	er.setErrorDesc(objectError.getDefaultMessage());
        	errs.add(err);
		}
        wrapper.setResponseCode(ERROR_RESPONSE_CODE);
        wrapper.setErrors(errs);
        return wrapper;
    }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) 
    public @ResponseBody ResponseWrapper handleExceptions(MethodArgumentNotValidException aValidationExc) {
        ResponseWrapper wrapper = new ResponseWrapper();
        wrapper.setMessageType(MessageType.USERERROR);
        Error err = new Error();
        err.setErrorCode("VAL-ERR");
        err.setErrorDesc("Please check your form again. Required information is not available or not in the required format.");
        wrapper.setRootError(err);
        wrapper.setResponseDateTime(DateUtility.getBusinessDateTime());
        wrapper.setTxnRefNo(UUID.randomUUID().toString());
        BindingResult result = aValidationExc.getBindingResult();
        List<Error> errs = new ArrayList<Error>();
        int i=0;
        for(FieldError fldError : result.getFieldErrors()){
        	Error er = new Error();
        	er.setErrorCode("ERR-" + (++i));
        	er.setErrorDesc(messageSource.getMessage(fldError, null));
        	errs.add(er);
		}
         wrapper.setResponseCode(ERROR_RESPONSE_CODE);
        wrapper.setErrors(errs);
        return wrapper;
    }
	
	protected ResponseWrapper prepareSuccessResponse(Nizer data, List dataColl) {
		ResponseWrapper wrapper = new ResponseWrapper();
		wrapper.setResponseCode(SUCCESS_RESPONSE_CODE);
		wrapper.setResponseObject(data);
		wrapper.setResponseCollection(dataColl);
		return wrapper; 
	}
}
