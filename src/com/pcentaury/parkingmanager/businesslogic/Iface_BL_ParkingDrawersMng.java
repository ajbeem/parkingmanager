package com.pcentaury.parkingmanager.businesslogic;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Cajon;

public interface Iface_BL_ParkingDrawersMng {
	public void getParkingDrawer() throws Exception;

	public void deleteParkingDrawer() throws Exception;

	public void getAllParkingDrawer() throws Exception;

	public void getAllAvailableParkingDrawer() throws Exception;

	public void setParkingDrawer() throws Exception;

	public void updateParkingDrawerOcup(int idC) throws Exception;
	
	public void freeUpParkingDrawer(int idC1) throws Exception;
}
