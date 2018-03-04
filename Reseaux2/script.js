
/*
TODO LIST

- recuperer saisie et l'afficher pour le test
- determiner taille matrice en fonction de la taille de la chaine
- afficher "mire" couleurs fixe en haut à gauche à prendre en compte pour l'affichage du reste, zone à pas toucher
/ ! \ Ajouter un caractère pour changement de mot ou changement de couleurs à chaque mot


ON peut proceder par "carrés" de deux couleurs, changeantes à chaque carac le code est un code de code

IL est nécessaire de gérer idéalement chars codés sur 16bits.

On a donc lettres codées sur des carrés de 4×4 (16) une couleur pour le 0 l'autre pour le 1

Le premier est toujours la "mire" qui indique les couleurs utilisées (l'encodage)
*/

// récuperer le code unicode des caractères d'une chaîne
String.prototype.getBytes = function() {
  var bytes = [];
  for (var i = 0; i < this.length; ++i) { 
    bytes.push(this.charCodeAt(i) /*& 255*/); // si on met ça on passe en 8 bits et pas 16
  }
  return bytes;
};

Array.prototype.toBits = function() {
  var bits = [];
  for (var i = 0; i < this.length; ++i) {
    bits.push(this[i].toString(2));
  }
  return bits;
}

// test
// var str = "Hello";
// var bytes = str.getBytes();
// console.log(bytes)
// var bits = bytes.toBits();
// console.log(bits);


var draw = function() {
  // var canvas = document.getElementById('tutorial');
  // if (canvas.getContext) {
  //   var ctx = canvas.getContext('2d');
  //   ctx.fillStyle = 'rgb(200, 0, 0)';
  //   ctx.fillRect(10, 10, 50, 50);
  //   ctx.fillStyle = 'rgba(0, 0, 200, 0.5)';
  //   ctx.fillRect(30, 30, 50, 50);
  // 	ctx.font = '48px monospace';
  //   var msg = input.value;
  //   ctx.fillText(msg, 50, 50);
  // }
  var msg = input.value;

  /* -- Debug -- */
  console.log("msg : " + msg);
  var codes = msg.getBytes();
  console.log("codes : " + codes);
  var bits = codes.toBits();
  console.log("bits : " + bits);
  var canvas = document.getElementById('tutorial');
  if (canvas.getContext) {
    var ctx = canvas.getContext('2d');
    ctx.fillStyle = "red";
    for (var i = 0; i < bits.length; i++) {
      for (var j = 0; j < bits[i].length; j++) {
        // si i pair un couple de couleurs | si i impair une autre
        console.log(" b["+i+"]["+j+"] : " + bits[i][j]);
        // ctx.fillRect(i,j,1,1);
      }
    }
  }
  /* -- -- */
  if (canvas.getContext) {
    
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
    console.clear();
  }
}

// window.addEventListener("load", draw);
suppr = document.getElementById("delete");
suppr.addEventListener("click", clear);
val = document.getElementById("send");
val.addEventListener("click", draw);
input = document.getElementById("chaine");
// input.addEventListener("input", draw);