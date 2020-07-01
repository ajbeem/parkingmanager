package com.pcentaury.parkingmanager.businesslogic;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Nomina;
import com.pcentaury.parkingmanager.repositories.DAO_Nomina;
import com.pcentaury.parkingmanager.repositories.DAO_Nomina_Impl;

public class PayrollMng_Impl implements Iface_BL_PayrollMng {
	private DAO_Nomina nom = new DAO_Nomina_Impl();
	private Nomina nom1 = new Nomina();
	
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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

}
