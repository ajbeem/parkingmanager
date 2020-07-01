package com.pcentaury.parkingmanager.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.pcentaury.parkingmanager.models.Boletos;
import com.pcentaury.parkingmanager.models.Cajon;
import com.pcentaury.parkingmanager.models.Empleado;
import com.pcentaury.parkingmanager.services.IfaceSingletonConect;
import com.pcentaury.parkingmanager.services.SingletonConect_Impl;

public class DAO_Boletos_Impl implements DAO_Boletos {
	// Conexión
	// Abrir la conexion
	// Crear Prepared Statement
	// Añadir los atributos, construir PS
	// Realizar la consulta
	// Recuperar los datos
	//Enviar datos a capa superior
	// Cerrar PS, Conexion
	/*
	 * 	private Integer id;
	private String marca;
	private String modelo;
	private String color;
	private String placas;
	private String estadofisico;
	private Date fechaingreso;
	private Date horaingreso;
	private Date horasalida;
	private Integer id_cajon;
	private Integer id_acomodador;
	private Integer nombreacomodador;
	 */
	
	private Connection conTkc;
	private IfaceSingletonConect conx = new SingletonConect_Impl();
	private PreparedStatement PST;
	private PreparedStatement PST1;
	private Byte affectedrow = 0;
	private ResultSet rstTkct;
	private DAO_Cajon cj;
	private Cajon cajon;
	private Empleado emp1;
	private Boletos bolWork;

	@Override
	public Byte CreaBoleto(Boletos tck1, String roll) throws Exception {
		try {
			emp1 = new Empleado();
			cj = new DAO_Cajon_Impl();
			cajon = new Cajon();
			/*
			 *  INSERT INTO `boletos`(`id`, `marca`, `modelo`, `color`, `placas`, `estadofisico`, 
			 * `fechaingreso`, `horaingreso`, `horasalida`, `id_cajon`, `id_acomodador`, 
			 * `nombreacomodador`, `created_at`, `updated_at`) 
			 *  VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10],[value-11],[value-12],[value-13],[value-14])
			 */
			if(roll.equals("ADMIN")) {
				java.util.Date date=new java.util.Date();
				java.sql.Date sqlDate=new java.sql.Date(date.getTime());
				java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
				conTkc = conx.getConnection();
				/*PST = conTkc.prepareStatement("SELECT nombres FROM empleados WHERE id=?");
				PST.setInt(1, tck1.getId_acomodador());
				rstTkct = PST.executeQuery();
				while(rstTkct.next()) {
					emp1.setNombres(rstTkct.getString("nombres"));
				}*/
				PST1 = conTkc.prepareStatement("INSERT INTO boletos (id, marca, modelo, color, placas, estadofisico"
						+ ", fechaingreso, horaingreso, horasalida, id_cajon, id_acomodador, nombreacomodador)"
						+ " VALUES (null,?,?,?,?,?,?,?,null,?,?,(SELECT nombres FROM empleados WHERE id=?))");
				PST1.setString(1, tck1.getMarca());
				PST1.setString(2, tck1.getModelo());
				PST1.setString(3, tck1.getColor());
				PST1.setString(4, tck1.getPlacas());
				PST1.setString(5, tck1.getEstadofisico());
				PST1.setDate(6, sqlDate);
				PST1.setTimestamp(7, sqlTime);
				PST1.setInt(8, tck1.getId_cajon());
				PST1.setInt(9, tck1.getId_acomodador());
				PST1.setInt(10, tck1.getId_acomodador());
				affectedrow = (byte) PST1.executeUpdate();
				cajon.setId(tck1.getId_cajon());
				cajon.setDisponibilidad((byte)1);
				cj.asigCajon(cajon, roll);
				
			}else {
				System.out.println("No puedes realizar esta operación");
			}
			
		} catch (Exception e) {
			throw e;
		}finally {
			emp1 = null;
			cj = null;
			cajon = null;
			conx.closeConnection();
			//PST.close();
			PST1.close();
		}
		return affectedrow;
	}

	@Override
	public Boletos GetBoletoById(int idTck, String roll1) throws Exception {
		bolWork = new Boletos();
		if(roll1.equals("ADMIN")) {
			try {
				conTkc = conx.getConnection();
				PST = conTkc.prepareStatement("SELECT * FROM boletos WHERE  id=?");
				PST.setInt(1, idTck);
				rstTkct = PST.executeQuery();
				while(rstTkct.next()) {
					bolWork.setId(rstTkct.getInt("id"));
					bolWork.setPlacas(rstTkct.getString("placas"));
					bolWork.setHoraingreso(rstTkct.getTimestamp("horaingreso"));
					bolWork.setId_cajon(rstTkct.getInt("id_cajon"));
					bolWork.setNombreacomodador(rstTkct.getString("nombreacomodador"));
				}
			} catch (Exception e) {
				throw e;
			}finally {
				conx.closeConnection();
				PST.close();
				rstTkct.close();
			}
		}else {
			System.out.println("No tienes permiso para realizar esta accion...");
			return null;
		}
		return bolWork;
	}

	@Override
	public List<Boletos> getAllBoletos() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdtBoleto(Boletos tck2) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer DeleteBoleto(int idTck2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
