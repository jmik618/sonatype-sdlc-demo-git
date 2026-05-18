package com.demo;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testMessage() {
        assertEquals("Sonatype SDLC demo application", App.message());
    }
}
