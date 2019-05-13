<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
								xmlns:fo="http://www.w3.org/1999/XSL/Format"
								xmlns:fox="http://xmlgraphics.apache.org/fop/extensions">
	<xsl:param name="compName"/>
    <xsl:param name="compAddress"/>
    <xsl:param name="compRepresentative"/>
	<xsl:decimal-format name="spaces" grouping-separator=" " />
	
    <xsl:template match="contract">   
    	    				
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="all"
					page-height="11in" page-width="8.5in" 
					margin-top="0.25in" margin-bottom="0.25in" margin-left="1in" margin-right="1in">
					<fo:region-body margin-top="2in" margin-bottom="1in" />
					<fo:region-before extent="2in" />
					<fo:region-after extent="1in" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			
			<fo:page-sequence master-reference="all">
				<!-- header -->
				<fo:static-content flow-name="xsl-region-before">					 
					<xsl:call-template name="companyInfo" />
					<fo:block font-size="18pt" font-family="sans-serif" line-height="1.5em" text-align="center">
						<xsl:value-of select="number" />
					</fo:block>
				</fo:static-content>
				<fo:static-content flow-name="xsl-region-after">
					<fo:block text-align="end" font-size="10pt" font-family="serif">
						Signed <xsl:value-of select="signedDate"/>.
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<xsl:apply-templates select="holder" />
					<xsl:call-template name="contractInfo" />
					<xsl:call-template name="signature" />
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	<xsl:template name="companyInfo">
		<fo:table width="100%">
			<fo:table-column column-width="40%" />
			<fo:table-column column-width="60%" />
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block text-align="left">
							<fo:external-graphic content-height="60px" src="file:src/main/resources/images/logo.jpg"/>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell>
						<fo:block>
							<fo:block text-align="right">
								<xsl:value-of select="$compName" />
							</fo:block>
							<fo:block text-align="right">
								<xsl:value-of select="$compAddress" />
							</fo:block>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>	
	</xsl:template>	
	
	<xsl:template match="holder">
		<fo:block font-weight="bold" background-color="black" color="white" 
					padding="1pt" text-align="center">
			HOLDER
		</fo:block>
		<fo:block color="black" border="solid black 1px" space-after="2em">
			<fo:block>
				Name : 
				<xsl:value-of select="surname" />
				<xsl:text>  </xsl:text>
				<xsl:value-of select="name" />			
			</fo:block>
			<fo:block>
				Email : <xsl:value-of select="email" />			
			</fo:block>
		</fo:block>
	</xsl:template>

  	<xsl:template name="contractInfo">
  		<fo:block text-align="right">
			<fo:block>
				<fo:inline font-weight="bold">Since : </fo:inline>
				<xsl:value-of select="since" />
			</fo:block>
			<fo:block>
				<fo:inline font-weight="bold">Till : </fo:inline>
				<xsl:value-of select="till" />
			</fo:block>
			<fo:block>		
				<fo:inline font-weight="bold">Total : </fo:inline>	
				<xsl:value-of select="format-number(sum, '$# ###.##', 'spaces')"/>		
			</fo:block>		
  		</fo:block>
	</xsl:template>
	
	<xsl:template name="signature">
		<fo:block>
			<xsl:value-of select="$compRepresentative" />			
		</fo:block>
		<fo:block text-align="center">
			<fo:block-container background-image = "file:src/main/resources/images/stamp.jpg" 
								fox:background-image-height = "120px" 
								fox:background-image-width = "120px" 
								background-repeat = "no-repeat" 								
								width="120px" height="120px">
				<fo:block text-align="center" >
					<fo:external-graphic content-height="80px" content-width="80px" padding-top="40px"
											src="file:src/main/resources/images/signature.jpg"/>
				</fo:block>			
			</fo:block-container>
		</fo:block>
	</xsl:template>
  
</xsl:stylesheet>