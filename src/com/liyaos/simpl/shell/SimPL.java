package com.liyaos.simpl.shell;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 下午6:53
 */
public class SimPL {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please input the args!");
            printUsage();
            return;
        }
        Interpretable interpreter;
        if (args[0].equals("-s")) {
            interpreter = new InteractiveShell();
        } else if (args[0].equals("-f")) {
            interpreter = new FileInterpreter();
        } else {
            System.out.println("Unknown arguments!");
            System.out.println(args[0]);
            printUsage();
            return;
        }
        interpreter.run(Arrays.copyOfRange(args, 1, args.length));
    }

    private static void printUsage() {
        System.out.println("Example:");
        System.out.println("java -jar SimPL.jar -s\t\t\t(Start an interactive shell.)");
        System.out.println("java -jar SimPL.jar -f [FileName]\t(Use file as input)");
    }
}
