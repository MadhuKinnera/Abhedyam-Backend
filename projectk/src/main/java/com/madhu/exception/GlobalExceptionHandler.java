package com.madhu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.madhu.dto.GeneralResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserException.class)
	ResponseEntity<GeneralResponse> userExceptionHandler(UserException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerRequestException.class)
	ResponseEntity<GeneralResponse> customerRequestExceptionHandler(CustomerRequestException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerException.class)
	ResponseEntity<GeneralResponse> customerExceptionHandler(CustomerException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductException.class)
	ResponseEntity<GeneralResponse> productExceptionHandler(ProductException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(VillageException.class)
	ResponseEntity<GeneralResponse> villageExceptionHandler(VillageException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecordException.class)
	ResponseEntity<GeneralResponse> recordExceptionHandler(RecordException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RemainderException.class)
	ResponseEntity<GeneralResponse> remainderExceptionHandler(RemainderException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionException.class)
	ResponseEntity<GeneralResponse> transactionExceptionHandler(TransactionException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AddressException.class)
	ResponseEntity<GeneralResponse> addressExceptionHandler(AddressException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<GeneralResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e,
			WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage("Validation Error");
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	ResponseEntity<GeneralResponse> noHandlerFoundExceptionHandler(NoHandlerFoundException e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<GeneralResponse> ExceptionHandler(Exception e, WebRequest req) {

		var err = new GeneralResponse();

		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setData(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

}
