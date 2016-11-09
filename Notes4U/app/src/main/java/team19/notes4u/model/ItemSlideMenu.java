package team19.notes4u.model;

/**
 * Created by Muser on 11/9/2016.
 */

public class ItemSlideMenu {

    private int ImgId;
    private String title;

    public ItemSlideMenu(int imgId, String title) {
        ImgId = imgId;
        this.title = title;
    }

    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
