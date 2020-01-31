/* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

/*
 * Set the width of the side navigation to 0 and the left margin of the page
 * content to 0, and the background color of body to white
 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}





// const url = new URL("http://localhost:8080/codenames-web-final/boardgame");
// fetch (url, {
// method: 'POST',
// headers : {
// 'content-Type' : 'application/json'
// },
// body: JSON.stringify(passeur)
//	
// }).then(resp => resp.json())
// .then( carte => {
//
// if (carte==null){
// alert("Aucun joueur de ce nom existe.");
// }

// Recuperation des inputs des maitres espions
document.querySelectorAll('input[name="envoyer"]').forEach(bouton=>
{bouton.addEventListener('click', (event) =>{

		
	let passeur = {
			indice : document.querySelector('input[name="indice"]').value,
			nbr:document.querySelector('input[name="nombre"]').value
	}
	
		
		const url = new URL("http://localhost:8080/codenames-web-final/boardgame/indice");
		
		fetch (url, {
			method: 'POST',
			headers : {
				'content-Type' : 'application/json'
			},
			body: JSON.stringify(passeur)
			
		}).then(resp => resp.text())
		.then(string => {
			
			let myElement = document.createElement("p");
			myElement.innerHTML = string;
			document.querySelector('div[name="tchat"]').append(myElement);
		});
		
});
});



// affichage au démarrage de l'appli
document.addEventListener('DOMContentLoaded', (load) =>{
	
	let passeur = {};
	
	const url = new URL("http://localhost:8080/codenames-web-final/boardgame/initialisation");
	
	fetch (url, {
		method: 'POST',
		headers : {
			'content-Type' : 'application/json'
		},
		body: JSON.stringify(passeur)
		
	}).then(resp => resp.text())
	.then(string => {
		
		let myElement = document.createElement("p");
		myElement.innerHTML = string;
		document.querySelector('div[name="tchat"]').append(myElement);
		
	});
	
});



// cartes nom de code

// Changer background lors du clic sur cartes et récuperer les mots
document.querySelectorAll('button').forEach(button=>{

	button.addEventListener("click", ( event ) => {
		
		
		let passeur = {
				carte : event.target.textContent
				};
		
		const url = new URL("http://localhost:8080/codenames-web-final/boardgame/reponse");
		
		fetch (url, {
			method: 'POST',
			headers : {
				'content-Type' : 'application/json'
			},
			body: JSON.stringify(passeur)
			
		}).then(resp => resp.text())
		.then(a => {
			if (a==0){
				alert("noir perdue")
			}
			
			else if (a==1){
				alert("gris changement de tour")	
			}
			
			else if (a==2){
				alert("BOnne réponse")	
				event.target.style.backgroundImage = "url('assets/tuile-rouge.png')";
			}
			
			else if (a==3){
				alert("Réponse de l'équipe adverse")	
				event.target.style.backgroundImage = "url('assets/tuile-bleu.png')";
			}
		});
		
    
    console.log(event.target.textContent);
  });
});