#!/bin/bash

set -e

BASEDIR=$(dirname "$0")
LDAP_HOST=${1:-localhost:389}

initEnvironment(){
  printf "\nStarting environment...\n"
  docker compose -f "$BASEDIR"/docker/docker-compose.yml up -d
}

registerLdap(){
  printf "\nRegistering Ldap conection...\n"
  sleep 10
  #ldapadd -x -D "cn=admin,dc=maincompany,dc=com" -w admin -H ldap://$LDAP_HOST -f security/ldap/ldap-company-com.ldif
  sh "$BASEDIR"/security/import-openldap-users.sh
}

registerKeycloack(){
  printf "\nRegistering Keycloack conection...\n"
  sleep 40
  #sh "$BASEDIR"/security/init-keycloak-bitamini.Unix.sh
  #sh "$BASEDIR"/security/init-keycloak-redhat.Unix.sh
  sh "$BASEDIR"/init-keycloak-bitamini-ldap.sh
}


initEnvironment
registerLdap
registerKeycloack