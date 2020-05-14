package Utama;

import javax.swing.*;
import java.sql.*;

public class DetailStruk {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    protected Object[][] struk = new Object[1][5];

    public Object[][] Find_struk(int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM detail_struk WHERE id_struk = "+ id +" ");

            struk[0][0] = resultSet.getString("id_struk");
            struk[0][1] = resultSet.getString("id_pegawai");
            struk[0][2] = resultSet.getString("tanggal");

            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }

        return struk;
    }

    public void Create_struk(Object[][] data){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO detail_struk VALUES('"+ data[0][0] +"','"+ data[0][1] +"','"+ data[0][2] +"')");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
