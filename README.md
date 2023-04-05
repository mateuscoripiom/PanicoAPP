# PanicoAPP
<b>Dupla:  Mateus Coripio e Yasmim Borges
3º DS ETIM - PAM (Profª. Aline Firmino Brito)</b>

Utilizando a API do The Movie Database (TMDB)<br>
<b>URL da documentação:</b> https://developers.themoviedb.org/3/getting-started/introduction

<b>URL de acesso a API:</b> https://api.themoviedb.org/3/

<b>Métodos e dados de autenticação:</b><br>
GET: /authentication/guest_session/new - Método utilizado para criar uma nova sessão de convidado. Sessões de convidados deixam o usuário dar notas para filmes e séries sem ter uma conta.<br>
exemplo - ````https://api.themoviedb.org/3/authentication/guest_session/new?api_key=<<api_key>>```` <br>
Dados retornados:
````
{
    "success": true,
    "guest_session_id": "33e636e9b5289bf4cc8eb04aa3f66684",
    "expires_at": "2023-04-06 13:58:28 UTC"
}
````
<br>

GET: /authentication/token/new - Cria uma requisição temporária de um token para possibilitar a validação de um login.<br>
exemplo - ````https://api.themoviedb.org/3/authentication/token/new?api_key=<<api_key>>````<br>
Dados retornados:
````
  {
    "success": true,
    "expires_at": "2023-04-05 15:02:48 UTC",
    "request_token": "61618614927b357ef70e1ef91602bd6fe0599ead"
}
````
<br>

POST: /authentication/session/new - Depois de ter validado o pedido de um token, este método é usado para criar uma ID de sessão válido e completo.<br>
exemplo - ````https://api.themoviedb.org/3/authentication/session/new?api_key=<<api_key>>````<br>
Request body:
````
{
  "request_token": "6bc047b88f669d1fb86574f06381005d93d3517a"
}
````
Dados retornados:
````
{
    "success": false,
    "status_code": 34,
    "status_message": "The resource you requested could not be found."
}
````
<br>

POST: /authentication/token/validate_with_login - Permite que um app valide um pedido de token só colocando o usuário e a senha. <br>
exemplo - ````https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key=<<api_key>>````<br>
Request body:
````
{
  "username": "johnny_appleseed",
  "password": "test123",
  "request_token": "1531f1a558c8357ce8990cf887ff196e8f5402ec"
}
````
Dados retornados:
````
{
    "success": false,
    "status_code": 34,
    "status_message": "The resource you requested could not be found."
}
````
<br>

POST: /authentication/session/convert/4 - Usa este método para criar um ID de sessão v3 se você já tem um token de acesso v4 válido.<br>
exemplo - ````https://api.themoviedb.org/3/authentication/session/convert/4?api_key=<<api_key>>````<br>
Request body:
````
{
  "access_token": "eyK0eXAiOiJAV1QiLCJhbGciOiUIUzI1NiJ9.eyJhdWQiOiI0Ozc2YzA1ZTg4YTY1Yzk0MjFjZDI1NmBiYzRiNGE0NyIsInN1YiI6IjRiYzg4OTJhMDE3YTNjMGY5MjAwMDAwMiIsInNjb3BlayI6WyJhcGlfcmVhZCJdLCL2ZXJzaW9uIjoxfQ.Bn660W0Vi-_AI5HvwIEqtc2s5mAXDknBnTrUREZYH7A"
}
````
Dados retornados:
````
{
    "success": false,
    "failure": true,
    "status_code": 36,
    "status_message": "This token hasn't been granted write permission by the user."
}
````
<br>

DELETE: /authentication/session - Método usado para deletar/deslogar uma sessão.<br>
exemplo - ````https://api.themoviedb.org/3/authentication/session?api_key=<<api_key>>````<br>
Request body:
````
{
  "session_id": "2629f70fb498edc263a0adb99118ac41f0053e8c"
}
````
Dados retornados:
````
{
    "success": false,
    "status_code": 34,
    "status_message": "The resource you requested could not be found."
}
````
<br>

<b>Métodos disponíveis (endpoints) e indicação de qual/quais serão implementados na aplicação:</b>
Certificações: Para obter classificações etárias em filmes e séries.<br>
exemplo (filmes) - será utilizado: <br>
GET: /certification/movie/list - Pega uma lista atualizada de todas as classificações etárias oficialmente suportadas em filmes.<br>
exemplo - ````https://api.themoviedb.org/3/certification/movie/list?api_key=<<api_key>>````<br>
Dados retornados:
````
{
    "certifications": {
        "AU": [
            {
                "certification": "E",
                "meaning": "Exempt from classification. Films that are exempt from classification must not contain contentious material (i.e. material that would ordinarily be rated M or higher).",
                "order": 1
            },
            {
                "certification": "G",
                "meaning": "General. The content is very mild in impact.",
                "order": 2
            },
            {
                "certification": "PG",
                "meaning": "Parental guidance recommended. There are no age restrictions. The content is mild in impact.",
                "order": 3
            },
            {
                "certification": "M",
                "meaning": "Recommended for mature audiences. There are no age restrictions. The content is moderate in impact.",
                "order": 4
            },
            {
                "certification": "MA 15+",
                "meaning": "Mature Accompanied. Unsuitable for children younger than 15. Children younger than 15 years must be accompanied by a parent or guardian. The content is strong in impact.",
                "order": 5
            },...
````
<br>

Mudanças: Para obter lista de filmes, séries ou atores que tiveram alguma alteração na página nas últimas 24 horas (padrão) ou em uma determinada data dentro de um período de 14 dias.<br>
exemplo (filmes) - não será utilizado:<br>
GET: /movie/changes - Pega uma lista de ID's de filmes que foram alterados nas últimas 24 horas.<br>
exemplo - ````https://api.themoviedb.org/3/movie/changes?api_key=<<api_key>>&page=1````<br>
Dados retornados:
````
{
    "results": [
        {
            "id": 423646,
            "adult": false
        },
        {
            "id": 1105014,
            "adult": false
        },
        {
            "id": 1084920,
            "adult": false
        },
        {
            "id": 580428,
            "adult": false
        },
        {
            "id": 1108230,
            "adult": false
        },
        {
            "id": 893250,
            "adult": false
        },
        {
            "id": 975022,
            "adult": false
        },...
````
<br>

Coleções: Para obter imagens, detalhes e traduções de coleções de filmes e séries já criadas.<br>
exemplo (detalhes) - não será utilizado:<br>
GET: /collection/{collection_id} - Coleta detalhes de uma coleção por ID.<br>
exemplo - ````https://api.themoviedb.org/3/collection/{collection_id}?api_key=<<api_key>>&language=en-US````<br>
Dados retornados:
````
{
    "id": 2602,
    "name": "Scream Collection",
    "overview": "The Scream films follow Sidney Prescott and her war against a succession of murderers who adopt the guise of Ghostface to stalk and torment their victims. Sidney receives support from town deputy and later sheriff Dewey Riley, tabloid reporter and writer Gale Weathers, and film-geek Randy Meeks, along with various friends, romantic partners, and other acquaintances that change as the series progresses. From the fifth film onwards, the focus shifts to Billy Loomis's daughter, Samantha Carpenter, and her sister, Tara, who are targeted due to their connection to the original killers. Together with their friends and some of the original survivors (including Sidney, Gale, Dewey, and Kirby), they must contend with new Ghostface killers with motives that may sometimes be connected to the in-universe Stab movies.",
    "poster_path": "/p3EjClFy20jjT0u06dzBs4lvvhi.jpg",
    "backdrop_path": "/dZqsivuEhuHMYgLiMjtMXLImRzE.jpg",
    "parts": [
        {
            "adult": false,
            "backdrop_path": "/4PPC7fKClu0u7NTbo5xgV4vb5VD.jpg",
            "id": 4232,
            "title": "Scream",
            "original_language": "en",
            "original_title": "Scream",
            "overview": "After a series of mysterious deaths befalls their small town, an offbeat group of friends become the target of a masked killer.",
            "poster_path": "/3O3klyyYpAZBBE4n7IngzTomRDp.jpg",
            "media_type": "movie",
            "genre_ids": [
                80,
                27,
                9648
            ],
            "popularity": 111.977,
            "release_date": "1996-12-20",
            "video": false,
            "vote_average": 7.376,
            "vote_count": 5730
        },
        {
            "adult": false,
            "backdrop_path": "/dyqmtee83Df1rGwkRo4N8kHHC3i.jpg",
            "id": 4233,
            "title": "Scream 2",
            "original_language": "en",
            "original_title": "Scream 2",
            "overview": "Away at college, Sidney thought she'd finally put the shocking murders that shattered her life behind her... until a copycat killer begins acting out a real-life sequel.",
            "poster_path": "/dORlVasiaDkJXTqt9bdH7nFNs6C.jpg",
            "media_type": "movie",
            "genre_ids": [
                27,
                9648
            ],
            "popularity": 77.308,
            "release_date": "1997-12-12",
            "video": false,
            "vote_average": 6.46,
            "vote_count": 3377
        },
        ...
````
<br>

Estúdios e produtoras: Coleta detalhes, nomes alternativos e imagens de estúdios e produtoras.<br>
exemplo (detalhes) - não será utilizado:<br>
GET: /company/{company_id} - Coleta detalhes de um estúdio/produtora por ID.<br>
exemplo - ````https://api.themoviedb.org/3/company/{company_id}?api_key=<<api_key>>````<br>
Dados retornados:
````
{
    "description": "",
    "headquarters": "Burbank, California",
    "homepage": "https://www.warnerbros.com",
    "id": 17,
    "logo_path": "/s1y7CTv6YHe87YUGOq6SRB6DmO7.png",
    "name": "Warner Bros. Entertainment",
    "origin_country": "US",
    "parent_company": {
        "name": "Time Warner",
        "id": 128,
        "logo_path": null
    }
}
````
<br>

Configuração: Pega as informações de configuração de todo o sistema.<br>
exemplo: (detahes) - não será utilizado:<br>
GET: /configuration - Contém os dados relevantes para construção de URLs de iamgem e as chaves para alteração.<br> 
exemplo - ````https://api.themoviedb.org/3/configuration?api_key=<<api_key>>````<br>


Créditos: Coleta as informações de crédito de filmes e séries.<br>
exemplo: (detahes) - será utilizado:<br>
GET: /configuration - Contém os dados relevantes para construção de URLs de iamgem e as chaves para alteração.<br> 
exemplo - ````https://api.themoviedb.org/3/credit/{credit_id}?api_key=<<api_key>>````<br>

Descobrir: Reune diferentes dados de filmes ou séries por classificação, número de votos, gêneros e certificações.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /discover/movie - Usado para descobrir filmes.<br>
exemplo - ````https://api.themoviedb.org/3/discover/movie?api_key=<<api_key>>&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate````<br>

Encontrar: Este método facilita a busca de objetos no banco de dados do TMDB.<br>
exemplo: (detahes) - não será utilizado:<br>
GET: /find/{external_id}<br> 
exemplo - ````https://api.themoviedb.org/3/find/{external_id}?api_key=<<api_key>>&language=en-US&external_source=imdb_id````<br>

Gêneros: Pega uma lista oficial de gêneros de filmes.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /genre/movie/list<br>
exemplo - ````https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>&language=en-US````<br>

Sessões de convidados: Coleta informações de classificação para uma sessão de convidados.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /guest_session/{guest_session_id}/rated/movies - Coleta informações para filmes<br>
exemplo - ````https://api.themoviedb.org/3/guest_session/{guest_session_id}/rated/movies?api_key=<<api_key>>&language=en-US&sort_by=created_at.asc````<br>



