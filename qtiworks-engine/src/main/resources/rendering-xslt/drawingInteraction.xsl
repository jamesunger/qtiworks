<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:qti="http://www.imsglobal.org/xsd/imsqti_v2p1"
  xmlns="http://www.w3.org/1999/xhtml"
  exclude-result-prefixes="qti">
  
  <xsl:template match="/">
  	<xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="qti:drawingInteraction">
  	<html>
  		<head>
  			<script src="//jsxgraph.uni-bayreuth.de/distrib/jsxgraphcore.js"/>
  		</head>
  	</html>
    <p/>
	  <input type="checkbox" id="drawline"/>Draw a Line<br/>
	  <span id="linedirections">Click two points between which to draw a line</span>
	  <div id="jxgbox" class="jxgbox">
		<xsl:attribute name="height">
			<xsl:value-of select="@height" />
		</xsl:attribute>
		<xsl:attribute name="width">
			<xsl:value-of select="@width" />
		</xsl:attribute>
	    <object>
			<xsl:attribute name="type">
				<xsl:value-of select="'grid'" />
			</xsl:attribute>
			<xsl:attribute name="bounds">
				<xsl:value-of select="@bounds" />
			</xsl:attribute>
			<xsl:attribute name="axis">
				<xsl:value-of select="@axis" />
			</xsl:attribute>
			<xsl:attribute name="grid">
				<xsl:value-of select="@grid" />
			</xsl:attribute>
			<xsl:attribute name="snapTo">
				<xsl:value-of select="@snapTo" />
			</xsl:attribute>
		</object>
		<xsl:apply-templates select="qti:object"/>
		<script type="text/javascript">
          $(document).ready(function() {
            QtiWorksRendering.registerDrawingInteraction();
          });
        </script>
	  </div>
  </xsl:template>
  
  <xsl:template match="qti:object">
	  <object>
		  <xsl:attribute name="type">
			<xsl:value-of select="'gridImg'" />
		  </xsl:attribute>
		  <xsl:attribute name="data">
			<xsl:value-of select="@data" />
		  </xsl:attribute>
		  <xsl:attribute name="height">
			<xsl:value-of select="@height" />
		  </xsl:attribute>
		  <xsl:attribute name="width">
			<xsl:value-of select="@width" />
		  </xsl:attribute>
	  </object>
</xsl:template>

</xsl:stylesheet>
