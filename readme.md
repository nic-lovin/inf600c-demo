# Démo pour INF600C

## Application

Créer/modifier/supprimer des usagers
Ajouter des images

## Installation

`./gradlew bootRun --args="accessKey secretKey"`

## Commands

find access and secret keys `http://localhost:8080/actuator/heapdump`
`strings heapdump | grep -i commandLineArgs -A3 -B3`

set up aws using leaked keys
`aws configure`

list permissions
`aws s3 --profile default ls `

list files
`aws s3 ls s3://inf600c-demo`

create bucket
`aws s3 mb s3://inf600c-demo2`

# Vulnerabilities

- LFI / file write / file delete
- Race condition
- Hardcoded password
- Database running with too much privileges
- Potential xss
- Use of non prepared statement
- ...
