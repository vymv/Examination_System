package com.system.controller.converter;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomDateConverterTest {

    @Test
    public void convert() {
        CustomDateConverter customDateConverter = new CustomDateConverter();
        customDateConverter.convert("2021-12-12");
    }
}