package com.pcentaury.parkingmanager.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pcentaury.parkingmanager.models.Empleado;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class DAO_EmpleadoImpl implements DAO_Empleado {
	IfaceSingletonConect conx = new SingletonConect_Impl();
	private List<Empleado> empList = new ArrayList();
	// private Connection conn;

	@Override
	public void CrearEmpleado(Empleado emp) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			// conn = conx.getConnection();
			PreparedStatement pstat = SingletonConect_Impl.conn
					.prepareStatement("INSERT INTO empleados(id, nombres, apellidopaterno, apellidomaterno,"
							+ "nombreusuario, password, email, puesto, salariobase)" + "VALUES (null,?,?,?,?,?,?,?,?)");
			pstat.setString(1, emp.getNombres());
			pstat.setString(2, emp.getApellidopaterno());
			pstat.setString(3, emp.getApellidomaterno());
			pstat.setString(4, emp.getNombreusuario());
			pstat.setString(5, emp.getPassword());
			pstat.setString(6, emp.getEmail());
			pstat.setString(7, emp.getPuesto());
			pstat.setFloat(8, emp.getSalariobase());
			int rowAffected = pstat.executeUpdate();
			System.out.println("Resultado de la consulta: " + rowAffected);

		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
	}

	@Override
	public Empleado GetEmpleado(Empleado gEmp) throws Exception {
		System.out.println("Parametro recibido: "+ gEmp.getId());
		Empleado emp1 = new Empleado();
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement psT = SingletonConect_Impl.conn.prepareStatement("SELECT * FROM empleados WHERE id = ?");
			psT.setInt(1, gEmp.getId());
			ResultSet rs = psT.executeQuery();
			while (rs.next()) {				
				emp1.setId(rs.getInt("id"));
				emp1.setNombres(rs.getString("nombres"));
				emp1.setApellidopaterno(rs.getString("apellidopaterno"));
				emp1.setApellidomaterno(rs.getString("apellidomaterno"));
				emp1.setNombreusuario(rs.getString("nombreusuario"));
				emp1.setPassword(rs.getString("password"));
				emp1.setEmail(rs.getString("email"));
				emp1.setPuesto(rs.getString("puesto"));
				emp1.setSalariobase((float) rs.getFloat("salariobase"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
		return emp1;

	}

	@Override
	public void EditEmpleado(Empleado empEdit) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement PsT = SingletonConect_Impl.conn.prepareStatement(
					"UPDATE empleados SET nombres=?,apellidopaterno=?,apellidomaterno=?,nombreusuario=?,password=?,"
							+ "email=?,puesto=?,salariobase=? WHERE id=?");
			PsT.setString(1, empEdit.getNombres());
			PsT.setString(2, empEdit.getApellidopaterno());
			PsT.setString(3, empEdit.getApellidomaterno());
			PsT.setString(4, empEdit.getNombreusuario());
			PsT.setString(5, empEdit.getPassword());
			PsT.setString(6, empEdit.getEmail());
			PsT.setString(7, empEdit.getPuesto());
			PsT.setFloat(8, empEdit.getSalariobase());
			PsT.setInt(9, empEdit.getId());
			int row = PsT.executeUpdate();
			PsT.close();
			System.out.println(row + " registro(s) actualizados");
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
	}

	@Override
	public void EliminarEmpleado(Empleado empDel) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement PstDel = SingletonConect_Impl.conn.prepareStatement(
					"DELETE FROM empleados WHERE id=?"
					);
			PstDel.setInt(1, empDel.getId());
			int resultado = PstDel.executeUpdate();
			PstDel.close();
			System.out.println("Resultado de la consulta: "+resultado);
		} catch (Exception e) {
			throw e;
		} finally {
			conx.closeConnection();
		}
	}

	@Override
	public List<Empleado> listarEmpleados() throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement psT = SingletonConect_Impl.conn.prepareStatement("SELECT * FROM empleados");
			ResultSet rs = psT.executeQuery();
			while (rs.next()) {
				Empleado emp1 = new Empleado();
				emp1.setId(rs.getInt("id"));
				emp1.setNombres(rs.getString("nombres"));
				emp1.setApellidopaterno(rs.getString("apellidopaterno"));
				emp1.setApellidomaterno(rs.getString("apellidomaterno"));
				emp1.setNombreusuario(rs.getString("nombreusuario"));
				emp1.setPassword(rs.getString("password"));
				emp1.setEmail(rs.getString("email"));
				emp1.setPuesto(rs.getString("puesto"));
				emp1.setSalariobase((float) rs.getFloat("salariobase"));
				empList.add(emp1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SingletonConect_Impl.conn.close();
			conx.closeConnection();
		}
		return empList;
	}

}
