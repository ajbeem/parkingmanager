package com.pcentaury.parkingmanager.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Boletos {	
	private Integer id;
	private String marca;
	private String modelo;
	private String color;
	private String placas;
	private String estadofisico;
	private Date fechaingreso;
	private Timestamp horaingreso;
	private Date horasalida;
	private Integer id_cajon;
	private Integer id_acomodador;
	private String nombreacomodador;
	
	public Boletos(String marca, String modelo, String color, String placas, String estadofisico, 
			Integer id_cajon, Integer id_acomodador) {
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.placas = placas;
		this.estadofisico = estadofisico;
		this.fechaingreso = fechaingreso;
		this.horaingreso = horaingreso;
		this.horasalida = horasalida;
		this.id_cajon = id_cajon;
		this.id_acomodador = id_acomodador;
		this.nombreacomodador = nombreacomodador;
	}
	
	public Boletos() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlacas() {
		return placas;
	}

	public void setPlacas(String placas) {
		this.placas = placas;
	}

	public String getEstadofisico() {
		return estadofisico;
	}

	public void setEstadofisico(String estadofisico) {
		this.estadofisico = estadofisico;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Timestamp getHoraingreso() {
		return horaingreso;
	}

	public void setHoraingreso(Timestamp horaingreso) {
		this.horaingreso = horaingreso;
	}

	public Date getHorasalida() {
		return horasalida;
	}

	public void setHorasalida(Date horasalida) {
		this.horasalida = horasalida;
	}

	public Integer getId_cajon() {
		return id_cajon;
	}

	public void setId_cajon(Integer id_cajon) {
		this.id_cajon = id_cajon;
	}

	public Integer getId_acomodador() {
		return id_acomodador;
	}

	public void setId_acomodador(Integer id_acomodador) {
		this.id_acomodador = id_acomodador;
	}

	public String getNombreacomodador() {
		return nombreacomodador;
	}

	public void setNombreacomodador(String nombreacomodador) {
		this.nombreacomodador = nombreacomodador;
	}
	
}
