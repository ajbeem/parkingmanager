package com.pcentaury.parkingmanager.models;

import java.util.Date;

public class Nomina {
	private Integer id;
	private Date fechainicio;
	private Date fechafin;
	private Float ingresostotales;
	private Integer serviciostotales;
	private Float pagostotales;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Float getIngresostotales() {
		return ingresostotales;
	}
	public void setIngresostotales(Float ingresostotales) {
		this.ingresostotales = ingresostotales;
	}
	public Integer getServiciostotales() {
		return serviciostotales;
	}
	public void setServiciostotales(Integer serviciostotales) {
		this.serviciostotales = serviciostotales;
	}
	public Float getPagostotales() {
		return pagostotales;
	}
	public void setPagostotales(Float pagostotales) {
		this.pagostotales = pagostotales;
	}
	
	
}
