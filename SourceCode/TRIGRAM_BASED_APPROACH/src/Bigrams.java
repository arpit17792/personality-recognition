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
import java.util.TreeMap;


public class Bigrams {

	HashSet<String> stopwords = new HashSet<String>();
//Stop words list	
public void addstopword(){
	stopwords.add("a");
	stopwords.add("aaa");
	stopwords.add("all");
	stopwords.add("also");
	stopwords.add("amp");
	stopwords.add("an");
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
	stopwords.add("monon");
	stopwords.add("more");
	stopwords.add("most");
	stopwords.add("my");
	stopwords.add("me");
	stopwords.add("must");
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
		Bigrams Tr = new Bigrams();
		HashMap<String,Integer[]> hmap1=new HashMap<String, Integer[]>();
		TreeMap<Double,ArrayList<String>> top_trigrams=new TreeMap<Double, ArrayList<String>>();
		//Input file to be used
		BufferedReader br=new BufferedReader(new FileReader("class1.txt"));
		BufferedWriter wr=new BufferedWriter(new FileWriter("biop1.txt")); 
		String newLine=null;
		
		int line_count=0;
		
		Tr.addstopword();	
		String trigram_key;
		Integer[] array_addr= new Integer[3];
		while((newLine=br.readLine())!=null){
			
			line_count++;
			newLine = newLine.toLowerCase();
			Tr.stopwordRemove(newLine);
			System.out.println("tokens:"+Tr.tokens);
			for(int i=0;i<(Tr.tokens.size()-1);i++){
				trigram_key=Tr.tokens.get(i)+" "+Tr.tokens.get(i+1);//+" "+Tr.tokens.get(i+2);
				if(hmap1.containsKey(trigram_key)){
					array_addr = hmap1.get(trigram_key);
					array_addr[0]++;
					//To handle df(sentence frequency)
					if(array_addr[2]!=line_count){
						array_addr[1]++;
						array_addr[2]=line_count;
					}
				}
				else{
					array_addr=new Integer[3];
					array_addr[0]=1;
					array_addr[1]=1;
					array_addr[2]=line_count;
					hmap1.put(trigram_key, array_addr);
				}
					
				
			}
				
			
		}
		double tf_idf=0;
		for(Map.Entry<String,Integer[]> entry:hmap1.entrySet()){
//			wr.write(entry.getKey()+" :tf- "+entry.getValue()[0]+" df- "+entry.getValue()[1]+"\n");
				tf_idf=(entry.getValue()[0])*Math.log((line_count/entry.getValue()[1]));
				if(top_trigrams.containsKey(tf_idf)){
					top_trigrams.get(tf_idf).add(entry.getKey());
					
				}else{
					ArrayList<String> arr_tri=new ArrayList<String>();
					arr_tri.add(entry.getKey());
					top_trigrams.put(tf_idf, arr_tri);
				}
				
			
		}
		NavigableMap<Double , ArrayList<String>> mp=top_trigrams.descendingMap();
		line_count=0;
		for(Map.Entry<Double,ArrayList<String>> entry:mp.entrySet()){
			//wr.write(entry.getKey()+" :"+entry.getValue()+"\n");
			for(int i=0;i<entry.getValue().size();i++){
				if(line_count>2000)break;
				wr.write(entry.getValue().get(i)+"\n");
				line_count++;
			}
			if(line_count>2000)break;
				
		}
		wr.close();	
		
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	
}
}