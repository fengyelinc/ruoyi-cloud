#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/iuiga_20210908.sql ./mysql/db
cp ../sql/iuiga_config_20211118.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../iuiga-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy iuiga-gateway "
cp ../iuiga-gateway/target/iuiga-gateway.jar ./iuiga/gateway/jar

echo "begin copy iuiga-auth "
cp ../iuiga-auth/target/iuiga-auth.jar ./iuiga/auth/jar

echo "begin copy iuiga-visual "
cp ../iuiga-visual/iuiga-monitor/target/iuiga-visual-monitor.jar  ./iuiga/visual/monitor/jar

echo "begin copy iuiga-modules-system "
cp ../iuiga-modules/iuiga-system/target/iuiga-modules-system.jar ./iuiga/modules/system/jar

echo "begin copy iuiga-modules-file "
cp ../iuiga-modules/iuiga-file/target/iuiga-modules-file.jar ./iuiga/modules/file/jar

echo "begin copy iuiga-modules-job "
cp ../iuiga-modules/iuiga-job/target/iuiga-modules-job.jar ./iuiga/modules/job/jar

echo "begin copy iuiga-modules-gen "
cp ../iuiga-modules/iuiga-gen/target/iuiga-modules-gen.jar ./iuiga/modules/gen/jar

