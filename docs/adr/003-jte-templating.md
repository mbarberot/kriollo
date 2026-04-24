# JTE Templating Engine

- Status: Accepted
- Date: 2026-04-24

## Context

Kriollo generates code from templates. We need a templating engine that is
type-safe, extensible, and produces clean output.

## Decision

**Use JTE (Joyful Template Engine)** for code generation.

Rationale:
1. **Type safety** - Template parameters are type-checked at compile time
2. **Kotlin-native** - Designed for Kotlin, seamless integration
3. **Static typing** - Prevents runtime template errors
4. **Performance** - Compiled templates, no interpretation overhead
5. **IDE support** - Syntax highlighting, code completion in IntelliJ

## Alternatives

- **Handlebars/Mustache**: Logic-less and simple, but *limited for code generation* — no type checking at compile time, awkward for generating typed code
- **Kotlin Template Strings**: Built-in and convenient, but *no compile-time safety* — errors caught only at runtime, no model validation
- **FreeMarker**: Powerful with rich features, but *requires a license in IntelliJ IDEA* — adds friction for contributors, verbose XML configuration

## Consequences

- Templates go in `src/main/jte/`
- Template compilation integrated in Maven build at `generate-sources` phase
- Generated code depends on JTE model classes
- Template files use `.jte` extension
