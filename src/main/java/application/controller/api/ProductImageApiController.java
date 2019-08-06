package application.controller.api;

import application.data.model.Product;
import application.data.model.ProductImage;
import application.data.service.ProductImageService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/product-image")
public class ProductImageApiController {

    private static final Logger logger = LogManager.getLogger(ProductImageApiController.class);


    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    private String[] images = {
            "https://media.bibomart.net/u/bbm/product/2017/10/05/16/15/1000_1000/bo-quan-ao-momma-baby-be-trai-dai-tay-116157_1.jpg",
            "https://media.bibomart.net/u/bbm/product/2016/05/27/16/48/410_410/bo-quan-ao-sat-nach-thai-theu-huou-cao-co-hh177-111021-2_1.jpg",
            "https://icdn.dantri.com.vn/2016/may-giat-1-1482739244259.jpg",
            "https://sakurafashion.vn/upload/sanpham/4745-top-7-shop-quan-ao-dep-o-ha-long-tp-quang-ninh.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT57Rqc_U33oNMaSqAvsE42ZvIOwKFcjbs8YjL3nLIG3sqowW5ALg",
            "https://thethaominhphu.com/wp-content/uploads/2017/04/ao-bong-ban-x1-tim-than.jpg",
            "https://sc01.alicdn.com/kf/HTB1nojZSXXXXXa8aXXXq6xXFXXXG/Thailand-wholesale-clothing-2017-fashion-clothes-women.jpg_350x350.jpg"
    };

    @GetMapping("/fake")
    public BaseApiResult fakeProductImage() {
        BaseApiResult result = new BaseApiResult();

        try {
            Random random = new Random();
            List<Product> productList = productService.getListAllProducts();
            for(Product product : productList) {
                if(product.getProductImageList().size() == 0) {
                    List<ProductImage> productImages = new ArrayList<>();
                    ProductImage productMainImage = new ProductImage();
                    productMainImage.setLink(product.getMainImage());
                    productMainImage.setProduct(product);
                    productMainImage.setCreatedDate(new Date());

                    productImages.add(productMainImage);
                    for(int i=0; i<random.nextInt(2) +1 ; i++) {
                        ProductImage productImage = new ProductImage();
                        productImage.setLink(images[random.nextInt(images.length)]);
                        productImage.setProduct(product);
                        productImage.setCreatedDate(new Date());

                        productImages.add(productImage);
                    }
                    productImageService.addNewListProductImages(productImages);
                }
            }
            result.setSuccess(true);
            result.setMessage("Fake list product images successfully !");
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            logger.error(e.getMessage());
        }
        return result;
    }

}

