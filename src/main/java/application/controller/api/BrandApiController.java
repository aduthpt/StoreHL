package application.controller.api;


import application.data.model.Brand;
import application.data.model.Category;
import application.data.service.BrandService;
import application.data.service.CategoryService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.BrandDTO;
import application.model.dto.CategoryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/brand")
public class BrandApiController {

    private static final Logger logger = LogManager.getLogger(BrandApiController.class);


    @Autowired
    private BrandService brandService;

    @GetMapping("/fake")
    public BaseApiResult fakeBrand(){
        BaseApiResult result = new BaseApiResult();

        try {
            long totalBrands = brandService.getTotalBrands();
            List<Brand> brandList = new ArrayList<>();
            for(long i = totalBrands +1; i<= totalBrands + 5; i++) {
                Brand brand = new Brand();
                brand.setName("Brand " + i);
                brand.setShortDesc("Brand " + i + " short desc");
                brand.setCreatedDate(new Date());
                brandList.add(brand);
            }
            brandService.addNewListBrands(brandList);
            result.setSuccess(true);
            result.setMessage("Fake list categories success !");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @PostMapping("/create")
    public BaseApiResult createBrand(@RequestBody BrandDTO dto){
        BaseApiResult result = new BaseApiResult();

        try {
            Brand brandEntity = new Brand();
            brandEntity.setShortDesc(dto.getShortDesc());
            brandEntity.setName(dto.getName());
            brandEntity.setCreatedDate(new Date());
            brandService.addNewBrand(brandEntity);
            result.setSuccess(true);
            result.setMessage("Create brand successfully: " + brandEntity.getId());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{brandId}")
    public DataApiResult getDetailBrand(@PathVariable int brandId){
        DataApiResult result= new DataApiResult();

        try {
            Brand brand = brandService.findOne(brandId);
            if(brand == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this brand");
            } else {
               BrandDTO dto = new BrandDTO();
                dto.setId(brand.getId());
                dto.setName(brand.getName());
                dto.setShortDesc(brand.getShortDesc());
                dto.setCreatedDate(brand.getCreatedDate());
                result.setSuccess(true);
                result.setMessage("Get detail brand successfully !");
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update/{brandId}")
    public BaseApiResult updateBrand(@PathVariable int brandId, @RequestBody BrandDTO dto){
        BaseApiResult result = new BaseApiResult();

        try {
            Brand brandEntity = brandService.findOne(brandId);
            brandEntity.setShortDesc(dto.getShortDesc());
            brandEntity.setName(dto.getName());
            brandEntity.setCreatedDate(new Date());
            brandService.addNewBrand(brandEntity);
            result.setSuccess(true);
            result.setMessage("Update brand" + brandEntity.getId() + " successfully:");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }


}
