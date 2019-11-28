package ua.training.controller.command;

import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.MANAGER_NEW_REQUEST;

public class NewRequests implements Command {

    private RequestService requestService;

    public NewRequests(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            requestService.findByStatus("new").ifPresent(requests -> request.setAttribute("newRequests",requests));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MANAGER_NEW_REQUEST;
    }
}
