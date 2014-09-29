package com.gtcafe.sdna.core.notation;

import java.util.ArrayList;
import java.util.HashMap;

import com.gtcafe.sdna.core.CoreUtils;



/**
 * <b> NaturalNotation </b><br>
 * <br>
 * 
 * Expression the notation by natural mode. The "natural" only expresses the
 * notations without the octave concept in midi. <br>
 * <br>
 * 
 * <b> Members </b>
 * <li> naturalIndex: range is 0 ~ 11.
 * <li> naturalLetters: C, D, E, F, G, A, and B.
 * <li> naturalNumber: 1, 2, 3, 4, 5, 6, 7, b3 ... etc
 * <li> expr: user expression
 * <li> letterSharp
 * 
 * @author rick
 * @see MidiNotation
 * @see http://en.wikipedia.org/wiki/Note
 */
public class NaturalNotation extends HashMap<String, String> {//implements Comparable<NaturalNotation> {

	private static final long serialVersionUID = 1718805317836249877L;
	
	///////////////////////////////////////////////////////////////////////////
	// PROPERTY NAME
	///////////////////////////////////////////////////////////////////////////
	
	public final static String NATURAL_INDEX = "NATURAL_INDEX";
	
	// letter
	public final static String NATURAL_LETTER = "NATURAL_LETTER";
	public final static String SHARP_LETTER = "SHARP_LETTER";
	public final static String FLAT_LETTER = "FLAT_LETTER";
	
	// number
	public final static String NATURAL_NUMBER = "NATURAL_NUMBER";
	public final static String SHARP_NUMBER = "SHARP_NUMBER";
	public final static String FLAT_NUMBER = "FLAT_NUMBER";
	
	// user expression
	public final static String EXPRESSION = "EXPRESSION";
	
	// setting
	public final static String USE_SHARP = "USE_SHARP";
	public final static String LETTER_SYMBOL_RIGHT = "LETTER_SYMBOL_RIGHT";
	public final static String NUMBER_SYMBOL_RIGHT = "NUMBER_SYMBOL_RIGHT";
	
	///////////////////////////////////////////////////////////////////////////

	private boolean debug = false;

	/** range: 0 ~ 11 */
	private int naturalIndex;

	/**
	 * letter: C, D, E, F, G ...
	 */
	private String naturalLetter;

	private String sharpLetter = "";

	private String flatLetter = "";

	/**
	 * range: 1 ~ 7, out major scale: #1, b5, b7 ... etc 數字簡譜形式
	 */
	private String naturalNumber;

	private String sharpNumber = "";

	private String flatNumber = "";

	/**
	 * User's expression
	 */
	private String expr;

	/** default using flat */
	public boolean useSharp = false;
	
	/**
	 * 升降記號的位置, 預設在音名右邊
	 */
	private boolean letterSymbolRight = true;
	
	/**
	 * 升降記號的位置, 預設在數字左邊
	 */
	private boolean numberSymbolRight = false;

	
	///////////////////////////////////////////////////////////////////////////
	// DEFINITION
	///////////////////////////////////////////////////////////////////////////
	/**
	 * 音名系統
	 */
	public static final String[] LETTERS = new String[] { "C", "", "D", "", "E", "F", "", "G", "", "A", "", "B" };

	/**
	 * 簡譜系統
	 */
	public static final String[] NUMBERS = new String[] { "1", "", "2", "", "3", "4", "", "5", "", "6", "", "7" };

	public static final String SHARP = "#";

	public static final String DOUBLE_SHARP = "x";

	public static final String FLAT = "b";

	public static final String DOUBLE_FLAT = "bb";
	///////////////////////////////////////////////////////////////////////////

	/**
	 * Create the notation by natural number
	 * 
	 * @param naturalIndex
	 *            available range: 0~11
	 */
	public NaturalNotation(int naturalIndex) {
		initByNaturalIndex(naturalIndex);
	}

	/**
	 * Create the notation by natural letter
	 * 
	 * @param letter
	 *            available range: C, D, E, F, G ...
	 */
	public NaturalNotation(String letter) {
		this.expr = letter;
		initByLetter(letter);
	}

	private void initByNaturalIndex(int num) {
		// ****************************************
		// mask the index
		num = CoreUtils.maskNaturalIndex(num);

		// ****************************************
		// set the index, letter, number.
		this.naturalIndex = num % LETTERS.length;
		this.naturalLetter = LETTERS[naturalIndex];
		this.naturalNumber = NUMBERS[naturalIndex];

		drawLetter();
	}

	private void drawLetter() {
		// ****************************************
		// contains the sharp or flat.
		if ("".equals(naturalLetter)) {
			this.sharpLetter = setLetterLocation(LETTERS[naturalIndex - 1], SHARP);
			this.flatLetter = setLetterLocation(LETTERS[naturalIndex + 1], FLAT);

			this.sharpNumber = setNumberLocation(NUMBERS[naturalIndex - 1], SHARP);
			this.flatNumber = setNumberLocation(NUMBERS[naturalIndex + 1], FLAT);

		} else {

			// Calculate the double flat
			if (naturalIndex == 11) { // B = Cb
				this.flatLetter = setLetterLocation(LETTERS[0], FLAT);
				this.flatNumber = setNumberLocation(NUMBERS[0], FLAT);
			} else if (!"".equals(LETTERS[naturalIndex + 2])) {
				this.flatLetter = setLetterLocation(LETTERS[naturalIndex + 2], DOUBLE_FLAT);
				this.flatNumber = setNumberLocation(NUMBERS[naturalIndex + 2], DOUBLE_FLAT);
			} else if (!"".equals(LETTERS[naturalIndex + 1])) {
				this.flatLetter = setLetterLocation(LETTERS[naturalIndex + 1], FLAT);
				this.flatNumber = setNumberLocation(NUMBERS[naturalIndex + 1], FLAT);
			}

			// Calculate the double sharp
			if (naturalIndex == 0) { // C = B#
				this.sharpLetter = setLetterLocation(LETTERS[11], SHARP);
				this.sharpNumber = setNumberLocation(NUMBERS[11], SHARP);
			} else if (!"".equals(LETTERS[naturalIndex - 2])) {
				this.sharpLetter = setLetterLocation(LETTERS[naturalIndex - 2], DOUBLE_SHARP);
				this.sharpNumber = setNumberLocation(NUMBERS[naturalIndex - 2], DOUBLE_SHARP);
			} else if (!"".equals(LETTERS[naturalIndex - 1])) {
				this.sharpLetter = setLetterLocation(LETTERS[naturalIndex - 1], SHARP);
				this.sharpNumber = setNumberLocation(NUMBERS[naturalIndex - 1], SHARP);
			}
		}
	}
	
	private String setLetterLocation(String letter, String symbol) {
		if(letterSymbolRight) {
			return letter + symbol;
		} else {
			return symbol + letter;
		}
	}

	private String setNumberLocation(String number, String symbol) {
		if(numberSymbolRight) {
			return number + symbol;
		} else {
			return symbol + number;
		}
	}

	private void initByLetter(String letter) {
		// List l = Arrays.asList(letters);
		useSharp = false;
		if (letter.length() == 1) {
			int num = findNaturalIndex(letter);
			initByNaturalIndex(num);
		} else if (letter.length() == 2) {
			String l = letter.substring(0, 1);
			int num = findNaturalIndex(l);
			String symbol = letter.substring(1);
			if (symbol.equals(SHARP)) {
				useSharp = true;
				initByNaturalIndex(num + 1);
			} else if (symbol.equals(FLAT)) {
				useSharp = false;
				initByNaturalIndex(num - 1);
			} else if (symbol.equals(DOUBLE_SHARP)) {
				useSharp = true;
				initByNaturalIndex(num + 2);
			}
		} else if (letter.length() == 3) {
			String l = letter.substring(0, 1);
			int num = findNaturalIndex(l);
			initByNaturalIndex(num - 2);
		}
	}

	private int findNaturalIndex(String letter) {
		int num = 0;
		for (int i = 0; i < LETTERS.length; i++) {
			if (LETTERS[i].equals(letter)) {
				num = i;
				break;
			}
		}
		return num;
	}

	// **** getter/setter ****
	public String getNaturalLetter() {
		return this.naturalLetter;
	}

	public String getFlatLetter() {
		return this.flatLetter;
	}

	public String getSharpLetter() {
		return this.sharpLetter;
	}

	public int getNaturalIndex() {
		return this.naturalIndex;
	}

	/**
	 * 
	 * @return 簡譜音程
	 */
	public String getNaturalNumber() {
		return this.naturalNumber;
	}

	public String getFlatNumber() {
		return this.flatNumber;
	}

	public String getSharpNumber() {
		return this.sharpNumber;
	}

	public boolean isUseSharp() {
		return this.useSharp;
	}

	public void setUseSharp(boolean useSharp) {
		this.useSharp = useSharp;
	}

	// public void setNaturalNumber(int naturalNumber) {
	// initByNaturalNumber(naturalNumber);
	// }
	public String toString() {
		if (debug)
			return naturalInfo();
		return natural();
	}

	/**
	 * @return 經過調整的 letter. 可能是指 letter, letterSharp, 或 letterFlat
	 */
	public String natural() {
		if (!"".equals(naturalLetter))
			return naturalLetter;
		else {
			if (useSharp)
				return sharpLetter;
			return flatLetter;
		}
	}

	/**
	 * @return 經過調整過的數字音程, 1 ~ 7, b3, #5 ...
	 */
	public String number() {
		if (!"".equals(naturalNumber))
			return this.naturalNumber;
		else {
			if (useSharp)
				return this.sharpNumber;
			return this.flatNumber;
		}
	}

	/**
	 * @return 使用者最初輸入的字串 (原始資料)
	 */
	public String expression() {
		return this.expr;
	}

	public String naturalInfo() {
		return " natural notation: " + natural() + "\n expression: " + this.expr + "\n naturalIndex: " + this.naturalIndex + "\n useSharp: " + this.useSharp + "\n  * Represent by Letter (音名): " + "\n    - naturalLetter: " + this.naturalLetter + "\n    - sharpLetter: " + this.sharpLetter + "\n    - flatLetter: " + this.flatLetter + "\n  * Represent by Number (簡譜): " + "\n    - naturalNumber: " + this.naturalNumber + "\n    - sharpNumber: " + this.sharpNumber + "\n    - flatNumber: " + this.flatNumber + "\n";

	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof NaturalNotation) {
			NaturalNotation nn = (NaturalNotation)o;
			if (nn.getNaturalIndex() == this.naturalIndex 
					&& nn.getNaturalLetter() == this.naturalLetter
					&& nn.getNaturalNumber() == this.naturalNumber)
				return true;
			return false;
		}
		return false;
	}

	
	@Override
	public int hashCode() {
		return this.naturalIndex;
	}
	
	public static void main(String[] args) {

		// NaturalNotation.useSharp = true;
		/*
		 * System.out.println(new NaturalNotation(0)); System.out.println(new
		 * NaturalNotation(1)); System.out.println(new NaturalNotation(2));
		 * System.out.println(new NaturalNotation(3)); System.out.println(new
		 * NaturalNotation(4)); //NaturalNotation.useSharp = false;
		 * System.out.println(new NaturalNotation(5)); System.out.println(new
		 * NaturalNotation(6)); System.out.println(new NaturalNotation(7));
		 * System.out.println(new NaturalNotation(8)); System.out.println(new
		 * NaturalNotation(9)); System.out.println(new NaturalNotation(10));
		 * System.out.println(new NaturalNotation(11));
		 */

		// System.out.println("==================");
		ArrayList<NaturalNotation> nns = new ArrayList<NaturalNotation>();

		for (int i = 0; i < 12; i++)
			nns.add(new NaturalNotation(i));

		System.out.print("Natural\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).natural() + "\t");
		}
		System.out.println();

		System.out.print("NaturalIndex\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).getNaturalIndex() + "\t");
		}
		System.out.println();

		System.out.print("Letter\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).getNaturalLetter() + "\t");
		}
		System.out.println();

		System.out.print(" - FlatLetter\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).getFlatLetter() + "\t");
		}
		System.out.println();

		System.out.print(" - SharpLetter\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).getSharpLetter() + "\t");
		}
		System.out.println();

		System.out.print("Number\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).getNaturalNumber() + "\t");
		}
		System.out.println();

		System.out.print(" - FlatNumber\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).getFlatNumber() + "\t");
		}
		System.out.println();

		System.out.print(" - SharpNumber\t");
		for (int i = 0; i < 12; i++) {
			System.out.print(nns.get(i).getSharpNumber() + "\t");
		}
		System.out.println();

		// ///////
		/*
		 * System.out.println("============ 一般 ==============");
		 * System.out.println(new NaturalNotation("C")); System.out.println(new
		 * NaturalNotation("D")); System.out.println(new NaturalNotation("E"));
		 * System.out.println(new NaturalNotation("F")); System.out.println(new
		 * NaturalNotation("G")); System.out.println(new NaturalNotation("A"));
		 * System.out.println(new NaturalNotation("B"));
		 * System.out.println("============ 少見 ==============");
		 * System.out.println(); System.out.println(new NaturalNotation("C#"));
		 * System.out.println(new NaturalNotation("Ex")); System.out.println(new
		 * NaturalNotation("Fx")); System.out.println(new
		 * NaturalNotation("Gx")); System.out.println(new
		 * NaturalNotation("Ax")); System.out.println(new
		 * NaturalNotation("Bbx")); System.out.println(new
		 * NaturalNotation("B#")); System.out.println(new
		 * NaturalNotation("Cbb"));
		 */
		NaturalNotation nn = new NaturalNotation(1);
		System.out.println(nn);
		nn.setLetterSymbolLocation(false);
		System.out.println(nn);
		nn.setLetterSymbolLocation(true);
		nn.setUseSharp(true);
		System.out.println(nn);
		nn.setNumberSymbolLocation(true);
		System.out.println(nn.number());
	}

	public boolean isLetterSymbolRight() {
		return letterSymbolRight;
	}

	public boolean isNumberSymbolRight() {
		return numberSymbolRight;
	}

	public void setLetterSymbolLocation(boolean isRightSide) {
		this.letterSymbolRight = isRightSide;
		drawLetter();
	}

	public void setNumberSymbolLocation(boolean isRightSide) {
		this.numberSymbolRight = isRightSide;
		drawLetter();
	}

}