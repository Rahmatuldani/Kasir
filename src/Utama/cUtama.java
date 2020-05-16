package Utama;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cUtama extends WindowAdapter {
    vUtama view;
    mBarang barang = new mBarang();
    mStruk struk = new mStruk();
    DetailStruk detailStruk = new DetailStruk();
    mPegawai pegawai = new mPegawai();
    Object[] fdata;
    Object[][] data = new Object[100][6];
    int total = 0;
    cUtama(vUtama view1, int id){
        view = view1;

        Object[] dpegawai = pegawai.Find_pegawai(id);

        view.fkasir.setText(String.valueOf(dpegawai[1]));

        view.barangJTable.setModel((new JTable(barang.All_barang(),view.kolomBarang)).getModel());
        view.laporanJTable.setModel((new JTable(struk.Read_struk(),view.kolomLaporan)).getModel());

        view.bTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int kode = Integer.parseInt(view.fkode.getText());
                fdata = barang.Find_barang(kode,view.no+1,Integer.parseInt(view.fjum.getText()));
                total = total + (int)fdata[5];
                view.tableModel.addRow(fdata);
                view.ftotal.setText(Integer.toString(total));
                view.no++;
            }
        });

        view.bHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.tableModel.removeRow(view.table.getSelectedRow());
            }
        });

        view.bBayar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                struk.Create_struk(Integer.parseInt(view.fno.getText()),id,dateFormat.format(date));

                for (int i = 0; i < view.tableModel.getRowCount(); i++) {
                    data[i][0] = view.table.getValueAt(i,1).toString();
                    data[i][1] = view.table.getValueAt(i,4).toString();
                    detailStruk.Create_struk((int)data[i][0],Integer.parseInt(view.fno.getText()),(int)data[i][1]);
                }
            }
        });
    }
}
