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

//    @Override
//    public String execute(HttpServletRequest request) {
//        String email = request.getParameter("email");
//        String password = request.getParameter("pass");
//        if (email == null) return "/login.jsp";
//        logger.info("user enter email: " + email + " " + password);
//
////        logger.info("entering DB : ");
////        userService.findAllUsers().isPresent().get().forEach(logger::info);
//
//        if (nonNull(request.getSession().getAttribute("userEmail"))) return "/welcome.jsp";
//        Optional<User> user = userService.findUser(email, password);
//        if (!user.isPresent()) {
//            logger.info("Invalid attempt of user email: '" + email + "'");
//            request.setAttribute("error", true);
//            return "/login.jsp";
//        }
//        if (CommandUtility.checkUserIsLogged(request, email)) {
//            request.setAttribute("error", true);
//            logger.info("User email " + email + " already logged.");
//            throw new RuntimeException("You are already logged");
//        }
//        logger.info("User email " + email + " logged successfully.");
//
//        request.getSession(true).setAttribute("userName", email);
//
//        if (user.get().getRole().equals(Role.MASTER)) {
//            CommandUtility.setUserRole(request, Role.MASTER, email);
//            return "redirect:/api/app/master/accepted_requests";
//        } else if (user.get().getRole().equals(Role.USER)) {
//            CommandUtility.setUserRole(request, Role.USER, email);
//            return "redirect:/api/app/user/create_request";
//        } else if (user.get().getRole().equals(Role.MANAGER)) {
//            CommandUtility.setUserRole(request, Role.MANAGER, email);
//            return "redirect:/api/app/manager/new_requests";
//        } else {
//            return "redirect:/index.jsp";
//        }
//    }
//}









    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //TODO: Delete this after test
        System.out.println("Before ifs: username " + username);
        System.out.println("Before ifs: password " + password);

        if (username == null || password == null)
            return "/login.jsp";

        Optional<User> user = userService.findUser(username, password);

        //TODO: Delete this after test
            System.out.println("After 1 if: username " + username);
            System.out.println("After 1 if: password " + password);

        if (!user.isPresent()) {
            request.setAttribute("error", "Invalid username or password.");
            return "/login.jsp";
        }

        //TODO: Delete this after test
        System.out.println("After 2 if: username " + username);
        System.out.println("After 2 if: password " + password);

        if (CommandUtility.checkUserIsLogged(request, username)) {
            request.setAttribute("error", "You has already logged.");
            return "/login.jsp";
        }

        //TODO: Delete this after test
        System.out.println("After 3 if: username " + username);
        System.out.println("After 3 if: password " + password);

        if (user.get().getRole().equals(Role.MANAGER)) {
            CommandUtility.setUser(request, user.get());

            logger.info(user.get() + " logged successfully.");

            return "redirect:/app/" + "manager";
        } else if (user.get().getRole().equals(Role.MASTER)) {
            CommandUtility.setUser(request, user.get());

            logger.info(user.get() + " logged successfully.");

            return "redirect:/app/" + "master";
        } else {
            CommandUtility.setUser(request, user.get());

            logger.info(user.get() + " logged successfully.");

            return "redirect:/app/" + "user";
        }

    }
}