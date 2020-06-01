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
		//NewEmployee();
	}
	
	public void pm_closeCnx() {
		conx.closeConnection();
	}
	
	public void NewEmployee() {
		emp1.setNombres("Alfredo");
		emp1.setApellidopaterno("Jimenez");
		emp1.setApellidomaterno("Miguel");
		emp1.setNombreusuario("ajbeem");
		emp1.setPassword("wsd456");
		emp1.setEmail("fred.jm.74@gmail.com");
		emp1.setPuesto("Administrador");
		emp1.setSalariobase((float) 459.30);
		try {
			emp.CrearEmpleado(emp1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void allEmployees() {
		try {
			for(Empleado e : emp.listarEmpleados()) {
				System.out.println(e.getNombres());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getEmployee() {
		Empleado getEmp = new Empleado();
		int id= 1;
		emp1.setId(id);
		try {
			getEmp = emp.GetEmpleado(emp1);
			System.out.println(getEmp.getNombres());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateEmployee() {
		emp1.setId(1);
		emp1.setNombres("Alfred");
		emp1.setApellidopaterno("Jim");
		emp1.setApellidomaterno("Mig");
		emp1.setNombreusuario("ajbeem");
		emp1.setPassword("wsd456");
		emp1.setEmail("fred.jm.74@contoso.com");
		emp1.setPuesto("Administrador");
		emp1.setSalariobase((float) 559.30);
		try {
			emp.EditEmpleado(emp1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void DeleteEmployee() {
		emp1.setId(6);
		try {
			emp.EliminarEmpleado(emp1);
			System.out.println("Usuario eliminado con id: "+emp1.getId());
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();	
		}
	}

	public static void main(String[] args) {
		PM_Aplication objt1 = new PM_Aplication();
		//objt1.pm_closeCnx();
		objt1.allEmployees();
		System.out.println("----------------*******************-------------------");
		objt1.getEmployee();
		System.out.println("----------------*******************-------------------");
		//objt1.UpdateEmployee();
		//objt1.DeleteEmployee();
		//objt1.NewEmployee();
	}

}
