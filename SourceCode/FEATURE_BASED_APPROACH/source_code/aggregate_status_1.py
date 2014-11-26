import csv
authors = []
features = []
status = []
with open('data/facebook_data.csv', 'rb') as f: #"with" equivalent to try-catch block
	reader = csv.reader(f)
	flag = 0
	for split_line in reader:
		if flag == 0:
			flag = 1
			continue
		if split_line[0] in authors:#existing author
			cur_auth_index = authors.index(split_line[0])
			status[cur_auth_index] =status[cur_auth_index]+" "+split_line[1].replace(","," ")
		else:
			authors.append(split_line[0])
			status.append(split_line[1].replace(","," "))
			features.append(split_line[2:])
	
with open('aggregated_status.csv', 'wb') as csvfile:
	writer = csv.writer(csvfile, delimiter=',',quotechar='\"', quoting=csv.QUOTE_MINIMAL)
    
    	
	for i in range(0,len(authors)):
		tmp=[]
		tmp.append(authors[i])
		tmp.append(status[i])
		tmp2=features[i]
		for x in range(0,len(tmp2)):
			tmp.append(tmp2[x])

		writer.writerow(tmp)	

		     
 
