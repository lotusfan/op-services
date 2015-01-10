#!/bin/bash
pwd=$PWD
workspace=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P)

cd $workspace

git remote add online git@git.car.woqu.com:op/op-service.git

cd $pwd
