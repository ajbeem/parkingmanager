package com.pcentaury.parkingmanager.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConect_Impl implements IfaceSingletonConect {
	static Connection conn= null;

	public SingletonConect_Impl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(urlHost, userName, userPwd);
					
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(conn!= null) {
			System.out.println("Conexion exitosa");
		}
	}

	@Override
	public Connection getConnection() {
		if(conn==null) {
			System.out.println("No existe conexión reintentando conectar");
			new SingletonConect_Impl();
		}else {
			System.out.println("Ya existe una conexion");
		}
		return conn;
	}

	@Override
	public void closeConnection() {
		if(conn!= null) {
			try {
				conn.close();
				conn= null;
				System.out.println("Se ha cerrado la conexion");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
