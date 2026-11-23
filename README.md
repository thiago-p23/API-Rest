# API-Rest

# AutoTech — Sistema de Gestão de Oficina Mecânica

## 1. Descrição do Projeto
Aplicação web full-stack integrada a uma API REST desenvolvida em Java com Spring Boot para digitalizar o controle operacional de uma oficina mecânica. O sistema substitui planilhas e anotações manuais por uma plataforma centralizada que gerencia clientes, veículos, mecânicos, serviços e ordens de serviço.

---

## 2. Problema Atendido, Público Usuário e Funcionalidades
- **Problema Atendido:** Dificuldade no rastreamento de veículos, histórico de manutenções, controle de valores e acompanhamento do status das ordens de serviço em oficinas de pequeno e médio porte.
- **Público Usuário:** Atendentes (recepção e cadastro), gerentes de oficina (acompanhamento operacional) e mecânicos (consulta de serviços atribuídos).
- **Funcionalidades:**
  - Cadastro, listagem, consulta por identificador, atualização e exclusão (CRUD completo) para **5 entidades relacionais**: Clientes, Veículos, Mecânicos, Serviços e Ordens de Serviço.
  - Validação de campos obrigatórios, unicidade de CPF e placa, e integridade referencial entre entidades.
  - Interface web responsiva com feedback visual de carregamento, sucesso, lista vazia e mensagens de erro.

---

## 3. Tecnologias Utilizadas
- **Back-end:** Java 17+, Spring Boot (Web, Data JPA, Validation), Gradle, Springdoc OpenAPI (Swagger UI).
- **Banco de Dados:** H2 Database (persistido localmente em arquivo).
- **Front-end:** HTML5, CSS3 e JavaScript (ES6+ com Fetch API).
- **Ferramentas de Desenvolvimento:** IntelliJ IDEA, Git e GitHub.

---

## 4. Pré-requisitos e Instruções de Instalação
### Pré-requisitos
- **Java Development Kit (JDK) 17** ou superior instalado.
- **Git** instalado.

### Instalação
1. Clone este repositório:
   ```bash
   git clone <URL_DO_SEU_REPOSITORIO_GIT>
   cd sistema-oficina
