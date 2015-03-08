package com.gtcafe.sdna.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.gtcafe.sdna.ui.composite.NotationComposite;
import com.gtcafe.sdna.ui.composite.ScaleRootTableComposite;
import com.gtcafe.sdna.ui.composite.ScaleTypeTableComposite;

/**
 * @author Rick Hwang<rick_kyhwang@hotmail.com>
 */
public class Main {
	private Display display;
	private Shell shell;
	private CTabFolder mainFolder;
	private CTabItem notation, interval, chord, timing, scaleRoot, scaleCategory;

	public Main() {
		initShell();
		createMenu();
		createTabFolder();
		openWindow();
		readAndDispatch();
	}

	private void initShell() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setImage(new Image(display, "resources/images/logo.gif"));
		shell.setText(About.ABOUT_THIS_SOFTWARE);
	}

	private void createTabFolder() {
		mainFolder = new CTabFolder(shell, SWT.BORDER);
		// mainComposite.setMaximizeVisible(true);
		// mainComposite.setMinimizeVisible(true);
		mainFolder.setTabHeight(24);
		mainFolder.setSelectionBackground(new Color[] {
				display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND),
				display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT) },
				new int[] { 90 }, true);
		mainFolder.setSelectionForeground(display
				.getSystemColor(SWT.COLOR_WHITE));

		scaleRoot = createTabItem(mainFolder, "Scale Root");
		scaleRoot.setControl(new ScaleRootTableComposite(mainFolder));
		scaleRoot.setToolTipText("音階表格 (根音排列)");

		scaleCategory = createTabItem(mainFolder, "Scale Type");
		scaleCategory.setControl(new ScaleTypeTableComposite(mainFolder));
		scaleCategory.setToolTipText("音階分類表");

		//
		notation = createTabItem(mainFolder, "Notation");
		notation.setControl(new NotationComposite(mainFolder));
		notation.setToolTipText("音符");
		
		/*
		interval = createTabItem(mainFolder, "Interval");
		interval.setToolTipText("音程");
		
		chord = createTabItem(mainFolder, "Chord");
		chord.setToolTipText("和弦/和絃");

		timing = createTabItem(mainFolder, "Rhythm/Timing");
		timing.setToolTipText("節奏");
		*/
	}

	private void createMenu() {
		Menu menubar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menubar);

		/*
		 * MenuItem fileMenuItem = new MenuItem(menubar, SWT.CASCADE);
		 * fileMenuItem.setText("File");
		 * 
		 * Menu menu1 = new Menu(fileMenuItem); fileMenuItem.setMenu(menu1);
		 * 
		 * MenuItem item1_1 = new MenuItem(menu1, SWT.PUSH);
		 * item1_1.setText("Open");
		 */

		MenuItem aboutMenuItem = new MenuItem(menubar, SWT.PUSH);
		aboutMenuItem.setText("About");
		aboutMenuItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox aboutBox = new MessageBox(shell, 
						SWT.OK | SWT.ICON_INFORMATION);
				
				aboutBox.setMessage(About.ABOUT_THIS_SOFTWARE + "\n\n" + About.ABOUT_AUTHOR);
				aboutBox.open();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

	}

	private CTabItem createTabItem(CTabFolder parent, String title) {
		CTabItem item = new CTabItem(parent, SWT.NONE);
		item.setText(title);
		Image image = new Image(display, "resources/images/text.gif");
		item.setImage(image);
		return item;
	}

	private void openWindow() {
		shell.setSize(800, 400);
		// shell.pack();
		shell.open();
	}

	private void readAndDispatch() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public static void main(String[] args) {
		new Main();
	}
}
