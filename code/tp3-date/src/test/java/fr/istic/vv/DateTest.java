package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // Tests if the isValidDate() method returns true for valid dates
    @Test
    public void testValidDate() {
        assertTrue(Date.isValidDate(29, 2, 2020));  
        assertTrue(Date.isValidDate(28, 2, 2019));  
        assertTrue(Date.isValidDate(31, 12, 2023)); 
        assertTrue(Date.isValidDate(1, 1, 2023));   
    }

    // Tests if the isValidDate() method returns false for invalid dates
    @Test
    public void testInvalidDate() {
        assertFalse(Date.isValidDate(31, 4, 2023));   
        assertFalse(Date.isValidDate(29, 2, 2019));   
        assertFalse(Date.isValidDate(0, 1, 2023));    
        assertFalse(Date.isValidDate(31, 13, 2023));  
        assertFalse(Date.isValidDate(31, -1, 2023));  
    }

    // Tests if the Date constructor works correctly for valid dates
    @Test
    public void testValidConstructor() {
        assertDoesNotThrow(() -> new Date(16, 4, 2002)); 
    }

    // Tests if the Date constructor throws an exception for invalid dates
    @Test
    public void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Date(30, 2, 2001)); 
    }

    // Tests the isLeapYear() method for different years
    @Test
    public void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020)); 
        assertFalse(Date.isLeapYear(2019)); 
        assertTrue(Date.isLeapYear(2000));  
        assertFalse(Date.isLeapYear(1900)); 
        assertTrue(Date.isLeapYear(1996)); 
        assertFalse(Date.isLeapYear(2100)); 

    }

    // Tests the previousDate() method for month change
    @Test
    public void testPreviousDateMonth() {
        Date date = new Date(1, 5,2024);
        Date previous = date.previousDate();
        assertEquals(new Date(30, 4, 2024), previous); 
    }

    // Tests the previousDate() method for day change
    @Test
    public void testPreviousDateDay() {
        Date date = new Date(10, 5, 2024);
        Date previous = date.previousDate();
        assertEquals(new Date(9, 5, 2024), previous);  
    }

    // Tests the previousDate() method for year change
    @Test
    public void testPreviousDateYear() {
        Date date = new Date(1, 1, 2024);
        Date previous = date.previousDate();
        assertEquals(new Date(31, 12, 2023), previous); 
    }

    // Tests the previousDate() method for a leap year date
    @Test
    public void testPreviousDateLeapYear() {
        Date date = new Date(1, 3, 2020); 
        Date previous = date.previousDate();
        assertEquals(new Date(29, 2, 2020), previous); 
    }

    // Tests the nextDate() method for year change
    @Test
    public void testNextDateYear() {
        Date date = new Date(31, 12, 2023);
        Date next = date.nextDate();
        assertEquals(new Date(1, 1, 2024), next); 
    }
    
    // Tests the nextDate() method for a leap year date
    @Test
    public void testNextDateLeapYear() {
        Date date = new Date(28, 2, 2020); 
        Date next = date.nextDate();
        assertEquals(new Date(29, 2, 2020), next); 
    }
    
    // Tests the nextDate() method for month change
    @Test
    public void testNextDateMonth() {
        Date date = new Date(31, 5, 2021);
        Date next = date.nextDate();
        assertEquals(new Date(1, 6, 2021), next); 
    }

    // Tests the compareTo() method for null input
    @Test
    public void testCompareTo_Null() {
        Date date = new Date(10, 5, 2020);
        assertThrows(NullPointerException.class, () -> {
            date.compareTo(null); 
        });
    }

    // Tests the compareTo() method for a date that is earlier
    @Test
    public void testCompareTo1() {
        Date date1 = new Date(1, 1, 2023);
        Date date2 = new Date(1, 1, 2024);
        assertTrue(date1.compareTo(date2) < 0); 
    }

    
    // Tests the compareTo() method for a date that is later
    @Test
    public void testCompareTo2() {
        Date date1 = new Date(10, 10, 2024);
        Date date2 = new Date(10, 9, 2024);
        assertTrue(date1.compareTo(date2) > 0); 
    }

    // Tests the compareTo() method for a date that is later on the same day
    @Test
    public void testCompareTo3() {
        Date date1 = new Date(10, 10, 2024);
        Date date2 = new Date(9, 10, 2024);
        assertTrue(date1.compareTo(date2) > 0); 
    }
    
    // Tests the compareTo() method for two equal dates
    @Test
    public void testCompareTo_Equal() {
        Date date1 = new Date(10, 10, 2024);
        Date date2 = new Date(10, 10, 2024);
        assertEquals(0, date1.compareTo(date2)); 
    }

}
