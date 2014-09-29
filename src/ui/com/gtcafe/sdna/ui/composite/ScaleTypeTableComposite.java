package com.gtcafe.sdna.ui.composite;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.gtcafe.sdna.core.notation.NaturalNotation;
import com.gtcafe.sdna.core.scale.IScaleTable;
import com.gtcafe.sdna.core.scale.MidiScale;
import com.gtcafe.swt.BorderLayout;


public class ScaleTypeTableComposite extends Composite implements SelectionListener {

	private static final long serialVersionUID = 7040366663250041519L;

	private Combo rootNotesCombo, scaleCategoryCombo, symbolCombo, letterSymbolLocCombo, numberSymbolLocCombo;
	
	private Table scaleTable;
	private Composite optionPanel, statusPanel;
	private Label rootModeLabel, scaleRoleLabel, symbolModeLabel;
	private Label statusLabel;
	private String[] symbolLabels = {"Default", "Sharp (#)", "Flat (b)"};
	private String[] symbolLocLabels = {"Right (A#)", "Left(#A)"};

	// ----
	private int rootNoteIdx = 0;
	private int scaleCategoryId = 0;
	private int symbolMode = 0; //0, 1, 2
	private boolean letterSymbolRightSide = true; 
	private boolean numberSymbolRightSide = false;

	public ScaleTypeTableComposite(Composite parent) {
		super(parent, SWT.NULL);

		setLayout(new BorderLayout());

		createOptionComposite();
		createTable();
		createStatusBar();

		updateScaleTable();
	}

	private void createOptionComposite() {
		optionPanel = new Composite(this, SWT.NULL);
		//optionPanel.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TITLE_BACKGROUND));
		optionPanel.setLayoutData(new BorderLayout.BorderData(BorderLayout.EAST));
		optionPanel.setLayout(new GridLayout(2, false));

		new Label(optionPanel, SWT.NULL).setText("根音：");
		rootNotesCombo = new Combo(optionPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
		for(int i=0; i<IScaleTable.ROOTS_HALF_STEP_MODE.length; i++) 
			rootNotesCombo.add(IScaleTable.ROOTS_HALF_STEP_MODE[i].natural());
		rootNotesCombo.select(0);
		rootNotesCombo.addSelectionListener(this);

		new Label(optionPanel, SWT.NULL).setText("音階類別：");
		scaleCategoryCombo = createCombo(IScaleTable.SCALE_CATEGORY_NAME);

		//TODO, 小調用降記號
		new Label(optionPanel, SWT.NULL).setText("升降記號：");
		symbolCombo = createCombo(symbolLabels);
		symbolCombo.setToolTipText("Default is by 5th cycle.");

		new Label(optionPanel, SWT.NULL).setText("記號位置：");
		letterSymbolLocCombo = createCombo(symbolLocLabels);

		//new Label(optionPanel, SWT.NULL).setText("簡譜位置：");
		//numberSymbolLocCombo = createCombo(symbolLocLabels, 1);
		
	}

	private Combo createCombo(String[] items) {
		return createCombo(items, 0);
	}
	
	private Combo createCombo(String[] items, int select) {
		Combo combo = new Combo(optionPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setItems(items);
		combo.select(select);
		combo.addSelectionListener(this);
		return combo;
	}

	private void createTable() {
		scaleTable = new Table(this, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		scaleTable.setLayoutData(new BorderLayout.BorderData(BorderLayout.CENTER));
		scaleTable.setHeaderVisible(true);
		scaleTable.removeAll();
		scaleTable.addSelectionListener(this);
		
		TableColumn roleName = new TableColumn(scaleTable, SWT.CENTER);
		roleName.setText("音程名稱");
		roleName.setWidth(200);
		
		for (int i = 1; i <= 8; i++) {
			TableColumn col = new TableColumn(scaleTable, SWT.CENTER);
			col.setText(""+i);
			col.setWidth(50);
		}
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
		if(e.getSource() == rootNotesCombo) {
			rootNoteIdx = rootNotesCombo.getSelectionIndex();
			updateScaleTable();
		} else if (e.getSource() == scaleCategoryCombo) {
			scaleCategoryId = scaleCategoryCombo.getSelectionIndex();
			
			//System.out.println(String.format("scaleRoleId:[%d], [%s]", scaleRoleId, IScaleTable.SCALE_NAMES[scaleRoleId]));
			updateScaleTable();
		} 
		else if (e.getSource() == symbolCombo) {
			TableItem[] items = scaleTable.getSelection();
			this.symbolMode = symbolCombo.getSelectionIndex();
			// update all
			if(items == null || items.length <= 0) {
				updateScaleTable();
			} else if(items != null && items.length >0) {
				TableItem item = items[0];
				MidiScale scale = (MidiScale)item.getData();
				scale.setSymbolMode(symbolMode);
				String[] data = createScaleNoteArray(scale);
				item.setText(data);
				updateStatusBar(items);
			}
			
			//System.out.println(String.format("rootNote:[%d], [%s]", rootNoteIdx, rootNote.natural()));
		} else if(e.getSource() == scaleTable) {
			TableItem[] items = scaleTable.getSelection();
			if(items != null && items.length >0) {
				updateStatusBar(items);
			}
		} else if(e.getSource() == letterSymbolLocCombo) {
			TableItem[] items = scaleTable.getSelection();
			letterSymbolRightSide = letterSymbolLocCombo.getSelectionIndex() == 0 ? true : false;
			if(items == null || items.length <= 0) {
				updateScaleTable();
			} else if(items != null && items.length >0) {
				MidiScale scale = (MidiScale)items[0].getData();
				scale.setLetterSymbolLocation(letterSymbolRightSide);
				String[] data = createScaleNoteArray(scale);
				items[0].setText(data);
				updateStatusBar(items);
			}
		}
		/*
		else if(e.getSource() == numberSymbolLocCombo) {
			TableItem[] items = scaleTable.getSelection();
			numberSymbolRightSide = numberSymbolLocCombo.getSelectionIndex() == 0 ? true : false;
			if(items == null || items.length <= 0) {
				updateScaleTable();
			} else if(items != null && items.length >0) {
				// update midiScaleMap
				MidiScale scale = (MidiScale)items[0].getData();
				scale.setNumberSymbolLocation(numberSymbolRightSide);
				//String[] data = createScaleNoteArray(scale);
				//items[0].setText(data);
				
				//scale.getNaturalScaleRole().
				
				updateStatusBar(items);
			}
		}
		*/
	}

	private void updateStatusBar(TableItem[] items) {
		if(items == null || items.length <= 0) {
			statusLabel.setText("");
		} else if(items != null && items.length >0) {
			TableItem item = items[0];
			MidiScale scale = (MidiScale)item.getData();
			statusLabel.setText(String.format("根音：%s  音階名稱：%s  音程關係：%s  升降記號：%s", 
				scale.getRootNotation().natural(), 
				scale.getNaturalScaleRole().getRoleName(), 
				scale.getNaturalScaleRole().getScaleNumbers(),
				scale.getSymbols()));
		}
		/*
		rootModeLabel.setText(scale.getRootNotation().natural()); 
		scaleRoleLabel.setText(scale.getNaturalScaleRole().getRoleName());
		symbolModeLabel.setText(scale.getSymbols().toString());
		*/

	}

	private void updateScaleTable() {
		scaleTable.removeAll();
		
		for (int i = 0; i < IScaleTable.SCALE_CATEGORY_LIST[scaleCategoryId].length; i++) {
			TableItem item = new TableItem(scaleTable, SWT.NULL);
			NaturalNotation rootNote = IScaleTable.ROOTS_HALF_STEP_MODE[rootNoteIdx];
			MidiScale scale = new MidiScale(rootNote, IScaleTable.SCALE_CATEGORY_LIST[scaleCategoryId][i]);
			scale.setLetterSymbolLocation(letterSymbolRightSide);
			scale.setNumberSymbolLocation(numberSymbolRightSide);
			
			String[] data = createScaleNoteArray(scale);
			item.setText(data);
			item.setData(scale);
			
			// highlight the root
			
			if(scale.isMinorSystem()) {
				//item.setForeground(2, this.getDisplay().getSystemColor(SWT.COLOR_RED));
				item.setForeground(this.getDisplay().getSystemColor(SWT.COLOR_BLUE));
				item.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
	}

	private String[] createScaleNoteArray(MidiScale scale) {
		scale.setSymbolMode(symbolMode);
		ArrayList<String> al = new ArrayList<String>();
		al.add(scale.getNaturalScaleRole().getRoleName());
		Iterator<Integer> iterator = scale.getNaturalScaleMap().keySet().iterator();
		while(iterator.hasNext()) {
			int idx = iterator.next();
			NaturalNotation nn = scale.getNaturalScaleMap().get(idx);
			al.add(nn.natural());
		}
		
		String[] data = al.toArray(new String[al.size()]);
		return data;
	}
	
}
