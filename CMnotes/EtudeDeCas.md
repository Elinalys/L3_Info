# Études de cas

Ici, mini-problèmes -> algorithme

<!-- refaire hierarchie titres -->

## 3 cas

1. Pb orienté optimisation et satisfaction des contraintes
2. Pb orienté "simulation"
3. Pb orienté "Apprentissag et réseaux de neurones"

## Problème orienté optimisation

### Pb n reines

	Input : Tableau n×n TAB a valeurs dans nombres entiers ̛̛supérieurs ou égaux à 0
		TAB[i,j] ...

	Output : Un placement de n "reines" sur l'échiquier (marquage de n cases)

		tant que on a pas 2 cases marquées sur une même ligne, colonne, diagonales
		l'argent correspondant aux cases marquées soit maximal

#### Étape 1

Établier une spécification formelle via un modèle linéaire (PLNE).
**modèle linéaire** : calculer un vecteur Z a valeurs dans des nombres (flottants ou entiers).
	tq (1) pour i dans J > 1..N, Z, doit petre entier
		 (2) pour i dans 1..K, Σ Ak,i, , Zi ?? Bk
		 (3) Maximiser Σ Ci Zi

		Input : 
			N
			J <= (1..N)
			Ak, k = 1..K
			Bk, k = 1..K
			Ci, i = 1..N

**Ce qu'il faut savoir** :
	* Les modèles linéaires → Beaucoup d'effort des editeurs de logiciels
		- Bibliothèque dédiées IBM (OSL, CPLEX), XPRESS, GUROBI
		- Bibliothèques de calcul généraliste MATLAB, SAS, ...
		- Logiciels de gestion d'entreprises : SAP
		- Langages : CHIP, Prolog, CP-optimizer
	* Ces bibliothèques peuvent traiter des modèles avec => 10^5 variables ou contraintes
	* Rôle renctral en Algo car Beaucoup de Problèmes pratiques peuent être exprimés via ce formalisme

##### Le cas des n reines

Vecteurs inconnu 

Z indexe sur les cases de l'echiquier

Z=(Zi,j , i = 1..N, j = 1..N) Entier scrictement entre 0 et 1 (exclues)...

Sémantique : Zi,j = 1 ~ on lace une reine en case (i, j)
										0 ~ on ne places pas

**Quantité a maximum (critere qualité)**

	ΣTAB[i,j] * Zi,j
	i = 1..N
	j = 1..N

**Contraintes**

1. je veux exactement n reines
	Σi,j Zi,j = n

2. Pas + de 1 reine par ligne
	Pour toute ligne j = 1..N
		Σ Zi,j =< 1

3. Pas + de 1 reine par colonne
	Pour toute colonne i = 1..N
		Σ Zi,j =< 1

4. Pas + de 1 reine pour une diagonale montante, 2n-1, diagonales montante

Pour tout k = -(n-1)..(n-1)

Σ Zi,i+k =< 1
i tq 1 <= i <= n
i tq 1 <= i+k <= n

2 types d'algos :
	- *exact* : Realise exactement la spécification
	- *approché* : Induisent une marge d'erreur 

Prendre en compte temps d'exec et critère de qualité de l'approximation dans le cas d'un algo approché (également la robustesse). 

###### Approché

**Remarque** : Il faut préciser "appoche" :
	- J'impose vraiment *n-reines* (sinon échec)
	- J'accepte moins de n-reine → maximiser l'argent donc pénalités (n - nbres de reines placées)

```
not stop;
tant que not stop faire
	choisir une case libre;
	si echec(choisir)
		alors stop;
	sinon
		placer une reine sur la case choisie;
		déduire le plus de choses possibles;
		mettre à jour stop;
```
→ algo glouton avec propagation de contraintes

Déduire des reines imposées, interdites

**Exercice** : Écrire algorithme pour réalistion ces déductions imposé/interdit
→ SDD, Algo (on ne prend pas en compte le gains ici)


- Un tableau deux dimensions i,j où :
	- 0 case interdite
	- -1 case libre
	- 1 case occupée
- Un tableau compteur ligne, en ligne j nbe de case libre
- statut ligne, statutligne[1] = 1, il y a une reine en ligne j
- Idem colonnes :
	- compteur colonnes
	- statut colonnes
- Liste de reines imposées (fait déclencheurs en propagation de contraintes) :
	- couples (i,j) correspond à des cases où une reine est imposée
	- ehec, booleen diagnostiquant les impossibilités

Algo : 

```
Echec ← 0;
LISTE ← case qi'on vient de choisir dans la boucle principale de l'algo;
Tant que (Not Échec) && (Liste != Nil) Faire
	(i,j) ← Tête(LISTE);
	LISTE ← Queue(LISTE);
	Pour toute case (i',j') telle que (i = i') || (j = j') || [(i+j) = (i'+j')] || [(i-j)=(i'-j')] Faire
	Si occupé[i',j'] = 1 alors Échec
	Sinon 
		Si (occupé[i',j'] = -1) alors 
			occupé[i',j'] = O;
			compteur_lignes[i'] ← compteur_lignes[i]-1
			compteur_colonnes[j'] ← compteur_colonnes[j]-1
			Si (compteur_lignes[i] = 0) && (statut_lignes[i'] = 0) alors ÉCHEC;
			Sinon
				Si (compteur_lignes[i'] = 1) && (statut_lignes[0] = 0)
					soit j0 ← unique j tq occupé [i',j] = 0;
					occupé[i',j0] ← 1;
					LISTE ← (i',  j0).LISTE;
					statut_lignes[i'] = 1;
					statut_colonnes[j0] = 1;
```