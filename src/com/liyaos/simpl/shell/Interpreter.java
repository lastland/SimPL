package com.liyaos.simpl.shell;

import com.liyaos.simpl.interpreter.InterpretVisitor;
import com.liyaos.simpl.interpreter.SimPLObject;
import com.liyaos.simpl.parser.ASTProgram;
import com.liyaos.simpl.parser.ParseException;
import com.liyaos.simpl.parser.SimPLParser;
import com.liyaos.simpl.types.NameList;
import com.liyaos.simpl.types.TypeCheckingVisitor;
import com.liyaos.simpl.types.TypeErrorException;
import com.liyaos.simpl.types.TypeInferenceVisitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 下午6:35
 */
public class Interpreter {
    public static String run(InputStream input) throws ParseException, TypeErrorException {
        // Parser
        SimPLParser parser =  new SimPLParser(input);
        ASTProgram node = parser.Start();

        // Type Inference
        TypeInferenceVisitor inferenceVisitor = new TypeInferenceVisitor();
        NameList nameList = (NameList) inferenceVisitor.visit(node, null);

        // Type Checking
        TypeCheckingVisitor checkingVisitor = new TypeCheckingVisitor(nameList);
        checkingVisitor.visit(node, null);

        // Interpreter
        InterpretVisitor interpretVisitor = new InterpretVisitor();
        SimPLObject ob = (SimPLObject) interpretVisitor.visit(node, null);

        return ob.toString();
    }
}
