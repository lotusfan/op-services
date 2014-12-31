#!/bin/bash

git checkout develop
git pull
git merge srpdev
git push
git checkout srpdev
git merge develop
