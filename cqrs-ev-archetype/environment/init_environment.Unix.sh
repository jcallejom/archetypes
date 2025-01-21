#!/bin/bash

set -e

BASEDIR=$(dirname "$0")

initEnvironment(){
  printf "\nStarting environment...\n"
  docker compose -f "$BASEDIR"/docker/docker-compose.yml up -d
}

initEnvironment
