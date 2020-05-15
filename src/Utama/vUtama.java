package Utama;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.text.*;

public class vUtama extends JFrame implements ActionListener {
    JTabbedPane tab = new JTabbedPane();
    JPanel pKasir = new JPanel();
    JPanel pBarang = new JPanel();
    JPanel pLaporan = new JPanel();

    cUtama controller = new cUtama();
    int no = 0;
    Object[][] tdata = new Object[1][6];
    Object[][] listbarang = new Object[1][8];
    
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
    JTextField ftotal = new JTextField("0",30);

    JButton bTambah = new JButton("Tambah");
    JButton bEdit = new JButton("Edit");
    JButton bHapus = new JButton("Hapus");
    JButton bBayar = new JButton("Bayar");

    JPanel data = new JPanel();
    JPanel dbarang = new JPanel();
    String[] column = {"No","Kode Item","Nama Barang","Harga","Jumlah","Total"};
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

        bTambah.addActionListener(this);
        bHapus.addActionListener(this);
        bBayar.addActionListener(this);
        
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
        listbarang = controller.getBarang();
        JTable barangJTable = new JTable(listbarang,kolomBarang);
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
//        String[][] liststruk = new String[readStruk()][4];
//        JTable laporanJTable = new JTable(readStruk(liststruk),kolomLaporan);
//        JScrollPane LaporanjScrollPane = new JScrollPane(laporanJTable);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == bTambah){
            setVisible(false);
        }
        if (actionEvent.getSource() == bHapus){
            setVisible(false);
        }
        if (actionEvent.getSource() == bBayar){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new vUtama();
    }
}
