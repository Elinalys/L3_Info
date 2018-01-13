<html>
	<head>
		<meta charset="utf-8">
		<title>${title}</title>
	</head>
	<body>
		<h1>${liste.titre}</h1>
		<p><em>${liste.description}</em></p>
		<ul>
			<#list liste.elements as element>
				<li>${element.titre} <em>${element.description}</li>
			</#list>
		</ul>
	</body>
</html>