# Complete example of a BDD process

This project contains the code from my article ["Behaviour-Driven Development in practice"](https://www.nachobrito.es/software-architecture/bdd-in-practice/)

## catalog-spec

The [catalog-spec](./catalog-spec/) folder contains the specification project for the Catalog Service.

The project is cloned from the [cucumber-java-skeleton](https://github.com/cucumber/cucumber-java-skeleton) and uses Maven for automation. The command

```shell
mvn test
```

will run the Behavior Tests against a service deployed on `http://localhost:8080`. You can change the endpoint by editing the [OpenAPI file](./catalog-spec/src/main/resources/spec/api.yaml).


## catalog-service

In [catalog-service](./catalog-service/) you will find the service implementation. It is a [Micronaut](https://micronaut.io/) application that implements the interface defined in OpenAPI.

### Generate code from OpenAPI:

Thanks to [the embedded OpenAPI code generator](https://guides.micronaut.io/latest/micronaut-openapi-generator-server-maven-java.html) included with the Micronaut Maven plugin, the command:

```shell
mvn compile
```

Will generate the model classes, as well as the interface definition, for the REST layer.

### Run service tests

The command:

```shell
mvn test
```

Will execute both Unit and Integration tests.

In order to run the Behavior Tests defined in the specification project, you need to run first this service with the command:

```shell
mvn mn:run
```


