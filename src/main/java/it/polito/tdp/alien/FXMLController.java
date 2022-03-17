package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.alien.model.Elenco;
import it.polito.tdp.alien.model.Traduzioni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnTraduci;
    @FXML
    private TextField txtStatus;
    @FXML
    private TextField txtParole;
    @FXML
    private TextArea txtRisultato;
    
    private Traduzioni model;

    @FXML
    void handleClear(ActionEvent event) {
    	
    	this.txtStatus.clear();
    	this.txtParole.clear();
    	this.txtRisultato.clear();

    }

    @FXML
    void handleHelp(ActionEvent event) {
    	
    	this.txtRisultato.setText("Welcome to Alien dictionary\n");
    	this.txtRisultato.appendText("You can:\n");
    	this.txtRisultato.appendText("1) Enter a pair of words: <alien word> <translation>\n");
    	this.txtRisultato.appendText("to update the dictionay\n");
    	this.txtRisultato.appendText("2) Enter an alien word\n");
    	this.txtRisultato.appendText("to get the translation\n");
    	this.txtRisultato.appendText("Note: you can insert more than one translation for each alien word");

    }

    @FXML
    void handleTraduci(ActionEvent event) {
    	
    	this.txtStatus.clear();
    	
    	// 1) CONTROLLI
    	String stringa = this.txtParole.getText();
    	
    	if(stringa.equals(""))
    	{
    		this.txtStatus.setText("ERROR! Insert a pair of words or a word!!");
    		this.txtRisultato.clear();
        	return;
    	}
    	
    	String array[] = stringa.split(" ");
    	
    	if(array.length > 2)
    	{
    		this.txtStatus.setText("ERROR! Insert a pair of words or a word!!");
    		this.txtRisultato.clear();
        	return;
    	}
    	
    	// 2) ESECUZIONE
    	if(array.length == 1)
    	{
    		// una sola parola inserita --> devo restituire la traduzione
    		String alien = array[0].toLowerCase();
    		boolean ok = this.controlloParola(alien);
    		if(!ok)
    		{
    			this.txtStatus.setText("ERROR! Use only alphabetical letters [a-zA-Z]");
    			this.txtRisultato.clear();
    			this.txtParole.clear();
            	return;
    		}
    		
    		Elenco elenco = model.traduci(alien);
    		
    		if(elenco == null)
    		{
    			// non ci sono traduzioni per questa parola
    			this.txtRisultato.setText("There isn't a translation for the word <" + alien + ">");
    		}
    		else
    		{
    			this.txtRisultato.setText("The translation of the word <" + alien + "> is:\n");
    			this.txtRisultato.appendText(elenco.toString());
    		}
    		
    		this.txtParole.clear();
    	}
    	
    	if(array.length == 2)
    	{
    		// due parole inserite --> devo aggiornare il dizionario
    		String alien = array[0].toLowerCase();
    		boolean ok = this.controlloParola(alien);
    		if(!ok)
    		{
    			this.txtStatus.setText("ERROR! Use only alphabetical letters [a-zA-Z]");
    			this.txtParole.clear();
    			this.txtRisultato.clear();
            	return;
    		}
    		
    		String trad = array[1].toLowerCase();
    		ok = this.controlloParola(trad);
    		if(!ok)
    		{
    			this.txtStatus.setText("ERROR! Use only alphabetical letters [a-zA-Z]");
    			this.txtParole.clear();
    			this.txtRisultato.clear();
            	return;
    		}
    		
    		model.aggiorna(alien, trad);
    		this.txtRisultato.setText("The dictionary has been updated");
    		this.txtParole.clear();
    	}
    	

    }
    
    void setModel(Traduzioni model)
    {
    	this.model = model;
    }
    
    boolean controlloParola(String parola)
    {
    	String pattern = "[a-zA-Z]+";
    	
		return parola.matches(pattern);
    }

    @FXML
    void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnHelp != null : "fx:id=\"btnHelp\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTraduci != null : "fx:id=\"btnTraduci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStatus != null : "fx:id=\"txtErrore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParole != null : "fx:id=\"txtParole\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

        this.txtRisultato.setText("Welcome to Alien dictionary \n");
    }

}