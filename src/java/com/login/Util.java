/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mala singh
 */
public class Util {

    static final String URL = "jdbc:mysql://localhost:3306/";
    //static final String DATABASE_NAME = "imobicash";
    static final String DATABASE_NAME = "imobicoin";

    static final String USERNAME = "root";
    static final String PASSWORD="namrata";
//static final String PASSWORD = "zaI4!@#$%dkdkTsmHw";
//static final String PASSWORD="This@#1212";
    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD);
        return con;

    }
}
