package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.URL_MASTER_IN_PROGRESS_REQUESTS;

public class MakeBeyondRepair implements Command {
    private RequestService requestService;
    private static final Logger logger = LogManager.getLogger(Login.class);

    public MakeBeyondRepair(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try{
            logger.info(request.getParameter("id"));
            Long id=Long.parseLong(request.getParameter("id"));
            requestService.updateRequest("beyond repair", id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/app/" + URL_MASTER_IN_PROGRESS_REQUESTS;
    }
}
