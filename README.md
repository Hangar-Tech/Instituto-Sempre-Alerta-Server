# Gestão Instituto Sempre Alerta API

API para o sistema de gerenciamento para os escoteiros do instituto sempre alerta

## UML

[![](https://mermaid.ink/img/pako:eNqdVF1v2jAU_SuWnzYtRfmABvJGiyZNYlMF42XKy218G6wZG9mmXYb473Mcoqar0brmAZtzru_H8b0-0koxpAWtBBiz4FBr2JWSuM8jZGNQk2OHtN8nslSyJpwNobXV3IEaa26sBsuVDNASdhiAcQdcBPC9C_-kdChQjVKHXN1zbbcMmiG1UgIN0e3vEL5V0kJlSdWtQ2rOmEZXOnTrkFqARVJpdAub21fMYc96Zsg9Ks6IgUf88HEIe2kfuGQ3zRf2kvInOm8Xz6wGar_DyIdgKPA5xKlPu7v6XqLj_1zaVkkMeutVDXlzGSLaAFFx24SiKGNB3LrODYa6c_Rbe9ZyK0KtZMShDsCtJihfpLpZLckNSIn6fZ3SM6EkXvVM4Nb-0TFejK4Z1q6mi-TfLbh0fdPxwu3mQtxBjXAvvPug7J-5wOD1fgsP_kI9SaGAbTQPsN-bPQZkaw-02zdN2EW1fHE-37Z4V9xgBNrFT1BJk5KSq6vzph-HgmzBXDbr-_xs1hqWkkZ0h9rNDHNPrZeopHaLThZauC0D_bOkpTw5OzhYtW5kRQurDxjR7k7PLzMtHkAYhyLjVumv57e7XSK6B_lDqWcb958WR_qLFkmWj6az2TSJ81kSJ5PrNKKNg2ejJEmmk_R6PJnm4zjNThH97V0ko2ySxnmSj7Msbtn09Af588MN?type=png)](https://mermaid.live/edit#pako:eNqdVF1v2jAU_SuWnzYtRfmABvJGiyZNYlMF42XKy218G6wZG9mmXYb473Mcoqar0brmAZtzru_H8b0-0koxpAWtBBiz4FBr2JWSuM8jZGNQk2OHtN8nslSyJpwNobXV3IEaa26sBsuVDNASdhiAcQdcBPC9C_-kdChQjVKHXN1zbbcMmiG1UgIN0e3vEL5V0kJlSdWtQ2rOmEZXOnTrkFqARVJpdAub21fMYc96Zsg9Ks6IgUf88HEIe2kfuGQ3zRf2kvInOm8Xz6wGar_DyIdgKPA5xKlPu7v6XqLj_1zaVkkMeutVDXlzGSLaAFFx24SiKGNB3LrODYa6c_Rbe9ZyK0KtZMShDsCtJihfpLpZLckNSIn6fZ3SM6EkXvVM4Nb-0TFejK4Z1q6mi-TfLbh0fdPxwu3mQtxBjXAvvPug7J-5wOD1fgsP_kI9SaGAbTQPsN-bPQZkaw-02zdN2EW1fHE-37Z4V9xgBNrFT1BJk5KSq6vzph-HgmzBXDbr-_xs1hqWkkZ0h9rNDHNPrZeopHaLThZauC0D_bOkpTw5OzhYtW5kRQurDxjR7k7PLzMtHkAYhyLjVumv57e7XSK6B_lDqWcb958WR_qLFkmWj6az2TSJ81kSJ5PrNKKNg2ejJEmmk_R6PJnm4zjNThH97V0ko2ySxnmSj7Msbtn09Af588MN)

### Tecnologias Utilizadas

* [Java](https://www.java.com/en/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Docker](https://www.docker.com/)
* [Postgres](https://www.postgresql.org/)


## Dependências e Versões Necessárias

* Java(JDK) - Versão: 17+
* Postgres - Versão: 15+

### Como rodar o projeto

**Com Docker compose:**

```
docker compose up -d
```

**ou com Docker:**

```
docker run --name my-postgres --env POSTGRES_PASSWORD=123 --volume postgres-volume:/var/lib/postgresql/data --publish 5432:5432 --detach postgres
```
e depois 

```
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

**ou com sua base de dados:**

Mude as propieadas para conectar com sua base de dados Postgresql em ```src/main/resources/application-dev.yml```

e depois execetute

```
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

Depois disso poderá abrir em [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) e verá a documentação em **Swagger** com todas as rotas


## Como rodar os testes

```
./mvnw test
```

## Possiveis problemas enfrentados


### Problema 1:
Problemas ao executar com permissões do sistema operacional
* e você estiver usando Windows, execute como adminstrador.

### Problema 2:
Conexão com o banco de dados
* Verifique os passos passados e tente executar com docker


