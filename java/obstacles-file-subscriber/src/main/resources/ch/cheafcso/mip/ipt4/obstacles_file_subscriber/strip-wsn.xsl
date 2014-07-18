<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:ns2="http://docs.oasis-open.org/wsn/b-2">
    <xsl:output method="xml" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
    <xsl:template match="ns2:Message">
            <xsl:copy-of select="node()"/>
    </xsl:template>
    <xsl:template match="text()"/>
</xsl:stylesheet>
