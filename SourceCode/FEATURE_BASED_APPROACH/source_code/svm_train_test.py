import numpy as np
from sklearn import cross_validation
from sklearn import svm
w = open('all_features','r')

features = [] 
label_1 = []
label_2 = []
label_3 = []
label_4 = []
label_5 = []
for line in w.readlines():
	line = line.replace('\n','')
	line = line.strip()
	line = line.split(' ')
	feature = map(float,line[0:len(line)])
	features.append(feature[0:len(feature)-1])
	label_1.append(int(feature[len(feature)-5]))
	label_2.append(int(feature[len(feature)-2]))
	label_3.append(int(feature[len(feature)-3]))
	label_4.append(int(feature[len(feature)-4]))
	label_5.append(int(feature[len(feature)-5]))
	

labels = []
labels.append(label_1)
labels.append(label_2)
labels.append(label_3)
labels.append(label_4)
labels.append(label_5)

for iter_i in range(0,len(labels)):
	print set(labels[iter_i])

	label_array = np.asarray(labels[iter_i])
	clf = svm.SVC(kernel='linear', C=1)
	clf.fit(features[0:200],label_array[0:200])
	pos = 0
	neg = 0
	for i in range(201,len(features)):
		predicted_label = clf.predict(features[i])
		if predicted_label == label_array[i]:
			pos += 1
		else:
			neg += 1
#scores = cross_validation.cross_val_score(clf,features[0:500],label_array[0:500],cv=10)
#print scores
	print pos , neg

	
