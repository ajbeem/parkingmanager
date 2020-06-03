package com.pcentaury.parkingmanager.app;

import com.pcentaury.parkingmanager.models.Empleado;
import com.pcentaury.parkingmanager.repositories.DAO_Empleado;
import com.pcentaury.parkingmanager.repositories.DAO_EmpleadoImpl;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class PM_Aplication {
	IfaceSingletonConect conx = new SingletonConect_Impl();
	DAO_Empleado emp = new DAO_EmpleadoImpl();
	Empleado emp1 = new Empleado();

	public PM_Aplication() {
		//conx.getConnection();
	}
	
	public void pm_closeCnx() {
		conx.closeConnection();
	}
	
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
	
	public void GetEmployee() {
		Empleado getEmp = new Empleado();
		emp1.setId(1);
		try {
			getEmp = emp.GetEmpleado(emp1);
			System.out.println("Nombre del empleado: "+ getEmp.getNombres());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void GetAllEmployees() {
		try {
			for(Empleado e : emp.listarEmpleados()) {
				System.out.println("Nombre del usuario:"+ e.getNombres());
				System.out.println("Correo electrónico del usuario: "+e.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public void DeleteEmployee() {
		try {
			emp1.setId(8);
			emp.EliminarEmpleado(emp1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PM_Aplication objt1 = new PM_Aplication();
		//objt1.GetEmployee();
		//objt1.NewEmployee();
		//objt1.pm_closeCnx();
		//objt1.GetAllEmployees();
		//objt1.UpdateEmployee();
		objt1.DeleteEmployee();
	}

}
