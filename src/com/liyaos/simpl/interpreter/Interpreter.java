package com.liyaos.simpl.interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.liyaos.simpl.parser.ASTProgram;
import com.liyaos.simpl.parser.ParseException;
import com.liyaos.simpl.parser.SimPLParser;
import com.liyaos.simpl.parser.SimPLNode;
import com.liyaos.simpl.types.TypeInferenceVisitor;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-8
 * Time: 下午5:53
 */
public class Interpreter {
    public static void main(String args[]) {
        SimPLParser parser = null;
        try {
            parser = new SimPLParser(new FileInputStream(new File("test_input.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        assert parser != null;
        ASTProgram node;
        try {
            node = parser.Start();
            TypeInferenceVisitor inferenceVisitor = new TypeInferenceVisitor();
            inferenceVisitor.visit(node, null);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println("Success!");
    }
}
