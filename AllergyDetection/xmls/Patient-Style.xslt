<!-- XSLT -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <html>
      <head>
        <title>Patient</title>
      </head>
      <body>
        <table border="2">
          <tr>
            <th>name</th>
            <th>surname</th>
            <th>dateOfBirth</th>
            <th>gender</th>
          </tr>
          <xsl:for-each select="Patient">
            <tr>
              <td><xsl:value-of select="name"/></td>
              <td><xsl:value-of select="surname"/></td>
              <td><xsl:value-of select="dateOfBirth"/></td>
              <td><xsl:value-of select="gender"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
