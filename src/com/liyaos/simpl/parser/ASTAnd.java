/* Generated By:JJTree: Do not edit this line. ASTAnd.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.liyaos.simpl.parser;

public
class ASTAnd extends SimPLNode {
  public ASTAnd(int id) {
    super(id);
  }

  public ASTAnd(SimPLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SimPLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b25e89c06821c213bc827ae13c5cc576 (do not edit this line) */
