package Utama;

public class Tambah {
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

public JFrame Tambah(){
        setTitle("Tambah Barang");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(400,200);
        add(namaLabel);
        add(jenisLabel);
        add(stokLabel);
        add(hargaLabel);
        add(nama_barang);
        add(harga_barang);
        add(jenis_barang);
        add(stok_barang);
        add(tambahBarang);
        namaLabel.setBounds(20,20,100,20);
        jenisLabel.setBounds(20,40,100,20);
        stokLabel.setBounds(20,60,100,20);
        hargaLabel.setBounds(20,80,100,20);
        nama_barang.setBounds(150,20,150,20);
        harga_barang.setBounds(150,40,150,20);
        jenis_barang.setBounds(150,60,150,20);
        stok_barang.setBounds(150,80,150,20);
        tambahBarang.setBounds(100,120,150,20);
        return currentFrame;
    }
}
