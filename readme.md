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
export FORUM_DATABASE_URL=jdbc:h2:mem:alura-forum
export FORUM_DATABASE_USERNAME=sa
export FORUM_DATABASE_PASSWORD=
export FORUM_JWT_SECRET=123456

``` 

2. Opção (windows/linux/mac)
```
java -jar Dspring.profiles.active=prod 
-DFORUM_DATABASE_URL=jdbc:h2:mem:alura-forum
-DFORUM_DATABASE_USERNAME=sa
-DFORUM_DATABASE_PASSWORD=
-DFORUM_JWT_SECRET=123456
 <nome do arquivo .jar>.jar - 
```

3. Opção (windows)
```
SET FORUM_DATASOURCE_URL=jdbc:h2:mem:alura-forum
SET FORUM_DATASOURCE_USERNAME=sa
SET FORUM_DATASOURCE_PASSWORD=
SET FORUM_JWT_SECRET=!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\}x3?JR3.2zr~v)gYF^8\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\J?N,nvH.<2\.r~w]*e~vgak)X"v8H`MH/7"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/
```

``` 
    docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE='prod' -e FORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -e FORUM_DATABASE_USERNAME='sa' -e FORUM_DATABASE_PASSWORD='' -e FORUM_JWT_SECRET='123456' alura/forum

```



