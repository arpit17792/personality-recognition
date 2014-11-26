import csv
authors = []
features = []
avg = []
count=[]
sum_val=0
count_val=0
with open('data', 'rb') as f: #"with" equivalent to try-catch block
	reader = csv.reader(f)
	flag = 0
	for split_line in reader:
		if flag == 0:
			flag = 1
			continue
		if split_line[0] in authors:#existing author
			cur_auth_index = authors.index(split_line[0])
			avg[cur_auth_index] =int(avg[cur_auth_index])+int(split_line[1])
			count[cur_auth_index] =count[cur_auth_index]+1
		else:
			authors.append(split_line[0])
			sum_val=split_line[1]
			count_val=1
			avg.append(sum_val)
			count.append(count_val)

	
with open('concept_avg.csv', 'wb') as csvfile:
	writer = csv.writer(csvfile, delimiter=',',quotechar='\"', quoting=csv.QUOTE_MINIMAL)
    
    	
	for i in range(0,len(authors)):
		tmp=[]
		tmp.append(authors[i])
		res=int(avg[i])/int(count[i])
		tmp.append(res)
		

		writer.writerow(tmp)

		     
 
