/* Generated By:JJTree: Do not edit this line. ASTLet.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.liyaos.simpl.parser;

public
class ASTLet extends SimPLNode {
  public ASTLet(int id) {
    super(id);
  }

  public ASTLet(SimPLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SimPLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=7233cf906e3eaadc0bd8c30fb2740ae3 (do not edit this line) */
