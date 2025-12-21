APP_NAME := flowcart
CATALOG_SERVICE := catalog-service
VERSION := latest

DB_COMPOSE := deploy/docker-compose-database.yml
APP_COMPOSE := deploy/docker-compose.yml
MAVEN := ./mvnw

.PHONY: all build test docker-build infra-up infra-down app-up app-down down logs help

all: clean build docker-build infra-up app-up

help:
	@echo "Available commands: build, test, docker-build, infra-up, infra-down, app-up, app-down, down, logs, clean"

build:
	$(MAVEN) clean package -DskipTests

test:
	$(MAVEN) test

docker-build:
	docker build --no-cache -t $(APP_NAME)/$(CATALOG_SERVICE):$(VERSION) -f services/$(CATALOG_SERVICE)/Dockerfile services/$(CATALOG_SERVICE)

infra-up:
	docker compose -f $(DB_COMPOSE) up -d

infra-down:
	docker compose -f $(DB_COMPOSE) down

app-up:
	docker compose -f $(APP_COMPOSE) up -d

app-down:
	docker compose -f $(APP_COMPOSE) down

down:
	docker compose -f $(DB_COMPOSE) -f $(APP_COMPOSE) down

logs:
	docker compose -f $(DB_COMPOSE) -f $(APP_COMPOSE) logs -f

clean:
	$(MAVEN) clean