package com.liyaos.simpl.shell;

import com.liyaos.simpl.parser.ParseException;
import com.liyaos.simpl.types.TypeErrorException;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 下午6:32
 */
public class InteractiveShell implements Interpretable {
    public void run(String[] args) {
        while (true) {
            boolean firstLine = true;
            String line = "";
            String program = "";
            Scanner scanner = new Scanner(System.in);
            while (!line.contains("$")) {
                if (firstLine) {
                    System.out.print("SimPL>");
                    firstLine = false;
                } else {
                    System.out.print("      ");
                }
                line = scanner.nextLine();
                program += "\n" + line;
            }
            try {
                program = program.substring(0, program.indexOf("$"));
                String output = Interpreter.run(new ByteArrayInputStream(program.getBytes()));
                System.out.println(output);
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
            } catch (TypeErrorException e) {
                System.out.println("Type Error!");
            } catch (RuntimeException e) {
                System.out.println("Runtime Error!");
            }
        }
    }
}
