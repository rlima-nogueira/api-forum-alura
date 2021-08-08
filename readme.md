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

1. Opção 
```
export FORUM_DATABASE_URL=jdbc:h2:mem:alura-forum
export FORUM_DATABASE_USERNAME=sa
export FORUM_DATABASE_PASSWORD=
export FORUM_JWT_SECRET=123456

``` 

2. Opção
```
java -jar Dspring.profiles.active=prod 
-DFORUM_DATABASE_URL=jdbc:h2:mem:alura-forum
-DFORUM_DATABASE_USERNAME=sa
-DFORUM_DATABASE_PASSWORD=
-DFORUM_JWT_SECRET=123456
 <nome do arquivo .jar>.jar - 
```

``` 
    docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE='prod' -e FORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -e FORUM_DATABASE_USERNAME='sa' -e FORUM_DATABASE_PASSWORD='' -e FORUM_JWT_SECRET='123456' alura/forum

```