import nltk
import string
import csv
count_symbols = lambda l1, l2: len(list(filter(lambda c: c in l2, l1)))


with open('data/aggregated_status.csv', 'rb') as f:
        reader = csv.reader(f)
        line_count=0;
        with open('data/remaining_features.csv', 'wb') as csvfile:
        	writer = csv.writer(csvfile, delimiter=',',quotechar='\"', quoting=csv.QUOTE_MINIMAL)
	        for split_line in reader:

			if line_count==0:
				tmp=[]
				tmp.append("AUTH_ID")
				tmp.append("STATUSES")
				tmp.append("Length")
				tmp.append("uniquewords/totalwords")
				tmp.append("punctuations count")
				tmp.append("ratio of uppercase words")
				tmp.append("ratio of uppdercase letters")
				writer.writerow(tmp)
			line_count+=1
			feature = []
			feature.append(split_line[0])
			feature.append(split_line[1])
			feature.append(len(split_line[1]))
		
			tokenized_text =  nltk.word_tokenize(split_line[1])
		
			feature.append(len(set(tokenized_text)) / (len(tokenized_text)*1.0))
	
			feature.append(count_symbols(split_line[1],string.punctuation))
	
			count_upper_word = 0
			count_upper_letter = 0
	
			for word in tokenized_text:
				if word.isupper():
					count_upper_word += 1
				elif word[0].isupper():
					count_upper_letter += 1
	
			feature.append(count_upper_word / (len(tokenized_text)*1.0))		
			feature.append(count_upper_letter / (len(split_line[1])*1.0))	

			writer.writerow(feature)
			print feature
