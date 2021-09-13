# TESTE PRÁTICO - CRUD ESIG

## O QUE FOI FEITO E IMPLEMENTADO:

- a) Criar uma aplicação Java Web utilizando JavaServer Faces (JSF)
- b) Utilizar persistência em um banco de dados PostgreSQL.

## INSTRUÇÕES PARA RODAR EM UM AMBIENTE LOCAL

### OBSERVAÇÕES

- Vou deduzir que já está com algum servidor configurado (no meu caso, utilizei o [APACHE TOMCAT 9](https://tomcat.apache.org/download-90.cgi) e o [eclipse](https://www.eclipse.org/downloads/packages/release/2021-06/r/eclipse-ide-enterprise-java-and-web-developers)) e que já se tenha o [postgreSQL](https://www.postgresql.org/) instalado e com ao menos um usuário, um banco de dados esquema pronto.

- O projeto é feito utilizado maven, então creio que não será necessário fazer download de dependências.

### REQUERIMENTOS

- Um servidor para poder subir a aplicação, pode ser o TOMCAT
- PostgreSQL configurado, com ao menos um usuário, um banco de dados e um esquema

### PASSO A PASSO


1. Com o projeto já baixado, vá em src/main/java/crudESIG.controller.ConnectionController.java
	A partir disso edite a **url** da linha 18, deve estar algo como `jdbc:postgresql://localhost:5432/esigproject`, troque o `esigproject` pelo nome do seu banco de dados. troque o **user**, linha 19 pelo seu nome de usuário do banco de dados e o **password** linha 20 pela sua senha do banco;

2. Se tudo ocorrer bem, ao iniciar o projeto e entrar na página web `http://localhost:8080/crudESIG/faces/index.xhtml`, ele criará a tabela **tarefa**, a qual será utilizada para processos com dados.





