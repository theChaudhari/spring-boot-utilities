package com.springboot.utilities.jsontopojo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;


@ExtendWith(MockitoExtension.class)
class JsonToPojoUtilityTest {

    @InjectMocks
    private JsonToPojoUtility utility;

    @Test
    void performOperation() throws IOException {
        utility.jsonToPojo();
    }
}