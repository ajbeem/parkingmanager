package com.pcentaury.parkingmanager.app;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Boletos;
import com.pcentaury.parkingmanager.models.Cajon;
import com.pcentaury.parkingmanager.models.Empleado;
import com.pcentaury.parkingmanager.models.Nivel;
import com.pcentaury.parkingmanager.models.Nomina;
import com.pcentaury.parkingmanager.repositories.DAO_Boletos;
import com.pcentaury.parkingmanager.repositories.DAO_Boletos_Impl;
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
	/*
	 * public void pm_closeCnx() { conx.closeConnection(); }
	 */
	private DAO_Boletos repBoletos;
	private Boletos boletoWork;
	private Scanner scan;
	
	public void getTicket() {
		scan = new Scanner(System.in);
		try {
			repBoletos = new DAO_Boletos_Impl();
			boletoWork = new Boletos();
			System.out.println("Ingrese el boleto a buscar: ");
			int idTck= scan.nextInt();
			boletoWork = repBoletos.GetBoletoById(idTck, "OTHER");
			if (boletoWork!= null) {
				System.out.println("Placas vehiculo: "+boletoWork.getPlacas());
				System.out.println("Id cajon: "+boletoWork.getId_cajon());
				System.out.println("Nombre acomodador: "+boletoWork.getNombreacomodador());
				System.out.println("Hora de ingreso: "+boletoWork.getHoraingreso());
				System.out.println("*********--------------**********");
			}else {
				JOptionPane.showMessageDialog(null, "No se recuperaron valores para ID: "+idTck,"SIN DATOS",JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado");
			e.printStackTrace();
		}finally {
			scan.reset();
			scan.close();
			boletoWork = null;
			repBoletos = null;
		}
	}
	
	public void CreateTicket() {
		try {
			repBoletos = new DAO_Boletos_Impl();
			boletoWork = new Boletos("VW", "Sedan", "Negro", "MRV1251", "Excelente",
					4, 1);
			repBoletos.CreaBoleto(boletoWork, "ADMIN");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido algo inesperado");
			e.printStackTrace();
		}finally {
			repBoletos = null;
			boletoWork = null;
		}
	} 
	

	private void getHour() {
		/*Calendar cal = Calendar.getInstance();
		java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
		System.out.println(""+sqlDate);*/
		Calendar localTime = Calendar.getInstance();
		Calendar germanyTime = new GregorianCalendar(TimeZone.getTimeZone("America/Mexico_City"));
	    germanyTime.setTimeInMillis(localTime.getTimeInMillis());
	    int hour = germanyTime.get(Calendar.HOUR);
	    int minute = germanyTime.get(Calendar.MINUTE);
	    int second = germanyTime.get(Calendar.SECOND);

	    System.out.printf("Germany time: %02d:%02d:%02d\n", hour, minute, second);
	}
	
	private static java.sql.Date convertDate(String fecha) {
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
	
	private static java.sql.Date convertTime(String fecha) {
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
	
	
	public PM_Aplication() {
		// conx.getConnection();
	}

	


	public static void main(String[] args) {
		PM_Aplication objt1 = new PM_Aplication();
		//objt1.CreateTicket();
		objt1.getTicket();
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
		//objt1.freeUpParkingDrawer(2);
	}

}
