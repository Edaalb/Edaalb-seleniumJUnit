package day06_assertions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C01_ByeByeMainMethod {
    @After
    public void afterMethod(){
        System.out.println("After method is executed");
    }

    @Before
    public void beforemethod(){
        System.out.println("before method is executed");
    }

    @Test
    public void test01(){
        System.out.println("Test 01 is executed");
    }

    @Test
    public void test02(){
        System.out.println("Test 02 is executed");
    }

    @Test
    public void test03(){
        System.out.println("Test 03 is executed");
    }
}
