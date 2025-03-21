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

## Usage in deployment pipeline

- Reusable workflow using biome's CI command running lint and format (Read-only): https://github.com/Jyllands-Posten/github-actions/blob/main/.github/workflows/node-run-biome.yml

- **Example usage in pipeline:**

  ```yml
  lint_format:
    needs: build
    uses: Jyllands-Posten/github-actions/.github/workflows/node-run-biome.yml@v1.3.6
    with:
      node-version: "20.x"
  ```
