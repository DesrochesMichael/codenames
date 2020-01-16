
//gestion des différents menus
function hideSections(){
		document.querySelectorAll('.submenu').forEach((section)=>{section.style.display='none';
		});
	}
hideSections();

	document.querySelectorAll('button').forEach(lien=>
	{lien.addEventListener('click', (event) =>
	{event.preventDefault();
	hideSections();
		let id =event.target.getAttribute('href');
		
		if (id=="#creerjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
		}
		if (id=="#modifierjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
		}
		
		if (id=="#modifjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector('div[id="modifierjoueur"]').style.display='block';
		}
		
		if (id=="#modifpassword"){
			document.querySelector('div[id="joueurs"]').style.display='block';
			document.querySelector('div[id="modifierjoueur"]').style.display='block';
		}
		
		if (id=="#modifierjoueur"){
			document.querySelector('div[id="joueurs"]').style.display='block';
		}
		
		if (id=="#supjoueurpartie"){
			document.querySelector('div[id="joueurs"]').style.display='block';
		}
		
		if (id=="#creercarte"){
			document.querySelector('div[id="cartes"]').style.display='block';
		}
		
		if (id=="#modifiercarte"){
			document.querySelector('div[id="cartes"]').style.display='block';
		}
		if (id=="#supcarte"){
			document.querySelector('div[id="cartes"]').style.display='block';
		}
		
		if (id=="#statistiquejoueur"){
			document.querySelector('div[id="historique"]').style.display='block';
		}
		
		if (id=="#supjoueur"){
			document.querySelector('div[id="historique"]').style.display='block';
		}
		document.querySelector(id).style.display='block';
		
	});
	});
	
	
	
	
	// gestion des couleurs menus
	function couleurMenu(loc){
		document.querySelectorAll(loc).forEach((bouton)=>{bouton.style.background='brown';
		});
	}

	
	

	document.querySelectorAll('div[name="principal"] button').forEach(bouton=>
	{bouton.addEventListener('click', (event) =>
	{event.preventDefault();
	couleurMenu('div[id="joueurs"] button');
	couleurMenu('div[id="cartes"] button');
	couleurMenu('div[name="principal"] button');
	couleurMenu('div[id="historique"] button');
	couleurMenu('div[id="modifjoueur"] button');
		event.target.style.background='grey';
		
	});
	});
	
	
	document.querySelectorAll('div[id="joueurs"] button').forEach(bouton=>
	{bouton.addEventListener('click', (event) =>
	{event.preventDefault();
	
	couleurMenu('div[id="joueurs"] button');
	couleurMenu('div[id="modifierjoueur"] button');
		event.target.style.background='grey';
		
	});
	});
	
	document.querySelectorAll('div[id="modifierjoueur"] button').forEach(bouton=>
	{bouton.addEventListener('click', (event) =>
	{event.preventDefault();
	couleurMenu('div[id="modifierjoueur"] button');
		event.target.style.background='grey';
		
	});
	});
	
	document.querySelectorAll('div[id="cartes"] button').forEach(bouton=>
	{bouton.addEventListener('click', (event) =>
	{event.preventDefault();
	couleurMenu('div[id="cartes"] button');
		event.target.style.background='grey';
		
	});
	});
	
	document.querySelectorAll('div[id="historique"] button').forEach(bouton=>
	{bouton.addEventListener('click', (event) =>
	{event.preventDefault();
	couleurMenu('div[id="historique"] button');
		event.target.style.background='grey';
		
	});
	});
	

	//Recuperation des inputs
	
	document.querySelectorAll('a').forEach(bouton=>
	{bouton.addEventListener('click', (event) =>
	{event.preventDefault();
	let href =event.target.getAttribute('href');
		if (href=="connection"){
			console.log(document.querySelector('input[name="pseudo"]').value);
			console.log(document.querySelector('input[name="password"]').value);
		}
		
		else if(href=="creerjoueur"){
			console.log(document.querySelector('input[name="pseudocreer"]').value);
			console.log(document.querySelector('input[name="passwordcreer"]').value);
			console.log(document.querySelector('input[name="confirmpasswordcreer"]').value);
		}
		
		else if(href=="modifpseudo"){
			console.log(document.querySelector('input[name="pseudojoueurmodifpseudo"]').value);
			console.log(document.querySelector('input[name="passwordmodifpseudo"]').value);
			console.log(document.querySelector('input[name="pseudonouveaupseudo"]').value);
		}
		
		else if(href=="modifpassword"){
			console.log(document.querySelector('input[name="pseudojoueurmodifpassword"]').value);
			console.log(document.querySelector('input[name="passwordmodifpassword"]').value);
			console.log(document.querySelector('input[name="newpasswordmodifpassword"]').value);
			console.log(document.querySelector('input[name="confirmnouveaupassword"]').value);
		}
		
		else if(href=="supjoueurpartie"){
			console.log(document.querySelector('input[name="pseudojoueursuppartie"]').value);
		}
		
	});
	});
	
	
	
	
