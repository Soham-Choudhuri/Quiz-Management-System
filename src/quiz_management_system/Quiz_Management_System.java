package quiz_management_system;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author soham
 */
public class Quiz_Management_System {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ProcessBuilder compileProcessBuilder = new ProcessBuilder("javac", "index.java");
            Process compileProcess = compileProcessBuilder.start();
            compileProcess.waitFor();

            if (compileProcess.exitValue() != 0) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
                String line;
                while ((line = errorReader.readLine()) != null) {
                    System.err.println(line);
                }
                return;
            }

            ProcessBuilder runProcessBuilder = new ProcessBuilder("java", "index");
            Process runProcess = runProcessBuilder.start();
            runProcess.waitFor();

            if (runProcess.exitValue() != 0) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                String line;
                while ((line = errorReader.readLine()) != null) {
                    System.err.println(line);
                }
                return;
            }

            BufferedReader outputReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            String line;
            while ((line = outputReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
