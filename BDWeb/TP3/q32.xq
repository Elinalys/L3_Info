<CATALOG>
(:Exposure est affiché à chaque fois, pour remedier à ça il faudrait faire une boucle imbriquée:)
{
	for $p in doc("plant_catalog.xml")/CATALOG/PLANT
	order by $p/LIGHT/text() descending
	return <EXPOSURE>{$p/LIGHT}<PLANT>
    				{$p/COMMON}
    				{$p/BOTANICAL}
    				{$p/ZONE}
    				{$p/PRICE}
    				{$p/AVAILABITY}
  				</PLANT></EXPOSURE>


}
</CATALOG>