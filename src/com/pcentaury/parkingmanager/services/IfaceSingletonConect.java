package com.pcentaury.parkingmanager.services;

import java.sql.Connection;

public interface IfaceSingletonConect {
	String urlHost ="jdbc:mysql://185.201.11.149:3306/u538442363_aGHVt";
	String driver="com.mysql.jdbc.Driver";
	String userName="u538442363_9QoPD";
	String userPwd= "server1root";	
	
	Connection getConnection();
	void closeConnection();

}
