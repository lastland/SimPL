/* Generated By:JJTree: Do not edit this line. ASTEq.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.liyaos.simpl.parser;

public
class ASTEq extends SimPLNode {
  public ASTEq(int id) {
    super(id);
  }

  public ASTEq(SimPLParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SimPLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=71049298ed4f420f8cb5eed361222254 (do not edit this line) */