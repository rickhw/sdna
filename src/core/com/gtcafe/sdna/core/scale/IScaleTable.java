package com.gtcafe.sdna.core.scale;

import com.gtcafe.sdna.core.IMIDICommon;
import com.gtcafe.sdna.core.IMIDINotes;
import com.gtcafe.sdna.core.notation.NaturalNotation;

/**
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public interface IScaleTable extends IScaleDeclaration, IMIDICommon, IMIDINotes {

	/**
	 * 所有音階名稱
	 */
	String[] SCALE_NAMES = { 		// 音階名稱
			NATURAL_MAJOR_NAME, 	// 0
			NATURAL_MINOR_NAME, 	// 1
			MAJOR_PENTATONIC_NAME, 	// 2
			MINOR_PENTATONIC_NAME, 	// 3
			MAJOR_BLUES_NAME, 		// 4
			MINOR_BLUES_NAME,		// 5
			HARMONIC_MINOR_NAME, 			// 6
			MELODIC_MINOR_NAME, 			// 7
			// 調式
			GREEK_MODE_IONIAN_NAME, // 8
			GREEK_MODE_DORIAN_NAME, // 9
			GREEK_MODE_PHRYGIAN_NAME, // 10
			GREEK_MODE_LYDIAN_NAME, // 11
			GREEK_MODE_MIXOLYDIAN_NAME, // 12
			GREEK_MODE_AEOLIAN_NAME, // 13
			GREEK_MODE_LOCRIAN_NAME, // 14
			// 和聲小調調式
			HARMONIC_MODE_LOCRIAN_6TH_SHARP_NAME,
			HARMONIC_MODE_IONIAN_5TH_SHARP_NAME,
			HARMONIC_MODE_DORIAN_4TH_SHARP_NAME, 
			HARMONIC_MODE_PHRYGIAN_MAJOR_NAME,
			HARMONIC_MODE_LYDIAN_2ND_SHARP_NAME, 
			HARMONIC_MODE_ALT_DOMINANT_7TH_DOUBLE_FLAT_NAME,
	};

	/**
	 * 所有的音階音程關係
	 */
	int[][] SCALE_INTERVALS = { // 音程關係
			NATURAL_MAJOR_INTERVAL, // 0
			NATURAL_MINOR_INTERVAL, // 1
			MAJOR_PENTATONIC_INTERVAL, // 2
			MINOR_PENTATONIC_INTERVAL, // 3
			MAJOR_BLUES_INTERVAL, // 4
			MINOR_BLUES_INTERVAL, // 5
			HARMONIC_MINOR_INTERVAL, // 6
			MELODIC_MINOR_INTERVAL, // 7
			// 調式
			GREEK_MODE_IONIAN_INTERVAL, // 8
			GREEK_MODE_DORIAN_INTERVAL, // 9
			GREEK_MODE_PHRYGIAN_INTERVAL, // 10
			GREEK_MODE_LYDIAN_INTERVAL, // 11
			GREEK_MODE_MIXOLYDIAN_INTERVAL, // 12
			GREEK_MODE_AEOLIAN_INTERVAL,// 13
			GREEK_MODE_LOCRIAN_INTERVAL, // 14
			// 和聲小調調式
			HARMONIC_MODE_LOCRIAN_6TH_SHARP_INTERVAL,
			HARMONIC_MODE_IONIAN_5TH_SHARP_INTERVAL,
			HARMONIC_MODE_DORIAN_4TH_SHARP_INTERVAL, 
			HARMONIC_MODE_PHRYGIAN_MAJOR_INTERVAL,
			HARMONIC_MODE_LYDIAN_2ND_SHARP_INTERVAL, 
			HARMONIC_MODE_ALT_DOMINANT_7TH_DOUBLE_FLAT_INTERVAL,
	};
	
	

	///////////////////////////////////////////////////////////////////////////
	// 根音排列表
	///////////////////////////////////////////////////////////////////////////
	/**
	 * 自然大音階排列
	 */
	NaturalNotation[] ROOTS_NATURAL_MODE = { ROOT_C, ROOT_D, ROOT_E, ROOT_F, ROOT_G, ROOT_A, ROOT_B };
	/**
	 * 五度圈排列
	 */
	NaturalNotation[] ROOTS_5TH_CYCLE_MODE = { ROOT_Df, ROOT_Af, ROOT_Ef, ROOT_Bf, ROOT_F, ROOT_C, ROOT_G, ROOT_D, ROOT_A, ROOT_E, ROOT_B, ROOT_Fs };
	/**
	 * 半音階排列
	 */
	NaturalNotation[] ROOTS_HALF_STEP_MODE = { ROOT_C, ROOT_Cs, ROOT_D, ROOT_Ds, ROOT_E, ROOT_F, ROOT_Fs, ROOT_G, ROOT_Gs, ROOT_A, ROOT_As, ROOT_B };
	
	String ROOTS_NATURAL_MODE_NAME = "自然音階";
	String ROOTS_5TH_CYCLE_MODE_NAME = "五度圈";
	String ROOTS_HALF_STEP_MODE_NAME = "半音模式";
	
	String[] ROOT_MODE_LIST_NAMES = {ROOTS_NATURAL_MODE_NAME, ROOTS_5TH_CYCLE_MODE_NAME, ROOTS_HALF_STEP_MODE_NAME};
	NaturalNotation[][] ROOTS_MODE_LIST = {ROOTS_NATURAL_MODE, ROOTS_5TH_CYCLE_MODE, ROOTS_HALF_STEP_MODE};
	

	///////////////////////////////////////////////////////////////////////////
	// 音階分類
	///////////////////////////////////////////////////////////////////////////
	String DIATONIC_SCALE_NAME = "調性系統 (Diatonic)";
	String PENTATONIC_SCALE_NAME = "五聲系統 (Pentatonic)";
	String MODE_SCALE_NAME = "調式 (Mode)";
	/**
	 * 和絃和對稱系統
	 */
	String CHORD_AND_SYMMETRICAL_SCALE_NAME = "Chord & Symmeterical";
	/**
	 * 其他和民俗音階
	 */
	String OTHER_AND_ETHNIC_NAME = "Other & Ethnic";

	// INDEX
	int[] DIATONIC_SCALE_INDEX_LIST = {	
			NATURAL_MAJOR, // 0
			NATURAL_MINOR, // 1
			HARMONIC_MINOR, // 6
			MELODIC_MINOR, // 7
	};

	int[] PENTATONIC_SCALE_INDEX_LIST = {
			MAJOR_PENTATONIC, // 2
			MINOR_PENTATONIC, // 3
			MAJOR_BLUES, // 4
			MINOR_BLUES, // 5
	};
	
	int[] MODE_SCALE_INDEX_LIST = {
			GREEK_MODE_IONIAN, // 8
			GREEK_MODE_DORIAN, // 9
			GREEK_MODE_PHRYGIAN, // 10
			GREEK_MODE_LYDIAN, // 11
			GREEK_MODE_MIXOLYDIAN, // 12
			GREEK_MODE_AEOLIAN,// 13
			GREEK_MODE_LOCRIAN,// 14
			// 和聲小調調式
			HARMONIC_MODE_LOCRIAN_6TH_SHARP,
			HARMONIC_MODE_IONIAN_5TH_SHARP,
			HARMONIC_MODE_DORIAN_4TH_SHARP, 
			HARMONIC_MODE_PHRYGIAN_MAJOR,
			HARMONIC_MODE_LYDIAN_2ND_SHARP, 
			HARMONIC_MODE_ALT_DOMINANT_7TH_DOUBLE_FLAT,
	};
	
	int[] CHORD_AND_SYMMETRICAL_SCALE_INDEX_LIST = {};
	int[] OTHER_AND_ETHNIC_INDEX_LIST = {};

	String[] SCALE_CATEGORY_NAME = { 		// 音階分類
			DIATONIC_SCALE_NAME,
			PENTATONIC_SCALE_NAME,
			MODE_SCALE_NAME,
			CHORD_AND_SYMMETRICAL_SCALE_NAME,
			OTHER_AND_ETHNIC_NAME
	};
	
	int[][] SCALE_CATEGORY_LIST = {
			DIATONIC_SCALE_INDEX_LIST,
			PENTATONIC_SCALE_INDEX_LIST,
			MODE_SCALE_INDEX_LIST,
			CHORD_AND_SYMMETRICAL_SCALE_INDEX_LIST,
			OTHER_AND_ETHNIC_INDEX_LIST
	};

}
