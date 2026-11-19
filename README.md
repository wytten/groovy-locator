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

Scripts live in `src/main/groovy/org/wyttenbach/dale/groovy`. Each one is run
as a JUnit test, so a script that throws an exception is reported as a failure
and fails the build.

Tests
-----

`GroovyScriptRunnerTest` (under `src/test/java`) runs in the maven test phase,
before the jar is built. It checks that:

- a script that throws is reported as a failure, not just logged
- the Groovy scripts are found on the classpath
- every script runs without error

Because of the last check, a broken script fails the build at the test phase.
To skip the tests, build with `mvn install -DskipTests`.

