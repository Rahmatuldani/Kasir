package Utama;

import javax.swing.*;
import java.sql.*;

public class mStruk {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    protected Object[][] struk = new Object[get_data()][5];

    public Object[][] All_struk(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM struk" +
                    "                               join detailstruk on struk.id_struk = detail_struk.id_struk" +
                    "                               join pegawai on struk.id_pegawai = pegawai.id_pegawai");
            int p = 0;
            while (resultSet.next()){
                struk[p][0] = p+1;
                struk[p][1] = resultSet.getString("tanggal");
                struk[p][2] = resultSet.getString("id_struk");
                struk[p][3] = resultSet.getString("nama_pegawai");
                struk[p][4] = resultSet.getInt("harga_barang") * resultSet.getInt("jumlah");
                p++;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (barang/get)","Error",JOptionPane.ERROR_MESSAGE);
        }
        return struk;
    }

    public Object[][] Find_struk(int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
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

    public void Create_struk(Object[][] data){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO struk VALUES('',"+ data[0][0] +","+ data[0][1] +")");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (struk/create)","Error",JOptionPane.ERROR_MESSAGE);
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
}
