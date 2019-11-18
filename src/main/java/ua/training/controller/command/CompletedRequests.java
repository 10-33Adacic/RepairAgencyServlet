package ua.training.controller.command;

import ua.training.model.entity.Request;
import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class CompletedRequests implements Command {

    private final RequestService requestService;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;

    public CompletedRequests(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String master= (String)request.getSession().getAttribute( "userName");
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
            requestService.findByMasterAndStatus(master, "completed",page,size).ifPresent(requests ->
                    request.setAttribute("completedRequests", requests));
            request.setAttribute("page", page);
            request.setAttribute("size", size);
            request.setAttribute("pagesCount", (int) Math.ceil(elementsCount * 1.0 / size));


        } catch ( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/master/master-completed-request.jsp";
    }
}
