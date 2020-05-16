package Utama;

import javax.swing.*;
import java.sql.*;

public class mStruk {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    protected Object[][] struk = new Object[get_data()][5];

    public Object[][] Find_struk(int id){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM struk WHERE id_struk = "+ id +" ");

            struk[0][0] = resultSet.getString("id_struk");
            struk[0][1] = resultSet.getString("id_pegawai");
            struk[0][2] = resultSet.getString("tanggal");

            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (struk/find)","Error",JOptionPane.ERROR_MESSAGE);
        }

        return struk;
    }

    public void Create_struk(int id_struk,int id, String date){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO struk VALUES("+ id_struk +","+ id +",'"+ date +"')");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error create struk",JOptionPane.ERROR_MESSAGE);
        }
    }

    public int get_data(){
        int jum = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM struk");
            while (resultSet.next()){
                jum++;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (barang/get)","Error",JOptionPane.ERROR_MESSAGE);
        }
        return jum;
    }

    public Object[][] Read_struk(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * ,sum(barang.harga_barang*detail_struk.jumlah) AS pendapatan " +
                            "FROM struk inner join detail_struk on struk.id_struk = detail_struk.id_struk " +
                            "inner join pegawai on struk.id_pegawai = pegawai.id_pegawai " +
                            "inner join barang on detail_struk.id_barang = barang.id_barang " +
                            "group by struk.id_struk");
            int p = 0;
            while (resultSet.next()) {
                struk[p][0] = resultSet.getString("tanggal");
                struk[p][1] = resultSet.getString("id_struk");
                struk[p][2] = resultSet.getString("nama_pegawai");
                struk[p][3] = resultSet.getInt("pendapatan");
                p++;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

        return struk;
    }
}
