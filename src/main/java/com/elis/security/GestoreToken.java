package com.elis.security;

import java.security.Key;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elis.exception.DatiNonValidiException;
import com.elis.model.Utente;
import com.elis.repository.UtenteRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class GestoreToken {
	
	@Autowired
	UtenteRepository repo;

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
		}
		private static final String SECRET_KEY =
		"404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";


	private Claims creaClaims(Utente u){
		String ruolo=u.getRuolo().getRuolo();
		Claims claims = Jwts.claims().setSubject(u.getUsername());
		if(ruolo.startsWith("ROLE_"))ruolo=ruolo.substring(5);
		claims.put("ruolo",ruolo);
		return claims;
		}
	
	public String generateToken(Utente userDetails) {
		return generateToken(creaClaims(userDetails));
		}
	
	public String generateToken(Claims extraClaims) {
		return Jwts
		.builder()
		.setClaims(extraClaims)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 60))
		.signWith(getSignInKey(), SignatureAlgorithm.HS256)
		.compact();

		}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
		}
	
	private Claims extractAllClaims(String token) {
		return Jwts
		.parserBuilder()
		.setSigningKey(getSignInKey())
		.build()
		.parseClaimsJws(token)
		.getBody();
		}
	
	public LocalDateTime prendiDataScadenza(String token) {
		
		Claims c=extractAllClaims(token);  //riprendo il Claims col metodo scritto sopra
		
		Date d=c.getExpiration();  //prendo da quel claims la data di scadenza (settata in creazione)
		
		return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();  //converto la data di scadenza da Date a LocalDateTime in modo che
																				//sia più comoda da utilizzare all'interno del mio programma
	}
	
	public String prendiUsername(String token) {
		Claims c=extractAllClaims(token);
		return c.getSubject();
	}
	
	public Utente prendiUtenteDaToken(String token) {
		String username=prendiUsername(token);
		Utente u=repo.findUtenteByEmail(username)
				.orElseThrow(()->new DatiNonValidiException(token,"token fallato"));
		return u;
	}
	
	public boolean tokenAncoraValido(String token) {
		if(prendiDataScadenza(token).isBefore(LocalDateTime.now()))return false;
		Utente u=prendiUtenteDaToken(token);
		return u.isEnabled();
	}
	
	public String refreshToken(String token) {
		Utente u=prendiUtenteDaToken(token);
		return generateToken(u);
	}
	
	

}
	
