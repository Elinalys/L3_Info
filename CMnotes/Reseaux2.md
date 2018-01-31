# Reseaux 

(...)

## Codes correcteurs

distance de Hamming (entre 2 mots)

			011011011
	xor	011111100
			---------
			000100111

→ 4 bits de différences, on peut le modèliser comme 4 erreurs

Code = ensemble de mots "valides"

Poids de Hamming d'un code : distance minimale entre 2 mots du code.

Exemple, bit de parité : `01110111`, poids = 2, si l'on fait une erreur plus valide mais 2 OK.

*n = m + r*, 2^n mots, 2^m valides.

Détection : comment détecter d erreurs ?
➡ code avec un poids de d + 1
Correction : comment corriger d erreurs ?
➡ code avec un poids de 2d + 1

Code de Hamming ... Parité paire, on rajoute les bits de controle au puissances de 2

Permet de corriger 1 erreur, optimal dans la taille de r.

## Codes détecteurs 

### Codes polynomiaux (ex : CRC)

basés sur Arithmétique polynomiale modulo 2. (+ = -)

Emetteur et récepteur se mettent d'accord sur un polynôme générateur G(x) de degré r+1.

Message à emettre = M(x)
Message transmis T(x) = x^(r+1), M(x) + R(x) avec R(x) = reste de la division de x^(r+1)×M(x) par G(x)

**Propriété** : T(x) est divisible par G(x)

