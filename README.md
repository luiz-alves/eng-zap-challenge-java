# eng-zap-challenge-java

Repositório do código fonte do desafio OLX / Zap. Foi desenvolvida a solução solicitada na **Opção B: Fazer uma API (backend)**. Todos os detalhes dos
requisitos estão [aqui](https://olxbr.github.io/cultura/challenges/engineering.html).

## Executando Testes

Execute o comando abaixo na raiz do projeto:

``` 
mvn clean test 
```

## Executando a aplicação localmente

Execute o comando abaixo na raiz do projeto:

``` 
./mvnw spring-boot:run -pl challenge-api
```

## Acessando a API

Após executar a aplicação localmente com o comando acima. Foi disponibilizada a documentação através do Swagger. Ele pode ser acessado pela URL abaixo

``` 
http://localhost:8181/swagger-ui/index.html
```

## Como utilizar a API

Execute uma requisição para carregar os dados dos imóveis:

``` 
curl -X POST "http://localhost:8181/" -H "accept: */*" -d ""
```

Após carregar os dados, é possível realizar consultas dos imóveis por portal.

``` 
curl -X GET "http://localhost:8181/" -H "accept: */*" -H "page: 0" -H "portal: zap" -H "size: 30"
```

## Autor

* **Luiz Gustavo Alves** - [luiz-alves](https://github.com/luiz-alves)