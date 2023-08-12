package com.atguigu.maven;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testApp(){
        App app = new App();
        app.showMessage();
    }
}
