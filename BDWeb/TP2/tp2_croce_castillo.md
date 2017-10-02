# Rendu G3 TP2

## Exercice 1

1.  `xmllint --xpath "/Contacts" contacts.xml`
Affiche la balise *Contacts* et son contenu.

2. `xmllint --xpath "/Contacts/Person" contacts.xml`
Affiche toutes les balises *Person*.

3. `xmllint --xpath "//Person[Firstname='John']" contacts.xml`
Affiche toutes les balises *Person* qui ont un fils nommé Firstname avec une valeur John.

4. `xmllint --xpath "//Person[Email]" contacts.xml`
Affiche toutes les balises *Person* qui contiennent une balise *Email*.

5. `xmllint --xpath "/Contacts/Person[1]/Firstname/child::text()" contacts.xml`
Affiche la valeur de l'attribut *Firstname* de la premiere personne.

6. `xmllint --xpath "/Contacts/Person[1]/Firstname/text()" contacts.xml`
Affiche la valeur de l'attribut Firstname de la premiere personne.
Le résultat est le même que la requête précédente.

7. `xmllint --xpath "/Contacts//Address[@type='home']//Street/child::text()" contacts.xml`
Affiche la rue de chaque adresse avec un attribut type qui a une valeur home.

8. `xmllint --xpath "/Contacts//Address[@type='home' and City='London']" contacts.xml`
Affiche la balise adresse, quand son attribut type a pour valeur home, et que sa balise City a la valeur London.

9. `xmllint --xpath "/Contacts//Address[@type='work' and City='Dublin']/parent::node()/Lastname/text()" contacts.xml`
Affiche le contenu de la balise lastname, quand sa balise parente a une adresse avec un attribut nommé type, qui a pour valeur work, et une balise City qui a pour valeur Dublin.

10. `xmllint --xpath "/Contacts//Address[@type='work' and City='Dublin']/../Lastname/text()" contacts.xml`
Le résultat est le même que la requête précédente, la manière de parcourir l'arborescence est différente.

11. `xmllint --xpath "/Contacts[.//Address[@type='work' and City='Dublin']]//Lastname/text()" contacts.xml`
Le résultat est le même que la requête précédente, la manière de parcourir l'arborescence est différente.
Dans la question 9, on accède à la balise Lastname de la balise parente de la balise Address.
Dans la question 10, on ne s'intéresse pas à la localisation du Lastname, s'il y en a à plusieurs endroits dans la balise Person et ses enfants, on les verra aussi. De même pour la 11.

12. `xmllint --xpath "/Contacts//Address[@type='work']/ancestor::node()" contacts.xml`
On accède au noeud, de la balise parent de la balise qui contient une balise adresse avec un attribut nommé type qui a une valeur 'work'. (Donc on affiche Contacts).

13. `xmllint --xpath "/Contacts/Person[Lastname='Smith']/following-sibling::node()/Lastname/text()" contacts.xml`
On accède à la valeur de la balise Lastname de la balise personne qui suit la personne qui a comme Lastname : Smith. (Dunne)


14. `xmllint --xpath "/Contacts/Person[following-sibling::node()/Lastname='Dunne']/Lastname/text()" contacts.xml`
Pareil que la question 13.

## Exercice 2

1. `xmllint --xpath "//composition" cd.xml`

2. `xmllint --xpath "//performance[soloist]/composition" cd.xml`

3. `xmllint --xpath "//performance[not(soloist) and count(orchestra)<2 and count(orchestra)>0]" cd.xml`

4. `xmllint --xpath "//CD[..//orchestra='London Symphony Orchestra' and publisher='Deutsche Grammophon']/performance/soloist" cd.xml`

5. `xmllint --xpath "//CD[performance/orchestra='London Symphony Orchestra']" cd.xml`

## Exercice 3

1. `xmllint --xpath "//award[5]/title/text()" booker.xml`

2. `xmllint --xpath "//award[6]/author/text()" booker.xml`

3. `xmllint --xpath "//award[year='2000']/title/text()" booker.xml`

4. `xmllint --xpath "//award[title='Possession']/author/text()" booker.xml`

5. `xmllint --xpath "//award[author='J M Coetzee']/title/text()" booker.xml`

6. `xmllint --xpath "//award[year>1995]/author/text()" booker.xml`

7. `xmllint --xpath "count(//award)" booker.xml`


## Exercice 4

1. `xmllint --xpath "//titre" recettes1.xml`
`xmllint --xpath "//titre" recettes2.xml`

2. `xmllint --xpath "//nom_ing" recettes1.xml`
`xmllint --xpath "//ingredient[@nom]" recettes2.xml`

3. `xmllint --xpath "//recette[2]/titre" recettes1.xml`
`xmllint --xpath "//cuisine/recette[2]/titre" recettes2.xml`

4. `xmllint --xpath "//recette[last()]/titre" recettes1.xml`
`xmllint --xpath "//cuisine/recette[last()]/titre" recettes2.xml`

5. `xmllint --xpath "count(//cuisine/recette)" recettes1.xml`
`xmllint --xpath "count(//cuisine/recette)" recettes2.xml`

6. `xmllint --xpath "//cuisine/recette[count(ingredients/ingredient) < 7]" recettes1.xml`
`xmllint --xpath "//recette[count(ingredients/ing-recette) < 7]" recettes2.xml`

7. `xmllint --xpath "//cuisine/recette[count(ingredients/ingredient) < 7]/titre/text()" recettes1.xml`
`xmllint --xpath "//cuisine/recette[count(ingredients/ing-recette) < 7]/titre/text()" recettes2.xml`

8. `xmllint --xpath "//cuisine/recette[ingredients/ingredient/nom_ing='farine']" recettes1.xml`
`xmllint --xpath "//cuisine/recette[ingredients/ing-recette/@ingredient='farine']" recettes2.xml`

9. `xmllint --xpath "//cuisine/recette[categorie='Entrée']" recettes1.xml`
`xmllint --xpath "//cuisine/recette[starts-with(@categ,'entree')]" recettes2.xml`


## Exercice 5

1. `xmllint --xpath "count(plist/dict/dict/dict)" iTunes-Music-Library.xml`

2. `xmllint --xpath "plist/dict/dict/dict/key[text()='Album']/following-sibling::string[1]/text()" iTunes-Music-Library.xml`
`ledit xmllint --shell iTunes-Music-Library.xml`

3. `xmllint --xpath "//key[text()='Genre']/following-sibling::string[1]" iTunes-Music-Library.xml`

4. `xmllint --xpath "count(plist/dict/dict/dict/key[text()='Genre']/following-sibling::string[1][text()='Jazz'])" iTunes-Music-Library.xml`

5. `xmllint --xpath "//key[text()='Genre']/following-sibling::string[1][not(following::key[text()='Genre']/following-sibling::string[1] = .)]/text()" iTunes-Music-Library.xml`


6. `xmllint -xpath "//dict[key[text()='Play Count']]/./key[text()='Name']/following-sibling::string[1]" iTunes-Music-Library.xml`

7. `xmllint -xpath "plist/dict/dict/dict[not(key[text()='Play Count'])]/./key[text()='Name']/following-sibling::string[1]" iTunes-Music-Library.xml`

8. `xmllint -xpath "//key[text()='Year']/following-sibling::integer[1][not(.>//key[text()='Year']/following-sibling::integer[1]/text())]/../key[text()='Name']/following-sibling::string[1]/text()" iTunes-Music-Library.xml`
