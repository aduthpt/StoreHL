package application.data.service;

import application.data.model.Color;
import application.data.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public Color findOne(int ColorId){
        return colorRepository.findOne(ColorId);
    }

    public List<Color> getAll(){
        return colorRepository.findAll();
    }

    public void addNewColor(Color color){
        colorRepository.save(color);
    }

    @Transactional
    public void delColor(int ColorId){
        colorRepository.delete(ColorId);
    }

    public List<Color> getListColorByName(String ColorName){
        return colorRepository.getListColorByName(ColorName);
    }

    public List<Color> getListColorByProductId (Integer productId){
        return  colorRepository.getListColorByProductId(productId);
    }


}
