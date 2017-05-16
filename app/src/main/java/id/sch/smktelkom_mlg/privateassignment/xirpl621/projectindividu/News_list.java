package id.sch.smktelkom_mlg.privateassignment.xirpl621.projectindividu;

/**
 * Created by Mokleters on 13/05/2017.
 */

public class News_list {
    private String image;
    private String judul;
    private String popular;

    public News_list(String image, String judul, String popular) {
        this.image = image;
        this.judul = judul;
        this.popular = popular;
    }

    public String getImage() {
        return image;
    }

    public String getJudul() {
        return judul;
    }

    public String getPopular() {
        return popular;
    }

}
