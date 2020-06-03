package com.pcentaury.parkingmanager.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pcentaury.parkingmanager.models.Empleado;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class DAO_EmpleadoImpl implements DAO_Empleado {
	IfaceSingletonConect conx = new SingletonConect_Impl();

	@Override
	public void CrearEmpleado(Empleado emp) throws Exception {
		// TODO Auto-generated method stub
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement PsT1 = SingletonConect_Impl.conn.prepareStatement(""
					+ "INSERT INTO empleados(id, nombres, apellidopaterno, apellidomaterno,"
					+ "nombreusuario, password, email, puesto, salariobase)"
					+ "VALUES(null,?,?,?,?,?,?,?,?)");
			PsT1.setString(1, emp.getNombres());
			PsT1.setString(2, emp.getApellidopaterno());
			PsT1.setString(3, emp.getApellidomaterno());
			PsT1.setString(4, emp.getNombreusuario());
			PsT1.setString(5, emp.getPassword());
			PsT1.setString(6, emp.getEmail());
			PsT1.setString(7, emp.getPuesto());
			PsT1.setFloat(8, emp.getSalariobase());
			
			int row = PsT1.executeUpdate();
			System.out.println("Registros agregados: "+ row);
			PsT1.close();
			
		} catch (Exception e) {
			throw e;
		}finally {
			conx.closeConnection();
		}
		
	}

	@Override
	public Empleado GetEmpleado(Empleado gEmp) throws Exception {
		Empleado emp1 = new Empleado();
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement PsT2 = SingletonConect_Impl.conn.prepareStatement(""
					+ "SELECT * FROM empleados WHERE id=?");
			PsT2.setInt(1, gEmp.getId());
			ResultSet rs = PsT2.executeQuery();
			while(rs.next()) {
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
			PsT2.close();
			
		} catch (Exception e) {
			throw e;
		}finally {
			conx.closeConnection();
		}
		return emp1;
		
	}
	

	@Override
	public List<Empleado> listarEmpleados() throws Exception {
		List <Empleado> list = new ArrayList();
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement PsT3 = SingletonConect_Impl.conn.prepareStatement(
					"SELECT * FROM empleados");
			ResultSet rs = PsT3.executeQuery();
			while(rs.next()) {
				Empleado emp2 = new Empleado();
				emp2.setId(rs.getInt("id"));
				emp2.setNombres(rs.getString("nombres"));
				emp2.setApellidopaterno(rs.getString("apellidopaterno"));
				emp2.setApellidomaterno(rs.getString("apellidomaterno"));
				emp2.setNombreusuario(rs.getString("nombreusuario"));
				emp2.setPassword(rs.getString("password"));
				emp2.setEmail(rs.getString("email"));
				emp2.setPuesto(rs.getString("puesto"));
				emp2.setSalariobase((float) rs.getFloat("salariobase"));
				list.add(emp2);
			}
			PsT3.close();
			
		} catch (Exception e) {
			throw e;
		}finally {
			conx.closeConnection();
		}
		return list;
	}

	@Override
	public void EditEmpleado(Empleado empEdit) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement PsT4 = SingletonConect_Impl.conn.prepareStatement(""
					+ "UPDATE empleados SET nombres=?,apellidopaterno=?,apellidomaterno=?"
					+ ",nombreusuario=?,password=?,email=?,puesto=?,salariobase=? "
					+ "WHERE id=?");
			PsT4.setString(1, empEdit.getNombres());
			PsT4.setString(2, empEdit.getApellidopaterno());
			PsT4.setString(3, empEdit.getApellidomaterno());
			PsT4.setString(4, empEdit.getNombreusuario());
			PsT4.setString(5, empEdit.getPassword());
			PsT4.setString(6, empEdit.getEmail());
			PsT4.setString(7, empEdit.getPuesto());
			PsT4.setFloat(8, empEdit.getSalariobase());
			PsT4.setInt(9, empEdit.getId());
			int row = PsT4.executeUpdate();
			System.out.println("Registro actualizado: "+row);
			PsT4.close();			
		} catch (Exception e) {
			throw e;
		}finally {
			conx.closeConnection();
		}
	}

	@Override
	public void EliminarEmpleado(Empleado empDel) throws Exception {
		try {
			SingletonConect_Impl.conn = conx.getConnection();
			PreparedStatement PsT5 = SingletonConect_Impl.conn.prepareStatement(""
					+ "DELETE FROM empleados WHERE id=?");
			PsT5.setInt(1, empDel.getId());
			int row = PsT5.executeUpdate();
			System.out.println("Registros borrados: "+row);
			PsT5.close();
		} catch (Exception e) {
			throw e;
		}finally {
			conx.closeConnection();
		}
		
	}

}
