package ua.training.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.controller.command.*;
import ua.training.model.service.CommentService;
import ua.training.model.service.RequestService;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static ua.training.controller.util.Constants.*;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(Login.class);

    public void init(ServletConfig servletConfig) {
        UserService userService = new UserService();
        RequestService requestService=new RequestService();
        CommentService commentService=new CommentService();

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put(LOGOUT, new LogOut());
        commands.put(LOGIN, new Login(userService));
        commands.put(REGISTRATION, new Registration(userService));
        commands.put(URL_SERVER_ERROR, new ServerError());

        commands.put(URL_USER_CREATE_REQUEST, new CreateRequest(requestService));
        commands.put(URL_USER_ALL_REQUESTS, new UserAllRequest(requestService));
        commands.put(URL_USER_CREATE_COMMENT, new CreateComment(commentService,userService));

        commands.put(URL_MASTER_ACCEPTED_REQUESTS, new AcceptedRequests(requestService));
        commands.put(URL_MASTER_ACCEPTED_REQUESTS_MAKE_IN_PROGRESS, new MakeInProgress(requestService));
        commands.put(URL_MASTER_IN_PROGRESS_REQUESTS, new InProgressRequests(requestService));
        commands.put(URL_MASTER_IN_PROGRESS_REQUESTS_DONE, new MakeCompleted(requestService));
        commands.put(URL_MASTER_IN_PROGRESS_REQUESTS_BEYOUND_REPAIR, new MakeBeyondRepair(requestService));
        commands.put(URL_MASTER_COMPLETED_REQUESTS, new CompletedRequests(requestService));

        commands.put(URL_MANAGER_NEW_REQUESTS, new NewRequests(requestService));
        commands.put(URL_MANAGER_NEW_REQUESTS_ACCEPTED, new MakeAcceptedRequest(userService));
        commands.put(URL_MANAGER_NEW_REQUESTS_ACCEPTED_DONE, new MakeRequestAcceptedDone(requestService,userService));
        commands.put(URL_MANAGER_NEW_REQUESTS_REJECT, new MakeRejectedRequest());
        commands.put(URL_MANAGER_NEW_REQUESTS_REJECT_DONE, new MakeRequestRejectedDone(requestService));

        commands.put(URL_MANAGER_ALL_COMMENTS, new ManagerAllComments(commentService));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        String path = request.getRequestURI();
        logger.info(path);
        path = path.replaceAll(".*/app/", "");
        logger.info(path);
        //TODO 404
        Command command = commands.getOrDefault(path,
                (r) -> PAGE_NOT_FOUND_ERROR);

        String page = command.execute(request);

        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    } catch (Exception e) {
            response.sendRedirect("/app/" + URL_SERVER_ERROR);
        }
    }
}