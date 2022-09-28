package com.mb.api.business.exceptions;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler
{
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse badRequest(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> details.add(error.getDefaultMessage()));
		return new ErrorResponse(new Date(), "Bad request", ex.getMessage(), HttpStatus.BAD_REQUEST.value(),
				details.toString());
	}

	@ExceptionHandler(value = { IOException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse badRequest(Exception ex) {

		return new ErrorResponse(new Date(), "Bad request", ex.getMessage(), HttpStatus.BAD_REQUEST.value(),
				ex.getMessage());
	}

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse unKnownException(Exception ex) {
		return new ErrorResponse(new Date(), "Not found", ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse internalServerException(Exception ex) {

		return new ErrorResponse(new Date(), "Internal server error", ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	@ExceptionHandler(value = { AccessDeniedException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse accessDeniedException(Exception ex) {
		return new ErrorResponse(new Date(), "Access denied", ex.getMessage(), HttpStatus.FORBIDDEN.value(), null);
	}

	@ExceptionHandler(value = { CustomException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse handleCustomException(Exception ex) {
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getMessage(), HttpStatus.FORBIDDEN.value(), null);
	}

	@ExceptionHandler(value = { ResourceAlreadyExistsException.class })
	@ResponseStatus(HttpStatus.FOUND)
	public ErrorResponse handleAlreadyExistsExceptions(Exception ex) {
		return new ErrorResponse(new Date(), "Resource Already Exists", ex.getMessage(), HttpStatus.FOUND.value(),
				null);
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleResourceNotFoundExceptions(Exception ex) {
		return new ErrorResponse(new Date(), "Resource Not Exists", ex.getMessage(), HttpStatus.NOT_FOUND.value(),
				null);
	}
	
}
