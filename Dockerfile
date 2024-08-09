FROM openjdk:17-jdk

# Instala Google Chrome y dependencias necesarias
RUN apt-get update && \
    apt-get install -y wget gnupg unzip && \
    wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list' && \
    apt-get update && \
    apt-get install -y google-chrome-stable

# Descarga y configura ChromeDriver
RUN wget -q https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/chromedriver && \
    chmod +x /usr/local/bin/chromedriver && \
    rm chromedriver_linux64.zip

# Copia el JAR de tu aplicación al contenedor
ARG JAR_FILE=target/bot-0.0.1.jar
COPY ${JAR_FILE} app_bot.jar
EXPOSE 8080
# Define el comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "/app_bot.jar"]