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
// Le pb vient de cette fonction -> non c'est juste tu l'appelles deux fois ducon
String.prototype.toMatrix = function() {
  var tab = [];
  var mot = this.hamming()+'2'+'2'+'2'+'2'; //Rajout des 4 bits vides
  // console.log("mot matrice :"+mot);
  tab[0] = mot.slice(0,5).split('');
  tab[1] = mot.slice(5,10).split('');
  tab[2] = mot.slice(10,15).split('');
  tab[3] = mot.slice(15,20).split('');
  tab[4] = mot.slice(20,25).split('');
  return tab;
}

var draw = function() {
  var msg = input.value;
  var codes = msg.getBytes();
  var bits = codes.toBits();

  
  /*console.log("msg : " + msg);
  console.log("codes : " + codes);
  console.log("bits : " + bits);*/
  

  var canvas = document.getElementById('tutorial');
  
  // matrice qui contiendra le code a afficher
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

/*  console.log(" ".getBytes());
  console.log(" ".getBytes().toBits());
  console.log(" ".getBytes().toBits().fixBits(16));
  console.log(" ".getBytes().toBits().fixBits(16).hamming());*/
  //console.log("☭ ".getBytes().toBits().fixBits(16).hamming().toMatrix());

    if (canvas.getContext) {
    var ctx = canvas.getContext('2d');
    //mire
    ctx.fillStyle = "#66CCE1";
    ctx.fillRect(0,0,5*5,5);
    ctx.fillStyle = "#AA7DFC";
    ctx.fillRect(0,5,5*5,5);
    ctx.fillStyle = "#B2F64C";
    ctx.fillRect(0,10,5*5,5);
    ctx.fillStyle = "#FF5794";
    ctx.fillRect(0,15,5*5,5);
    ctx.fillStyle = "#FAA23D";
    ctx.fillRect(0,20,5*5,5);
    // faire boucle while aec modulo pour saut de ligne proportionnel
    var cpt = 0

    /*
    var cpt2 = 0;
    var len = bits.length+1;
    while(Math.sqrt(len) !== Math.round(Math.sqrt(len))){
      len++;
    }
    len = Math.sqrt(len);
    console.log(len);
    */

    /*for (var m = 0; m < bits.length; m++) {
      else { cpt = 0; }
    */
    var b = true;
    for (var k = 0; k < bits.length; k++) {
      if (k == 0) { cpt = 25; }
      for (var i = 0; i < bits[k].length; i++) {
        for (var j = 0; j < bits[k][i].length; j++) {
          // console.log("i : "+i+" j : "+j);

          if (bits[k][i][j] == 0) {
            if (b == true) {
              ctx.fillStyle = "#66CCE1";
            } else if (b == false) {
              ctx.fillStyle = "#B2F64C";
            }
            ctx.fillRect(cpt+(j*5),/*cpt2+*/(i*5),5,5);
          } else if (bits[k][i][j] == 1) {
            if (b == true) {
              ctx.fillStyle = "#AA7DFC";
            } else if (b == false) {
              ctx.fillStyle = "#FF5794";
            }
            ctx.fillRect(cpt+(j*5),/*cpt2+*/(i*5),5,5);
          } else if (bits[k][i][j] == 2) {
            ctx.fillStyle = "#FAA23D";
            ctx.fillRect(cpt+(j*5),/*cpt2+*/(i*5),5,5);
          }
        }
      }
      b = !b;
      cpt+=25;
    }
      /*cpt2+=50;*/
    /*}*/
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
}

// window.addEventListener("load", draw);
input = document.getElementById("chaine");
val = document.getElementById("send");
val.addEventListener("click", draw);
suppr = document.getElementById("delete");
suppr.addEventListener("click", clear);
// input.addEventListener("input", draw);