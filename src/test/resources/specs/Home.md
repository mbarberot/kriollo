# Documentation

Kriollo is a code generation tool that try to reduce the amount of 
boilerplate code that we, devs, have to write. 

Kriollo will handle both : 

* the project configuration (java/kotlin, maven, git, ...)
* the project boilerplate code

To do so, Kriollo will install itself into the `codegen` directory. 

In that directory, it will expect to have : 

* the `kriollo` bash script
* the `kriollo.jar` artifact
* a `codegen.kdl` configuration file (see [KDL]() for the syntax)
* a `generated-files.txt` file that lists the files managed by Kriollo

## Managed files

* [Generated files index](GeneratedFilesIndex.md "c:run")

## Features

* [Tests](tests/Tests.md "c:run")

## Contributing

* [XML Concordion extension](dev/concordion/ConcordionXMLExtension.md "c:run")