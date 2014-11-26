import nltk
import csv
with open('data/aggregated_status.csv', 'rb') as f:

        reader = csv.reader(f)
        with open('data/pos_tagging.csv', 'wb') as csvfile:
		writer = csv.writer(csvfile, delimiter=',',quotechar='\"', quoting=csv.QUOTE_MINIMAL)

		line_count=0;
		first_person_singular = [ 'I','me','my','mine' ]
		first_person_plural =  [ 'we','us','our','ours']
		second_person = [ 'you', 'your','yours' ]
		third_person_singular = [ 'he','she','it','his','her','hers','its','him','her']
		third_person_plural = [ 'they','them','their','theirs']
        	for split_line in reader:
        		pos_tags={'CC':0,'CD':0,'DT':0,'EX':0,'FW':0,'IN':0,'JJ':0,'JJR':0,'JJS':0,'LS':0,'MD':0,'NN':0,'NNS':0,'NNP':0,'NNPS':0,
        		'PDT':0,'POS':0,'PRP':0,
        		'PRP$':0,'RB':0,'RBR':0,'RBS':0,
        		'RP':0,'SYM':0,'TO':0,'UH':0,'VB':0,'VBD':0,'VBG':0,'VBN':0,'VBP':0,'VBZ':0,'WDT':0,'WP':0,'WP$':0,'WRB':0,'-NONE-':0,'1PS':0,'1PP':0,'2P':0,'3PS':0,'3PP':0}
			special_symbols=0;        		
			tokens=nltk.word_tokenize(split_line[1])
			tagged = nltk.pos_tag(tokens)
			for x in range (0,len(tagged)):
			
				if tagged[x][1] not in pos_tags:
					special_symbols+=1
				else:
					pos_tags[tagged[x][1]]+=1
	
				if tagged[x][0].lower() in first_person_singular:
					pos_tags["1PS"]+=1
				if tagged[x][0].lower() in first_person_plural:
					pos_tags["1PP"]+=1
				if tagged[x][0].lower() in second_person:
					pos_tags["2P"]+=1
				if tagged[x][0].lower() in third_person_singular:
					pos_tags["3PS"]+=1
				if tagged[x][0].lower() in third_person_plural:
					pos_tags["3PP"]+=1
				
				
		
		
			pos_list=[]
			
			if line_count==0:#for table header
				pos_list.append("USER ID")
				pos_list.append("STATUSES")
				for key, value in pos_tags.iteritems():
					pos_list.append(key); 
				pos_list.append("special symbols")
				writer.writerow(pos_list);
			
			#adding values to file
			pos_list=[]
			pos_list.append(split_line[0])
			pos_list.append(split_line[1])
			
			for key, value in pos_tags.iteritems():
				pos_list.append(value);    
			
			pos_list.append(special_symbols)
			print pos_tags
			print pos_list
			line_count+=1
			
			print "------"
			print line_count
			print "-----"
			writer.writerow(pos_list)	

			
			pos_tags.clear()
		
		
		
		
