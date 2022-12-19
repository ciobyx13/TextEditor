package document;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {

	public static void main(String[] args) {
		String pattern = "%+";
		String text = "%one%%two%%%three%%%%";
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		for (String s : tokens) {
			System.out.println(s);
		}
		System.out.println();
		for (String s : text.split("[a-z]+")) {
			System.out.println(s);
		}
		

	}

}
