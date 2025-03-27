# Complete example of a BDD process



## catalog-spec

The [catalog-spec](./catalog-spec/) folder contains the specification project for the Catalog Service.

## catalog-service

In [catalog-service](./catalog-service/) you will find the service implementation.

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

