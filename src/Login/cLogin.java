package Login;

import javax.swing.*;

public class cLogin {
    private mLogin mlogin;

    public void setMlogin(mLogin mlogin) {
        this.mlogin = mlogin;
    }


    public void submitForm(vLogin vlogin) {
        String id = vlogin.getFid().getText();
        String pass = vlogin.getFpass().getText();

        if (id.equals("")){
            JOptionPane.showMessageDialog(vlogin,"ID Harus Diisi!", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else if (pass.trim().equals("")){
            JOptionPane.showMessageDialog(vlogin,"Password Harus Diisi!", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mlogin.submitForm(vlogin);
        }
    }
}
