package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        CommandUtility.deleteUserFromContextAndSession(request);
        return "redirect:/api";
    }
}
