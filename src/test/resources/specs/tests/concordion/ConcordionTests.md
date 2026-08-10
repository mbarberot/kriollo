# Living specifications

Kriollo can setup living specification tests using Concordion

## Activate Concordion support

This configuration will enable living specifications into your project

<pre>
tests { 
    livingSpecifications {
        enabled "true"
    }
}
</pre>

## Concordion support

[When activated](- "#result = activateConcordion()")

* The Concordion dependency will be added :

<pre>
<code data-type="xml" concordion:assert-equals="#result.dependency">
<dependency>
    <groupId>org.concordion</groupId>
    <artifactId>concordion</artifactId>
    <version>4.0.1</version>
    <scope>test</scope>
</dependency>
</code>
</pre>

* The Surefire Plugin will be configured to generate the documentation into  
  the `docs` directory

<pre>
<code data-type="xml" concordion:assert-equals="#result.plugin">
<plugin>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
        <systemPropertyVariables>
            <concordion.output.dir>docs</concordion.output.dir>
        </systemPropertyVariables>
        <includes>
            <include>**/*Fixture</include>
            <include>**/*Test</include>
        </includes>
    </configuration>
</plugin>
</code>
</pre>
