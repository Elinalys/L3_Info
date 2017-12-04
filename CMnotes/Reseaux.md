# Réseaux

## plan 
	192.168.0.0CM
	192.168.0.32CM
	192.168.0.064M
	192.168.0.0C96
	192.168.0.0C128
	192.168.0.0C160
	192.168.0.0C202
	192.168.0.0C234
	
1. Chap 1 : Généralités

2. Chap 2 : Pile protocolaire
	1. Accès réseaux
	2. Réseau IP
	3. Transport TCP UDP & Application
	4. Sécurité des réseaux

## Calcul binaire : exercice
6. L'adresse broadcast de chaque sous réseau :
	192.168.0.31
	192.168.0.63
	192.168.0.95
	192.168.0.127
	192.168.0.159
	192.168.0.201
	192.168.0.233
	192.168.0.255
		01110110
	+ 11101101
	----------
	 101100011


	 2+4+16+32+64
	 1+4+8+32+64+128

	 	101011
	-	011110
	--------
		001101	

	1+2+8+32
	16+8+4+2

	73

			1110
		x 1111
	--------
	11010010

	10011000  | 101
						|-----
						|11110
						|
						|
						|
						|
						|

## Détéctions d'erreurs

	11010100|101
					|---
					|....
	10			|

Reste = 10, on envoit 11010110 (message + reste)

## IPV4 exos

1. ip 190.24.12.8 masque 255.255.0.0 on convertit en binaire et on fait &.

→ 190.24.0.0

2. 10.0.0.0

(...)

## Sous-réseau : exercices

1. Adresse réseau `172.16.0.0` (Class B), 12 sous réseaux à créer.
	1. Nombres de bits utilisés pour créer les sous réseaux : 4
	2. Nombre de sous réseaux réellement créés : 2^4
	3. Masque de sous réseau : 8 (255.255.0.0 donc /16) + 4 (2^4) = 16 + 4 = 20  -> 255.255.240.0 
	4. Nombre max de sous réseau : 2^12 - 2 (10101100.00010000.0000|0000.00000000, gauche NetId droite HostId)

2. Adresse réeseau `192.168.0.0` (classe C), 20 machines max
	1. Nombre max d'equipements : 2^8 - 2
	2. Nombre de bits à reserver pour l'adressage des machins, on utilise 5 bits pour l'adressage des machine (20 -> 10100). 2^5 - 2
	3. Nombre de sous réseaux créés : il reste 3 bits pour le sous réseau, 2^3
	4. Masque de Sous-réseau : 255.255.255.224.
	5. Les plages d'adresse pour chaque Sous-réseau : 
		192.168.0.0 à 30
		192.168.0.32 à 63
		192.168.0.64 etc.
		192.168.0.96
		192.168.0.128
		192.168.0.160
		192.168.0.192
		192.168.0.224
	6. L'adresse broadcast de chaque sous réseau :
		192.168.0.31
		192.168.0.63
		192.168.0.95
		192.168.0.127
		192.168.0.159
		192.168.0.201
		192.168.0.223
		192.168.0.255

## Détection d'erreurs : Cyclic Redundancy Check : envoie

	11010011101100000 / 1011
	1011
	----
	01100
	 1011
	 -----
	 01110
	  1011
	  ----
	  01011
	   1011
	   ----
	   00001101
	       1011
	       ----
	       01100
	        1011
	        ----
	        01110
	         1011
	         ----
	         01010
	          1011
	          ----
	          000100

	11010011101100100 / 1011
	1011
	----
	01100
	 1011
	 -----
	 01110
	  1011
	  ----
	  01011
	   1011
	   ----
	   00001101
	       1011
	       ----
	       01100
	        1011
	        ----
	        01110
	         1011
	         ----
	         01011
	          1011
	          ----
	          000000

