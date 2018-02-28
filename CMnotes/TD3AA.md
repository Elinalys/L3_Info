# TD3

## Exercice 1

1. k : calcule la distance entres les voisins et les k voisins les plus proches et prend la classe majoritaire. Pour bayes on se base sur des probas qu'on connait mais pas la classe. On fait donc des probabilités conditionnelles, classe qui a la plus grande proba

2. Les deux sont des algos de classification. Bayes se base sur des probas et k-PPV se base sur des distances, Bayes fait l'hypothèse que les probas sont indépendantes

## Exercice 2

| Class | X1 | X2  |
|:------|:---|:----|
| +     | a  | 1.0 |
| +     | b  | 1.2 |
| +     | a  | 3.0 |
| -     | b  | 4.4 |
| -     | b  | 4.5 |

1. Ordinaire :
	P (c = +) = 3/5
	P (c = -) = 2/5
	Correction de laplace : (Évite la division par 0)
 	P(c = +) = (3+1)/(5+2)
	P(c = -) = (2+1)/(5+2)
	*Où 2 est le nombre de classes*

2. Ordinaire :
		P(X1 = a | +) = 3/5, P(X1 = b | +) = 1/5 
	Laplace :
		P(X1 = a | +) = 2+1/3+2, P(X1 = b | +) = 1+1/3+2
		etc.

3. 
Estimer P(X 2 = 1.0 | +), P(X 2 = 1.0 | -), P(X 2 = 4.5 | +), P(X 2 = 4.5 | -)
1. En discrétisant
2. En faisant l’hypothèse d’une distribution normale. Voir formule
