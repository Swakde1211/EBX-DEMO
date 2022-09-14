#!/bin/bash
# description: Customer Starter Project Setup


# specify the following parameters:
#   1) Full Path to the Starter Project Workspace
#   2) Full Path to where the Target Project Workspace should be created
#   3) The desired name for the Customer's Project in Eclipse  (will replace "cust-mdm" in the Starter Project)
#   4) The 4 letter acronym for the Project Cust  (will replace the "CUST" prefix in the Starter Project)
#   5) The lower case name for the java package node for that Cust (will replace the "cust" package node in the Starter Project)
#   6) The Camel case name for the cust which will be used for Artifact naming that contains the actual Cust Name (will replace the word "Cust" used for Data Model Names, etc in the Starter Project)
#   7) The lower case name for the java package node for that customer (will replace the "cust.mdm" package node in the Starter Project)
#   8) The Camel case name for the Cust (will replace the "Cust" text in the Starter Project)
#   9) The lower case name for the company (will replace the "company" text in the Starter Project)

# Examples:
# /Users/Bala/Documents/ON_Docs/EBX/StarterWorkspace/psworkspace/ /Users/Bala/Documents/ON_Docs/EBX/StarterWorkspace/CustomerWorkspace customer-mdm LOCN location Location customer.mdm Customer customer
# /Users/frankkoh/Projects/PSWorkspace/psworkspace /Users/frankkoh/Projects/AcadianPOCWorkspace acadian-mdm BEMD businessentity BusinessEntity acadian.mdm Acadian acadian

# to set permissions from within the folder containing the script:  chmod 755 starterprojectsetup.sh

echo "*** Setup started ***"

#cd $1
cp -R $1 $2
cd $2
rm -rf .git
mv cust-mdm $3
cd $3

echo "* Changes in cust-mdm *"

# find and replace string within files recursively
export LC_CTYPE=C
export LANG=C

echo "- Find and replace occurrences of cust-mdm with "$3" within the files recursively"
grep -rli 'cust-mdm' * | xargs -I@ sed -i '' -e 's/cust-mdm/'$3'/g' @

echo "- Find and replace occurrences of CUST with "$4" within the files recursively"
grep -rli 'CUST' * | xargs -I@ sed -i '' -e 's/CUST/'$4'/g' @

echo "- Find and replace occurrences of cust with "$5" within the files recursively"
grep -rli 'cust' * | xargs -I@ sed -i '' -e 's/cust/'$5/'g' @

# replacing cust with customer will also replace the <domain> tags in the Perspective artifact files. The below statement is executed to negate that.
#grep -rli '<customer>' * | xargs -I@ sed -i '' -e 's/<customer>/<domain>/g' @
grep -rli $5'>' * | xargs -I@ sed -i '' -e 's/'$5'>/domain>/g' @

echo "- Find and replace occurrences of Cust with "$6" within the files recursively"
grep -rli 'Cust' * | xargs -I@ sed -i '' -e 's/Cust/'$6'/g' @

echo "- Find and replace occurrences of cust.mdm with "$7" within the files recursively. This is for the package structure defined within the java files"
grep -rli 'cust.mdm' * | xargs -I@ sed -i '' -e 's/cust.mdm/'$7'/g' @

echo "- Find and replace occurrences of Cust with "$8" within the files recursively. This is for CustDevArtifactsServiceMain and CustModuleRegistrationListener class names within the java files"
grep -rli 'Cust' * | xargs -I@ sed -i '' -e 's/Cust/'$8'/g' @


# rename the file names
echo "- Find and replace occurrences of Cust with "$8" in the file names"
find . -name '*Cust*' -exec bash -c 'mv $0 ${0/Cust/'$8'}' {} \;

echo "- Find and replace occurrences of Cust with "$6" in the file names"
find . -name '*Cust*' -exec bash -c 'mv $0 ${0/Cust/'$6'}' {} \;

echo "- Find and replace occurrences of cust with "$5" in the file names"
find . -name '*cust*' -exec bash -c 'mv $0 ${0/cust/'$5'}' {} \;

echo "- Find and replace occurrences of CUST with "$4" in the file names"
find . -name '*CUST*' -exec bash -c 'mv $0 ${0/CUST/'$4'}' {} \;


# rename folder names
echo "- Find and replace occurrences of company with "$9" in the folder names"
find . -depth -type d -name 'company' -execdir mv {} ''$9'' \;

echo "- Find and replace occurrences of cust with "$5" in the folder names"
find . -depth -type d -name 'cust' -execdir mv {} ''$5'' \;

# rename cust-mdm in the .project file
echo "- Rename cust-mdm in the .project file with "$3
sed -i '' -e 's/cust-mdm/'$3'/g' .project


# Changes in EBX Server
echo "* Changes in EBX Server *"
cd ../EBX\ Server/
echo "- Find and replace occurrences of cust-mdm with "$3" within the files recursively"
grep -rli 'cust-mdm' * | xargs -I@ sed -i '' -e 's/cust-mdm/'$3'/g' @

# Changes in EBX Home
echo "* Changes in EBX Server *"
cd ../EBX\ Home/
echo "- Delete the existing h2 repository from EBX Home"
rm -rf ebxRepository/h2/repository.h2.db

echo "*** Setup completed ***"
exit 0
