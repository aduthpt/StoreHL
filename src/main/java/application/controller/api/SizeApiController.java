package application.controller.api;

import application.data.model.Size;
import application.data.service.SizeService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.SizeDTO;

import application.model.viewmodel.common.SizeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/size")
public class SizeApiController {
    @Autowired
    private SizeService sizeService;

    @RequestMapping(value="/detail", params = {"sizeId"}) //params có/không => tùy
    public @ResponseBody SizeVM getSizeInfor (@RequestParam(value = "sizeId") int sizeId){
        Size size = sizeService.findOne(sizeId);
        if(size==null)
            return null;
        SizeVM sizeVM= new SizeVM();
        sizeVM.setId(size.getId());
        sizeVM.setName(size.getName());
        sizeVM.setShortDesc(size.getShortDesc());
        sizeVM.setCreatedDate(size.getCreatedDate());

        return sizeVM;
    }

    @PostMapping(value="/delete/{sizeId}") //params có/không => tùy
    public @ResponseBody
    BaseApiResult delete (@PathVariable int sizeId){
        BaseApiResult result= new BaseApiResult();
        try {
            result.setSuccess(true);
            result.setMessage("Delete category successfully");
            sizeService.delSize(sizeId);

        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @PostMapping("/update/{sizeId}")
    public BaseApiResult update (@PathVariable int sizeId,
                                 @RequestBody SizeDTO dto){
        BaseApiResult result = new BaseApiResult();
        try{
            Size size = sizeService.findOne(sizeId);
            size.setId(dto.getId());
            size.setName(dto.getName());
            size.setShortDesc(dto.getShortDesc());
            size.setShortDesc(dto.getShortDesc());
            sizeService.addNewSize(size);
            result.setSuccess(true);
            result.setMessage("Update size successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/create")
    public BaseApiResult createProduct(@RequestBody Size dto){
        DataApiResult result = new DataApiResult();

        try {
            Size size = new Size();
            size.setName(dto.getName());
            size.setShortDesc(dto.getShortDesc());
            size.setCreatedDate(new Date());
            sizeService.addNewSize(size);
            result.setData(size.getId());
            result.setMessage("Save size successfully: " + size.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
