# RedPanda Security Labs
RedPanda Security Labs é uma aplicação web desenvolvida em Sping Boot.
A ideia é juntar várias vulnerabilidades e criar laboratórios de exploração
para ajudar a estudantes de Segurança Web e revisão de código.

## Instalação via DockerHub
A instalação pode ser feita via dockerhub acessando o seguinte link
https://hub.docker.com/r/c4v4r0n/redpandalab

## Instalação Manual
Executar a aplicação em docker é extremamente fácil, os seguintes comandos devem ser o suficiente para
iniciar a aplicação usando docker e buildx.

Também é possível executar a aplicação diretamente pela IDLE Intellij usando java 17.

### Docker
```
git clone https://github.com/RedPanda-Seguranca-Ofensiva/SecurityLabs
cd SecurityLabs
docker buildx build -t redpandalab:v1 .
docker run --rm -ti -p 8081:8081 redpandalab:v1
```

### jar
O arquivo jar já compilado existe dentro do diretório target/, você deve conseguir executa-lo
usando
 ```
 java -jar target/labs-0.0.1-SNAPSHOT.jar
 ```