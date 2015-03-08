package com.gtcafe.sdna.core.scale;

import com.gtcafe.sdna.core.notation.NaturalNotation;

/**
 * Scale Declaration which includes name, role, step
 * 
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public interface IScaleDeclaration {

	/**
	 * 音階起音值 by MIDI Notes
	 */
	int SCALE_ROOT_0 = 0; // C

	int SCALE_ROOT_1 = 1;

	int SCALE_ROOT_2 = 2; // D

	int SCALE_ROOT_3 = 3;

	int SCALE_ROOT_4 = 4; // E

	int SCALE_ROOT_5 = 5; // F

	int SCALE_ROOT_6 = 6;

	int SCALE_ROOT_7 = 7; // G

	int SCALE_ROOT_8 = 8;

	int SCALE_ROOT_9 = 9; // A

	int SCALE_ROOT_10 = 10;

	int SCALE_ROOT_11 = 11; // B

	// Root by notation letter
	
	NaturalNotation ROOT_Bs = new NaturalNotation("B#"); // B sharp

	NaturalNotation ROOT_C = new NaturalNotation("C"); // C

	NaturalNotation ROOT_Cs = new NaturalNotation("C#"); // C sharp

	NaturalNotation ROOT_Df = new NaturalNotation("Db"); // D flat

	NaturalNotation ROOT_D = new NaturalNotation("D"); // D

	NaturalNotation ROOT_Ds = new NaturalNotation("D#"); // D sharp

	NaturalNotation ROOT_Ef = new NaturalNotation("Eb"); // E flat

	NaturalNotation ROOT_E = new NaturalNotation("E"); // E

	NaturalNotation ROOT_Ff = new NaturalNotation("Fb"); // F flat

	NaturalNotation ROOT_Es = new NaturalNotation("E#"); // E sharp

	NaturalNotation ROOT_F = new NaturalNotation("F"); // F

	NaturalNotation ROOT_Fs = new NaturalNotation("F#"); // F sharp

	NaturalNotation ROOT_Gf = new NaturalNotation("Gb"); // G flat

	NaturalNotation ROOT_G = new NaturalNotation("G"); // G

	NaturalNotation ROOT_Gs = new NaturalNotation("G#"); // G sharp

	NaturalNotation ROOT_Af = new NaturalNotation("Ab"); // A flat

	NaturalNotation ROOT_A = new NaturalNotation("A"); // A

	NaturalNotation ROOT_As = new NaturalNotation("A#"); // A sharp

	NaturalNotation ROOT_Bf = new NaturalNotation("Bb"); // B flat

	NaturalNotation ROOT_B = new NaturalNotation("B"); // B

	NaturalNotation ROOT_Cf = new NaturalNotation("Cb"); // C flat
	//

	int SCALE_ROOT_Bs = 0; // B sharp

	int SCALE_ROOT_C = 0; // C

	int SCALE_ROOT_Cs = 1; // C sharp

	int SCALE_ROOT_Df = 1; // D flat

	int SCALE_ROOT_D = 2; // D

	int SCALE_ROOT_Ds = 3; // D sharp

	int SCALE_ROOT_Ef = 3; // E flat

	int SCALE_ROOT_E = 4; // E

	int SCALE_ROOT_Ff = 4; // F flat

	int SCALE_ROOT_Es = 5; // E sharp

	int SCALE_ROOT_F = 5; // F

	int SCALE_ROOT_Fs = 6; // F sharp

	int SCALE_ROOT_Gf = 6; // G flat

	int SCALE_ROOT_G = 7; // G

	int SCALE_ROOT_Gs = 8; // G sharp

	int SCALE_ROOT_Af = 8; // A flat

	int SCALE_ROOT_A = 9; // A

	int SCALE_ROOT_As = 10; // A sharp

	int SCALE_ROOT_Bf = 10; // B flat

	int SCALE_ROOT_B = 11; // B

	int SCALE_ROOT_Cf = 11; // C flat

	///////////////////////////////////////////////////////////////////////////
	// 新增音階的定義步驟:
	// 1. Index.
	// 2. Nmae
	// 3. Interval
	// 4. List array
	///////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////////////////////////////////
	// Index of Harmonic Monir Mode 
	///////////////////////////////////////////////////////////////////////////
	/**
	 * 音階類型總數
	 */
	int SCALE_TYPE_LENGTH = 20;

	// scales index
	/**
	 * http://en.wikipedia.org/wiki/Major_scale
	 */
	int NATURAL_MAJOR = 0;

	/**
	 * http://en.wikipedia.org/wiki/Minor_scale
	 */
	int NATURAL_MINOR = 1;

	int MAJOR_PENTATONIC = 2;

	int MINOR_PENTATONIC = 3;

	int MAJOR_BLUES = 4;

	int MINOR_BLUES = 5;

	int HARMONIC_MINOR = 6;

	int MELODIC_MINOR = 7;

	///////////////////////////////////////////////////////////////////////////
	// Index of Greek modes (希臘調式)  
	///////////////////////////////////////////////////////////////////////////
	/**
	 * http://en.wikipedia.org/wiki/Ionian_mode
	 */
	int GREEK_MODE_IONIAN = 8;

	/**
	 * http://en.wikipedia.org/wiki/Dorian_mode
	 */
	int GREEK_MODE_DORIAN = 9;

	/**
	 * http://en.wikipedia.org/wiki/Phrygian_mode
	 */
	int GREEK_MODE_PHRYGIAN = 10;

	/**
	 * http://en.wikipedia.org/wiki/Lydian_mode
	 */
	int GREEK_MODE_LYDIAN = 11;

	/**
	 * http://en.wikipedia.org/wiki/Mixolydian_mode
	 */
	int GREEK_MODE_MIXOLYDIAN = 12;

	/**
	 * http://en.wikipedia.org/wiki/Aeolian_mode
	 */
	int GREEK_MODE_AEOLIAN = 13;

	/**
	 * http://en.wikipedia.org/wiki/Locrian_mode
	 */
	int GREEK_MODE_LOCRIAN = 14;

	
	///////////////////////////////////////////////////////////////////////////
	// Index of Harmonic Monir Mode 
	///////////////////////////////////////////////////////////////////////////
	/**
	 * Index of Harmonic Minor Mode 2: Locrian #6
	 */
	int HARMONIC_MODE_LOCRIAN_6TH_SHARP = 15;
	/**
	 * Index of Harmonic Minor Mode 3: Ionian #5
	 */
	int HARMONIC_MODE_IONIAN_5TH_SHARP = 16;
	/**
	 * Index of Harmonic Minor Mode 4: Dorian #4
	 */
	int HARMONIC_MODE_DORIAN_4TH_SHARP = 17; 
	/**
	 * Index of Harmonic Minor Mode 5: Phrygian Major
	 */
	int HARMONIC_MODE_PHRYGIAN_MAJOR = 18;
	/**
	 * Index of Harmonic Minor Mode 6: Lydian #2
	 */
	int HARMONIC_MODE_LYDIAN_2ND_SHARP = 19; 
	/**
	 * Index of Harmonic Minor Mode 7: Alt Dominant bb7
	 */
	int HARMONIC_MODE_ALT_DOMINANT_7TH_DOUBLE_FLAT = 20;

	///////////////////////////////////////////////////////////////////////////
	// Name of Harmonic Monir Mode 
	///////////////////////////////////////////////////////////////////////////
	/**
	 * Index of Harmonic Minor Mode 2: Locrian #6
	 */
	String HARMONIC_MODE_LOCRIAN_6TH_SHARP_NAME = "Harmonic mode 2: Locrian #6";
	/**
	 * Index of Harmonic Minor Mode 3: Ionian #5
	 */
	String HARMONIC_MODE_IONIAN_5TH_SHARP_NAME = "Harmonic mode 3: Ionian #5";
	/**
	 * Index of Harmonic Minor Mode 4: Dorian #4
	 */
	String HARMONIC_MODE_DORIAN_4TH_SHARP_NAME = "Harmonic mode 4: Dorian #4"; 
	/**
	 * Index of Harmonic Minor Mode 5: Phrygian Major
	 */
	String HARMONIC_MODE_PHRYGIAN_MAJOR_NAME = "Harmonic mode 5: Phrygian Major";
	/**
	 * Index of Harmonic Minor Mode 6: Lydian #2
	 */
	String HARMONIC_MODE_LYDIAN_2ND_SHARP_NAME = "Harmonic mode 6: Lydian #2"; 
	/**
	 * Index of Harmonic Minor Mode 7: Alt Dominant bb7
	 */
	String HARMONIC_MODE_ALT_DOMINANT_7TH_DOUBLE_FLAT_NAME = "Harmonic mode 7: Alt Dominant bb7";

	// Church modes (教會調式), 不確定 ...
	//int CHURCH_MODE_DORIAN = 61;

	//int CHURCH_MODE_HYPODORIAN = 62;

	//int CHURCH_MODE_PHRYGIAN = 63;

	//int CHURCH_MODE_HYPOPHRYGIAN = 64;

	//int CHURCH_MODE_LYDIAN = 65;

	//int CHURCH_MODE_HYPOLYDIAN = 66;

	//int CHURCH_MODE_MIXOLYDIAN = 67;

	//int CHURCH_MODE_HYPOMIXOLYDIAN = 68;

	///////////////////////////////////////////////////////////////////////////
	// Name for Scale 
	///////////////////////////////////////////////////////////////////////////
	String NATURAL_MAJOR_NAME = "自然大調 (Natural Major)";

	String NATURAL_MINOR_NAME = "自然小調 (Natural Minor)";

	String MAJOR_PENTATONIC_NAME = "大調五聲 (Major Pentatonic)";

	String MINOR_PENTATONIC_NAME = "小調五聲 (Minor Pentatonic)";

	String MAJOR_BLUES_NAME = "大調藍調 (Major Blues)";

	String MINOR_BLUES_NAME = "小調藍調 (Minor Blues)";

	String HARMONIC_MINOR_NAME = "合聲小調 (Hamonic Minor)";

	String MELODIC_MINOR_NAME = "旋律小調 (Melodic Minor)";

	///////////////////////////////////////////////////////////////////////////
	String GREEK_MODE_IONIAN_NAME = "Mode - Ionian";

	String GREEK_MODE_DORIAN_NAME = "Mode - Dorian";

	String GREEK_MODE_PHRYGIAN_NAME = "Mode - Phrygian";

	String GREEK_MODE_LYDIAN_NAME = "Mode - Lydian";

	String GREEK_MODE_MIXOLYDIAN_NAME = "Mode - Mixolydian";

	String GREEK_MODE_AEOLIAN_NAME = "Mode - Aeolian";

	String GREEK_MODE_LOCRIAN_NAME = "Mode - Locrian";
	


	///////////////////////////////////////////////////////////////////////////
	// Interval of Scale
	///////////////////////////////////////////////////////////////////////////

	int[] NATURAL_MAJOR_INTERVAL = new int[] { 2, 2, 1, 2, 2, 2, 1 };

	int[] NATURAL_MINOR_INTERVAL = new int[] { 2, 1, 2, 2, 1, 2, 2 };

	int[] MAJOR_PENTATONIC_INTERVAL = new int[] { 2, 2, 3, 2, 3 };

	int[] MINOR_PENTATONIC_INTERVAL = new int[] { 3, 2, 2, 3, 2 };

	int[] MAJOR_BLUES_INTERVAL = new int[] { 2, 1, 1, 3, 2, 3 };

	int[] MINOR_BLUES_INTERVAL = new int[] { 3, 2, 1, 1, 3, 2 };

	int[] HARMONIC_MINOR_INTERVAL = new int[] { 2, 1, 2, 2, 1, 3, 1 };

	int[] MELODIC_MINOR_INTERVAL = new int[] { 2, 1, 2, 2, 2, 2, 1 };

	///////////////////////////////////////////////////////////////////////////
	// Intervals for Greek Modes
	///////////////////////////////////////////////////////////////////////////
	int[] GREEK_MODE_IONIAN_INTERVAL = new int[] { 2, 2, 1, 2, 2, 2, 1 };

	int[] GREEK_MODE_DORIAN_INTERVAL = new int[] { 2, 1, 2, 2, 2, 1, 2 };

	int[] GREEK_MODE_PHRYGIAN_INTERVAL = new int[] { 1, 2, 2, 2, 1, 2, 2 };

	int[] GREEK_MODE_LYDIAN_INTERVAL = new int[] { 2, 2, 2, 1, 2, 2, 1 };

	int[] GREEK_MODE_MIXOLYDIAN_INTERVAL = new int[] { 2, 2, 1, 2, 2, 1, 2 };

	int[] GREEK_MODE_AEOLIAN_INTERVAL = new int[] { 2, 1, 2, 2, 1, 2, 2 };

	int[] GREEK_MODE_LOCRIAN_INTERVAL = new int[] { 1, 2, 2, 1, 2, 2, 2 };

	///////////////////////////////////////////////////////////////////////////
	// Interval of Harmonic Monir Mode 
	// @TODO, check the interval for this mode
	///////////////////////////////////////////////////////////////////////////
	/**
	 * Index of Harmonic Minor Mode 2: Locrian #6
	 */
	int[] HARMONIC_MODE_LOCRIAN_6TH_SHARP_INTERVAL = new int[] { 1, 2, 2, 1, 2, 2, 2 };
	/**
	 * Index of Harmonic Minor Mode 3: Ionian #5
	 */
	int[] HARMONIC_MODE_IONIAN_5TH_SHARP_INTERVAL = new int[] { 2, 2, 1, 2, 2, 2, 1 };
	/**
	 * Index of Harmonic Minor Mode 4: Dorian #4
	 */
	int[] HARMONIC_MODE_DORIAN_4TH_SHARP_INTERVAL = new int[] { 2, 1, 2, 2, 2, 1, 2 }; 
	/**
	 * Index of Harmonic Minor Mode 5: Phrygian Major
	 */
	int[] HARMONIC_MODE_PHRYGIAN_MAJOR_INTERVAL = new int[] { 1, 2, 2, 2, 1, 2, 2 };
	/**
	 * Index of Harmonic Minor Mode 6: Lydian #2
	 */
	int[] HARMONIC_MODE_LYDIAN_2ND_SHARP_INTERVAL = new int[] { 2, 2, 2, 1, 2, 2, 1 };
	/**
	 * Index of Harmonic Minor Mode 7: Alt Dominant bb7
	 */
	int[] HARMONIC_MODE_ALT_DOMINANT_7TH_DOUBLE_FLAT_INTERVAL = new int[] { 2, 2, 1, 2, 2, 1, 2 };

}
