package mposadar.com.thelastfm.domain;

/**
 * Created by mposadar on 29/06/16.
 */
public class Artists {
    private String name;
    private String image;

    public Artists(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Artists() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
