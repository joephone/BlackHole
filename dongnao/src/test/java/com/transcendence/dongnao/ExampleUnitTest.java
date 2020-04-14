package com.transcendence.dongnao;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);

        sort(arrayN, arrayN.length);
    }
    int []arrayN = {1,23,5,2,7,8,9,4,3};

    void sort(int [] arrayN,int length){
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length-1; j++) {
                if(arrayN[j-1]>arrayN[j]){
                    swap(arrayN[j-1],arrayN[j]);
                }
            }
        }
    }

    private void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

}