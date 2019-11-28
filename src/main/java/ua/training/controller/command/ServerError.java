package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.PAGE_SERVER_ERROR;

public class ServerError implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return PAGE_SERVER_ERROR;
    }
}
