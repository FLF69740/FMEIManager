package com.example.fmeimanager;

import com.example.fmeimanager.controllers.navigationPackage1.processusTheme.BusinnessProcessusTheme;
import com.example.fmeimanager.models.Processus;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private Processus getFirstProcessus(){ return new Processus("name", 1, 5);}
    private Processus getSecondProcessus(){ return new Processus("name", 1, 3);}
    private Processus getThirdProcessus(){ return new Processus("name", 1, 4);}
    private Processus getFourProcessus(){ return new Processus("name", 1, 2);}
    private Processus getFiveProcessus(){ return new Processus("name", 1, 1);}

    private List<Processus> getProcessusList(){
        List<Processus> processusList = new ArrayList<>();
        processusList.add(getFirstProcessus());
        processusList.add(getSecondProcessus());
        processusList.add(getThirdProcessus());
        processusList.add(getFourProcessus());
        processusList.add(getFiveProcessus());
        return processusList;
    }

    @Test
    public void testBubbleSortingProcessus(){
        List<Processus> processusList = getProcessusList();
        assertEquals(5, processusList.get(0).getStep());
        assertEquals(3, processusList.get(1).getStep());
        assertEquals(4, processusList.get(2).getStep());
        assertEquals(2, processusList.get(3).getStep());
        assertEquals(1, processusList.get(4).getStep());

        List<Processus> processusList2 = BusinnessProcessusTheme.getProcessusByStepLadder(processusList);
        assertEquals(1, processusList2.get(0).getStep());
        assertEquals(2, processusList2.get(1).getStep());
        assertEquals(3, processusList2.get(2).getStep());
        assertEquals(4, processusList2.get(3).getStep());
        assertEquals(5, processusList2.get(4).getStep());

    }




}