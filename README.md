groovy-locator
==============

Creates an executable jar that will run all the groovy scripts found therein.

Build with 'mvn install', which will create an executable jar containing the
groovy scripts along with all the classes required to run them.  After the
jar is created it will automatically be executed as part of the [maven verify phase](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
If you prefer that the jar not be automatically executed, comment out the
following line near the bottom of pom.xml:
`
<phase>verify</phase>
`

