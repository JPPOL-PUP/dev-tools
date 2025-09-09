# Dev tools

This repository contains various development tools and scripts to assist with software development tasks. 
Below is a brief overview of the contents and how to use them.

## Biome config

`biome-config`: Configuration package for Biome, a tool for managing TypeScript projects. 
  It includes settings for linting and formatting.

- Link to package: [biome-config](https://github.com/JPPOL-PUP/dev-tools/pkgs/npm/biome-config)
- Installation: `npm i @jppol-pup/biome-config@latest --save-optional`

It is important that this package is installed as an optional dependency.
The reason for this so we don't face authentication issues when building with Docker.
Authentication will be needed for local development.
