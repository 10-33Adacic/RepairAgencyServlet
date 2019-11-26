package ua.training.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dto.UserDTO;
import ua.training.model.entity.Role;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Registration implements Command {

    private UserService userService;
    private static final Logger logger = LogManager.getLogger(Login.class);
    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String role = request.getParameter("role");
        logger.info("Regisatation: "
                + "name: " + name + "   "
                + "email: " + email + "   "
                + "password: " + password + "   "
                + "role: " + role);

        if (name == null || name.equals("") || password == null || password.equals("") || email == null || email.equals("")) {
            return "/registration.jsp";
        }
        try {
            if (userService.findUserEmail(email).isPresent()) {
                request.setAttribute("error", true);
                return "/registration.jsp";
//                throw new RuntimeException("User with this email already exist");

            }
            String userRole = "";
            if (role.equals("USER"))
                userRole = Role.USER.name();
            if (role.equals("MASTER"))
                userRole = Role.MASTER.name();
            if (role.equals("MANAGER"))
                userRole = Role.MANAGER.name();

            userService.addUser(
                    UserDTO.builder().email(email)
                    .name(name)
                    .password(password)
                    .role(Role.valueOf(userRole))
                    .build());


        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
        return "/welcome.jsp";
    }
}
