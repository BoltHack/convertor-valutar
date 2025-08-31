import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JTextField messageLabel;
    private JButton rateRoubleDollarButton;
    private JButton rateDollarRoubleButton;

    private JTextField currency;
    private JButton converterButton;
    private JButton backButton;

    private float dollar = 0.013f;

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBackground(new Color(0x16171d));
        button.setForeground(Color.white);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void showStartScreen() {
        getContentPane().removeAll();
        add(rateRoubleDollarButton, BorderLayout.SOUTH);
        add(rateDollarRoubleButton, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    public App() {
        setLayout(new BorderLayout());
        setSize(400, 200);
        getContentPane().setBackground(new Color(0x16171d));
        setLocationRelativeTo(null);
        setTitle("Конвертер валют");

        messageLabel = new JTextField();
        messageLabel.setForeground(new Color(0xffffff));
        messageLabel.setBackground(new Color(0x16171d));
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setEditable(false);

        currency = new JTextField();
        currency.setBackground(new Color(0x242632));
        currency.setBorder(new EmptyBorder(10, 10, 10, 10));
        currency.setHorizontalAlignment(SwingConstants.CENTER);
        currency.setForeground(new Color(0xffffff));
        currency.setCaretColor(new Color(0xffffff));

        rateRoubleDollarButton = createButton("Рубль к доллару");
        rateDollarRoubleButton = createButton("Доллар к рублю");
        converterButton = createButton("Сконвертировать");
        backButton = createButton("Назад");

        add(rateRoubleDollarButton, BorderLayout.SOUTH);
        add(rateDollarRoubleButton, BorderLayout.NORTH);

        rateRoubleDollarButton.addActionListener(e ->
                Convertor("rouble-dollar")
        );
        rateDollarRoubleButton.addActionListener(e ->
                Convertor("dollar-rouble")
        );

        backButton.addActionListener(e -> showStartScreen());
    }

    private void Convertor(String rate) {
        remove(rateRoubleDollarButton);
        remove(rateDollarRoubleButton);

        add(messageLabel, BorderLayout.NORTH);
        add(currency, BorderLayout.CENTER);
        add(converterButton, BorderLayout.SOUTH);

        currency.setEnabled(true);
        currency.grabFocus();

        revalidate();
        repaint();

        for (ActionListener al : converterButton.getActionListeners()) {
            converterButton.removeActionListener(al);
        }

        if (rate.equals("rouble-dollar")) {
            messageLabel.setText("Введите количетсов рублей");

            converterButton.addActionListener(e -> {
                Float value = Float.parseFloat(currency.getText());
                float res = value * dollar;

                messageLabel.setText(String.format("%.2f рублей сконвертированы в %.2f долларов", value, res));
                currency.setText("");
                currency.setEnabled(false);

                remove(converterButton);
                add(backButton, BorderLayout.SOUTH);

                revalidate();
                repaint();
            });
        } else {
            messageLabel.setText("Введите количетсов долларов");

            converterButton.addActionListener(e -> {
                Float value = Float.parseFloat(currency.getText());
                float res = value / dollar;

                messageLabel.setText(String.format("%.2f долларов сконвертированы в %.2f рублей", value, res));
                currency.setText("");
                currency.setEnabled(false);

                remove(converterButton);
                add(backButton, BorderLayout.SOUTH);

                revalidate();
                repaint();
            });
        }
        revalidate();
        repaint();
    }
}
