package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static ua.training.controller.util.Constants.*;

public class Login implements Command {

    private static final Logger logger = LogManager.getLogger(Login.class);
    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("pass");

        if (email == null)
            return LOGIN_PAGE;

        logger.info("user enter email: " + email + " " + password);

        if (nonNull(request.getSession().getAttribute("userEmail")))
            return "/welcome.jsp";

        Optional<User> user = userService.findUser(email, password);

        if (!user.isPresent()) {
            logger.info("Invalid attempt of user email: '" + email + "'");
            request.setAttribute("error", true);
            return LOGIN_PAGE;
        }

        if (CommandUtility.checkUserIsLogged(request, email)) {
            request.setAttribute("error", true);
            logger.info("User email " + email + " already logged.");
            throw new RuntimeException("You are already logged");
        }
        logger.info("User email " + email + " logged successfully.");

        request.getSession(true).setAttribute("userName", email);

        if (user.get().getRole().equals(Role.MASTER)) {
            CommandUtility.setUserRole(request, Role.MASTER, email);
            return "redirect:/app/" + URL_MASTER_ACCEPTED_REQUESTS;
        } else if (user.get().getRole().equals(Role.USER)) {
            CommandUtility.setUserRole(request, Role.USER, email);
            return "redirect:/app/" + URL_USER_CREATE_REQUEST;
        } else if (user.get().getRole().equals(Role.MANAGER)) {
            CommandUtility.setUserRole(request, Role.MANAGER, email);
            return "redirect:/app/" + URL_MANAGER_NEW_REQUESTS;
        } else {
            return "redirect:" + INDEX_PAGE;
        }
    }
}