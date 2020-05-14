package Utama;

import javax.swing.*;
import java.sql.*;

public class mPegawai {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    protected Object[][] pegawai = new Object[100][6];

    public Object[][] Find_pegawai(int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM pegawai WHERE id_pegawai = "+ id +" ");

            pegawai[0][0] = resultSet.getString("id_pegawai");
            pegawai[0][1] = resultSet.getString("nama_pegawai");
            pegawai[0][2] = resultSet.getString("jenis_pegawai");
            pegawai[0][3] = resultSet.getString("harga_pegawai");
            pegawai[0][4] = resultSet.getString("stok_pegawai");

            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }

        return pegawai;
    }

    public void Create_pegawai(Object[][] data){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO pegawai VALUES('','"+ data[0][0] +"','"+ data[0][1] +"',"+ data[0][2] +","+ data[0][3] +")");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Update_pegawai(Object[][] data, int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE pegawai SET nama_pegawai = '"+ data[0][0] +"', jenis_pegawai = '"+ data[0][1] +"', harga_pegawai = "+ data[0][2] +", stok_pegawai = "+ data[0][3] +" WHERE id_pegawai = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Delete_pegawai(int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM pegawai WHERE id_pegawai = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
