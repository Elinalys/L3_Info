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
				<li><strong>${element.titre}</strong> : <em>${element.description}</em> Créé le ${element.dateCreation}. Modifié le ${element.dateModification} <a href="/supprimerElement/${liste.titre}/${element.titre}">supprimer</a></li>
			</#list>
		</ul>
	</body>
</html>