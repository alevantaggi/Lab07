package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
//	private PowerOutageDAO podao;
//	private List<PowerOutage> listPowerOutage;
//	private List<PowerOutage> migliore;
//	
//	public Model() {
//		podao = new PowerOutageDAO();
//	}
//	
//	public List<Nerc> getNercList() {
//		return podao.getNercList();
//	}
//	
//
//	public String worstSequenza(Nerc nerc,int anni, int ore) {
//		this.listPowerOutage= new ArrayList<>(podao.readOutages(nerc));
//		this.migliore= new ArrayList<>();
//		
//		List<PowerOutage> parziale= new ArrayList<>();
//		ricerca(parziale, anni, ore);	
//		String s="Tot people affected: "+totalPeopleAffected(migliore)+"\nTot hours of outage: "+ totalOreMaxDisservizio(migliore)+"\n";
//		for(PowerOutage po: this.migliore) {
//			s+=po+"\n";
//		}
//		return s.substring(0, s.length()-1);
//	}
//	
//	private void ricerca(List<PowerOutage> parziale, int MaxAnni,int OreMaxDisservizio) {
//		
//		if(totalPeopleAffected(migliore)< totalPeopleAffected(parziale)) {
//			this.migliore= new ArrayList<>(parziale);
//		}
//		
//		for(PowerOutage po: this.listPowerOutage) {
//			if(!parziale.contains(po)) {
//				parziale.add(po);
//				if (isMaxYear(parziale, MaxAnni) && totalOreMaxDisservizio(parziale)<= OreMaxDisservizio){
//					ricerca(parziale, MaxAnni, OreMaxDisservizio);
//				}
//				parziale.remove(parziale.size()-1);
//			}
//		}
//		return;
//	}
//		
//	private boolean isMaxYear(List<PowerOutage> lista,int anni) {
//		
//		if(lista.size()>1) {
//			if(lista.get(0).getDataInizio().plusYears(anni).isBefore(lista.get(lista.size()-1).getDataInizio()))
//				return false;
//		}
//		
//		return true;
//	}
//	
//	private Double totalOreMaxDisservizio(List<PowerOutage> lista) {
//		Double count= 0.0;
//		for(int i=0;i<lista.size();i++) {
//			count+=lista.get(i).getTotalDurata();
//		}
//		return count;
//	}
//	
//	private Integer totalPeopleAffected(List<PowerOutage> lista) {
//		Integer count=0;
//		
//		if(!lista.isEmpty()) {
//			for(PowerOutage p: lista) 
//				count+=p.getClientiAffetti();
//		}
//		return count;
//	}
	private PowerOutageDAO podao;
	private List<PowerOutage> listPowerOutage;
	private List<Progressivo> migliore;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	

	public String worstSequenza(Nerc nerc,int anni, Double ore) {
		this.listPowerOutage= new ArrayList<>(podao.readOutages(nerc));
		this.migliore= new ArrayList<>();
		
		List<Progressivo> parziale= new ArrayList<>();
		ricerca(parziale, anni, ore);	
		
		String s="Tot people affected: "+migliore.get(migliore.size()-1).getProgressivoClienti()
				+"\nTot hours of outage: "+ migliore.get(migliore.size()-1).getProgressivoOre()+"\n";
		for(Progressivo p: this.migliore) {
			s+=p.getPowerOutage()+"\n";
		}
		return s.substring(0, s.length()-1);
	}
	
	private void ricerca(List<Progressivo> parziale, int MaxAnni,Double OreMaxDisservizio) {
		
		if(!parziale.isEmpty()) {	
			if(!migliore.isEmpty()) {
				if(parziale.get(parziale.size()-1).getProgressivoClienti() > migliore.get(migliore.size()-1).getProgressivoClienti())
					this.migliore= new ArrayList<>(parziale);
			}
			else 
				this.migliore= new ArrayList<>(parziale);
		}
		
		
		for(PowerOutage po: this.listPowerOutage) {
			
			if(isAggiungibile(parziale, po, MaxAnni, OreMaxDisservizio)) {
				Progressivo prog;
				if(parziale.isEmpty()) {
					prog= new Progressivo(po, po.getClientiAffetti(), po.getTotalDurata());				
				}
				else {
					prog= new Progressivo(po, 
							po.getClientiAffetti()+parziale.get(parziale.size()-1).getProgressivoClienti(), 
							po.getTotalDurata()+parziale.get(parziale.size()-1).getProgressivoOre());
				}
				parziale.add(prog);
				ricerca(parziale, MaxAnni, OreMaxDisservizio);
				parziale.remove(parziale.size()-1);
			}
		}
		return;
	}
		
	
	private boolean isAggiungibile(List<Progressivo> lista, PowerOutage po, int anni, Double ore) {
		
		// Lista vuota
		if(lista.isEmpty())
			return true;
		
		// Guardo la data (se sono qui lista non vuota)
		if((lista.get(0).getPowerOutage().getDataInizio().plusYears(anni)).isBefore(po.getDataInizio()))
			return false;
		
		// Guardo ore massime
		if ((lista.get(lista.size()-1).getProgressivoOre()+po.getTotalDurata())> ore)
			return false;
		
		//Guardo se la lista già lo contiene
		for(Progressivo p: lista) {
			if(p.getPowerOutage().equals(po))
				return false;
		}
		
		return true;
		
	}

}
