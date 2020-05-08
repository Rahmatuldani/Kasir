package Utama;

import javax.swing.*;
import java.sql.*;

public class mBarang {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    protected Object[][] barang = new Object[100][6];
    public Object[][] All_barang(){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM barang");
            int p = 0;
            while (resultSet.next()){
                barang[p][0] = resultSet.getString("id_barang");
                barang[p][1] = resultSet.getString("nama_barang");
                barang[p][2] = resultSet.getString("jenis_barang");
                barang[p][3] = resultSet.getString("harga_barang");
                barang[p][4] = resultSet.getString("stok_barang");
                p++;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        return barang;
    }
    
    public Object[][] Find_barang(int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM barang WHERE id_barang = "+ id +" ");

                barang[0][0] = resultSet.getString("id_barang");
                barang[0][1] = resultSet.getString("nama_barang");
                barang[0][2] = resultSet.getString("jenis_barang");
                barang[0][3] = resultSet.getString("harga_barang");
                barang[0][4] = resultSet.getString("stok_barang");

            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }

        return barang;
    }

    public void Create_barang(Object[][] data){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO barang VALUES('','"+ data[0][0] +"','"+ data[0][1] +"',"+ data[0][2] +","+ data[0][3] +")");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Update_barang(Object[][] data, int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE barang SET nama_barang = '"+ data[0][0] +"', jenis_barang = '"+ data[0][1] +"', harga_barang = "+ data[0][2] +", stok_barang = "+ data[0][3] +" WHERE id_barang = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Delete_barang(int id){
        try {
            connection = DriverManager.getConnection("mysql:jdbc://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM barang WHERE id_barang = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
