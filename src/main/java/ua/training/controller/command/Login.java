package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Objects.nonNull;

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
        if (email == null) return "/login.jsp";
        logger.info("user enter email: " + email + " " + password);

//        logger.info("entering DB : ");
//        userService.findAllUsers().isPresent().get().forEach(logger::info);

        if (nonNull(request.getSession().getAttribute("userEmail"))) return "/welcome.jsp";
        Optional<User> user = userService.findUser(email, password);
        if (!user.isPresent()) {
            logger.info("Invalid attempt of user email: '" + email + "'");
            request.setAttribute("error", true);
            return "/login.jsp";
        }
        if (CommandUtility.checkUserIsLogged(request, email)) {
            request.setAttribute("error", true);
            logger.info("User email " + email + " already logged.");
            throw new RuntimeException("You already logged");
        }
        logger.info("User email " + email + " logged successfully.");

        request.getSession(true).setAttribute("userName", email);

        if (user.get().getRole().equals(Role.ROLE_MASTER)) {
            CommandUtility.setUserRole(request, Role.ROLE_MASTER, email);
            return "redirect:/api/app/master/accepted_requests";
        } else if (user.get().getRole().equals(Role.ROLE_USER)) {
            CommandUtility.setUserRole(request, Role.ROLE_USER, email);
            return "redirect:/api/app/user/create_request";
        } else if (user.get().getRole().equals(Role.ROLE_MANAGER)) {
            CommandUtility.setUserRole(request, Role.ROLE_MANAGER, email);
            return "redirect:/api/app/manager/new_requests";
        }else {
            return "redirect:/index.jsp";
        }
    }
}