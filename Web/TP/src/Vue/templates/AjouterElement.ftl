<html>
	<head>
		<meta charset="utf-8">
	</head>
	<body>
		<!-- Formulaire simple qui enverra une requête POST -->
		<form action="" method="post">
			<SELECT name="nom" size="1">
				<#list listes as liste>
		      		<OPTION>${liste.titre}
		    	</#list>
			</SELECT>
		  <label for="">Nom de la liste :</label>
		  <input id="PostNomListe" type="text" name="name">
		  <label for="DescriptionListe">Description :</label>
		  <input for="PostDescription">
		  <input type="submit" value="Enregistrer">
		</form>
	
	</body>
</html>