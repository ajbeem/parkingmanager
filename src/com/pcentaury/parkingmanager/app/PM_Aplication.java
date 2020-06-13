package com.pcentaury.parkingmanager.app;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Cajon;
import com.pcentaury.parkingmanager.models.Empleado;
import com.pcentaury.parkingmanager.models.Nivel;
import com.pcentaury.parkingmanager.models.Nomina;
import com.pcentaury.parkingmanager.repositories.DAO_Cajon;
import com.pcentaury.parkingmanager.repositories.DAO_Cajon_Impl;
import com.pcentaury.parkingmanager.repositories.DAO_Empleado;
import com.pcentaury.parkingmanager.repositories.DAO_EmpleadoImpl;
import com.pcentaury.parkingmanager.repositories.DAO_Nivel;
import com.pcentaury.parkingmanager.repositories.DAO_Nivel_Impl;
import com.pcentaury.parkingmanager.repositories.DAO_Nomina;
import com.pcentaury.parkingmanager.repositories.DAO_Nomina_Impl;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class PM_Aplication {
	// IfaceSingletonConect conx = new SingletonConect_Impl();
	DAO_Empleado emp = new DAO_EmpleadoImpl();
	Empleado emp1 = new Empleado();
	DAO_Nomina nom = new DAO_Nomina_Impl();
	Nomina nom1 = new Nomina();
	DAO_Nivel lv = new DAO_Nivel_Impl();
	Nivel lv1 = new Nivel();
	DAO_Cajon cj = new DAO_Cajon_Impl();
	Cajon cj1 = new Cajon();
	Scanner scan = new Scanner(System.in);

	public void getParkingDrawer() {
		Cajon cjGet = new Cajon();
		boolean disponible = false;
		try {
			cj1.setId(83);
			cjGet = cj.GetCajon(cj1);
			System.out.println("Id Cajon: " + cjGet.getId());
			System.out.println("Id Piso: " + cjGet.getId_piso());
			if (cjGet.getDisponibilidad() == 0) {
				disponible = true;
			}
			System.out.println("Espacio disponible: " + disponible);
			cj1 = null;
			cj = null;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido una excepción");
		}

	}

	public void deleteParkingDrawer() {
		try {
			cj1.setId(83);
			cj.EliminarCajon(cj1, "ADMIN");
			cj1 = null;
			cj = null;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido una excepción");
		}
	}

	public void getAllParkingDrawer() {
		boolean disp = false;
		cj1.setId_piso(4);
		try {
			for (Cajon cjGet1 : cj.listarCajones(cj1)) {
				disp = false;
				System.out.println("Id Cajon: " + cjGet1.getId());
				System.out.println("Id Piso: " + cjGet1.getId_piso());
				if (cjGet1.getDisponibilidad() == 0) {
					disp = true;
				}
				System.out.println("Espacio disponible: " + disp);
			}
			cj1 = null;

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido una excepción");
		}
	}

	public void getAllAvailableParkingDrawer() {
		int idPiso;
		do {
			System.out.println("Ingrese numero de piso: ");
			idPiso = scan.nextInt();
			boolean disp = false;
			if (idPiso > 0 && idPiso < 5) {
				cj1.setId_piso(idPiso);
				try {
					for (Cajon cjGet1 : cj.listarCajones(cj1)) {
						disp = false;
						if (cjGet1.getDisponibilidad() == 0) {
							disp = true;
							System.out.println("Id Cajon: " + cjGet1.getId());
							System.out.println("Id Piso: " + cjGet1.getId_piso());
							System.out.println("Espacio disponible: " + disp);
						}
					}
					cj1 = null;
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha ocurrido una excepción");
				}
			} else {
				System.out.println("El valor de piso es inválido");
			}
		} while (idPiso < 0 || idPiso > 4);

		System.out.println("Desea asignar cajon? Y/N");
		String resp;
		resp = scan.next();
		try {
			if (resp.equalsIgnoreCase("y")) {
				setParkingDrawer();
			} else {
				System.out.println("Gracias por utilizar el administrador");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido una excepción");
		}

	}

	public void setParkingDrawer() {
		int idCajon;
		System.out.println("Ingrese id de cajon: ");
		idCajon = scan.nextInt();
		if (idCajon > 0) {
			//System.out.println(""+idCajon);
			updateParkingDrawerOcup(idCajon);
		}
	}

	public void updateParkingDrawerOcup(int idC) {
		cj1= new Cajon();
		cj1.setId(idC);
		//System.out.println("Id a actualizar: "+cj1.getId());
		cj1.setDisponibilidad((byte) 1);
		//System.out.println("Disponibilidad: "+cj1.getDisponibilidad());
		try {
			cj.asigCajon(cj1, "ADMIN");
			/* Obtener el ID de piso
			 * obtener espacios disponibles del piso
			 * restar cajon
			 * cambiar disponibilidad de cajon
			 */
			cj1 = null;
			cj = null;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido una excepción");
		}
	}
	
	public void freeUpParkingDrawer(int idC1) {
		cj1= new Cajon();
		cj1.setId(idC1);
		try {
			cj.liberarCajon(cj1, "ADMIN");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido una excepción");
		}
	}
	
	
	public PM_Aplication() {
		// conx.getConnection();
	}

	/*
	 * public void pm_closeCnx() { conx.closeConnection(); }
	 */

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
			System.out.println("Nombre del empleado: " + getEmp.getNombres());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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

	public void NewPayroll() {
		try {
			nom1.setFechainicio(convertDate("2020-02-11"));
			nom1.setFechafin(convertDate("2020-02-17"));
			nom1.setIngresostotales((float) 3000.00);
			nom1.setPagostotales((float) 2000.00);
			nom1.setServiciostotales(70);
			// System.out.println("Fecha inicio: "+nom1.getFechainicio());
			// System.out.println("Fecha inicio: "+nom1.getFechafin());
			nom.CrearNomina(nom1);
			nom = null;
			nom1 = null;
			JOptionPane.showMessageDialog(null, "Nomina Agregada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado");
			e.printStackTrace();
		}

	}

	public void getPayroll() {
		nom1.setId(2);
		Nomina nomGet;
		try {
			nomGet = nom.getNomina(nom1);
			System.out.println("Nomina con folio: " + nomGet.getId());
			System.out.println("Ingresos totales: " + nomGet.getIngresostotales());
			System.out.println("Servicios totales: " + nomGet.getServiciostotales());
			nomGet = null;
			nom1 = null;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado");
			e.printStackTrace();
		}
	}

	public static java.sql.Date convertDate(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate;
		try {
			java.util.Date utilDate = sdf.parse(fecha);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			sqlDate = null;
		}
		return sqlDate;

	}

	public void updatePayroll() {
		try {
			nom1.setFechainicio(convertDate("2020-02-11"));
			nom1.setFechafin(convertDate("2020-02-17"));
			nom1.setIngresostotales((float) 4000.00);
			nom1.setPagostotales((float) 2000.00);
			nom1.setServiciostotales(100);
			nom1.setId(3);
			nom.updateNomina(nom1);
			nom = null;
			nom1 = null;
			JOptionPane.showMessageDialog(null, "Nomina Actualizada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado");
			e.printStackTrace();
		}

	}

	public void deletePayroll() {
		try {
			nom1.setId(3);
			nom.deleteNomina(nom1);
			nom = null;
			nom1 = null;
			JOptionPane.showMessageDialog(null, "Nomina Borrada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado");
			e.printStackTrace();
		}
	}

	public void listPayroll() {
		try {
			for (Nomina n : nom.listNom()) {
				System.out.println("Nomina con folio: " + n.getId());
				System.out.println("Ingresos totales: " + n.getIngresostotales());
				System.out.println("Servicios totales: " + n.getServiciostotales());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado");
			e.printStackTrace();
		}
	}

	public void createLevel() {
		try {
			lv1.setEspaciostotales((byte) 20);
			lv1.setEspaciosdisponibles((byte) 20);
			lv.crearNivel(lv1, "ADMIN");
			lv1 = null;
			lv = null;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado!!");
		}
	}

	public void getLevelData() {
		try {
			Nivel lvGet = new Nivel();
			lv1.setId(2);
			lvGet = lv.getNivel(lv1);
			System.out.println("Nivel: " + lvGet.getId());
			System.out.println("Capacidad: " + lvGet.getEspaciostotales());
			System.out.println("Disponibles: " + lvGet.getEspaciosdisponibles());
			lv1 = null;
			lv = null;
			lvGet = null;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado!!");
		}
	}

	public void updateLevel() {
		try {
			lv1.setEspaciostotales((byte) 20);
			lv1.setEspaciosdisponibles((byte) 20);
			lv1.setId(3);
			lv.updateNivel(lv1, "ADMIN");
			lv1 = null;
			lv = null;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado!!");
		}
	}

	public void deleteLevel() {
		try {
			lv1.setId(5);
			lv.deleteNivel(lv1, "ADMIN");
			lv1 = null;
			lv = null;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado!!");
		}
	}

	public void getListLevelData() {
		try {
			for (Nivel niv : lv.litLv()) {
				System.out.println("Nivel: " + niv.getId());
				System.out.println("Capacidad: " + niv.getEspaciostotales());
				System.out.println("Disponibles: " + niv.getEspaciosdisponibles());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado!!");
		}
	}

	public static void main(String[] args) {
		PM_Aplication objt1 = new PM_Aplication();
		// objt1.GetEmployee();
		// objt1.NewEmployee();
		// objt1.pm_closeCnx();
		// objt1.GetAllEmployees();
		// objt1.UpdateEmployee();
		// objt1.DeleteEmployee();
		// objt1.NewPayroll();
		// objt1.getPayroll();
		// objt1.updatePayroll();
		// objt1.deletePayroll();
		// objt1.listPayroll();
		// objt1.createLevel();
		// objt1.getLevelData();
		// objt1.updateLevel();
		// objt1.deleteLevel();
		// objt1.getListLevelData();
		// objt1.createParkingDrawer();
		// objt1.getParkingDrawer();
		// objt1.updateParkingDrawer();
		// objt1.getAllParkingDrawer();
		// objt1.deleteParkingDrawer();
		//objt1.getAllAvailableParkingDrawer();
		//objt1.updateParkingDrawerOcup(2);
		objt1.freeUpParkingDrawer(2);
	}

}
