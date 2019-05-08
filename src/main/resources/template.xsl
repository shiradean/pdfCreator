<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">

    <xsl:template
    			xmlns:ns2="http://creator/domain/"
    			match="contract">
    			
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="all"
					page-height="11in" page-width="8.5in" margin-top="0.25in"
					margin-bottom="0.25in" margin-left="1in" margin-right="1in">
					<fo:region-body margin-top="2in" margin-bottom="1in" />
					<fo:region-before extent="2in" />
					<fo:region-after extent="1in" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="all">
				<!-- header -->
				<fo:static-content flow-name="xsl-region-before">
					<fo:block text-align="end">
						<fo:external-graphic src="url(file:coffee-cup-logoa.jpg)"/>
					</fo:block>
					<fo:block font-size="18pt" font-family="sans-serif"	line-height="1.5em"	text-align="center">
						<xsl:value-of select="number" />
					</fo:block>
				</fo:static-content>
				<fo:static-content flow-name="xsl-region-after">
					<fo:block text-align="end" font-size="10pt" font-family="serif">
						Page <fo:page-number />
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<xsl:call-template name="DisplayOrderInformation" />
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
  </xsl:template>
  
  	<xsl:template name="DisplayOrderInformation">
		<fo:block text-align="right">
			<fo:inline font-weight="bold">Id: </fo:inline>
			<xsl:value-of select="./@id" />
		</fo:block>
		<fo:block text-align="right">
			<fo:inline font-weight="bold">Since: </fo:inline>
			<xsl:value-of select="./since" />
		</fo:block>
		<fo:block text-align="right">
			<fo:inline font-weight="bold">Till: </fo:inline>
			<xsl:value-of select="./till" />
		</fo:block>
	</xsl:template>
  
  	<xsl:template match="number">

						
	</xsl:template>
  
</xsl:stylesheet>