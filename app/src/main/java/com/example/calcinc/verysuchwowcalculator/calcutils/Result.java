package com.example.calcinc.verysuchwowcalculator.calcutils;

public class Result {

    private enum Type {
        STRING, DOUBLE
    }

    private Type type;
    private String resultS;
    private double resultD;

    Result(double resultD) {
        this.type = Type.DOUBLE;
        this.resultS = "";
        this.resultD = resultD;
    }

    Result(String resultS) {
        this.type = Type.STRING;
        this.resultS = resultS;
        this.resultD = 0;
    }

    @Override
    public String toString() {
        if (type.equals(Type.STRING)) return resultS;
        else if (resultD == 42) return "Смысл жизнь и все такое";
        return String.valueOf(resultD);
    }
}
