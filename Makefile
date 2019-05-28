KUBERNETES_SERVICE_NAME=ms-salesforce
# starts netty on localhost:8080

DEPLOYMENT := deployment
SSH_KEY := ~/.ssh/id_rsa
SSH_PUB := ~/.ssh/id_rsa.pub

.PHONY: run
run: build
	./gradlew run

# runs unit tests
.PHONY: test
test: build
	MICRONAUT_PROFILES_ACTIVE=test ./gradlew clean test

.PHONY: clean
clean:
	./gradlew clean 
	rm -rf $(DEPLOYMENT)

# bundles app for production
.PHONY: build
build:  
	./gradlew build

.PHONY: install
install:
	./gradlew build

$(SSH_PUB):
	ssh-keygen -y -f $(SSH_KEY) > $(SSH_PUB)
	ssh-add -d $(SSH_PUB)
	@echo "$(SSH_GITHUB_KEY)" | base64 -d > $(SSH_KEY)
	chmod 600 $(SSH_KEY)
	ssh-add $(SSH_KEY)

$(DEPLOYMENT): | update-ssh-identity
	git clone git@github.com:yoigo-thor/continuous-deployment-scripts.git
	mv continuous-deployment-scripts/deployment .


.PHONY: continuous-deployment-scripts
continuous-deployment-scripts: | $(DEPLOYMENT)

.PHONY: environment
environment: | $(DEPLOYMENT) 

.PHONY: update-ssh-identity
update-ssh-identity: | $(SSH_PUB)
# builds the production docker image
.PHONY: build-docker-image
build-docker-image:
	mkdir target
	cp build/libs/*all.jar target/
	docker build -t ${KUBERNETES_SERVICE_NAME} ${ARGS} .

# starts the development docker image on localhost:4200
.PHONY: run-docker-image
run-docker-image:
	docker run -p 8080:8080 ${KUBERNETES_SERVICE_NAME}

# starts the production docker image on localhost:8080
.PHONY: run-prod-image
run-prod-image:
	docker run -p 8080:8080 ${KUBERNETES_SERVICE_NAME}
