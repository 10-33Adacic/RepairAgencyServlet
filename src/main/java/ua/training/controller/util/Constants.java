package ua.training.controller.util;

public interface Constants {
    String URL_SERVER_ERROR = "server-error";
    String URL_MASTER_ACCEPTED_REQUESTS = "master/accepted_requests";
    String URL_MASTER_ACCEPTED_REQUESTS_MAKE_IN_PROGRESS = "master/accepted_requests/make";
    String URL_MASTER_IN_PROGRESS_REQUESTS = "master/in_progress_requests";
    String URL_MASTER_IN_PROGRESS_REQUESTS_BEYOUND_REPAIR = "master/in_progress_requests/beyond_repair";
    String URL_MASTER_IN_PROGRESS_REQUESTS_DONE = "master/in_progress_requests/done";
    String URL_MASTER_COMPLETED_REQUESTS = "master/completed_requests";
    String URL_USER_CREATE_REQUEST = "user/create_request";
    String URL_USER_ALL_REQUESTS = "user/all_requests";
    String URL_USER_CREATE_COMMENT = "user/create_comment";
    String URL_MANAGER_NEW_REQUESTS = "manager/new_requests";
    String URL_MANAGER_NEW_REQUESTS_ACCEPTED = "manager/new_requests/accept";
    String URL_MANAGER_NEW_REQUESTS_ACCEPTED_DONE = "manager/new_requests/accept/done";
    String URL_MANAGER_NEW_REQUESTS_REJECT = "manager/new_requests/reject";
    String URL_MANAGER_NEW_REQUESTS_REJECT_DONE = "manager/new_requests/reject/done";
    String URL_MANAGER_ALL_COMMENTS = "manager/all-comments";

    String LOGIN_PAGE = "/login.jsp";
    String INDEX_PAGE = "/index.jsp";
    String WELCOME_PAGE = "/welcome.jsp";

    String PAGE_SERVER_ERROR = "/WEB-INF/error.jsp";
    String PAGE_NOT_FOUND_ERROR = "/WEB-INF/error404.jsp";

    String MASTER_ACCEPTED_REQUEST = "/WEB-INF/master/master-accepted-request.jsp";
    String MASTER_COMPLETED_REQUEST = "/WEB-INF/master/master-completed-request.jsp";
    String MASTER_IN_PROGRESS_REQUEST = "/WEB-INF/master/master-in-progress-request.jsp";

    String USER_CREATE_COMMENT = "/WEB-INF/user/user-create-comment.jsp";
    String USER_CREATE_REQUEST = "/WEB-INF/user/user-create-request.jsp";
    String USER_ALL_REQUESTS = "/WEB-INF/user/user-all-request.jsp";

    String MANAGER_ACCEPTED_REQUEST = "/WEB-INF/manager/manager-accept-request.jsp";
    String MANAGER_REJECTED_REQUESTS = "/WEB-INF/manager/manager-reject-request.jsp";
    String MANAGER_ALL_COMMENT = "/WEB-INF/manager/manager-all-comment.jsp";
    String MANAGER_NEW_REQUEST = "/WEB-INF/manager/manager-new-request.jsp";

    String LOGOUT = "logout";
    String LOGIN = "login";
    String REGISTRATION = "registration";
}
