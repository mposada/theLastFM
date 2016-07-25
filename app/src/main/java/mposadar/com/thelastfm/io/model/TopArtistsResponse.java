package mposadar.com.thelastfm.io.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mposadar on 24/07/16.
 */
public class TopArtistsResponse {
    @SerializedName("topartists")
    @Expose
    private TopArtists topArtists;

    /**
     *
     * @return
     * The topArtists
     */
    public TopArtists getTopArtists() {
        return topArtists;
    }

    /**
     *
     * @param topArtists
     * The topArtists
     */
    public void setTopartists(TopArtists topArtists) {
        this.topArtists = topArtists;
    }

    public class TopArtists {

        @SerializedName("artist")
        @Expose
        private List<Artist> artist = new ArrayList<>();
        @SerializedName("@attr")
        @Expose
        private Attr attr;

        /**
         *
         * @return
         * The artist
         */
        public List<Artist> getArtist() {
            return artist;
        }

        /**
         *
         * @param artist
         * The artist
         */
        public void setArtist(List<Artist> artist) {
            this.artist = artist;
        }

        /**
         *
         * @return
         * The attr
         */
        public Attr getAttr() {
            return attr;
        }

        /**
         *
         * @param attr
         * The @attr
         */
        public void setAttr(Attr attr) {
            this.attr = attr;
        }

    }

    public class Artist {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("listeners")
        @Expose
        private String listeners;
        @SerializedName("mbid")
        @Expose
        private String mbid;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("streamable")
        @Expose
        private String streamable;
        @SerializedName("image")
        @Expose
        private List<Image> image = new ArrayList<Image>();

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The listeners
         */
        public String getListeners() {
            return listeners;
        }

        /**
         *
         * @param listeners
         * The listeners
         */
        public void setListeners(String listeners) {
            this.listeners = listeners;
        }

        /**
         *
         * @return
         * The mbid
         */
        public String getMbid() {
            return mbid;
        }

        /**
         *
         * @param mbid
         * The mbid
         */
        public void setMbid(String mbid) {
            this.mbid = mbid;
        }

        /**
         *
         * @return
         * The url
         */
        public String getUrl() {
            return url;
        }

        /**
         *
         * @param url
         * The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         *
         * @return
         * The streamable
         */
        public String getStreamable() {
            return streamable;
        }

        /**
         *
         * @param streamable
         * The streamable
         */
        public void setStreamable(String streamable) {
            this.streamable = streamable;
        }

        /**
         *
         * @return
         * The image
         */
        public List<Image> getImage() {
            return image;
        }

        /**
         *
         * @param image
         * The image
         */
        public void setImage(List<Image> image) {
            this.image = image;
        }

    }

    public class Attr {

        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("page")
        @Expose
        private String page;
        @SerializedName("perPage")
        @Expose
        private String perPage;
        @SerializedName("totalPages")
        @Expose
        private String totalPages;
        @SerializedName("total")
        @Expose
        private String total;

        /**
         *
         * @return
         * The country
         */
        public String getCountry() {
            return country;
        }

        /**
         *
         * @param country
         * The country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         *
         * @return
         * The page
         */
        public String getPage() {
            return page;
        }

        /**
         *
         * @param page
         * The page
         */
        public void setPage(String page) {
            this.page = page;
        }

        /**
         *
         * @return
         * The perPage
         */
        public String getPerPage() {
            return perPage;
        }

        /**
         *
         * @param perPage
         * The perPage
         */
        public void setPerPage(String perPage) {
            this.perPage = perPage;
        }

        /**
         *
         * @return
         * The totalPages
         */
        public String getTotalPages() {
            return totalPages;
        }

        /**
         *
         * @param totalPages
         * The totalPages
         */
        public void setTotalPages(String totalPages) {
            this.totalPages = totalPages;
        }

        /**
         *
         * @return
         * The total
         */
        public String getTotal() {
            return total;
        }

        /**
         *
         * @param total
         * The total
         */
        public void setTotal(String total) {
            this.total = total;
        }

    }

    public class Image {

        @SerializedName("#text")
        @Expose
        private String text;
        @SerializedName("size")
        @Expose
        private String size;

        /**
         *
         * @return
         * The text
         */
        public String getText() {
            return text;
        }

        /**
         *
         * @param text
         * The #text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         *
         * @return
         * The size
         */
        public String getSize() {
            return size;
        }

        /**
         *
         * @param size
         * The size
         */
        public void setSize(String size) {
            this.size = size;
        }

    }

}
