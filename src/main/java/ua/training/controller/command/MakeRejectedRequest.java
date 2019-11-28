package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.MANAGER_REJECTED_REQUESTS;

public class MakeRejectedRequest implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try{
            Long id=Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return MANAGER_REJECTED_REQUESTS;
    }
}
