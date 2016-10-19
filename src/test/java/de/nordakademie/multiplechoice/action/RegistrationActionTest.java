package de.nordakademie.multiplechoice.action;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferenc on 19.10.2016.
 */
public class RegistrationActionTest {
    private static final String CORRECT_FIRST_NAME = "Hans";
    private static final String CORRECT_LAST_NAME = "Müller";
    private static final String CORRECT_MAIL = "hans.müller@nordakademie.de";
    private static final String CORRECT_PASSWORD = "!Password1";
    private RegistrationAction registrationAction;

    @Test
    public void testFirstNameValidation() {
        registrationAction = Mockito.spy(new RegistrationAction());
        registrationAction.setLastName(CORRECT_LAST_NAME);
        registrationAction.setMail(CORRECT_MAIL);
        registrationAction.setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testLastNameValidation() {
        registrationAction = Mockito.spy(new RegistrationAction());
        registrationAction.setFirstName(CORRECT_FIRST_NAME);
        registrationAction.setMail(CORRECT_MAIL);
        registrationAction.setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testMailBasicValidation() {
        registrationAction = Mockito.spy(new RegistrationAction());
        registrationAction.setFirstName(CORRECT_FIRST_NAME);
        registrationAction.setLastName(CORRECT_LAST_NAME);
        registrationAction.setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testMailNakOnlyValidation() {
        registrationAction = Mockito.spy(new RegistrationAction());
        registrationAction.setFirstName(CORRECT_FIRST_NAME);
        registrationAction.setLastName(CORRECT_LAST_NAME);
        registrationAction.setMail("incorrect.mail@gmail.com");
        registrationAction.setPassword(CORRECT_PASSWORD);

        registrationAction.validate();
        Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void testPasswordBasicValidation() {
        registrationAction = Mockito.spy(new RegistrationAction());
        registrationAction.setFirstName(CORRECT_FIRST_NAME);
        registrationAction.setLastName(CORRECT_LAST_NAME);
        registrationAction.setMail(CORRECT_MAIL);

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

        for (String incorrectPassword : incorrectPasswords) {
            registrationAction = Mockito.spy(new RegistrationAction());
            registrationAction.setFirstName(CORRECT_FIRST_NAME);
            registrationAction.setLastName(CORRECT_LAST_NAME);
            registrationAction.setMail(CORRECT_MAIL);
            registrationAction.setPassword(incorrectPassword);

            registrationAction.validate();
            Mockito.verify(registrationAction, Mockito.times(1)).addFieldError(Mockito.anyString(), Mockito.anyString());
        }
    }
}