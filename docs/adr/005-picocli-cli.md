# Picocli for CLI

- Status: Accepted
- Date: 2026-04-24

## Context

Kriollo is a CLI tool invoked from the command line. We need a framework to
parse command-line arguments and generate usage documentation.

## Decision

**Use Picocli** for command-line interface.

Rationale:
1. **Annotation-based** - Declarative, low boilerplate
2. **Subcommands** - Native support for nested CLI hierarchies
3. **Usage auto-generation** - Help text from annotations
4. **Type conversion** - Built-in converters for common types
5. **Single JAR** - Can be shaded, no transitive dependencies
6. **Speed** - Compiles to fast startup

## Alternatives

- **Spring Shell**: Powerful, but heavy dependency, opinionated
- **Quarkus CLI**: GraalVM native compilation, but heavy framework for microservices
- **JCommander**: Simple, but less features than Picocli

## Consequences

- CLI entrypoint annotated with Picocli annotations
- Usage help auto-generated from annotations
- Subcommands map to Kriollo features (init, build, etc.)
