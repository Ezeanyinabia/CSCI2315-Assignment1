import java.util.Scanner;

public class State {
    private int[][] cities = new int[4][2]; 
    private double[] populations = new double[4]; 
    private int plantX = 1;
    private int plantY = 1;

    public int[][] getCities() { return cities; }
    public void setCities(int[][] cities) { this.cities = cities; }

    public double[] getPopulations() { return populations; }
    public void setPopulations(double[] populations) { this.populations = populations; }

    public int getPlantX() { return plantX; }
    public void setPlantX(int plantX) { this.plantX = plantX; }

    public int getPlantY() { return plantY; }
    public void setPlantY(int plantY) { this.plantY = plantY; }

    
    public void read_input(Scanner scanner) {
        for (int i = 0; i < 4; i++) {
            boolean valid = false;
            while (!valid) {
                System.out.print("Enter the x and y for City " + (i + 1) + " : ");
                cities[i][0] = scanner.nextInt();
                cities[i][1] = scanner.nextInt();

                if (cities[i][0] >= 1 && cities[i][0] <= 25 && cities[i][1] >= 1 && cities[i][1] <= 25) {
                    valid = true;
                } else {
                    System.out.println("Coordinates must be between 1 and 25. Please try again.");
                }
            }
            System.out.print("Enter the Population for City " + (i + 1) + ": ");
            populations[i] = scanner.nextDouble();
        }
        System.out.println("\n**********\n");
    }

    public void calc_plant() {
        double minAvgUnhappiness = Double.MAX_VALUE;
        double totalPopulation = 0;
        
        for (double pop : populations) {
            totalPopulation += pop;
        }

        for (int px = 1; px <= 25; px++) {
            for (int py = 1; py <= 25; py++) {
                double sumUnhappiness = 0;

                for (int i = 0; i < 4; i++) {
                    double distance = Math.sqrt(Math.pow(px - cities[i][0], 2) + Math.pow(py - cities[i][1], 2));

                    if (distance <= 2.0) {
                        sumUnhappiness += 1e9; 
                    } else {
                        sumUnhappiness += populations[i] / distance;
                    }
                }

                double avgUnhappiness = sumUnhappiness / totalPopulation;

                if (avgUnhappiness < minAvgUnhappiness) {
                    minAvgUnhappiness = avgUnhappiness;
                    plantX = px;
                    plantY = py;
                }
            }
        }

        System.out.println("Locate the Plant At: " + plantX + " " + plantY + "\n");
    }

    public void display_map() {
        System.out.println("\n MAP OF SCENARIO");
        System.out.println(" --------------- ");

        for (int y = 25; y >= 1; y--) {
            for (int x = 1; x <= 25; x++) {
                int cityIndex = -1;
                for (int i = 0; i < 4; i++) {
                    if (cities[i][0] == x && cities[i][1] == y) {
                        cityIndex = i + 1;
                        break;
                    }
                }

                if (cityIndex != -1) {
                    System.out.print("C" + cityIndex);
                } else if (plantX == x && plantY == y) {
                    System.out.print("PP");
                } else {
                    System.out.print("<>");
                }
            }
            System.out.println();
        }
    }
}
