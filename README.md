# GIT COMMANDS


****DELETING Options

To delete all empty directories, untracked-nonempty directories and untracked files  |  git clean -fd

To delete all untracked files  |  git clean -fx

To remove tracked Non-empty directory   |  git rm -rf <directoryname>

To remove tracked file  |  git rm -f <filename>

To remove the file from the repo and not delete it from the local file system use(will be untracked)  |  git rm --cached <filename>
																										 git push origin branch_name  

																					  

****IMPORTING Project from repo to Local

git clone <repo url>	 (to be executed in the workspace folder)		

																	  
																	  
																	  
****ROLLBACK the last COMMIT

git reset HEAD~1		


														  
****BASIC COMMANDS

$ git init
$ git add .
$ git commit -m "Commit message in quotes"       (m stands for message)
$ git status
$ git remote add origin https://github.com/naveenautomation/ebaycourse
$ git push -u origin master
$ git log
$ git --help


****TO CHANGE THE REMOTE URL
$ git remote set-url origin https://github.com/naveenautomation/ebaycourse



****CLONE & PUSH

$ git clone https://github.com/naveenautomation/ebaycourse
$ git pull origin master  


****BRANCHING & MERGING

CREATE BRANCH (when a new branch is created, it will be created with the current status(interms of files/folders) of the existing branch(the branch where we are trying to create a new branch) )

$ git branch <branchname>

DELETE BRANCH FROM LOCAL

$ git branch -d <branchname>

CHECKOUT BRANCH 

$ git checkout <branchname>

MERGE <xyz> BRANCH IN MASTER BRANCH    (first we need to checkout to master branch and then initiate merge)

$ git merge <xyz>

DELETING BRANCH FROM REMOTE

$ git push origin --delete <branchname>




****CONFIGURATIONS:

git config --global user.email "xyz@gmail.com"

git config --global user.name "xyz-github"

