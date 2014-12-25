#!/bin/bash
port=8184

pwd=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P)
name=$(basename $pwd)
tomcat=/usr/local/tomcat
memSize=512m
vMount="-v /data/wars/$name:$tomcat/webapps -v /data/log/tomcat/$name:$tomcat/logs -v $pwd/../hosts:/etc/hosts"
image=10.10.40.117:5000/tomcat

docker stop -t 0 $name
docker rm $name
docker run -d $vMount --name $name -m $memSize -p $port:8080 $image