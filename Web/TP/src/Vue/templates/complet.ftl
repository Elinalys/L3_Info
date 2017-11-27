<html>
	<head>
		<meta charset="utf-8">
		<title>interface liste web</title>
	</head>
	<body>
		<h1>Tous les élements de toutes les listes</h1>
		<ul>
	    	<#list listes as liste>
	      		<li>${liste_index + 1}
	      			<h2>${liste.titre}</h2>
	      			<p><em>${liste.description}</em><p>
	      			<ul>
	      				<#list liste.elements as element>
	      					<li>${element.titre}</li>
	      				</#list>
	      			</ul>
	 	  		</li>
	    	</#list>
	    </ul>
	</body>
</html>