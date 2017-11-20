package security;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    String findLoggedInUsername();

    void authUser(String username, String password, HttpServletRequest request);

    void autoLogin(String username, String password);
}
