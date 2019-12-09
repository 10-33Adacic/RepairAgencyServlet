package ua.training.controller.command;

import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.MANAGER_NEW_REQUEST;

public class NewRequests implements Command {

    private RequestService requestService;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 6;

    public NewRequests(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer page = null;
        Integer size = null;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = DEFAULT_PAGE;
        }

        try {
            size = Integer.parseInt(request.getParameter("size"));
        } catch (NumberFormatException e) {
            size = DEFAULT_SIZE;
        }

        try {
            long elementsCount = requestService.findCount();
            requestService.findByStatus("new", page,size).ifPresent(requests -> request.setAttribute("newRequests",requests));
            request.setAttribute("page", page);
            request.setAttribute("size", size);
            request.setAttribute("pagesCount", (int) Math.ceil(elementsCount * 1.0 / size));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MANAGER_NEW_REQUEST;
    }
}
