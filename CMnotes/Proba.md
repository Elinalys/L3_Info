# Théorie de l'esimation et Tests d'hypothèses :

## Théorie de l'estimation

Dans la pratique on ne connait pas forcèment les valeurs de tous les paramètres du système étudié. On a parfois besoin d'estimer ces valeurs.
On utilise alors un échantillon d'observation poiur déterminer l'estimation du paramètre étudié.
On distingue deux types d'estimations : 
- l'estimation *ponctuelle* (pour estimer la meilleur valeur)
- l'estimation *par intervalle* (pour détermier un intervalle contenant la vraie valeur des paramètres avec une certaine probabilité d'erreur)

### Estimation ponctuelle 

**Définition :** Un estimateur T (qui est une fonction des observations) T ($X_1,\dots,X_n$) où $X_n,\dots,\X_n$ sont des variables aléatoires **indépendantes** de même loi qu'une certaine variable aléatoire X dont la loi dépend d'un certain paramètre σ.
T est dit sans biais si E(T) = σ
Soit T un estimateur sans biais de σ. la variance de T mesure l'écart entre les valeurs de T et σ. En effet σ² = E(T²)-(E(T))² = E(T²)-σ
**Définition :** Un estimateur T d'un pramètre σ est dit efficace s'il est sans biais et si 

$$\sigma^2_T \be \sigma^2_{T'} \forall T'$$ estimation sans biais de σ (estimation sans biais de variance minimum)

*Exemple :* Soit $X_1,\dots,X_n$ un échantillon d'une variable aléatoire X dont l'espérence est p

Soit $\bar{X}$ (...) 

Donc $\bar{X_n} est une estimation sans biais de p

(...)

**Méthode de maximum de vraisemblance :** C'est une méthode d'estimation ponctuelle qui permet de déterminer des estimateurs pour des paramètres vérifiant certaines propriétés. Soit $X_1,\dots,X_n$ un échantillon de n observation indépendantes d'une variable aléatoire X dont la loi dépend d'un paramètre σ donc la densité de proba est $f(x,\sigma)$.

**Définition :** On appelle fonction de vraisemblance associé à l'échantillon la fonction V(σ) définie par V(σ)=f(X_1,σ)...f(X_n,σ)

On cherhce alors la valeur $\bar{\sigma}$ qui maximise V(σ)

**Définition :** $\bar{\sigma}$est appelé l'estimateur du maximum de vraisemblance de σ (EMV)

**Définition :** Une fonction f(x) est dite *concave* si $\forall x, f(x)\be$
**Propriété :** Si V(σ) est concave, alors $\bar{\sigma}$ est la solution unique de dv/dσ = 0

