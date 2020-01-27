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
			event.preventDefault();
			document.querySelector('li[id="h"]').style.backgroundColor='brown';	
			document.querySelector('li[id="lj"]').style.backgroundColor='brown';	
			document.querySelector(id).style.display='block';
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