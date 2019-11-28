package ua.training.controller.command;

import ua.training.model.service.CommentService;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.training.controller.util.Constants.USER_CREATE_COMMENT;


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
            return USER_CREATE_COMMENT;
        }

        userService.findByEmail(userName).ifPresent(u -> {
            try {
                commentService.addComment(commentName, u);
                request.setAttribute("sucess", true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return USER_CREATE_COMMENT;
    }
}
