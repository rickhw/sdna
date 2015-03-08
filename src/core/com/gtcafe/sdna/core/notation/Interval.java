package com.gtcafe.sdna.core.notation;

/**
 * 音程
 * <b>properties</b><br>
 * <li>semitone: </li>
 * <li>naturalStep: </li>
 * <li>qualityName: </li>
 * 
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 * @see http://en.wikipedia.org/wiki/Interval_(music)
 * @TODO
 */
public class Interval implements IntervalDeclaration {
	
	/**
	 * enharmonic notes (同音異名)
	 * @see http://en.wikipedia.org/wiki/Enharmonic
	 */
	private String enharmonicName;
	
	// ------------------------------------------------------------------------
	// Properties
	// ------------------------------------------------------------------------

	/**
	 * Number of semitone
	 * #range: 0 ~ 23
	 */
	private int _semitone = 0;
	
	/**
	 * step represent a level of natural major scale.
	 * #range: 1 ~ 14
	 */
	private String _step = "1";
	
	/**
	 *  They are perfect, major, minor, augmented, and diminished.
	 */
	private String _qualityName;
	
	/**
	 *  They are represented by P (perfect), M (major), m (minor), A (augmented), and d (diminished) with case sensitive.
	 *   
	 */
	private String _qualityAbbrName;
	
	/**
	 * augmented
	 */
	private String _upQName;
	
	/**
	 * minor, diminished
	 */
	private String _downQName;
	
	// ------------------------------------------------------------------------
	
	/**
	 * create a interval with semitone
	 * @param semitone
	 */
	public Interval(int semitone) {
		initSemitone(semitone);
		this._step = NATURAL_SCALE[this._semitone];
	}
	

	/**
	 * create a interval with step
	 * @param step
	 */
	public Interval(String step) {
		
	}
	
	/**
	 * @TODO
	 * @param n1
	 * @param n2
	 */
	public Interval(NaturalNotation n1, NaturalNotation n2) {
		//TODO, calc the semitone with n1 and n2.
	}
	
	/**
	 * @TODO
	 * @param m1
	 * @param m2
	 */
	public Interval(MIDINotation m1, MIDINotation m2) {
		
	}
	
	// ------------------------------------------------------------------------
	public String toString() {
		return null;
	}
	
	
	// ------------------------------------------------------------------------
	
	private void initSemitone(int semitone) {
		if(semitone < 0)
			semitone = 0;
		this._semitone = (semitone % 24);
	}
	
}
