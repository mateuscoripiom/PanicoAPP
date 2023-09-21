

# PanicoAPP <img src="https://cdn-icons-png.flaticon.com/512/1141/1141463.png" width="40px"/>

>Aplicativo voltado para o universo dos filmes da franquia de horror-slasher Pânico, criada por Wes Craven e iniciada em 1996. <br>

><b>Descrição da aplicação:</b> Nesta aplicação voltada para o universo de Pânico, o usuário poderá explorar informações de todos os filmes, e da série. Com direito a galeria de imagens, elenco completo, cadastro, escolha de personagem personalizado e até mesmo uma ligação com o Ghostface. Todas as informações técnicas do filmes serão distribuidas através da API do site TMDB (The Movie Database).

<b>Dupla realizadora:  Mateus Coripio e Yasmim Borges<br>
3º DS ETIM - PAM (Profª. Aline Firmino Brito)</b>

<b>Link para o vídeo de demonstração do APP:</b> https://youtu.be/hL23QKsqOMg

Utilizando a API do <i>The Movie Database (TMDB)</i><br>
<img src="https://cdn-icons-png.flaticon.com/512/252/252006.png" width="15px"/> <b>URL da documentação:</b> https://developers.themoviedb.org/3/getting-started/introduction<br>
<img src="https://cdn-icons-png.flaticon.com/512/252/252006.png" width="15px"/> <b>URL de acesso a API:</b> https://api.themoviedb.org/3/<br>
<img src="https://cdn-icons-png.flaticon.com/512/252/252006.png" width="15px"/> <b>URL do protótipo animado do APP:</b> https://www.figma.com/file/82PaR9kVLWMk2obII3g2Qx/APP-P%C3%A2nico?node-id=0%3A1&t=ivJgoLQBdOvn5ADG-1

<b><h2>Métodos e dados de autenticação:</h2></b>
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
___

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
___

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
___

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
___

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
___

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

<b><h2>Métodos disponíveis (endpoints) e indicação de qual/quais serão implementados na aplicação:</h2></b>
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

___

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
___

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
___

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
___

Configuração: Pega as informações de configuração de todo o sistema.<br>
exemplo: (detahes) - não será utilizado:<br>
GET: /configuration - Contém os dados relevantes para construção de URLs de iamgem e as chaves para alteração.<br> 
exemplo - ````https://api.themoviedb.org/3/configuration?api_key=<<api_key>>````<br>
___

Créditos: Coleta as informações de crédito de filmes e séries.<br>
exemplo: (detahes) - será utilizado:<br>
GET: /configuration - Contém os dados relevantes para construção de URLs de iamgem e as chaves para alteração.<br> 
exemplo - ````https://api.themoviedb.org/3/credit/{credit_id}?api_key=<<api_key>>````<br>
___

Descobrir: Reune diferentes dados de filmes ou séries por classificação, número de votos, gêneros e certificações.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /discover/movie - Usado para descobrir filmes.<br>
exemplo - ````https://api.themoviedb.org/3/discover/movie?api_key=<<api_key>>&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate````<br>
___

Encontrar: Este método facilita a busca de objetos no banco de dados do TMDB.<br>
exemplo: (detahes) - não será utilizado:<br>
GET: /find/{external_id}<br> 
exemplo - ````https://api.themoviedb.org/3/find/{external_id}?api_key=<<api_key>>&language=en-US&external_source=imdb_id````<br>
___

Gêneros: Pega uma lista oficial de gêneros de filmes.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /genre/movie/list<br>
exemplo - ````https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>&language=en-US````<br>
___

Sessões de convidados: Coleta informações de classificação para uma sessão de convidados.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /guest_session/{guest_session_id}/rated/movies - Coleta informações para filmes<br>
exemplo - ````https://api.themoviedb.org/3/guest_session/{guest_session_id}/rated/movies?api_key=<<api_key>>&language=en-US&sort_by=created_at.asc````<br>

___

Palavras-chaves: Pega detalhes de filmes ou uma lista deles, que possuem uma determinada palavra chave.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /keyword/{keyword_id} - Coleta detalhes dos filmes de uma palavra-chave através de seu ID<br>
exemplo - ```` https://api.themoviedb.org/3/keyword/{keyword_id}?api_key=<<api_key>>````
___

Listas: Usado para manipular listas ou ver listas pré-criadas.
exemplo: (detalhes) - não será utilizado:<br>
GET: /list/{list_id} - Coleta detalhes de uma lista já criada<br>
exemplo - ```` https://api.themoviedb.org/3/list/{list_id}?api_key=<<api_key>>&language=en-US````<br>
POST: /list - Cria uma lista nova<br>
exemplo - ````https://api.themoviedb.org/3/list?api_key=<<api_key>>````<br>
Request Body:
````
{
  "name": "This is my awesome test list.",
  "description": "Just an awesome list dawg.",
  "language": "en"
}
````
DELETE: /list/{list_id} - Deleta lista criada<br>
exemplo - ````https://api.themoviedb.org/3/list/{list_id}?api_key=<<api_key>>````<br>
___

Filmes: Usado para pegar qualquer informação sobre filme, seja detalhes, elenco, imagens, estúdio, nomes, etc.<br>
exemplo: (detalhes) - será utilizado:<br>
GET: /movie/{movie_id} - Coleta os detalhes gerais do filme (necessário especificar a linguagem)<br>
exemplo - ````https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US````<br>
Dados retornados:
````{
    "adult": false,
    "backdrop_path": "/44immBwzhDVyjn87b3x3l9mlhAD.jpg",
    "belongs_to_collection": {
        "id": 2602,
        "name": "Pânico: Coleção",
        "poster_path": "/glmWNyOkoXMqltWJJhQelAvIVxk.jpg",
        "backdrop_path": "/dZqsivuEhuHMYgLiMjtMXLImRzE.jpg"
    },
    "budget": 35000000,
    "genres": [
        {
            "id": 27,
            "name": "Terror"
        },
        {
            "id": 9648,
            "name": "Mistério"
        },
        {
            "id": 53,
            "name": "Thriller"
        }
    ],
    "homepage": "http://www.screammovie.com",
    "id": 934433,
    "imdb_id": "tt17663992",
    "original_language": "en",
    "original_title": "Scream VI",
    "overview": "Sam, Tara, Chad e Mindy, os quatro sobreviventes do massacre realizado pelo Ghostface, decidem deixar Woodsboro para trás em busca de um novo começo em uma cidade diferente. Mas não demora muito para eles se tornarem alvo de um novo serial killer mascarado.",
    "popularity": 416.019,
    "poster_path": "/dbd7DQCHZ57SF4tM2puHtwNxDQD.jpg",
    "production_companies": [
        {
            "id": 126588,
            "logo_path": "/cNhOITS96oOV7SCgUHxvZlWRecx.png",
            "name": "Radio Silence",
            "origin_country": "US"
        },
        {
            "id": 130448,
            "logo_path": "/yHWTTGKbOGZKUd1cp6l3uLyDeiv.png",
            "name": "Project X Entertainment",
            "origin_country": "US"
        },
        {
            "id": 143790,
            "logo_path": "/wo1smiXdiwwxai2dwJlRiGwE7rS.png",
            "name": "Spyglass Media Group",
            "origin_country": "US"
        },
        {
            "id": 4,
            "logo_path": "/gz66EfNoYPqHTYI4q9UEN4CbHRc.png",
            "name": "Paramount",
            "origin_country": "US"
        }
    ],
    "production_countries": [
        {
            "iso_3166_1": "US",
            "name": "United States of America"
        }
    ],
    "release_date": "2023-03-08",
    "revenue": 141000000,
    "runtime": 123,
    "spoken_languages": [
        {
            "english_name": "English",
            "iso_639_1": "en",
            "name": "English"
        }
    ],
    "status": "Released",
    "tagline": "Nova York. Novas regras.",
    "title": "Pânico VI",
    "video": false,
    "vote_average": 7.3,
    "vote_count": 474
}
````
GET: /movie/{movie_id}/images - Coleta imagens dos filmes (será muito utilizado)<br>
exemplo - ````https://api.themoviedb.org/3/movie/{movie_id}/images?api_key=<<api_key>>```` <br>
Dados retornados:
````
{
    "backdrops": [
        {
            "aspect_ratio": 1.778,
            "height": 2160,
            "iso_639_1": null,
            "file_path": "/44immBwzhDVyjn87b3x3l9mlhAD.jpg",
            "vote_average": 5.384,
            "vote_count": 2,
            "width": 3840
        },
        {
            "aspect_ratio": 1.778,
            "height": 1080,
            "iso_639_1": null,
            "file_path": "/1svWiR9Jza3Eplhu0zGbTAbzNZd.jpg",
            "vote_average": 5.252,
            "vote_count": 4,
            "width": 1920
        },
        ...
}
````
POST: /movie/{movie_id}/rating - Tendo uma sessão de ID válida, é possível que o usuário dê uma avaliação para o filme. (não será utilizado)<br>
exemplo - ````https://api.themoviedb.org/3/movie/{movie_id}/rating?api_key=<<api_key>>````<br>
Request Body:
````
{
  "value": 8.5
}
````
DELETE: /movie/{movie_id}/rating - Deleta uma avaliação feita pelo próprio usuário. (não será utilizado)<br>
exemplo - ```` https://api.themoviedb.org/3/movie/{movie_id}/rating?api_key=<<api_key>>````<br>
___

Networks: Coleta detalhes de networks, seja imagens ou títulos alternativos.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /network/{network_id} - Coleta detalhes gerais da network especificada<br>
exemplo - ```` https://api.themoviedb.org/3/network/{network_id}?api_key=<<api_key>>````<br>
___

Popularidade: Coleta os filmes ou séries que estão em alta nas últimas 24 horas.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /trending/{media_type}/{time_window}<br>
exemplo - ```` https://api.themoviedb.org/3/trending/all/day?api_key=<<api_key>>````
___

Pessoas: Coleta informações das pessoas envolvidas em produções, sejam detalhes, imagens, popularidade, etc.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /person/{person_id}<br>
exemplo - ```` https://api.themoviedb.org/3/person/{person_id}?api_key=<<api_key>>&language=en-US````
___

Avaliações: Coleta informações de uma avaliação de um filme ou série.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /review/{review_id}<br>
exemplo - ```` https://api.themoviedb.org/3/review/{review_id}?api_key=<<api_key>>````
___

Busca: Utilizado para buscar de uma forma mais geral por empresas, estúdios, palavras-chaves, filmes, etc.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /search/company - Busca por empresas<br>
exemplo - ```` https://api.themoviedb.org/3/search/company?api_key=<<api_key>>&page=1````
___

TV: Tudo relacionado a TV exatamente igual a de filmes, porém os comandos invés de apontarem para filmes, agora apontam para séries, fora isso os comandos seguem o mesmo padrão.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /movie/{movie_id} - Coleta os detalhes gerais do filme (necessário especificar a linguagem)<br>
GET: /tv/{tv_id} - Coleta os detalhes gerais de uma série de TV
___

Provedores de streaming: Coleta informações de streamings onde determinado título está disponível no seu país.<br>
exemplo: (detalhes) - não será utilizado:<br>
GET: /watch/providers/regions - Retorna uma lista que o banco de dados tem streamings cadastrados para determinado país<br>
exemplo - ```` https://api.themoviedb.org/3/watch/providers/regions?api_key=<<api_key>>&language=en-US````
___

<b><h2>Protótipo e mapa de navegação:</h2></b>
<h3>Tela 1: Tela de Splash</h3>
<img src="https://i.imgur.com/ZFBsIxc.png" width="20%">
A tela de splash será somente utilizada na abertura da aplicação.<br>
<h3>Tela 2: Login</h3>
<img src="https://i.imgur.com/Cb0g5i1.png" width="20%">
Nesta tela, será realizado o login do usuário. Caso o usuário não possua um, pode clicar no texto mais abaixo, onde será redirecionado para a próxima tela, a de cadastro. Caso o usuário já tenha um login, ele pode somente colocá-lo e clicar o botão "SCREAM" para adentrar à aplicação. Se o usuário já tiver utilizado o app e logado, esta tela não aparecerá para ele enquanto sua sessão estiver ativa.<br>
<h3>Tela 3: Cadastro</h3>
<img src="https://i.imgur.com/h9noZ34.png" width="20%">
Nesta tela, o usuário poderá realizar o seu cadastro. Será pedido o nome, email e uma senha. Após preencher suas informações, ele pode clicar no botão "SCREAM", que o levará para a tela inicial. Caso ele já possua um login, podera utilizar o texto mais abaixo para ir de volta à tela de login. Todas as informações serão armazenadas no banco de dados da aplicação.<br>
<h3>Tela 4: Inicial</h3>
<img src="https://i.imgur.com/Cz9eTeC.png" width="20%">
Esta tela somente poderá ser acessada pelo usuário se ele estiver logado. Nela, teremos o vislumbre geral de tudo que nossa aplicação pode oferecer. Temos acima, ao lado da logo, uma imagem circular, que na verdade é um botão para acessar a tela de perfil do usuário. Já abaixo, é mostrado todos os filmes da franquia e também uma opção para conhecer mais sobre a série deste universo. Já no canto mais inferior da tela, teremos uma barra de navegação com alguns ícones: tela inicial, filmes, série, atores e a ligação.<br>
<h3>Tela 5: Filmes</h3>
<img src="https://i.imgur.com/S7C9gk0.png" width="20%">
Nesta tela serão exibidos todos os filmes da franquia na ordem de lançamento. Cada filme pode ser clicado e irá redirecionar o usuário para uma tela onde serão mostrados e filtrados dados da API sobre aquele determinado filme.<br>
<h3>Tela 6: Tela de filme/série com API</h3>
<img src="https://i.imgur.com/Qew6li7.png" width="20%">
Esta é a tela onde o usuário é redirecionado quando clica em algum filme ou na série. Através da API, serão requisitados os dados de cada filme específico e exibidos nesta tela de forma filtrada. Teremos imagens, avaliação do público (de acordo com a API), sinopse, Os dois primeiros nomes responsáveis na produção técnica do filme, um botão para redirecionamento do elenco do filme (que será em outra tela) e uma galeria de fotos. Todos os dados serão fornecidos e trazidos pela API. Esta tela também serve para a série, que terá os mesmos tipos de dados dos filmes.<br>
<h3>Tela 7: Elenco</h3>
<img src=https://i.imgur.com/IS0i8zb.png" width="20%">
Nesta tela o usuário poderá pesquisar por filme o elenco. Na barra de pesquisa, ele irá digitar o nome do filme, dentro da coleção Pânico, que deseja e a API irá procurar dentro desta coleção qual é o nome correto. Após encontrar, será retornado os dados de todo o elenco do longa de uma forma filtrada, com nome, imagens e uma pequena descrição.<br>
<h3>Tela 8: Ligação</h3>
<img src="https://i.imgur.com/u9RzPDt.png" width="20%">
Nesta tela, o usuário pode realizar uma ligação com o Ghostface. Através do botão "Ligar", um áudio pré-gravado irá ser tocado com a voz do assassino.<br>
<h3>Tela 9: Perfil do usuário</h3>
<img src="https://i.imgur.com/PbZjShG.png" width="20%">
Após ter clicado no ícone de personagem no topo superior direito de qualquer tela, o usuário será direcionado para um tela onde serão todos os seus dados de cadastro, que foram armazenados no banco de dados. Ele poderá visualizar estes dados e editá-los, também podendo realizar o encerramento de sua sessão e sendo redirecionado de volta à tela de login. Também, ao lado da foto de personagem, o usuário poderá editar esta foto com um botão que irá o levar para a última tela do app.<br>
___

<b><h2>Diagrama de Banco de Dados Interno do APP</h2></b>
<img src="https://i.imgur.com/sgl6JZc.png" width="40%">

<b><h2>Diagrama de Classes do APP</h2></b>
<img src="https://i.imgur.com/GPjt4Fx.png" width="40%">

<b><h2>Diagrama de Entidade Relacionamento - API</h2></b>
<img src="https://images2.imgbox.com/00/00/UrNYlcyu_o.png" width="40%">

<b><h2>Diagrama de Classes - API</h2></b>
<img src="https://images2.imgbox.com/c0/27/Q1YcRDRC_o.jpg" width="40%">

<b><h2>Diagrama do Banco de Dados - API</h2></b>
<img src="https://images2.imgbox.com/66/ae/lyt31W1O_o.png" width="40%">

<b><h1>Novas Atualizações: API</h1></b>

<b><h3>LOGIN E CADASTRO</h3><b>
<p>Login e cadastro atualizados para funcionar de acordo com a API, utilizando os métodos GET e POST para trazer e colocar dados na API./<p><br>
___

<b><h3>NAVBAR</h3><b>
<p>Foram removidos os botões de filmes e seriado, pois seriam coisas duplicadas que já estõa na tela inicial, e foi adicionado duas novas telas: Atores e Personagens.</p><br>
___

<b><h3>TELA INICIAL</h3><b>
<p>Utilização de uma recyclerview para trazer todos os filmes contidos na tabela Obras da API, para mostrar o poster e o ID.</p><br>
___

<b><h3>TELA DE OBRAS</h3><b>
<p>Só é possível acessá-la de acordo com a escolha e Obra que o usuário realizou na tela inicial, utilizando uma recyclerview para trazer os detalhes da tabela Obras na API</p><br>
___

<b><h3>TELA DE ATORES</h3><b>
<p>Utilização de uma recyclerview para trazer todos o elenco contidos da tabela Atores da API, para mostrar imagem, nome e descrição dos atores.</p>
___

<b><h3>TELA DE PERSONAGENS</h3><b>
<p>Utilização de uma recyclerview para trazer todos os personagens contidos da tabela Personagens da API, para mostrar imagem, nome e descrição dos Personagens.</p>
