#!/bin/bash

echo "Starting build process..."
./gradlew clean build
echo "Build process completed."

docker-compose up --build
echo "Docker containers are up and running."


