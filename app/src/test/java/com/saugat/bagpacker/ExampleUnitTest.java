package com.saugat.bagpacker;

import com.saugat.bagpacker.bll.HotelBLL;
import com.saugat.bagpacker.bll.LoginBLL;
import com.saugat.bagpacker.bll.SignupBLL;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test

    public void testlogin() {
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("saugat123", "saugat123");
        assertEquals(true, result);
    }


    @Test

    public void testLoginwithwrongpw() {
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("saugat123", "saugat12");
        assertEquals(false, result);
    }

    @Test

    public void addnewuser() {
        SignupBLL signupBLL = new SignupBLL();
        boolean result = signupBLL.addUser("bibekayadi", "saugat12","bibek123","9876464646","bibek123@gmail.com","bibek.png");
        assertEquals(true, result);
    }

    @Test

    public void conflictUsername() {
        SignupBLL signupBLL = new SignupBLL();
        boolean result = signupBLL.addUser("bibekayadi", "saugat12","bibek123","9876464646","bibek123@gmail.com","bibek.png");
        assertEquals(false, result);
    }

    @Test

    public void fetchHotel() {
        HotelBLL hotelBLL = new HotelBLL();
        boolean result = hotelBLL.getHoteldetails("5e3db6b7b78fba329b98d4fc");
        assertEquals(true, result);
    }

}
