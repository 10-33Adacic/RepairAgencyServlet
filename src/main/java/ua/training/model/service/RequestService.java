package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.RequestDao;
import ua.training.model.entity.Request;
import ua.training.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RequestService {

    private DaoFactory daoFactory;
    private RequestDao requestDao;

    public RequestService(){
        this.daoFactory = DaoFactory.getInstance();
        this.requestDao = daoFactory.createRequestDao();
    }

    public Request addRequest(String request, String userName) throws SQLException {
        Request newRequest = Request.builder()
                .request(request)
                .status("new")
                .price(0L)
                .creator(userName)
                .build();
        requestDao.add(newRequest);
        return newRequest;
    }

    public long findCount() {
        return requestDao.findCount();
    }
    public Optional<List<Request>>findByCreatorAndStatus(String creator, String status){
        return Optional.ofNullable(requestDao.findByCreatorAndStatus(creator,status));
    }

    public Optional<List<Request>>findByCreatorAndNotStatus(String creator, String status, int page, int size){
        return Optional.ofNullable(requestDao.findByCreatorAndNotStatus(creator,status,page,size));
    }

    public void updateRequest(String status, Long number){
        Request newRequest=Request.builder()
                .status(status)
                .id(number)
                .build();
        requestDao.update(newRequest);
    }

    public  Optional<List<Request>> findByMasterAndStatus(String master, String status,int page,int size){
        return Optional.ofNullable(requestDao.findByMasterAndStatus(master, status, page,size));
    }

    public Optional<List<Request>> findByStatus(String status){
        return Optional.ofNullable(requestDao.findByStatus(status));
    }

    public void updateStatusAndPriceAndUser(Long id,String status,Long price, User user){
         requestDao.updateStatusAndPriceAndUser(id, status,price,user);
    }

    public void updateStatusAndReason(Long id,String status,String reason){
        requestDao.updateStatusAndReason(id, status,reason);
    }
}
