
/*
TODO LIST

- recuperer saisie et l'afficher pour le test
- determiner taille matrice en fonction de la taille de la chaine
- afficher "mire" couleurs fixe en haut à gauche à prendre en compte pour l'affichage du reste, zone à pas toucher
 
*/

var draw = function() {
  var canvas = document.getElementById('tutorial');
  if (canvas.getContext) {
    var ctx = canvas.getContext('2d');

    ctx.fillStyle = 'rgb(200, 0, 0)';
    ctx.fillRect(10, 10, 50, 50);

    ctx.fillStyle = 'rgba(0, 0, 200, 0.5)';
    ctx.fillRect(30, 30, 50, 50);
  	ctx.font = '48px monospace';
  	ctx.fillText('Hello world', 50, 50);
  }
}

// document.write("test");
//window.addEventListener("load", draw);
input = document.getElementById("chaine");
input.addEventListener("input", draw)
