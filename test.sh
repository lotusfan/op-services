#!/bin/bash
# 用于测试各个服务自己的接口

SCRIPTNAME="${0##*/}"

case "$1" in 
	local)
		git submodule foreach "./test.sh $1"
	;;
	innerfunc)
		git submodule foreach "./test.sh $1"
	;;
	outerfunc)
		git submodule foreach "./test.sh $1"
	;;
	online)
		git submodule foreach "./test.sh $1"
	;;
	*)
		echo "Usage: $SCRIPTNAME {local|innerfunc|outerfunc|online}"
		exit 3;
	;;
esac

exit 0

