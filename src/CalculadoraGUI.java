import javax.swing.*;

public class CalculadoraGUI {
    public static void main(String[] args) {
        // Criando o JFrame
        JFrame frame = new JFrame("Calculadora");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Criando componentes de interface
        JTextField display = new JTextField();
        display.setBounds(10, 10, 260, 40);
        frame.add(display);

        // Criando os botões
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btn0 = new JButton("0");
        JButton btnAdd = new JButton("+");
        JButton btnSub = new JButton("-");
        JButton btnMul = new JButton("*");
        JButton btnDiv = new JButton("/");
        JButton btnEq = new JButton("=");
        JButton btnClear = new JButton("C");

        // Definindo a posição dos botões
        btn1.setBounds(10, 60, 50, 50);
        btn2.setBounds(70, 60, 50, 50);
        btn3.setBounds(130, 60, 50, 50);
        btn4.setBounds(10, 120, 50, 50);
        btn5.setBounds(70, 120, 50, 50);
        btn6.setBounds(130, 120, 50, 50);
        btn7.setBounds(10, 180, 50, 50);
        btn8.setBounds(70, 180, 50, 50);
        btn9.setBounds(130, 180, 50, 50);
        btn0.setBounds(10, 240, 110, 50);
        btnAdd.setBounds(190, 60, 50, 50);
        btnSub.setBounds(190, 120, 50, 50);
        btnMul.setBounds(190, 180, 50, 50);
        btnDiv.setBounds(190, 240, 50, 50);
        btnEq.setBounds(130, 240, 50, 50);
        btnClear.setBounds(10, 300, 230, 50);

        // Adicionando os botões ao JFrame
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(btn4);
        frame.add(btn5);
        frame.add(btn6);
        frame.add(btn7);
        frame.add(btn8);
        frame.add(btn9);
        frame.add(btn0);
        frame.add(btnAdd);
        frame.add(btnSub);
        frame.add(btnMul);
        frame.add(btnDiv);
        frame.add(btnEq);
        frame.add(btnClear);

        // Ação dos botões
        btn1.addActionListener(e -> display.setText(display.getText() + "1"));
        btn2.addActionListener(e -> display.setText(display.getText() + "2"));
        btn3.addActionListener(e -> display.setText(display.getText() + "3"));
        btn4.addActionListener(e -> display.setText(display.getText() + "4"));
        btn5.addActionListener(e -> display.setText(display.getText() + "5"));
        btn6.addActionListener(e -> display.setText(display.getText() + "6"));
        btn7.addActionListener(e -> display.setText(display.getText() + "7"));
        btn8.addActionListener(e -> display.setText(display.getText() + "8"));
        btn9.addActionListener(e -> display.setText(display.getText() + "9"));
        btn0.addActionListener(e -> display.setText(display.getText() + "0"));
        btnAdd.addActionListener(e -> display.setText(display.getText() + "+"));
        btnSub.addActionListener(e -> display.setText(display.getText() + "-"));
        btnMul.addActionListener(e -> display.setText(display.getText() + "*"));
        btnDiv.addActionListener(e -> display.setText(display.getText() + "/"));
        btnClear.addActionListener(e -> display.setText(""));
        btnEq.addActionListener(e -> {
            try {
                String expression = display.getText().replaceAll("\\s", "");
                double result = evaluate(expression);
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Erro");
            }
        });

        // Exibir o JFrame
        frame.setVisible(true);
    }

    //  Avalia a expressão
    private static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        // Pilhas para números e operadores
        java.util.Stack<Double> values = new java.util.Stack<>();
        java.util.Stack<Character> ops = new java.util.Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // Ignorar espaços
            if (tokens[i] == ' ')
                continue;

            // Verificar se é um número
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuilder sbuf = new StringBuilder();
                // Pode ter mais de um dígito
                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9'))
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
                i--; // Corrige o índice
            }
            // Verificar se é um operador
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }

        // Aplicar o operador restante
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Resultado final
        return values.pop();
    }

    // Verifica a precedência dos operadores
    public static boolean hasPrecedence(char op1, char op2) {
        return (op2 != '+' && op2 != '-') || (op1 != '*' && op1 != '/');
    }

    // Aplica o operador
    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Não pode dividir por zero");
                return a / b;
        }
        return 0;
    }
}
