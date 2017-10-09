<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    >
  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
  
  <xsl:template match="/">
  	<xsl:for-each select="//LIGHT[not(.=preceding::*)]">
      <xsl:sort select="." order="descending"/>
      <LIGHT><EXPOSURE><xsl:value-of select="./text()"/></EXPOSURE>
      <xsl:apply-templates select="//PLANT">
        <xsl:with-param name="lumiere" select="."/>
      </xsl:apply-templates>
      </LIGHT>
    </xsl:for-each>
  </xsl:template>
  <xsl:template match="PLANT">
    <xsl:param name="lumiere"/>
    <xsl:if test="$lumiere = ./LIGHT">
      <PLANT><xsl:copy-of select="./*[name() != 'LIGHT']"/></PLANT>
    </xsl:if>
  </xsl:template>
</xsl:stylesheet>
