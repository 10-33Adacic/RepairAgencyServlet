package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class MakeRejectedRequest implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try{
            Long id=Long.parseLong(request.getParameter("id"));

            request.setAttribute("id", id);

        }catch( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/manager/manager-reject-request.jsp";
    }
}
