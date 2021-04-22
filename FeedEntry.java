package com.example.asynctask;

public class FeedEntry
{
    private String name;
    private String summary;
    private String imgUrl;
    private String releaseDate;
    private String artist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary(String tagName) {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return
                "name=" + name + '\n' +
                        ", imgUrl=" + imgUrl + '\n' +
                        ", releaseDate=" + releaseDate + '\n' +
                        ", artist=" + artist + '\n';
    }
}


