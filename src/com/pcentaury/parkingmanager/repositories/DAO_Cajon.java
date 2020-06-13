package com.pcentaury.parkingmanager.repositories;

import java.util.List;

import com.pcentaury.parkingmanager.models.Cajon;

public interface DAO_Cajon {
	void CrearCajon(Cajon c, String rol1) throws Exception;
	Cajon GetCajon(Cajon c1) throws Exception;
	void EditCajon(Cajon c2, String rol2) throws Exception;
	void EliminarCajon(Cajon c3, String rol3) throws Exception;
	List<Cajon> listarCajones(Cajon c4) throws Exception;
	List<Cajon> listarCajonesDisponibles(Cajon c5) throws Exception;
	void asigCajon (Cajon c6, String rol4) throws Exception;
	void liberarCajon(Cajon cj7, String rol5) throws Exception;
}
