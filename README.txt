部署步骤：
1.解压缩到目录，修改./bin/run.sh->PROJECT_HOME项，配置为程序根目录
2.maven编译程序，程序根目录执行 mvn install -Dmaven.test.skip=true
3.赋值权限，chmod 755 ./bin/run.sh
4.运行 ./bin/run.sh

配置文件说明：
数据库相关配置：./bin/db-config.properties
定时相关配置：./bin/springqtz.properties
自定义相关配置:./bin/config.conf