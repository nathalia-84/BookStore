# Gerenciamento de Livros - IMD0509

Este repositório contém o projeto desenvolvido para a disciplina **IMD0509 - Desenvolvimento para Dispositivos Móveis** do curso de **Bacharelado em Tecnologia da Informação** do **Instituto Metrópole Digital** da **Universidade Federal do Rio Grande do Norte (UFRN)**.

## Índice

- [Descrição do Projeto](#descrição-do-projeto)
- [Funcionalidades do Aplicativo](#funcionalidades-do-aplicativo)
  - [Tela de Login](#tela-de-login)
  - [Tela de Menu Principal](#tela-de-menu-principal)
  - [Tela de Cadastro de Livros](#tela-de-cadastro-de-livros)
  - [Tela de Listagem de Livros](#tela-de-listagem-de-livros)
  - [Tela de Informações Detalhadas de Livros](#tela-de-informações-detalhadas-de-livros)
  - [Tela de Edição de Informações](#tela-de-edição-de-informações)
- [Arquitetura do Projeto](#arquitetura-do-projeto)
- [Contribuições do Grupo](#contribuições-do-grupo)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Ferramentas e Tecnologias Utilizadas](#ferramentas-e-tecnologias-utilizadas)
- [Contato](#contato)

## Descrição do Projeto

O aplicativo desenvolvido trata-se de um sistema para gerenciamento de livros de uma livraria, permitindo as seguintes funcionalidades:

- Cadastro de livros, incluindo informações como título, autor, editora, ISBN, descrição breve e URL da capa.
- Listagem dos livros cadastrados em um formato visual.
- Detalhamento das informações de um livro específico.
- Edição das informações de um livro existente.
- Exclusão de livros utilizando o identificador ISBN.
- Sistema de login simples com opções de cadastro e redefinição de senha.

O projeto foi desenvolvido utilizando o **Jetpack Compose**, um padrão moderno para construção de interfaces no Android, em substituição ao uso tradicional de XML.

## Funcionalidades do Aplicativo

### Tela de Login

- Permite ao usuário fazer login no aplicativo.
- Nesta versão, é utilizado login "admin" e senha "admin" para acessar o sistema.

### Tela de Menu Principal

- Permite ao usuário escolher entre as opções de adicionar livro ou listar os livros cadastrados.

### Tela de Cadastro de Livros

- Permite que o usuário cadastre um livro preenchendo obrigatoriamente os campos: título, autor, editora, ISBN, descrição e URL da capa.
- Caso algum campo não seja preenchido, um **Toast** informa que todos os campos são obrigatórios.
- Se o cadastro for bem-sucedido, um **Toast** confirma a ação.

### Tela de Listagem de Livros

- Lista todos os livros cadastrados em um layout rolável, exibindo a capa, título e nome do autor.
- O usuário pode clicar em um livro para visualizar mais informações.

### Tela de Informações Detalhadas de Livros

- Exibe todas as informações do livro, incluindo capa, título, autor, editora, ISBN e descrição.
- Possui dois botões: um para editar as informações do livro, redirecionando para a tela de edição, e outro para deletar o livro, exibindo um **Toast** de confirmação e retornando à tela de listagem de livros.

### Tela de Edição de Informações

- Permite que o usuário edite qualquer informação do livro.
- Os campos exibem os valores previamente preenchidos.
- Após salvar as alterações, um **Toast** confirma a atualização e o usuário é redirecionado para a tela de informações detalhadas.

## Arquitetura do Projeto

Baseando-se na estrutura apresentada, o projeto segue a arquitetura **MVVM (Model-View-ViewModel)**, que promove uma separação clara entre as camadas de dados, interface e lógica de apresentação. Essa abordagem facilita a manutenção, testabilidade e escalabilidade do código. A organização dos pacotes reflete essa arquitetura:

- **db:** Contém as classes relacionadas ao banco de dados SQLite, como `AppDatabase` e `BookDao`.
- **enums:** Define enumerações utilizadas no projeto, como rotas de navegação.
- **model:** Contém as classes de modelo de dados, como `BookEntity`.
- **ui.theme:** Configurações de tema e estilo.
- **view:** Contém as telas principais e componentes do aplicativo.
- **viewmodel:** Contém a lógica de negócios e classes como `AppViewModel`.

## Contribuições do Grupo

O projeto foi desenvolvido em grupo com os seguintes integrantes e respectivas contribuições:

- **Allyson Santos:**
  - Tela de login.
  - Tela principal.
  - Tela de cadastro de livro.

- **Nathalia Azevedo:**
  - Banco de dados e integração com a funcionalidade de cadastro.
  - Tela de listagem de livros.
  - Tela de informações detalhadas de livros.
  - Tela de edição de informações (e edição no banco de dados).
  - Exclusão de livros do banco de dados.

## Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Abra o projeto no **Android Studio**.
3. Execute o aplicativo em um dispositivo ou emulador configurado.

## Ferramentas e Tecnologias Utilizadas

- Linguagem: **Kotlin**
- Framework: **Jetpack Compose**
- Banco de Dados: **SQLite**
- Gerenciamento de dependências: **Gradle**


