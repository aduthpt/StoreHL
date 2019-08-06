package application.controller.api;

import application.data.model.ProductEntity;
import application.data.service.ColorService;
import application.data.service.ProductEntityService;
import application.data.service.ProductService;
import application.data.service.SizeService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ProductEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/product-entity")
public class ProductEntityApiController {

    @Autowired
    ProductEntityService productEntityService;

    @Autowired
    ProductService productService;

    @Autowired
    ColorService colorService;

    @Autowired
    SizeService sizeService;

    @PostMapping(value = "/add")
    public BaseApiResult addProductEntity(@RequestBody ProductEntityDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            ProductEntity obj = productEntityService.getByProductSizeColor(dto.getProductId(),dto.getColorId(),dto.getSizeId() );
            int add=0;
            long oldAmount, amount=0;
            if(obj==null){
                add=1;
                obj= new ProductEntity();
                obj.setColor(colorService.findOne(dto.getColorId()));
                obj.setSize(sizeService.findOne(dto.getSizeId()));
                obj.setProduct(productService.findOne(dto.getProductId()));
                obj.setAmount(dto.getAmount());

            } else{
                amount=dto.getAmount()+obj.getAmount();
                obj.setAmount(amount);
            }

            productEntityService.update(obj);

            result.setMessage("Lưu thành công");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
