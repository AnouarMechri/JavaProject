package metier;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataBaseConnection;
import database.DataBaseTableNames;

public class Client {
	// Les caractéristiques:
	private String codeClient;
	private String matriculeFiscale;
	private String raisonSociale;
	private String adresse;
	private int telephoneFixe;
	private int telephoneMobile;
	private int fax;
	private String email;
	private String website;
	private String etatFiscale;
	private int comptesBancaire;
	
	
	
	public Client(String codeClient, String matriculeFiscale, String raisonSociale, String adresse,
			int telephoneFixe, int telephoneMobile, int fax, String email, String website, String etatFiscale, int comptesBancaire) {
		super();
		this.codeClient = codeClient;
		this.matriculeFiscale = matriculeFiscale;
		this.raisonSociale = raisonSociale;
		this.adresse = adresse;
		this.telephoneFixe = telephoneFixe;
		this.telephoneMobile = telephoneMobile;
		this.fax = fax;
		this.email = email;
		this.website = website;
		this.etatFiscale = etatFiscale;
		this.comptesBancaire = comptesBancaire;
	}

	public int getComptesBancaire() {
		return comptesBancaire;
	}

	public void setComptesBancaire(int comptesBancaire) {
		this.comptesBancaire = comptesBancaire;
	}

	public String getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}
	public String getMatriculeFiscale() {
		return matriculeFiscale;
	}
	public void setMatriculeFiscale(String matriculeFiscale) {
		this.matriculeFiscale = matriculeFiscale;
	}
	
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getTelephoneFixe() {
		return telephoneFixe;
	}
	public void setTelephoneFixe(int telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}
	public int getTelephoneMobile() {
		return telephoneMobile;
	}
	public void setTelephoneMobile(int telephoneMobile) {
		this.telephoneMobile = telephoneMobile;
	}
	public int getFax() {
		return fax;
	}
	public void setFax(int fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEtatFiscale() {
		return etatFiscale;
	}

	public void setEtatFiscale(String etatFiscale) {
		this.etatFiscale = etatFiscale;
	}

	public boolean ajouterClient() throws HeadlessException, SQLException
	{	
		String req = "insert into " + DataBaseTableNames.CLIENT + " values ("+
		"'" + codeClient + "' " + "," + 
		"'" + matriculeFiscale + "' " + "," + 
		"'" +  raisonSociale + "' " + "," + 
		"'" +  adresse + "' " + "," + 
		telephoneFixe + "," + 
		telephoneMobile +  "," + 
		fax + "," + 
		"'" +  email + "' " + "," + 
		"'" +  website + "' " + "," + 
		"'" +  etatFiscale + "' " + "," +
		comptesBancaire + 
		")"; 
		
		if(DataBaseConnection.executionUpdate(req) >0)
			return true;
		return false;
		
	}
	
    public boolean modifierClient(Client C) throws HeadlessException, SQLException
    {
        String req = "UPDATE Client SET "                     + 
                "codeClient='"+C.codeClient+"',"                 +
                "matriculeFiscale='"+C.matriculeFiscale+"',"               +
                "raisonSociale='"+C.raisonSociale+"',"             +
                "adresse='"+C.adresse+"',"                +
                "telephoneFixe="+C.telephoneFixe+","          +
                "telephoneMobile="+C.telephoneMobile+","                         +
                "fax="+C.fax+","             +
                "email='"+C.email+"'," +
                "website='"+C.website+"',"                                 +
                "etatFiscale='"+C.etatFiscale+"',"                                 +
                "comptesBancaire="+C.comptesBancaire+"'"                                 +
                "WHERE codeClient='"+C.codeClient+"'";

        if(DataBaseConnection.executionUpdate(req) >0)
            return true;
        return false;

    }
	
	public static Client getClient(String codeClient) throws SQLException
	{
		Client currentClient = null;
		String req = "select * from Client where codeClient = '" + codeClient + "'";
		ResultSet rs = DataBaseConnection.executionQuery(req);
		while(rs.next())
        {        	
			currentClient = new Client(rs.getString("codeClient"),rs.getString("matriculeFiscale"),rs.getString("raisonSociale"),rs.getString("adresse"),rs.getInt("telephoneFixe"),rs.getInt("telephoneMobile"),rs.getInt("fax"),rs.getString("email"),rs.getString("website"),rs.getString("etatFiscale"),rs.getInt("comptesBancaire"));
        }
		return currentClient;
	}
}
