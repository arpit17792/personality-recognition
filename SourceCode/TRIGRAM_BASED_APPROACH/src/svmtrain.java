import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;


public class svmtrain {

	HashSet<String> stopwords = new HashSet<String>();
//Stop words list	
public void addstopword(){
	stopwords.add("a");
	stopwords.add("aaa");
	stopwords.add("all");
	stopwords.add("also");
	stopwords.add("amp");
	stopwords.add("an");
	stopwords.add("am");
	stopwords.add("and");
	stopwords.add("are");
	stopwords.add("as");
	stopwords.add("at");
	stopwords.add("b");
	stopwords.add("bbb");
	stopwords.add("be");
	stopwords.add("been");
	stopwords.add("br");
	stopwords.add("but");
	stopwords.add("by");
	stopwords.add("c");
	stopwords.add("ccc");
	stopwords.add("com");
	stopwords.add("d");
	stopwords.add("do");
	stopwords.add("dst");
	stopwords.add("e");
	stopwords.add("f");
	stopwords.add("for");
	stopwords.add("from");
	stopwords.add("ft");
	stopwords.add("g");
	stopwords.add("gnis");
	stopwords.add("gt");
	stopwords.add("h");
	stopwords.add("had");
	stopwords.add("has");
	stopwords.add("have");
	stopwords.add("he");
	stopwords.add("her");
	stopwords.add("his");
	stopwords.add("html");
	stopwords.add("http");
	stopwords.add("https");
	stopwords.add("i");
	stopwords.add("id");
	stopwords.add("id");
	stopwords.add("if");
	stopwords.add("in");
	stopwords.add("is");
	stopwords.add("it");
	stopwords.add("its");
	stopwords.add("j");
	stopwords.add("k");
	stopwords.add("km");
	stopwords.add("l");
	stopwords.add("lt");
	stopwords.add("m");
	stopwords.add("mi");
	stopwords.add("me");
	stopwords.add("my");
	stopwords.add("may");
	stopwords.add("might");
	stopwords.add("monon");
	stopwords.add("more");
	stopwords.add("most");
	stopwords.add("n");
	stopwords.add("name");
	stopwords.add("nbsp");
	stopwords.add("new");
	stopwords.add("no");
	stopwords.add("not");
	stopwords.add("o");
	stopwords.add("of");
	stopwords.add("off");
	stopwords.add("on");
	stopwords.add("one");
	stopwords.add("or");
	stopwords.add("org");
	stopwords.add("p");
	stopwords.add("pref");
	stopwords.add("q");
	stopwords.add("r");
	stopwords.add("rd");
	stopwords.add("ref");
	stopwords.add("s");
	stopwords.add("said");
	stopwords.add("say");
	stopwords.add("she");
	stopwords.add("so");
	stopwords.add("sq");
	stopwords.add("t");
	stopwords.add("td");
	stopwords.add("th");
	stopwords.add("that");
	stopwords.add("the");
	stopwords.add("their");
	stopwords.add("they");
	stopwords.add("this");
	stopwords.add("those");
	stopwords.add("there");
	stopwords.add("these");
	stopwords.add("to");
	stopwords.add("u");
	stopwords.add("unit");
	stopwords.add("url");
	stopwords.add("us");
	stopwords.add("utc");
	stopwords.add("v");
	stopwords.add("w");
	stopwords.add("was");
	stopwords.add("we");
	stopwords.add("were");
	stopwords.add("what");
	stopwords.add("when");
	stopwords.add("which");
	stopwords.add("white");
	stopwords.add("who");
	stopwords.add("will");
	stopwords.add("with");
	stopwords.add("would");
	stopwords.add("www");
	stopwords.add("x");
	stopwords.add("y");
	stopwords.add("you");
	stopwords.add("z");
	stopwords.add("null");

}

//To store tokens of each line
ArrayList<String> tokens=new ArrayList<String>();
//Called from main()...Remove stop words and return  
public void stopwordRemove(String remLine){
	
	remLine=remLine.replaceAll("[^A-Za-z\']+", " ");
	remLine=remLine.replaceAll("[\']+", "");
	String[] splitLine=remLine.split("\\s+");
	tokens.clear();
	for(int i=0;i<splitLine.length;i++){
		splitLine[i]=splitLine[i].trim();
		if(!stopwords.contains(splitLine[i])&&splitLine[i].length()!=0){
			tokens.add(splitLine[i]);
		}
	}
				
}
	
public static void main(String[] args){
	

	try {
		svmtrain Tr = new svmtrain();
		HashSet<String> hmap1=new HashSet<String>();
		HashSet<String> hmap2=new HashSet<String>();
		TreeMap<Double,ArrayList<String>> top_trigrams=new TreeMap<Double, ArrayList<String>>();
		//Input file to be used
		BufferedReader br=new BufferedReader(new FileReader("svm_ext.txt"));
		BufferedReader br2=new BufferedReader(new FileReader("svm_neu.txt"));
		BufferedReader br3=new BufferedReader(new FileReader("svm_agr.txt"));
		BufferedReader br4=new BufferedReader(new FileReader("svm_con.txt"));
		BufferedReader br5=new BufferedReader(new FileReader("svm_opn.txt"));
		//BufferedWriter wr=new BufferedWriter(new FileWriter("svm_opn.txt"));//contain trigrams
		BufferedReader rr=new BufferedReader(new FileReader("training.txt"));
		BufferedWriter ww=new BufferedWriter(new FileWriter("opn_test.txt"));
		String newLine=null;
		
		int line_count=0;
		
		Tr.addstopword();	
		String trigram_key;
	//	Integer[] array_addr= new Integer[3];
		while((newLine=br5.readLine())!=null){
			
			hmap1.add(newLine);
			
		}

		while((newLine=br.readLine())!=null){
			
			hmap2.add(newLine);
			
		}
		while((newLine=br2.readLine())!=null){
			
			hmap2.add(newLine);
			
		}
		while((newLine=br3.readLine())!=null){
	
			hmap2.add(newLine);
	
		}
		while((newLine=br4.readLine())!=null){
	
			hmap2.add(newLine);
	
		}

		
float trigram_frq1=0,trigram_frq2=0;
float trigram_count=0;	
int status=0;
while((newLine=rr.readLine())!=null){
			
			trigram_frq1=0;
			trigram_frq2=0;
			trigram_count=0;
			newLine = newLine.toLowerCase();
			Tr.stopwordRemove(newLine);
	//		System.out.println("tokens:"+Tr.tokens);
			for(int i=0;i<(Tr.tokens.size()-2);i++){
				trigram_key=Tr.tokens.get(i)+" "+Tr.tokens.get(i+1)+" "+Tr.tokens.get(i+2);
				trigram_count++;
				if(hmap1.contains(trigram_key)){
					trigram_frq1++;
					status=1;
				}
				else if(hmap2.contains(trigram_key)){
					trigram_frq2++;
					status=0;
				}
			}
			if(trigram_count!=0){
					trigram_frq1=(trigram_frq1/trigram_count);
					trigram_frq2=(trigram_frq2/trigram_count);
					ww.write(status+" "+trigram_frq1+" "+trigram_frq2+"\n");
			}		

}
ww.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	
}
}