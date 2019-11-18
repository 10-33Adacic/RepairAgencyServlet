package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class MakeInProgress implements Command {

    private RequestService requestService;
    private static final Logger logger = LogManager.getLogger(Login.class);

    public MakeInProgress(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try{
            logger.info(request.getParameter("id"));
            Long id=Long.parseLong(request.getParameter("id"));

            requestService.updateRequest("in progress", id);
        }catch( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api/app/master/accepted_requests";
    }
}
