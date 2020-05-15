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
            JOptionPane.showMessageDialog(null,"Database tidak ada (detailstruk/find)","Error",JOptionPane.ERROR_MESSAGE);
        }

        return struk;
    }

    public void Create_struk(int id_barang, int id_struk, int jumlah){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO detail_struk VALUES("+ id_barang +","+ id_struk +","+ jumlah +")");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (detailstruk/create)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
