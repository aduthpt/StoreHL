//package application.data.service;
//
//import application.data.model.Cmt;
//import application.data.repository.CmtRepository;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CmtService {
//    private static final Logger logger = LogManager.getLogger(CmtService.class);
//
//    @Autowired
//    private CmtRepository cmtRepository;
//
//    public void addNewCmt(Cmt cmt) {
//        cmtRepository.save(cmt);
//    }
//
//    public boolean updateCmt(Cmt cmt) {
//        try {
//            cmtRepository.save(cmt);
//            return true;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//        return false;
//    }
//
//    public Cmt findOne(int cmtId) {
//        return cmtRepository.findOne(cmtId);
//    }
//
//    public boolean deleteCmt(int cmtId) {
//        try {
//            cmtRepository.delete(cmtId);
//            return true;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//        return false;
//    }
//
//
////    @Transactional
////    public boolean deleteListCartProducts(List<CartProduct> cartProducts) {
////        try {
////            cartProductRepository.delete(cartProducts);
////            return true;
////        } catch (Exception e) {
////            logger.error(e.getMessage());
////        }
////        return false;
////    }
//public long getTotalCmts(){
//    return cmtRepository.getTotalCmts();
//}
//    public Cmt findFirstCmtByUserIdAndProductId(int userId, int productId) {
//        try {
//            return  cmtRepository.findFirstCmtByUserIdAndProductId(userId, productId);
//        }catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//        return null;
//    }
//}
