<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
		<style type="text/css">
/*			table, th, td, tr {
				text-align: center;
			}*/
		</style>
	</head>
	<body>
		<h1>Toutes les listes</h1>
		<table>
			<tr>
				<td>&nbsp;</td>
				<th>Titre</th>
				<th>Description</th>
			</tr>
			<#list listes as liste>
				<tr>
					<td>${liste_index + 1}</td>
					<td><a href="liste/${liste.titre}">${liste.titre}</a></td> <!-- n'affiche pas les élements... -->
					<td><em>${liste.description}</em></td>
				</tr>
			</#list>
		</table>
		<h2>Créer une nouvelle liste</h2>
		<!-- Formulaire simple qui enverra une requête POST -->
		<form action="/creerListe" method="post">
		  <label for="NomListe">Nom de la liste :</label>
		  <input id="PostNomListe" type="text" name="titreListe">
		  <label for="DescriptionListe">Description :</label>
		  <input for="PostDescriptionListe" type="text" name="descriptionListe">
		  <input type="submit" value="Enregistrer">
		</form>
		<h2>Ajouter un élement</h2>
		<!-- Formulaire simple qui enverra une requête POST -->
		<form action="/creerElement" method="post">
			<label for="listeElement">Nom de la liste</label>
			<select id="titreMaListe" name="titreMaListe" size="1">
				<#list listes as liste>
		      <option value="${liste.titre}">${liste.titre}</option>
		    </#list>
			</select>
	  	<label for="NomElement">Nom de l'élément :</label>
	  	<input id="titreElement" type="text" name="titreElement">
	  	<label for="DescriptionELement">Description :</label>
	  	<input id="descriptionElement" type="text" name="descriptionElement">
	  	<input type="submit" value="Enregistrer">
		</form>
		
	</body>
</html>