package com.bradley.readinggenerator;

import java.awt.Event;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.*;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/*
 * @author bradley
 * created by bradley - 03 June 2017
*/
public class Main extends JFrame {

	static final String DESKTOP_DIR = System.getProperty("user.home") + "/Desktop/";
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final JSeparator separator = new JSeparator();
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 441);
		setTitle("Readings Generator - shantanu.banerjee.vt@gmail.com");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Project Name");
		lblNewLabel.setBounds(422, 36, 79, 20);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(508, 36, 116, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Serial Number");
		lblNewLabel_1.setBounds(10, 70, 94, 14);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(101, 67, 116, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Select Catogery");
		lblNewLabel_2.setBounds(241, 70, 94, 14);
		contentPane.add(lblNewLabel_2);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(345, 67, 116, 20);
		comboBox.addItem("5");
		comboBox.addItem("10");
		comboBox.addItem("1.7");
		comboBox.addItem("3.5");
		comboBox.addItem("50");
		comboBox.addItem("200");
		comboBox.addItem("1000");
		comboBox.addItem("5 to 35");
		comboBox.addItem("1");
		comboBox.addItem("35");
		comboBox.addItem("100");
		comboBox.addItem("150");
		comboBox.addItem("2");
		comboBox.addItem("500");
		comboBox.addItem("4");
		comboBox.addItem("400");
		contentPane.add(comboBox);
		separator.setBounds(3, 115, 692, 7);
		contentPane.add(separator);

		JLabel lblZeroReading = new JLabel("Zero Reading");
		lblZeroReading.setBounds(106, 133, 79, 20);
		contentPane.add(lblZeroReading);

		textField_2 = new JTextField();
		textField_2.setBounds(208, 133, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblSecondReading = new JLabel("Second Reading");
		lblSecondReading.setBounds(367, 132, 94, 17);
		contentPane.add(lblSecondReading);

		textField_3 = new JTextField();
		textField_3.setBounds(473, 131, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		table = new JTable(new DefaultTableModel());
		table.setBounds(106, 169, 453, 156);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("load");
		model.addColumn("up");
		model.addColumn("down");
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		model.addRow(new Object[] { "Pressure", "Up Reading", "Down Reading" });
		contentPane.add(table);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(293, 352, 89, 23);
		contentPane.add(btnSave);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selection = comboBox.getSelectedIndex();
				makeChangesToTable(selection);
			}
		});

		textField_2.setText("0");
		textField_3.setText("0");
				
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setBounds(10, 11, 59, 14);
		contentPane.add(lblCustomer);
		
		textField_4 = new JTextField();
		textField_4.setBounds(79, 8, 116, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPoRef = new JLabel("P.O. Ref");
		lblPoRef.setBounds(237, 11, 46, 14);
		contentPane.add(lblPoRef);
		
		textField_5 = new JTextField();
		textField_5.setBounds(302, 8, 116, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblInstrument = new JLabel("Instrument");
		lblInstrument.setBounds(454, 11, 73, 14);
		contentPane.add(lblInstrument);
		
		textField_6 = new JTextField();
		textField_6.setBounds(537, 8, 116, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblRange = new JLabel("Range");
		lblRange.setBounds(10, 39, 46, 14);
		contentPane.add(lblRange);
		
		textField_7 = new JTextField();
		textField_7.setBounds(66, 36, 116, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(219, 39, 46, 14);
		contentPane.add(lblDate);
		
		textField_8 = new JTextField();
		textField_8.setBounds(275, 36, 116, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(566, 67, 58, 20);
		comboBox_1.addItem("MPa");
		comboBox_1.addItem("Ksc");
		comboBox_1.addItem("KPa");
		comboBox_1.addItem("psi");
		comboBox_1.addItem("Bar");
		comboBox_1.addItem("mm");
		comboBox_1.addItem("Ton");
		contentPane.add(comboBox_1);
		
		JLabel lblSelectUnit = new JLabel("Select Unit");
		lblSelectUnit.setBounds(481, 70, 75, 14);
		contentPane.add(lblSelectUnit);
		
		JCheckBox autoIncCheckBox = new JCheckBox("Auto Increment S.No");
		autoIncCheckBox.setBounds(101, 88, 182, 21);
		contentPane.add(autoIncCheckBox);

		textField_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertIntoTableCol1(textField_2.getText(), textField_3.getText(), comboBox.getSelectedIndex());
			}
		});
		textField_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insertIntoTableCol1(textField_2.getText(), textField_3.getText(), comboBox.getSelectedIndex());
			}
		});

		KeyStroke saveShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK);
		AbstractAction saveAction = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText() == null || textField.getText().equals("") || textField_1.getText() == null
						|| textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Project Name or Serial Number",
							"Readings Generator - shantanu.banerjee.vt@gmail.com", JOptionPane.ERROR_MESSAGE);
					return;
				}

				File dir = new File(DESKTOP_DIR + textField.getText());
				File cDir = new File(new File(dir, textField_6.getText().isEmpty() ? "Instr" : textField_6.getText()), ((String) comboBox.getSelectedItem()) + (String) comboBox_1.getSelectedItem());
				cDir.mkdirs();
				File dFile = new File(cDir, textField_1.getText() + ".pdf");
				destFile = dFile.getAbsolutePath();
				if (dFile.exists()) {
					int opt = JOptionPane.showConfirmDialog(null,
							"File already exits. \nDo you want to overwrite the existing file?",
							"Readings Generator - shantanu.banerjee.vt@gmail.com", JOptionPane.YES_NO_OPTION);
					if (opt == JOptionPane.YES_OPTION)
						;
					else
						return;
				}
				
				unit = (String) comboBox_1.getSelectedItem();

				try {
					LocalDate localDate = LocalDate.parse(textField_8.getText(), dateTimeFormatter);
					date = localDate.format(dateTimeFormatter);
					date6mo = localDate.plusMonths(6).format(dateTimeFormatter);
				} catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
							"Please write the date in dd/mm/yyyy format",
							"Readings Generator - shantanu.banerjee.vt@gmail.com",
							JOptionPane.ERROR_MESSAGE);
					return;
                }

                saveToTemplate();
				try {
					if (autoIncCheckBox.isSelected()) {
						textField_1.setText(String.valueOf(Integer.valueOf(textField_1.getText()) + 1));
						textField_2.requestFocus();
					} else {
						textField_1.requestFocus();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Saved successfully!",
						"Readings Generator - shantanu.banerjee.vt@gmail.com", JOptionPane.INFORMATION_MESSAGE);
			}
		};
		btnSave.addActionListener(saveAction);
		btnSave.getActionMap().put("save", saveAction);
		btnSave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(saveShortcut, "save");
		
		FocusAdapter fa = new FocusAdapter() {
			
			public void focusGained(FocusEvent evt) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						((JTextField)evt.getSource()).selectAll();
					}
				});
			}
		};
		textField_1.addFocusListener(fa);
		textField_2.addFocusListener(fa);
		textField_3.addFocusListener(fa);
		makeChangesToTable(0);
	}

	String unit;
	
	protected void insertIntoTableCol1(String a, String b, int idx) {
		// TODO Auto-generated method stub
		if (a.equals("") || b.equals(""))
			return;
		float f = Float.parseFloat(a);
		float s = Float.parseFloat(b);
		float diff;
		if (idx == 2) {
			diff = (f - s) / 17f;
			s = s - diff;
		} else if (idx == 7) {
			diff = (f - s) / 5f;
			s = f - (diff * 35);
		}

		
		diff = -(f - s) / num;
		

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.setValueAt(String.format("%.0f", f), 1, 1);
		model.setValueAt(String.format("%.0f", f + ThreadLocalRandom.current().nextInt(-4, 5)), 1, 2);
		f = f + diff;
		for (int i = 1; i <= num; i = i + 1) {
			model.setValueAt(String.format("%.0f", f), i + 1, 1);
			if (i < num)
				model.setValueAt(String.format("%.0f", f + ThreadLocalRandom.current().nextInt(-4, 5)), i + 1, 2);
			else
				model.setValueAt(String.format("%.0f", f), i + 1, 2);
			f = f + diff;
		}

	}

	float maxLoad = 0, difference = 0;
	int num = 0;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	
	private String destFile;

	protected void makeChangesToTable(int selection) {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		if (selection == 0) {
			maxLoad = 5;
			difference = 1;
			num = 5;
		} else if (selection == 1) {
			maxLoad = 10;
			difference = 2;
			num = 5;
		} else if (selection == 2) {
			maxLoad = 1.8f;
			difference = 0.3f;
			num = 6;
		} else if (selection == 3) {
			maxLoad = 3.5f;
			difference = 0.7f;
			num = 5;
		} else if (selection == 4) {
			maxLoad = 50;
			difference = 10;
			num = 5;
		} else if (selection == 5) {
			maxLoad = 200;
			difference = 40;
			num = 5;
		} else if (selection == 6) {
			maxLoad = 1000;
			difference = 200;
			num = 5;
		} else if (selection == 7) {
			maxLoad = 3.5f;
			difference = 0.7f;
			num = 5;
		} else if (selection == 8) {
			maxLoad = 1.0f;
			difference = 0.2f;
			num = 5;
		} else if (selection == 9) {
			maxLoad = 35.0f;
			difference = 7.0f;
			num = 5;
		} else if (selection == 10) {
			maxLoad = 100.0f;
			difference = 20.0f;
			num = 5;
		} else if (selection == 11) {
			maxLoad = 150.0f;
			difference = 30.0f;
			num = 5;
		} else if (selection == 12) {
			maxLoad = 2.0f;
			difference = 0.4f;
			num = 5;
		} else if (selection == 13) {
			maxLoad = 500.0f;
			difference = 100.0f;
			num = 5;
		} else if (selection == 14) {
			maxLoad = 4.0f;
			difference = 0.8f;
			num = 5;
		} else if (selection == 15) {
			maxLoad = 400.0f;
			difference = 80.0f;
			num = 5;
		}

		model.setRowCount(num + 2);

		for (int i = 1; i < model.getRowCount(); i++) {
			model.setValueAt("", i, 0);
			model.setValueAt("", i, 1);
			model.setValueAt("", i, 2);
		}

		for (int i = 0; i <= num; i++) {
			model.setValueAt(String.format("%.1f", i * difference), i + 1, 0);
		}

	}
	
	double[][] data;
	double x[], y[], abc[], gf, lpress[], ppress[], lpresserr[], ppresserr[];
	String customer, poref, instrument, range, date, sno, date6mo;
	
	@SuppressWarnings({ "unchecked" })
	void saveToTemplate() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Vector<Vector> v = model.getDataVector();
		customer = textField_4.getText();
		poref = textField_5.getText();
		instrument = textField_6.getText();
		range = textField_7.getText();
		sno = textField_1.getText();
		
		data = new double [v.size() - 1][v.get(0).size()];
		
		for (int i = 1 ; i < v.size() ; i++) {
			Vector<Object> vv = v.get(i);
			for (int j = 0 ; j < vv.size() ; j++) {
				data[i - 1][j] = Double.parseDouble((String) vv.get(j));
			}
		}
		
		y = new double[data.length];
		x = new double[data.length];
		for (int i = 0 ; i < data.length ; i++) {
			y[i] = data[i][0];
			x[i] = (data[i][1] + data[i][2]) / 2.0d;
		}
		
		gf = y[y.length - 1] / (x[0] - x[x.length - 1]);
		
		WeightedObservedPoints pts = new WeightedObservedPoints();
		for (int i = 0 ; i < data.length ; i++) {
			pts.add(x[i], y[i]);
		}
		PolynomialCurveFitter fitter =PolynomialCurveFitter.create(2);
		abc = fitter.fit(pts.toList());
		
		lpress = new double[data.length];
		ppress = new double[data.length];
		lpresserr = new double[data.length];
		ppresserr = new double[data.length];
		
		for (int i = 0 ; i < data.length ; i++) {
			lpress[i] = gf * (x[0] - x[i]);
			ppress[i] = abc[2] * (x[i] * x[i]) + abc[1] * (x[i]) + abc[0];
			lpresserr[i] = Math.abs(y[i] - lpress[i]) / y[y.length - 1] * 100;
			ppresserr[i] = Math.abs(y[i] - ppress[i]) / y[y.length - 1] * 100;
		}
		
		savePDF();
		
	}

	private void savePDF() {
		// TODO Auto-generated method stub
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(destFile));
			
			String[][] t1L = { 
					{"Customer", customer},
					{"P.O. Ref", poref},
					{"Instrument", instrument},
					{"Date", date},
					{"Range", range},
					{"R.Temp", "23\u00b0C"},
					{"Mfg. Sl. No", sno},
					{"Atm. Press", "1004 mb"}
			};
			doc.setMargins(70, 70, 180, 50);
			doc.open();
			doc.setMargins(0, 50, 50, 10);
			PdfPTable t1 = new PdfPTable(new float[] {4, 7, 4, 7});
			t1.setWidthPercentage(100);
			PdfPCell p = new PdfPCell(), q = new PdfPCell();
			Phrase pp = new Phrase(), qp = new Phrase();
			
			for (int i = 0 ; i < t1L.length ; i++) {
				pp = new MyCustomePhrase(t1L[i][0], FontFactory.getFont(FontFactory.TIMES_BOLD, 10)); qp = new MyCustomePhrase(t1L[i][1]);
				p.setPhrase(pp);
				q.setPhrase(qp);
				
				if (i < 2) {
					if (i == 0) {
						p.setBorder(Rectangle.LEFT|Rectangle.TOP);
						q.setBorder(Rectangle.TOP);
					} else {
						p.setBorder(Rectangle.TOP);
						q.setBorder(Rectangle.TOP|Rectangle.RIGHT);
					}
				} else if (i < 4) {
					if (i == 2) {
						p.setBorder(Rectangle.LEFT);
						q.setBorder(0);
					} else {
						p.setBorder(0);
						q.setBorder(Rectangle.RIGHT);
					}
				} else if (i < 6) {
					if (i == 4) {
						p.setBorder(Rectangle.LEFT);
						q.setBorder(0);
					} else {
						p.setBorder(0);
						q.setBorder(Rectangle.RIGHT);
					} 
				} else {
					if (i == 6) {
						p.setBorder(Rectangle.LEFT|Rectangle.BOTTOM);
						q.setBorder(Rectangle.BOTTOM);
					} else {
						p.setBorder(Rectangle.BOTTOM);
						q.setBorder(Rectangle.RIGHT|Rectangle.BOTTOM);
					}
				}
				
				t1.addCell(p);
				t1.addCell(q);
			}
			
			String[] t2L = {
					"Pressure",
					"O/P DIGIT (f�/1000)",
					"End Point",
					"Polyfit",
					"End Point",
					"Polyfit"
			};
			
			PdfPTable t2 = new PdfPTable(new float[] {1, 3, 1, 1, 1, 1});
			t2.setWidthPercentage(100);
			p = new PdfPCell();
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			for (int i = 0 ; i < t2L.length ; i++) {
				pp = new MyCustomePhrase(t2L[i], FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
				p.setPhrase(pp);
				t2.addCell(p);
			}
			
			String[] t3L = {
					unit,
					"UP",
					"DOWN",
					"AVG",
					"Acc. " + unit,
					"Acc. " + unit,
					"NL.%F.S.",
					"Err%F.S."
			};
			PdfPTable t3 = new PdfPTable(new float[] {1, 1, 1, 1, 1, 1, 1, 1});
			t3.setWidthPercentage(100);
			p = new PdfPCell();
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			for (int i = 0 ; i < t3L.length ; i++) {
				pp = new MyCustomePhrase(t3L[i], FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
				p.setPhrase(pp);
				t3.addCell(p);
			}
			double max1 = Double.MIN_VALUE, max2 = Double.MIN_VALUE;
			for (int i = 0 ; i < y.length ; i++) {
				p = new PdfPCell();
				p.setHorizontalAlignment(Element.ALIGN_CENTER);
				p.setBorder(Rectangle.LEFT);
				p.setPhrase(new MyCustomePhrase(String.format("%.1f", y[i])));
				t3.addCell(p);
				p.setBorder(0);
				p.setPhrase(new MyCustomePhrase(String.format("%.0f", data[i][1])));
				t3.addCell(p);
				p.setPhrase(new MyCustomePhrase(String.format("%.0f", data[i][2])));
				t3.addCell(p);
				p.setPhrase(new MyCustomePhrase(String.format("%.0f", x[i])));
				p.setGrayFill(0.8f);
				t3.addCell(p);
				p.setGrayFill(1.0f);
				p.setPhrase(new MyCustomePhrase(String.format("%.3f", lpress[i])));
				t3.addCell(p);
				p.setPhrase(new MyCustomePhrase(String.format("%.3f", ppress[i])));
				t3.addCell(p);
				p.setPhrase(new MyCustomePhrase(String.format("%.2f", lpresserr[i])));
				t3.addCell(p);
				p.setBorder(Rectangle.RIGHT);
				p.setPhrase(new MyCustomePhrase(String.format("%.2f", ppresserr[i])));
				t3.addCell(p);
				
				max1 = Math.max(max1, lpresserr[i]);
				max2 = Math.max(max2, ppresserr[i]);
				
			}
			
			p = new PdfPCell();
			p.setBorder(Rectangle.LEFT|Rectangle.TOP);
			t3.addCell(p); p.setBorder(Rectangle.TOP); t3.addCell(p); t3.addCell(p);
			p = new PdfPCell();
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			p.setPhrase(new MyCustomePhrase("NL%F.S.", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			t3.addCell(p);
			p.setPhrase(new MyCustomePhrase(String.format("%.2f", max1)));
			t3.addCell(p);
			p.setPhrase(new MyCustomePhrase(String.format("%.2f", max2)));
			t3.addCell(p);
			p.setPhrase(new MyCustomePhrase(String.format("%.2f", max1)));
			t3.addCell(p);
			p.setPhrase(new MyCustomePhrase(String.format("%.2f", max2)));
			t3.addCell(p);
			
			DecimalFormat format = new DecimalFormat("0.####E0");
			PdfPTable t4 = new PdfPTable(2);
			t4.setWidthPercentage(100);
			p = new PdfPCell(new MyCustomePhrase("LINEAR ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			p.setBorder(Rectangle.LEFT|Rectangle.TOP|Rectangle.BOTTOM);
			p.setGrayFill(0.8f);
			t4.addCell(p);
			p = new PdfPCell(new MyCustomePhrase("(G) = " + String.valueOf(format.format(gf)) + " " + unit + "/DIGIT"));
			p.setBorder(Rectangle.TOP|Rectangle.RIGHT|Rectangle.BOTTOM);
			p.setGrayFill(0.8f);
			t4.addCell(p);
			p = new PdfPCell(new MyCustomePhrase("THERMAL ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10)));
			p.setBorder(Rectangle.LEFT|Rectangle.TOP|Rectangle.BOTTOM);
			t4.addCell(p);
			p = new PdfPCell(new MyCustomePhrase("(K) = " + String.valueOf(format.format(0.000126)) + " " + unit + "� C"));
			p.setBorder(Rectangle.TOP|Rectangle.RIGHT|Rectangle.BOTTOM);
			t4.addCell(p);
			
			PdfPTable t5 = new PdfPTable(4);
			t5.setWidthPercentage(100);
			t5.addCell(new PdfPCell(new MyCustomePhrase("Polynomial Const.")));
			
			String sa = String.valueOf(format.format(abc[2]));
			p = new PdfPCell(new MyCustomePhrase("A = " + sa));
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			t5.addCell(p);
			sa = String.valueOf(format.format(abc[1]));
			p = new PdfPCell(new MyCustomePhrase("B = " + sa));
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			t5.addCell(p);
			sa = String.valueOf(format.format(abc[0]));
			p = new PdfPCell(new MyCustomePhrase("C = " + sa));
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			t5.addCell(p);
			
			PdfPTable t6 = new PdfPTable(1);
			t6.setWidthPercentage(100);
			p = new PdfPCell();
			p.setBorder(Rectangle.LEFT|Rectangle.RIGHT|Rectangle.TOP);
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			p.setVerticalAlignment(Element.ALIGN_CENTER);
			
			String[] t4L = {
					"",
					"To Calculate Pressure 'P'  use the following equation:",
					"Linear (P) = G(R0 - R1) + k(T1 - T0) - (S1 - S0)",
					"Polynomial  (P) = A(R\u0031)\u00b2 + B(R1) + C + k(T1 - T0) - (S1 - S0)",
					"R1 = Current reading in digit during observation.",
			};

			for (int i = 0 ; i < t4L.length ; i++) {
				p.setPhrase(new MyCustomePhrase(t4L[i], FontFactory.getFont(FontFactory.TIMES, 8)));
				p.setBorder(Rectangle.LEFT|Rectangle.RIGHT);
				t6.addCell(p);
			}

			PdfPTable t7 = new PdfPTable(6);
			t7.setWidthPercentage(100);
			p = new PdfPCell();
			p.setBorder(0);
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			p.setVerticalAlignment(Element.ALIGN_CENTER);

			p.setPhrase(new MyCustomePhrase("Factory Zero Reading"));
			t7.addCell(p);

			String fzeroreading = String.format("%.0f", x[0] + ThreadLocalRandom.current().nextInt(-5, 6));
			p.setPhrase(new MyCustomePhrase(fzeroreading));
			p.setBorder(15);
			t7.addCell(p);

			p.setBorder(0);

			p.setPhrase(new MyCustomePhrase("Temperature"));
			t7.addCell(p);

			p.setBorder(15);
			p.setPhrase(new MyCustomePhrase("23°C"));
			t7.addCell(p);

			p.setBorder(0);
			p.setPhrase(new MyCustomePhrase("Barometer"));
			t7.addCell(p);

			p.setBorder(15);
			p.setPhrase(new MyCustomePhrase("1004 mb"));
			t7.addCell(p);

			t6.addCell(t7);

			p.setHorizontalAlignment(Element.ALIGN_LEFT);

			p.setPhrase(new MyCustomePhrase("This calibration has been verified/validated as of " + date6mo, FontFactory.getFont(FontFactory.TIMES, 8)));
			p.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			t6.addCell(p);

			p.setHorizontalAlignment(Element.ALIGN_CENTER);

			p.setPhrase(
					new Phrase(
							"The above instrument was found to be in tolerance in all operating ranges.",
							FontFactory.getFont(FontFactory.TIMES_BOLD, 8)
					)
			);
			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			p.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
			t6.addCell(p);

			p.setPhrase(
					new Phrase(
							"The above instrument has been calibrated by comparison with standards traceable to the NIST, in compliance with ANSI Z540-1",
							FontFactory.getFont(FontFactory.TIMES_BOLD, 9)
					)
			);
			t6.addCell(p);

			p.setHorizontalAlignment(Element.ALIGN_CENTER);
			p.setVerticalAlignment(Element.ALIGN_CENTER);

			String[] t5L = {
					"User is advised to establish zero conditions at known temperature & atmospheric pressure.",
					"",
					"Digit = Freq\u00b2/1000 also called LU (Linear Unit).",
					"R0 = Reading at zero pressure in LU.",
					"R1 = Current reading in LU during observation.",
					"k = Thermal factor in " + unit + "/\u00b0C.",
					"T0 = Temp at the time of taking zero reading.",
					"T1 = Temp during observation.",
					"S0 = atmospheric pressure at the time of taking zero reading",
					"S1 = Atmospheric pressure during observation.",
					"",
					"Red\t=\tSENSOR+\tGreen\t=\tThermistor",
					"Black\t=\tSENSOR-\tWhite\t=\tThermistor\tBare\t=\tSHIELD"
			};
			
			for (int i = 0 ; i < t5L.length ; i++) {
				p.setPhrase(new Phrase(t5L[i], FontFactory.getFont(FontFactory.TIMES, 10)));
				if (i != t5L.length - 1) {
					if (i != 0) p.setBorder(Rectangle.LEFT|Rectangle.RIGHT);
				} else {
					p.setBorder(Rectangle.LEFT|Rectangle.BOTTOM|Rectangle.RIGHT);
				}
				
				p.setGrayFill(i == 0 ? 0.8f : 1f);
				t6.addCell(p);
			}
			
			doc.add(t1);
			doc.add(t2);
			doc.add(t3);
			doc.add(t4);
			doc.add(t5);
			doc.add(t6);
			doc.close();
			
			
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
