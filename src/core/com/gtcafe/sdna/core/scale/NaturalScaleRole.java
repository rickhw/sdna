package com.gtcafe.sdna.core.scale;

import java.util.ArrayList;

import com.gtcafe.sdna.core.notation.NaturalNotation;

/**
 * 一個八度以內 (Natural) 的音階關係定義.
 * <p>
 * <b>功能:</b>
 * 
 * <li> 依據定義, 計算音群, 提供 natural index 跟 natural letter 資料 </li>
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public class NaturalScaleRole implements IScaleTable {

	/**
	 * 音階名稱, 中文名稱
	 */
	private String roleName;

	/**
	 * 音階代號, 數字代碼
	 */
	private int scaleRoleId;

	/**
	 * 音程關係
	 */
	private ArrayList<Integer> scaleIntervals;

	/**
	 * 音符索引: 0 ~ 11
	 */
	private ArrayList<Integer> scaleIndex;
	
	/**
	 * 簡譜音程關係
	 */
	private ArrayList<String> scaleNumbers;

	/**
	 * 音符符號: C, D, E, F ...
	 */
	private ArrayList<NaturalNotation> scaleNotations;

	/**
	 * Create the Natural Scale by Scale ID. There is not key concept in Natural
	 * Scale. The root is C for all roles.
	 * 
	 * @param scaleRoleId
	 *            {@link IScaleDeclaration}
	 */
	public NaturalScaleRole(int scaleRoleId) {
		// init members
		this.scaleRoleId = scaleRoleId;
		this.scaleIntervals = new ArrayList<Integer>();
		this.roleName = SCALE_NAMES[scaleRoleId];
		this.scaleIndex = new ArrayList<Integer>();
		this.scaleNotations = new ArrayList<NaturalNotation>();
		this.scaleNumbers = new ArrayList<String>();

		// create the scaleInterval
		int[] s = SCALE_INTERVALS[scaleRoleId];
		for (int i = 0; i < s.length; i++) {
			scaleIntervals.add(new Integer(s[i]));
		}

		// create the index, number, and natural objects
		this.scaleIndex.add(0);
		this.scaleNumbers.add("1");
		this.scaleNotations.add(new NaturalNotation(0));
		for (int i = 1; i < scaleIntervals.size(); i++) {
			this.scaleIndex.add(scaleIndex.get(i - 1) + scaleIntervals.get(i - 1));
			NaturalNotation nn = new NaturalNotation(scaleIndex.get(i));
			this.scaleNotations.add(nn);
			this.scaleNumbers.add(nn.number());
		}
	}

	public NaturalScaleRole() {
		this(NATURAL_MAJOR);
	}

	/**
	 * 
	 * @param scaleRoleId
	 * @return 音階關係
	 */
	//public static int[] scaleIntervals(int scaleId) {
	//	return SCALE_INTERVALS[scaleId];
	//}

	public String toString() {
		return "scale name: " + roleName 
		+ "\n  naturalLetters: " + this.scaleNotations.toString() 
		+ "\n  naturalIndex: " + this.scaleIndex.toString() 
		+ "\n  naturalNumbers: " + this.scaleNumbers.toString()
		+ "\n  scaleIntervals: " + this.scaleIntervals.toString()
		+ "\n";
	}

	/**
	 * 簡譜音程關係: 1 2 b3 4 b6 ...
	 */
	public ArrayList<String> getScaleNumbers() {
		return scaleNumbers;
	}

	/**
	 * @return 音階名稱
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @return 音符的名稱群
	 */
	public ArrayList<NaturalNotation> getScaleNotations() {
		return this.scaleNotations;
	}

	/**
	 * @return 音符的數字編號
	 */
	public ArrayList<Integer> getScaleIndex() {
		return this.scaleIndex;
	}

	/**
	 * @return 音階代號
	 * @see #scaleRoleId
	 */
	public int getScaleRoleId() {
		return scaleRoleId;
	}

	/**
	 * @return 音階音程關係 (ex: WWHWWWH => 2212221)
	 */
	public ArrayList<Integer> getScaleIntervals() {
		return scaleIntervals;
	}

	public static void main(String[] args) {
		System.out.println(new NaturalScaleRole());
		System.out.println(new NaturalScaleRole(NATURAL_MINOR));
		System.out.println(new NaturalScaleRole(MAJOR_PENTATONIC));
		System.out.println(new NaturalScaleRole(MINOR_PENTATONIC));
		System.out.println(new NaturalScaleRole(MAJOR_BLUES));
		System.out.println(new NaturalScaleRole(MINOR_BLUES));
		System.out.println(new NaturalScaleRole(HARMONIC_MINOR));
		System.out.println(new NaturalScaleRole(MELODIC_MINOR));
		System.out.println(new NaturalScaleRole(GREEK_MODE_IONIAN));
		System.out.println(new NaturalScaleRole(GREEK_MODE_DORIAN));
		System.out.println(new NaturalScaleRole(GREEK_MODE_PHRYGIAN));
		System.out.println(new NaturalScaleRole(GREEK_MODE_LYDIAN));
		System.out.println(new NaturalScaleRole(GREEK_MODE_MIXOLYDIAN));
		System.out.println(new NaturalScaleRole(GREEK_MODE_AEOLIAN));
		System.out.println(new NaturalScaleRole(GREEK_MODE_LOCRIAN));
	}

}