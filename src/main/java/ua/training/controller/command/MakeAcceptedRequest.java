package ua.training.controller.command;

import ua.training.model.entity.Role;
import ua.training.model.service.RequestService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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
            request.setAttribute("masters",userService.findByRole(Arrays
                    .asList(Role.values())
                    .indexOf(Role.ROLE_MASTER)+1).get());

        }catch( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/manager/manager-accept-request.jsp";
    }
}
