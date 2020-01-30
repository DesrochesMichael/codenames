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


//Changer background lors du clic sur cartes
function bgImage(){}
//	document.getElementById("test").style.backgroundImage = "url('assets/tuile-bleu.png')";

var el= document.querySelectorAll('button').forEach(button=>{
	 
	button.addEventListener("click", ( event ) => {   
    event.target.style.backgroundImage = "url('assets/tuile-bleu.png')";
    var txt = el.textContent
    console.log(txt)
  });
});



//Recuperation des inputs
document.querySelectorAll('input').forEach(bouton=>
{bouton.addEventListener('click', (event) =>
{
let id =event.target.getAttribute('id');
	if (id=="test3"){
		event.preventDefault();
		console.log(document.querySelector('input[name="indice"]').value);
		console.log(document.querySelector('input[name="nombre"]').value);
		console.log(document.querySelector('input[name="illimite"]').value);
	}
})});



