package application.controller.api;


import application.data.model.Category;
import application.data.service.CategoryService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.CategoryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryApiController {

   private static final Logger logger = LogManager.getLogger(CategoryApiController.class);


   @Autowired
   private CategoryService categoryService;

   @GetMapping("/fake")
    public BaseApiResult fakeCategory(){
      BaseApiResult result = new BaseApiResult();

      try {
         long totalCategories = categoryService.getTotalCategories();
         List<Category> categoryList = new ArrayList<>();
         for(long i = totalCategories +1; i<= totalCategories + 5; i++) {
            Category category = new Category();
            category.setName("Category " + i);
            category.setShortDesc("Category " + i + " short desc");
            category.setCreatedDate(new Date());
            categoryList.add(category);
         }
         categoryService.addNewListCategories(categoryList);
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
   public BaseApiResult createCategory(@RequestBody CategoryDTO dto){
      BaseApiResult result = new BaseApiResult();

      try {
         Category categoryEntity = new Category();
         categoryEntity.setShortDesc(dto.getShortDesc());
         categoryEntity.setName(dto.getName());
         categoryEntity.setCreatedDate(new Date());
         categoryService.addNewCategory(categoryEntity);
         result.setSuccess(true);
         result.setMessage("Create category successfully: " + categoryEntity.getId());
      } catch (Exception e) {
         result.setSuccess(false);
         result.setMessage(e.getMessage());
         logger.error(e.getMessage());
      }
      return result;
   }

   @GetMapping("/detail/{categoryId}")
   public DataApiResult getDetailCategory(@PathVariable int categoryId){
      DataApiResult result= new DataApiResult();

      try {
         Category category = categoryService.findOne(categoryId);
         if(category == null) {
            result.setSuccess(false);
            result.setMessage("Can't find this category");
         } else {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setShortDesc(category.getShortDesc());
            dto.setCreatedDate(category.getCreatedDate());
            result.setSuccess(true);
            result.setMessage("Get detail category successfully !");
            result.setData(dto);
         }
      } catch (Exception e) {
         result.setSuccess(false);
         result.setMessage(e.getMessage());
         logger.error(e.getMessage());
      }

      return result;
   }

   @PostMapping("/update/{categoryId}")
   public BaseApiResult updateCategory(@PathVariable int categoryId, @RequestBody CategoryDTO dto){
      BaseApiResult result = new BaseApiResult();

      try {
         Category categoryEntity = categoryService.findOne(categoryId);
         categoryEntity.setShortDesc(dto.getShortDesc());
         categoryEntity.setName(dto.getName());
         categoryEntity.setCreatedDate(new Date());
         categoryService.addNewCategory(categoryEntity);
         result.setSuccess(true);
         result.setMessage("Update category " + categoryEntity.getId() + " successfully:");
      } catch (Exception e) {
         result.setSuccess(false);
         result.setMessage(e.getMessage());
         logger.error(e.getMessage());
      }
      return result;
   }


}
