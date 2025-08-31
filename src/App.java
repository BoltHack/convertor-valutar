import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JLabel messageLabel;
    private JButton rateRoubleDollarButton;
    private JButton rateDollarRoubleButton;

    private JTextField currency;
    private JButton converterButton;
    private JButton backButton;

    private float dollar = 0.013f;

    public App() {
        setLayout(new BorderLayout());
        setSize(400, 200);
        getContentPane().setBackground(new Color(0x16171d));
        setLocationRelativeTo(null);
        setTitle("Конвертер валют");

        messageLabel = new JLabel();
        messageLabel.setForeground(new Color(0xffffff));
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        currency = new JTextField();
        currency.setBackground(new Color(0x242632));
        currency.setBorder(new EmptyBorder(10, 10, 10, 10));
        currency.setHorizontalAlignment(SwingConstants.CENTER);
        currency.setForeground(new Color(0xffffff));
        currency.setCaretColor(new Color(0xffffff));

        rateRoubleDollarButton = new JButton("Рубль к доллару");
        rateRoubleDollarButton.setContentAreaFilled(false);
        rateRoubleDollarButton.setFocusPainted(false);
        rateRoubleDollarButton.setBackground(new Color(0x16171d));
        rateRoubleDollarButton.setForeground(new Color(0xffffff));
        rateRoubleDollarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rateDollarRoubleButton = new JButton("Доллар к рублю");
        rateDollarRoubleButton.setContentAreaFilled(false);
        rateDollarRoubleButton.setFocusPainted(false);
        rateDollarRoubleButton.setBackground(new Color(0x16171d));
        rateDollarRoubleButton.setForeground(new Color(0xffffff));
        rateDollarRoubleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        converterButton = new JButton("Сконвертировать");
        converterButton.setContentAreaFilled(false);
        converterButton.setFocusPainted(false);
        converterButton.setBackground(new Color(0x16171d));
        converterButton.setForeground(new Color(0xffffff));
        converterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        backButton = new JButton("Назад");
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(0x16171d));
        backButton.setForeground(new Color(0xffffff));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(rateRoubleDollarButton, BorderLayout.SOUTH);
        add(rateDollarRoubleButton, BorderLayout.NORTH);

        rateRoubleDollarButton.addActionListener(e ->
                Convertor("rouble-dollar")
        );
        rateDollarRoubleButton.addActionListener(e ->
                Convertor("dollar-rouble")
        );

        backButton.addActionListener(e -> {
            add(rateRoubleDollarButton, BorderLayout.SOUTH);
            add(rateDollarRoubleButton, BorderLayout.NORTH);
            remove(converterButton);
            remove(backButton);

            remove(messageLabel);
            remove(currency);

            revalidate();
            repaint();
        });
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
                messageLabel.setEnabled(true);

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
