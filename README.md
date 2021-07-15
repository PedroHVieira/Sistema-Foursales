<strong>Teste de código | Foursales</strong><br>
Desenvolver API REST utilizando Spring Boot p/ controle de candidatos e cartões de crédito, nesta API deve conter rotas p/ controle dos candidatos e p/ controle dos cartões de créditos dos candidatos, o projeto deve seguir até o ponto que salvaria os dados no banco de dados utilizando o Hibernate.<br>
- <strong>Requisitos</strong>: Rota p/ deletar um candidato deve ser pública
---------------------------------------------------------------------------------------------------------------------------

<strong>PASSOS NECESSÁRIO PARA EXECUÇÃO DO MESMO:</strong><br>
1-BAIXAR OU CLONAR O PROJETO<br>
2-IMPORTAR O PROJETO NO SEU WORKSPACE SPRING<br>
3-INICIALIZAR O MYSQL LOCALMENTE (UTILIZANDO O XAMPP, WAMPP OU ALGUM PROGRAMA SIMILAR)<br>
4-INICIALIZAR O POSTMAN PARA TESTE DA API (OPCIONAL, POIS O PRÓPRIO SISTEMA TEM UMA INTERFACE QUE UTILIZA DESSA API RESP PARA FUNCIONAR)<br>
5-EXECUTAR O SISTEMA<br>

---------------------------------------------------------------------------------------------------------------------------

<strong>INFORMAÇÕES IMPORTANTES:</strong><br>
- <strong>USUÁRIO PADRÃO DO SISTEMA (SERÁ CRIADO JUNTAMENTE COM A EXECUÇÃO DO PROJETO)</strong><br>
	<strong>Usuário</strong>: admin@admin.com<br>
	<strong>Senha</strong>: admin<br><br>
- <strong>DADOS PARA UTILIZAÇÃO DO POSTMAN</strong> <br>
  <strong>Authorization</strong>: Basic Auth<br>
  <strong>Usuário</strong>: admin@admin.com<br>
	<strong>Senha</strong>: admin<br>
  <strong>Body</strong>: <strong>Raw</strong> do tipo <strong>JSON</strong> (Para os verbos HTTP <strong>POST</strong>, <strong>PUT</strong> e <strong>DELETE</strong>)<br>
  <strong>OBS</strong>: Lembrando que para o endpoint do Candidato (/candidato) o verbo HTTP 'DELETE' é público<br>
  <br>
- <strong>DADOS PARA CONEXÃO DO BANCO DE DADOS</strong> <br>
	url: localhost:3306/foursales?createDatabaseIfNotExist=true&useSSL=false<br>
	username: root<br>
	password:<br><br>
- <strong>OS DADOS DE CONEXÃO PODEM SER ALTERADOS NO ARQUIVO ‘src\main\resources\application.properties’</strong><br>

---------------------------------------------------------------------------------------------------------------------------

<strong>ROTEIRO DA INTERFACE</strong>:<br>
<strong>1)HOME (url = /)</strong> <br>
Tela destinado para fazer apresentar a documentação do sistema.<br>

<strong>2)CANDIDATOS (url = /candidatos)</strong><br>
Tela destinada para fazer o Gerenciamento dos Candidatos (CRUD para os Candidatos), sendo que a interface utiliza da API para fazer as funcionalidades (Inserção, Atualizaão e Deleção)<br><br>
-<strong>C (CREATE - INSERÇÃO)</strong> = Feito no endpoint <strong>'/candidato'</strong> utilizando o verbo HTTP <strong>'POST'</strong><br>
-<strong>R (READ - LISTAGEM)</strong> = Feito no endpoint <strong>'/candidato'</strong> utilizando o verbo HTTP <strong>'GET'</strong><br>
-<strong>U (UPDATE - ATUALIZAÇÃO)</strong> = Feito no endpoint <strong>'/candidato'</strong> utilizando o verbo HTTP <strong>'PUT'</strong><br>
-<strong>D (DELETE - DELEÇÃO)</strong> = Feito no endpoint <strong>'/candidato'</strong> utilizando o verbo HTTP <strong>'DELETE'</strong><br>
  
<strong>3)CARTÕES (url = /cartoes)</strong><br>
Tela destinada para fazer o Gerenciamento dos Cartões de Crédito (CRUD para os Cartões de Crédito), sendo que a interface utiliza da API para fazer as funcionalidades (Inserção, Atualizaão e Deleção)<br><br>
-<strong>C (CREATE - INSERÇÃO)</strong> = Feito no endpoint <strong>'/cartao_credito'</strong> utilizando o verbo HTTP <strong>'POST'</strong><br>
-<strong>R (READ - LISTAGEM)</strong> = Feito no endpoint <strong>'/cartao_credito'</strong> utilizando o verbo HTTP <strong>'GET'</strong><br>
-<strong>U (UPDATE - ATUALIZAÇÃO)</strong> = Feito no endpoint <strong>'/cartao_credito'</strong> utilizando o verbo HTTP <strong>'PUT'</strong><br>
-<strong>D (DELETE - DELEÇÃO)</strong> = Feito no endpoint <strong>'/cartao_credito'</strong> utilizando o verbo HTTP <strong>'DELETE'</strong><br>

---------------------------------------------------------------------------------------------------------------------------

<strong>PADRÃO DO JSON PARA O CRUD DO CANDIDATO</strong><br>
 {<br>
    "id": "1",<br>
    "nome": "Candidato Teste",<br>
    "cpf": "111.111.111-11",<br>
    "email": "email@teste.com",<br>
    "cep": "77777-777"<br>
}<br><br>
<strong>PADRÃO DO JSON PARA O CRUD DO CARTÃO DE CRÉDITO</strong><br>
{<br>
    "id" : "1",<br>
    "nome" : "Cartão Teste",<br>
    "bandeira" : "MasterCard",<br>
    "numero" : "099009809809",<br>
    "validade" : "02/25",<br>
    "candidato" : {"id": 1}<br>
}<br>
