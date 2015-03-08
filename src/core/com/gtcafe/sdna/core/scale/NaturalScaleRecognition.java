package com.gtcafe.sdna.core.scale;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 * @TODO
 */
public class NaturalScaleRecognition {

	public static ArrayList<NaturalScaleRole> roles = new ArrayList<NaturalScaleRole>(IScaleDeclaration.SCALE_TYPE_LENGTH);

	static {
		for (int i = 0; i < IScaleDeclaration.SCALE_TYPE_LENGTH; i++)
			roles.add(new NaturalScaleRole(i));
	}

	/**
	 * 用音群 (Natural Notes, 一個八度) 辨識音階/調式類型
	 * 
	 * @param naturalNumbers
	 *            音群數字 0~11
	 * @return scaleId 音階名稱, 可能會找到多個可能的音階
	 * @see IScaleDeclaration
	 * @TODO
	 */
	public NaturalScaleRecognition(int[] naturalNumbers) {

		// return new int[0];
	}

	/**
	 * 用音群 (一個八度) 辨識音階/調式類型
	 * 
	 * @param naturalNames
	 *            音群名稱 C ~ B
	 * @return scaleId 可能的音階名稱
	 * @TODO
	 */
	public NaturalScaleRecognition(String[] naturalNames) {
		// return new int[0];
		boolean result = false;

		int denVal = 0; // 分母加權
		int inspVal = 0; // 檢查加權職

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] notes = new String[] { "C", "E", "G" };
		// it might be major natural, minor natural, major pentatonic, or minor
		// pent. ..

		NaturalScaleRecognition nsr = new NaturalScaleRecognition(notes);

	}

	private class RecognitionScale implements Runnable {
		NaturalScaleRole role;

		List<Integer> nums;

		public RecognitionScale() {

		}

		public void run() {

		}

	}
}
