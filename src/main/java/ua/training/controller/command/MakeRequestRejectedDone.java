package ua.training.controller.command;

import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.util.Constants.URL_MANAGER_NEW_REQUESTS;

public class MakeRequestRejectedDone implements Command {

    private RequestService requestService;

    public MakeRequestRejectedDone(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            String reason = request.getParameter("reason");

            requestService.updateStatusAndReason(id, "rejected", reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/app/" + URL_MANAGER_NEW_REQUESTS;
    }
}
