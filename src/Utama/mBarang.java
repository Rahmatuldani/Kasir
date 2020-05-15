package Utama;

import javax.swing.*;
import java.sql.*;

public class mBarang {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    protected Object[][] dbarang = new Object[get_data()][6];
    protected Object[] nbarang = new Object[get_data()];

    mBarang(){
        All_barang();
    }
    public Object[][] All_barang(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM barang");
            int p = 0;
            while (resultSet.next()){
                dbarang[p][0] = p+1;
                dbarang[p][1] = resultSet.getString("id_barang");
                dbarang[p][2] = resultSet.getString("nama_barang");
                nbarang[p] = resultSet.getString("nama_barang");
                dbarang[p][3] = resultSet.getString("jenis_barang");
                dbarang[p][4] = resultSet.getString("harga_barang");
                dbarang[p][5] = resultSet.getString("stok_barang");
                p++;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (barang/get)","Error",JOptionPane.ERROR_MESSAGE);
        }
        return dbarang;
    }
    
    public Object[][] Find_barang(int id, int no, int jumlah){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM barang WHERE id_barang = "+ id +" ");
            int p = 0;
            while (resultSet.next()) {
                dbarang[p][0] = no;
                dbarang[p][1] = resultSet.getString("id_barang");
                dbarang[p][2] = resultSet.getString("nama_barang");
                dbarang[p][3] = resultSet.getString("harga_barang");
                dbarang[p][4] = jumlah;
                dbarang[p][5] = resultSet.getInt("harga_barang") * jumlah;
            }

            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

        return dbarang;
    }

    public void Create_barang(Object[][] data){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO barang VALUES('','"+ data[0][0] +"','"+ data[0][1] +"',"+ data[0][2] +","+ data[0][3] +")");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (barang/create)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Update_barang(Object[][] data, int id){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE barang SET nama_barang = '"+ data[0][0] +"', jenis_barang = '"+ data[0][1] +"', harga_barang = "+ data[0][2] +", stok_barang = "+ data[0][3] +" WHERE id_barang = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (barang/update)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Delete_barang(int id){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM barang WHERE id_barang = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (barang/delete)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public int get_data(){
        int jum = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM barang");
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
