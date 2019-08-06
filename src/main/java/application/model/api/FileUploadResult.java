package application.model.api;

import application.model.api.BaseApiResult;

public class FileUploadResult extends BaseApiResult {
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
