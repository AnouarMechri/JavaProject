package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import metier.FamilleProduit;
import metier.Fournisseur;
import metier.GlobalVariables;
import metier.Produit;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import java.awt.event.WindowFocusListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class AddProduit extends JFrame {

	private JPanel contentPane;
	private JTextField reference_txt;
	private JTextField designation_txt;
	private JTextField stock_txt;
	private JTextField stockmin_txt;
	private JTextField prixUnit_txt;
	private JTextField tva_txt;
	private static AddProduit currentAddProduitForm = null;
	private boolean isClosed = false;
	private JTextField uniteMesure_txt;
	private JTextField fournisseur_txt;
	private JTextField famille_txt;
	private String typeOperation = GlobalVariables.ADDVALUE; // "Add" ou "Update"
	private Connection connection;
	private JTextField txtThisReferenceIs;
	
	public void connecter() throws ClassNotFoundException{

		
        Class.forName("com.mysql.jdbc.Driver");
        try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_commercial","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduit frame = new AddProduit(GlobalVariables.ADDVALUE);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddProduit(String _typeOperation) {
		typeOperation = _typeOperation;
		try {
			connecter();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					AcceuilForm.getCurrentAcceuilForm().setEntityName(null);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				isClosed = true;
			}
		});
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			}
			public void windowLostFocus(WindowEvent e) {
				if(!isClosed)
					AddProduit.getCurrentAddProduitForm(typeOperation).setVisible(true);
			}
		});
		setTitle("Add Produit");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(5, 279, 424, 33);
		contentPane.add(panel_6);
		
		JButton valider_bn = new JButton("Valider");
		valider_bn.setEnabled(false);
		valider_bn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String reference = reference_txt.getText();
				String designation = designation_txt.getText();
				String uniteMesure = uniteMesure_txt.getText();
				Fournisseur fournisseur = Fournisseur.getFournisseur(fournisseur_txt.getText());
				
				int codeFamilleProduit = Integer.parseInt(famille_txt.getText());
				FamilleProduit familleProduit = FamilleProduit.getFamilleProduit(codeFamilleProduit);
				int stock = Integer.parseInt(stock_txt.getText());
				int stockMinimal = Integer.parseInt(stockmin_txt.getText());
				int prixUnitaireHTaxe = Integer.parseInt(prixUnit_txt.getText());
				int tva = Integer.parseInt(tva_txt.getText());
				
				//verifier la validité des informations :
				String msg = "";
				boolean verif = true;
				if(reference.isEmpty())
				{
					reference_txt.setForeground(Color.RED);
					verif = false;
				}
				if(designation.isEmpty())
				{
					designation_txt.setForeground(Color.RED);
					verif = false;
				}
				if(uniteMesure.isEmpty())
				{
					uniteMesure_txt.setForeground(Color.RED);
					verif = false;
				}
				
				
				// à completer..
				
				if(!verif)
				{
					JOptionPane.showMessageDialog(null, "Verify values");
					return;
				}
								
				Produit newProduit = new Produit(reference, designation, 
				uniteMesure, fournisseur,familleProduit, stock, stockMinimal,
				prixUnitaireHTaxe, tva);
				
					if(typeOperation == GlobalVariables.ADDVALUE && newProduit.ajouterProduit())
						JOptionPane.showMessageDialog(null, "Product added successfully");
					
					
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			 catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Invalid values");
			}
			}
		});
		panel_6.add(valider_bn);
		
		JButton annuler_bn = new JButton("Annuler");
		annuler_bn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reference_txt.setText("");
				designation_txt.setText("");
				uniteMesure_txt.setText("");
				fournisseur_txt.setText("");
				famille_txt.setText("");
				stock_txt.setText("");
				stockmin_txt.setText("");
				tva_txt.setText("");	
				prixUnit_txt.setText("");
				}
		});
		panel_6.add(annuler_bn);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(5, 5, 424, 180);
		panel_7.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_7);
		panel_7.setLayout(new GridLayout(9, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_7.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel reference_lab = new JLabel("Reference");
		panel.add(reference_lab);
		
		reference_txt = new JTextField();
		reference_txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				Statement stmt;
				try {
					stmt = (Statement)connection.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT * FROM produit");
					ArrayList<String> test= new ArrayList<String>();
					
					while(rs.next()) {
						test.add(rs.getString("reference"));
					}
					if(test.contains(reference_txt.getText())) {
						valider_bn.setEnabled(false);
						txtThisReferenceIs.setVisible(true);
					} 
					else if(reference_txt.getText().isEmpty()){
						valider_bn.setEnabled(false);
						txtThisReferenceIs.setVisible(false);
					}
					else {
						valider_bn.setEnabled(true);
						txtThisReferenceIs.setVisible(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		reference_txt.setColumns(10);
		panel.add(reference_txt);
		
		JPanel panel_1 = new JPanel();
		panel_7.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel designation_lab = new JLabel("Designation");
		panel_1.add(designation_lab);
		
		designation_txt = new JTextField();
		designation_txt.setColumns(10);
		panel_1.add(designation_txt);
		
		JPanel panel_2 = new JPanel();
		panel_7.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel stock_lab = new JLabel("Stock");
		panel_2.add(stock_lab);
		
		stock_txt = new JTextField();
		stock_txt.setColumns(10);
		panel_2.add(stock_txt);
		
		JPanel panel_3 = new JPanel();
		panel_7.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel stockMin_lab = new JLabel("Stock Min");
		panel_3.add(stockMin_lab);
		
		stockmin_txt = new JTextField();
		stockmin_txt.setColumns(10);
		panel_3.add(stockmin_txt);
		
		JPanel panel_4 = new JPanel();
		panel_7.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel pricUnit_lab = new JLabel("Prix Unit.");
		panel_4.add(pricUnit_lab);
		
		prixUnit_txt = new JTextField();
		prixUnit_txt.setColumns(10);
		panel_4.add(prixUnit_txt);
		
		JPanel panel_5_1 = new JPanel();
		panel_7.add(panel_5_1);
		panel_5_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel uniteMesure_lab = new JLabel("Unite Mesure");
		panel_5_1.add(uniteMesure_lab);
		
		uniteMesure_txt = new JTextField();
		uniteMesure_txt.setColumns(10);
		panel_5_1.add(uniteMesure_txt);
		
		JPanel panel_5 = new JPanel();
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel tva_lab = new JLabel("TVA");
		panel_5.add(tva_lab);
		
		tva_txt = new JTextField();
		tva_txt.setColumns(10);
		panel_5.add(tva_txt);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel Fournisseur = new JLabel("Fournisseur");
		panel_8.add(Fournisseur);
		
		fournisseur_txt = new JTextField();
		panel_8.add(fournisseur_txt);
		fournisseur_txt.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel famille_lab = new JLabel("Famille");
		panel_9.add(famille_lab);
		
		famille_txt = new JTextField();
		panel_9.add(famille_txt);
		famille_txt.setColumns(10);
		
		txtThisReferenceIs = new JTextField();
		txtThisReferenceIs.setVisible(false);
		txtThisReferenceIs.setForeground(Color.RED);
		txtThisReferenceIs.setHorizontalAlignment(SwingConstants.CENTER);
		txtThisReferenceIs.setText("This reference is already used !");
		txtThisReferenceIs.setBounds(70, 222, 297, 27);
		contentPane.add(txtThisReferenceIs);
		txtThisReferenceIs.setColumns(10);
	}

	public static AddProduit getCurrentAddProduitForm(String _typeOperation)
	{
		if(currentAddProduitForm == null)
			currentAddProduitForm = new AddProduit(_typeOperation);
		return currentAddProduitForm;
	}
}
