package com.pcentaury.parkingmanager.businesslogic;

import javax.swing.JOptionPane;

import com.pcentaury.parkingmanager.models.Nivel;

public interface Iface_BL_LevelsMng {
	public void createLevel() throws Exception;

	public void getLevelData() throws Exception;

	public void updateLevel() throws Exception;

	public void deleteLevel() throws Exception;
	
	public void getListLevelData() throws Exception;

}
