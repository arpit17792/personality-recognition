# coding=utf-8
import nltk
import csv

f1 = open('DATA_SET/health_related_words','r')
health_related_words = f1.read().lower().splitlines()
sexual_words=['sexy','sex','frisky','screw','screwed','porn','stang','bed','bump','ass','hump','fuck']
print health_related_words
with open('data/aggregated_status.csv', 'rb') as f:
        reader = csv.reader(f)
        line_count=0;
        with open('data/extra_words_type.csv', 'wb') as csvfile:
        	writer = csv.writer(csvfile, delimiter=',',quotechar='\"', quoting=csv.QUOTE_MINIMAL)
	        for split_line in reader:

			if line_count==0:
				tmp=[]
				tmp.append("AUTH_ID")
				tmp.append("STATUSES")
				tmp.append("health_relatedwords")
				tmp.append("sexual_words")
				
				writer.writerow(tmp)
			line_count+=1
			add_row=['','',0,0]
			add_row[0]=split_line[0]
			add_row[1]=split_line[1]
			tokens=nltk.word_tokenize(split_line[1])
			
			for x in range (0,len(tokens)):
				if tokens[x].lower() in health_related_words:
					add_row[2]+=1
				if tokens[x].lower() in sexual_words:
					add_row[3]+=1
			writer.writerow(add_row)
			
