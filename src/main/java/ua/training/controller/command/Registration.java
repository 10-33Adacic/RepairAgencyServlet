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
        logger.info("Regisatation: " + name + " " + email + " " + " " + password);

        if (name == null || name.equals("") || password == null || password.equals("") || email == null || email.equals("")) {
            return "/registration.jsp";
        }
        try {
            if (userService.findUserEmail(email).isPresent()) {
                request.setAttribute("error", true);
//                request.setAttribute("inwalidInput", "User with this email already exist");
                return "/registration.jsp";
//                throw new RuntimeException("User with this email already exist");

            }
            String userRole = "";
            if (role.equals("ROLE_USER"))
                userRole = Role.ROLE_USER.name();
            if (role.equals("ROLE_MASTER"))
                userRole = Role.ROLE_MASTER.name();
            if (role.equals("ROLE_MANAGER"))
                userRole = Role.ROLE_MANAGER.name();

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
