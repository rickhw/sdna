package com.gtcafe.sdna.core.scale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gtcafe.sdna.core.notation.MIDINotation;
import com.gtcafe.sdna.core.notation.NaturalNotation;

/**
 * 調的音階
 * 
 * @input (required): 
 *   @param rootNotation
 *   @param scaleRole
 * @optional
 *   @param useSharp
 *   @param isMinorSystem() 
 * 
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public class MIDIScale implements IScaleTable {
	//private boolean debug = true;
	/**
	 * by 5th cycle
	 */
	public static final int DEFAULT_SYMBOL = 0;
	public static final int SHARP_SYMBOL = 1;
	public static final int FLAT_SYMBOL = 2;
	
	
	// ========================================================================
	// Class properties
	// ========================================================================
	/**
	 * Key Notation
	 */
	private NaturalNotation rootNotation;
	
	/**
	 * The scale role (the interval for scale)
	 */
	private NaturalScaleRole naturalScaleRole;

	private int symbolMode = DEFAULT_SYMBOL;
	
	private boolean useSharp = false;
	
	private boolean letterSymbolRight = true;
	private boolean numberSymbolRight = false;
	
	private boolean isMinorSystem = false;
	
	// ========================================================================
	// Collection
	// ========================================================================
	
	/**
	 * Key Symbols (升降記號)
	 */
	private List<String> symbols;

	/**
	 * Map for scale mapping of midiIndex and midiNotation
	 */
	private Map<Integer, MIDINotation> midiScaleMap;

	/**
	 * Natural scale for Key
	 */
	private Map<Integer, NaturalNotation> naturalScaleMap;
	

	// ========================================================================
	// constructors
	// ========================================================================

	/**
	 * Create the Scale by root in SCALE_ROOT_C, and scale root with
	 * NATURAL_MAJOR.
	 */
	public MIDIScale() {
		this(ROOT_C, NATURAL_MAJOR);
	}

	/**
	 * 
	 * @param scaleId
	 * @see IScaleTable#SCALE_INTERVALS
	 * @see IScaleDeclaration#scale_index
	 */
	public MIDIScale(int scaleId) {
		this(ROOT_C, scaleId);
	}

	public MIDIScale(NaturalNotation rootNote, int scaleIntervalId) {
		this.naturalScaleRole = new NaturalScaleRole(scaleIntervalId);
		this.midiScaleMap = new TreeMap<Integer, MIDINotation>();
		this.naturalScaleMap = new HashMap<Integer, NaturalNotation>();
		this.symbols = new ArrayList<String>();
		this.rootNotation = rootNote;
		
		initScale();
	}

	// ========================================================================
	// private methods
	// ========================================================================

	/**
	 * create the scale information
	 */
	private void initScale() {
		initMidiScaleMap();
		initNaturalScaleMap();
		setSymbolMode(DEFAULT_SYMBOL);
	}

	public void setSymbolMode(int mode) {
		int key = rootNotation.getNaturalIndex();
		this.symbolMode = mode;
		if(symbolMode == DEFAULT_SYMBOL) {
			//***************************************************
			// represent the key notes by sharp (according to 5th circle)
			if (key == SCALE_ROOT_G || key == SCALE_ROOT_D || 
					key == SCALE_ROOT_A || key == SCALE_ROOT_E || 
					key == SCALE_ROOT_B || key == SCALE_ROOT_Fs) {
				rootNotation.setUseSharp(true);
				setUseSharp(true);
			} else {
				rootNotation.setUseSharp(false);
				setUseSharp(false);
			}
		} else {
			if(symbolMode == SHARP_SYMBOL) {
				rootNotation.setUseSharp(true);
				setUseSharp(true);
			} else {
				rootNotation.setUseSharp(false);
				setUseSharp(false);
			}
		}
	}

	/**
	 * collect the symbols notation for key
	 */
	private void collectSymbols() {
		this.symbols.clear();
		Iterator<Integer> iterator = naturalScaleMap.keySet().iterator();
		while(iterator.hasNext()) {
			int ntIdx = iterator.next();
			String nt = naturalScaleMap.get(ntIdx).natural();
			if( (nt.indexOf("#") > -1 || nt.indexOf("b") > -1)) {
				this.symbols.add(nt);
			}
		}
		
	}

	private void initMidiScaleMap() {
		int key = rootNotation.getNaturalIndex();
		
		//***************************************************
		// change the order for interval, since the key root will be shifted by user.
		// below octave the steps.
		// *[algorithm]* 
		// 1. find the index when over the scale cycle
		int val = key;
		int idx = 0;
		int startPos = 0;
		List<Integer> oldInterval = naturalScaleRole.getScaleIntervals();

		for(int i = 0; i<oldInterval.size(); i++) {
			int s = oldInterval.get(i);
			val += s;
			if(val > SCALE_CYCLE) {
				idx = i; 
				startPos = val - s - SCALE_CYCLE;
				break;
			}
		}
		
		// 2. re-create the List for new steps, and calculate the start position
		List<Integer> newInterval = new ArrayList<Integer>();
		for(int i=idx; i<oldInterval.size(); i++) 
			newInterval.add(oldInterval.get(i));
		for(int i=0; i<idx; i++)
			newInterval.add(oldInterval.get(i));
		
		///////////////////////////////////////////////////////////////////////
		// create the midiScaleMap.
		// the interval with newInterval, and start with startPos
		///////////////////////////////////////////////////////////////////////
		for (int i = startPos; i < MIDI_INDEX_END; i += SCALE_CYCLE) {
			int stepMidiIndex = i;
			for (Iterator<Integer> iter = newInterval.iterator(); iter.hasNext();) {
				Integer interval = (Integer) iter.next();
				if (stepMidiIndex >= 0 && stepMidiIndex <= MIDI_INDEX_END) {
					midiScaleMap.put(stepMidiIndex, new MIDINotation(stepMidiIndex));
				}
				stepMidiIndex += interval;
			}
		}
	}


	// create the map for midiIndex and midiNotations
	private void initNaturalScaleMap() {
		naturalScaleMap.clear();
		int level = 1;
		boolean findFirstNote = false;
		
		Iterator<Integer> iterator = midiScaleMap.keySet().iterator();
		while(iterator.hasNext()) {
			int midiIndex = iterator.next();
			MIDINotation midiNote = midiScaleMap.get(midiIndex);
			NaturalNotation nn = midiNote.getNatural();
			int idx = nn.getNaturalIndex();
			
			if(idx == rootNotation.getNaturalIndex()) {
				findFirstNote = true;
			}
			
			if(findFirstNote) {
				naturalScaleMap.put(level++, nn);
				if(level > naturalScaleRole.getScaleNotations().size())
					break;
			}
		}
		
		//return null;
	}

	// ========================================================================
	// public/operation methods
	// ========================================================================

	/**
	 * The scale is minor system. detection by the 3rd note is b3. 
	 */
	public boolean isMinorSystem() {
		String t3rd = naturalScaleRole.getScaleNumbers().get(2);
		if("3".equals(t3rd))
			return false;
		else
			return true;
	}

	public void setUseSharp(boolean useSharp) {
		this.useSharp = useSharp;
		Iterator<Integer> iterator = midiScaleMap.keySet().iterator();
		while(iterator.hasNext()) {
			int midiIndex = iterator.next();
			MIDINotation midiNote = midiScaleMap.get(midiIndex);
			NaturalNotation nn = midiNote.getNatural();
			nn.setUseSharp(useSharp);
		}
		
		collectSymbols();
	}
	
	/**
	 * 高低八度
	 * 
	 * @param octave
	 */
	//public void octave(int octave) {

	//}

	/**
	 * 轉調
	 * 
	 * @param shift
	 *            半音
	 */
	//public void transport(int shift) {

	//}

	// ========================================================================
	// Override
	// ========================================================================

	public String toString() {
		return "Key: " + this.rootNotation + " (" + this.symbols + ")\n"
		+ "  naturalNotes: " + this.naturalScaleMap + "\n"
		+ "  midi scale map: " + this.midiScaleMap + "\n"
		+ "  midiIndex: " + this.midiScaleMap.keySet() + "\n"
		+ "  isMinor: " + this.isMinorSystem() + "\n"
		+ "\n";
	}


	/**
	 * Unit Test.
	 */
	public static void main(String[] args) {
		//System.out.println(new Scale());
		// System.out.println(new Scale(MINOR_BLUES));
		//System.out.println(new Scale(SCALE_ROOT_A, NATURAL_MAJOR));
		//for(int i=0; i<12; i++)
		//System.out.println(new Scale(ROOT_As, NATURAL_MAJOR));
		System.out.println(new MIDIScale(ROOT_D, NATURAL_MAJOR));
		System.out.println(new MIDIScale(ROOT_D, HARMONIC_MINOR));
		//Scale s = new Scale(ROOT_A, NATURAL_MAJOR);
		//System.out.println(s.midiNumberInfo());
		//System.out.println(s.notationsInfo());
		
	}
	
	// ========================================================================
	// getter/setter
	// ========================================================================

	public NaturalNotation getRootNotation() {
		return rootNotation;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	public NaturalScaleRole getNaturalScaleRole() {
		return naturalScaleRole;
	}
	
	public boolean isUseSharp() {
		return useSharp;
	}

	public Map<Integer, NaturalNotation> getNaturalScaleMap() {
		return naturalScaleMap;
	}

	public Map<Integer, MIDINotation> getMidiScaleMap() {
		return midiScaleMap;
	}

	public void setLetterSymbolLocation(boolean isRightSide) {
		this.letterSymbolRight = isRightSide;
		Iterator<Integer> iterator = this.midiScaleMap.keySet().iterator();
		while(iterator.hasNext()) {
			int midiIdx = iterator.next();
			MIDINotation mn = midiScaleMap.get(midiIdx);
			mn.getNatural().setLetterSymbolLocation(isRightSide);
		}
	}

	public void setNumberSymbolLocation(boolean isRightSide) {
		this.numberSymbolRight = isRightSide;
		Iterator<Integer> iterator = this.midiScaleMap.keySet().iterator();
		while(iterator.hasNext()) {
			int midiIdx = iterator.next();
			MIDINotation mn = midiScaleMap.get(midiIdx);
			mn.getNatural().setNumberSymbolLocation(isRightSide);
		}
	}
	

}
