package application.data.service;

import application.data.model.Size;
import application.data.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public Size findOne(int SizeId){
        return sizeRepository.findOne(SizeId);
    }

    public List<Size> getAll(){
        return sizeRepository.findAll();
    }

    public void addNewSize(Size Size){
        sizeRepository.save(Size);
    }

    @Transactional
    public void delSize(int SizeId){
        sizeRepository.delete(SizeId);
    }

    public List<Size> getListSizeByName(String SizeName){
        return sizeRepository.getListSizeByName(SizeName);
    }

    public List<Size> getListSizeByProductId (Integer productId){
        return sizeRepository.getListSizeByProductId(productId);
    }

}
