# jachin
## 环境配置

### docker 安装 redis
```shell
docker pull redis
mkdir -p /mydata/redis/conf
touch /mydata/redis/conf/redis.conf

docker run -p 6379:6379 --name redis --restart=always --requirepass 123456 \
-v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```


### docekr 安装 mongoDB
```shell
docker pull mongo
mkdir -p /mydata/mongo
docker run -itd -p 27017:27017 --name mongo --restart=always -v /mydata/mongo:/data/db mongo --auth
```


### docker 安装 rabbitmq
```shell
docker run -d --name rabbitmq --restart=always \ 
-p 5671:5671 -p 15671:15671 -p 4369:4369 -p 5672:5672 -p 15672:15672 -p 25672:25672 \
-v /mydata/rabbitmq:/var/lib/rabbitmq \
rabbitmq:management
```

```shell
docker pull minio/minio
docker run -d -p 9000:9000 -p 9001:9001 --name minio --restart=always -e "MINIO_ROOT_USER=minioadmin" -e "MINIO_ROOT_PASSWORD=minioadmin" -v /mydata/minio/data:/data -v /mydata/minio/config:/root/.minio minio/minio server /data --console-address ":9001" --address ":9000"
```

docker run -d \
-p 9000:9000 \
--name minio \
-v /mydata/minio/data:/data \
-e "MINIO_ROOT_USER=minioadmin" \
-e "MINIO_ROOT_PASSWORD=minioadmin" \
minio/minio server /data --console-address ":9090"