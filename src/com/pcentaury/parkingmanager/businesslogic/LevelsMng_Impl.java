package com.pcentaury.parkingmanager.businesslogic;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Nivel;
import com.pcentaury.parkingmanager.repositories.DAO_Nivel;
import com.pcentaury.parkingmanager.repositories.DAO_Nivel_Impl;

public class LevelsMng_Impl implements Iface_BL_LevelsMng {
	private DAO_Nivel lv = new DAO_Nivel_Impl();
	private Nivel lv1 = new Nivel();
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

	@Override
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
}
