package com.gtcafe.sdna.core.scale;

import java.util.ArrayList;
import java.util.List;

import com.gtcafe.sdna.core.notation.NaturalNotation;

/**
 * Natural Scale Comparation
 * 音階比對: 輸入適當的音符比對符合比率.
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 * @TODO
 */
public class NaturalScaleComparation {
	NaturalScaleRole role; // 對照音群

	// NaturalNotation[] nns; // 辨識的音群
	List<NaturalNotation> nns;

	int den; // is role size

	int insp; // 比對音符加權直

	// ***********************************
	// 比例 = den/insp
	// ratio > 1.0 表示完全符合該對照音階
	// ratio < 1.0 表示不完原符合對照音階, 可能有 outside 音符
	double ratio;

	// 不屬於對照音群的音符
	List<NaturalNotation> outsideNotation;

	// 屬於對照音群的音符
	List<NaturalNotation> insideNotation;

	public NaturalScaleComparation(NaturalScaleRole role, List<NaturalNotation> nns) {

		// initiate the members
		this.role = role;
		this.nns = nns;

		this.den = role.getScaleNotations().size();
		this.insp = 0;
		this.ratio = 0.0;
		this.outsideNotation = new ArrayList<NaturalNotation>();
		this.insideNotation = new ArrayList<NaturalNotation>();

		compare();
	}

	private void compare() {

		for (int i = 0; i < this.nns.size(); i++) {
			NaturalNotation comparedNote = this.nns.get(i);
			boolean fnd = false;
			for (int j = 0; j < den; j++) {
				NaturalNotation roleNote = role.getScaleNotations().get(j);
				// 找到了
				if (comparedNote.equals(roleNote)) {
					insp++;
					insideNotation.add(comparedNote);
					fnd = true;
					break;
				}
			}

			// 沒找到
			if (!fnd)
				outsideNotation.add(comparedNote);
		}

		this.ratio = (double)(insp - outsideNotation.size())/ (double) den;

	}

	public String toString() {
		return "role (對照音階): " + role + 
		"\n  den (對照音階數): " + den + 
		"\n  nns (比對音符): " + nns + 
		"\n  insp (符合音符數): " + insp + 
		"\n  ratio (符合比率): " + ratio +
		"\n  inside: " + insideNotation +
		"\n  ouside: " + outsideNotation;
	}

	public static void main(String[] args) {
		ArrayList<NaturalNotation> nns = new ArrayList<NaturalNotation>();
		nns.add(new NaturalNotation("C"));
		nns.add(new NaturalNotation("E"));
		nns.add(new NaturalNotation("G"));
		nns.add(new NaturalNotation("G#"));

		NaturalScaleComparation nsc = new NaturalScaleComparation(new NaturalScaleRole(), nns);
		System.out.println(nsc.toString());

	}
}
