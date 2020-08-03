package com.pcentaury.parkingmanager.repositories;

import java.util.List;

import com.pcentaury.parkingmanager.models.Boletos;

public interface DAO_Boletos {
	Byte CreaBoleto(Boletos tck1, int idEmpleado, String roll) throws Exception;
	Boletos GetBoletoById(int idTck, String roll1) throws Exception;
	List<Boletos> getAllBoletos() throws Exception;
	void UpdtBoleto(Boletos tck2) throws Exception;
	Integer DeleteBoleto (int idTck2) throws Exception;
}
