//gestion des différents menus avec la couleur
function hideSections(){
		document.querySelectorAll('.submenu').forEach((section)=>{section.style.display='none';
		});
	}
function reset(){
	document.querySelectorAll('li').forEach((li)=>{li.style.background='none';});
}

	document.querySelectorAll('button').forEach(lien=>
	{lien.addEventListener('click', (event) =>
	{event.preventDefault();
	hideSections();
	reset();
	
		let id =event.target.getAttribute('href');
		
		// menu principal
		if (id=="#connect"){
			document.querySelector('li[id="co"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		
		else if (id=="#joueurs"){
			document.querySelector('li[id="j"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		else if (id=="#cartes"){
			document.querySelector('li[id="c"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		else if (id=="#historique"){
			document.querySelector('li[id="h"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		
		// menu joueurs
		else if (id=="#creerjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector('li[id="j"]').style.backgroundColor='brown';	
			document.querySelector('li[id="creaj"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
		}
		else if (id=="#modifierjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector('li[id="j"]').style.backgroundColor='brown';	
			document.querySelector('li[id="modj"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
		}
		
		
		else if (id=="#modifjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector('div[id="modifierjoueur"]').style.display='block';
			document.querySelector('li[id="j"]').style.backgroundColor='brown';	
			document.querySelector('li[id="modj"]').style.backgroundColor='brown';	
			document.querySelector('li[id="modpseudo"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		// menu modif joeuur
		else if (id=="#modifpassword"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector('div[id="modifierjoueur"]').style.display='block';
			document.querySelector('li[id="j"]').style.backgroundColor='brown';	
			document.querySelector('li[id="modj"]').style.backgroundColor='brown';	
			document.querySelector('li[id="modpassword"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		
		else if (id=="#modifierjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector(id).style.display='block';
		}
		
		else if (id=="#supjoueurpartie"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector('li[id="j"]').style.backgroundColor='brown';	
			document.querySelector('li[id="supj"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
		}
		
		// menu cartes
		else if(id=="#listecartes"){
			document.querySelector('div[id="cartes"]').style.display='block';
			document.querySelector('li[id="c"]').style.backgroundColor='brown';	
			document.querySelector('li[id="lc"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
			
			let passeur = {
			};	
			
			const url = new URL("http://localhost:8080/codenames-web-final/Lobby/listecartes");
				
				fetch (url, {
					method: 'POST',
					headers : {
						'content-Type' : 'application/json'
					},
					body: JSON.stringify(passeur)
					
				})
				.then(resp => resp.json())
//				.then(resp=>resp.text())
//				.then(text=> console.log(text))
				.then(cartes => {
					cartes.forEach( (carte) => {
						
						let myElement = document.createElement("table");
						myElement.innerHTML ="<tr>"+carte.nom+"</tr> "
							
						document.querySelector('div[id="listecartes"]').append(myElement);
						document.querySelector('div[id="listecartes"]').style.display='block';
					});
					
				});
		}
		
		
		else if (id=="#creercarte"){
			document.querySelector('div[id="cartes"]').style.display='block';
			document.querySelector('li[id="c"]').style.backgroundColor='brown';	
			document.querySelector('li[id="creac"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
		}
		
		else if(id=="creercartebouton"){
			document.querySelector('div[id="cartes"]').style.display='block';
			document.querySelector('div[id="creercarte"]').style.display='block';
			document.querySelector('li[id="c"]').style.backgroundColor='brown';	
			document.querySelector('li[id="creac"]').style.backgroundColor='brown';	
		}
		
		else if (id=="#modifiercarte"){
			document.querySelector('div[id="cartes"]').style.display='block';
			document.querySelector('li[id="c"]').style.backgroundColor='brown';	
			document.querySelector('li[id="modifc"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		else if (id=="#supcarte"){
			document.querySelector('div[id="cartes"]').style.display='block';
			document.querySelector('li[id="c"]').style.backgroundColor='brown';	
			document.querySelector('li[id="supc"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
		}
		// menu historique
		else if(id=="#listejoueurs"){
			document.querySelector('div[id="historique"]').style.display='block';
			document.querySelector('li[id="h"]').style.backgroundColor='brown';	
			document.querySelector('li[id="lj"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
						
			let passeur = {
			};	
			
			const url = new URL("http://localhost:8080/codenames-web-final/Lobby/listejoueur");
				
				fetch (url, {
					method: 'POST',
					headers : {
						'content-Type' : 'application/json'
					},
					body: JSON.stringify(passeur)
					
				})
				.then(resp => resp.json())
				.then( joueurs => {
					joueurs.forEach( (joueur) => {
						
						let myElement = document.createElement("table");
						myElement.innerHTML ="<tr>"+joueur.pseudo+"</tr> "
							
						document.querySelector('div[id="listejoueurs"]').append(myElement);
						document.querySelector('div[id="listejoueurs"]').style.display='block';
					});
					
				});
						
		}
		
		
		
		else if (id=="#statistiquejoueur"){
			document.querySelector('div[id="historique"]').style.display='block';
			document.querySelector('li[id="h"]').style.backgroundColor='brown';	
			document.querySelector('li[id="statj"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
		}
		
		else if (id=="#supjoueur"){
			document.querySelector('div[id="historique"]').style.display='block';
			document.querySelector('li[id="h"]').style.backgroundColor='brown';	
			document.querySelector('li[id="supbddj"]').style.backgroundColor='brown';
			document.querySelector(id).style.display='block';
		}
		
		
		
	});
	});
	
	
//	var formValid = document.getElementById('test18');
//	var pseudo = document.getElementById('pseudocreer');
//	var missPseudo = document.getElementById('misspseudocreer');
//	
//	formValid.addEventListener('click', validation);
//	
//	function validation(event){
//		//Si le champ est vide
//		if(pseudo.validity.valueMissing){
//			event.preventDefault();
//			missPseudo.textConten='Pseudo manquant';
//			missPseudo.style.color='red';
//		}
//	}
	
	
	
	
	// query selector de tout les input avec fetch
	
	document.querySelectorAll('input[type="submit"]').forEach(lien=>
	{lien.addEventListener('click', (event) =>
	{event.preventDefault();
	let id =event.target.getAttribute('value');
	
	 
	if(id=="Se connecter"){
		
		let joueur = {
				pseudo : document.querySelector('input[name="pseudoconnect"]').value,
				mdp : document.querySelector('input[name="mdpconnect"]').value
		};
				
		
		
		const url = new URL("http://localhost:8080/codenames-web-final/Lobby/connect");
		
		fetch (url, {
			method: 'POST',
			headers : {
				'content-Type' : 'application/json'
			},
			body: JSON.stringify(joueur)
			
		}).then(resp => resp.text())
		.then(a => {
			
			if (a==0){
				alert("Ce joueur n'existe pas en base de données");
			}
			
			else if (a==1){
				alert("Pas de correspondance avec le mdp");
			}
			
			else if(a==2){
				alert("Joueur connecté");
				
			}
		});
	
	}
	else if(id=="Creer joueur"){
		let joueur = {
				pseudo : document.querySelector('input[name="pseudocreer"]').value,
				mdp : document.querySelector('input[name="mdpcreer"]').value
		};	
		let mdp = document.querySelector('input[name="confmdpcreer"]').value;
		
		if (joueur.mdp!=mdp){
			alert("Les deux mdp sont différents.");
		}
		else if(joueur.pseudo.length == 0){
			alert("Met un pseudo Bruh");
		}
		else if(joueur.mdp.length ==0){
			alert("Met un mdp Bruh");
		}
		else {
		
		const url = new URL("http://localhost:8080/codenames-web-final/Lobby/creerjoueur");
		
		fetch (url, {
			method: 'POST',
			headers : {
				'content-Type' : 'application/json'
			},
			body: JSON.stringify(joueur)
			
		}).then(resp => resp.text())
		.then(a => {
			if (a==0){
				alert("Joueur créé.")
			}
			});
		}
		}
	
	
	
	
	
	else if(id=="Modifier pseudo"){
		let passeur = {
				pseudo1 : document.querySelector('input[name="pseudomodifpseudo"]').value,
				mdp1 : document.querySelector('input[name="mdpmodifpseudo"]').value,
				pseudo2 : document.querySelector('input[name="newpseudo"]').value    
				
		};	
		
		
		if (passeur.pseudo1==passeur.pseudo2){
			alert("Les deux pseudo sont identiques. Veuillez choisir un pseudo différent");
		}
		else if(passeur.pseudo1.length==0 || passeur.pseudo2.length ==0){
			alert("Veuillez rentrer un pseudo mamène");
		}
		else if(passeur.mdp1.length==0){
			alert("Veuillez rentrer un mdp mamène");
		}
		else {
		
			const url = new URL("http://localhost:8080/codenames-web-final/Lobby/modifpseudo");
			
			fetch (url, {
				method: 'POST',
				headers : {
					'content-Type' : 'application/json'
				},
				body: JSON.stringify(passeur)
				
			}).then(resp => resp.text())
			.then(a => {
				if(a==-1){
					alert("Ce joueur n'existe pas.");
				}
				else if (a==0){
					alert("Un joueur de ce nom existe déjà. Veuillez saisir un autre pseudo.");
				}
				else if (a==1){
					alert("Mdp du joueur incorrect.");
				}
				
				else if (a==2){
					alert("Changement de pseudo effectué.");
				}
			});

		}
	}
	
	
	
	else if(id=="Modifier mdp"){
		let passeur = {
				pseudo1 : document.querySelector('input[name="pseudomodifmdp"]').value,
				mdp1 : document.querySelector('input[name="mdpmodifmdp"]').value,
				mdp2 : document.querySelector('input[name="newmdp"]').value  
		};	
		
		let conf = document.querySelector('input[name="confnewmdp"]').value 
		
		
		if (passeur.mdp1==passeur.mdp2){
			alert("L'ancien et le nouveau mdp sont identiques. Veuillez choisir un mdp différent");
		}
		else if (conf!=passeur.mdp2){
			alert("Les deux saisies du nouveau mdp ne sont pas identiques.");
		}
		else if(passeur.pseudo1.length==0){
			alert("Il faut rentrer un pseudo, t'es bête ou quoi ?!");
		}
		else if(passeur.mdp1.length==0 || passeur.mdp2.length==0){
			alert("Tu ne feras rien sans rentrer de mdp");
		}
		
		else {
		
			const url = new URL("http://localhost:8080/codenames-web-final/Lobby/modifmdp");
			
			fetch (url, {
				method: 'POST',
				headers : {
					'content-Type' : 'application/json'
				},
				body: JSON.stringify(passeur)
				
			}).then(resp => resp.text())
			.then(a => {
			
				if (a==0){
					alert("Aucun joueur de ce nom existe.");
				}
				else if (a==1){
					alert("Mdp du joueur incorrect.");
				}
				
				else if (a==2){
					alert("Changement de mdp effectué.");
				}
			});

		}
	}
	else if(id=="Creer carte"){
		let passeur = {
				nom1 : document.querySelector('input[name="cartecreer"]').value,
		};	
		
		if(passeur.nom1.length==0){
			alert("Tu veux vraiment créer une carte sans nom ? Tsss ...");
		}
		else{
		const url = new URL("http://localhost:8080/codenames-web-final/Lobby/creercarte");
			
			fetch (url, {
				method: 'POST',
				headers : {
					'content-Type' : 'application/json'
				},
				body: JSON.stringify(passeur)
				
			}).then(resp => resp.text())
			.then(a => {
			
				if (a==0){
					alert("Cette carte existe déjà.");
				}
				else if (a==1){
					alert("Carte créé.");
				}
				
			});

		}
	}
	else if(id=="Modifier carte"){
		let passeur = {
				nom1 : document.querySelector('input[name="cartemodifnom"]').value,
				nom2 : document.querySelector('input[name="cartenouveaunom"]').value,
				
		};	
		
		if (passeur.nom1==passeur.nom2){
			alert("Les deux noms sont identiques.");
		}
		else if(passeur.nom1.length==0 || passeur.nom2.length ==0) {
			alert("YOU SHALL NOT PASS !! (T'as oublié de rentrer le mot)");
		}
		else{
		 
		const url = new URL("http://localhost:8080/codenames-web-final/Lobby/modifcarte");
			
			fetch (url, {
				method: 'POST',
				headers : {
					'content-Type' : 'application/json'
				},
				body: JSON.stringify(passeur)
				
			}).then(resp => resp.text())
			.then(a => {
			
				if (a==0){
					alert("Aucune carte de ce nom existe.");
				}
				else if (a==1){
					alert("Carte modifié.");
				}
				
			});
		}
		
	}
	
	else if(id=="Supprimer carte"){
		let passeur = {
				nom1 : document.querySelector('input[name="cartesupprimer"]').value,
	
		};	
		
		if(passeur.nom1.length==0){
			alert("Tu veux supprimer une carte ? Rentre un mot alors.")
		}
		else{
			
		const url = new URL("http://localhost:8080/codenames-web-final/Lobby/supcarte");
			
			fetch (url, {
				method: 'POST',
				headers : {
					'content-Type' : 'application/json'
				},
				body: JSON.stringify(passeur)
				
			}).then(resp => resp.text())
			.then(a => {
			
				if (a==0){
					alert("Aucune carte de ce nom existe.");
				}
				else if (a==1){
					alert("Carte supprimé.");
				}
				
			});
		
		}
	}
	
	else if(id=="Consulter"){
		let passeur = {
				pseudo1 : document.querySelector('input[name="histojoueur"]').value,
	
		};	
		
		const url = new URL("http://localhost:8080/codenames-web-final/Lobby/histojoueur");
			
			fetch (url, {
				method: 'POST',
				headers : {
					'content-Type' : 'application/json'
				},
				body: JSON.stringify(passeur)
				
			}).then(resp => resp.json())
			.then( joueur => {
			
				if (joueur==null){
					alert("Aucun joueur de ce nom existe.");
				}
				else if (joueur!=null) {

					let myElement = document.createElement("table");
					myElement.innerHTML ="<tr><th>Pseudo du joueur</th> " +
							"<th>nombre de partie</th> " +
							"<th>Nombre de victoire</th> " +
							"<th>Nombre de maitre espion</th></tr>" +
							"<tr><td>"+joueur.pseudo+"</td> " +
							"<td>"+joueur.nbrPartie+"</td> " +
							"<td>"+joueur.nbrVictoire+"</td> " +
							"<td>"+joueur.nbrMaitreEspion+"</td></tr> "
						
					document.querySelector('div[id="insertionstat"]').append(myElement);
					document.querySelector('div[id="insertionstat"]').style.display='block';
				}
				
			});
		
		
	}
	
	else if(id=="Supprimer joueur"){
		let passeur = {
				pseudo1 : document.querySelector('input[name="joueurhistosup"]').value,
				mdp1 : document.querySelector('input[name="mdphistosup"]').value,
		};	
		if(passeur.pseudo1.length==0){
			alert("Quel joueur veux-tu supprimer ? Il faut rentrer un nom")
		}
		else if(passeur.mdp1.length==0){
			alert("Tututuu tu vas où comme ça ? Si tu veux supprimer un joueur il faut son mdp")	
		}
		else{
			
		const url = new URL("http://localhost:8080/codenames-web-final/Lobby/histosup");
			
			fetch (url, {
				method: 'POST',
				headers : {
					'content-Type' : 'application/json'
				},
				body: JSON.stringify(passeur)
				
			}).then(resp => resp.text())
			.then( a => {
			
				if (a==0){
					alert("Aucun joueur de ce nom existe.");
				}
				else if (a==1) {
					alert("Le mdp du joueur est incorrect.");
				}
				
				else if (a==2) {
					alert("Joueur supprimé.");
				}
				
			});
		}
		
	}
	// ici
	
	 
	});
	});
	
	
	
	
	
	
// au survol de la souris
	
	
// pour les a
document.querySelectorAll('a').forEach(button=>{
		
		button.addEventListener("mouseover", ( event ) => {   
	    event.target.style.background = "brown";
	  });
	});

document.querySelectorAll('a').forEach(button=>{
	
	button.addEventListener("mouseout", ( event ) => {   
    event.target.style.background = "grey";
  });
});


// pour les button
document.querySelectorAll('button').forEach(button=>{
	
	button.addEventListener("mouseover", ( event ) => {   
    event.target.style.background = "brown";
  });
});

document.querySelectorAll('button').forEach(button=>{
	
	button.addEventListener("mouseout", ( event ) => {   
    event.target.style.background = "grey";
  });
});
	

	// Recuperation des inputs
	
	document.querySelectorAll('a').forEach(bouton=>
	{bouton.addEventListener('click', (event) =>
	{
	let href =event.target.getAttribute('href');
		if (href=="connection"){
			event.preventDefault();
			console.log(document.querySelector('input[name="pseudo"]').value);
			console.log(document.querySelector('input[name="password"]').value);
		}
		
		else if(href=="creerjoueur"){
			event.preventDefault();
			console.log(document.querySelector('input[name="pseudocreer"]').value);
			console.log(document.querySelector('input[name="passwordcreer"]').value);
			console.log(document.querySelector('input[name="confirmpasswordcreer"]').value);
		}
		
		else if(href=="modifpseudo"){
			event.preventDefault();
			console.log(document.querySelector('input[name="pseudojoueurmodifpseudo"]').value);
			console.log(document.querySelector('input[name="passwordmodifpseudo"]').value);
			console.log(document.querySelector('input[name="pseudonouveaupseudo"]').value);
		}
		
		else if(href=="modifpassword"){
			event.preventDefault();
			console.log(document.querySelector('input[name="pseudojoueurmodifpassword"]').value);
			console.log(document.querySelector('input[name="passwordmodifpassword"]').value);
			console.log(document.querySelector('input[name="newpasswordmodifpassword"]').value);
			console.log(document.querySelector('input[name="confirmnouveaupassword"]').value);
		}
		
		else if(href=="supjoueurpartie"){
			event.preventDefault();
			console.log(document.querySelector('input[name="pseudojoueursuppartie"]').value);
		}
		
		else if(href=="creercarte"){
			event.preventDefault();
			console.log(document.querySelector('input[name="creercarte"]').value);
		}
		
		else if(href=="modifiercarte"){
			event.preventDefault();
			console.log(document.querySelector('input[name="carteamodifier"]').value);
			console.log(document.querySelector('input[name="carteamodifiernouveau"]').value);
		}
		
		else if(href=="supcarte"){
			event.preventDefault();
			console.log(document.querySelector('input[name="carteasup"]').value);
		}

	});
	});
	
	
	
