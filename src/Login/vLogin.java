package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class vLogin extends JFrame implements ActionListener, Listener {
    JLabel lid = new JLabel("ID");
    JLabel lpass = new JLabel("Password");
    JLabel warning = new JLabel();

    JTextField fid = new JTextField(120);
    JPasswordField fpass = new JPasswordField(120);

    JButton bLogin = new JButton("Login");

    cLogin clogin;
    mLogin mlogin;

    public vLogin(){
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360,220);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        warning.setForeground(Color.red);

        fid.addKeyListener(new KeyAdapter() {
                               public void keyPressed(KeyEvent ke) {
                                   String value = fid.getText();
                                   int l = value.length();
                                   if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                                       bLogin.setEnabled(true);
                                       warning.setText("");
                                   } else {
                                       bLogin.setEnabled(false);
                                       warning.setText("* ID harus berupa angka");
                                   }
                               }
                           });

        add(lid);
        add(fid);
        add(warning);
        add(lpass);
        add(fpass);
        add(bLogin);

        lid.setBounds(20,20,120,30);
        fid.setBounds(140,20,180,30);
        warning.setBounds(140,50,280,30);
        lpass.setBounds(20,80,120,30);
        fpass.setBounds(140,80,180,30);
        bLogin.setBounds(170,130,150,30);

        bLogin.addActionListener(this);

        clogin = new cLogin();
        mlogin = new mLogin();
        mlogin.setListener(this);
        clogin.setMlogin(mlogin);
    }

    public JTextField getFid() {
        return fid;
    }

    public JPasswordField getFpass() {
        return fpass;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == bLogin){
            clogin.submitForm(this);
        }
    }

    @Override
    public void onChange(mLogin mlogin) {
        fid.setText(mlogin.getId());
        fpass.setText(mlogin.getPassword());
    }
}
