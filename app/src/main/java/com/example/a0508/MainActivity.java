package com.example.a0508;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText textView;
    private double firstNumber = 0.0;
    private String operation = "";
    private boolean isNewNumber = true;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        setupNumberButtons();
        setupOperationButtons();
        setupFunctionButtons();
    }

    private void setupNumberButtons() {
        int[] numberButtons = {
                R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four,
                R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine
        };

        for (int id : numberButtons) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                Button btn = (Button) v;
                onNumberClick(btn.getText().toString());
            });
        }

        // 소수점 버튼
        Button dotButton = findViewById(R.id.dot);
        dotButton.setOnClickListener(v -> onDotClick());
    }

    private void setupOperationButtons() {
        Button plusButton = findViewById(R.id.plus);
        Button minusButton = findViewById(R.id.minus);
        Button multipleButton = findViewById(R.id.multiple);
        Button divisionButton = findViewById(R.id.division);
        Button resultButton = findViewById(R.id.result);

        plusButton.setOnClickListener(v -> onOperationClick("+"));
        minusButton.setOnClickListener(v -> onOperationClick("−"));
        multipleButton.setOnClickListener(v -> onOperationClick("×"));
        divisionButton.setOnClickListener(v -> onOperationClick("÷"));
        resultButton.setOnClickListener(v -> onEqualsClick());
    }

    private void setupFunctionButtons() {
        Button resetButton = findViewById(R.id.reset);
        Button plmaButton = findViewById(R.id.plma);
        Button percentButton = findViewById(R.id.persent);

        resetButton.setOnClickListener(v -> onResetClick());
        plmaButton.setOnClickListener(v -> onPlusMinusClick());
        percentButton.setOnClickListener(v -> onPercentClick());
    }

    private void onNumberClick(String number) {
        if (isNewNumber) {
            textView.setText(number);
            isNewNumber = false;
        } else {
            String currentText = textView.getText().toString();
            if (currentText.length() < 12) {  // 최대 12자리 제한
                textView.append(number);
            }
        }
    }

    private void onDotClick() {
        String currentText = textView.getText().toString();
        if (!currentText.contains(".")) {
            if (isNewNumber) {
                textView.setText("0.");
                isNewNumber = false;
            } else {
                textView.append(".");
            }
        }
    }

    private void onOperationClick(String op) {
        firstNumber = Double.parseDouble(textView.getText().toString());
        operation = op;
        isNewNumber = true;
    }

    private void onEqualsClick() {
        if (operation.isEmpty()) return;

        double secondNumber = Double.parseDouble(textView.getText().toString());
        Object result;

        switch (operation) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "−":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                result = secondNumber != 0.0 ? firstNumber / secondNumber : "Error";
                break;
            default:
                return;
        }

        displayResult(result);
        operation = "";
        isNewNumber = true;
    }

    private void onResetClick() {
        textView.setText("0");
        firstNumber = 0.0;
        operation = "";
        isNewNumber = true;
    }

    private void onPlusMinusClick() {
        String currentText = textView.getText().toString();
        if (!currentText.equals("0")) {
            if (currentText.startsWith("-")) {
                textView.setText(currentText.substring(1));
            } else {
                textView.setText("-" + currentText);
            }
        }
    }

    private void onPercentClick() {
        double currentNumber = Double.parseDouble(textView.getText().toString());
        double result = currentNumber / 100;
        displayResult(result);
    }

    private void displayResult(Object result) {
        if (result instanceof Double) {
            textView.setText(decimalFormat.format(result));
        } else {
            textView.setText(result.toString());
        }
    }
}