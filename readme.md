## Definindo profiles VSCode ## 

Acesse ``` launch.json ```, no menu Executar > Adicionar Configurações 

Adicione esse linha ao json dentro do bloco que tenha o main class da sua aplicação: 
```     "args": "--spring.profiles.active=dev" ``` 


#### Gerando o build do projeto ####
<p>Entrar no diretório do projeto e rodar o comando</p>

``` mvn clean package ```

<p>O arquivo .jar será gerado dentro do diretório 

``` /target ``` </p>

<p>Para executar o arquivo .jar, acesse o diretório /target e execute o seguinte comando</p>


``` java -jar Dspring.profiles.active=prod <nome do arquivo .jar>.jar -```

Passando variaveis de ambiente, use uma das opções abaixo rodando direto no terminal

1. Opção  (linux/mac)
```
export FORUM_DATASOURCE_URL=jdbc:h2:mem:alura-forum
export FORUM_DATASOURCE_USERNAME=sa
export FORUM_DATASOURCE_PASSWORD=
export FORUM_JWT_SECRET=123456

``` 

2. Opção (windows/linux/mac)
```
java -jar Dspring.profiles.active=prod 
-DFORUM_DATASOURCE_URL=jdbc:h2:mem:alura-forum
-DFORUM_DATASOURCE_USERNAME=sa
-DFORUM_DATASOURCE_PASSWORD=
-DFORUM_JWT_SECRET=123456
 <nome do arquivo .jar>.jar - 
```

3. Opção (windows)
```
SET FORUM_DATASOURCE_URL=jdbc:h2:~/mem:alura-forum
SET FORUM_DATASOURCE_USERNAME=sa
SET FORUM_DATASOURCE_PASSWORD=
SET FORUM_JWT_SECRET=123456
```

``` 
    docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE='prod' -e FORUM_DATASOURCE_URL='jdbc:h2:mem:alura-forum' -e FORUM_DATASOURCE_USERNAME='sa' -e FORUM_DATASOURCE_PASSWORD='' -e FORUM_JWT_SECRET='123456' alura/forum

```


```
heroku login
```
```
heroku container:login
```
```
heroku create <nome-aplicacao>
```
```
heroku git:remote -a <nome-aplicacao>
```
```
heroku container:push web
```
```
heroku container:release web
```
```
heroku open
```


