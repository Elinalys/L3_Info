<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
	</head>
	<body>
		<p><a href="/">Accueil</a></p>
		<h1>Tous les éléments de toutes les listes</h1>
		<#list listes as liste>
	    <section>
  			<h2>${liste_index + 1}. ${liste.titre}</h2>
  			<p><em>${liste.description}</em><p>
  			<ul>
  				<#list liste.elements as element>
  					<li><strong>${element.titre}</strong>, <em>${element.description}</em></li>
  				</#list>
  			</ul>
	 	  </section>
	  </#list>
	</body>
</html>