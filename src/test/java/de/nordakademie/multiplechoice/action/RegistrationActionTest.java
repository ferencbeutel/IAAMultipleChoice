package de.nordakademie.multiplechoice.action;

import de.nordakademie.multiplechoice.model.User;
import de.nordakademie.multiplechoice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferenc on 19.10.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationActionTest {
    private static final String CORRECT_FIRST_NAME = "Hans";
    private static final String CORRECT_LAST_NAME = "Müller";
    private static final String CORRECT_MAIL = "hans.müller@nordakademie.de";
    private static final String CORRECT_PASSWORD = "!Password1";
    @Mock
    private UserService userService;
    @InjectMocks
    private RegistrationAction registrationAction = Mockito.spy(new RegistrationAction());

    @Test
    public void testFirstNameValidation() {
        Mockito.when(userService.byMail(Mockito.anyString())).thenReturn(null);
        registrationAction.setUser(new User());
        registrationAction.getUser().setSurName(CORRECT_LAST_NAME);
        registrationAction.getUser().setEmail(CORRECT_MAIL);
        registrationAction.getUser().setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testLastNameValidation() {
        Mockito.when(userService.byMail(Mockito.anyString())).thenReturn(null);
        registrationAction.setUser(new User());
        registrationAction.getUser().setName(CORRECT_FIRST_NAME);
        registrationAction.getUser().setEmail(CORRECT_MAIL);
        registrationAction.getUser().setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testMailBasicValidation() {
        Mockito.when(userService.byMail(Mockito.anyString())).thenReturn(null);
        registrationAction.setUser(new User());
        registrationAction.getUser().setName(CORRECT_FIRST_NAME);
        registrationAction.getUser().setSurName(CORRECT_LAST_NAME);
        registrationAction.getUser().setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testMailNakOnlyValidation() {
        Mockito.when(userService.byMail(Mockito.anyString())).thenReturn(null);
        registrationAction.setUser(new User());
        registrationAction.getUser().setName(CORRECT_FIRST_NAME);
        registrationAction.getUser().setSurName(CORRECT_LAST_NAME);
        registrationAction.getUser().setEmail("incorrect.mail@gmx.de");
        registrationAction.getUser().setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testNoDuplicateMail() {
        Mockito.when(userService.byMail(Mockito.anyString())).thenReturn(new User());
        registrationAction.setUser(new User());
        registrationAction.getUser().setName(CORRECT_FIRST_NAME);
        registrationAction.getUser().setSurName(CORRECT_LAST_NAME);
        registrationAction.getUser().setEmail(CORRECT_MAIL);
        registrationAction.getUser().setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testPasswordBasicValidation() {
        Mockito.when(userService.byMail(Mockito.anyString())).thenReturn(null);
        registrationAction.setUser(new User());
        registrationAction.getUser().setName(CORRECT_FIRST_NAME);
        registrationAction.getUser().setSurName(CORRECT_LAST_NAME);
        registrationAction.getUser().setEmail(CORRECT_MAIL);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testPasswordCriteriaValidation() {
        final List<String> incorrectPasswords = new ArrayList<String>();
        incorrectPasswords.add("ONLYUPPERCASE");
        incorrectPasswords.add("UPPERCASEANDDIGITS132");
        incorrectPasswords.add("uppercaseAndLowercase");
        incorrectPasswords.add("ONLY_UPPERCASE_AND_SPECIAL");
        incorrectPasswords.add("onlylowercase");
        incorrectPasswords.add("lowercaseanddigits123");
        incorrectPasswords.add("only-lowercase-and-special");
        incorrectPasswords.add("!!!<>_-./&&!");
        incorrectPasswords.add("1337!!!<>_-.");
        incorrectPasswords.add("123456789");

        Mockito.when(userService.byMail(Mockito.anyString())).thenReturn(null);

        for (String incorrectPassword : incorrectPasswords) {
            registrationAction.setUser(new User());
            registrationAction.getUser().setName(CORRECT_FIRST_NAME);
            registrationAction.getUser().setSurName(CORRECT_LAST_NAME);
            registrationAction.getUser().setEmail(CORRECT_MAIL);
            registrationAction.getUser().setPassword(incorrectPassword);

            registrationAction.validate();
            Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
            Mockito.reset(registrationAction);
        }
    }
}