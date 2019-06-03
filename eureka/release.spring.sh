#!/bin/bash
JAVA_OPTS='-Xmx1g -Xms512m -Xmn128m -XX:SurvivorRatio=8 -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=1g -XX:MaxTenuringThreshold=15'
port=30000
active_profile=rel
if [ $# -gt 0 ]
then
port=$1
fi
echo '启动端口: '${port}
logging_file=/opt/spring-logs/qa/${port}.log
git pull
mvn clean package -DskipTests
rc=$?
if [[ ${rc} -ne 0 ]] ; then
    print "[FAIL] maven package error"
    exit ${rc}
fi
printf "[SUCCESS] maven package success, restarting..."
spid=`ps -ef|grep 'server.port='${port} | awk '{print $2 $8}' | grep java`
spid=${spid/'java'/''}
if [ ${spid} ]
then
kill -9 ${spid}
if [ $? = 0 ]
  then
  echo '成功结束进程:'${spid}
else
  echo '进程结束失败'
fi
else
echo '不存在进程'
fi

jar_file=`ls target/|awk '/.*jar$/{print}'`

if [ ${jar_file} ]
  then
  echo 'find jar file'
  nohup java -jar target/${jar_file} --spring.profiles.active=${active_profile} --server.port=${port} --logging.file=${logging_file} > /dev/null 2>&1 &
  tailf ${logging_file}
else
  echo '未找到 target文件夹下的jar文件'
fi
