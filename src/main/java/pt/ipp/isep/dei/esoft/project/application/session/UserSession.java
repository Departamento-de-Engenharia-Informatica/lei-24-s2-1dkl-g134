package pt.ipp.isep.dei.esoft.project.application.session;

import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

public class UserSession {

    private pt.isep.lei.esoft.auth.UserSession userSession;

    public UserSession(pt.isep.lei.esoft.auth.UserSession userSession) {
        this.userSession = userSession;
    }

    public String getUserEmail() {
        return userSession.getUserId().getEmail();
    }

    public String getUserName() {
        return this.userSession.getUserName();
    }

    public Email getUserID(){return userSession.getUserId();}

    public List<UserRoleDTO> getUserRoles() {
        return this.userSession.getUserRoles();
    }

    public void doLogout() {
        this.userSession.doLogout();
    }

    public boolean isLoggedIn() {
        return this.userSession.isLoggedIn();
    }

    public boolean isLoggedInWithRole(String roleId) {
        return this.userSession.isLoggedInWithRole(roleId);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof UserSession)){
            return false;
        }
        UserSession other = (UserSession) o;
        if(other.getUserEmail().equalsIgnoreCase(getUserEmail()) || other.getUserName().equalsIgnoreCase(getUserName())){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return getUserName() + " | " + getUserEmail();
    }
}