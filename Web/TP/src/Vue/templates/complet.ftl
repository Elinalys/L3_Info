<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
	</head>
	<body>
		<h1>Tous les éléments de toutes les listes</h1>
		<#list listes as liste>
	    <section>
  			<h2>${liste_index + 1}. ${liste.titre}</h2>
  			<p><em>${liste.description}</em><p>
  			<ul>
  				<#list liste.elements as element>
  					<li>${element.titre}</li>
  				</#list>
  			</ul>
	 	  </section>
	  </#list>
	</body>
</html>