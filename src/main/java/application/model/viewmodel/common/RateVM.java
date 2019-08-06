package application.model.viewmodel.common;

import java.util.Date;
import java.util.List;

public class RateVM {
    private int id;
    private int productId;
    private int star;
    private String comment;
    private String avatar;
    private String userName;
    private Date createdDate;
    private  int parentId;
    private List<RateVM> rateVMList;

    public List<RateVM> getRateVMList() {
        return rateVMList;
    }

    public void setRateVMList(List<RateVM> rateVMList) {
        this.rateVMList = rateVMList;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
