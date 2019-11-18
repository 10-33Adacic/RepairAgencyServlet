package ua.training.controller.command;

import ua.training.model.service.RequestService;
import ua.training.model.service.UserService;
import javax.servlet.http.HttpServletRequest;


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
        }catch( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api/app/manager/new_requests";
    }
}
