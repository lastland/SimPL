package com.liyaos.simpl.shell;

import com.liyaos.simpl.parser.ParseException;
import com.liyaos.simpl.types.TypeErrorException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 下午7:02
 */
public class FileInterpreter implements Interpretable {

    @Override
    public void run(String[] args) {
        try {
            System.out.println(Interpreter.run(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        } catch (ParseException e) {
            System.out.println("Syntax Error!");
        } catch (TypeErrorException e) {
            System.out.println("Type Error!");
        } catch (RuntimeException e) {
            System.out.println("Runtime Error!");
        }
    }
}
