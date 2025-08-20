# Copilot Code Review Instructions

## Comment Classification System

Use these comment types to indicate the severity and required action for review feedback:

### `[blocking]` - Must Fix Before Merge
Use for issues that **will cause failures** or **significant problems**:
- Security vulnerabilities or data exposure risks
- Logic errors that will cause runtime exceptions or incorrect behavior
- Breaking API changes without proper versioning/migration
- Performance issues that will impact production (memory leaks, infinite loops)
- Violations of critical business rules or compliance requirements
- Missing error handling for failure scenarios
- Resource management issues (unclosed connections, file handles)

**Scope:** Focus on correctness, security, and production stability.

### `[non-blocking]` - Suggestions for Improvement
Use for improvements that **enhance but don't break**:
- Code clarity and readability improvements
- Better variable/method naming suggestions
- Refactoring opportunities to reduce complexity
- Performance optimizations (non-critical)
- Design pattern suggestions
- Documentation improvements
- Test coverage enhancements
- Code organization and structure improvements

**Scope:** Focus on maintainability, readability, and best practices.

### Conventional Comments (no prefix)
Use for **standard feedback** without explicit blocking designation:
- Questions for clarification
- General observations about the approach
- Minor style suggestions (when not handled by automated formatting)
- Educational comments explaining concepts
- Acknowledgment of good practices

**Scope:** Focus on knowledge sharing and understanding.

## Review Depth Guidelines

### Deep Review Scenarios
Provide detailed analysis when:
- Changes affect critical business logic or security boundaries
- New architectural patterns are introduced
- Complex algorithms or data transformations are implemented
- Changes impact multiple systems or have broad downstream effects

### Surface-Level Review Scenarios  
Keep feedback high-level when:
- Changes are simple CRUD operations following existing patterns
- Automated tools already handle formatting/linting
- Changes are isolated bug fixes in well-understood areas
- Updates to configuration or documentation

## Comment Examples

**[blocking]** Database connection not closed in error path - will cause connection pool exhaustion
```scala
try {
  connection.execute(query)
} catch {
  case ex => log.error(ex) // connection never closed!
}
```

**[non-blocking]** Consider extracting this validation logic into a separate method for reusability
```scala  
if (user.email.contains("@") && user.email.length > 5 && ...) // complex validation inline
```

**Conventional:** What's the expected behavior when the external API is unavailable? Should we cache the last successful response?

## Decision Framework

Before commenting, ask:
1. **Will this cause a failure?** → `[blocking]`
2. **Will this make future maintenance harder?** → `[non-blocking]`
3. **Am I just sharing knowledge or asking questions?** → Conventional comment
4. **Is this already handled by automated tools?** → Skip comment

## Trust and Efficiency

- Focus review time on logic, security, and architecture rather than style
- Reference existing patterns in the codebase when suggesting changes
- Assume automated workflows handle formatting, linting, and basic quality checks
- Prioritize comments that prevent production issues or improve team velocity