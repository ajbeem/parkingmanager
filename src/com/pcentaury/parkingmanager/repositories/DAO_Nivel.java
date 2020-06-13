package com.pcentaury.parkingmanager.repositories;

import java.util.List;

import com.pcentaury.parkingmanager.models.Nivel;

public interface DAO_Nivel {
	void crearNivel(Nivel lv, String roll) throws Exception;
	Nivel getNivel(Nivel lv1) throws Exception;
	void updateNivel(Nivel lv2, String roll1) throws Exception;
	void deleteNivel(Nivel lv3, String roll2) throws Exception;
	List<Nivel> litLv() throws Exception;
}
