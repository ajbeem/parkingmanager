package com.pcentaury.parkingmanager.businesslogic;

import com.pcentaury.parkingmanager.models.Empleado;
import com.pcentaury.parkingmanager.repositories.DAO_Empleado;
import com.pcentaury.parkingmanager.repositories.DAO_EmpleadoImpl;

public class EmpleadosMng_Impl implements Iface_BL_EmpleadosMng {
	DAO_Empleado emp = new DAO_EmpleadoImpl();
	Empleado emp1 = new Empleado();
	@Override
	public void NewEmployee() {
		try {
			emp1.setNombres("Maira");
			emp1.setApellidopaterno("S");
			emp1.setApellidomaterno("M");
			emp1.setNombreusuario("suem");
			emp1.setPassword("987654");
			emp1.setEmail("suem4@contoso.com");
			emp1.setPuesto("Acomodador");
			emp1.setSalariobase((float) 259.30);

			emp.CrearEmpleado(emp1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void GetEmployee() {
		Empleado getEmp = new Empleado();
		emp1.setId(1);
		try {
			getEmp = emp.GetEmpleado(emp1);
			System.out.println("Nombre del empleado: " + getEmp.getNombres());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void GetAllEmployees() {
		try {
			for (Empleado e : emp.listarEmpleados()) {
				System.out.println("Nombre del usuario:" + e.getNombres());
				System.out.println("Correo electrónico del usuario: " + e.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void UpdateEmployee() {
		try {
			emp1.setId(1);
			emp1.setNombres("Alfred");
			emp1.setApellidopaterno("Jim");
			emp1.setApellidomaterno("Mig");
			emp1.setNombreusuario("ajbeem");
			emp1.setPassword("wsd456");
			emp1.setEmail("fred.jm.74@contoso.com");
			emp1.setPuesto("Administrador");
			emp1.setSalariobase((float) 759.30);
			emp.EditEmpleado(emp1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void DeleteEmployee() {
		try {
			emp1.setId(8);
			emp.EliminarEmpleado(emp1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
