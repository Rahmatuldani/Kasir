package Utama;

public class MVC {
    public MVC(int id) {
        vUtama view = new vUtama();
        cUtama controller = new cUtama(view,id);
    }
}
