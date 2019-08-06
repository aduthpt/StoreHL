package application.model.viewmodel.common;

import java.util.List;

public class ProductDetailVM {
    private ProductVM productVM;
    private LayoutHeaderVM layoutHeaderVM;
    private List<CategoryVM> categoryVMList;
    private List<BrandVM> brandVMList;
    private  List<ProductRVM> productRVMList;
    private List<ColorVM> colorVMList;
    private List<SizeVM> sizeVMList;
    private List<ProductEntityVM> productEntityVMList;
//    private String totalPrice;
    private List<RateVM> rateVMList;
    private int totalRate;
    private int rateAvg;
    private int productAmount;

    public List<ColorVM> getColorVMList() {
        return colorVMList;
    }

    public void setColorVMList(List<ColorVM> colorVMList) {
        this.colorVMList = colorVMList;
    }

    public List<SizeVM> getSizeVMList() {
        return sizeVMList;
    }

    public void setSizeVMList(List<SizeVM> sizeVMList) {
        this.sizeVMList = sizeVMList;
    }

    public List<ProductEntityVM> getProductEntityVMList() {
        return productEntityVMList;
    }

    public void setProductEntityVMList(List<ProductEntityVM> productEntityVMList) {
        this.productEntityVMList = productEntityVMList;
    }

    public List<RateVM> getRateVMList() {
        return rateVMList;
    }

    public void setRateVMList(List<RateVM> rateVMList) {
        this.rateVMList = rateVMList;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }

    public int getRateAvg() {
        return rateAvg;
    }

    public void setRateAvg(int rateAvg) {
        this.rateAvg = rateAvg;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public List<ProductRVM> getProductRVMList() {
        return productRVMList;
    }

    public void setProductRVMList(List<ProductRVM> productRVMList) {
        this.productRVMList = productRVMList;
    }

    public List<BrandVM> getBrandVMList() {
        return brandVMList;
    }

    public void setBrandVMList(List<BrandVM> brandVMList) {
        this.brandVMList = brandVMList;
    }

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }

    public ProductVM getProductVM() {
        return productVM;
    }

    public void setProductVM(ProductVM productVM) {
        this.productVM = productVM;
    }

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }
}
