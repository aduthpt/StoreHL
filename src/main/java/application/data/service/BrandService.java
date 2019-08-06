package application.data.service;
import application.data.model.Brand;
import application.data.repository.BrandRepository;
import org.springframework.data.domain.Pageable;
import application.data.model.Category;
import application.data.model.Product;
import application.data.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    private static final Logger logger = LogManager.getLogger(BrandService.class);


    @Autowired
    private BrandRepository brandRepository;

    @Transactional
    public void addNewListBrands(List<Brand> brandList) {
        try {
            brandRepository.save(brandList);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewBrand(Brand brand) {
        try {
            brandRepository.save(brand);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }



    public Brand findOne(int brandId) {
        return brandRepository.findOne(brandId);
    }
    public boolean updateBrand(Brand brand) {
        try {
            brandRepository.save(brand);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public List<Brand> getListAllBrands() {
        try {
            return brandRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public long getTotalBrands(){
        return brandRepository.getTotalBrands();
    }
    public List<Brand> getListBrandByBrandNameContaining( String brandName){
        return brandRepository.getListBrandByBrandNameContaining(brandName);
    }

}
