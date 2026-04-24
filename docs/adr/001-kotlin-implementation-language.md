# Kotlin as Implementation Language

- Status: Accepted
- Date: 2026-04-24

## Context

At project inception, we had to choose the implementation language for Kriollo.
Kriollo is a codegen tool that produces project configurations, scripts, and
boilerplate code for various target languages (Java, Kotlin, etc.).

## Decision

**Use Kotlin** as the implementation language for Kriollo.

Rationale:
1. **Conciseness** - Data classes, extension functions, DSL support reduce boilerplate
2. **Null safety** - Build-time null checking reduces runtime errors
3. **Interoperability** - 100% compatible with Java libraries, no friction
4. **Modernity** - Actively developed, JetBrains-backed, strong community
5. **Tooling** - Excellent IDE support, fast incremental compilation
6. **Coroutines** - Future potential for non-blocking generation

## Alternatives

- **Java**: Mature ecosystem, but verbose for DSLs and data handling
- **Scala**: Powerful type system, but steeper learning curve
- **Groovy**: Dynamic and flexible, but less static guarantees

## Consequences

- Contributors must know Kotlin (or learn it)
- JVM-based, requires JDK (LTS versions)
