/* Generated By:JJTree: Do not edit this line. ASTbracket.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.liyaos.simpl.parser;

public
class ASTbracket extends SimpleNode {
  public ASTbracket(int id) {
    super(id);
  }

  public ASTbracket(SimPL p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SimPLVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=eb590c4c2ecd6416eaf521c5e000e4b0 (do not edit this line) */
