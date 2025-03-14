# Publication Platform Biome Config

This project provides a shared Biome configuration for JavaScript projects.

## Features

- **Biome**: Linter and formatter.

## Installation

1. **Install the Biome configuration package:**

   ```sh
   npm install --save-dev @jppol-pup/biome-config
   ```

2. **You can now run Biome commands and set your IDE up to use it:**

   ```sh
   biome lint
   biome format
   ```

3. **(Optional) Create additional project-specific configuration:**

   If you have project-specific requirements or ignore rules, you can create an additional `biome.json` file in your project root. This file can extend or override the shared configuration.

   **Example `biome.json` file:**

   ```json
   {
     "extends": ["./node_modules/@jppol-pup/biome-config/biome.json"],
     "files": {
       "ignore": ["custom-ignore-pattern"]
     },
     "linter": {
       "rules": {
         "custom-rule": "error"
       }
     }
   }
   ```

## Usage in ci

- **Formatter, linter and import sorting project (read-only):**

  ```sh
  npx biome ci
  ```
