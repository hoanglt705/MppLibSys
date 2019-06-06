package service;

import context.AppContext;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Role;
import domain.User;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class UserServiceImp implements IUserService {

    public static Role currentRole = null;

    @Override
    public void login(String username, String password) throws LoginException {

        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(username)) {
            throw new LoginException("User " + username + " not found");
        }
        String passwordFound = map.get(username).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentRole = map.get(username).getRoles();
        AppContext appContext = AppContext.getInstance();
        User currUsr = new User(username, password, currentRole);
        appContext.user = currUsr;

    }
}
