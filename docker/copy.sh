#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/demo_20210908.sql ./mysql/db
cp ../sql/demo_config_20211118.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../demo-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy demo-gateway "
cp ../demo-gateway/target/demo-gateway.jar ./demo/gateway/jar

echo "begin copy demo-auth "
cp ../demo-auth/target/demo-auth.jar ./demo/auth/jar

echo "begin copy demo-visual "
cp ../demo-visual/demo-monitor/target/demo-visual-monitor.jar  ./demo/visual/monitor/jar

echo "begin copy iuiga-modules-system "
cp ../iuiga-modules/demo-system/target/iuiga-modules-system.jar ./demo/modules/system/jar

echo "begin copy iuiga-modules-file "
cp ../iuiga-modules/demo-file/target/iuiga-modules-file.jar ./demo/modules/file/jar

echo "begin copy iuiga-modules-job "
cp ../iuiga-modules/demo-job/target/iuiga-modules-job.jar ./demo/modules/job/jar

echo "begin copy iuiga-modules-gen "
cp ../iuiga-modules/demo-gen/target/iuiga-modules-gen.jar ./demo/modules/gen/jar

