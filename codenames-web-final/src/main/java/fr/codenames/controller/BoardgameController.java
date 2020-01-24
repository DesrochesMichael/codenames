package fr.codenames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardgameController {

	@GetMapping("/boardgame")
	public String affichage() {
		return "boardgame";
	}
	
	
	
	
}
