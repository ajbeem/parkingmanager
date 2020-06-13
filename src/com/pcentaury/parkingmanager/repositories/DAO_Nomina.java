package com.pcentaury.parkingmanager.repositories;

import java.util.List;

import com.pcentaury.parkingmanager.models.Nomina;

public interface DAO_Nomina {
	void CrearNomina(Nomina nom) throws Exception;
	Nomina getNomina(Nomina gNom) throws Exception;
	void updateNomina(Nomina uNom) throws Exception;
	void deleteNomina(Nomina delNom) throws Exception;
	List <Nomina> listNom() throws Exception;
}
