package com.gtcafe.sdna.core.notation;

import com.gtcafe.sdna.core.CoreUtils;
import com.gtcafe.sdna.core.IMidiCommon;
import com.gtcafe.sdna.core.IMidiNotes;

/**
 * <b>MIDI Notation</b>
 * <li>MIDI index: 0 ~ 127
 * <li>Midi letter: C-1 ~ G9
 * 
 * @author rick
 * @see http://en.wikipedia.org/wiki/Note
 */
public class MidiNotation implements IMidiCommon {

	private static boolean debug = false;

	/** MIDI Note index, 0 ~ 127 */
	private int midiIndex;

	/**
	 * Name of the notation, consist of letter and octave. The range is from C-1
	 * to G9, ex: C#4 D3 Eb5 F6 G7 A8 B1 ...
	 */
	private String midiLetter;

	/** frequency of A [Hz], A is 440Hz */
	private double freq;

	/** Express the letter by C, D, E, F, G, A, B .... */
	private NaturalNotation natural;

	/** 0 ~ 11 */
	// private int naturalNumber;
	/** range: -1 ~ 9 */
	private int octave;

	/**
	 * initiate in A4
	 */
	public MidiNotation() {
		this(IMidiNotes.A4);
	}

	/**
	 * @param midiIndex
	 *            0 ~ 127
	 */
	public MidiNotation(int midiIndex) {
		initByMidiIndex(midiIndex);
	}

	/**
	 * @param naturalName =
	 *            letter + level, ex: G9, C3, Ax3 ...
	 */
	public MidiNotation(String naturalName) {
		initByNaturalName(naturalName);
	}

	/**
	 * @param letter
	 *            C, C#, D, Gx, Ab ...
	 * @param level
	 *            -1 ~ 9
	 */
	public MidiNotation(String letter, int level) {
		// init members
		this.octave = level;
		this.natural = new NaturalNotation(letter);
		this.midiLetter = this.natural.natural() + level;
		// this.naturalNumber = this.notationLetter.getNaturalNumber();
		this.midiIndex = (this.octave + 1) * SCALE_CYCLE + this.natural.getNaturalIndex();
		this.freq = CoreUtils.calcFreq(midiIndex);
	}

	/**
	 * initial the MidiNotation by natural name
	 * 
	 * @param naturalName
	 */
	private void initByNaturalName(String naturalName) {
		if (naturalName.indexOf("-") > 0) {
			String level = naturalName.substring(naturalName.indexOf("-"), naturalName.length());
			this.octave = Integer.parseInt(level);
			String n = naturalName.substring(0, naturalName.indexOf("-"));
			this.natural = new NaturalNotation(n);
		} else {
			String level = naturalName.substring(naturalName.length() - 1);
			this.octave = Integer.parseInt(level);
			String n = naturalName.substring(0, naturalName.length() - 1);
			this.natural = new NaturalNotation(n);
		}

		this.midiLetter = natural.natural() + octave;
		// this.naturalNumber = notationLetter.getNaturalNumber();
		this.midiIndex = (this.octave + 1) * SCALE_CYCLE + this.natural.getNaturalIndex();
		this.freq = CoreUtils.calcFreq(midiIndex);
	}

	/**
	 * initial the MidiNotation by midiIndex
	 * 
	 * @param midiIndex
	 *            0 ~ 127
	 */
	private void initByMidiIndex(int midiIndex) {

		this.midiIndex = CoreUtils.maskMidiIndex(midiIndex);

		this.octave = this.midiIndex / SCALE_CYCLE - 1;

		int naturalIndex = (this.midiIndex % SCALE_CYCLE);

		this.natural = new NaturalNotation(naturalIndex);
		this.midiLetter = natural.natural() + octave;

		// calculate the frequency for note ..
		this.freq = CoreUtils.calcFreq(midiIndex);
	}

	// *****************************************
	// getter/setter for members

	public double getFreq() {
		return freq;
	}

	public int getOctave() {
		return octave;
	}

	// public void setOctaveValue(int level) {
	// // TODO 上下平移八度
	// this.octave = level;
	// }

	/**
	 * @return the midi Index, available range is 0 ~ 127
	 */
	public int getMidiIndex() {
		return this.midiIndex;
	}

	// public void setMidiNumber(int midiNumber) {
	// initByMidiNumber(midiNumber);
	// }

	/**
	 * @return the midi letter, ex: C1, D1, E1 ...
	 */
	public String getMidiLetter() {
		return this.midiLetter;
	}

	// public void setNaturalName(String naturalName) {
	// initByNaturalName(naturalName);
	// }

	/**
	 * @return the naturalNotation
	 */
	public NaturalNotation getNatural() {
		return this.natural;
	}

	/**
	 * 調整一個八度內的位置
	 * 
	 * @param notationLetter
	 */
	public void setNotationLetter(NaturalNotation notationLetter) {
		changeNaturalIndex(notationLetter.getNaturalIndex());
	}

	/**
	 * 調整一個八度內的位置
	 * 
	 * @param naturalIndex
	 *            0~11
	 */
	public void changeNaturalIndex(int naturalIndex) {
		naturalIndex = CoreUtils.maskNaturalIndex(naturalIndex);
		int midiNum = this.midiIndex + (naturalIndex - this.natural.getNaturalIndex());
		initByMidiIndex(midiNum);
	}

	/**
	 * debug function
	 */
	public String notationInfo() {
		if (debug)
			return "  midiIndex: " + this.midiIndex + "\n  midiLetter: " + this.midiLetter + "\n  naturalIndex: " + this.natural.getNaturalIndex() + "\n  naturalLetter: " + this.natural.natural() + "\n  octave: " + this.octave + "\n  freq: " + this.freq + "\n";
		return "";
	}

	// class method
	public String toString() {
		if (debug)
			return notationInfo();
		return getMidiLetter();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// boundary test
		System.out.println("===== boundary test =====");
		System.out.println(new MidiNotation(-1)); // = 127
		System.out.println(new MidiNotation(0));
		System.out.println(new MidiNotation(127));
		System.out.println(new MidiNotation(128)); // = 0

		// default
		System.out.println("==== Default ====");
		System.out.println(new MidiNotation());

		// normolize...
		// NaturalNotation.useSharp = false;
		System.out.println(new MidiNotation(60));
		System.out.println(new MidiNotation(61));
		System.out.println(new MidiNotation(62));
		System.out.println(new MidiNotation(63));
		System.out.println(new MidiNotation(64));
		System.out.println(new MidiNotation(65));
		// NaturalNotation.useSharp = true;
		System.out.println(new MidiNotation(66));
		System.out.println(new MidiNotation(67));
		System.out.println(new MidiNotation(68));
		System.out.println(new MidiNotation(69));
		System.out.println(new MidiNotation(70));
		System.out.println(new MidiNotation(71));
		System.out.println(new MidiNotation(72));

		System.out.println("===== midiIndex to notation =====");
		// for (int i = 0; i < 128; i++)
		// System.out.println(new MidiNotation(i));

		System.out.println("===== natural to notation =====");
		System.out.println(new MidiNotation(IMidiNotes._A1));
		System.out.println(new MidiNotation("C-1"));
		System.out.println(new MidiNotation(108));
		System.out.println(new MidiNotation("C8"));
		System.out.println(new MidiNotation(104));
		System.out.println(new MidiNotation("G#4"));
		System.out.println(new MidiNotation("C#-1"));

		// function test.
		MidiNotation n1 = new MidiNotation(60);
		System.out.println(n1);
		n1.changeNaturalIndex(5);
		System.out.println(n1);
		n1.setNotationLetter(new NaturalNotation("A"));
		System.out.println(n1);
		System.out.println(n1.getNatural());

		System.out.println(new MidiNotation(IMidiNotes.B2));

//		int i = 1;
//		while (true) {
//			int seed = (int) (Math.random() * 100000);
//			System.out.println(seed + ": " + new MidiNotation(seed));
//			i++;
//			if (i > 100)
//				break;
//		}
	}
}