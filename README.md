

Generar build

```
mvn clean install -DskipTests
```

Construir una imagen de Docker desde el Dockerfile en el directorio actual, sin usar el caché

```
docker build --no-cache=True -t bot-image .
```

Crear una nueva red de Docker llamada app_bot_network

```
docker network create app_bot_network
```

Ejecutar un contenedor de Docker basado en la imagen bot-image, conectado a la red app_bot_network

```
docker run --network app_bot_network -p 8080:8080 -p 4444:4444 -p 9223:9222 -p 6900:5900 -it --rm --name bot-container bot-image:latest
```
