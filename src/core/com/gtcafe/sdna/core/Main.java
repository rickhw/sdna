package com.gtcafe.sdna.core;

public class Main {

	// 全音
	public static final int FULL_NOTE = 2;

	// 半音
	public static final int HALF_NOTE = 1;

	// 中央 C
	public static final int NOTES_START = 0;

	public static final int NOTES_END = 127;

	public static final int SCALE_BEGIN_LEVEL = -1;

	public static final int SCALE_CYCLE = 12;

	public static void main(String[] args) {
		getLevel(-30);
		getLevel(0);
		getLevel(5);
		getLevel(50);
		getLevel(118);
		getLevel(127);
		getLevel(128);

	}

	public static int getLevel(int note) {
		if (note < NOTES_START || note > NOTES_END) {
			System.out.println("Out of range.");
			return -2;
		}

		int level = note / SCALE_CYCLE - 1;
		System.out.println("Level: " + level);
		return level;
	}

	public static int getNoteOrder(int note) {
		if (note < NOTES_START || note > NOTES_END) {
			System.out.println("Out of range.");
			return -2;
		}

		return (note % SCALE_CYCLE);
	}


}
