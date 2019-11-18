package ua.training.controller.command;

import ua.training.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;

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
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api/app/manager/new_requests";
    }
}
