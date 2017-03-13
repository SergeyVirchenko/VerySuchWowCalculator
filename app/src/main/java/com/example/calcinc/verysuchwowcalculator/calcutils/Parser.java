package com.example.calcinc.verysuchwowcalculator.calcutils;


import com.example.calcinc.verysuchwowcalculator.BuildConfig;

public class Parser {

    private String str;
    private String origStr;
    private boolean longMulti = false;
    private boolean crashOnFirstLetter = false;
    private boolean roundFinalResult = false;

    private int pos = -1, ch, numbersCount;
    private boolean crashOnMoreThan10numbers = false;

    private Parser(String str) {
        this.str = str;
        origStr = str;
    }

    public static Result parse(String str) {
        return new Parser(str).parse();
    }

    private Result parse() {
        switch (BuildConfig.FLAVOR) {
            case "v1":
                removeScopes();
                removeMinus();
                longMulti = true;
            case "v2":
                crashOnMoreThan10numbers = true;
                crashOnFirstLetter = true;
                roundFinalResult = true;
            default:
                return parseResult();
        }
    }

    private void removeMinus() {
        str = str.trim().replace(" ", "");
        if (str.charAt(0) == "-".charAt(0)) {
            str = str.substring(1);
        }
    }

    private void removeScopes() {
        str = str.replace("(", "").replace(")", "");
    }

    private void nextChar() {
        ch = (++pos < str.length()) ? str.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private Result parseResult() {
        nextChar();
        double x;
        try {
            x = parseExpression();
        } catch (InvalidArgsException e) {
            return new Result(origStr.replace("+", "").replace("-", "").replace("*", "").replace("/", "").replace(" ", ""));
        }
        if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
        return new Result(roundFinalResult ? (int) x : x);
    }

    private double parseExpression() throws InvalidArgsException {
        double x = parseTerm();
        while (true) {
            if (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else return x;
        }
    }

    private double parseTerm() throws InvalidArgsException {
        double x = parseFactor();
        while (true) {
            if (eat('*')) {
                x *= parseFactor();
                if (longMulti) {
                    int z = 0;
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        z++;
                    }
                }
            }  // multiplication
            else if (eat('/')) x /= parseFactor(); // division
            else return x;
        }
    }

    private double parseFactor() throws InvalidArgsException {
        if (eat('+')) return parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(str.substring(startPos, this.pos));
            numbersCount++;
            if (numbersCount == 11 && crashOnMoreThan10numbers) {
                throw new RuntimeException("Too many zooz");
            }
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') nextChar();

            if (pos == 1 && crashOnFirstLetter) {
                throw new RuntimeException();
            }

            throw new InvalidArgsException();
        } else {
            throw new RuntimeException("Unexpected: " + (char) ch);
        }

        return x;
    }
}
