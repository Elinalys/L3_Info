# Apprentissage artificiel

## Exercice 2

ACC = (41+12+18+17)/100
ERR = 1-ACC


*→ classe prédite ; ↓ classe réelle*

|       | P  | N  |
|:------|:---|:---|
| **P** | TP | FN |
| **N** | FP | TN |

|    | TP | FP | TN       | FN |
|:---|:---|:---|:---------|:---|
| A  | 41 | 3  | 12+18+17 | 4  |
| B  | 12 | 4  | 41+18+17 | 3  |
| C  | 18 | 2  | 41+12+17 | 3  |
| D  | 17 | 3  | 41+12+18 | 2  |

FPrate(A) = FP/(FP+TN) = 3/55
TPrate(B) = recall = sensibilité = TP/(TP+FN) = 12/16
FNrate(B) = FN/(FN+TP) = 4/45
TNrate(B) = TN/(TN+FP) = 3/15

Precision(C) = TP(C) /  (TP(C) + FP(C)) = 18/20
Rappel(D) = TP(D) / (TP(D) + FN(D)) = 17/18

Sensibilité(A) = Rappel(A) = TP / (TP+FN)

choixDeModele(matriceConfusion1, matriceConfusion2)

l1 = erreurDeGeneralisation(matriceConfusion1)
l2 = erreurDeGeneralisation(matriceConfusion2)

	si e1 < e2 alors
	|	afficher("Choisir modèle 1")
	sinon
	|	afficher("Choix modèle 2")
	fsi

## Exercice 3

1. ACC_M1 = 800/1000, Err_M1 = 1-ACC = 21

(...)

