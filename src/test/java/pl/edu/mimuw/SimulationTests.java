package pl.edu.mimuw;

import org.junit.jupiter.api.Test;

import java.awt.desktop.QuitEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimulationTests {
    int n = 10;

    @Test
    public void zeroTramsTest(){
        for(int i = 0; i < 1; i++) {
            String simulatedInput =
                    "3\n"   // simulationDays
                    + "10\n"  // stopLimit
                    + "3\n"  // numberOfStops
                    + "Banacha\nKrakowskie\nCentrum\n"  // stopsNames
                    + "15\n"  // numberOfPassengers
                    + "5\n"   // tramLimit
                    + "1\n"   // getTracks
                    + "0\n3\nBanacha\n3\nCentrum\n2\nKrakowskie\n10\n";

            InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

            InputStream originalIn = System.in;
            System.setIn(simulatedInputStream);

            Simulation simulationClass = new Simulation();
            simulationClass.readData();
            simulationClass.playSimulation();

            System.setIn(originalIn);
        }
    }
    @Test
    public void assertTest(){
        EventQueue a = new EventQueue();
        assertThrows(AssertionError.class, () -> {
            a.poll();
        });
    }
    @Test
    public void minimalTest(){
        String simulatedInput =
                "0\n"   // simulationDays
                + "0\n"  // stopLimit
                + "0\n"  // numberOfStops
                + "0\n"  // stopsNames
                + "0\n"  // numberOfPassengers
                + "0\n";   // tramLimit
        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        InputStream originalIn = System.in;
        System.setIn(simulatedInputStream);

        Simulation simulationClass = new Simulation();
        simulationClass.readData();
        simulationClass.playSimulation();

        System.setIn(originalIn);
    }
    @Test
    public void simpleTest1(){
        String simulatedInput =
                "1\n"   // simulationDays
                + "1\n"  // stopLimit
                + "1\n"  // numberOfStops
                + "Banacha\n"  // stopsNames
                + "1\n"  // numberOfPassengers
                + "1\n"   // tramLimit
                + "0\n";  // getTracks
        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        InputStream originalIn = System.in;
        System.setIn(simulatedInputStream);

        Simulation simulationClass = new Simulation();
        simulationClass.readData();
        simulationClass.playSimulation();

        System.setIn(originalIn);
    }

    @Test
    public void simpleTest2(){
        String simulatedInput =
                "2\n"   // simulationDays
                + "1\n"  // stopLimit
                + "2\n"  // numberOfStops
                + "A\nB\n"  // stopsNames
                + "1\n"  // numberOfPassengers
                + "1\n"   // tramLimit
                + "1\n"   // getTracks
                + "1\n2\nA\n20\nB\n10\n";  // Line 1: numberOfTrams, trackLength, stops and travel times

        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        InputStream originalIn = System.in;
        System.setIn(simulatedInputStream);

        Simulation simulationClass = new Simulation();
        simulationClass.readData();
        simulationClass.playSimulation();

        System.setIn(originalIn);
    }

    @Test
    public void simpleTest3(){
        String simulatedInput =
                "1\n"   // simulationDays
                + "1\n"  // stopLimit
                + "4\n"  // numberOfStops
                + "A\nB\nC\nD\n"  // stopsNames
                + "1\n"  // numberOfPassengers
                + "1\n"   // tramLimit
                + "1\n"   // getTracks
                + "2\n4\nA\n5\nB\n10\nC\n5\nD\n10\n";  //2 Trams, Line 1: A -> 5 -> B -> 10 -> C -> 5 -> D, LOOP: 10

        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        InputStream originalIn = System.in;
        System.setIn(simulatedInputStream);

        Simulation simulationClass = new Simulation();
        simulationClass.readData();
        simulationClass.playSimulation();

        System.setIn(originalIn);
    }
    @Test
    public void simpleTest4(){
        String simulatedInput =
                "1\n"   // simulationDays
                + "1\n"  // stopLimit
                + "2\n"  // numberOfStops
                + "A\nB\n"  // stopsNames
                + "2\n"  // numberOfPassengers
                + "1\n"   // tramLimit
                + "1\n"   // getTracks
                + "1\n2\nA\n20\nB\n10\n";

        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        InputStream originalIn = System.in;
        System.setIn(simulatedInputStream);

        Simulation simulationClass = new Simulation();
        simulationClass.readData();
        simulationClass.playSimulation();

        System.setIn(originalIn);
    }

    @Test
    public void ExampleTestWithOnePassenger(){
        String simulatedInput =
                "3\n"   // simulationDays
                + "10\n"  // stopLimit
                + "3\n"  // numberOfStops
                + "Banacha\nKrakowskie\nCentrum\n"  // stopsNames
                + "1\n"  // numberOfPassengers
                + "5\n"   // tramLimit
                + "1\n"   // getTracks
                + "2\n3\nBanacha\n3\nCentrum\n2\nKrakowskie\n10\n";

        InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        InputStream originalIn = System.in;
        System.setIn(simulatedInputStream);

        Simulation simulationClass = new Simulation();
        simulationClass.readData();
        simulationClass.playSimulation();

        System.setIn(originalIn);
    }

    @Test
    public void ExampleTest(){
        for(int i = 0; i < 1; i++) {
            String simulatedInput =
                    "3\n"   // simulationDays
                    + "10\n"  // stopLimit
                    + "3\n"  // numberOfStops
                    + "Banacha\nKrakowskie\nCentrum\n"  // stopsNames
                    + "15\n"  // numberOfPassengers
                    + "5\n"   // tramLimit
                    + "1\n"   // getTracks
                    + "2\n3\nBanacha\n3\nCentrum\n2\nKrakowskie\n10\n";

            InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

            InputStream originalIn = System.in;
            System.setIn(simulatedInputStream);

            Simulation simulationClass = new Simulation();
            simulationClass.readData();
            simulationClass.playSimulation();

            System.setIn(originalIn);
        }
    }
    @Test
    public void ExampleTestLimitOne(){
        for(int i = 0; i < 1; i++) {
            String simulatedInput =
                    "3\n"   // simulationDays
                    + "1\n"  // stopLimit
                    + "3\n"  // numberOfStops
                    + "Banacha\nKrakowskie\nCentrum\n"  // stopsNames
                    + "15\n"  // numberOfPassengers
                    + "5\n"   // tramLimit
                    + "1\n"   // getTracks
                    + "2 3 Banacha 3 Centrum 2 Krakowskie 10\n";

            InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

            InputStream originalIn = System.in;
            System.setIn(simulatedInputStream);

            Simulation simulationClass = new Simulation();
            simulationClass.readData();
            simulationClass.playSimulation();

            System.setIn(originalIn);
        }
    }
    @Test
    public void ExampleTestLimitOne3Tracks(){
        for(int i = 0; i < 1; i++) {
            String simulatedInput =
                    "10\n"   // simulationDays
                    + "30\n"  // stopLimit
                    + "3\n"  // numberOfStops
                    + "Banacha\nKrakowskie\nCentrum\n"  // stopsNames
                    + "25\n"  // numberOfPassengers
                    + "1\n"   // tramLimit
                    + "3\n"   // getTracks
                    + "2 3 Banacha 3 Centrum 2 Krakowskie 10\n"
                    + "2 3 Banacha 3 Centrum 2 Krakowskie 10\n"
                    + "2 3 Banacha 3 Centrum 2 Krakowskie 10\n";

            InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

            InputStream originalIn = System.in;
            System.setIn(simulatedInputStream);

            Simulation simulationClass = new Simulation();
            simulationClass.readData();
            simulationClass.playSimulation();

            System.setIn(originalIn);
        }
    }
    @Test
    public void LargerTest(){
        for(int i = 0; i < 1; i++) {
            String simulatedInput =
                    "10\n"   // simulationDays
                    + "30\n"  // stopLimit
                    + "20\n"  // numberOfStops
                    + "Banacha\nKrakowskie\nCentrum\nMokotow\nWilanow\nNowowiejska\nPolitechnika\nZoliborz\nBielany\nTargowek\nPraga\nGoclaw\nSaska\nGrochow\nWola\nOchota\nUrsynow\nBemowo\nRadość\nMarymont\n"  // stopsNames
                    + "100\n"  // numberOfPassengers
                    + "5\n"   // tramLimit
                    + "6\n"   // getTracks
                    +"3 7 Banacha 5 Centrum 2 Mokotow 2 Wilanow 3 Krakowskie 3 Centrum 5 Marymont 3\n"
                    +"4 7 Nowowiejska 3 Politechnika 4 Zoliborz 3 Bielany 2 Targowek 3 Bielany 3 Zoliborz 3\n"
                    +"3 6 Praga 4 Goclaw 5 Saska 4 Grochow 5 Praga 4 Goclaw 3\n"
                    +"2 7 Wola 5 Ochota 5 Ursynow 3 Bemowo 5 Ursynow 5 Ochota 5 Grochow 2\n"
                    +"3 7 Radość 4 Marymont 3 Targowek 3 Zoliborz 3 Radość 4 Marymont 3 Targowek 3\n"
                    +"3 7 Saska 1 Centrum 1 Wilanow 1 Nowowiejska 1 Saska 1 Centrum 1 Wilanow 3\n";

            InputStream simulatedInputStream = new ByteArrayInputStream(simulatedInput.getBytes());

            InputStream originalIn = System.in;
            System.setIn(simulatedInputStream);

            Simulation simulationClass = new Simulation();
            simulationClass.readData();
            simulationClass.playSimulation();

            System.setIn(originalIn);
        }
    }



}
