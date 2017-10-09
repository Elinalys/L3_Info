<CATALOG>
{for $m in doc("plant_catalog.xml")//PLANT
    for $n in doc("plant_families.xml")//FAMILY
    where $m/BOTANICAL = $n/SPECIES
    return <PLANT>
    				{$m/*}
            <FAMILY>{$n/NAME/text()}</FAMILY>
           </PLANT>}
</CATALOG>