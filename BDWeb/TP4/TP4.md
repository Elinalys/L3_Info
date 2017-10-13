# TP4

## Exercice 1

```html
<html>
 <body>
    <table border="1">
        <thead>
            <tr><th>Maisons</th><th>Surfaces (m2)</th></tr>
        </thead>
        <tbody>
			{
			for $x in doc("maisons.xml")/maisons/maison
			return 
				<tr>
					<td>Maison {data($x/@id)}</td>
					<td>{sum($x//@surface-m2 except $x//alcove/@surface-m2)}</td>
				</tr>
			}
		</tbody>
	</table>
 </body>
</html> 
```

## Exercice 2

### 1

#### a
Affiche le nombre total de déclarations rdf:type.
Exemple :
rdf:type humans:Man, humans:Researcher ;
Correspond à deux déclarations.

#### b

```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
SELECT ?type WHERE
{
?c humans:name "John" .
?c rdf:type ?type .
}
```

### 2

Retourne les instances ayant une arete hasSpouse.

### 3 

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?x WHERE
{
?x humans:age ?age .
FILTER (xsd:integer(?age) > "20"^^xsd:integer)
}
```

### 4

#### a

```	
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?x ?pointure WHERE
{
?x rdf:type humans:Person .
?x humans:shoesize ?pointure.
}
```

#### b

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?x ?pointure WHERE
{
?x rdf:type humans:Person .
OPTIONAL { ?x humans:shoesize ?pointure. }
}
```

#### c

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?x ?pointure WHERE
{
?x rdf:type humans:Person .
OPTIONAL
{
?x humans:shoesize ?pointure.
FILTER (xsd:integer(?pointure) > "8"^^xsd:integer)
}
}
```

#### d

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?x ?pointure ?chemise WHERE
{
?x rdf:type humans:Person .

{
?x humans:shoesize ?pointure.
FILTER (xsd:integer(?pointure) > "8"^^xsd:integer)
}
UNION
{
?x humans:shirtsize ?chemise .
FILTER (xsd:integer(?chemise) > "12"^^xsd:integer)
}
} 
```

### 5

#### a

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT * WHERE
{
?x humans:name "John".
?x ?links ?p.
}
```

#### b

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
DESCRIBE ?x WHERE
{
?x humans:name "John".
}
```

### 6 

#### a

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?parent ?enfant WHERE
{
{?enfant humans:hasParent ?parent.}
UNION
{?enfant humans:hasFather ?parent.}
UNION
{?parent humans:hasChild ?enfant.}
UNION
{?enfant humans:hasMother ?parent.}
}
```

#### b

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?parent WHERE
{
{?enfant humans:hasParent ?parent.}
UNION
{?enfant humans:hasFather ?parent.}
UNION
{?parent humans:hasChild ?enfant.}
UNION
{?enfant humans:hasMother ?parent.}
}
```

#### c

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT DISTINCT ?parent WHERE
{
{?enfant humans:hasParent ?parent.}
UNION
{?enfant humans:hasFather ?parent.}
UNION
{?parent humans:hasChild ?enfant.}
UNION
{?enfant humans:hasMother ?parent.}
}
```

#### d

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT DISTINCT ?parent WHERE
{
?parent rdf:type humans:Man.

MINUS {?enfant humans:hasParent ?parent. }
MINUS {?enfant humans:hasFather ?parent.}
MINUS {?parent humans:hasChild ?enfant.}
MINUS {?enfant humans:hasMother ?parent.}
}
```

#### e

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT ?mere ?enfant WHERE
{
?mere rdf:type humans:Woman.
?mere humans:hasSpouse ?x.

OPTIONAL {?mere humans:hasChild ?enfant.}
OPTIONAL {?enfant humans:hasMother ?mere.}
}
```

### 7

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT DISTINCT ?x ?y WHERE
{
?x humans:shirtsize ?taille.
?y humans:shirtsize ?taille.
FILTER (?x != ?y)
}
```

### 8

#### a

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
CONSTRUCT {?x humans:hasFriend ?y } WHERE
{
{?x humans:hasFriend ?y.}
UNION
{?y humans:hasFriend ?x.}
}
```

#### b

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
CONSTRUCT { ?x humans:hasFriend ?y. }
WHERE
{
?x (humans:hasFriend|^humans:hasFriend)+ ?y
}
```

### 9

```
PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
PREFIX instance: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
SELECT DISTINCT ?y WHERE
{
?y rdf:type ?l.
MINUS {?y rdf:type humans:Man. }
} 
```