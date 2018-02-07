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

## Résultats théoriques

Si G(x) = (1+x)p(x) avec p(x) contenant au moins 3 termes (degré = n)
1. On détecte toutes les erreurs simples
2. On détecte toutes les erreurs doubles
3. On détecte toutes les erreurs impaires
4. On détecte tous les paquets d'erreurs de taille < n
5. Paquts d'erreurs de tailles n+1, détectés avec une probabilité de 1-(1/2^(n-1))
6. Paquets de taille > n+1, proba : 1-(1/2^n)

CRC - 12 : x^12 + x^11 + x^3 + x^2 + x + 1
CRC - 16 :  x^16 + x^15 + x^2 + 1
CRC - CCITT : x^16 + x^12 + x^3 + 1

## Contrôle de flux 

Hypothèses simples :
- transmission par trames
- Pas d'erreurs
- Trames reçues en fifo

1. Stop and wait :
	- émission d'une trame
	- attente d'un acknowledgement
2. Sliding window protocol :
	- numérotation de bits

Canal bruité :
- Trames peuvent se perdre (y compris 1AC)
- Trames peuvent être endommagées

- Détection d'erreurs
- ACK
- NAK + retransmission
- Retransmission après **timeout**

Mécanisme ARQ (AUtomatic Repeat Request)

1. Le plus simple : stop & wait ARQ

2. GO BAck ARQ 
	basé sur SWP
	En cas d'eeur on restransment tout à partir de la trame erronée
	Taille fenêtre 2^k - 1 pour détecter duplication

3. Rejet selectif ARQ
	On ne réémet que les trames erronées
	Taille fenre 2^k-1


