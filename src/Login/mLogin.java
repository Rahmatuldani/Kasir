package Login;

import javax.swing.*;
import java.sql.*;

public class mLogin {
    private String id;
    private String password;
    private Listener listener;

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    String[] data = new String[4];
    protected void fireOnChange(){
        if (listener != null) {
            listener.onChange(this);
        }
    }

    public Listener getListener(){
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        fireOnChange();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        fireOnChange();
    }

    public void submitForm(vLogin vlogin){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM pegawai WHERE id_pegawai = '"+ vlogin.getFid().getText() +"'");
            while (resultSet.next()){
                data[0] = resultSet.getString("id_pegawai");
                data[1] = resultSet.getString("nama_pegawai");
                data[2] = resultSet.getString("jenis_kelamin_pegawai");
                data[3] = resultSet.getString("alamat_pegawai");
            }
            if (data[0] == null){
                JOptionPane.showMessageDialog(null,"ID tidak ditemukan", "Database Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, data[0] + " - " + data[1] + " - " + data[2] + " - " + data[3], "Database", JOptionPane.INFORMATION_MESSAGE);
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak terkoneksi", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
