package com.pcentaury.parkingmanager.app;

import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class PM_Aplication {
	IfaceSingletonConect conx = new SingletonConect_Impl();

	public PM_Aplication() {
		conx.getConnection();
	}
	
	public void pm_closeCnx() {
		conx.closeConnection();
	}

	public static void main(String[] args) {
		PM_Aplication objt1 = new PM_Aplication();
		objt1.pm_closeCnx();
	}

}
