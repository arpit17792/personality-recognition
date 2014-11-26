#tmp=`curl -d 'text=i am very happy' http://text-processing.com/api/sentiment/`
file=now2.csv
while IFS= read -r line
do
        # echo line is stored in $line
	tmp=`curl -d "text=$line" http://text-processing.com/api/sentiment/`
	echo $tmp >> result.csv
	echo "-----------------"
done < "$file"
echo "-----------------------"
#echo $tmp
echo "--------"
