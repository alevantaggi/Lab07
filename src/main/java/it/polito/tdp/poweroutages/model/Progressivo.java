package it.polito.tdp.poweroutages.model;

import java.util.Objects;

public class Progressivo {
	
	private PowerOutage powerOutage;
	private Integer progressivoClienti;
	private Double progressivoOre;
	
	public Progressivo(PowerOutage powerOutage, Integer progressivoClienti, Double progressivoOre) {
		this.powerOutage = powerOutage;
		this.progressivoClienti = progressivoClienti;
		this.progressivoOre = progressivoOre;
	}

	public PowerOutage getPowerOutage() {
		return powerOutage;
	}

	public Integer getProgressivoClienti() {
		return progressivoClienti;
	}

	public Double getProgressivoOre() {
		return progressivoOre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(powerOutage);
	}

}
