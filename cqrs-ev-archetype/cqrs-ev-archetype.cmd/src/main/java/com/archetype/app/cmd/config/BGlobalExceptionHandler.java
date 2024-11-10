package com.archetype.app.cmd.config;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.archetype.cqrsev.core.vo.BaseResponse;

//@ControllerAdvice(basePackages ="com.prueba" )
@ControllerAdvice
public class BGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	private final Logger logger=Logger.getLogger(BGlobalExceptionHandler.class.getName());
		
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(Exception ex,
//				HttpHeaders headers, HttpStatus status, WebRequest request) {
//			logger.log(Level.WARNING,MessageFormat.format("Could not operate  because validate error - {0} :: mensaje -{1} ", ex.toString(),request));
//			return new ResponseEntity<>(new BaseVo(
////					ex.toString()
//					MessageFormat.format("Could not operate  because validate error - {0} :: mensaje -{1} ", ex.toString(),request)
//					),HttpStatus.BAD_REQUEST);
//
//	}			
			//aqui llamo a la de spring que es lo que no quiero hacer
//			return handleExceptionInternal(
//				      ex, new BaseVo(ex.toString()),
//				      headers, status, request);
//			return new ResponseEntity<>( MessageFormat.format("Could not generate search because validate error - {0} ", ex.toString()),
//					HttpStatus.BAD_REQUEST);
//			return  new ResponseEntity<>(new BaseVo(ex.toString()),HttpStatus.BAD_REQUEST);
	
		//Decapeted j17 uses handleException
//		@Override
//		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//				HttpHeaders headers, HttpStatus status, WebRequest request) {
//			logger.log(Level.WARNING,MessageFormat.format("Could not generate search because validate error - {0} ", ex.getMessage()));
//			return handleExceptionInternal(
//				      ex, new BaseVo(ex.toString()),
//				      headers, status, request);
////			return new ResponseEntity<>( MessageFormat.format("Could not generate search because validate error - {0} ", ex.toString()),
//					HttpStatus.BAD_REQUEST);
//			return  new ResponseEntity<>(new BaseVo(ex.toString()),HttpStatus.BAD_REQUEST);
	//	}	
		@ExceptionHandler(ParseException.class)
		protected ResponseEntity<Object> handleMethodArgumentNotValid(ParseException ex) {
			logger.log(Level.WARNING,MessageFormat.format("Could not generate prototype because validate error - {0} ", ex.getMessage()));
//			return handleExceptionInternal(
//				      ex, new BaseVo(ex.toString()),
//				      new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
			return new ResponseEntity<>(new BaseResponse(ex.toString()),HttpStatus.BAD_REQUEST);
		}
}
