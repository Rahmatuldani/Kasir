package Utama;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class cUtama extends WindowAdapter {
    vUtama view;
    mBarang barang = new mBarang();
    mStruk struk = new mStruk();
    DetailStruk detailStruk = new DetailStruk();
    mPegawai pegawai = new mPegawai();
    Object[][] data = new Object[2][6];

    cUtama(vUtama view1){
        view = view1;

        view.barangJTable.setModel((new JTable(barang.All_barang(),view.kolomBarang)).getModel());

        view.bTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int kode = Integer.parseInt(view.fkode.getText());
//                JOptionPane.showMessageDialog(null,kode,"kode",JOptionPane.INFORMATION_MESSAGE);
                data = barang.Find_barang(kode,view.no+1,Integer.parseInt(view.fjum.getText()));
                view.table.setModel((new JTable(data,view.column)).getModel());
                view.no++;
            }
        });
    }
}
