package net.redpandasec.labs.Classes;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.io.Serializable;

public class Evil2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cmd;

    public Evil2(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String toString() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(cmd);
        return this.cmd + " Results in " + exp.getValue();
    }

}
