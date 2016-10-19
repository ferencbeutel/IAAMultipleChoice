package de.nordakademie.multiplechoice.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.sun.deploy.security.MozillaMyKeyStore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class LoginActionTest {

    LoginAction loginAction;

    @Test
    public void validateNAKAdress() throws Exception {
        loginAction = Mockito.spy(new LoginAction());
        loginAction.setMail("ferenc.beutel@gmx.de");
        loginAction.setPassword("1234");
        loginAction.validate();
        Mockito.verify(loginAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void validateMailIsSet() throws Exception {
        loginAction = Mockito.spy(new LoginAction());
        loginAction.setPassword("1234");
        loginAction.validate();
        Mockito.verify(loginAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void validatePasswordIsSet() throws Exception {
        loginAction = Mockito.spy(new LoginAction());
        loginAction.setMail("user@nordakademie.de");
        loginAction.validate();
        Mockito.verify(loginAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void validateAllFieldsAreFilled() throws Exception {
        loginAction = Mockito.spy(new LoginAction());
        loginAction.validate();
        Mockito.verify(loginAction, Mockito.times(2)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }
}