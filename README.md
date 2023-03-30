# PanicoAPP
<b>Dupla:  Mateus Coripio e Yasmim Borges
3º DS ETIM - PAM (Profª. Aline Firmino Brito)</b>

Utilizando a API do The Movie Database (TMDB)<br>
<b>URL da documentação:</b> https://developers.themoviedb.org/3/getting-started/introduction

<b>URL de acesso a API:</b> https://api.themoviedb.org/3/

<b>Métodos e dados de autenticação:</b><br>
GET: /authentication/guest_session/new - Método utilizado para criar uma nova sessão de convidado. Sessões de convidados deixam o usuário dar notas para filmes e séries sem ter uma conta. 
exemplo - https://api.themoviedb.org/3/authentication/guest_session/new?api_key=<<api_key>>

GET: /authentication/token/new - Cria uma requisição temporária de um token para possibilitar a validação de um login. 
exemplo - https://api.themoviedb.org/3/authentication/token/new?api_key=<<api_key>>

POST: /authentication/session/new - Depois de ter validado o pedido de um token, este método é usado para criar uma ID de sessão válido e completo.
exemplo - https://api.themoviedb.org/3/authentication/session/new?api_key=<<api_key>>

POST: /authentication/token/validate_with_login - Permite que um app valide um pedido de token só colocando o usuário e a senha. 
exemplo - https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key=<<api_key>>

POST: /authentication/session/convert/4 - Usa este método para criar um ID de sessão v3 se você já tem um token de acesso v4 válido.
exemplo - https://api.themoviedb.org/3/authentication/session/convert/4?api_key=<<api_key>>

DELETE: /authentication/session - Método usado para deletar/deslogar uma sessão.
exemplo - https://api.themoviedb.org/3/authentication/session?api_key=<<api_key>>

<b>Métodos disponíveis (endpoints) e indicação de qual/quais serão implementados na aplicação:</b>

Certificações: Para obter classificações etárias em filmes e séries.
exemplo (filmes) - será utilizado: 
GET: /certification/movie/list - Pega uma lista atualizada de todas as classificações etárias oficialmente suportadas em filmes.
exemplo - https://api.themoviedb.org/3/certification/movie/list?api_key=<<api_key>>

Mudanças: Para obter lista de filmes, séries ou atores que tiveram alguma alteração na página nas últimas 24 horas (padrão) ou em uma determinada data dentro de um período de 14 dias.
exemplo (filmes) - não será utilizado:
GET: /movie/changes - Pega uma lista de ID's de filmes que foram alterados nas últimas 24 horas.
exemplo - https://api.themoviedb.org/3/movie/changes?api_key=<<api_key>>&page=1

Coleções: Para obter imagens, detalhes e traduções de coleções de filmes e séries já criadas.
exemplo (detalhes) - não será utilizado:
GET: /collection/{collection_id} - Coleta detalhes de uma coleção por ID.
exemplo - https://api.themoviedb.org/3/collection/{collection_id}?api_key=<<api_key>>&language=en-US

Estúdios e produtoras: Coleta detalhes, nomes alternativos e imagens de estúdios e produtoras.
exemplo (detalhes) - não será utilizado:
GET: /company/{company_id} - Coleta detalhes de um estúdio/produtora por ID.
exemplo - https://api.themoviedb.org/3/company/{company_id}?api_key=<<api_key>>






