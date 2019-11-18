package ua.training.controller.command;

import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateRequest implements Command{

    private RequestService requestService;

    public CreateRequest(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String requestName=request.getParameter("request");

        String userName= (String)request.getSession().getAttribute( "userName");
        request.setAttribute("lang", request.getParameter("lang"));

        if (requestName == null || requestName.equals("")) {
            return "/WEB-INF/user/user-create-request.jsp";
        }
        try {
           if(requestService.addRequest(requestName, userName)!=null)
               request.setAttribute("sucess", true);

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/user/user-create-request.jsp";
    }
}
