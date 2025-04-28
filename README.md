<div style="text-align: center;"><h1>Desafio Júnior Itaú: API de Transações</h1></div>

<div style="text-align: center;">
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java">
<img src="https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white" alt="maven">
<img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="spring">
<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="postman">
<img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" alt="swagger">
<img src="https://img.shields.io/badge/grafana-%23F46800.svg?style=for-the-badge&logo=grafana&logoColor=white" alt="grafana"/>
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" alt="docker">
<img src="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=Prometheus&logoColor=white" alt="prometheus">
</div>

## Tópicos

- [Descrição do projeto](#descrição-do-projeto)

- [Instruções da API](#instruções-da-api)

- [Endpoints](#Endpoints)

- [Como rodar este projeto](#como-rodar-este-projeto)

## Descrição do projeto

Este é um desafio de programação a nível júnior estruturado pelo itaú que visa testar a capacidade do desenvolvedor em construir um software com várias partes diferentes funcionando em conjunto. 

A tarefa dada é o desenvolvimento de uma **API REST** que recebe **Transações** e retorna **Estatísticas** sob essas transações.

## Instruções da API

### 1. **Receber Transações**
- **Requisitos dos Dados**:
    - A transação deve conter os campos **valor** (decimal com ponto flutuante) e **dataHora** (data/hora no formato ISO 8601).
    - O valor da transação deve ser **positivo ou zero** (não pode ser negativo).
    - A transação deve ter ocorrido no **passado** (não pode ter data/hora no futuro).

- **Respostas Esperadas**:
    - **201 Created**: Quando a transação for aceita (dados válidos).
    - **422 Unprocessable Entity**: Quando algum critério não for atendido (ex: valor negativo ou data futura).
    - **400 Bad Request**: Quando o JSON estiver malformado ou inválido.

### 2. **Limpar Transações**
- **Funcionalidade**: Apaga todas as transações armazenadas.

- **Resposta Esperada**:
    - **200 OK**: Quando as transações forem apagadas com sucesso.

### 3. **Calcular Estatísticas**
- **Critérios**:
    - As estatísticas são baseadas nas **transações dos últimos 60 segundos**.

- **Cálculos**:
    - **count**: Número de transações nos últimos 60 segundos.
    - **sum**: Soma total dos valores transacionados nos últimos 60 segundos.
    - **avg**: Média dos valores transacionados nos últimos 60 segundos.
    - **min**: Menor valor transacionado nos últimos 60 segundos.
    - **max**: Maior valor transacionado nos últimos 60 segundos.

- **Resposta Esperada**:
    - **200 OK**: Retorna um JSON com as estatísticas (count, sum, avg, min, max) ou valores zerados (se não houver transações no intervalo).


## Endpoints

- ### POST /transacao

  - **Este endpoint realiza o cadastro de uma nova transação que deve obrigatóriamente ser realizada em uma data não futura
  e no formato ISO 8601**
  - **Valor ``double``**
  - **dataHora ``OffSetDateTime``** 

 ```json
{
    "valor": 123.45,
    "dataHora": "ano-mes-diaThora:minutos:segundos.789-03:00 <- fuso para horário de brasília"
}
```

- ### DELETE /transacao

  - **Este endpoint realiza a limpeza completa de todas as transações realizadas durante o funcionamento da API**

- ### GET /estatística
  
  - **Este endpoint retorna as estatísticas de todas as transações realizadas em um intervalo de tempo. Não é retornado apenas as transações nos últimos 60 segundos, pois um dos extras era tornar este intervalo de tempo a escolha do usuário.**
  - **Padrão: 60 segundo**
  - **Intervalo Dinâmico: ``localhost:8080/transacao/estatistica?intervaloEmSegundos=(definir tempo)``**

**Retorno deste endpoint: ``DoubleSummaryStatistics``**
```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```

## Como rodar este projeto


### Requisitos:
 - Java 17+
 - Docker desktop
 - IDE de sua preferência
 - Postman (opcional)

#### 1- Clone este repositório ``git clone https://github.com/Rian-Porfirio/desafio-itau-junior.git``

#### 2- Rode o projeto de preferência em modo debug

#### 3- Com o projeto rodando, acesse a seguinte URL: ``http://localhost:8080/swagger-ui/index.html#/``

#### 4- Utilize o json já mostrado em [Endpoints](#endpoints) neste README para registrar as transações


## Utilizando observabilidade

#### 1- Abra o docker desktop

#### 2- No terminal do projeto, rode o seguinte comando``docker-compose up`` isso irá baixar as imagens presentes no arquivo **docker-compose.yml**

#### 3- Com as imagens baixadas, você pode acessar ambas as ferramentas prometheus e grafana
##### Grafana: ``localhost:3000``
##### Prometheus: ``localhost:9090``
#### Irei trazer instruções de como realizar testes na API com estas ferramentas. Veja em [Rian Porfírio](https://www.linkedin.com/in/rian-porfírio)

