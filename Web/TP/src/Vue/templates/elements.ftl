<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
	</head>
	<body>
		<h1>Tous les éléments </h1>
		<ul>
			<#list elements as element>
				<li>${element.titre}</li>
			</#list>
		</ul>		
	</body>
</html>