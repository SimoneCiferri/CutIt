package testutils;

import cutit.utils.ListFromModelList;
import cutit.utils.TextFieldCheck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTextFieldCheck {

    @Test
    public void testIsEmailAddress(){
        String email = "thisIsValidEmail@email.com";
        boolean output = TextFieldCheck.isEmailAddress(email);
        assertTrue(output);
    }

    @Test
    public void testIsPiva(){
        String validPIVA = "11332245876";
        boolean output = TextFieldCheck.isPiva(validPIVA);
        assertTrue(output);
    }

    @Test
    public void testCheckAddress(){
        String validAddress = "Via Roma-Roma-00133";
        boolean output = TextFieldCheck.checkAddress(validAddress);
        assertTrue(output);
    }

    @Test
    public void testIsSomethingNull(){
        List<String> stringList = new ArrayList<>();
        String notNullString1 = "String1";
        stringList.add(notNullString1);
        String notNullString2 = "String2";
        stringList.add(notNullString2);
        String notNullString3 = "String3";
        stringList.add(notNullString3);
        boolean output = TextFieldCheck.isSomethingNull(stringList);
        assertFalse(output);
    }

    @Test
    public void testIsPhoneNumber(){
        String validPhoneNumber = "3345544698";
        boolean output = TextFieldCheck.isPhoneNumber(validPhoneNumber);
        assertTrue(output);
    }

    @Test
    public void testIsInteger(){
        String validInteger = "15";
        boolean output = TextFieldCheck.isInteger(validInteger, "Invalid Input.");
        assertTrue(output);
    }

    @Test
    public void testIsNumeric(){
        String validNumericInput = "13.5";
        boolean output = TextFieldCheck.isNumeric(validNumericInput, "Invalid Input.");
        assertTrue(output);
    }



}
