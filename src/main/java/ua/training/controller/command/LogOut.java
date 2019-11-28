package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.LOGIN_PAGE;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        CommandUtility.deleteUserFromContextAndSession(request);
        return LOGIN_PAGE;
    }
}
