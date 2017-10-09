<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
		<xsl:template match="/">    
		 <xsl:apply-templates select="//journees">
		 	<xsl:with-param name="n" select="18"/>
		</xsl:apply-templates>
		</xsl:template>
		<xsl:template match="journee">
			<xsl:param name="n"/>
			<xsl:if test="./@num = $n">
				<xsl:copy-of select="."/>
			</xsl:if>
	</xsl:template>
</xsl:stylesheet>
