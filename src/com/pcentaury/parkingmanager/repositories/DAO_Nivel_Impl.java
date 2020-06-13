package com.pcentaury.parkingmanager.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pcentaury.parkingmanager.models.Nivel;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class DAO_Nivel_Impl implements DAO_Nivel {
	IfaceSingletonConect conx = new SingletonConect_Impl();
	private PreparedStatement PsT;
	private Byte row;
	private ResultSet rs;

	@Override
	public void crearNivel(Nivel lv, String roll) throws Exception {
		if (roll == "ADMIN") {
			try {
				PsT = SingletonConect_Impl.conn.prepareStatement(
						"INSERT INTO nivel" + "(id, espaciostotales, espaciosdisponibles)" + "VALUES(null,?,?)");
				PsT.setByte(1, lv.getEspaciostotales());
				PsT.setByte(2, lv.getEspaciosdisponibles());
				row = (byte) PsT.executeUpdate();
				System.out.println("Registros agregados:" + row);
				PsT.close();
				row = null;
			} catch (Exception e) {
				throw e;
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No tienes permisos para realizar esta acción");
		}

	}

	@Override
	public Nivel getNivel(Nivel lv1) throws Exception {
		Nivel lvGet = new Nivel();
		try {
			PsT = SingletonConect_Impl.conn.prepareStatement("SELECT * FROM nivel WHERE id=?");
			PsT.setInt(1, lv1.getId());
			rs = PsT.executeQuery();
			while (rs.next()) {
				lvGet.setId(rs.getInt("id"));
				lvGet.setEspaciostotales(rs.getByte("espaciostotales"));
				lvGet.setEspaciosdisponibles(rs.getByte("espaciosdisponibles"));
			}
			PsT.close();
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return lvGet;
	}

	@Override
	public void updateNivel(Nivel lv2, String roll1) throws Exception {
		if (roll1 == "ADMIN") {
			try {
				try {
					PsT = SingletonConect_Impl.conn.prepareStatement(
							"UPDATE nivel SET espaciostotales=?, espaciosdisponibles=? WHERE id=?");
					PsT.setByte(1, lv2.getEspaciostotales());
					PsT.setByte(2, lv2.getEspaciosdisponibles());
					PsT.setInt(3, lv2.getId());
					row = (byte) PsT.executeUpdate();
					System.out.println("Registros actualizados:" + row);
					PsT.close();
					row = null;
				} catch (Exception e) {
					throw e;
				}
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No tienes permisos para realizar esta acción");
		}

	}

	@Override
	public void deleteNivel(Nivel lv3, String roll1) throws Exception {
		if (roll1 == "ADMIN") {
			try {
				PsT = SingletonConect_Impl.conn.prepareStatement("DELETE FROM nivel WHERE id=?");
				PsT.setInt(1, lv3.getId());
				row = (byte) PsT.executeUpdate();
				System.out.println("Registros borrados:" + row);
				PsT.close();
				row = null;
			} catch (Exception e) {
				throw e;
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No tienes permisos para realizar esta acción");
		}
	}

	@Override
	public List<Nivel> litLv() throws Exception {
		List<Nivel> lvList = new ArrayList();
		try {
			PsT = SingletonConect_Impl.conn.prepareStatement("SELECT * FROM nivel");
			rs = PsT.executeQuery();
			while (rs.next()) {
				Nivel lvGet01 = new Nivel();
				lvGet01.setId(rs.getInt("id"));
				lvGet01.setEspaciostotales(rs.getByte("espaciostotales"));
				lvGet01.setEspaciosdisponibles(rs.getByte("espaciosdisponibles"));
				lvList.add(lvGet01);
			}
			PsT.close();
			rs = null;
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return lvList;
	}

}
