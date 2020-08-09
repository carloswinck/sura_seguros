# Desafio Desenvolvedor Java – Seguros SURA

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)

![](https://img.shields.io/badge/h2-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)



# Estrutura dos arquivos

```
Sura/
 │
 ├── src/main/java/
 │   └── br.com.sure
 │       ├── SuraApplication.java
 │       │
 │       ├── config
 │       │   └── SwaggerConfig.java
 │       │
 │       ├── controller
 │       │   └── CategoriaController.java
 │       │   └── ClienteController.java
 │       │   └── PedidoController.java
 │       │   └── ProdutoController.java
 │       │   └── SegurancaController.java
 │       │
 │       ├── dto
 │       │   └── CategoriaDTO.java
 │       │   └── ClienteDTO.java
 │       │   └── PedidoDTO.java
 │       │   └── PedidoItensDTO.java
 │       │   └── PedidoResponseDTO.java
 │       │   └── ProdutoDTO.java
 │       │   └── UsuarioDataDTO.java
 │       │   └── UsuarioResponseDTO.java
 │       │
 │       ├── model
 │       │   └── Categoria.java
 │       │   └── Cliente.java
 │       │   └── Pedido.java
 │       │   └── PedidoItens.java
 │       │   └── PedidoItensPK.java
 │       │   └── Produto.java
 │       │   └── Papel.java
 │       │   └── Usuario.java
 │       │
 │       ├── repository
 │       │   └── CategoriaRepository.java
 │       │   └── ClienteRepository.java
 │       │   └── PedidoRepository.java
 │       │   └── ProdutoRepository.java
 │       │   └── SegurancaRepository.java
 │       │
 │       ├── security
 │       │   └── JwtTokenFilter.java
 │       │   └── JwtTokenFilterConfigurer.java
 │       │   └── JwtTokenProvider.java
 │       │   └── UsuarioDetalhes.java
 │       │   └── WebSecurityConfig.java
 │       │
 │       ├── service
 │       │   └── CategoriaService.java
 │       │   └── ClienteService.java
 │       │   └── PedidoService.java
 │       │   └── ProdutoService.java
 │       │   └── SegurancaService.java
 │       │
 │       └── JwtAuthServiceApp.java
 │
 ├── src/main/resources/
 │   └── application.yml
 │   └── application.properties
 │   └── data.sql
 │
 ├── .gitignore
 ├── mvnw/mvnw.cmd
 ├── README.md
 └── pom.xml
```

# Introdução

Esse é um sistema completo de pedidos em micro-serviços utilizando o framework Spring Boot. Esse sistema é apenas para uso do desafio da Sura Seguros.



## Como utilizar

Pode se utilizar ou dentro do eclipse ou IntelliJ IDEA, ou rodar por fora utilizando o Maven embutido.

**Antes de rodar, certifique-se que esta utilizando o Java 8 JDK e não o JRE.*



Pelo Maven embutido rodar os seguintes comandos: (se for Linux, pode como ./mvnw )

```
$ mvnw clean
```

```
$ mvnw install
```

```
$ mvnw spring-boot:run
```



Após subir a aplicação você pode acessar o Swagger e o H2 nos seguintes endereços:

http://localhost:8080/swagger-ui.html

![image-20200809173237562](C:\Users\Carlos\AppData\Roaming\Typora\typora-user-images\image-20200809173237562.png)



http://localhost:8080/h2-console

![image-20200809173144059](C:\Users\Carlos\AppData\Roaming\Typora\typora-user-images\image-20200809173144059.png)

## Como se logar utilizando o token

No Swagger, na aba Segurança, no serviço signin vc pode entrar como administrador ou usuário com as seguintes senhas:

​	admin/admim

​	user/user



![image-20200809173948866](C:\Users\Carlos\AppData\Roaming\Typora\typora-user-images\image-20200809173948866.png)



No response será retornado o Token:

![image-20200809174411246](C:\Users\Carlos\AppData\Roaming\Typora\typora-user-images\image-20200809174411246.png)

Você pode usar o Token para obter acesso as funcionalidades utilizando os paramentos do Header ou se logando no próprio Swagger:

![image-20200809174728140](C:\Users\Carlos\AppData\Roaming\Typora\typora-user-images\image-20200809174728140.png)

![image-20200809174931274](C:\Users\Carlos\AppData\Roaming\Typora\typora-user-images\image-20200809174931274.png)



Não esquecer de colocar a palavra Bearer antes do Token que você copiou:

```
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTU5NzAwNjEwMiwiZXhwIjoxNTk3MDA2NDAyfQ.HZZTZbz5emzk7x7HZ5uABLUd5lpH2gUxkgRiDUeZryU
```



Pronto, você está apto para utilizar os serviços. 

Lembres-se que existem 2 papeis, o Admin e o User. Admin pode rodar todos os serviços e User pode apenas consultar.



# Dúvidas me contatar

- Carlos Frederico Winck
- carloswinck@gmail.com
- Celular:  41-9852-44960
