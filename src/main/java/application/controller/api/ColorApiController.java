package application.controller.api;

import application.data.model.Color;
import application.data.service.ColorService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ColorDTO;

import application.model.viewmodel.common.ColorVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/color")
public class ColorApiController {
    @Autowired
    private ColorService colorService;

    @RequestMapping(value="/detail", params = {"colorId"}) //params có/không => tùy
    public @ResponseBody
    ColorVM getcolorInfor (@RequestParam(value = "colorId") int colorId){
        Color color = colorService.findOne(colorId);
        if(color==null)
            return null;
        ColorVM colorVM= new ColorVM();
        colorVM.setId(color.getId());
        colorVM.setName(color.getName());
        colorVM.setShortDesc(color.getShortDesc());
        colorVM.setCreatedDate(color.getCreatedDate());

        return colorVM;
    }

    @PostMapping(value="/delete/{colorId}") //params có/không => tùy
    public @ResponseBody
    BaseApiResult delete (@PathVariable int colorId){
        BaseApiResult result= new BaseApiResult();
        try {
            result.setSuccess(true);
            result.setMessage("Delete color successfully");
            colorService.delColor(colorId);

        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @PostMapping("/update/{colorId}")
    public BaseApiResult update (@PathVariable int colorId,
                                 @RequestBody ColorDTO dto){
        BaseApiResult result = new BaseApiResult();
        try{
            Color color = colorService.findOne(colorId);
            color.setId(dto.getId());
            color.setName(dto.getName());
            color.setShortDesc(dto.getShortDesc());
            color.setShortDesc(dto.getShortDesc());
            colorService.addNewColor(color);
            result.setSuccess(true);
            result.setMessage("Update color successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/create")
    public BaseApiResult createProduct(@RequestBody ColorDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            Color color = new Color();
            color.setName(dto.getName());
            color.setShortDesc(dto.getShortDesc());
            color.setCreatedDate(new Date());
            colorService.addNewColor(color);
            result.setData(color.getId());
            result.setMessage("Save color successfully: " + color.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
