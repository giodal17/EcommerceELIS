//package com.elis.controllersenzadb;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.elis.database.SingletonUtente;
//import com.elis.model.Indirizzo;
//import com.elis.model.Ruolo;
//import com.elis.model.Utente;
//
//@RestController
//@RequestMapping("/UtenteUser")
//public class UtenteUserControllerSenzaDB {
//	
//	@PostMapping("/Aggiungi")
//	public ResponseEntity<String> AggiungiUser(@RequestBody Utente utente, @RequestParam("passwordConferma") String passwordConferma){
//		SingletonUtente instance = SingletonUtente.getInstance();
//		Utente utenteEsistente = instance.findByEmail(utente.getEmail());
//		if (utenteEsistente == null) {
//			if (utente!=null && (utente.getNome()!=null && !utente.getNome().isBlank()) 
//					&& (utente.getCognome()!=null && !utente.getCognome().isBlank()) 
//					&& (utente.getEmail()!=null && !utente.getEmail().isBlank() && utente.getEmail().contains("@"))
//					&& (utente.getPassword()!=null && !utente.getPassword().isBlank())) {
//				utente.setRuolo(Ruolo.USER);
//				instance.aggiungiUtente(utente, passwordConferma);
//				return ResponseEntity.status(HttpStatus.CREATED).body("Utente aggiunto");
//			}
//		} else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email gia' utilizzata");
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore nell'aggiungere l'utente");
//	}
//	
//	@PostMapping("Login")
//	public ResponseEntity<Utente> LoginUser(@RequestParam("email") String email, @RequestParam ("password") String password){
//		if(email!=null && !email.isBlank() && password!=null && !password.isBlank()) {
//			Utente utenteTrovato = SingletonUtente.getInstance().findByEmailAndPassword(email, password);
//			if(utenteTrovato!=null) {
//				return ResponseEntity.ok().body(utenteTrovato);
//			}
//			return ResponseEntity.badRequest().body(null);
//		}
//		return ResponseEntity.badRequest().body(null);
//	
//	}
//
//	
//	@PostMapping("AggiungiIndirizzo")
//	public ResponseEntity<String> AggiungiIndirizzo(@RequestParam("indirizzo") String indirizzo, @RequestParam("provincia") String provincia,
//			@RequestParam("cap")String cap, @RequestParam("numeroTelefono") String numeroTelefono, @RequestParam("comune") String comune, 
//			@RequestParam("nome") String nome, @RequestParam("id") String id){
//		
//		if(indirizzo!=null && !indirizzo.isBlank() && provincia!=null && !provincia.isBlank() && cap!=null
//				&& !cap.isBlank() && numeroTelefono!=null && !numeroTelefono.isBlank() && comune!=null
//				&& !comune.isBlank() && nome!=null && !nome.isBlank()
//				&& id!=null) {
//			Utente utente = SingletonUtente.getInstance().findById(Integer.parseInt(id));
//			if(utente!=null) {
//				Indirizzo indirizzoDaAggiungere = new Indirizzo(indirizzo, provincia, cap, numeroTelefono, comune, nome, utente);
//				utente.addIndirizzo(indirizzoDaAggiungere);
//				return ResponseEntity.ok().body("Indirizzo aggiunto correttamente");
//			}
//		}
//		return ResponseEntity.badRequest().body("Errore!");
//	}
//	
//	@GetMapping("GetIndirizzi")
//	public ResponseEntity<List<Indirizzo>> GetIndirizzi(int id){
//		
//		Utente utenteTrovato=SingletonUtente.getInstance().findById(id);
//		return ResponseEntity.ok().body(utenteTrovato.getIndirizzi());
//		
//	}
//	@GetMapping("/GetUtenti")
//	public List<Utente> GetUtenti(){
//		if(SingletonUtente.getInstance().getUtenti()!=null) {
//			return SingletonUtente.getInstance().getUtenti();
//		}
//		return null;
//		}
//	
//	@PostMapping("ModificaPassword")
//	public ResponseEntity<String> modificaPassword(@RequestParam("id")int id,@RequestParam("nuovaPassword") String nuovaPassword){
//		
//		if(id!=0 && nuovaPassword!=null && !nuovaPassword.isEmpty()) {
//			return SingletonUtente.getInstance().modificaPassword(id, nuovaPassword)
//					?ResponseEntity.ok().body("Modifica eseguita correttamemte")
//							:ResponseEntity.badRequest().body("Modifica NON eseguita!");
//		}
//		
//		return ResponseEntity.badRequest().body("Modifica NON eseguita!");
//	}
//	
//	@PostMapping("ModificaEmail")
//	public ResponseEntity<String> modificaEmail(@RequestParam("id") int id, @RequestParam("nuovaEmail") String nuovaEmail){
//		
//		if(id!=0 && nuovaEmail !=null && !nuovaEmail.isBlank()) {
//			return SingletonUtente.getInstance().modificaPassword(id, nuovaEmail)
//					?ResponseEntity.ok().body("Modifica email effettuata con successo!")
//							: ResponseEntity.badRequest().body("Modfica NON eseguita!");
//		}
//		
//		return null;
//		
//	}
//
//}
