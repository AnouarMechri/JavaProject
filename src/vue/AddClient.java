package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import metier.Client;
import metier.GlobalVariables;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.event.WindowFocusListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import javax.swing.JTextPane;

public class AddClient extends JFrame {

	private JPanel contentPane;
	private JTextField codeClient_txt;
	private JTextField matriculeFiscale_txt;
	private JTextField raisonSociale_txt;
	private JTextField adresse_txt;
	private JTextField telephoneFixe_txt;
	private JTextField telephoneMobile_txt;
	private JTextField fax_txt;
	private JTextField email_txt;
	private JTextField website_txt;
	private JTextField etatFiscale_txt;
	private JTextField comptesBancaire_txt;
	private static AddClient currentAddClientForm = null;
	private boolean isClosed = false;
	private String typeOperation = GlobalVariables.ADDVALUE; 
	private JTextField txtThisCodeClient;
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
					AddClient frame = new AddClient(GlobalVariables.ADDVALUE);
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
	public AddClient(String _typeOperation) {
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
					AddClient.getCurrentAddClientForm(typeOperation).setVisible(true);
			}
		});
		setTitle("Add Client");
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
				String codeClient = codeClient_txt.getText();
				String matriculeFiscale = matriculeFiscale_txt.getText();
				String raisonSociale = raisonSociale_txt.getText();
				String adresse = adresse_txt.getText();
				
				int telephoneFixe = Integer.parseInt(telephoneFixe_txt.getText());
				int telephoneMobile = Integer.parseInt(telephoneMobile_txt.getText());
				int fax = Integer.parseInt(fax_txt.getText());
				String email = email_txt.getText();
				String website = website_txt.getText();
				String etatFiscale = etatFiscale_txt.getText();
				int comptesBancaire = Integer.parseInt(comptesBancaire_txt.getText());
			
			
				boolean verif = true;
				if(codeClient.isEmpty())
				{
					codeClient_txt.setForeground(Color.RED);
					verif = false;
				}
				// à completer..
				
				if(!verif)
				{
					JOptionPane.showMessageDialog(null, "Verify values");
					return;
				}
								
				Client newClient = new Client(codeClient, matriculeFiscale, 
						raisonSociale, adresse,telephoneFixe, telephoneMobile, fax,
						email, website,etatFiscale,comptesBancaire);
				
					if(typeOperation == GlobalVariables.ADDVALUE && newClient.ajouterClient())
						JOptionPane.showMessageDialog(null, "Client successfully added");
					
					
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
				codeClient_txt.setText("");
				matriculeFiscale_txt.setText("");
				raisonSociale_txt.setText("");
				adresse_txt.setText("");
				telephoneMobile_txt.setText("");	
				telephoneFixe_txt.setText("");
				fax_txt.setText("");
				email_txt.setText("");
				website_txt.setText("");
				etatFiscale_txt.setText("");
				comptesBancaire_txt.setText("");
				}
		});
		panel_6.add(annuler_bn);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(5, 5, 424, 220);
		panel_7.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_7);
		panel_7.setLayout(new GridLayout(11, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_7.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel codeClient_lab = new JLabel("Code Client");
		panel.add(codeClient_lab);
		
		codeClient_txt = new JTextField();
		codeClient_txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Statement stmt;
				try {
					stmt = (Statement)connection.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT * FROM client");
					ArrayList<String> test= new ArrayList<String>();
					
					while(rs.next()) {
						test.add(rs.getString("codeClient"));
					}
					if(test.contains(codeClient_txt.getText())) {
						valider_bn.setEnabled(false);
						txtThisCodeClient.setVisible(true);
					} 
					else if(codeClient_txt.getText().isEmpty()){
						valider_bn.setEnabled(false);
						txtThisCodeClient.setVisible(false);
					}
					else {
						valider_bn.setEnabled(true);
						txtThisCodeClient.setVisible(false);
					}
					} catch (SQLException t) {
						// TODO Auto-generated catch block
						t.printStackTrace();
					}
			}
		});
		codeClient_txt.setColumns(10);
		panel.add(codeClient_txt);
		
		JPanel panel_1 = new JPanel();
		panel_7.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel matriculeFiscale_lab = new JLabel("Matricule Fiscale");
		panel_1.add(matriculeFiscale_lab);
		
		matriculeFiscale_txt = new JTextField();
		matriculeFiscale_txt.setColumns(10);
		panel_1.add(matriculeFiscale_txt);
		
		JPanel panel_2 = new JPanel();
		panel_7.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel raisonSociale_lab = new JLabel("Raison Fiscale");
		panel_2.add(raisonSociale_lab);
		
		raisonSociale_txt = new JTextField();
		raisonSociale_txt.setColumns(10);
		panel_2.add(raisonSociale_txt);
		
		JPanel panel_3 = new JPanel();
		panel_7.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel adresse_lab = new JLabel("Adresse");
		panel_3.add(adresse_lab);
		
		adresse_txt = new JTextField();
		adresse_txt.setColumns(10);
		panel_3.add(adresse_txt);
		
		JPanel panel_4 = new JPanel();
		panel_7.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel telephoneFixe_lab = new JLabel("Telephone Fixe");
		panel_4.add(telephoneFixe_lab);
		
		telephoneFixe_txt = new JTextField();
		telephoneFixe_txt.setColumns(10);
		panel_4.add(telephoneFixe_txt);
		
		JPanel panel_5_1 = new JPanel();
		panel_7.add(panel_5_1);
		panel_5_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel telephoneMobile_lab = new JLabel("Telephone Mobile");
		panel_5_1.add(telephoneMobile_lab);
		
		telephoneMobile_txt = new JTextField();
		telephoneMobile_txt.setColumns(10);
		panel_5_1.add(telephoneMobile_txt);
		
		JPanel panel_5 = new JPanel();
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel fax_lab = new JLabel("Fax");
		panel_5.add(fax_lab);
		
		fax_txt = new JTextField();
		fax_txt.setColumns(10);
		panel_5.add(fax_txt);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel email_lab = new JLabel("Email");
		panel_8.add(email_lab);
		
		email_txt = new JTextField();
		panel_8.add(email_txt);
		email_txt.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel website_lab = new JLabel("Website");
		panel_9.add(website_lab);
		
		website_txt = new JTextField();
		panel_9.add(website_txt);
		website_txt.setColumns(10);
		
		JPanel panel_9_1 = new JPanel();
		panel_7.add(panel_9_1);
		panel_9_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel etatFiscale_lab = new JLabel("Etat Fiscale");
		panel_9_1.add(etatFiscale_lab);
		
		etatFiscale_txt = new JTextField();
		panel_9_1.add(etatFiscale_txt);
		etatFiscale_txt.setColumns(10);
		
		JPanel panel_9_2 = new JPanel();
		panel_7.add(panel_9_2);
		panel_9_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel comptesBancaire_lab_1 = new JLabel("Compte Bancaire");
		panel_9_2.add(comptesBancaire_lab_1);
		
		comptesBancaire_txt = new JTextField();
		panel_9_2.add(comptesBancaire_txt);
		comptesBancaire_txt.setColumns(10);
		
		
		txtThisCodeClient = new JTextField();
		txtThisCodeClient.setVisible(false);
		txtThisCodeClient.setBackground(Color.WHITE);
		txtThisCodeClient.setHorizontalAlignment(SwingConstants.CENTER);
		txtThisCodeClient.setForeground(Color.RED);
		txtThisCodeClient.setText("This code client is used !");
		txtThisCodeClient.setBounds(124, 236, 189, 33);
		contentPane.add(txtThisCodeClient);
		txtThisCodeClient.setColumns(10);
		
	}

	public static AddClient getCurrentAddClientForm(String _typeOperation)
	{
		if(currentAddClientForm == null)
			currentAddClientForm = new AddClient(_typeOperation);
		return currentAddClientForm;
	}
}
