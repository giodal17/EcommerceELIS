package com.elis.security;

import java.io.IOException;

import com.elis.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterAutorizzazione extends OncePerRequestFilter{

	@Autowired
	GestoreToken tokenP;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//mi riprendo l'header che ha come chiave Authorization
		String header=request.getHeader("Authorization");
		//se esiste e rispetta la convenzione che inzia per Bearer quindi
		//è un token JWT
		if(header!=null&&header.startsWith("Bearer ")) {
			//tolgo dalla stringa "Bearer " e lascio solo il token
			String token =header.substring(7);
			//adesso che ho il token prendo da quel token l'utente che ha effettuato la login
			Utente u=tokenP.prendiUtenteDaToken(token);
			//se non sono mai passato per questo metodo quindi non ho ancora settato nessuna autorizzazione
			if(SecurityContextHolder.getContext().getAuthentication()==null&&
					//e il token è ancora valido
					tokenP.tokenAncoraValido(token)) {
				//creo un oggetto per gestire le autorizzazioni che ha l'utente
				//questo oggetto ha bisogno di 3 cose per esser creato
				//ma di norma due sono alternative
				UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(
						//la prima cosa di cui ha bisono è il "principal" ovvero l'utente che effettua la login
						u,
						//la seconda cosa che potremmo passargli sono le credenziali di accesso
						//nel nostro caso useremo le autorizzazioni quini non le usiamo
						null,
						//l'ultima cosa che gli andremo a passare è la lista di autorizzazioni in base
						//ai ruoli che ha quell'utente
						u.getAuthorities());
				//oltre ai parametri passati dal costruttore setto al nostro oggetto
				//anche la request in modo da "capire" quale servizio stiamo chiamando
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//finito di creare il tutto lo setto al contesto della chiamata
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		//a prescidere da tutto gli dico di passare al punto successivo
		filterChain.doFilter(request, response);	
	}

}

