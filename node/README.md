# Publication Platform Biome Config

This project provides a shared Biome configuration for JavaScript projects.

## Features

- **Biome**: Linter and formatter.

## Installation

1. **Install the Biome configuration package:**

   ```sh
   npm install --save-dev @jppol-pup/biome-config
   ```

2. **Run Biome commands:**

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

## Usage

- **Lint your project:**

  ```sh
  npm run lint
  ```

- **Format your project:**

  ```sh
  npm run format
  ```

## Configuration

The `biome.json` file contains the shared Biome configuration. You can customize it as needed for your project.