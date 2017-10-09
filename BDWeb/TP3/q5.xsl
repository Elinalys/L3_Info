<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
    	<clubs>
	  	 	<xsl:for-each select="//club">
	  	 		<club>
					<xsl:copy-of select="./nom"/>
					<xsl:copy-of select="./ville"/>
					<rencontres>
						<domicile>
							<xsl:apply-templates select="//rencontre">
								<xsl:with-param name="id" select="@id" />
								<xsl:with-param name="id" select="./nom" />
	      			</xsl:apply-templates>
	      		</domicile>
      		</rencontres>
      	</club>
	    	</xsl:for-each>
    </clubs>
  	</xsl:template>

	<xsl:template match="//rencontre">
    <xsl:param name="id" />
    <xsl:param name="name" />

  	<xsl:if test="./receveur = $id">
  		<club><xsl:copy-of select="$name/text()"/></club>
			<xsl:copy-of select="./score"/>
  	</xsl:if>
  </xsl:template>

</xsl:stylesheet>
