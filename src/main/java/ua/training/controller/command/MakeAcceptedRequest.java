package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.RequestService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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
//request.setAttribute("masters",userService.findByRole(Arrays
//                    .asList(Role.values())
//                    .indexOf(Role.MASTER)+1).get());

        }catch( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/manager/manager-accept-request.jsp";
    }
}
