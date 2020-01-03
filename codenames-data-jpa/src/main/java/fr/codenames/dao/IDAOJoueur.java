package fr.codenames.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import fr.codenames.model.Joueur;

@EnableJpaRepositories
public interface IDAOJoueur extends JpaRepository<Joueur, Integer> {

	public Joueur findByPseudo(String pseudo);
	public List<Joueur> deleteByPseudo(String Nom);
	
//	@Query("select j from Joueur j where j.pseudo = :nom and j.mdp=:mdp")
//	public Optional<Joueur> connect(@Param("nom") String pseudo, @Param("mdp")String mdp);
	
	@Query("select j from Joueur j where j.pseudo = :#{#j.pseudo} and j.mdp=:#{#j.mdp}")
	public Joueur connect(@Param("j") Joueur j);
	

}
