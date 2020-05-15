package Utama;

public class cUtama {
    mBarang barang = new mBarang();
    mStruk struk = new mStruk();
    DetailStruk detailStruk = new DetailStruk();
    mPegawai pegawai = new mPegawai();
    Object[][] data = new Object[100][6];

//    *Start Bagian Barang
    public Object[][] getBarang(){
        data = barang.All_barang();
        return data;
    }

    public Object[][] findBarang(int id){
        barang.Find_barang(id);
        data = barang.barang;
        return data;
    }

    public void createBarang(Object[][] data){
        barang.Create_barang(data);
    }

    public void updateBarang(Object[][] data, int id){
        barang.Update_barang(data,id);
    }

    public void deleteBarang(int id){
        barang.Delete_barang(id);
    }
//    *End Bagian Barang

//    *Start Bagian Pegawai
    public Object[][] findPegawai(int id){
        pegawai.Find_pegawai(id);
        data = pegawai.pegawai;
        return data;
    }

    public void createPegawai(Object[][] data){
        pegawai.Create_pegawai(data);
    }

    public void updatePegawai(Object[][] data, int id){
        pegawai.Update_pegawai(data,id);
    }

    public void deletePegawai(int id){
        pegawai.Delete_pegawai(id);
    }
//    *End Bagian Pegawai

//    *Start Bagian Struk
    public Object[][] findStruk(int id){
        struk.Find_struk(id);
        data = struk.struk;
        return data;
    }

    public void createStruk(Object[][] data){
        struk.Create_struk(data);
    }

    public Object[][] findDetaiStruk(int id){
        detailStruk.Find_struk(id);
        data = detailStruk.struk;
        return data;
    }

    public void createDetailStruk(Object[][] data){
        detailStruk.Create_struk(data);
    }
//    *End Bagian Struk
}
