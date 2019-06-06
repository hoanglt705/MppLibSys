package service;


import javax.security.auth.login.LoginException;

public interface IUserService {
    public void login(String username, String password) throws LoginException;

}
