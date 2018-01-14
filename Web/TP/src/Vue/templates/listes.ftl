<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
	</head>
	<body>
		<h1>Listes</h1>
		<table>
			<tr>
				<td>&nbsp;</td>
				<th>Titre</th>
				<th>Description</th>
			</tr>
			<#list listes as liste>
				<tr>
					<td>${liste_index + 1}</td>
					<td><a href="liste/${liste.titre}">${liste.titre}</a></td> 
					<td><em>${liste.description}</em></td>
					<td><a href="/supprimerListe/${liste.titre}">Supprimer</a></td>
				</tr>
			</#list>
		</table>
		<p><a href="/complet">Tous les éléments de toutes les listes</a></h2b>
		<h2>Créer une nouvelle liste</h2>
		<form action="/creerListe" method="post">
			<label for="nomListe">Nom de la liste :</label>
	  		<input id="postNomListe" type="text" name="titreListe">
			<label for="descriptionListe">Description :</label>
			<input for="postDescriptionListe" type="text" name="descriptionListe">
			<input type="submit" value="Enregistrer">
		</form>
		<h2>Ajouter un élément</h2>
		<form action="/creerElement" method="post">
			<label for="listeElement">Nom de la liste</label>
			<select id="titreMaListe" name="titreMaListe" size="1">
				<#list listes as liste>
		      		<option value="${liste.titre}">${liste.titre}</option>
		    	</#list>
			</select>
		  	<label for="nomElement">Nom de l'élément :</label>
		  	<input id="titreElement" type="text" name="titreElement">
		  	<label for="descriptionELement">Description :</label>
		  	<input id="descriptionElement" type="text" name="descriptionElement">
		  	<input type="submit" value="Enregistrer">
		</form>
		<h2>Modifier liste</h2>
		<form action="/modifierListe" method="post">
			<label for="listeElement">Nom de la liste</label>
			<select id="titreMaListe" name="titreMaListe" size="1">
				<#list listes as liste>
		      		<option value="${liste.titre}">${liste.titre}</option>
		    	</#list>
			</select>
		  	<label for="nomListe">Nouveau nom :</label>
		  	<input id="titreListe" type="text" name="titreListe">
		  	<label for="descriptionListe">Nouvelle description :</label>
		  	<input id="descriptionListe" type="text" name="descriptionListe">
		  	<input type="submit" value="Enregistrer">
		</form>
	</body>
</html>