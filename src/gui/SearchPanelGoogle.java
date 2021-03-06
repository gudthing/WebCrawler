package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchPanelGoogle extends JPanel{
	private JLabel googleLabel, numberPagesLabel, quickScanLabel, fullScanLabel;
	private JTextField smartSearchField, googleSearchField;
	private JComboBox numberOfPagesComboBox;
	private JRadioButton quickScanRadio, fullScanRadio ;
	private JButton startButton, clearButton;
	private FormListener formListener;
	private ButtonGroup scanGroup;
	String[] pages = { "1 Page", "2 Pages", "3 Pages", "4 Pages", "5 Pages" };
	
	public SearchPanelGoogle(){
		//set size for panel
		Dimension dim = getPreferredSize();
		dim.width = 200;
		setPreferredSize(dim);
		
		//create components
		//titleLabel = new JLabel("Build up your Mailing List");
		googleLabel = new JLabel("Google Search: ");
		googleSearchField = new JTextField(); //might need to set default width
		
		numberPagesLabel = new JLabel("Number Of Pages");
		numberOfPagesComboBox = new JComboBox(pages);
		numberOfPagesComboBox.setSelectedIndex(1);
		
		
		//smartSearchLabel = new JLabel("Google Word Search: ");
		//smartSearchField = new JTextField(); //may need default width
		
		quickScanLabel = new JLabel("Quick Scan");
		quickScanLabel.setHorizontalAlignment(JLabel.CENTER);
		quickScanRadio = new JRadioButton();
		quickScanRadio.setHorizontalAlignment(JLabel.CENTER);
		fullScanLabel = new JLabel("Full Scan");
		fullScanLabel.setHorizontalAlignment(JLabel.CENTER);
		fullScanRadio = new JRadioButton();
		fullScanRadio.setHorizontalAlignment(JLabel.CENTER);
		fullScanRadio.setSelected(true);
		scanGroup = new ButtonGroup();
		
		scanGroup.add(quickScanRadio);
		scanGroup.add(fullScanRadio);
		
		startButton = new JButton("Start Scan");
		clearButton = new JButton("Clear All");
		
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//String searchQuery = smartSearchField.getText();
				String searchQuery = googleSearchField.getText();;
				String searchW = ""; 
				boolean fullSearch = fullScanRadio.isSelected();
				System.out.println(fullSearch);
				
				FormEvent ev = new FormEvent(this, searchW, searchQuery, fullSearch);
				if(formListener != null){
					formListener.formEventOccured(ev);
				}
			}
		});
		
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				MainFrame.clearText();
			}
			
		});
		
		layoutComponents();
		}
	
	private void addFieldListener(final JTextField listen, final JTextField clear) {
		// dynamically checks if content is in both fields, if so, it clears the
		// field
		listen.getDocument().addDocumentListener(
				new DocumentListener() {
					public void changedUpdate(DocumentEvent arg0) {
						changed();
					}
					public void insertUpdate(DocumentEvent arg0) {
						changed();
					}
					public void removeUpdate(DocumentEvent arg0) {
						changed();
					}
					public void changed() {
						if (!listen.getText().isEmpty()) {
							clear.setText("");
							// websiteField.setEnabled(false);
						}
					}
				});		
	}

	private void layoutComponents(){
		// set layout
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(8, 1));

		JPanel firstRowPanel = new JPanel();
		firstRowPanel.setLayout(new GridLayout(2, 1));
		firstRowPanel.add(googleLabel);
		firstRowPanel.add(googleSearchField);
		
		JPanel secondRowPanel = new JPanel();
		secondRowPanel.setLayout(new GridLayout(2, 1));
		secondRowPanel.add(numberPagesLabel);
		secondRowPanel.add(numberOfPagesComboBox);

		JPanel thirdRowPanel = new JPanel();
		thirdRowPanel.setLayout(new GridLayout(2, 2));
		thirdRowPanel.add(quickScanLabel);
		thirdRowPanel.add(fullScanLabel);
		thirdRowPanel.add(quickScanRadio);
		thirdRowPanel.add(fullScanRadio);

		JPanel fourthRowPanel = new JPanel();
		fourthRowPanel.setLayout(new GridLayout(2, 1));
		fourthRowPanel.add(new JLabel(""));
		fourthRowPanel.add(startButton);

		JPanel fifthRowPanel = new JPanel();
		fifthRowPanel.setLayout(new GridLayout(2, 1));
		fifthRowPanel.add(clearButton);

		setLayout(new GridLayout(6, 1));
		add(firstRowPanel);
		add(secondRowPanel);
		add(thirdRowPanel);
		add(fourthRowPanel);
		add(fifthRowPanel);

		// add components to layout

		Border innerBorder = BorderFactory.createTitledBorder("Scan");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

	}
	//not working sincve added jtabbedpane
	public void hidePanel(){
		if(isShowing()){
			
			setVisible(false);
		}else{
			setVisible(true);
		}
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}



