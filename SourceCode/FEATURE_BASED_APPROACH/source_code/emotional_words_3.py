# coding=utf-8
import nltk
import csv
anger = ['belligerence','envious','aggravate','resentful','abominate','murderously','greedy','hatred','disdain','envy','annoy','mad','jealousy','huffiness','sore','anger','harass','bother','enraged','hateful','irritating','hostile','outrage','devil','irritate','angry','furious','temper','angered','outraged','ire','furious','annoyed','outraged']
disgust = ['nauseous','sicken','foul','disgust','nausea','revolt','hideous','horror','detestable','wicked','repel','offensive','repulsive','yucky','repulsive','queasy','obscene','noisome','repulsion','sick','disgusted']
fear = ['fearful','apprehensively','anxiously','presage','horrified','hysterical','timidity','horrible','timid','fright','hesitance','affright','trepid','horrific','unassertive','apprehensiveness','hideous','scarey','cruel','panic','scared','terror','awful','dire','fear','dread','crawl','anxious','distrust','diffidence','panic-stricken','afraid','scared']
happy = ['avidness','glad','warmheartedness','exalt','enjoy','comforting','joviality','cheery','joyful','like','cheer','adoring','fascinating','happy','impress','great','satisfaction','cheerful','charmed','romantic','joy','pleased','inspire','good','fulfill','gladness','merry','triump','titillation','satisfaction','rejoicing','love','liking','exhilarated','triump','euphoric','pleased','happy','festive','jubilation','content']
sad = ['poor','sorry','woeful','guilty','miserable','glooming','bad','grim','tearful','glum','mourning','joyless','sadness','blue','rueful','hamed','regret','hapless','regretful','dismay','dismal','misery','godforsaken','oppression','harass','dark','sadly','attrition','depressed','dispirited','contrite','demoralized','depressed','despair','sadness','doleful']
surprise = ['wondrous','amaze','gravel','marvel','fantastic','wonderful','surprising','marvelous','wonderment','astonish','wonder','admiration','terrific','dumfounded','trounce','surprised','dumbfounded','surprised','perplexed','astonished','surprise']





with open('data/aggregated_status.csv', 'rb') as f:
        reader = csv.reader(f)
        line_count=0;
        with open('data/emotions.csv', 'wb') as csvfile:
		writer = csv.writer(csvfile, delimiter=',',quotechar='\"', quoting=csv.QUOTE_MINIMAL)
	        for split_line in reader:
		
			if line_count==0:
				headings=['AUTH_ID','STATUSES','anger','disgust','fear','happy','sad','surprise']
				writer.writerow(headings)
			count_emotion = [ 0 ,  0 , 0 , 0 , 0 , 0 ]
			split_line[1] = split_line[1].replace(","," ")
			split_line[1] = split_line[1].replace("."," ")
			split_line[1] = split_line[1].replace("'"," ")

			tokenized_text =  nltk.word_tokenize(split_line[1])
	
			#create dictionary

			for i in range(0,len(tokenized_text)):
		
				if tokenized_text[i].lower() in anger:
					count_emotion[0] += 1
				if tokenized_text[i].lower() in disgust:
					count_emotion[1] += 1
				if tokenized_text[i].lower() in fear:
					count_emotion[2] += 1
				if tokenized_text[i].lower() in happy:
					count_emotion[3] += 1
				if tokenized_text[i].lower() in sad:
					count_emotion[4] += 1
				if tokenized_text[i].lower() in surprise:
					count_emotion[5] += 1

			tmp=[]
			tmp.append(split_line[0])
			tmp.append(split_line[1])
			tmp=tmp+count_emotion
			print(tmp)
			writer.writerow(tmp)
			line_count+=1

		
	

