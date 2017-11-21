package pl.edu.agh.eaiib.appium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.edu.agh.eaiib.appium.junit.AppiumExtension;

@ExtendWith(AppiumExtension.class)
public class UserLoginTests {

    @Test
    @DisplayName("Cannot login with incorrect username and password")
    void testLogin1() {

    }

    @Test
    @DisplayName("User can login with correct username and password")
    void testLogin2() {

    }

}
