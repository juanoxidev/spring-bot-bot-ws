FROM azul/zulu-openjdk-alpine:17-jre
RUN addgroup -S spring && adduser -S spring -G spring


RUN mkdir -p config/driver/chromedriver-linux
RUN find config/ -type d -exec chmod 0777 {} \;
RUN find config/ -type f -exec chmod 777 {} \;
RUN find config/driver/chromedriver-linux/ -type d -exec chmod 0777 {} \;
RUN find config/driver/chromedriver-linux/ -type f -exec chmod 777 {} \;

ADD config/ /config/
RUN chmod 0777 /config/driver/chromedriver-linux/chromedriver


RUN apk update && apk add --no-cache bash \
alsa-lib \
at-spi2-core \
cairo \
cups-libs \
dbus-libs \
eudev-libs \
expat \
flac \
gdk-pixbuf \
glib \
libgcc \
libjpeg-turbo \
libpng \
libwebp \
libx11 \
libxcomposite \
libxdamage \
libxext \
libxfixes \
tzdata \
libexif \
udev \
xvfb \
zlib-dev \
chromium \
chromium-chromedriver \
&& rm -rf /var/cache/apk/


COPY target/bot-0.0.1.jar /app/app_bot.jar
ENTRYPOINT ["java", "-jar", "/app/app_bot.jar"]
EXPOSE 8080
