package vue;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import database.DataBaseConnection;
import metier.GlobalVariables;
import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import metier.Produit;
import java.awt.Color;
import java.awt.SystemColor;
public class AcceuilForm extends JFrame {

	public JPanel contentPane;
	public String entityName;
	public JPanel panel_operations;
	static Connection connection;
	
	public String getEntityName() {
		return entityName;
	}
	public void connecter() throws ClassNotFoundException{

		
        Class.forName("com.mysql.jdbc.Driver");
        try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_commercial","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	private static AcceuilForm CurrentAcceuilForm;
	private JTable table;
	private JTextField search_txt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataBaseConnection.initialiser();
					DataBaseConnection.connecter();
					AcceuilForm frame = new AcceuilForm();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public AcceuilForm() throws ClassNotFoundException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					DataBaseConnection.deconnecter();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Problème de fermeture de la source de données!!");
				}
			}
		});
		connecter();
		setTitle("Projet-Anouar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 631);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu StructureMenu = new JMenu("Structure");
		StructureMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(StructureMenu);
		
		JMenuItem ProduitsMenuItem = new JMenuItem("Produits");
		ProduitsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entityName = "Produits";
				panel_operations.setEnabled(true);
			}
		});
		
		JMenuItem entrepriseMenuItem = new JMenuItem("Entreprise");
		entrepriseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entityName = "Entreprise";
				panel_operations.setEnabled(true);			}
		});
		StructureMenu.add(entrepriseMenuItem);
		StructureMenu.add(ProduitsMenuItem);
		
		JMenuItem FamilleProduitsMenuItem = new JMenuItem("Famille Produits");
		FamilleProduitsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entityName = "Famille Produits";
				panel_operations.setEnabled(true);	
			}
		});
		StructureMenu.add(FamilleProduitsMenuItem);
		
		JMenuItem ClientsMenuItem = new JMenuItem("Clients");
		ClientsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entityName = "Clients";
				panel_operations.setEnabled(true);	
			}
		});
		StructureMenu.add(ClientsMenuItem);
		
		JMenuItem FournisseursMenuItem = new JMenuItem("Fournisseurs");
		FournisseursMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entityName = "Fournisseurs";
				panel_operations.setEnabled(true);
			}
		});
		StructureMenu.add(FournisseursMenuItem);
		
		JMenu AchatsMenu = new JMenu("Achats");
		menuBar.add(AchatsMenu);
		
		JMenu VentesMenu = new JMenu("Ventes");
		menuBar.add(VentesMenu);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_operations = new JPanel();
		panel_operations.setBounds(400, 5, 159, 126);
		panel_operations.setEnabled(false);
		contentPane.add(panel_operations);
		panel_operations.setLayout(new GridLayout(0, 1, 0, 0));
		
		

		
		
		
		JButton update_bn = new JButton("Modifier");
		update_bn.setForeground(new Color(0, 0, 0));
		update_bn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(entityName == null)
				{	JOptionPane.showMessageDialog(null, "You have to choose a structure");
					return;
				}
				panel_operations.repaint();
				
				switch (entityName)
				{
				case "Produits" : 

					int RowIndexSelected=table.getSelectedRow();
					String reference=table.getValueAt(RowIndexSelected, 0).toString();
					String designation=table.getValueAt(RowIndexSelected, 1).toString();
					String uniteMesure=table.getValueAt(RowIndexSelected, 2).toString();
					String fournisseur=table.getValueAt(RowIndexSelected, 3).toString();
					String familleProduit=table.getValueAt(RowIndexSelected, 4).toString();
					String stock=table.getValueAt(RowIndexSelected, 5).toString();
					String stockMinimal=table.getValueAt(RowIndexSelected, 6).toString();
					String prixUnitaireHTaxe=table.getValueAt(RowIndexSelected, 7).toString();
					String tva=table.getValueAt(RowIndexSelected, 8).toString();
					

					String Opdesignation=JOptionPane.showInputDialog(null,"Enter designation:",designation);
					String OpuniteMesure=JOptionPane.showInputDialog(null,"Enter uniteMesure:",uniteMesure);
					String Opstock=JOptionPane.showInputDialog(null,"Enter stock:",stock);
					String OpstockMinimal=JOptionPane.showInputDialog(null,"Enter stockMinimal:",stockMinimal);
					String OpprixUnitaireHTaxe=JOptionPane.showInputDialog(null,"Enter prixUnitaireHTaxe:",prixUnitaireHTaxe);
					String Optva=JOptionPane.showInputDialog(null,"Enter tva:",tva);
					

						table.setValueAt(Opdesignation, RowIndexSelected, 1);
						table.setValueAt(OpuniteMesure, RowIndexSelected, 2);
						table.setValueAt(Integer.parseInt(Opstock), RowIndexSelected, 5);
						table.setValueAt(Integer.parseInt(OpstockMinimal), RowIndexSelected, 6);
						table.setValueAt(Integer.parseInt(OpprixUnitaireHTaxe), RowIndexSelected, 7);
						table.setValueAt(Integer.parseInt(Optva), RowIndexSelected, 8);
						
						String newdesignation=table.getValueAt(RowIndexSelected, 1).toString();
						String newuniteMesure=table.getValueAt(RowIndexSelected, 2).toString();
						String newstock=table.getValueAt(RowIndexSelected, 5).toString();
						String newstockMinimal=table.getValueAt(RowIndexSelected, 6).toString();
						String newprixUnitaireHTaxe=table.getValueAt(RowIndexSelected, 7).toString();
						String newtva=table.getValueAt(RowIndexSelected, 8).toString();
					
						
						try {
							Statement stmt = (Statement) connection.createStatement();
							stmt.executeUpdate("UPDATE `produit` SET `reference`='"+reference+"',`designation`='"+newdesignation+"',`uniteMesure`='"+newuniteMesure+"',`fournisseur`='"+fournisseur+"',`familleProduit`="+familleProduit+",`stock`="+newstock+",`stockMinimal`="+newstockMinimal+",`prixUnitaireHTaxe`="+newprixUnitaireHTaxe+",`tva`="+newtva+" WHERE reference='"+reference+"'");
						JOptionPane.showMessageDialog(null, "Update Success");
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						break;

				case "Fournisseurs" : 
					int RowIndexSelected1=table.getSelectedRow();
					String codeFournisseur=table.getValueAt(RowIndexSelected1, 0).toString();
					String matriculeFiscale=table.getValueAt(RowIndexSelected1, 1).toString();
					String raisonSociale=table.getValueAt(RowIndexSelected1, 2).toString();
					String adresse=table.getValueAt(RowIndexSelected1, 3).toString();
					
					String telephoneFixe=table.getValueAt(RowIndexSelected1, 4).toString();
					String telephoneMobile=table.getValueAt(RowIndexSelected1, 5).toString();
					String fax=table.getValueAt(RowIndexSelected1, 6).toString();
					String email=table.getValueAt(RowIndexSelected1, 7).toString();
					String website=table.getValueAt(RowIndexSelected1, 8).toString();
					String etatFiscale=table.getValueAt(RowIndexSelected1, 9).toString();
					String comptesBancaire=table.getValueAt(RowIndexSelected1, 10).toString();
					

					String OpmatriculeFiscale=JOptionPane.showInputDialog(null,"Enter matriculeFiscale:",matriculeFiscale);
					String OpraisonSociale=JOptionPane.showInputDialog(null,"Enter raisonSociale:",raisonSociale);
					String Opadresse=JOptionPane.showInputDialog(null,"Enter adresse:",adresse);					
					String OptelephoneFixe=JOptionPane.showInputDialog(null,"Enter telephoneFixe:",telephoneFixe);
					String OptelephoneMobile=JOptionPane.showInputDialog(null,"Enter telephoneMobile:",telephoneMobile);
					String Opfax=JOptionPane.showInputDialog(null,"Enter fax:",fax);
					String Opemail=JOptionPane.showInputDialog(null,"Enter email:",email);
					String Opwebsite=JOptionPane.showInputDialog(null,"Enter website:",website);
					String OpetatFiscale=JOptionPane.showInputDialog(null,"Enter etatFiscale:",etatFiscale);
					String OpcomptesBancaire=JOptionPane.showInputDialog(null,"Enter comptesBancaire:",comptesBancaire);
					

						table.setValueAt(OpmatriculeFiscale, RowIndexSelected1, 1);
						table.setValueAt(OpraisonSociale, RowIndexSelected1, 2);
						table.setValueAt(Opadresse, RowIndexSelected1, 3);
						
						table.setValueAt(Integer.parseInt(OptelephoneFixe), RowIndexSelected1, 4);
						table.setValueAt(Integer.parseInt(OptelephoneMobile), RowIndexSelected1, 5);
						table.setValueAt(Integer.parseInt(Opfax), RowIndexSelected1, 6);
						table.setValueAt(Opemail, RowIndexSelected1, 7);
						table.setValueAt(Opwebsite, RowIndexSelected1, 8);
						table.setValueAt(OpetatFiscale, RowIndexSelected1, 9);
						table.setValueAt(Integer.parseInt(OpcomptesBancaire), RowIndexSelected1, 10);
						
						String newmatriculeFiscale=table.getValueAt(RowIndexSelected1, 1).toString();
						String newraisonSociale=table.getValueAt(RowIndexSelected1, 2).toString();
						String newadresse=table.getValueAt(RowIndexSelected1, 3).toString();
						
						String newtelephoneFixe=table.getValueAt(RowIndexSelected1, 4).toString();
						String newtelephoneMobile=table.getValueAt(RowIndexSelected1, 5).toString();
						String newfax=table.getValueAt(RowIndexSelected1, 6).toString();
						String newemail=table.getValueAt(RowIndexSelected1, 7).toString();
						String newwebsite=table.getValueAt(RowIndexSelected1,8).toString();
						String newetatFiscale=table.getValueAt(RowIndexSelected1, 9).toString();
						String newcomptesBancaire=table.getValueAt(RowIndexSelected1, 10).toString();
					
						
						try {
							Statement stmt = (Statement) connection.createStatement();
							stmt.executeUpdate("UPDATE `fournisseur` SET `codeFournisseur`='"+codeFournisseur+"',`matriculeFiscale`='"+newmatriculeFiscale+"',`raisonSociale`='"+newraisonSociale+"',`adresse`='"+newadresse+"',`telephoneFixe`="+newtelephoneFixe+",`telephoneMobile`="+newtelephoneMobile+",`fax`="+newfax+",`email`='"+newemail+"',`website`='"+newwebsite+"',`etatFiscale`='"+newetatFiscale+"',`comptesBancaire`="+newcomptesBancaire+" WHERE codeFournisseur='"+codeFournisseur+"'");
						
						JOptionPane.showMessageDialog(null, "Update Success");
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						break;
				
				case "Famille Produits" : 
					
					int RowIndexSelected3=table.getSelectedRow();
						String codeFamille=table.getValueAt(RowIndexSelected3, 0).toString();
						String designationFamille=table.getValueAt(RowIndexSelected3, 1).toString();
				

						String opdesignationFamille=JOptionPane.showInputDialog(null,"Enter designationFamille:",designationFamille);

						table.setValueAt(opdesignationFamille, RowIndexSelected3, 1);
					
						String newdesignationFamille=table.getValueAt(RowIndexSelected3, 1).toString();
				
					
					try {
						Statement stmt = (Statement) connection.createStatement();
						stmt.executeUpdate("UPDATE `familleproduit` SET `codeFamille`="+codeFamille+",`designationFamille`='"+newdesignationFamille+"' WHERE codeFamille='"+codeFamille+"'");
					
					JOptionPane.showMessageDialog(null, "Update Success");
					} catch (SQLException e1) {
					
						e1.printStackTrace();
					}
					break;
				
				case "Entreprise" : 
					int RowIndexSelected4=table.getSelectedRow();
					String matriculeFiscale2=table.getValueAt(RowIndexSelected4, 0).toString();
					String raisonSociale2=table.getValueAt(RowIndexSelected4, 1).toString();
					String type=table.getValueAt(RowIndexSelected4, 2).toString();
					String description=table.getValueAt(RowIndexSelected4, 3).toString();
					String comptesBancaire2=table.getValueAt(RowIndexSelected4, 4).toString();
					String adresse2=table.getValueAt(RowIndexSelected4, 5).toString();
					String telephoneFixe2=table.getValueAt(RowIndexSelected4, 6).toString();
					String telephoneMobile2=table.getValueAt(RowIndexSelected4, 7).toString();
					String fax2=table.getValueAt(RowIndexSelected4, 8).toString();
					String email2=table.getValueAt(RowIndexSelected4, 9).toString();
					String website2=table.getValueAt(RowIndexSelected4, 10).toString();
					String etatFiscale2=table.getValueAt(RowIndexSelected4, 11).toString();
					
					
					String OpmatriculeFiscale2=JOptionPane.showInputDialog(null,"Enter matriculeFiscale:",matriculeFiscale2);
					String OpraisonSociale2=JOptionPane.showInputDialog(null,"Enter raisonSociale:",raisonSociale2);
					String Optype=JOptionPane.showInputDialog(null,"Enter type:",type);
					String Opdescription=JOptionPane.showInputDialog(null,"Enter description:",description);
					String OpcomptesBancaire2=JOptionPane.showInputDialog(null,"Enter comptesBancaire:",comptesBancaire2);
					String Opadresse2=JOptionPane.showInputDialog(null,"Enter adresse:",adresse2);
					String OptelephoneFixe2=JOptionPane.showInputDialog(null,"Enter telephoneFixe:",telephoneFixe2);
					String OptelephoneMobile2=JOptionPane.showInputDialog(null,"Enter telephoneMobile:",telephoneMobile2);
					String Opfax2=JOptionPane.showInputDialog(null,"Enter tva:",fax2);
					String Opemail2=JOptionPane.showInputDialog(null,"Enter email:",email2);
					String Opwebsite2=JOptionPane.showInputDialog(null,"Enter website:",website2);
					String OpetatFiscale2=JOptionPane.showInputDialog(null,"Enter etatFiscale:",etatFiscale2);

						table.setValueAt(OpmatriculeFiscale2, RowIndexSelected4, 0);
						table.setValueAt(OpraisonSociale2, RowIndexSelected4, 1);
						table.setValueAt(Optype, RowIndexSelected4, 2);
						table.setValueAt(Opdescription, RowIndexSelected4, 3);
						table.setValueAt(Integer.parseInt(OpcomptesBancaire2), RowIndexSelected4, 4);
						table.setValueAt(Opadresse2, RowIndexSelected4, 5);
						table.setValueAt(Integer.parseInt(OptelephoneFixe2), RowIndexSelected4, 6);
						table.setValueAt(Integer.parseInt(OptelephoneMobile2), RowIndexSelected4, 7);
						table.setValueAt(Integer.parseInt(Opfax2), RowIndexSelected4, 8);
						table.setValueAt(Opemail2, RowIndexSelected4, 9);
						table.setValueAt(Opwebsite2, RowIndexSelected4, 10);
						table.setValueAt(OpetatFiscale2, RowIndexSelected4, 11);
						
						
						String newmatriculeFiscale2=table.getValueAt(RowIndexSelected4, 0).toString();
						String newraisonSociale2=table.getValueAt(RowIndexSelected4, 1).toString();
						String newtype=table.getValueAt(RowIndexSelected4, 2).toString();
						String newdescription=table.getValueAt(RowIndexSelected4, 3).toString();
						String newcomptesBancaire2=table.getValueAt(RowIndexSelected4, 4).toString();
						String newadresse2=table.getValueAt(RowIndexSelected4, 5).toString();
						String newtelephoneFixe2=table.getValueAt(RowIndexSelected4, 6).toString();
						String newtelephoneMobile2=table.getValueAt(RowIndexSelected4, 7).toString();
						String newfax2=table.getValueAt(RowIndexSelected4, 8).toString();
						String newemail2=table.getValueAt(RowIndexSelected4, 9).toString();
						String newwebsite2=table.getValueAt(RowIndexSelected4, 10).toString();
						String newetatFiscale2=table.getValueAt(RowIndexSelected4, 11).toString();
					
						
						try {
							Statement stmt = (Statement) connection.createStatement();
							stmt.executeUpdate("UPDATE `entreprise` SET `matriculeFiscale`='"+newmatriculeFiscale2+"',`raisonSociale`='"+newraisonSociale2+"',`type`='"+newtype+"',`description`='"+newdescription+"',`comptesBancaire`="+newcomptesBancaire2+",`adresse`='"+newadresse2+"',`telephoneFixe`="+newtelephoneFixe2+",`telephoneMobile`="+newtelephoneMobile2+",`fax`="+newfax2+",`email`='"+newemail2+"',`website`='"+newwebsite2+"',`etatFiscale`='"+newetatFiscale2+"' WHERE matriculeFiscale='"+matriculeFiscale2+"'");
						
						JOptionPane.showMessageDialog(null, "Update Success");
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						break;
				case "Clients" :
					int RowIndexSelected2=table.getSelectedRow();
					String codeClient=table.getValueAt(RowIndexSelected2, 0).toString();
					String matriculeFiscale1=table.getValueAt(RowIndexSelected2, 1).toString();
					String raisonSociale1=table.getValueAt(RowIndexSelected2, 2).toString();
					String adresse1=table.getValueAt(RowIndexSelected2, 3).toString();
					
					String telephoneFixe1=table.getValueAt(RowIndexSelected2, 4).toString();
					String telephoneMobile1=table.getValueAt(RowIndexSelected2, 5).toString();
					String fax1=table.getValueAt(RowIndexSelected2, 6).toString();
					String email1=table.getValueAt(RowIndexSelected2, 7).toString();
					String website1=table.getValueAt(RowIndexSelected2, 8).toString();
					String etatFiscale1=table.getValueAt(RowIndexSelected2, 9).toString();
					String comptesBancaire1=table.getValueAt(RowIndexSelected2, 10).toString();
					

					String OpcodeClient=JOptionPane.showInputDialog(null,"Enter codeFournisseur:",codeClient);
					String OpmatriculeFiscale1=JOptionPane.showInputDialog(null,"Enter matriculeFiscale:",matriculeFiscale1);
					String OpraisonSociale1=JOptionPane.showInputDialog(null,"Enter raisonSociale:",raisonSociale1);
					String Opadresse1=JOptionPane.showInputDialog(null,"Enter adresse:",adresse1);
					
					String OptelephoneFixe1=JOptionPane.showInputDialog(null,"Enter telephoneFixe:",telephoneFixe1);
					String OptelephoneMobile1=JOptionPane.showInputDialog(null,"Enter telephoneMobile:",telephoneMobile1);
					String Opfax1=JOptionPane.showInputDialog(null,"Enter tva:",fax1);
					String Opemail1=JOptionPane.showInputDialog(null,"Enter email:",email1);
					String Opwebsite1=JOptionPane.showInputDialog(null,"Enter website:",website1);
					String OpetatFiscale1=JOptionPane.showInputDialog(null,"Enter etatFiscale:",etatFiscale1);
					String OpcomptesBancaire1=JOptionPane.showInputDialog(null,"Enter comptesBancaire:",comptesBancaire1);
					

						table.setValueAt(OpcodeClient, RowIndexSelected2, 0);
						table.setValueAt(OpmatriculeFiscale1, RowIndexSelected2, 1);
						table.setValueAt(OpraisonSociale1, RowIndexSelected2, 2);
						table.setValueAt(Opadresse1, RowIndexSelected2, 3);
						
						table.setValueAt(Integer.parseInt(OptelephoneFixe1), RowIndexSelected2, 4);
						table.setValueAt(Integer.parseInt(OptelephoneMobile1), RowIndexSelected2, 5);
						table.setValueAt(Integer.parseInt(Opfax1), RowIndexSelected2, 6);
						table.setValueAt(Opemail1, RowIndexSelected2, 7);
						table.setValueAt(Opwebsite1, RowIndexSelected2, 8);
						table.setValueAt(OpetatFiscale1, RowIndexSelected2, 9);
						table.setValueAt(Integer.parseInt(OpcomptesBancaire1), RowIndexSelected2, 10);
						
						String newcodeClient=table.getValueAt(RowIndexSelected2, 0).toString();
						String newmatriculeFiscale1=table.getValueAt(RowIndexSelected2, 1).toString();
						String newraisonSociale1=table.getValueAt(RowIndexSelected2, 2).toString();
						String newadresse1=table.getValueAt(RowIndexSelected2, 3).toString();
						
						String newtelephoneFixe1=table.getValueAt(RowIndexSelected2, 4).toString();
						String newtelephoneMobile1=table.getValueAt(RowIndexSelected2, 5).toString();
						String newfax1=table.getValueAt(RowIndexSelected2, 6).toString();
						String newemail1=table.getValueAt(RowIndexSelected2, 7).toString();
						String newwebsite1=table.getValueAt(RowIndexSelected2, 8).toString();
						String newetatFiscale1=table.getValueAt(RowIndexSelected2, 9).toString();
						String newcomptesBancaire1=table.getValueAt(RowIndexSelected2, 10).toString();
					
						
						try {
							Statement stmt = (Statement) connection.createStatement();
							stmt.executeUpdate("UPDATE `client` SET `codeClient`='"+newcodeClient+"',`matriculeFiscale`='"+newmatriculeFiscale1+"',`raisonSociale`='"+newraisonSociale1+"',`adresse`='"+newadresse1+"',`telephoneFixe`="+newtelephoneFixe1+",`telephoneMobile`="+newtelephoneMobile1+",`fax`="+newfax1+",`email`='"+newemail1+"',`website`='"+newwebsite1+"',`etatFiscale`='"+newetatFiscale1+"',`comptesBancaire`="+newcomptesBancaire1+" WHERE codeClient='"+codeClient+"'");
						
						JOptionPane.showMessageDialog(null, "Update Success");
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						break;
				default : JOptionPane.showMessageDialog(null, "Structure can't be implemented");
				}	
			}
		});
		
		JButton add_bn = new JButton("Ajouter");
		add_bn.setForeground(new Color(0, 0, 0));
		panel_operations.add(add_bn);
		add_bn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(entityName == null)
				{	JOptionPane.showMessageDialog(null, "You have to choose a structure");
					return;
				}
				panel_operations.repaint();
				
				switch (entityName)
				{
				case "Produits" : AddProduit addForm = 
						AddProduit.getCurrentAddProduitForm(GlobalVariables.ADDVALUE);
				//addForm.setModalExclusionType(null);
				addForm.setVisible(true);break;
				
				case "Fournisseurs" : AddFournisseur addForm1 = 
						AddFournisseur.getCurrentAddFournisseurForm(GlobalVariables.ADDVALUE);
				addForm1.setVisible(true);break;
				
				case "Famille Produits" : AddFamille addForm2 = 
						AddFamille.getCurrentAddFamilleForm(GlobalVariables.ADDVALUE);
				addForm2.setVisible(true);break;
				
				case "Entreprise" : AddEntreprise addForm3 = 
						AddEntreprise.getCurrentAddEntrepriseForm(GlobalVariables.ADDVALUE);
				addForm3.setVisible(true);break;
				
				case "Clients" : AddClient addForm4 = 
						AddClient.getCurrentAddClientForm(GlobalVariables.ADDVALUE);
				addForm4.setVisible(true);break;
				
				default : JOptionPane.showMessageDialog(null, "Structure can't be implemented");
				}
			}
		});
		panel_operations.add(update_bn);

		JButton delete_bn = new JButton("Supprimer");
		delete_bn.setForeground(new Color(0, 0, 0));
		delete_bn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				if(entityName == null)
				{	JOptionPane.showMessageDialog(null, "You have to choose a structure");
					return;
				}
				panel_operations.repaint();
				
				switch (entityName)
				{
					case "Produits" : 
						try {
							int RowIndexSelected=table.getSelectedRow();
							String R=table.getValueAt(RowIndexSelected, 0).toString();
							//String codereference=JOptionPane.showInputDialog("Put the reference code");
							stmt = (Statement) connection.createStatement();
							ResultSet	resultset= stmt.executeQuery("SELECT * FROM produit WHERE reference='"+R+"'");
							if(resultset.next()) {
								stmt.executeUpdate("DELETE FROM `produit` WHERE reference='"+R+"'");
								JOptionPane.showMessageDialog(null, "The product you selected has been successfully deleted ");
							}else {
								JOptionPane.showMessageDialog(null, "Error");
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							}
					break;
					
					case "Fournisseurs" : 
						try {
							int RowIndexSelected=table.getSelectedRow();
							String CF=table.getValueAt(RowIndexSelected, 0).toString();
							//String codeFournisseur=JOptionPane.showInputDialog("Put the provider code");
							stmt = (Statement) connection.createStatement();
							ResultSet	resultset= stmt.executeQuery("SELECT * FROM fournisseur WHERE codeFournisseur='"+CF+"'");
							if(resultset.next()) {
								stmt.executeUpdate("DELETE FROM `fournisseur` WHERE codeFournisseur='"+CF+"'");
								JOptionPane.showMessageDialog(null, "The provider you selected has been successfully deleted ");
							}else {
								JOptionPane.showMessageDialog(null, "Error");
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							}
					break;
						
					case "Famille Produits" : 
						try {
							int RowIndexSelected=table.getSelectedRow();
							String CFP=table.getValueAt(RowIndexSelected, 0).toString();
							//String codeFamille=JOptionPane.showInputDialog("Put the family code");
							stmt = (Statement) connection.createStatement();
							ResultSet	resultset= stmt.executeQuery("SELECT * FROM familleproduit WHERE codeFamille="+CFP+"");
							if(resultset.next()) {
								stmt.executeUpdate("DELETE FROM `familleproduit` WHERE codeFamille='"+CFP+"'");
								JOptionPane.showMessageDialog(null, "The family you selected has been successfully deleted ");
							}else {
								JOptionPane.showMessageDialog(null, "Error");
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							}
					break;
						
					case "Entreprise" : 
						try {
							int RowIndexSelected=table.getSelectedRow();
							String MF=table.getValueAt(RowIndexSelected, 0).toString();
							//String matriculeFiscale=JOptionPane.showInputDialog("Put the entreprise code");
							stmt = (Statement) connection.createStatement();
							ResultSet	resultset= stmt.executeQuery("SELECT * FROM entreprise WHERE matriculeFiscale='"+MF+"'");
							if(resultset.next()) {
								stmt.executeUpdate("DELETE FROM `entreprise` WHERE matriculeFiscale='"+MF+"'");
								JOptionPane.showMessageDialog(null, "The entreprise you selected has been successfully deleted ");
							}else {
								JOptionPane.showMessageDialog(null, "Error");
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							}
					break;
					
					case "Clients" : 
						try {
							int RowIndexSelected=table.getSelectedRow();
							String CC=table.getValueAt(RowIndexSelected, 0).toString();
							//String codeClient=JOptionPane.showInputDialog("Put the client code");
							stmt = (Statement) connection.createStatement();
							ResultSet	resultset= stmt.executeQuery("SELECT * FROM client WHERE codeClient='"+CC+"'");
							if(resultset.next()) {
								stmt.executeUpdate("DELETE FROM `client` WHERE codeClient='"+CC+"'");
								JOptionPane.showMessageDialog(null, "The client you selected has been successfully deleted ");
							}else {
								JOptionPane.showMessageDialog(null, "Error");
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							}
					break;
					
					default : JOptionPane.showMessageDialog(null, "Structure can't be implemented");
				}
				
			}
		});

		panel_operations.add(delete_bn);
		
		JButton btnDisplay = new JButton("Show me ");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt;
				if(entityName == null)
				{	JOptionPane.showMessageDialog(null, "You have to choose a structure");
					return;
				}
				panel_operations.repaint();
				switch (entityName)
				{
					case "Produits" :
						try {
							stmt = (Statement) connection.createStatement();
							ResultSet	rs= stmt.executeQuery("SELECT * FROM produit");
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Fournisseurs" :
						try {
							stmt = (Statement) connection.createStatement();
							ResultSet	rs= stmt.executeQuery("SELECT * FROM fournisseur");
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Famille Produits" :
						try {
							stmt = (Statement) connection.createStatement();
							ResultSet	rs= stmt.executeQuery("SELECT * FROM familleproduit");
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Entreprise" :
						try {
							stmt = (Statement) connection.createStatement();
							ResultSet	rs= stmt.executeQuery("SELECT * FROM entreprise");
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Clients" :
						try {
							stmt = (Statement) connection.createStatement();
							ResultSet	rs= stmt.executeQuery("SELECT * FROM client");
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					default : JOptionPane.showMessageDialog(null, "Structure can't be implemented");
				}
			}
		});
		panel_operations.add(btnDisplay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.controlHighlight);
		scrollPane.setBounds(5, 135, 889, 423);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
	}
	
	public static AcceuilForm getCurrentAcceuilForm() throws ClassNotFoundException
	{
		if(CurrentAcceuilForm == null)
			CurrentAcceuilForm = new AcceuilForm();
		return CurrentAcceuilForm;
	}
}
