from sklearn import svm
from sklearn.naive_bayes import GaussianNB
import sklearn
import math
import csv
with open('data/FINAL_FEATURES_FACEBOOK.csv', 'rb') as f:

        reader = csv.reader(f)
        line_count=0;
	feature_vectors_matrix_ext=[]
	feature_vectors_matrix_neu=[]
	feature_vectors_matrix_agr=[]
	feature_vectors_matrix_con=[]
	feature_vectors_matrix_opn=[]
	label_cext=[]
	label_cneu=[]
	label_cagr=[]
	label_ccon=[]
	label_copn=[]
        for split_line in reader:
        	if(line_count==0):
        		line_count+=1
        		print split_line[75]
			continue
		#7,8,9,10,11 are labels
		#12-74 form feature vector
		#12-date
		cur_sample_feature_vector_ext=[]
		cur_sample_feature_vector_neu=[]
		cur_sample_feature_vector_agr=[]
		cur_sample_feature_vector_con=[]
		cur_sample_feature_vector_opn=[]
		
		
		#----------------------------------@@@@@@@@@@@@@@@FEATURE VECTOR FORMING@@@@@@@@@@@@@@@@@@@@@@@@@@@----------------------
		ext_imp=[63,44,64,62,65,23,53,37,55,38,34,32,71,49,57,67,29,33,18,16,17,19,13,76]#BAYESIAN---74% ,SVC---63
		neu_imp=[45,32,31,43,27,53,15,61,22,72,34,55,60,21,52,20,29,50,36,73,33,17,19,13]#BAYESIAN --- 62%,SVC---60 
		#agr_imp=[76]#SVC----52%,BAYESIAN--51%
		agr_imp=[19,16,63,13,29,36,68,57,70,33,47,60,41,61,56,38,31,50,72]
		con_imp=[59,19,74,33,65,60,57,64,62,58,35,17,43,73,71,15,53,56,39,31,41,16,13,45,40]#BAYESIAN ----56%,SVC---52%
		opn_imp=[36,62,67,60,29,55,32,19,24,49,31,69,63,50,66,28,43,44,45,74,22]
		for x in ext_imp:
			cur_sample_feature_vector_ext.append(float(split_line[x]))
		for x in neu_imp:
			cur_sample_feature_vector_neu.append(float(split_line[x]))
		for x in agr_imp:
			cur_sample_feature_vector_agr.append(float(split_line[x]))
		for x in con_imp:
			cur_sample_feature_vector_con.append(float(split_line[x]))
		for x in opn_imp:
			cur_sample_feature_vector_opn.append(float(split_line[x]))
		
		feature_vectors_matrix_ext.append(cur_sample_feature_vector_ext)
		feature_vectors_matrix_neu.append(cur_sample_feature_vector_neu)
		feature_vectors_matrix_agr.append(cur_sample_feature_vector_agr)
		feature_vectors_matrix_con.append(cur_sample_feature_vector_con)
		feature_vectors_matrix_opn.append(cur_sample_feature_vector_opn)
		#-----------------------LABEL ADDING------------------------------------
		if(split_line[7]=='y'):
			label_cext.append(1)
		else:
			label_cext.append(0)
		if(split_line[8]=='y'):
			label_cneu.append(1)
		else:
			label_cneu.append(0)
		if(split_line[9]=='y'):
			label_cagr.append(1)
		else:
			label_cagr.append(0)
		if(split_line[10]=='y'):
			label_ccon.append(1)
		else:
			label_ccon.append(0)
		if(split_line[11]=='y'):
			label_copn.append(1)
		else:
			label_copn.append(0)

		#-------------------------------LABEL ADDING COMPLETED---------------------
		
	#--------------cext-----
	classifier=GaussianNB()
	classifier.fit(feature_vectors_matrix_ext[0:200],label_cext[0:200])# using 200 samples to train
	pos=0
	neg=0
	a=0
	b=0
	c=0
	d=0
	for i in range(201,len(label_cext)):
		predicted_label = classifier.predict(feature_vectors_matrix_ext[i])
		if predicted_label == label_cext[i]:
			if predicted_label==0:
				a=a+1
			else:
				d=d+1
			pos += 1
		else:
			if predicted_label==0:
				c=c+1
			else:
				b=b+1
			neg += 1
	print "-----CEXT"
	print pos,neg
	print a,b,c,d
	print "-------"
	#cext completed
	
	#--------------cneu-----
	a=0
	b=0
	c=0
	d=0
	classifier=GaussianNB()
	classifier.fit(feature_vectors_matrix_neu[0:200],label_cneu[0:200])# using 200 samples to train
	pos=0
	neg=0
	for i in range(201,len(label_cneu)):
		predicted_label = classifier.predict(feature_vectors_matrix_neu[i])
		if predicted_label == label_cneu[i]:
			if predicted_label==0:
				a=a+1
			else:
				d=d+1
			pos += 1
		else:
			if predicted_label==0:
				c=c+1
			else:
				b=b+1
			neg += 1
	print "-----CNEU"
	print pos,neg
	print a,b,c,d
	print "-------"
	#cneu completed
	
	#--------------cagr-----
	classifier=GaussianNB()
	classifier.fit(feature_vectors_matrix_agr[0:200],label_cagr[0:200])# using 200 samples to train
	pos=0
	neg=0
	a=0
	b=0
	c=0
	d=0
	for i in range(201,len(label_cagr)):
		predicted_label = classifier.predict(feature_vectors_matrix_agr[i])
		if predicted_label == label_cagr[i]:
			if predicted_label==0:
				a=a+1
			else:
				d=d+1
			pos += 1
		else:
			if predicted_label==0:
				c=c+1
			else:
				b=b+1
			neg += 1
	print "-----CAGR"
	print pos,neg
	print a,b,c,d
	print "-------"
	#cagr completed
	
	#--------------ccon-----
	classifier=GaussianNB()
	classifier.fit(feature_vectors_matrix_con[0:200],label_ccon[0:200])# using 200 samples to train
	pos=0
	neg=0
	a=0
	b=0
	c=0
	d=0
	for i in range(201,len(label_ccon)):
		predicted_label = classifier.predict(feature_vectors_matrix_con[i])
		if predicted_label == label_ccon[i]:
			if predicted_label==0:
				a=a+1
			else:
				d=d+1
			
			pos += 1
		else:
			if predicted_label==0:
				c=c+1
			else:
				b=b+1
			
			neg += 1
	print "-----@@@@@@@@@CCON"
	print pos,neg
	print a,b,c,d
	print "-------"
	#ccon completed
	
	#--------------copn-----
	classifier=GaussianNB()
	classifier.fit(feature_vectors_matrix_con[0:200],label_copn[0:200])# using 200 samples to train
	pos=0
	neg=0
	a=0
	b=0
	c=0
	d=0
	for i in range(201,len(label_copn)):
		predicted_label = classifier.predict(feature_vectors_matrix_con[i])
		if predicted_label == label_copn[i]:
			if predicted_label==0:
				a=a+1
			else:
				d=d+1
			pos += 1
		else:
			if predicted_label==0:
				c=c+1
			else:
				b=b+1
			neg += 1
	print "-----COPN"
	print pos,neg
	print a,b,c,d
	print "-------"
	#copn completed
	
	

