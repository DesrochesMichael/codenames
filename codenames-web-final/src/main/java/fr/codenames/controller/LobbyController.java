package fr.codenames.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.Partie;

@Controller
public class LobbyController {

	@Autowired
	IDAOPartie daopartie;

	@GetMapping("/Lobby")
	@Transactional
	public String affichage(Model model, HttpSession session) {
		Partie partie = new Partie();
		daopartie.save(partie);
		int id = daopartie.findTopByOrderByIdDesc().getId();
		session.setAttribute("id", id);
		return "Lobby";
	}

}
