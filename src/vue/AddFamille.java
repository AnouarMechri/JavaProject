package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import metier.FamilleProduit;
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
import java.sql.Connection;
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

public class AddFamille extends JFrame {

	private JPanel contentPane;
	private JTextField codeFamille_txt;
	private JTextField designationFamille_txt;
	private static AddFamille currentAddFamilleForm = null;
	private boolean isClosed = false;
	private String typeOperation = GlobalVariables.ADDVALUE; // "Add" ou "Update"
	private JTextField textField;
	private JTextField textField_1;
	static Connection connection;
	private JTextField txtThisCodeFamily;
	
	
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
					AddFamille frame = new AddFamille(GlobalVariables.ADDVALUE);
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
	public AddFamille(String _typeOperation) {
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
					AddFamille.getCurrentAddFamilleForm(typeOperation).setVisible(true);
			}
		});
		setTitle("Add FamilleProduit");
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
				int codeFamille = Integer.parseInt(codeFamille_txt.getText());
				String designationFamille = designationFamille_txt.getText();
			
				//verifier la validité des informations :
				String msg = "";
				boolean verif = true;
				
				if(!verif)
				{
					JOptionPane.showMessageDialog(null, "Verify values");
					return;
				}
								
				FamilleProduit newFamille = new FamilleProduit(codeFamille, designationFamille);
				
					if(typeOperation == GlobalVariables.ADDVALUE && newFamille.ajouterFamilleProduit())
						JOptionPane.showMessageDialog(null, "Family successfully added");
					
					
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
				codeFamille_txt.setText("");
				designationFamille_txt.setText("");
				}
		});
		panel_6.add(annuler_bn);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(5, 5, 424, 40);
		panel_7.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_7.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel codeFamille_lab = new JLabel("Code famille");
		panel.add(codeFamille_lab);
		
		codeFamille_txt = new JTextField();
		codeFamille_txt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Statement stmt;
				try {
					stmt = (Statement)connection.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT * FROM familleproduit");
					ArrayList<String> test= new ArrayList<String>();
					
					while(rs.next()) {
						test.add(rs.getString("codeFamille"));
					}
					if(test.contains(codeFamille_txt.getText())) {
						valider_bn.setEnabled(false);
						txtThisCodeFamily.setVisible(true);
					} 
					else if(codeFamille_txt.getText().isEmpty()){
						valider_bn.setEnabled(false);
						txtThisCodeFamily.setVisible(false);
					}
					else {
						valider_bn.setEnabled(true);
						txtThisCodeFamily.setVisible(false);
					}
				} catch (SQLException t) {
					// TODO Auto-generated catch block
					t.printStackTrace();
				}
			}
		});
		codeFamille_txt.setColumns(10);
		panel.add(codeFamille_txt);
		
		JPanel panel_1 = new JPanel();
		panel_7.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel designationFamille_lab = new JLabel("Designation");
		panel_1.add(designationFamille_lab);
		
		designationFamille_txt = new JTextField();
		designationFamille_txt.setColumns(10);
		panel_1.add(designationFamille_txt);
		
		txtThisCodeFamily = new JTextField();
		txtThisCodeFamily.setVisible(false);
		txtThisCodeFamily.setForeground(Color.RED);
		txtThisCodeFamily.setHorizontalAlignment(SwingConstants.CENTER);
		txtThisCodeFamily.setText("This code family is used !");
		txtThisCodeFamily.setBounds(129, 61, 176, 27);
		contentPane.add(txtThisCodeFamily);
		txtThisCodeFamily.setColumns(10);
		
	}

	public static AddFamille getCurrentAddFamilleForm(String _typeOperation)
	{
		if(currentAddFamilleForm == null)
			currentAddFamilleForm = new AddFamille(_typeOperation);
		return currentAddFamilleForm;
	}
}
