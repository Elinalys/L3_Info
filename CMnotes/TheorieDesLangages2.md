# Chapitre 1 Grammaires NON contextuelles

- CM 14h
- TD 14h
- TP 16h

## Contenu 

- Langages non contextuels 
	- Automates à Pile
	- Grammaires non contextuelles
	- Forme normale de Chomsky
- Compilation
	- Analyse lexicale
	- Analyse syntaxique
	- Génération de code

## Ressources 

- Algo, Sethi, Ullman Compilations : Principes Techniques et Outils (2000)

## Lemme de l'étoile

### Théorème 

Si L est 1 langage (non trivial) régulier alors ∃ 1 entier p

∀w ∃ xyz tq xy^iz appartient à L
|w|>=  w = wyz avec |xy| <= p, |y| > 0

### Idée de preuve

(q0) → → → →  
			<= p

p = taille de l'automate après avoir lu p lettres de w, vous avez forcément revenir à 1 état déjat recentré.

Pour montrer qu'un 1 langage est non réguler, il suffit de montrer qu'un 1 tel p n'existe pas

### Exemple 

L = {0^n 1^n | n ∈ N}

Supposons qu'il existe 1 p tq ∀ w < L, |w| >= p, ∃ xyz, avec w = xyz
 																															xy^iz ∃ L, ∀ i

Prenons w = 0^p 1^p
	Par le lemme de l'étoile ∃ xyz tq w = xyz, |xy| =< p
	→ xy est composé que de 0. Prenons xy^2z. Celui ci par le lemme de l'étoile appartient à L. Or xy^2 a plus de 0 que xyz et le même nombre de 1 que xyz

Par conséquent, le nombre de 0 dans xy²z > p Contradiction

## Automates à pile

### Rappel automates pour langages réguliers

Z = alaphabet, L inclut dans Σ*

1 automate c'est 1 tuple (Σ,Q,δ,I,F)

- Q = l'ensemble des états (fini)
- I inclut dans Q = l'ensemble des états initiaux
- F "		" Q = " " "	" finaux
- δ = Q × Σ → 2^Q

Une exécution d'1 automate A sur 1 mot w est la séquence (q0, a0, q1, a1, q2, a2, ... , qn) teq que q0 ∈ I, w = a0, a1, ..., an la s"quence et ∀ A <= i <= n, qi ∈ δ (qi-1, ai-1)

#### Exemple 

Si w = a0, a1, a2, a3

1 exécution est par exemple (q0, a0, q1, a1, q2, a3, q4)

Le mot w est dit accepté par A si il existe ue exécution (q0, a0, q1, a1, ..., qn, an, qn+1) de w sur 1 tellque qn+1 appartient ) F

Le langage de 1, noté L(A), c'est {w appartient ) Σ* | w est accepté par A}

On dit que L est *reconnaissable* si il existe 1 automate A tel que L(A) = L

1 automate A = (Σ, Q, δ, I, F) est dit deterministe ∀ q appartenant à Q, ∀ a appartenant à Σ, |δ (q, a)| = 1

#### Remarque

Si A est un automate déterministe, alors ∀w, ∃ 1 unique exécution de 1 sur w (preuve simple)

#### Théorème 

Pour tout automate A, il existe 1 automate déterministe A' tel q L(A) = L(A')

De plus, A' peut être calculé en temps f(|A|), pour 1 certaine fonction, par contre |A'| peut être proportionnel à 2^|A|

#### Corrolaire

Pour 1 automate A, on peut tester en temps O(|w|), si w appartient à L(A).

### Automates à pile

Prenons L = {w | nb 0 = nb de 1}

#### un algorithme pour reconnaitre ce langage

	Pour i ← 0 à |w| - 1 Faire
		si w[i] != {0, 1} Alors
			return False
		sinon si w[i] = 0 alors cpt++
		sinon cpt--

	si (cpt = 0) alors
		return true
	else
		return false

#### Algo 2

	Vérifier que w est sur {0, 1}
	while (i < |w|) do {
		si w[i] = 0 alors
			empiler(p, w[i])
		sinon dépiler(p)
	}
	si pileVide(p) alors
		return True
	else return False

Les automates à piles sont des machine qui font à peut près le travail de l'algorithme 2

#### Définition formelle des automates à Pile

Si Σ 1 alphabet, on note Σ_ε = Σ U {ε}

1 automate à pile est 1 tuple (Σ, Γ, Q, δ, {q0}, F)

- Σ_ε est l'alphabet fini de mots
- Γ_ε est l'alphabet fini utilisé sur la pile (valeurs manipulées par la pile) 
- Q est l'ensemble fini des états
- δ : Q × Σ_ε × Γ_ε → 2^(Q U Γ_ε) fonction de transition (Si V est un ensemble, 2^V ensemble des sous-ensembles de V)
- q0 appartient à Q est létat initial
- F inclut dans Q l'ensemble des états finaux

#### Exécution sur 1 mot de A

Prenons w = w1, w2, ..., wn

1 exécution de A sur w est une séquence (q0, q1, ..., qm), (s0, s1, ..., sm), qi appartient à Q, si appartient à Γ_ε

- s0 = ε
- Pour tout 0 <= i <= m-1, (qi+1, b) appartient à δ(q1, wi+1, a) et si = at, si+1 = bt a,b appartient à Γ_ε, t appartient à Γ*
- qm appartient à F

Si 1 pile p contient les valeurs ... on va le représenter par le mot a0, a1, a2, ..., an (téte de pile vers le haut)

(...) 

### Grammais non contextuells

On commence par cet exemple 

A → 0A1 ← règle
A → B
B → #

Une grammaire est constituée de *règless de subsititution* appelées des fois *productions*.

Pour une règle de type 

A (variables appelée non terminal) → 0A1 (mots composés de non terminaux et de constantes appelées terminaux)

Appliquer une règle c'est remplacer un non terminal par le mot se trouvant à droite de la règle.

On a toujours une variable de début et la convention c'est le non terminal de la prmière regle.

Dans notre exmple c'est A

La séance de substitutions qui permet de vérifier qu'un mot est généré par la grammaire est appelée dérivation

*Exemple :* A → 0A1 → 00A11 → 000A111 → 000B111 → 000#111

L'arbre de dérivation de 000#111 est la représentation sous forme d'arbre des différentes règles appliquées pour générer 000#111

				A
		/		|		\
	 0		A    1
		 / 	|	 \
		0   1   1
			/	|	\
		 0	A  1
				|
				B
				|
				#

L'ensemble des mots que l'on peut générer avec la grammaire est appelé le **langage de la grammaire**

*Exemple :* Le langage de notre grammaire c'est {0^n#1^n | n >= 1}

Une grammaire non contextuelle est un tuple (V, Σ, R, S)

1. *V* est un ensemble de variables (appelé souvent non-terminaux)
2. *Σ* est un ensemble disjoint de V appelé ensemble de terminaux (on peut voir cela commes des constantes)
3. *R* est l'ensemble des règles, une règle est composée d'une variable et d'un mot sur l'alphabet V U Σ. Une règle est notée X → ω où X est la variable et ω appartient (V U Σ)*
4. *S* est la variable de début.

Si u,v,ω appartient (V U Σ)*

A appartient V, A → ω appartient à R

On dira que uAv génère uωv

On le note : uAv → uωv

On dria que u génère v si u = v où il existe u1,...,uk tels que 
	u → u1 → u2 → u3 → ... → uk → v

On le note u →* v

Le langage d'une grammaire G = (V, Σ, R, S) est l'ensemble {ω appartient Σ* | S →* ω}

*Exemple :* Les mots correctement parenthèsés

S → aSb			a = "(" b = ")"
S → SS
S → ε

**Fait** : Tout langage régulier est reconnu par une grammaire non contextuelle
	**Preuve** : Pour L un langage régulier et 1 tq L(A) = L
	Notons (q0, q1,..., qn)
	(...)


### Langages ambigus

### Forme normale de CHomsky

analyseur lex