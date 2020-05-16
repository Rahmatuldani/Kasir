package Utama;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.text.*;

public class vUtama extends JFrame {
    cUtama controller;
    JTabbedPane tab = new JTabbedPane();
    JPanel pKasir = new JPanel();
    JPanel pBarang = new JPanel();
    JPanel pLaporan = new JPanel();
    int no = 0;
    
//    Bagian Kasir
    JLabel lno = new JLabel("No Transaksi : ",SwingConstants.RIGHT);
    JLabel ltanggal = new JLabel("Tanggal : ",SwingConstants.RIGHT);
    JLabel lkasir = new JLabel("Kasir : ",SwingConstants.RIGHT);
    JLabel lkode = new JLabel("Kode Item : ",SwingConstants.RIGHT);
    JLabel ljum = new JLabel("Jumlah : ",SwingConstants.RIGHT);
    JTextField fkasir = new JTextField(30);
    JTextField fno = new JTextField(30);
    JTextField ftanggal = new JTextField(30);

    JTextField fkode = new JTextField(15);
    JTextField fjum = new JTextField(7);
    JTextField ftotal = new JTextField("0",30);
    JButton bTambah = new JButton("Tambah");
    JButton bEdit = new JButton("Edit");
    JButton bHapus = new JButton("Hapus");
    JButton bBayar = new JButton("Bayar");

    JPanel data = new JPanel();
    JPanel dbarang = new JPanel();
    String[] column = {"No","Kode Item","Nama Barang","Harga","Jumlah","Total"};
    DefaultTableModel tableModel = new DefaultTableModel(column,0);
    JTable table = new JTable(tableModel);
    DefaultTableCellRenderer righttable = new DefaultTableCellRenderer();
    JScrollPane jScrollPane = new JScrollPane(table);
//    End Bagian Kasir
    
//  Bagian Barang
    String[] kolomBarang = {"No","Kode Item","Nama Barang","Jenis Barang","Harga","Stok"};
    DefaultTableModel tableModel1 = new DefaultTableModel(kolomBarang,0);
    JTable barangJTable = new JTable(tableModel1);
    JScrollPane BarangjScrollPane = new JScrollPane(barangJTable);
    JPanel bHeader = new JPanel();
    JPanel tBarang = new JPanel();
    JButton tambah = new JButton("Tambah");
    
// Tambah Barang
    JTextField nama_barang = new JTextField();
    JTextField jenis_barang = new JTextField();
    JTextField harga_barang = new JTextField();
    JTextField stok_barang = new JTextField();
    JLabel namaLabel = new JLabel("Nama Barang : ");
    JLabel jenisLabel = new JLabel("Jenis Barang : ");
    JLabel hargaLabel = new JLabel("Harga Barang : ");
    JLabel stokLabel = new JLabel("Stok Barang : ");
    JButton tambahBarang = new JButton("Tambah");
    JButton editBarang = new JButton("Edit");

//    Laporan
    String[] kolomLaporan = {"Tanggal","ID Struk","Nama Kasir","Pendapatan"};
    DefaultTableModel tableModel2 = new DefaultTableModel(kolomLaporan,0);
    JTable laporanJTable = new JTable(tableModel2);
    JScrollPane LaporanjScrollPane = new JScrollPane(laporanJTable);

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
        pLaporan.add(LaporanjScrollPane);
    }

//    public JFrame Tambah(){
//        setTitle("Tambah Barang");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        setLayout(null);
//        setSize(400,200);
//        add(namaLabel);
//        add(jenisLabel);
//        add(stokLabel);
//        add(hargaLabel);
//        add(nama_barang);
//        add(harga_barang);
//        add(jenis_barang);
//        add(stok_barang);
//        add(tambahBarang);
//        namaLabel.setBounds(20,20,100,20);
//        jenisLabel.setBounds(20,40,100,20);
//        stokLabel.setBounds(20,60,100,20);
//        hargaLabel.setBounds(20,80,100,20);
//        nama_barang.setBounds(150,20,150,20);
//        harga_barang.setBounds(150,40,150,20);
//        jenis_barang.setBounds(150,60,150,20);
//        stok_barang.setBounds(150,80,150,20);
//        tambahBarang.setBounds(100,120,150,20);
//        return currentFrame;
//    }
//
//    public JFrame Edit(){
//        setTitle("Edit Barang");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        setLayout(null);
//        setSize(400,200);
//        add(namaLabel);
//        add(jenisLabel);
//        add(stokLabel);
//        add(hargaLabel);
//        add(nama_barang);
//        add(harga_barang);
//        add(jenis_barang);
//        add(stok_barang);
//        add(editBarang);
//        namaLabel.setBounds(20,20,100,20);
//        jenisLabel.setBounds(20,40,100,20);
//        stokLabel.setBounds(20,60,100,20);
//        hargaLabel.setBounds(20,80,100,20);
//        nama_barang.setBounds(150,20,150,20);
//        harga_barang.setBounds(150,40,150,20);
//        jenis_barang.setBounds(150,60,150,20);
//        stok_barang.setBounds(150,80,150,20);
//        editBarang.setBounds(100,120,150,20);
//        return currentFrame;
//    }

    public static void main(String[] args) {
        new MVC(123180027);
    }
}
