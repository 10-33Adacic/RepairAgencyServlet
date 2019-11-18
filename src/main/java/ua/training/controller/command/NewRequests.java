package ua.training.controller.command;

import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class NewRequests implements Command {

    private RequestService requestService;

    public NewRequests(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            requestService.findByStatus("new").ifPresent(requests -> request.setAttribute("newRequests",requests));

        } catch ( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/manager/manager-new-request.jsp";
    }
}
