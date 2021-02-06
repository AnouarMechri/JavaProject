package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import metier.Entreprise;
import metier.GlobalVariables;

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

public class AddEntreprise extends JFrame {

	private JPanel contentPane;
	private JTextField matriculeFiscale_txt;
	private JTextField raisonSociale_txt;
	private JTextField type_txt;
	private JTextField description_txt;
	private JTextField comptesBancaire_txt;
	private JTextField adresse_txt;
	private JTextField telephoneFixe_txt;
	private JTextField telephoneMobile_txt;
	private JTextField fax_txt;
	private JTextField email_txt;
	private JTextField website_txt;
	private static AddEntreprise currentAddEntrepriseForm = null;
	private boolean isClosed = false;
	private String typeOperation = GlobalVariables.ADDVALUE; // "Add" ou "Update"
	private JTextField etatFiscale_txt;
	private JTextField txtThisMatriculeIs;
	private Connection connection;
	/**
	 * Launch the application.
	 */
public void connecter() throws ClassNotFoundException{

		
        Class.forName("com.mysql.jdbc.Driver");
        try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_commercial","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEntreprise frame = new AddEntreprise(GlobalVariables.ADDVALUE);
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
	public AddEntreprise(String _typeOperation) {
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
					AddEntreprise.getCurrentAddEntrepriseForm(typeOperation).setVisible(true);
			}
		});
		setTitle("Add Entreprise");
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
		valider_bn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String matriculeFiscale = matriculeFiscale_txt.getText();
				String raisonSociale = raisonSociale_txt.getText();
				String type = type_txt.getText();
				String description = description_txt.getText();
				int comptesBancaire = Integer.parseInt(comptesBancaire_txt.getText());
				String adresse = adresse_txt.getText();
				int telephoneFixe = Integer.parseInt(telephoneFixe_txt.getText());
				int telephoneMobile = Integer.parseInt(telephoneMobile_txt.getText());
				int fax = Integer.parseInt(fax_txt.getText());
				String email = email_txt.getText();
				String website = website_txt.getText();
				String etatFiscale = etatFiscale_txt.getText();
				
				//verifier la validité des informations :
				String msg = "";
				boolean verif = true;
				if(matriculeFiscale.isEmpty())
				{
					matriculeFiscale_txt.setForeground(Color.RED);
					verif = false;
				}
				// à completer..
				
				if(!verif)
				{
					JOptionPane.showMessageDialog(null, "Verify values");
					return;
				}
								
				Entreprise newEntreprise = new Entreprise(matriculeFiscale, raisonSociale, 
						type, description,comptesBancaire, adresse, telephoneFixe,
						telephoneMobile, fax,email,website,etatFiscale);
				
					if(typeOperation == GlobalVariables.ADDVALUE && newEntreprise.ajouterEntreprise())
						JOptionPane.showMessageDialog(null, "Entreprise successfully added");
					
					
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
				matriculeFiscale_txt.setText("");
				raisonSociale_txt.setText("");
				type_txt.setText("");
				description_txt.setText("");
				adresse_txt.setText("");	
				comptesBancaire_txt.setText("");
				telephoneFixe_txt.setText("");
				telephoneMobile_txt.setText("");
				fax_txt.setText("");
				email_txt.setText("");
				website_txt.setText("");
				etatFiscale_txt.setText("");
				}
		});
		panel_6.add(annuler_bn);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(5, 5, 424, 240);
		panel_7.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_7);
		panel_7.setLayout(new GridLayout(12, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_7.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel matriculeFiscale_lab = new JLabel("Matricule Fiscale");
		panel.add(matriculeFiscale_lab);
		
		matriculeFiscale_txt = new JTextField();
		matriculeFiscale_txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Statement stmt;
				try {
					stmt = (Statement)connection.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT * FROM entreprise");
					ArrayList<String> test= new ArrayList<String>();
					
					while(rs.next()) {
						test.add(rs.getString("matriculeFiscale"));
					}
					if(test.contains(matriculeFiscale_txt.getText())) {
						valider_bn.setEnabled(false);
						txtThisMatriculeIs.setVisible(true);
					} 
					else if(matriculeFiscale_txt.getText().isEmpty()){
						valider_bn.setEnabled(false);
						txtThisMatriculeIs.setVisible(false);
					}
					else {
						valider_bn.setEnabled(true);
						txtThisMatriculeIs.setVisible(false);
					}
				} catch (SQLException t) {
					// TODO Auto-generated catch block
					t.printStackTrace();
				}
			}
		});
		matriculeFiscale_txt.setColumns(10);
		panel.add(matriculeFiscale_txt);
		
		JPanel panel_1 = new JPanel();
		panel_7.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel raisonSociale_lab = new JLabel("Raison Fiscale");
		panel_1.add(raisonSociale_lab);
		
		raisonSociale_txt = new JTextField();
		raisonSociale_txt.setColumns(10);
		panel_1.add(raisonSociale_txt);
		
		JPanel panel_2 = new JPanel();
		panel_7.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel type_lab = new JLabel("Type");
		panel_2.add(type_lab);
		
		type_txt = new JTextField();
		type_txt.setColumns(10);
		panel_2.add(type_txt);
		
		JPanel panel_3 = new JPanel();
		panel_7.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel description_lab = new JLabel("Description");
		panel_3.add(description_lab);
		
		description_txt = new JTextField();
		description_txt.setColumns(10);
		panel_3.add(description_txt);
		
		JPanel panel_4 = new JPanel();
		panel_7.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel comptesBancaire_lab = new JLabel("Compte Bancaire ( RIB )");
		panel_4.add(comptesBancaire_lab);
		
		comptesBancaire_txt = new JTextField();
		comptesBancaire_txt.setColumns(10);
		panel_4.add(comptesBancaire_txt);
		
		JPanel panel_5_1 = new JPanel();
		panel_7.add(panel_5_1);
		panel_5_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel adresse_lab = new JLabel("Adresse");
		panel_5_1.add(adresse_lab);
		
		adresse_txt = new JTextField();
		adresse_txt.setColumns(10);
		panel_5_1.add(adresse_txt);
		
		JPanel panel_5 = new JPanel();
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel telephoneFixe_lab = new JLabel("Telephone fixe");
		panel_5.add(telephoneFixe_lab);
		
		telephoneFixe_txt = new JTextField();
		telephoneFixe_txt.setColumns(10);
		panel_5.add(telephoneFixe_txt);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel telephoneMobile_lab = new JLabel("Telephone mobile");
		panel_8.add(telephoneMobile_lab);
		
		telephoneMobile_txt = new JTextField();
		panel_8.add(telephoneMobile_txt);
		telephoneMobile_txt.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel fax_lab = new JLabel("Fax");
		panel_9.add(fax_lab);
		
		fax_txt = new JTextField();
		panel_9.add(fax_txt);
		fax_txt.setColumns(10);
		
		JPanel panel_9_1 = new JPanel();
		panel_7.add(panel_9_1);
		panel_9_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel email_lab = new JLabel("Email");
		panel_9_1.add(email_lab);
		
		email_txt = new JTextField();
		panel_9_1.add(email_txt);
		email_txt.setColumns(10);
		
		JPanel panel_9_2 = new JPanel();
		panel_7.add(panel_9_2);
		panel_9_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel website_lab_1 = new JLabel("Website");
		panel_9_2.add(website_lab_1);
		
		website_txt = new JTextField();
		panel_9_2.add(website_txt);
		website_txt.setColumns(10);
		
		JPanel panel_9_3 = new JPanel();
		panel_7.add(panel_9_3);
		panel_9_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel etatFiscale_lab_1 = new JLabel("Etat fiscale");
		panel_9_3.add(etatFiscale_lab_1);
		
		etatFiscale_txt = new JTextField();
		etatFiscale_txt.setColumns(10);
		panel_9_3.add(etatFiscale_txt);
		
		txtThisMatriculeIs = new JTextField();
		txtThisMatriculeIs.setVisible(false);
		txtThisMatriculeIs.setForeground(Color.RED);
		txtThisMatriculeIs.setHorizontalAlignment(SwingConstants.CENTER);
		txtThisMatriculeIs.setText("This Matricule is used !");
		txtThisMatriculeIs.setBounds(111, 246, 215, 33);
		contentPane.add(txtThisMatriculeIs);
		txtThisMatriculeIs.setColumns(10);
		
	}

	public static AddEntreprise getCurrentAddEntrepriseForm(String _typeOperation)
	{
		if(currentAddEntrepriseForm == null)
			currentAddEntrepriseForm = new AddEntreprise(_typeOperation);
		return currentAddEntrepriseForm;
	}
}
