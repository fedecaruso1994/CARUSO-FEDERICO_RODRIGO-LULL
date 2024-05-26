package com.backend.clinica_odontologica.repository.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2Connection {


    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/CARUSO-FEDERICO_LLULL-RODRIGO","sa","sa");
    }

    public static void createTable() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/CARUSO-FEDERICO_LLULL-RODRIGO;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa" );
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

}