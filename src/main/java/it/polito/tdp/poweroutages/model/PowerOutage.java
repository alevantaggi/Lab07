package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.Objects;

public class PowerOutage {
	
	private Integer id;
	private Integer clientiAffetti;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private Double totalDurata;

	public PowerOutage(Integer id, Integer clientiAffetti, LocalDate dataInizio, LocalDate dataFine,
			Double totalDurata) {
		this.id = id;
		this.clientiAffetti = clientiAffetti;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.totalDurata = totalDurata;
	}


	public Integer getId() {
		return id;
	}


	public Integer getClientiAffetti() {
		return clientiAffetti;
	}


	public LocalDate getDataInizio() {
		return dataInizio;
	}


	public LocalDate getDataFine() {
		return dataFine;
	}


	public Double getTotalDurata() {
		return totalDurata;
	}

	@Override
	public String toString() {
		return this.dataInizio.getYear()+" "+this.dataInizio+" "+dataFine+ " "+totalDurata+" "+clientiAffetti;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
	
//	public int totalDurata(LocalDate start, LocalDate end) {
//		start.plusYears(1);
//	}
//	

}
