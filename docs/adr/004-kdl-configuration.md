# KDL Configuration Format

- Status: Accepted
- Date: 2026-04-24

## Context

Kriollo needs a configuration format for users to define their project setup.
The format should be readable, extensible, and parseable.

## Decision

**Use KDL** for configuration.

Rationale:
1. **Human-readable** - Clean syntax, minimal punctuation
2. **Extensible** - Nodes can be nested, comments supported
3. **Type-safe** - Explicit types: strings, numbers, booleans
4. **Tooling** - Parser available in Java/Kotlin
5. **Familiar** - Similar to TOML but with nested blocks
6. **Growing adoption** - Used in fresh projects like Zellij

## Alternatives

- **YAML**: *indentation-sensitive — one wrong space breaks the file, types are ambiguous ('yes' vs true)*
- **JSON**: *verbose — brackets and braces everywhere, no comments, painful to read*
- **TOML**: *clean but limited — no nested blocks, forces flat structure*
- **Properties**: *flat only — can't express nested hierarchy*
- **XML**: *has solid Java tooling, but verbose — opening and closing tags everywhere feels antiquated*

## Consequences

- Config file: `codegen/codegen.kdl` (or user-defined)
- Parser: kdl4j library
- Generated artifacts depend on config structure
