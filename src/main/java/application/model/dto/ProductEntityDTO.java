package application.model.dto;

public class ProductEntityDTO {
    private int productId;
    private int colorId;
    private  int sizeId;
    private long amount;

    public ProductEntityDTO(int productId, int colorId, int sizeId, long amount) {
        this.productId = productId;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.amount = amount;
    }

    public ProductEntityDTO() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
