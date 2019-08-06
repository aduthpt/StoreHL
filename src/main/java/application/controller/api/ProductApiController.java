package application.controller.api;

import application.data.model.Brand;
import application.data.model.Category;
import application.data.model.Product;
import application.data.service.BrandService;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/product")
public class    ProductApiController {

    private static final Logger logger = LogManager.getLogger(ProductApiController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    private String[] images = {
            "https://media.bibomart.net/u/bbm/product/2017/10/05/16/15/1000_1000/bo-quan-ao-momma-baby-be-trai-dai-tay-116157_1.jpg",
            "https://media.bibomart.net/u/bbm/product/2016/05/27/16/48/410_410/bo-quan-ao-sat-nach-thai-theu-huou-cao-co-hh177-111021-2_1.jpg",
            "https://icdn.dantri.com.vn/2016/may-giat-1-1482739244259.jpg",
            "https://sakurafashion.vn/upload/sanpham/4745-top-7-shop-quan-ao-dep-o-ha-long-tp-quang-ninh.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT57Rqc_U33oNMaSqAvsE42ZvIOwKFcjbs8YjL3nLIG3sqowW5ALg",
            "https://thethaominhphu.com/wp-content/uploads/2017/04/ao-bong-ban-x1-tim-than.jpg",

    };


    @GetMapping("/fake")
    public BaseApiResult fakeCategoryBrand(){
        BaseApiResult result = new BaseApiResult();

        try {
            long totalProducts = productService.getTotalProducts();
            List<Category> categoryList = categoryService.getListAllCategories();
                List<Brand> brandList = brandService.getListAllBrands();
            List<Product> productList = new ArrayList<>();
            Random random = new Random();
            for(long i = totalProducts +1; i<= totalProducts + 40; i++) {
                Product product = new Product();
                product.setName("Product " + i);
                product.setShortDesc("Product " + i + " short desc");
                product.setMainImage(images[random.nextInt(images.length)]);

                /**
                 * random price
                 */
                double rangeMin = 4;
                double rangeMax = 30;
                double randomPrice = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
                product.setPrice(randomPrice);

                /**
                 * random category
                 */

                product.setCategory(categoryList.get(random.nextInt(categoryList.size())));
                product.setBrand(brandList.get(random.nextInt(brandList.size())));

                product.setCreatedDate(new Date());
                productList.add(product);

            }
            productService.addNewListProducts(productList);
            result.setSuccess(true);
            result.setMessage("Fake list products success !");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/create")
    public BaseApiResult createProduct(@RequestBody ProductDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setPrice(dto.getPrice());
            product.setMainImage(dto.getMainImage());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));
            product.setCreatedDate(new Date());
            productService.addNewProduct(product);
            result.setData(product.getId());
            result.setMessage("Save product successfully: " + product.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Product product = productService.findOne(productId);
            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setPrice(dto.getPrice());
            product.setMainImage(dto.getMainImage());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));
            product.setCreatedDate(new Date());
            productService.addNewProduct(product);
            result.setSuccess(true);
            result.setMessage("Update product successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{productId}")
    public BaseApiResult detailMaterial(@PathVariable int productId){
        DataApiResult result= new DataApiResult();

        try {
            Product productEntity = productService.findOne(productId);
            if(productEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                ProductDTO dto = new ProductDTO();
                dto.setId(productEntity.getId());
                if(productEntity.getCategory() != null) {
                    dto.setCategoryId(productEntity.getCategory().getId());
                }
                dto.setMainImage(productEntity.getMainImage());
                dto.setName(productEntity.getName());
                dto.setPrice(productEntity.getPrice());
                dto.setShortDesc(productEntity.getShortDesc());
                dto.setCreatedDate(new Date());
                result.setSuccess(true);
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
