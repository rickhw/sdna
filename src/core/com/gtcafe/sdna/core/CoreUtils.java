package com.gtcafe.sdna.core;

/**
 * Utils to calculate the midi notes.
 * 
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public class CoreUtils implements IMIDICommon {

	/**
	 * mask the midi index to available range (0 ~ 127)
	 * 
	 * @param mn
	 *            midiNumber
	 * @return available range 0 ~ 127
	 */
	public static int maskMidiIndex(int mn) {
		if (mn >= 128)
			return (mn % 128);
		else if (mn < 0)
			return (128 + (mn % 128));
		return mn;
	}

	/**
	 * octave the midiIndex in -3 ~ +3 octave
	 * 
	 * @param midiIndex
	 *            0 ~ 127
	 * @param octave
	 *            -3 ~ +3
	 * @return midiIndex 0 ~ 127
	 */
	public static int octaveMidiIndex(int midiIndex, int octave) {
		if (octave < -3)
			octave = -3;
		else if (octave > 3)
			octave = 3;

		return maskMidiIndex(midiIndex + octave * 12);
	}

	/**
	 * shift the note in midi mode
	 * 
	 * @param midiIndex
	 * @param shift
	 *            -12 ~ 12
	 * @return midiIndex 0 ~ 127
	 */
	public static int shiftMidiIndex(int midiIndex, int shift) {
		return shiftIndex(midiIndex, shift, 12, false);
	}

	/**
	 * shift the note in natural mode
	 * 
	 * @param midiIndex
	 * @param shift
	 *            range -11 ~ +11
	 * @return 0 ~ 11
	 */
	public static int shiftNaturalIndex(int midiIndex, int shift) {
		return shiftIndex(midiIndex, shift, 11, true);
	}

	/**
	 * shift the note index by the param
	 * 
	 * @param index
	 *            index number
	 * @param shift
	 *            shift size
	 * @param size
	 *            available size
	 * @param isNatural
	 *            natural or midi mode
	 * @return note for natural or midi mode
	 */
	public static int shiftIndex(int index, int shift, int size, boolean isNatural) {
		if (shift < -size)
			shift = -size;
		else if (shift > size)
			shift = size;
		if (isNatural)
			return maskNaturalIndex(index + shift);
		else
			return maskMidiIndex(index + shift);
	}

	/**
	 * mask the natural number to available
	 * 
	 * @param nn
	 *            naturalNumber 0 ~ 11
	 * @return 0 ~ 11
	 */
	public static int maskNaturalIndex(int nn) {
		if (nn >= 12)
			return (nn % 12);
		else if (nn < 0)
			return (12 + (nn % 12));
		return nn;
	}

	/**
	 * Calculate the frequency for midiNotation
	 * 
	 * @param midiIndex
	 * @return Hz
	 */
	public static double calcFreq(int midiIndex) {
		double e = (double) (midiIndex - 69) / 12.0;
		return Math.pow(2.0, e) * 440.0;
	}

	/**
	 * Unit Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(maskNaturalIndex(1));
		System.out.println(maskNaturalIndex(-1));
		System.out.println(maskMidiIndex(-1));
		System.out.println(maskMidiIndex(128));
		System.out.println(maskMidiIndex(234));
	}

}
