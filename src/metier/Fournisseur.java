package metier;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataBaseConnection;
import database.DataBaseTableNames;

public class Fournisseur {
	// Les caractéristiques:
	private String codeFournisseur;
	private String matriculeFiscale;
	private String raisonSociale;
	private String adresse;
	private int telephoneFixe;
	private int telephoneMobile;
	private int fax;
	private String email;
	private String website;
	private String etatFiscale; // 1 ou 2 ou 3 (assujiti tva + fodec)
	private int comptesBancaire;
	
	
	
	public Fournisseur(String codeFournisseur, String matriculeFiscale, String raisonSociale, String adresse,
			int telephoneFixe, int telephoneMobile, int fax, String email, String website, String etatFiscale, int comptesBancaire) {
		super();
		this.codeFournisseur = codeFournisseur;
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

	public String getCodeFournisseur() {
		return codeFournisseur;
	}
	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
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

	public boolean ajouterFournisseur() throws HeadlessException, SQLException
	{	
		String req = "insert into " + DataBaseTableNames.FOURNISSEUR + " values ("+
		"'" + codeFournisseur + "' " + "," + 
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
	
    public boolean modifierFournisseur(Fournisseur F) throws HeadlessException, SQLException
    {
        String req = "UPDATE fournisseur SET "                     + 
                "codeFournisseur='"+F.codeFournisseur+"',"                 +
                "matriculeFiscale='"+F.matriculeFiscale+"',"               +
                "raisonSociale='"+F.raisonSociale+"',"             +
                "adresse='"+F.adresse+"',"                +
                "telephoneFixe="+F.telephoneFixe+","          +
                "telephoneMobile="+F.telephoneMobile+","                         +
                "fax="+F.fax+","             +
                "email='"+F.email+"'," +
                "website='"+F.website+"',"                                 +
                "etatFiscale='"+F.etatFiscale+"',"                                 +
                "comptesBancaire="+F.comptesBancaire+
                "WHERE codeFournisseur='"+F.codeFournisseur+"'";

        if(DataBaseConnection.executionUpdate(req) >0)
            return true;
        return false;

    }
	
	public static Fournisseur getFournisseur(String codeFournisseur) throws SQLException
	{
		Fournisseur currentFournisseur = null;
		String req = "select * from Fournisseur where codeFournisseur = '" + codeFournisseur + "'";
		ResultSet rs = DataBaseConnection.executionQuery(req);
		while(rs.next())
        {        	
			currentFournisseur = new Fournisseur(rs.getString("codeFournisseur"),rs.getString("matriculeFiscale"),rs.getString("raisonSociale"),rs.getString("adresse"),rs.getInt("telephoneFixe"),rs.getInt("telephoneMobile"),rs.getInt("fax"),rs.getString("email"),rs.getString("website"),rs.getString("etatFiscale"),rs.getInt("comptesBancaire"));
        }
		return currentFournisseur;
	}
	
	
}
