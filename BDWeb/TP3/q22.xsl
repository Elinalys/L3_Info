<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    >
  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
  
  <xsl:template match="/">
  	<CATALOG>
     <xsl:apply-templates select="//PLANT"/>
    </CATALOG>
  </xsl:template>
  <xsl:template match="PLANT">
  	<PLANT>
	  	<xsl:copy-of select="*[name() != 'LIGHT']"/>
  	</PLANT>
	</xsl:template>
</xsl:stylesheet>
