# Scripts
This directory contains various scripts that can be used for different purposes. Below is a brief description of each script:

## pup-credentials.sh
A script to get inline credentials for AWS and MongoDB when running CLI commands.

### Prerequisites
- AWS CLI must be installed and configured and the profile names must be ```pup-dev``` and ```pup-prod```.
- jq must be installed for JSON parsing.

Example usage:
  ```bash
  ./pup-credentials.sh dev your-cli-command
  ```