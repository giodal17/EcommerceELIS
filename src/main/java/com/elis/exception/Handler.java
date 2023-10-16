package com.elis.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.PropertyValueException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.elis.dto.response.MessageDTO;

@RestControllerAdvice
public class Handler {
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<MessageDTO> erroreDB(SQLIntegrityConstraintViolationException e){
		MessageDTO m=new MessageDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<MessageDTO> erroreInserimentoValoriNumerici(HttpMessageNotReadableException e){
		MessageDTO m=new MessageDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}
	
	
	@ExceptionHandler(DatiNonValidiException.class)
	public ResponseEntity<MessageDTO> erroreDati(DatiNonValidiException e){
		MessageDTO m=new MessageDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value(),e.getRequest());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}
	
	@ExceptionHandler(OggettoNonTrovatoException.class)
	public ResponseEntity<MessageDTO> notFound(OggettoNonTrovatoException e){
		MessageDTO m = new MessageDTO(e.getMessage(), HttpStatus.NOT_FOUND.value(), e.getRequest());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
	}
	
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<MessageDTO> erroreValoriNull(PropertyValueException e){
		MessageDTO m=new MessageDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageDTO> erroreValidazione(MethodArgumentNotValidException e){
								//col getBindingResult.getFieldErrors prendo tutti gli errori che ci sono
		Map<String, String> errori=e.getBindingResult().getFieldErrors()
									.stream()
									//creo una map dove come chiave uso il nome del campo, e come valore uso il message di errore
									.collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
		//Creo una lista vuota
		List<String> erroriList=new ArrayList<>();
		//scorro tutte le chiavi presenti nella map(tutti i campi errati)
		for(String s:errori.keySet()) {
			//per ogni campo errato lo aggiungo alla lista insieme al suo errore
			erroriList.add(s+": "+errori.get(s));
		}
		
		MessageDTO m =new MessageDTO(erroriList, HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}
	
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<MessageDTO> erroreValidazioneDati(IllegalStateException e){
		MessageDTO m =new MessageDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
	}
}
