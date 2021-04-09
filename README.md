# eng-zap-challenge-java

Repositório do código fonte do desafio OLX / Zap. Foi desenvolvida a solução solicitada na **Opção B: Fazer uma API (backend)**. Todos os detalhes dos
requisitos estão [aqui](https://olxbr.github.io/cultura/challenges/engineering.html).

## Executando Testes e Gerando Jacoco Report

Primeiramente baixe o código-fonte deste repositório utilizando o comando abaixo:

``` 
git clone https://github.com/luiz-alves/eng-zap-challenge-java.git
```

Depois execute o comando abaixo na raiz do projeto:

``` 
mvn clean install 
```

Após a execução da Build e Testes é possível acessar o Jacoco Report que exibe o Test Coverage da aplicação. A partir da raiz do projeto, siga para o
diretório abaixo:

``` 
/challenge-jacoco/target/site/jacoco-aggregate/index.html
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

Você pode executar as requisições diretamente pelo Swagger citado acima, mas se preferir, pode utilizar a linha de comando. Primeiro é necessário
carregar os dados dos imóveis. Execute o comando abaixo:

``` 
curl -X POST "http://localhost:8181/property-loaders" -H "accept: */*" -d ""
```

Se tudo ocorrer bem, você receberá um HTTP 200, isso quer dizer que os dados foram carregados com sucesso. Após isso, é possível realizar consultas
dos imóveis por portal. Execute o comando abaixo (você pode alterar os parâmetros se preferir):

``` 
curl -X GET "http://localhost:8181/properties" -H "accept: */*" -H "page: 0" -H "portal: zap" -H "size: 30"
```

## Autor

* **Luiz Gustavo Alves** - [luiz-alves](https://github.com/luiz-alves)