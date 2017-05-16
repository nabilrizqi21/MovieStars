package id.sch.smktelkom_mlg.privateassignment.xirpl621.projectindividu;


public class News_list {
    private String imageUrl;
    private String head;
    private String desc;

    public News_list(String imageUrl, String head, String desc) {
        this.imageUrl = imageUrl;
        this.head = head;
        this.desc = desc;
    }

    public News_list() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}