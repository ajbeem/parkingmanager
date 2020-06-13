package com.pcentaury.parkingmanager.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pcentaury.parkingmanager.models.Nomina;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class DAO_Nomina_Impl implements DAO_Nomina {
	IfaceSingletonConect conx = new SingletonConect_Impl();
	private PreparedStatement PsT;
	private Byte row;
	private ResultSet rs;

	@Override
	public void CrearNomina(Nomina nom) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PsT = SingletonConect_Impl.conn
					.prepareStatement("" + "INSERT INTO nomina(id, fechainicio, fechafin, ingresostotales,"
							+ "serviciostotales, pagosrealizados)" + "VALUES(null,?,?,?,?,?)");
			PsT.setDate(1, (Date) nom.getFechainicio());
			PsT.setDate(2, (Date) nom.getFechafin());
			PsT.setFloat(3, nom.getIngresostotales());
			PsT.setInt(4, nom.getServiciostotales());
			PsT.setFloat(5, nom.getPagostotales());
			row = (byte) PsT.executeUpdate();
			System.out.println("Registros agregados:" + row);
			PsT.close();
			row = null;

		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
	}

	@Override
	public Nomina getNomina(Nomina gNom) throws Exception {
		Nomina nomget = new Nomina();
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PsT = SingletonConect_Impl.conn.prepareStatement("" + "SELECT * FROM nomina WHERE id=?");
			PsT.setInt(1, gNom.getId());
			rs = PsT.executeQuery();
			while (rs.next()) {
				nomget.setId(rs.getInt("id"));
				nomget.setFechainicio(rs.getDate("fechainicio"));
				nomget.setFechafin(rs.getDate("fechafin"));
				nomget.setIngresostotales(rs.getFloat("ingresostotales"));
				nomget.setServiciostotales(rs.getInt("serviciostotales"));
				nomget.setPagostotales(rs.getFloat("pagosrealizados"));
			}
			PsT.close();
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return nomget;
	}

	@Override
	public void updateNomina(Nomina uNom) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PsT = SingletonConect_Impl.conn
					.prepareStatement("UPDATE nomina SET fechainicio=?, fechafin=?, ingresostotales=?,"
							+ "serviciostotales=?, pagosrealizados=?" + "WHERE id=?");
			PsT.setDate(1, (Date) uNom.getFechainicio());
			PsT.setDate(2, (Date) uNom.getFechafin());
			PsT.setFloat(3, uNom.getIngresostotales());
			PsT.setInt(4, uNom.getServiciostotales());
			PsT.setFloat(5, uNom.getPagostotales());
			PsT.setInt(6, uNom.getId());
			row = (byte) PsT.executeUpdate();
			System.out.println("Registros Actualizados:" + row);
			PsT.close();
			row = null;
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}

	}

	@Override
	public void deleteNomina(Nomina delNom) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PsT = SingletonConect_Impl.conn.prepareStatement("DELETE FROM nomina WHERE id=?");
			PsT.setInt(1, delNom.getId());
			row = (byte) PsT.executeUpdate();
			System.out.println("Registros Borrados:" + row);
			PsT.close();
			row = null;
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
	}

	@Override
	public List<Nomina> listNom() throws Exception {
		List<Nomina> list = new ArrayList<Nomina>();
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PsT = SingletonConect_Impl.conn.prepareStatement("" + "SELECT * FROM nomina");
			rs = PsT.executeQuery();
			while (rs.next()) {
				Nomina nomget = new Nomina();
				nomget.setId(rs.getInt("id"));
				nomget.setFechainicio(rs.getDate("fechainicio"));
				nomget.setFechafin(rs.getDate("fechafin"));
				nomget.setIngresostotales(rs.getFloat("ingresostotales"));
				nomget.setServiciostotales(rs.getInt("serviciostotales"));
				nomget.setPagostotales(rs.getFloat("pagosrealizados"));
				list.add(nomget);
			}
			PsT.close();
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return list;
	}

}
