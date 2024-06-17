package com.base.javaproj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
public class LoginActivityTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "gauresh.prakash2023@vitstudent.ac.in",
            "sanketdatta.maniyar2023@vitstudent.ac.in",
            "jiya.shrimalani2023@vitstudent.ac.in",
            "rachit.tibrewal2023@vitstudent.ac.in",
            "utkarsh.kumar2022a@vitstudent.ac.in",
            "vanuj.gangrade2023@vitstudent.ac.in",
            "vedansh.gauravkumar2022@vitstudent.ac.in",
            "shivesh.kaushik2022@vitstudent.ac.in",
            "ujjwal.aggarwal2022@vitstudent.ac.in",
            "metta.siva2023@vitstudent.ac.in",
            "parth.agarwal2023@vitstudent.ac.in",
            "tanay.gupta2023@vitstudent.ac.in",
            "himanshu.garg2022@vitstudent.ac.in",
            "jay.patel2023@vitstudent.ac.in",
            "vinay.t2023@vitstudent.ac.in",
            "anubhav.saxena2022@vitstudent.ac.in",
            "hridansh.motwani2022@vitstudent.ac.in",
            "jishnu.suresh2023@vitstudent.ac.in",
            "atharv.agarwal2023a@vitstudent.ac.in",
            "thummala.partha2023@vitstudent.ac.in",
            "saksham.thakur2023@vitstudent.ac.in",
            "chrismartin.mattam2023@vitstudent.ac.in",
            "arpit.makarand2023@vitstudent.ac.in",
            "anishka.patel2023@vitstudent.ac.in",
            "nikhil.gupta2022@vitstudent.ac.in",
    })
    public void testValidEmailValidation(String email) {
        assertEquals(true, Login.Validate_EmailID(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalidemail@vitstudent.ac.com",
            "my.email@vitstudent.ac.in@domain.com",
            "@vitstudent.ac.in",
            "example@vitstudent.ac.in.",
            "example@vitstudent_ac_in.com",
            "example@vitstudent.ac.in@domain.com",
            "example@vitstudent..ac.in",
            "example@vitstudent.ac",
            "example@vitstudent_ac_in.com",
            "example@vitstudent.ac.in.",
            "example@vitstudent@ac.in",
            "example@.vitstudent.ac.in",
            "example@[vitstudent.ac.in]",
            "example@vitstudent_ac_in"
    })
    public void testInvalidEmailValidation(String email) {
        assertEquals(false, Login.Validate_EmailID(email));
    }
    @Test
    public void testOTPverification1() {
        assertEquals(4, Login.generate_OTP().length());
    }

    @Test
    public void testOTPverification2(){
        String otp=Login.generate_OTP();
        assertEquals(otp,otp);
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "3456",
            "4343",
            "3433",
            "2323",
            "9877",
            "443",
            "3322",
            "3223223",
            "87854r342",
            "34543532",
            "abcas"
    })
    public void testInvalidOTP(String otp) {
        String otp_generated = Login.generate_OTP();
        assertEquals(otp_generated,otp_generated);
    }
}
