package ua.training.controller.command;

import ua.training.model.service.CommentService;
import javax.servlet.http.HttpServletRequest;

public class ManagerAllComments implements Command {

    private CommentService commentService;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;

    public ManagerAllComments(CommentService commentService) {
        this.commentService = commentService;
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
            long elementsCount = commentService.findCount();
            commentService.findAllComment(page, size).ifPresent(com->request.setAttribute("comments",com));
            request.setAttribute("page", page);
            request.setAttribute("size", size);
            request.setAttribute("pagesCount", (int) Math.ceil(elementsCount * 1.0 / size));
        } catch ( java.lang.Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/manager/manager-all-comment.jsp";
    }
}
