/* Generated By:JJTree: Do not edit this line. ASTInt.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.liyaos.simpl.parser;

public
class ASTInt extends SimpleNode {

  private int value;
  private boolean undef;

  public ASTInt(int id) {
    super(id);
  }

  public ASTInt(SimPLParser p, int id) {
    super(p, id);
  }

  public void setValue(String img) {
    if (!img.equals("undef")) {
        value = Integer.valueOf(img);
        undef = false;
    } else {
        undef = true;
    }
  }

  public int getValue() {
    return value;
  }

  public boolean isUndef() {
    return undef;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(SimPLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }


}
/* JavaCC - OriginalChecksum=172ef627958699184c9f1e215dd0fa5b (do not edit this line) */
