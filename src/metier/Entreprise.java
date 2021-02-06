package metier;

import java.awt.HeadlessException;
import java.sql.SQLException;

import database.DataBaseConnection;
import database.DataBaseTableNames;

public class Entreprise {
	private String matriculeFiscale;
	private String raisonSociale;
	private String type;
	private String description;
	private int comptesBancaire;
	private String adresse;
	private int telephoneFixe;
	private int telephoneMobile;
	private int fax;
	private String email;
	private String website;
	private String etatFiscale;
	public Entreprise(String matriculeFiscale, String raisonSociale, String type, String description,
			int comptesBancaire, String adresse, int telephoneFixe, int telephoneMobile, int fax, String email,
			String website, String etatFiscale) {
		super();
		this.matriculeFiscale = matriculeFiscale;
		this.raisonSociale = raisonSociale;
		this.type = type;
		this.description = description;
		this.comptesBancaire = comptesBancaire;
		this.adresse = adresse;
		this.telephoneFixe = telephoneFixe;
		this.telephoneMobile = telephoneMobile;
		this.fax = fax;
		this.email = email;
		this.website = website;
		this.etatFiscale = etatFiscale;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getComptesBancaire() {
		return comptesBancaire;
	}
	public void setComptesBancaire(int comptesBancaire) {
		this.comptesBancaire = comptesBancaire;
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
	
	
	public boolean ajouterEntreprise() throws HeadlessException, SQLException
	{	
		String req = "insert into " + DataBaseTableNames.ENTREPRISE + " values ("+
		"'" + matriculeFiscale + "' " + "," + 
		"'" +  raisonSociale + "' " + "," + 
		"'" + type + "' " + "," + 
		"'" + description + "' " + "," + 
		comptesBancaire + "," + 
		"'" +  adresse + "' " + "," + 
		telephoneFixe + "," + 
		telephoneMobile +  "," + 
		fax + "," + 
		"'" +  email + "' " + "," + 
		"'" +  website + "' " + "," +
		"'" +  etatFiscale + "' " +
		")"; 
		
		if(DataBaseConnection.executionUpdate(req) >0)
			return true;
		return false;
		
	}
	
    public boolean modifierEntreprise(Entreprise E) throws HeadlessException, SQLException
    {
        String req = "UPDATE entreprise SET "                     + 
                "matriculeFiscale='"+E.matriculeFiscale+"',"                 +
                "raisonSociale='"+E.raisonSociale+"',"               +
                "type='"+E.type+"',"             +
                "description='"+E.description+"',"                +
                "comptesBancaire="+E.comptesBancaire+","          +
                "adresse='"+E.adresse+"',"                         +
                "telephoneFixe="+E.telephoneFixe+","             +
                "telephoneMobile="+E.telephoneMobile+"," +
                "fax="+E.fax+","                                 +
                "email='"+E.email+"',"                                 +
                "website='"+E.website+"',"                                 +
                "etatFiscale='"+E.etatFiscale+"'"                                 +
                "WHERE matriculeFiscale='"+E.matriculeFiscale+"'";

        if(DataBaseConnection.executionUpdate(req) >0)
            return true;
        return false;

    }
}
