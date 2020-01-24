package fr.codenames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LobbyController {

	@GetMapping("/Lobby")
	public String affichage() {

		return "Lobby";
	}
}
