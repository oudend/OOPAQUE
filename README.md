
# how to get formatting hooks

```bash
git config core.hooksPath .githooks
```

```bash
mvn spotless:install-git-pre-push-hook
```

# how to get format on save

IntelliJ IDEA: Install the Google Java Format plugin and configure it to format on save.

VS Code: The Spotless extension supports formatting on save using the Maven project's actual Spotless configuration. Spotless for Gradle and Maven.