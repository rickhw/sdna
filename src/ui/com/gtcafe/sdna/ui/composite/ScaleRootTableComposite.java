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
import com.gtcafe.sdna.core.scale.MIDIScale;
import com.gtcafe.swt.BorderLayout;

/**
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public class ScaleRootTableComposite extends Composite implements SelectionListener {

	private static final long serialVersionUID = 7040366663250041519L;

	private Combo rootModeCombo, scaleCombo, symbolCombo, letterSymbolLocCombo, numberSymbolLocCombo;
	
	private Table scaleTable;
	private Composite optionPanel, statusPanel;
	private Label rootModeLabel, scaleRoleLabel, symbolModeLabel;
	private Label statusLabel;
	private String[] symbolLabels = {"Default", "Sharp (#)", "Flat (b)"};
	private String[] symbolLocLabels = {"Right (A#)", "Left(#A)"};

	// ----
	private int rootModeId = 0;
	private int scaleRoleId = 0;
	private int symbolMode = 0; //0, 1, 2
	private boolean letterSymbolRightSide = true; 
	private boolean numberSymbolRightSide = false;

	public ScaleRootTableComposite(Composite parent) {
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

		new Label(optionPanel, SWT.NULL).setText("根音排列：");
		rootModeCombo = createCombo(IScaleTable.ROOT_MODE_LIST_NAMES);

		new Label(optionPanel, SWT.NULL).setText("音程關係：");
		scaleCombo = createCombo(IScaleTable.SCALE_NAMES);

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
		//scaleTable.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_TITLE_BACKGROUND));
		scaleTable.setHeaderVisible(true);
		scaleTable.removeAll();
		scaleTable.addSelectionListener(this);
		
		for (int i = 1; i <= 8; i++) {
			TableColumn col = new TableColumn(scaleTable, SWT.CENTER);
			col.setText(""+i);
			col.setWidth(50);
		}
	}

	private void createStatusBar() {
		statusLabel = new Label(this, SWT.BORDER);
		statusLabel.setLayoutData(new BorderLayout.BorderData(BorderLayout.SOUTH));
		
		/*
		statusPanel = new Composite(this, SWT.NULL);
		statusPanel.setLayoutData(new BorderLayout.BorderData(BorderLayout.SOUTH));
		//statusPanel.setLayout(new GridLayout(6, false));
		//statusPanel.setLayout(new RowData());
	

		new Label(statusPanel, SWT.NULL).setText("�ڭ��G");
		rootModeLabel = new Label(statusPanel, SWT.NULL | SWT.BORDER);
		rootModeLabel.setLayoutData(new RowData(100, 20));
		//new Label(statusPanel,SWT.SEPARATOR|SWT.VERTICAL);
		
		new Label(statusPanel, SWT.NULL).setText("�����G");
		scaleRoleLabel = new Label(statusPanel, SWT.NULL | SWT.BORDER);
		scaleRoleLabel.setLayoutData(new RowData(100, 20));
		//new Label(statusPanel,SWT.SEPARATOR|SWT.VERTICAL);
		
		new Label(statusPanel, SWT.NULL).setText("�ո��G");
		symbolModeLabel = new Label(statusPanel, SWT.NULL | SWT.BORDER);
		symbolModeLabel.setLayoutData(new RowData(200, 20));
		*/
	    
	}

	
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if(e.getSource() == rootModeCombo) {
			rootModeId = rootModeCombo.getSelectionIndex();
			updateScaleTable();
		} else if (e.getSource() == scaleCombo) {
			scaleRoleId = scaleCombo.getSelectionIndex();
			
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
				MIDIScale scale = (MIDIScale)item.getData();
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
				MIDIScale scale = (MIDIScale)items[0].getData();
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
			MIDIScale scale = (MIDIScale)item.getData();
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
		
		for (int i = 0; i < IScaleTable.ROOTS_MODE_LIST[rootModeId].length; i++) {
			TableItem item = new TableItem(scaleTable, SWT.NULL);
			NaturalNotation rootNote = IScaleTable.ROOTS_MODE_LIST[rootModeId][i];
			MIDIScale scale = new MIDIScale(rootNote, scaleRoleId);
			scale.setLetterSymbolLocation(letterSymbolRightSide);
			scale.setNumberSymbolLocation(numberSymbolRightSide);
			
			String[] data = createScaleNoteArray(scale);
			item.setText(data);
			item.setData(scale);
			
			// highlight the root
			item.setForeground(0, this.getDisplay().getSystemColor(SWT.COLOR_BLUE));
			item.setBackground(0, this.getDisplay().getSystemColor(SWT.COLOR_GRAY));
			
			if(scale.isMinorSystem()) {
				item.setForeground(2, this.getDisplay().getSystemColor(SWT.COLOR_RED));
			}
		}
		
	}

	private String[] createScaleNoteArray(MIDIScale scale) {
		scale.setSymbolMode(symbolMode);
		ArrayList<String> al = new ArrayList<String>();
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
