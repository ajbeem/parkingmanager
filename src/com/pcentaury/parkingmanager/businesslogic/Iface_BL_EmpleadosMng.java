package com.pcentaury.parkingmanager.businesslogic;

import com.pcentaury.parkingmanager.models.Empleado;

public interface Iface_BL_EmpleadosMng {
	void NewEmployee() throws Exception;

	void GetEmployee() throws Exception;

	void GetAllEmployees() throws Exception;

	void UpdateEmployee() throws Exception;

	void DeleteEmployee() throws Exception;
	
	// SOLID
}
