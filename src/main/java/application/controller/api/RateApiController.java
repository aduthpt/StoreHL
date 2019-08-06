package application.controller.api;

import application.data.model.Rate;
import application.data.model.User;
import application.data.service.ProductService;
import application.data.service.RateService;
import application.data.service.UserService;
import application.model.api.BaseApiResult;
import application.model.dto.RateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/rate")
public class RateApiController {
    @Autowired
    UserService userService;

    @Autowired
    RateService rateService;

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public BaseApiResult addrate(@RequestBody RateDTO dto) {
        BaseApiResult result = new BaseApiResult();
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userService.findUserByUsername(username);

        try {
            Rate rate= new Rate();
            rate.setCreatedDate(new Date());
            rate.setComment(dto.getComment());
            rate.setStar(dto.getStar());
            rate.setProduct(productService.findOne(dto.getProductId()));
            rate.setUser(userEntity);
            rate.setRate(rateService.findOne(dto.getParentId()));
            rateService.addRate(rate);
            result.setMessage("Đánh giá thành công!");
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            // logger.error(e.getMessage());
        }
        result.setMessage("Không thêm được đánh giá!");
        result.setSuccess(false);
        return result;
    }
}
