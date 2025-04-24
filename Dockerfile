# ── Stage 1: Build with Maven ──
FROM maven:3.9.2-eclipse-temurin-17 AS builder
WORKDIR /build

# 離線下載依賴，加速重複構建
COPY pom.xml .
COPY common/pom.xml common/
COPY rentauth/pom.xml rentauth/
COPY rentuser/pom.xml rentuser/
COPY rentorder/pom.xml rentorder/
RUN mvn dependency:go-offline -B

# 複製源代碼並打包
COPY . .
RUN mvn clean package -DskipTests

# ── Stage 2: Runtime Image 支援mac linux 不要用apline──
FROM eclipse-temurin:17-jre
WORKDIR /app

# 安裝 Nginx
USER root
RUN apt-get update && \
    apt-get install -y nginx && \
    rm -rf /var/lib/apt/lists/*

# 複製 Nginx 代理配置
COPY proxy.conf /etc/nginx/conf.d/proxy.conf

# 從 builder 階段取出三個服務的可執行 JAR
COPY --from=builder /build/rentauth/target/rentauth-0.0.1-SNAPSHOT.jar .
COPY --from=builder /build/rentuser/target/rentuser-0.0.1-SNAPSHOT.jar .
COPY --from=builder /build/rentorder/target/rentorder-0.0.1-SNAPSHOT.jar .

# 複製並授權啟動腳本
COPY run.sh .
RUN chmod +x run.sh

# 暴露端口
EXPOSE 8080

# 並行啟動三個 Spring Boot 應用
ENTRYPOINT ["./run.sh"]
