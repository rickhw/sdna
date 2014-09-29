package com.gtcafe.sdna.core.notation;
/**
 * 
 * @author rick
 * @see http://en.wikipedia.org/wiki/Interval_(music)
 */
public interface IntervalDeclaration {

	//term: interval quality.
	String PERFECT 		= "perfect";
	String MAJOR 		= "major";
	String MINOR 		= "minor";
	String AUGMENTED	= "augmented";
	String DIMINISHED	= "diminished";

	String ABBR_PERFECT 	= "P";
	String ABBR_MAJOR 		= "M";
	String ABBR_MINOR 		= "m";
	String ABBR_AUGMENTED	= "A";
	String ABBR_DIMINISHED	= "d";
	
	// steps are represented for natural major scale
	String STEP_1 = "1";	// P
	String STEP_2 = "2";	// M
	String STEP_3 = "3";	// M
	String STEP_4 = "4";	// P
	String STEP_5 = "5";	// P
	String STEP_6 = "6";	// M
	String STEP_7 = "7";	// M
	String STEP_8 = "8";	// P
	String STEP_9 = "9";	// M
	String STEP_10 = "10";	// M
	String STEP_11 = "11";	// P
	String STEP_12 = "12";	// P
	String STEP_13 = "13";	// M
	String STEP_14 = "14";	// M
	String STEP_15 = "15";	// P
	
	String[] NATURAL_SCALE = {
			STEP_1, "", STEP_2, "", STEP_3, STEP_4, "", STEP_5, "", STEP_6, "", STEP_7, 
			STEP_8, "", STEP_9, "", STEP_10, STEP_11, "", STEP_12, "", STEP_13, "", STEP_14,
		};

	// semitone
	int PERFECT_UNISON 	= 0;	//P1, dim2
	int MINOR_SECOND 	= 1;	//m2, aug1
	int MAJOR_SECOND 	= 2;	//M2, dim3
	int MINOR_THIRD 	= 3;	//m3, aug2
	int MAJOR_THIRD 	= 4;	//M3, dim4
	int PERFECT_FOURTH 	= 5;	//P4, aug3
	int TRITONE			= 6;	//tritone, aug4, dim5
	int PERFECT_FIFTH 	= 7;	//P5, dim6
	int MINOR_SIXTH		= 8;	//m6, aug5
	int MAJOR_SIXTH 	= 9;	//M6, dim7
	int MINOR_SEVENTH 	= 10;	//m7, aug6
	int MAJOR_SEVENTH 	= 11;	//M7, dim7
	int PERFECT_OCTAVE	= 12;	//P8, aug7

	// key in properties for display
	String PERFECT_UNISON_KEY 	= "perfect_unison";	//P1, dim2
	String MINOR_SECOND_KEY 	= "minor_second";	//m2, aug1
	String MAJOR_SECOND_KEY 	= "major_second";	//M2, dim3
	String MINOR_THIRD_KEY 		= "minor_third";	//m3, aug2
	String MAJOR_THIRD_KEY 		= "major_third";	//M3, dim4
	String PERFECT_FOURTH_KEY 	= "perfect_fourth";	//P4, aug3
	String TRITONE_KEY			= "tritone";		//tritone, aug4, dim5
	String PERFECT_FIFTH_KEY 	= "perfect_fifth";	//P5, dim6
	String MINOR_SIXTH_KEY		= "minor_sixth";	//m6, aug5
	String MAJOR_SIXTH_KEY 		= "major_sixth";	//M6, dim7
	String MINOR_SEVENTH_KEY 	= "minor_seventh";	//m7, aug6
	String MAJOR_SEVENTH_KEY 	= "major_seventh";	//M7, dim7
	String PERFECT_OCTAVE_KEY	= "perfect_octave";	//P8, aug7

	
}
