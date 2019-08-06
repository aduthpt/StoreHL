//package application.data.service;
//
//import application.data.model.Ratting;
//import application.data.repository.RattingRepository;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RattingService {
//    private static final Logger logger = LogManager.getLogger(RattingService.class);
//
//    @Autowired
//    private RattingRepository rattingRepository;
//
//    public void addNewRatting(Ratting ratting) {
//        rattingRepository.save(ratting);
//    }
//    public Ratting findOne(int rattingId){
//        return rattingRepository.findOne(rattingId);
//    }
//
//    public Ratting findFirstProductId(int productId){
//        try {
//            return  rattingRepository.findFirstProductId(productId);
//        }catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//        return null;
//    }
//}
