package Utama;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;
import java.text.*;

public class vUtama extends JFrame{
    JTabbedPane tab = new JTabbedPane();
    JPanel pKasir = new JPanel();
    JPanel pBarang = new JPanel();
    JPanel pLaporan = new JPanel();

    //    Koneksi
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    //    Bagian Kasir
    JLabel lno = new JLabel("No Transaksi : ",SwingConstants.RIGHT);
    JLabel ltanggal = new JLabel("Tanggal : ",SwingConstants.RIGHT);
    JLabel lkasir = new JLabel("Kasir : ",SwingConstants.RIGHT);
    JLabel lkode = new JLabel("Kode Item : ",SwingConstants.RIGHT);
    JLabel ljum = new JLabel("Jumlah : ",SwingConstants.RIGHT);

    JTextField fno = new JTextField(30);
    JTextField ftanggal = new JTextField(30);
    JTextField fkasir = new JTextField(30);
    JTextField fkode = new JTextField(15);
    JTextField fjum = new JTextField(7);
    JTextField ftotal = new JTextField("12.000",30);

    JButton bTambah = new JButton("Tambah");
    JButton bEdit = new JButton("Edit");
    JButton bHapus = new JButton("Hapus");
    JButton bBayar = new JButton("Bayar");

    JPanel data = new JPanel();
    JPanel dbarang = new JPanel();
    String[] column = {"No","Kode Item","Nama Barang","Harga","Jumlah","Total"};
    int a = 12000;
    int b = 20;
    Object[][] tdata = {{"1","123","pensil",a,b,new Integer(a*b)}};
    JTable table = new JTable(tdata,column);
    DefaultTableCellRenderer righttable = new DefaultTableCellRenderer();
    JScrollPane jScrollPane = new JScrollPane(table);
//    End Bagian Kasir


    //  Bagian Barang
    JPanel bHeader = new JPanel();
    JPanel tBarang = new JPanel();

    public vUtama(){
        setTitle("Sistem Kasir");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        tab.addTab("Kasir",pKasir);
        tab.addTab("Barang",pBarang);
        tab.addTab("Laporan",pLaporan);

//        Bagian Kasir
        pKasir.setLayout(null);
        dbarang.setLayout(new FlowLayout(FlowLayout.LEADING,30,3));
        dbarang.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        data.setLayout(null);
        data.setSize(300,300);
        ftotal.setSize(200,200);
        ftotal.setFont(new Font("SansSerif",Font.PLAIN,90));
        ftotal.setHorizontalAlignment(SwingConstants.RIGHT);
        ftotal.setEditable(false);

//        Setting Tabel
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(20);
        table.setFont(new Font("SansSerif",Font.PLAIN,14));
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(567);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(300);
        righttable.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(3).setCellRenderer(righttable);
        table.getColumnModel().getColumn(4).setCellRenderer(righttable);
        table.getColumnModel().getColumn(5).setCellRenderer(righttable);

//        Tambah ke panel data
        data.add(lno);
        data.add(fno);
        data.add(ltanggal);
        Timer date = new Timer();
        date.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ftanggal.setText(new SimpleDateFormat("E, dd MMMM yyyy HH:mm:ss").format(new java.util.Date()));
            }
        },0,1000);
        data.add(ftanggal);
        data.add(lkasir);
        data.add(fkasir);
        pKasir.add(data);

//        Tambah ke panel kode barang
        dbarang.add(lkode);
        dbarang.add(fkode);
        dbarang.add(ljum);
        dbarang.add(fjum);
        dbarang.add(bTambah);
        dbarang.add(bHapus);
        dbarang.add(bBayar);
        pKasir.add(dbarang);

//        tampilan total dan tabel di panel kasir
        pKasir.add(ftotal);
        pKasir.add(jScrollPane);

//        set bound panel data
        lno.setBounds(10,10,100,20);
        fno.setBounds(120,10,160,20);
        ltanggal.setBounds(10,40,100,20);
        ftanggal.setBounds(120,40,160,20);
        lkasir.setBounds(10,70,100,20);
        fkasir.setBounds(120,70,160,20);
        ftotal.setBounds(320,20,1165,110);

//        set panel data, kode barang dan tabel
        data.setBounds(10,10,300,130);
        dbarang.setBounds(10,140,800,30);
        jScrollPane.setBounds(10,180,1500,600);

        add(tab);
        tab.setBounds(10,10,1520,825);

//      End Bagian Kasir

//         Bagian Barang
        pBarang.setLayout(new BoxLayout(pBarang,BoxLayout.LINE_AXIS));
        String[] kolomBarang = {"Kode Item","Nama Barang","Jenis Barang","Harga","Stok","Aksi"};
        String[][] listbarang = new String[readBarang()][6];
        JTable barangJTable = new JTable(readBarang(listbarang),kolomBarang);
        JScrollPane BarangjScrollPane = new JScrollPane(barangJTable);

//        Setting Tabel
        barangJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        barangJTable.setRowHeight(20);
        barangJTable.setFont(new Font("SansSerif",Font.PLAIN,14));
        barangJTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        barangJTable.getColumnModel().getColumn(1).setPreferredWidth(567);
        barangJTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        barangJTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        barangJTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        barangJTable.getColumnModel().getColumn(5).setPreferredWidth(330);
        righttable.setHorizontalAlignment(JLabel.RIGHT);
        barangJTable.getColumnModel().getColumn(3).setCellRenderer(righttable);
        barangJTable.getColumnModel().getColumn(4).setCellRenderer(righttable);
        barangJTable.getColumnModel().getColumn(5).setCellRenderer(righttable);

//      Memasukkan Data ke dalam Frame
        pBarang.add(BarangjScrollPane);


//        Bagian Laporan
        pLaporan.setLayout(new BoxLayout(pLaporan,BoxLayout.LINE_AXIS));
        String[] kolomLaporan = {"Tanggal","ID Struk","Nama Kasir","Pendapatan"};
        String[][] liststruk = new String[readStruk()][4];
        JTable laporanJTable = new JTable(readStruk(liststruk),kolomLaporan);
        JScrollPane LaporanjScrollPane = new JScrollPane(laporanJTable);
    }


    //pindahin ke controller
    public int readBarang(){
        int i=0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM barang");
            while (resultSet.next()){
                i=i+1;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak terkoneksi", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return i;
    }

    public String[][] readBarang(String listbarang[][]){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM barang");
            int i=0;
            while (resultSet.next()){
                listbarang[i][0] = resultSet.getString("id_barang");
                listbarang[i][1] = resultSet.getString("nama_barang");
                listbarang[i][2] = resultSet.getString("jenis_barang");
                listbarang[i][3] = resultSet.getString("harga_barang");
                listbarang[i][4] = resultSet.getString("stok");
                listbarang[i][5] = "null";
                i=i+1;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak terkoneksi", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return listbarang;
    }

    public int readStruk(){
        int i=0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT struk.id_struk,struk.id_pegawai,tanggal,sum(jumlah) "
                    + "FROM struk inner join detailstruk on struk.id_struk = detailstruk_id.struk "
                    + "inner join pegawai on struk.id_pegawai = pegawai.id_pegawai group by struk.id_struk");
            while (resultSet.next()){
                i=i+1;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak terkoneksi", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return i;
    }

    public String[][] readStruk(String listbarang[][]){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kasir", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT struk.id_struk,struk.id_pegawai,tanggal,sum(jumlah) AS pendapatan "
                    + "FROM struk inner join detailstruk on struk.id_struk = detailstruk_id.struk "
                    + "inner join pegawai on struk.id_pegawai = pegawai.id_pegawai group by struk.id_struk");
            int i=0;
            while (resultSet.next()){
                listbarang[i][0] = resultSet.getString("id_struk");
                listbarang[i][1] = resultSet.getString("id_pegawai");
                listbarang[i][2] = resultSet.getString("tanggal");
                listbarang[i][3] = resultSet.getString("pendapatan");
                i=i+1;
            }
            statement.close();
            connection.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Database tidak terkoneksi", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return listbarang;
    }

    public void crud(){

    }

    public static void main(String[] args) {
        new vUtama();
    }
}