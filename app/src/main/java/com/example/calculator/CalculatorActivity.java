package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView result;
    private String firstNumber = "";
    private String operator = "";
    private String secondNumber = "";
    private String recentResult = "";
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        result = findViewById(R.id.result);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_reduce).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_part).setOnClickListener(this);
        findViewById(R.id.btn_point).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_clean).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String inputText;
        if (view.getId() == R.id.btn_sqrt) {
            inputText = "√";
        } else {
            inputText = ((TextView) view).getText().toString();
        }
        if (view.getId() == R.id.btn_add || view.getId() == R.id.btn_reduce || view.getId() == R.id.btn_multiply || view.getId() == R.id.btn_divide) {
            operator = inputText;
            refreshText(showText + operator);
        } else if (view.getId() == R.id.btn_cancel) {
            if (recentResult.length() > 0 && Objects.equals(operator, "")) {
                clean();
            } else if (!Objects.equals(secondNumber, "")) {
                secondNumber = secondNumber.substring(0, secondNumber.length() - 1);
                showText = showText.substring(0, showText.length() - 1);
                refreshText(showText);
            } else if (!Objects.equals(operator, "")) {
                operator = "";
                showText = showText.substring(0, showText.length() - 1);
                refreshText(showText);
            } else if (!Objects.equals(firstNumber, "")) {
                firstNumber = firstNumber.substring(0, firstNumber.length() - 1);
                showText = showText.substring(0, showText.length() - 1);
                refreshText(showText);
            }
        } else if (view.getId() == R.id.btn_clean) {
            clean();
        } else if (view.getId() == R.id.btn_equal) {
            double calculateResult = 0;
            try {
                calculateResult = calculate();
            } catch (Exception e) {
                Toast.makeText(CalculatorActivity.this, "请输入正确的运算数", Toast.LENGTH_SHORT).show();
                return;
            }
            refreshOperator(String.valueOf(calculateResult));
            refreshText(showText + "=" + recentResult);
        } else if (view.getId() == R.id.btn_sqrt) {
            double calculateResult = Math.sqrt(Double.parseDouble(firstNumber));
            refreshOperator(String.valueOf(calculateResult));
            refreshText(showText + "√=" + recentResult);
        } else if (view.getId() == R.id.btn_part) {
            double calculateResult = 1.0 / Double.parseDouble(firstNumber);
            refreshOperator(String.valueOf(calculateResult));
            refreshText(showText + "/=" + recentResult);
        } else {
            if (recentResult.length() > 0 && Objects.equals(operator, "")) {
                clean();
            }
            if (Objects.equals(operator, "")) {
                firstNumber = firstNumber + inputText;
            } else {
                secondNumber = secondNumber + inputText;
            }
            if (showText.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showText + inputText);
            }
        }
    }

    private double calculate() throws Exception {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
            case "-":
                return Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
            case "×":
                return Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
            case "÷":
                return Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
        }
        throw new Exception("运算符错误");
    }

    private void refreshText(String text) {
        showText = text;
        result.setText(showText);
    }

    private void clean() {
        refreshText("");
        refreshOperator("");
    }

    private void refreshOperator(String newResult) {
        recentResult = newResult;
        firstNumber = recentResult;
        secondNumber = "";
        operator = "";
    }
}