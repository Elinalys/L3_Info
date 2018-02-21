
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
    // ctx.fillStyle = 'rgb(200, 0, 0)';
    // ctx.fillRect(10, 10, 50, 50);
    // ctx.fillStyle = 'rgba(0, 0, 200, 0.5)';
    // ctx.fillRect(30, 30, 50, 50);
  	ctx.font = '48px monospace';
    msg = input.value;
  	ctx.fillText(msg, 50, 50);
  }
}

var clear = function() {
  var canvas = document.getElementById('tutorial');
  if (canvas.getContext) {
    var ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    if (input.value !== '') {
      input.value = '';
    }
  }

}

// window.addEventListener("load", draw);
suppr = document.getElementById("delete");
suppr.addEventListener("click", clear);
val = document.getElementById("send");
val.addEventListener("click", draw);
input = document.getElementById("chaine");
// input.addEventListener("input", draw);