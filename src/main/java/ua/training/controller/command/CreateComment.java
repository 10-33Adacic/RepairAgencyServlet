package ua.training.controller.command;

import ua.training.model.service.CommentService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class CreateComment implements Command {

    private CommentService commentService;
    private UserService userService;

    public CreateComment(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String commentName = request.getParameter("comment");

        String userName = (String) request.getSession().getAttribute("userName");

        if (commentName == null || commentName.equals("")) {
            return "/WEB-INF/user/user-create-comment.jsp";
        }
//        try {
        userService.findByEmail(userName).ifPresent(u -> {
            try {
                commentService.addComment(commentName, u);
                request.setAttribute("sucess", true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
//            commentService.addComment(commentName, userService.findByEmail(userName).get());
//
////                request.setAttribute("sucess", true);
//
//        } catch (SQLException | RuntimeException e) {
//            e.printStackTrace();
//        }
        return "/WEB-INF/user/user-create-comment.jsp";
    }
}
