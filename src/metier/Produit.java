package metier;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import database.DataBaseConnection;
import database.DataBaseTableNames;

public class Produit {
	// Les caractéristiques :
	String reference;
	String designation;
	String uniteMesure;
	Fournisseur fournisseur;
	FamilleProduit familleProduit;
	int stock;
	int stockMinimal;
	int prixUnitaireHTaxe;
	int tva;
	static Connection connection;
	public Produit(String reference, String designation, String uniteMesure, Fournisseur fournisseur,
			FamilleProduit familleProduit, int stock, int stockMinimal, int prixUnitaireHTaxe, int tva) {
		super();
		this.reference = reference;
		this.designation = designation;
		this.uniteMesure = uniteMesure;
		this.fournisseur = fournisseur;
		this.familleProduit = familleProduit;
		this.stock = stock;
		this.stockMinimal = stockMinimal;
		this.prixUnitaireHTaxe = prixUnitaireHTaxe;
		this.tva = tva;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getUniteMesure() {
		return uniteMesure;
	}
	public void setUniteMesure(String uniteMesure) {
		this.uniteMesure = uniteMesure;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	public FamilleProduit getFamilleProduit() {
		return familleProduit;
	}
	public void setFamilleProduit(FamilleProduit familleProduit) {
		this.familleProduit = familleProduit;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStockMinimal() {
		return stockMinimal;
	}
	public void setStockMinimal(int stockMinimal) {
		this.stockMinimal = stockMinimal;
	}
	public int getPrixUnitaireHTaxe() {
		return prixUnitaireHTaxe;
	}
	public void setPrixUnitaireHTaxe(int prixUnitaireHTaxe) {
		this.prixUnitaireHTaxe = prixUnitaireHTaxe;
	}
	public double getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	
	//ajouter un produit dans la base:


	
	public boolean ajouterProduit() throws HeadlessException, SQLException
	{	
		String req = "insert into " + DataBaseTableNames.PRODUIT + " values ("+
		"'" + reference + "' " + "," + 
		"'" + designation + "' " + "," + 
		"'" +  uniteMesure + "' " + "," + 
		"'" +  fournisseur.getCodeFournisseur()+ "' " + "," + 
		familleProduit.getCodeFamille() + "," + 
		stock +  "," + 
		stockMinimal + "," + 
		prixUnitaireHTaxe + "," + 
		tva + ")"; 
		
		if(DataBaseConnection.executionUpdate(req) >0)
			return true;
		return false;
		
	}
	
    public boolean modifierProduit(Produit P) throws HeadlessException, SQLException
    {
        String req = "UPDATE produit SET "                     + 
                "reference='"+P.reference+"',"                 +
                "designation='"+P.designation+"',"               +
                "uniteMesure='"+P.uniteMesure+"',"             +
                "fournisseur='"+P.fournisseur.getCodeFournisseur()+"',"                +
                "familleProduit='"+P.familleProduit.getCodeFamille()+"',"          +
                "stock='"+P.stock+"',"                         +
                "stockMinimal='"+P.stockMinimal+"',"             +
                "prixUnitaireHTaxe='"+P.prixUnitaireHTaxe+"'," +
                "tva='"+P.tva+"'"                                 +
                "WHERE reference='"+P.reference+"'";

        if(DataBaseConnection.executionUpdate(req) >0)
            return true;
        return false;

    }

    public boolean supprimerProduit(String ref) throws HeadlessException, SQLException
    {
        String req = "DELETE FROM produit WHERE reference='"+ref+"'";

        if(DataBaseConnection.executionUpdate(req) >0)
            return true;
        return false;

    }
}
