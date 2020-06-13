package com.pcentaury.parkingmanager.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pcentaury.parkingmanager.models.Cajon;
import com.pcentaury.parkingmanager.models.Nivel;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class DAO_Cajon_Impl implements DAO_Cajon {
	IfaceSingletonConect conx = new SingletonConect_Impl();
	private PreparedStatement PsT;
	private Byte row;
	private ResultSet rs;
	private Connection connC;

	@Override
	public void CrearCajon(Cajon c, String rol) throws Exception {
		if (rol == "ADMIN") {
			try {
				connC = conx.getConnection();
				PsT = connC.prepareStatement("INSERT INTO cajon" + "(id, disponibilidad, id_piso) VALUES (null,?,?)");
				PsT.setByte(1, c.getDisponibilidad());
				PsT.setInt(2, c.getId_piso());
				for (byte i = 0; i < 3; i++) {
					row = (byte) PsT.executeUpdate();
					System.out.println("Registros agregados:" + row);
				}
				PsT.close();
				row = null;
			} catch (Exception e) {
				throw e;
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No estas autorizado para realizar esta acción");
		}
	}

	@Override
	public Cajon GetCajon(Cajon c1) throws Exception {
		Cajon cGet = new Cajon();
		try {
			connC = conx.getConnection();
			PsT = connC.prepareStatement("SELECT * FROM cajon WHERE id=?");
			PsT.setInt(1, c1.getId());
			rs = PsT.executeQuery();
			while (rs.next()) {
				cGet.setId(rs.getInt("id"));
				cGet.setDisponibilidad(rs.getByte("disponibilidad"));
				cGet.setId_piso(rs.getInt("id_piso"));
			}
			PsT.close();
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return cGet;
	}

	@Override
	public void EditCajon(Cajon c2, String rol2) throws Exception {
		if (rol2 == "ADMIN") {
			try {
				connC = conx.getConnection();
				PsT = connC.prepareStatement("UPDATE cajon SET disponibilidad=? WHERE id=?");
				PsT.setByte(1, c2.getDisponibilidad());
				PsT.setInt(2, c2.getId());
				row = (byte) PsT.executeUpdate();
				System.out.println("Registros actualizados:" + row);
				PsT.close();
				row = null;
			} catch (Exception e) {
				throw e;
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No estas autorizado para realizar esta acción");
		}

	}

	@Override
	public void EliminarCajon(Cajon c3, String rol3) throws Exception {
		if (rol3 == "ADMIN") {
			try {
				connC = conx.getConnection();
				PsT = connC.prepareStatement("DELETE FROM cajon WHERE id=?");
				PsT.setInt(1, c3.getId());
				row = (byte) PsT.executeUpdate();
				System.out.println("Registros Borrados:" + row);
				PsT.close();
				row = null;
			} catch (Exception e) {
				throw e;
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No estas autorizado para realizar esta acción");
		}

	}

	@Override
	public List<Cajon> listarCajones(Cajon c4) throws Exception {
		List<Cajon> listCajon = new ArrayList();
		try {
			connC = conx.getConnection();
			PsT = connC.prepareStatement("SELECT * FROM cajon WHERE id_piso=?");
			PsT.setInt(1, c4.getId_piso());
			rs = PsT.executeQuery();
			while (rs.next()) {
				Cajon cGet = new Cajon();
				cGet.setId(rs.getInt("id"));
				cGet.setDisponibilidad(rs.getByte("disponibilidad"));
				cGet.setId_piso(rs.getInt("id_piso"));
				listCajon.add(cGet);
			}
			PsT.close();
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return listCajon;
	}

	@Override
	public List<Cajon> listarCajonesDisponibles(Cajon c5) throws Exception {
		List<Cajon> listCajon1 = new ArrayList();
		try {
			connC = conx.getConnection();
			PsT = connC.prepareStatement("SELECT * FROM cajon WHERE id_piso=? AND disponibilidad=?");
			PsT.setInt(1, c5.getId_piso());
			PsT.setByte(2, (byte) 0);
			rs = PsT.executeQuery();
			while (rs.next()) {
				Cajon cGet = new Cajon();
				cGet.setId(rs.getInt("id"));
				cGet.setDisponibilidad(rs.getByte("disponibilidad"));
				cGet.setId_piso(rs.getInt("id_piso"));
				listCajon1.add(cGet);
			}
			PsT.close();
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return listCajon1;
	}

	@Override
	public void asigCajon(Cajon c6, String rol4) throws Exception {
		Cajon cGet = new Cajon();
		Nivel nvGet = new Nivel();
		if (rol4 == "ADMIN") {
			try {
				connC = conx.getConnection();
				PsT = connC.prepareStatement(
						"SELECT c.id_piso, c.disponibilidad, n.id, n.espaciosdisponibles " 
						+ "FROM cajon c INNER JOIN nivel n ON c.id_piso = n.id "
						+ "WHERE c.id = ?");
				PsT.setInt(1, c6.getId());
				rs = PsT.executeQuery();
				while (rs.next()) {
					cGet.setId_piso(rs.getInt("c.id_piso"));
					cGet.setDisponibilidad(rs.getByte("c.disponibilidad"));
					nvGet.setId(rs.getInt("n.id"));
					nvGet.setEspaciosdisponibles(rs.getByte("n.espaciosdisponibles"));
				}
				System.out.println("Id piso: "+nvGet.getId());
				System.out.println("Espacios Disponibles: "+nvGet.getEspaciosdisponibles());
				System.out.println("Cajon del piso: "+cGet.getId_piso());
				System.out.println("Disponibilidad: "+cGet.getDisponibilidad());
				if(cGet.getDisponibilidad()==1) {
					System.out.println("El cajon ya se encuentra ocupado...");
					return;
				}else {
					byte dif = (byte)(nvGet.getEspaciosdisponibles()-1);
					System.out.println("Espacios actualizados: "+dif);
					PsT = connC.prepareStatement(
							"UPDATE cajon c INNER JOIN nivel n "
							+ "ON c.id_piso = n.id SET c.disponibilidad=?, n.espaciosdisponibles=?"
							+ " WHERE c.id = ?");
					PsT.setByte(1, c6.getDisponibilidad());
					PsT.setByte(2, dif);
					PsT.setInt(3, c6.getId());
					int res1 = PsT.executeUpdate();
					System.out.println("Registros actualizados: "+res1);
				}				
				PsT.close();
			} catch (Exception e) {
				throw e;
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No estas autorizado para realizar esta acción");
		}
	}

	@Override
	public void liberarCajon(Cajon cj7, String rol5) throws Exception {
		Cajon cGet1 = new Cajon();
		Nivel nvGet1 = new Nivel();
		if (rol5 == "ADMIN") {
			try {
				connC = conx.getConnection();
				PsT = connC.prepareStatement(
						"SELECT c.id_piso, c.disponibilidad, n.id, n.espaciosdisponibles " 
						+ "FROM cajon c INNER JOIN nivel n ON c.id_piso = n.id "
						+ "WHERE c.id = ?");
				PsT.setInt(1, cj7.getId());
				rs = PsT.executeQuery();
				while (rs.next()) {
					cGet1.setId_piso(rs.getInt("c.id_piso"));
					cGet1.setDisponibilidad(rs.getByte("c.disponibilidad"));
					nvGet1.setId(rs.getInt("n.id"));
					nvGet1.setEspaciosdisponibles(rs.getByte("n.espaciosdisponibles"));
				}
				System.out.println("Id piso: "+nvGet1.getId());
				System.out.println("Espacios Disponibles: "+nvGet1.getEspaciosdisponibles());
				System.out.println("Cajon del piso: "+cGet1.getId_piso());
				System.out.println("Disponibilidad: "+cGet1.getDisponibilidad());
				if(cGet1.getDisponibilidad()==0) {
					System.out.println("El cajon ya se encuentra disponible...");
					return;
				}else {
					byte dif1 = (byte)(nvGet1.getEspaciosdisponibles()+1);
					System.out.println("Espacios actualizados: "+dif1);
					cj7.setDisponibilidad((byte) 0);
					PsT = connC.prepareStatement(
							"UPDATE cajon c INNER JOIN nivel n "
							+ "ON c.id_piso = n.id SET c.disponibilidad=?, n.espaciosdisponibles=?"
							+ " WHERE c.id = ?");
					PsT.setByte(1, cj7.getDisponibilidad());
					PsT.setByte(2, dif1);
					PsT.setInt(3, cj7.getId());
					int res2 = PsT.executeUpdate();
					System.out.println("Registros actualizados: "+res2);
				}				
				PsT.close();
			} catch (Exception e) {
				throw e;
			} finally {
				conx.closeConnection();
			}
		} else {
			System.out.println("No estas autorizado para realizar esta acción");
			return;
		}
	}

}
