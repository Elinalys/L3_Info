<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
	</head>
	<body>
		<p><a href="/">Accueil</a></p>
		<h1>${liste.titre}</h1>
		<p><em>${liste.description}</em></p>
		<ul>
			<#list elements as element>
				<li><strong>${element.titre}</strong> : <em>${element.description}</em> Créé le ${element.dateCreation}. Modifié le ${element.dateModification} <a href="/supprimerElement/${liste.titre}/${element.titre}">Supprimer</a></li>
			</#list>
		</ul>
		<h2>Modifier élément</h2>
		<form action="/${liste.titre}/modifierElement" method="post">
			<label for="listeElement">Nom de l'élément</label>
			<select id="titreMonElement" name="titreMonElement" size="1">
				<#list elements as element>
		      		<option value="${element.titre}">${element.titre}</option>
		    	</#list>
			</select>
		  	<label for="NomElement">Nouveau nom :</label>
		  	<input id="titreElement" type="text" name="titreElement">
		  	<label for="descriptionELement">Nouvelle description :</label>
		  	<input id="descriptionElement" type="text" name="descriptionElement">
		  	<input type="submit" value="Enregistrer">
		</form>
	</body>
	</body>
</html>