

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

UPDATE : On doit effectuer hamming qui effectue une correction on doit rajouter 5 bits pour 16  donc on passe au mutiple
 de 4 supérieur un carré de 5×5 (25)

Les cases restantes pourraient etre utilisées pour placer des pixels de logo

  On est plus obligés de changer la couleur à chaque caractère
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

String.prototype.fixBits = function(nb) {
  if (this.length < nb) {
    var tmp = (nb-1) - this.length;
    var zeros = "0";
    for (var j = 0; j < tmp; j++) {
      zeros += "0";
    }
    // "".concat(zeros,this);
    // this = (zeros + this);
    zeros += this;
    return zeros;
    // console.log(msg[i] + " : " + bits[i]);
  }
}

String.prototype.hamming = function() {
  var str1 = this.slice(0,5);
  var str2 = this.slice(5,12);
  var str3 = this.slice(12,15);
  var str4 = this.slice(15,16);

  var strs = str1 + " " + str2 + " " + str3 + " " + str4 + " " + " ";
  var tab = [];
  var j = 0;

  for (var i = 0; i < strs.length; i++) {
    if (strs[i] == "1") {
      tab[j]=(strs.length-i).toString(2).fixBits(5);
      j++;
    }
  }
  /*console.log(strs);
  console.log(tab);*/
  var tmp = [];
  for (var i = 0; i < 5; i++) {
    for (var j = 0; j < tab.length; j++) {
      tmp[i] = tmp[i] ^ tab[j][i];
    }
  }
  // console.log("tmp : " + tmp);
  return str1 + tmp[0] + str2 + tmp[1] + str3 + tmp[2] + str4 + tmp[3] + tmp[4];
  // return strs;
}

//Creation des tableau de 5*5 a afficher
String.prototype.matrice = function(){
  var tab = [];
  var mot = this.hamming()+2+2+2+2; //Rajout des 4 bits vides
  console.log("mot matrice :"+mot);
  tab[0] = mot.slice(0,5);
  tab[1] = mot.slice(5,10);
  tab[2] = mot.slice(10,15);
  tab[3] = mot.slice(15,20);
  tab[4] = mot.slice(20,25);
  return tab;
}



// var test = "0000000001100001";

var draw = function() {
  var msg = input.value;
  /* -- Debug -- */
  // console.log("msg : " + msg);
  var codes = msg.getBytes();
  // console.log("codes : " + codes);
  var bits = codes.toBits();
  // console.log("bits : " + bits);
  /**/
  var canvas = document.getElementById('tutorial');
  
  // fix bits forcer la taille 16
  for (var i = 0; i < bits.length; i++) {
    bits[i] = bits[i].fixBits(16);
    console.log(msg[i] + " : " + bits[i]);
  //affichage de hamming, taille 21
    console.log("Hamming "+msg[i]+" :"+bits[i].hamming());
    console.log("matrice 5*5 :"+bits[i].matrice());
  }
  /* -- -- */
  if (canvas.getContext) {
    var ctx = canvas.getContext('2d');
    for (var i = 0; i < bits.length; i++) {
      for (var j = 0; j < bits[i].length; j++) {
        if (bits[i][j] == '0') {
          ctx.fillStyle = "Red";
          // ctx.fillRect(j+50,i+50,50,50);
        }
        else if (bits[i][j] == '1') {
          ctx.fillStyle = "Blue";
          // ctx.fillRect(i+50,j+50,50,50);          
        }
      }
    }
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
val = addEventListener("click", draw);
input = document.getElementById("chaine");
// input.addEventListener("input", draw);


/*var old_Draw = function() {
  var canvas = document.getElementById('tutorial');
  if (canvas.getContext) {
    var ctx = canvas.getContext('2d');
    ctx.fillStyle = 'rgb(200, 0, 0)';
    ctx.fillRect(10, 10, 50, 50);
    ctx.fillStyle = 'rgba(0, 0, 200, 0.5)';
    ctx.fillRect(30, 30, 50, 50);
    ctx.font = '48px monospace';
    var msg = input.value;
    ctx.fillText(msg, 50, 50);

    // ctx.fillStyle = "red";
    // for (var i = 0; i < bits.length; i++) {
    //   for (var j = 0; j < bits[i].length; j++) {
    //     // si i pair un couple de couleurs | si i impair une autre
    //     // console.log(" b["+i+"]["+j+"] : " + bits[i][j]);
    //     // ctx.fillRect(i,j,1,1);
    //   }
    // }
  }
}*/
