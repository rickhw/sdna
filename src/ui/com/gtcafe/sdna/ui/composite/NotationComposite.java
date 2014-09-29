package com.gtcafe.sdna.ui.composite;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.gtcafe.sdna.core.notation.MidiNotation;
import com.gtcafe.sdna.core.notation.NaturalNotation;
import com.gtcafe.sdna.core.scale.IScaleTable;
import com.gtcafe.sdna.core.scale.MidiScale;
import com.gtcafe.swt.BorderLayout;

public class NotationComposite extends Composite implements SelectionListener {

	private static final long serialVersionUID = 7040366663250041519L;

	private Button naturalNoteRadio, midiNoteRadio;
	private Composite optionPanel, statusPanel;
	private Combo noteNameCombo, noteLevelCombo;
	private Table infoTable;
	private Label statusLabel;

	// ----
	private Button noteType;
	private NaturalNotation nn;
	private int noteLevel = 4;

	public NotationComposite(Composite parent) {
		super(parent, SWT.NULL);

		setLayout(new BorderLayout());

		createOptionComposite();

		createTable();

		createStatusBar();

		updateScaleTable();
	}

	private void createOptionComposite() {
		optionPanel = new Composite(this, SWT.NULL);
		// optionPanel.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TITLE_BACKGROUND));
		optionPanel.setLayoutData(new BorderLayout.BorderData(BorderLayout.EAST));
		optionPanel.setLayout(new GridLayout(1, false));

		///////////////////////////////////////////////////////////////////////
		Group noteTypeGroup = new Group(optionPanel, SWT.SHADOW_IN);
		noteTypeGroup.setLayout(new RowLayout(SWT.VERTICAL));
		noteTypeGroup.setText("Notation Type");
		
		naturalNoteRadio = new Button(noteTypeGroup, SWT.RADIO);
		naturalNoteRadio.setText("Natural Note");
		naturalNoteRadio.setToolTipText("一個八度音以內的音符。");
		naturalNoteRadio.addSelectionListener(this);

		midiNoteRadio = new Button(noteTypeGroup, SWT.RADIO);
		midiNoteRadio.setText("MIDI Note");
		midiNoteRadio.setToolTipText("全音域範圍。");
		midiNoteRadio.addSelectionListener(this);

		///////////////////////////////////////////////////////////////////////
		Group noteSettingGroup = new Group(optionPanel, SWT.NULL);
		noteSettingGroup.setLayout(new GridLayout(2, false));
		noteSettingGroup.setText("Notation Setting");

		new Label(noteSettingGroup, SWT.NULL).setText("Name：");
		noteNameCombo = new Combo(noteSettingGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		for(int i=0; i<IScaleTable.ROOTS_HALF_STEP_MODE.length; i++) 
			noteNameCombo.add(IScaleTable.ROOTS_HALF_STEP_MODE[i].natural());
		noteNameCombo.select(0);
		noteNameCombo.addSelectionListener(this);

		new Label(noteSettingGroup, SWT.NULL).setText("Level：");
		noteLevelCombo = new Combo(noteSettingGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		for(int i=-1; i<=9; i++) {
			noteLevelCombo.add(new Integer(i).toString());
		}
		noteLevelCombo.select(5);
		noteLevelCombo.addSelectionListener(this);

		
//		new Label(optionPanel, SWT.NULL).setText("升降記號：");
//		symbolCombo = createCombo(symbolLabels);
//		symbolCombo.setToolTipText("Default is by 5th cycle.");
//
//		new Label(optionPanel, SWT.NULL).setText("記號位置：");
//		letterSymbolLocCombo = createCombo(symbolLocLabels);

		// new Label(optionPanel, SWT.NULL).setText("簡譜位置：");
		// numberSymbolLocCombo = createCombo(symbolLocLabels, 1);

	}

	private void createTable() {
		infoTable = new Table(this, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		infoTable.setLayoutData(new BorderLayout.BorderData(BorderLayout.CENTER));
		// scaleTable.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TITLE_BACKGROUND));
		infoTable.setHeaderVisible(true);
		//infoTable.addSelectionListener(this);

		TableColumn keyCol = new TableColumn(infoTable, SWT.CENTER);
		keyCol.setText("key");
		keyCol.setWidth(200);

		TableColumn valueCol = new TableColumn(infoTable, SWT.CENTER);
		valueCol.setText("value");
		valueCol.setWidth(300);

	}

	private void createStatusBar() {
		statusLabel = new Label(this, SWT.BORDER);
		statusLabel.setLayoutData(new BorderLayout.BorderData(BorderLayout.SOUTH));
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if(e.getSource() == naturalNoteRadio) {
			noteLevelCombo.setEnabled(!naturalNoteRadio.getSelection());
		} else if(e.getSource() == midiNoteRadio) {
			noteLevelCombo.setEnabled(midiNoteRadio.getSelection());
		}
		
		noteType = naturalNoteRadio;
		
		nn = IScaleTable.ROOTS_HALF_STEP_MODE[noteNameCombo.getSelectionIndex()];
		//noteLevel
		if(noteLevelCombo.isEnabled()) {
			noteLevel = Integer.parseInt(noteLevelCombo.getItem(noteLevelCombo.getSelectionIndex()));
			noteType = midiNoteRadio;
		}
		
		updateScaleTable();
	}

	private void updateStatusBar(TableItem[] items) {
		if (items == null || items.length <= 0) {
			statusLabel.setText("");
		} else if (items != null && items.length > 0) {
			TableItem item = items[0];
			MidiScale scale = (MidiScale) item.getData();
			statusLabel.setText(String.format(
					"根音：%s  音階名稱：%s  音程關係：%s  升降記號：%s", scale.getRootNotation()
							.natural(), scale.getNaturalScaleRole()
							.getRoleName(), scale.getNaturalScaleRole()
							.getScaleNumbers(), scale.getSymbols()));
		}
	}

	private void updateScaleTable() {
		infoTable.removeAll();
		if(noteType == naturalNoteRadio) {
			TableItem item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "natural");
			item.setText(1, nn.natural());
			
			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "expression");
			item.setText(1, nn.expression());

			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "NaturalIndex");
			item.setText(1, ""+nn.getNaturalIndex());

			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "NaturalLetter");
			item.setText(1, nn.getNaturalLetter());
			
			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "SharpLetter");
			item.setText(1, nn.getSharpLetter());

			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "FlatLetter");
			item.setText(1, nn.getFlatLetter());

			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "NaturalNumber");
			item.setText(1, nn.getNaturalNumber());
			
			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "SharpNumber");
			item.setText(1, nn.getSharpNumber());

			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "FlatNumber");
			item.setText(1, nn.getFlatNumber());



		} else if(noteType == midiNoteRadio) {
			MidiNotation mn = new MidiNotation(nn.natural(), noteLevel);
			
			TableItem item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "MidiLetter");
			item.setText(1, mn.getMidiLetter());

			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "MidiIndex");
			item.setText(1, ""+mn.getMidiIndex());

			item = new TableItem(infoTable, SWT.NULL);
			item.setText(0, "Freq");
			item.setText(1, String.format("%.2f", mn.getFreq()));

			
		}

	}


}
