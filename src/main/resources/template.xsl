<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">

    <xsl:template
    			xmlns:ns2="http://creator/domain/"
    			match="contract">
        <!-- fo:root is the top element of any printed item (document or book, etc.)-->
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="portraitMaster" 
                    margin-top="100mm" 
                    margin-bottom="10mm"
                    margin-left="10mm" 
                    margin-right="10mm">
                    <fo:region-body margin-top="10mm" margin-bottom="10mm"/>
                    <fo:region-before extent="105mm"/>
                    <fo:region-after extent="5mm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="portraitMaster">
                <!-- Header -->
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block text-align="center">Double It Response</fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
          
                   <fo:block text-align="center" > 
                        	Contract type is   <xsl:value-of select="." />
                   </fo:block>
                   	<fo:block text-align="center">
                   		Contract <xsl:value-of select="ns2:name" /> 
                   </fo:block>
                   <fo:block text-align="center" background-color="red" >
                   		<xsl:value-of select="."/> doubled is <xsl:value-of select=". * 2"/>
                   </fo:block>
              </fo:flow>                          
           </fo:page-sequence>
        </fo:root>
  </xsl:template>
</xsl:stylesheet>