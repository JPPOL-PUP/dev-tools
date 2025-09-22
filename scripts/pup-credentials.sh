#!/bin/bash
# Take the first argument as environment
environment="$1"
shift

# Check if the first argument is either "dev" or "prod"
if [[ "$environment" != "dev" && "$environment" != "prod" ]]; then
  echo "Error: First parameter must be 'dev' or 'prod'."
  exit 1
fi

aws_profile="pup-$environment"

# Fetch MongoDB credentials from AWS SSM Parameter Store
SSM_RESPONSE="$(aws ssm get-parameter --name mongodb-secret --with-decryption --profile "$aws_profile")"
SSM_VALUE=$(echo "${SSM_RESPONSE}" | jq -r ".Parameter.Value")
MONGO_PUBLIC_KEY=$(echo "${SSM_VALUE}" | jq -r ".public_key")
MONGO_PRIVATE_KEY=$(echo "${SSM_VALUE}" | jq -r ".private_key")

# Fetch AWS credentials for the specified profile
AWS_CREDENTIALS=$(aws configure export-credentials --profile "$aws_profile")
AWS_ACCESS_KEY_ID=$(echo "${AWS_CREDENTIALS}" | jq -r ".AccessKeyId")
AWS_SECRET_ACCESS_KEY=$(echo "${AWS_CREDENTIALS}" | jq -r ".SecretAccessKey")
AWS_SESSION_TOKEN=$(echo "${AWS_CREDENTIALS}" | jq -r ".SessionToken")

# Export the credentials as environment variables and run the command
AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} AWS_SESSION_TOKEN=${AWS_SESSION_TOKEN} MONGODB_ATLAS_PUBLIC_API_KEY=${MONGO_PUBLIC_KEY} MONGODB_ATLAS_PRIVATE_API_KEY=${MONGO_PRIVATE_KEY} "$@"