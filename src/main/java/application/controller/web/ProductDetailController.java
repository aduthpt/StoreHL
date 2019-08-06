package application.controller.web;

import application.data.model.*;
import application.data.service.*;


import application.model.viewmodel.common.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductDetailController extends BaseController {

    @Autowired
    RateService rateService;
    @Autowired
    SizeService sizeService;

    @Autowired
    ColorService colorService;
    @Autowired
    ProductEntityService productEntityService;
    @Autowired
   ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
   BrandService brandService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    UserService userService;

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Integer productId,  Model model,
                                @Valid @ModelAttribute("productname") ProductVM productName,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                final Principal principal) {


        ProductDetailVM vm = new ProductDetailVM();


        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();

        for(Category category : categoryList) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            category.setCreatedDate(category.getCreatedDate());
            categoryVMList.add(categoryVM);
        }


        List<Brand> brandList = brandService.getListAllBrands();
        List<BrandVM> brandVMList = new ArrayList<>();

        for(Brand brand : brandList) {
            BrandVM brandVM = new BrandVM();
            brandVM.setId(brand.getId());
            brandVM.setName(brand.getName());
            brandVM.setShortDesc(brand.getShortDesc());
            brand.setCreatedDate(brand.getCreatedDate());
            brandVMList.add(brandVM);
        }


        List<ProductEntity> productEntityList = productEntityService.findByProductId(productId);
        List<ProductEntityVM> productEntityVMList = new ArrayList<>();
        for(ProductEntity item : productEntityList){
            ProductEntityVM entityVM = new ProductEntityVM();
            entityVM.setColorName(item.getColor().getName());
            entityVM.setSizeName(item.getSize().getName());
            entityVM.setAmount(item.getAmount());
            entityVM.setProductId(item.getProductId());
            entityVM.setColorId(item.getColorId());
            entityVM.setSizeId(item.getSizeId());
            entityVM.setProductEntityId(item.getId());
            productEntityVMList.add(entityVM);
        }




        List<Size> sizeList = sizeService.getListSizeByProductId(productId);
        List<SizeVM> sizeVMList = new ArrayList<>();

        for(Size size2 : sizeList) {
            SizeVM sizeVM = new SizeVM();
            sizeVM.setId(size2.getId());
            sizeVM.setName(size2.getName());
            sizeVMList.add(sizeVM);
        }
//

        List<Color> colorList = colorService.getListColorByProductId(productId);
        List<ColorVM> colorVMList = new ArrayList<>();

        for(Color color : colorList) {
            ColorVM colorVM = new ColorVM();
            colorVM.setId(color.getId());
            colorVM.setName(color.getName());
            colorVMList.add(colorVM);
        }



        Product productEntity = productService.findOne(productId);
        ProductVM productVM = new ProductVM();

        if(productEntity!=null) {
            productVM.setId(productEntity.getId());
            productVM.setName(productEntity.getName());
            productVM.setMainImage(productEntity.getMainImage());
            productVM.setShortDesc(productEntity.getShortDesc());
            productVM.setPrice(productEntity.getPrice());
            productVM.setCategoryName(productEntity.getCategory().getName());
            productVM.setBrandName(productEntity.getBrand().getName());
            productVM.setRateAvg(Math.round(rateService.getRateAvg(productEntity.getId())));
            List<ProductImageVM> productImageVMS = new ArrayList<>();



            for(ProductImage productImage : productEntity.getProductImageList()) {
            ProductImageVM productImageVM = new ProductImageVM();
            productImageVM.setLink(productImage.getLink());

            productImageVMS.add(productImageVM);
        }

        productVM.setProductImageVMS(productImageVMS);
    }
        List<Rate> rateList = productEntity.getRateList();
        int totalStar=0, amountStar=0;
        List<RateVM> rateVMList = new ArrayList<>();
        for(Rate rate : rateList){
            RateVM rateVM= new RateVM();
            rateVM.setAvatar(rate.getUser().getAvatar());
            rateVM.setComment(rate.getComment());
            rateVM.setCreatedDate(rate.getCreatedDate());
            rateVM.setStar(rate.getStar());
            rateVM.setId(rate.getId());
            totalStar+=rate.getStar();
            amountStar++;
            if(rate.getUser().getName()==null)
                rateVM.setUserName(rate.getUser().getUserName());
            else
                rateVM.setUserName(rate.getUser().getName());
            List<Rate> chilRate = rate.getRateList();
            if(chilRate!=null){
                List<RateVM> rateVMListChil = new ArrayList<>();
                for(Rate item : chilRate){
                    RateVM rateVM2= new RateVM();
                    rateVM2.setAvatar(item.getUser().getAvatar());
                    rateVM2.setComment(item.getComment());
                    rateVM2.setCreatedDate(item.getCreatedDate());
                    rateVM2.setId(item.getId());
                    rateVM2.setParentId(item.getRate().getId());
                    if(item.getUser().getName()==null)
                        rateVM2.setUserName(item.getUser().getUserName());
                    else
                        rateVM2.setUserName(item.getUser().getName());
                    rateVMListChil.add(rateVM2);
                }
                rateVM.setRateVMList(rateVMListChil);
            }
            rateVMList.add(rateVM);
        }
        if(amountStar==0)
            vm.setTotalRate(0);
        else
            vm.setRateAvg(Math.round(totalStar/amountStar));
        Pageable pageable = new PageRequest(0, 50);

        Page<Product> productPage = null;

        if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,productEntity.getCategory().getId(),productName.getName().trim());

        } else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,productEntity.getCategory().getId(),null);
        }




//    for (int i=0;i<=3;i++){
        List<Product> products =productService.getListAllProducts();
        List<ProductRVM> productRVMList =new ArrayList<>();
      for (Product product :products){
          ProductRVM productRVM = new ProductRVM();
          productRVM.setCategoryName(product.getCategory().getName());
          productRVM.setBrandName(product.getBrand().getName());
          productRVM.setId(product.getId());
          if (product.getCategory()==productEntity.getCategory() &&product.getBrand()==productEntity.getBrand()&&product.getId()!=productEntity.getId()) {

              productRVM.setName(product.getName());
              productRVM.setMainImage(product.getMainImage());
              productRVM.setPrice(product.getPrice());
              productRVM.setShortDesc(product.getShortDesc());
              productRVM.setCreatedDate(product.getCreatedDate());

              productRVMList.add(productRVM);
          }
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setProductVM(productVM);
        vm.setCategoryVMList(categoryVMList);
        vm.setBrandVMList(brandVMList);
        vm.setProductRVMList(productRVMList);
        vm.setSizeVMList(sizeVMList);
        vm.setColorVMList(colorVMList);
        vm.setRateVMList(rateVMList);


        model.addAttribute("vm",vm);

        return "/product-detail";
    }

}
