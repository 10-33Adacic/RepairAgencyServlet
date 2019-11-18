package ua.training.controller.command;

import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;


public class UserAllRequest implements Command {

    private RequestService requestService;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;

    public UserAllRequest(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userName= (String)request.getSession().getAttribute( "userName");
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
            requestService.findByCreatorAndStatus(userName,"rejected").ifPresent(requests->request.setAttribute("rejectRequests",requests));
            requestService.findByCreatorAndNotStatus(userName, "rejected", page,size).ifPresent(requests->request.setAttribute("requests",requests));
            request.setAttribute("page", page);
            request.setAttribute("size", size);
            request.setAttribute("pagesCount", (int) Math.ceil(elementsCount * 1.0 / size));

        } catch ( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/user/user-all-request.jsp";
    }
}
