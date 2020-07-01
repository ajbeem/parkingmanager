package com.pcentaury.parkingmanager.businesslogic;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Nomina;

public interface Iface_BL_PayrollMng {
	void NewPayroll() throws Exception;

	void getPayroll() throws Exception;

	void updatePayroll() throws Exception;

	void deletePayroll() throws Exception;

	void listPayroll() throws Exception;
}
