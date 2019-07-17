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
        assertEquals("document_date", parser.processText("02.14.2019"));
    }

    @Test
    public void numeralFormatWithTextOk() {
        assertEquals("дата document_date", parser.processText("дата 02.14.2019"));
    }

    @Test
    public void numeralFormatWithTextNotOk() {
        assertNotEquals("document_date", parser.processText("дата 02.14.2019"));
    }

    @Test
    public void stringFormatOk() {
        assertEquals("document_date", parser.processText("24 февраля 2020"));
    }

    @Test
    public void stringFormatWithGDotOk() {
        assertEquals("document_date", parser.processText("24 февраля 2020 г."));
    }

    @Test
    public void stringFormatWithGodaOk() {
        assertEquals("document_date", parser.processText("24 февраля 2020 года"));
    }

    @Test
    public void NumberWithNOk() {
        assertEquals("document_number", parser.processText("N 245-hg"));
    }

    @Test
    public void NumberWithNoOk() {
        assertEquals("document_number", parser.processText("№ 245-пп-п"));
    }
}