package com.pcentaury.parkingmanager.businesslogic;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Cajon;
import com.pcentaury.parkingmanager.repositories.DAO_Cajon;
import com.pcentaury.parkingmanager.repositories.DAO_Cajon_Impl;

public class ParkignDrawersMng_Impl implements Iface_BL_ParkingDrawersMng {
	private DAO_Cajon cj = new DAO_Cajon_Impl();
	private Cajon cj1 = new Cajon();
	private Scanner scan = new Scanner(System.in);
	
	@Override
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
	@Override
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
	@Override
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
	@Override
	public void setParkingDrawer() {
		int idCajon;
		System.out.println("Ingrese id de cajon: ");
		idCajon = scan.nextInt();
		if (idCajon > 0) {
			//System.out.println(""+idCajon);
			updateParkingDrawerOcup(idCajon);
		}
	}
	@Override
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
	@Override
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

}
