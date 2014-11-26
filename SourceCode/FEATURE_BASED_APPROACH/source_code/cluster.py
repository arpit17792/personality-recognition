from sklearn import cluster
import csv
with open('CLUSTERING_DATA/copn.csv', 'rb') as f:
        reader = csv.reader(f)
        line_count=0;
        X=[]
        for split_line in reader:
        	if line_count==0:
        		line_count=1
        		continue
		tmp=[]
		tmp.append(split_line[3])
		X.append(tmp)
		
	print X
k_means = cluster.KMeans(25)
k_means.fit(X)
print k_means.labels_[:len(X)]
