package Utama;

import javax.swing.*;
import java.sql.*;

public class mPegawai {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    protected Object[][] pegawai = new Object[100][6];
    protected Object[] fpegawai = new Object[6];

    public Object[] Find_pegawai(int id){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM pegawai WHERE id_pegawai = "+ id +" ");

            while (resultSet.next()) {
                fpegawai[0] = resultSet.getString("id_pegawai");
                fpegawai[1] = resultSet.getString("nama_pegawai");
                fpegawai[2] = resultSet.getString("password_pegawai");
                fpegawai[3] = resultSet.getString("jenis_kelamin_pegawai");
                fpegawai[4] = resultSet.getString("alamat_pegawai");
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

        return fpegawai;
    }

    public void Create_pegawai(Object[][] data){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO pegawai VALUES('','"+ data[0][0] +"','"+ data[0][1] +"',"+ data[0][2] +","+ data[0][3] +")");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (pegawai/create)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Update_pegawai(Object[][] data, int id){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE pegawai SET nama_pegawai = '"+ data[0][0] +"', jenis_pegawai = '"+ data[0][1] +"', harga_pegawai = "+ data[0][2] +", stok_pegawai = "+ data[0][3] +" WHERE id_pegawai = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (pegawai/update)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Delete_pegawai(int id){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir","root","");
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM pegawai WHERE id_pegawai = "+ id +"");
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak ada (pegawai/delete)","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
