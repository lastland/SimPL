/* Generated By:JJTree: Do not edit this line. ASTBool.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.liyaos.simpl.parser;

public
class ASTBool extends SimpleNode {

  private boolean value;

  public ASTBool(int id) {
    super(id);
  }

  public ASTBool(SimPLParser p, int id) {
    super(p, id);
  }

  public void setValue(String img) {
    value = img.equals("true");
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SimPLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8b577428078767903802f6f47e385026 (do not edit this line) */
