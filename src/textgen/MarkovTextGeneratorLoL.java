package textgen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		List<String> tokens = getTokens("[a-zA-Z.!?]+", sourceText);
		if (tokens.size() == 0) return;
		ListNode starterNode = new ListNode(tokens.get(0));
		this.starter = starterNode.getWord();
		this.wordList.add(starterNode);
		ListNode prevWord = starterNode;
		for (int i = 1; i < tokens.size(); i++) {
			if (nodeExists(prevWord.getWord())) {
				ListNode currentNode = getCurrentNode(prevWord.getWord());
				currentNode.addNextWord(tokens.get(i));
			}
			else {
				this.wordList.add(prevWord);
				prevWord.addNextWord(tokens.get(i));
			}
			prevWord = new ListNode(tokens.get(i));
		}
		ListNode lastNode = new ListNode(tokens.get(tokens.size()-1));
		this.wordList.add(lastNode);
		lastNode.addNextWord(tokens.get(0));
	}
	
	private ListNode getCurrentNode(String word) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(word)) return node;
		}
		return null;
	}


	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if (numWords <= 0) return "";
		String output = starter;
		String currentWord = starter;
		for (int i = 1; i < numWords; i++) {
			if (nodeExists(currentWord)) {
				ListNode node = getCurrentNode(currentWord);
				String nextWord = node.getRandomNextWord(rnGenerator);
				output += " "+nextWord;
				currentWord = nextWord;
			}
			else break;
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}
	
	private boolean nodeExists(String word) {
		for (ListNode n : wordList) {
			if (n.getWord().equals(word)) return true;
		}
		return false;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get("D:\\Eclipse\\MOOCTextEditor\\data\\Hello Goodbye.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gen.retrain(content);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}
	
	//helper method to get the words
	protected List<String> getTokens(String pattern, String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	
	public String getRandomNextWord(Random generator)
	{
		return this.nextWords.get(generator.nextInt(this.nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


