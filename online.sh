#!/bin/bash

git checkout master
git pull
git merge srpdev
git push
git checkout develop
git merge master
