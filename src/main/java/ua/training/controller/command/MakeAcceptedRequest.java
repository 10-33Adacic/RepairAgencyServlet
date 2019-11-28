package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.controller.util.Constants.MANAGER_ACCEPTED_REQUEST;

public class MakeAcceptedRequest implements Command {

    private UserService userService;

    public MakeAcceptedRequest(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try{
            Long id=Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id);
            List<User> masters = userService.findAllMasters();
            request.setAttribute("masters", masters);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return MANAGER_ACCEPTED_REQUEST;
    }
}
