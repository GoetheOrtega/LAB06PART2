import java.util.Stack;
class Expression {
public static void main(String[] args) {
        String postfixExpression = "34+2";

        PostfixExpressionCalculator calculator = new PostfixExpressionCalculator();
        calculator.buildExpressionTree(postfixExpression);
        int result = calculator.evaluateExpression();

        System.out.println("Postfix Expression: " + postfixExpression);
        System.out.println("Result: " + result);
        }
        }
class BinaryTreeNode {
    char value;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(char value) {
        this.value = value;
    }

    boolean isOperator() {
        return left != null && right != null;
    }
}

class PostfixExpressionCalculator {
    private BinaryTreeNode root;

    public void buildExpressionTree(String postfixExpression) {
        Stack<BinaryTreeNode> stack = new Stack<>();

        for (char ch : postfixExpression.toCharArray()) {
            BinaryTreeNode node = new BinaryTreeNode(ch);

            if (isOperator(ch)) {
                node.right = stack.pop();
                node.left = stack.pop();
            }

            stack.push(node);
        }

        root = stack.pop();
    }

    public int evaluateExpression() {
        return evaluate(root);
    }

    private int evaluate(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        if (!node.isOperator()) {
            return Character.getNumericValue(node.value);
        }

        int leftValue = evaluate(node.left);
        int rightValue = evaluate(node.right);

        return performOperation(node.value, leftValue, rightValue);
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private int performOperation(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}




