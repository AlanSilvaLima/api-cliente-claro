# API de Contatos - Projeto Claro

API REST para gerenciamento de contatos, com preenchimento automático de endereço a partir do CEP.

---

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados: Oracle
- RestTemplate (consumo API de CEP)
- Maven (gerenciamento de dependências)
- Git (controle de versão)

---

## Endpoints

| Método | URL | Descrição | Request Body (JSON) | Response Exemplo |
|--------|-----|-----------|-------------------|----------------|
| POST | `/api/contatos` | Criar novo contato | ```json { "email": "teste@email.com", "nome": "Fulano", "telefone": "1199999999", "cep": "01001-000", "endereco": "Praça da Sé", "cidade": "São Paulo", "estado": "SP", "dataNascimento": "2025-10-01" }``` | ```json { "email": "teste@email.com", "nome": "Fulano", "telefone": "1199999999", "cep": "01001-000", "endereco": "Praça da Sé", "cidade": "São Paulo", "uf": "SP", "dataNascimento": "2025-10-01", "dataCadastro": "2025-10-01T21:00:00" }``` |
| GET | `/api/contatos` | Listar todos os contatos | - | ```json [ { "email": "teste@email.com", "nome": "Fulano", "telefone": "1199999999", "cep": "01001-000", "endereco": "Praça da Sé", "cidade": "São Paulo", "uf": "SP", "dataNascimento": "2025-10-01", "dataCadastro": "2025-10-01T21:00:00" } ]``` |
| GET | `/api/contatos/{email}` | Buscar contato por email | - | ```json { "email": "teste@email.com", "nome": "Fulano", "telefone": "1199999999", "cep": "01001-000", "endereco": "Praça da Sé", "cidade": "São Paulo", "uf": "SP", "dataNascimento": "2025-10-01", "dataCadastro": "2025-10-01T21:00:00" }``` |
| PUT | `/api/contatos/{email}` | Atualizar contato existente | ```json { "nome": "Fulano Atualizado", "telefone": "1197777777", "cep": "06233-030" }``` | ```json { "email": "teste@email.com", "nome": "Fulano Atualizado", "telefone": "1197777777", "cep": "06233-030", "endereco": "Rua Paula Rodrigues", "cidade": "Osasco", "uf": "SP", "dataNascimento": "2025-10-01", "dataCadastro": "2025-10-01T21:00:00" }``` |
| DELETE | `/api/contatos/{email}` | Excluir contato | - | 204 No Content |

---

## Observações Técnicas

- O **email** é a chave primária do contato.
- Campos `endereco`, `cidade` e `uf` são preenchidos automaticamente via API externa de CEP ([apicep](https://cdn.apicep.com/)).
- `dataCadastro` é definido automaticamente pelo servidor no POST.
- Todos os endpoints retornam **JSON**.
- Testes podem ser feitos via **Postman** ou outro cliente REST.

---

## Como executar

1. Tenha instalado **Java 17** e **Maven**.
2. Clone o repositório:
   ```bash
   git clone https://github.com/AlanSilvaLima/api-cliente-claro.git

3. Entre na pasta do projeto: cd api-cliente-claro

4. Execute a aplicação: mvn spring-boot:run

5. A API estará disponível em: http://localhost:8080/api/contatos

6. Verificar se application.properties esta configurado de acordo com seu banco de dados .
7. Deve colocar os seguintes o username e senha de acordo com o que foi criado no seu banco de dados, o meu no caso era system o nome e password a senha. "spring.datasource.username=system
   spring.datasource.password=password"

