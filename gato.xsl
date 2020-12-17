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
                    <!-- <tc>
                        <td><xsl:value-of select="CAMP"/></td>
                        <td><xsl:value-of select="TYPE"/></td>
                    </tc> -->
                </xsl:for-each>
            </table>
            <!-- <xsl:for-each select= "REGISTER">
                <xsl:value-of select="CAMP"/> -->
        <!-- <xsl:for-each select="CAMP"> -->
            <!-- <xsl:value-of select="CAMP" /><br />
            <xsl:value-of select="Type" /><br /> -->
        <!-- </xsl:for-each> -->
            <!-- </xsl:for-each> -->
        </body>
    </html>
</xsl:template>
</xsl:stylesheet> 

<!-- <?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>My CD Collection</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Campo</th>
        <th>Tipo</th>
      </tr>
      <xsl:for-each select="REGISTER">
        <tr>
          <td><xsl:value-of select="CAMP"/></td>
          <td><xsl:value-of select="TYPE"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet> -->