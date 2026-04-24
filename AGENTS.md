# AGENTS.md

## Build Commands

```bash
./scripts/build.sh   # Build jar (copies to codegen/)
./scripts/test.sh    # Run tests with coverage
./scripts/test.sh verify  # CI: includes coverage check
```

## Project Workflow

- This is a self-bootstrapping codegen tool - Kriollo uses itself to generate its generators
- Config: `codegen/codegen.kdl` (KDL format)
- Generated artifacts: `pom.xml`, scripts, `.gitignore`, nix config, etc.

## Regenerate Code

After editing `codegen/codegen.kdl`:
```bash
./codegen/kriollo build
```

## Release Process

```bash
./scripts/release.sh <TAG>  # e.g., ./scripts/release.sh 2026.0.0
```
Creates git tag, builds, packages, uploads to GitLab, creates release.

## Key Paths

- Entry point: `com.gitlab.mbarberot.kriollo.KriolloKt`
- Source: `src/main/kotlin/`
- Tests: `src/test/kotlin/`
- Templates: `src/main/jte/` (JTE templates generate code)

## Testing

- Framework: JUnit 5 + AssertJ
- Coverage: JaCoCo (branches: 30%, instructions: 60%)
- Coverage excludes: `**/*Generated*.class`

## CI

GitLab CI runs on tags only (`if: $CI_COMMIT_TAG`). Pipeline: test → build → package → upload → release.