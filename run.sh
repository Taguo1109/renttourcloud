#!/usr/bin/env bash
set -e

# 啟動 Auth Service
echo "Starting Auth Service on port 8081..."
java -jar /app/rentauth-0.0.1-SNAPSHOT.jar &

# 啟動 User Service
echo "Starting User Service on port 8083..."
java -jar /app/rentuser-0.0.1-SNAPSHOT.jar &

# 啟動 Order Service
echo "Starting Order Service on port 8084..."
java -jar /app/rentorder-0.0.1-SNAPSHOT.jar &

# 啟動 Nginx（前台模式）
echo "Starting Nginx"
nginx -g "daemon off;"

# 等待所有子進程
wait