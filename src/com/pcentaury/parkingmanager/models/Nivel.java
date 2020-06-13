package com.pcentaury.parkingmanager.models;

public class Nivel {
	private Integer id;
	private Byte espaciostotales;
	private Byte espaciosdisponibles;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Byte getEspaciostotales() {
		return espaciostotales;
	}
	public void setEspaciostotales(Byte espaciostotales) {
		this.espaciostotales = espaciostotales;
	}
	public Byte getEspaciosdisponibles() {
		return espaciosdisponibles;
	}
	public void setEspaciosdisponibles(Byte espaciosdisponibles) {
		this.espaciosdisponibles = espaciosdisponibles;
	}
}
