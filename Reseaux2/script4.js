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
  return "";
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
String.prototype.toMatrix = function() {
  var tab = [];
  var mot1 = this.hamming()
  var mot = '2'+mot1.slice(0,3)+'2'+mot1.slice(3,18)+'2'+mot1.slice(18,21)+'2';
  // console.log("mot matrice :"+mot);
  tab[0] = mot.slice(0,5).split('');
  tab[1] = mot.slice(5,10).split('');
  tab[2] = mot.slice(10,15).split('');
  tab[3] = mot.slice(15,20).split('');
  tab[4] = mot.slice(20,25).split('');
  return tab;
}

String.prototype.recup = function() {
  var taille = this+1;
  console.log("t : " + taille);
  while(Math.sqrt(taille) !== Math.round(Math.sqrt(taille))){
    taille++;
  }
  return Math.sqrt(taille);
}

var draw = function() {
  var msg = input.value;
  var codes = msg.getBytes();
  var bits = codes.toBits();
  /*
  console.log("msg : " + msg);
  console.log("codes : " + codes);
  console.log("bits : " + bits);
  */

  var canvas = document.getElementById('tutorial');

  for (var i = 0; i < bits.length; i++) {
    bits[i] = bits[i].fixBits(16);
    /*console.log("bits fixed : ")
    console.log(""+bits[i]);*/
  }

  for (var i = 0; i < bits.length; i++) {
    bits[i] = bits[i].toMatrix();
    /*console.log("bits fixed hamming : ")
    console.log(""+bits[i]);*/
  }

  /* console.log(" ".getBytes());
  console.log(" ".getBytes().toBits());
  console.log(" ".getBytes().toBits().fixBits(16));
  console.log(" ".getBytes().toBits().fixBits(16).hamming());*/
  //console.log("☭ ".getBytes().toBits().fixBits(16).hamming().toMatrix());

  //Recuperation de la racine carre pour la taille du carre (ex: taille mot =9 ou 8, racine et 3 donc cote et 3)
  //Pas reussis a le mettre dans une fonction :x
  var taille = bits.length+1;
  while(Math.sqrt(taille) !== Math.round(Math.sqrt(taille))){
    taille++;
  }
  taille = Math.sqrt(taille);

  if (canvas.getContext) {
    var ctx = canvas.getContext('2d');
    //mire
    ctx.fillStyle = "#66CCE1";
    ctx.fillRect(0,0,10*5,10);
    ctx.fillStyle = "#AA7DFC";
    ctx.fillRect(0,10,10*5,10);
    ctx.fillStyle = "#B2F64C";
    ctx.fillRect(0,20,10*5,10);
    ctx.fillStyle = "#FF5794";
    ctx.fillRect(0,30,10*5,10);
    ctx.fillStyle = "#FAA23D";
    ctx.fillRect(0,40,10*5,10);
    var cpt2 = 0;
    var cpt = 50;
    var b = true
    console.log(taille);
    var l = taille;
    var m = taille;
    for (var k = 1; k < bits.length+1; k++) {
          if(k>taille){
            console.log(k);
            console.log(taille);
            console.log((k%taille));
            l = 50*(k%taille);
            m = 50*(k+1-k%taille);
            console.log(l+"ee"+m);
          }
          else{
            l = 0;
            m = 0;
          }
      for (var i = 0; i < bits[k-1].length; i++) {
        for (var j = 0; j < bits[k-1][i].length; j++) {
          // console.log("i : "+i+" j : "+j);
          if (bits[k-1][i][j] == 0) {
            if (b == true) {
              ctx.fillStyle = "#66CCE1";
            } else if (b == false) {
              ctx.fillStyle = "#B2F64C";
            }
            ctx.fillRect(cpt+(j*10)-(m),(l)+cpt2+(i*10),10,10);
          } else if (bits[k-1][i][j] == 1) {
            if (b == true) {
              ctx.fillStyle = "#AA7DFC";
            } else if (b == false) {
              ctx.fillStyle = "#FF5794";
            }
            ctx.fillRect(cpt+(j*10)-(m),(l)+cpt2+(i*10),10,10);
          } else if (bits[k-1][i][j] == 2) {
            ctx.fillStyle = "#FAA23D";
            ctx.fillRect(cpt+(j*10)-(m),(l)+cpt2+(i*10),10,10);
          }
        }
      }
      b = !b;
      cpt+=50;
    }
  }
}

var clear = function() {
  var canvas = document.getElementById('tutorial');
  if (canvas.getContext) {
    var ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);
  }
  if (input.value !== '') {
    input.value = '';
  }
  console.clear();
  console.log("ok");
}

// window.addEventListener("load", draw);
input = document.getElementById("chaine");
val = document.getElementById("send");
val.addEventListener("click", draw);
suppr = document.getElementById("delete");
suppr.addEventListener("click", clear);
// input.addEventListener("input", draw);