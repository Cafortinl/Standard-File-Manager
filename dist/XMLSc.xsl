<?xml version="1.0" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="REGISTER">
    <html>
        <body>
            <h2>Campos del Registro</h2>
            <table border="1">
                <tr bgcolor="#9acd32">
                    <th>Campo</th>
                    <th>Tipo</th>
                    <th>Valor</th>
                </tr>

                <xsl:for-each select= "CAMP">
                    <tr>
                        <td><xsl:value-of select="."/></td>
                        <td><xsl:value-of select="following-sibling::TYPE[1]"/></td>
                        <td><xsl:value-of select="following-sibling::VALUE[1]"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
    </html>
</xsl:template>
</xsl:stylesheet>