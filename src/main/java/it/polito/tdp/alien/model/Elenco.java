package it.polito.tdp.alien.model;

import java.util.LinkedList;
import java.util.List;

public class Elenco {
	
	// ATTRIBUTI
	List<String> lista;
	
	// COSTRUTTORE
	public Elenco()
	{
		lista = new LinkedList<String>();
	}
	
	public void aggiungi(String trad)
	{
		this.lista.add(trad);
	}
	
	public int getSize()
	{
		return lista.size();
	}

	@Override
	public String toString() {
		
		String ret = "";
		
		for(String x: lista)
		{
			if(ret != "")
			{
				ret = ret + "\n";
			}
			
			ret = ret + x;
		}
		
		return ret;
	}
	
	

}
