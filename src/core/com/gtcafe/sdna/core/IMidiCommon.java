package com.gtcafe.sdna.core;

public interface IMidiCommon {
	// 全音
	int FULL_NOTE = 2;

	// 半音
	int HALF_NOTE = 1;

	// midi index range: 0 ~ 127
	int MIDI_INDEX_START = 0;
	int MIDI_INDEX_END = 127;
	
	// natural index range: 0 ~ 11
	int NATURAL_INDEX_START = 0;
	int NATURAL_INDEX_END = 11;

	// natural number range: 1 ~ 7
	int NATURAL_NUMBER_START = 1;
	int NATURAL_NUMBER_END = 7;
	
	// octaves range for the midi notes: -1 ~ 9
	int SCALE_OCTAVE_START = -1;
	int SCALE_OCTAVE_END = 9;

	int SCALE_CYCLE = 12;

}
