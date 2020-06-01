package com.pcentaury.parkingmanager.repositories;

import java.util.List;

import com.pcentaury.parkingmanager.models.Empleado;

public interface DAO_Empleado {
	void CrearEmpleado(Empleado emp) throws Exception;
	Empleado GetEmpleado(Empleado gEmp) throws Exception;
	void EditEmpleado(Empleado empEdit) throws Exception;
	void EliminarEmpleado (Empleado empDel) throws Exception;
	List<Empleado> listarEmpleados() throws Exception;
}
