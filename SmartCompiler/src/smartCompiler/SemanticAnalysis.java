package smartCompiler;
import java.util.*;

public class SemanticAnalysis {
    private Stack<String> symbolStack;
    private Stack<String> typeStack;
    private Map<String, String> symbolTable;

    public SemanticAnalysis() {
        symbolStack = new Stack<>();
        typeStack = new Stack<>();
        symbolTable = new HashMap<>();
    }

    public boolean insertSymbol(String symbol, String type) {
        if (symbolTable.containsKey(symbol)) {
            System.out.println("Semantic Error: Symbol '" + symbol + "' already declared.");
            return false;
        }

        symbolTable.put(symbol, type);
        return true;
    }

    public String getType(String symbol) {
        return symbolTable.get(symbol);
    }

    public void pushSymbol(String symbol) {
        symbolStack.push(symbol);
    }

    public void pushType(String type) {
        typeStack.push(type);
    }

    public String popSymbol() {
        return symbolStack.pop();
    }

    public String popType() {
        return typeStack.pop();
    }

    public boolean isStackEmpty() {
        return symbolStack.isEmpty();
    }

    public boolean checkTypes(String operator, String operand1, String operand2) {
        String type1 = getType(operand1);
        String type2 = getType(operand2);

        if (type1 == null || type2 == null) {
            System.out.println("Semantic Error: Invalid operands '" + operand1 + "' and/or '" + operand2 + "'.");
            return false;
        }

        if (!type1.equals(type2)) {
            System.out.println("Semantic Error: Incompatible types for operator '" + operator + "'.");
            return false;
        }

        return true;
    }
}
