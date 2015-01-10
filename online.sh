#!/bin/bash
pwd=$PWD # remain where exec sh
workspace=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P)	# here is the dir of project
cd $workspace

branch=$(git symbolic-ref HEAD 2>/dev/null | cut -d"/" -f 3)	# it's branch name of current git branch
git checkout develop

git pull
git merge $branch	# merge developed

git checkout master
git pull
git merge develop	# merge develop branch
git push		# update beta git
git push online		# update online git

git checkout $branch

git merge master

cd $pwd			# goto where u exec this sh
