/* Generated By:JJTree: Do not edit this line. ASTPlusMinus.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.liyaos.simpl.parser;

public
class ASTPlusMinus extends SimpleNode {
  public ASTPlusMinus(int id) {
    super(id);
  }

  public ASTPlusMinus(SimPL p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SimPLVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=90c58e96151189a40e38670b7edd68a3 (do not edit this line) */
