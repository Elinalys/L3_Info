<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
	</head>
	<body>
		<h1>Toutes les listes</h1>
		<ul>
	    	<#list listes as liste>
	      		<li>${liste_index + 1}
	      			<h2>${liste.titre}</h2>
	      			<p><em>${liste.description}</em><p>
	 	  		</li>
	    	</#list>
		</ul>
		<h2>Créer une nouvelle liste</h2>
		<!-- Formulaire simple qui enverra une requête POST -->
		<form action="" method="post">
		  <label for="NomListe">Nom de la liste :</label>
		  <input id="PostNomListe" type="text" name="name">
		  <label for="DescriptionListe">Description :</label>
		  <input for="PostDescription">
		  <input type="submit" value="Enregistrer">
		</form>
		<h2>Ajouter un élement</h2>
	</body>
</html>