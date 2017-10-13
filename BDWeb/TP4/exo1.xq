<html>
 <body>
    <table border="1">
        <thead>
            <tr><th>Maisons</th><th>Surfaces (m2)</th></tr>
        </thead>
        <tbody>
			{
			for $x in doc("maisons.xml")/maisons/maison
			return 
				<tr>
					<td>Maison {data($x/@id)}</td>
					<td>{sum($x//@surface-m2 except $x//alcove/@surface-m2)}</td>
				</tr>
			}
		</tbody>
	</table>
 </body>
</html> 
