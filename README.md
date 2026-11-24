# API de Horarios de Aula

Projeto de Website - Back End (Demonstracao de Competencia)
Aluno: Diogo Pereira Stein

API REST sem interface grafica para gerenciar horarios de aula de professores. Feita com Java 17, Spring Boot 3, Spring Data JPA (ORM), banco H2, documentacao Swagger e autenticacao basica.

## Como executar

Requisitos: Java 17 ou superior e Maven.

```
mvn spring-boot:run
```

A aplicacao sobe em http://localhost:8080. Na primeira execucao ela ja cria alguns dados de exemplo (2 professores, 3 disciplinas, 2 turmas e 4 horarios).

## Swagger

Abra http://localhost:8080/swagger-ui.html

Para testar, clique em **Authorize** e informe:

- Usuario: `admin`
- Senha: `admin123`

## Autenticacao

Todas as rotas da API exigem autenticacao basica (usuario e senha). Sem login a resposta e 401. A pagina do Swagger fica aberta para poder ser acessada.

## Endpoints

| Recurso | Metodos |
|---|---|
| `/professores` | GET, POST, GET `/{id}`, PUT `/{id}`, DELETE `/{id}` |
| `/disciplinas` | GET, POST, GET `/{id}`, PUT `/{id}`, DELETE `/{id}` |
| `/turmas` | GET, POST, GET `/{id}`, PUT `/{id}`, DELETE `/{id}` |
| `/horarios` | GET, POST, GET `/{id}`, PUT `/{id}`, DELETE `/{id}` |
| `/professores/{id}/horarios` | GET: disciplinas, turmas, dias e horarios do professor |
| `/turmas/{id}/horarios` | GET: professores, disciplinas, dias e horarios da turma |

Exemplo de corpo para cadastrar um horario (POST `/horarios`):

```json
{
  "professorId": 1,
  "disciplinaId": 1,
  "turmaId": 1,
  "diaSemana": "FRIDAY",
  "horaInicio": "19:00:00",
  "horaFim": "20:40:00"
}
```

Dias da semana aceitos: MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY.

## Validacoes

- Nome, email, semestre, carga horaria e demais campos obrigatorios (resposta 400 com a lista de erros)
- Email em formato valido
- Carga horaria maior que zero
- Hora de fim depois da hora de inicio
- Professor, disciplina e turma precisam existir (resposta 404)
- Nao e possivel excluir professor, disciplina ou turma que ja tenham horarios (resposta 409)

## Estrutura (MVC)

```
src/main/java/br/edu/unisales/horarios
  model        entidades JPA (Professor, Disciplina, Turma, Horario)
  repository   acesso ao banco (Spring Data JPA)
  service      regras de negocio
  controller   endpoints REST
  dto          objetos de entrada e saida de Horario
  config       seguranca, Swagger e dados de exemplo
  exception    tratamento de erros
```

## Banco de dados

H2 em memoria. Os dados sao recriados toda vez que a aplicacao inicia.
