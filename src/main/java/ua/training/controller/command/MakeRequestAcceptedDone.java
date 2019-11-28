package ua.training.controller.command;

import ua.training.model.service.RequestService;
import ua.training.model.service.UserService;
import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.URL_MANAGER_NEW_REQUESTS;


public class MakeRequestAcceptedDone implements Command {

    private RequestService requestService;
    private UserService userService;

    public MakeRequestAcceptedDone(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        try{
            Long id=Long.parseLong(request.getParameter("id"));
            String mastermail=request.getParameter("email");
            Long price=Long.parseLong(request.getParameter("price"));

            requestService.updateStatusAndPriceAndUser(id,"accepted",price,userService.findByEmail(mastermail).get());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/app/" + URL_MANAGER_NEW_REQUESTS;
    }
}
