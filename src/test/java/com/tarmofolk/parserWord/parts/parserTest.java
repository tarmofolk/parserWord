package com.tarmofolk.parserWord.parts;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class parserTest extends Assert {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(parserTest.class);
        System.out.println("run tests: " + result.getRunCount());
    }


    @Test
    public void numeralFormatOk() {
        String actual = parser.processText("02.11.2019");
        String expected = "document_date";
        assertEquals(expected, actual);
    }

    @Test
    public void numeralFormatWithWrongDateNotOk() {
        String actual = parser.processText("02.14.2019");
        String unexpected = "document_date";
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void numeralFormatWithTextOk() {
        String actual = parser.processText("дата ввода - 02.09.1919, дата вывода - 06.11.2011");
        String expected = "дата ввода - document_date, дата вывода - document_date";
        assertEquals(expected, actual);
    }

    @Test
    public void stringFormatOk() {
        String actual = parser.processText("24 февраля 2020");
        String expected = "document_date";
        assertEquals(expected, actual);
    }

    @Test
    public void stringFormatWithGDotOk() {
        String actual = parser.processText("22 мая 2020 г.");
        String expected = "document_date";
        assertEquals(expected, actual);
    }

    @Test
    public void stringFormatWithGodaOk() {
        String actual = parser.processText("14 июля 2020 года");
        String expected = "document_date";
        assertEquals(expected, actual);
    }

    @Test
    public void stringFormatWithTextOk() {
        String actual = parser.processText("14 июля 2020 года вынесено поостановление, 13 августа 2033 г. - выводы");
        String expected = "document_date вынесено поостановление, document_date - выводы";
        assertEquals(expected, actual);
    }

    @Test
    public void NumberWithNOk() {
        String actual = parser.processText("N 245-hg");
        String expected = "document_number";
        assertEquals(expected, actual);
    }

    @Test
    public void NumberWithNoOk() {
        String actual = parser.processText("№ 245-пп-п");
        String expected = "document_number";
        assertEquals(expected, actual);
    }

    @Test
    public void MixedTest() {
        String actual = parser.processText("14 июля 2020 года вынесено поостановление № 1458-87/З, 13.01.2033 был принят закон N KKK-14*12, ОК");
        String expected = "document_date вынесено поостановление document_number, document_date был принят закон document_number, ОК";
        assertEquals(expected, actual);
    }
}


