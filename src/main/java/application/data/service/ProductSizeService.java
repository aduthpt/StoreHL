package application.data.service;

import application.data.model.ProductSize;
import application.data.repository.ProductSizeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductSizeService {

    private static final Logger logger = LogManager.getLogger(ProductSizeService.class);

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Transactional
    public void addNewListProductSize(List<ProductSize> productSizes) {
        try {
            productSizeRepository.save(productSizes);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewProductSize(ProductSize productSize) {
        try {
            productSizeRepository.save(productSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public ProductSize findOne(int productSizeId) {
        return productSizeRepository.findOne(productSizeId);
    }

    public boolean updateProductSize(ProductSize productSize) {
        try {
            productSizeRepository.save(productSize);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteProductSize(int productSizeId) {
        try {
            productSizeRepository.delete(productSizeId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }



}
