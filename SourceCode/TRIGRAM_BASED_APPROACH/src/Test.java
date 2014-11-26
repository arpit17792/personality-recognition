import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;


public class Test {

	
	
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
try{
	Test Tr=new Test();
	BufferedReader br=new BufferedReader(new FileReader("triop5.txt"));
	//BufferedReader biop1=new BufferedReader(new FileReader("biop1.txt"));
	BufferedReader tr=new BufferedReader(new FileReader("training.txt"));
	BufferedWriter wr=new BufferedWriter(new FileWriter("accu_class5.txt")); 
	String new_line=null;
	ArrayList<String> trigram=new ArrayList<String>();
	ArrayList<String> bigram=new ArrayList<String>();
	ArrayList<String> ip_status=new ArrayList<String>();
	while((new_line=br.readLine())!=null){
			trigram.add(new_line);
	}
/*	while((new_line=biop1.readLine())!=null){
		bigram.add(new_line);
}*/
	Tr.addstopword();
	String trigram_key;
	boolean tri_check=false,bi_check=false;;
	while((new_line=tr.readLine())!=null){
		tri_check=false;
		bi_check=false;
		new_line = new_line.toLowerCase();
		Tr.stopwordRemove(new_line);
		int i;
		double ratio=0;
		
		//To check for Trigrams
		for( i=0;i<(Tr.tokens.size()-2);i++){
			trigram_key=Tr.tokens.get(i)+" "+Tr.tokens.get(i+1)+" "+Tr.tokens.get(i+2);
			
			if(trigram.contains(trigram_key))
			{
				wr.write("y\n");
				tri_check=true;
				break;
				//ratio++;
			}
				
			
		}
		
		if(tri_check!=true){
			
			
			wr.write("n\n");
		}
		
		

	}
	wr.close();
	
}catch(Exception e){
	e.printStackTrace();
}
}
}
