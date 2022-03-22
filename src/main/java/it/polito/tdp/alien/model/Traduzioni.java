package it.polito.tdp.alien.model;

import java.util.Map;
import java.util.TreeMap;

public class Traduzioni {
	
	// ATTRIBUTI
	Map<String, Elenco> model;
	
	// COSTRUTTORE
	public Traduzioni()
	{
		model = new TreeMap<String, Elenco>();
	}
	
	public Elenco traduci(String alien)
	{
		return model.get(alien);
	}
	
	public void aggiorna(String alien, String trad)
	{
		if(model.get(alien) == null)
		{
			// non ci sono ancora traduzioni per questa parola
			// creo una nuova traduzione
			Elenco x = new Elenco();
			x.aggiungi(trad);
			
			model.put(alien, x);
		}
		else
		{
			// ci sono gia traduzione per questa parola
			// aggiungo una nuova traduzione
			model.get(alien).aggiungi(trad);
		}
	}
	
	

}
