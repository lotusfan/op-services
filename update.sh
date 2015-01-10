#!/bin/bash
pwd=$PWD # remain where exec sh
workspace=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P) # here is the dir of project
cd $workspace

branch=$(git symbolic-ref HEAD 2>/dev/null | cut -d"/" -f 3) # it's branch name of current git branch
git checkout develop

git pull
git merge $branch	# merge developed
git push
git checkout $branch	# enter original branch

git merge develop	# merge develop branch

cd $pwd			# goto where u exec this sh
