package com.example.traingleApp.services;

import org.springframework.stereotype.Service;

@Service
public class TraingleService {

    public enum TYPE {
        SCALENE, ISOSCELES, EQUILATERAL, NOT_TRAINGLE;
    }

    public TYPE getType(Integer a, Integer b, Integer c) {
        if(isTraingle(a, b, c)) {
            if (a.equals(b) && b.equals(c)) {
                System.out.println(TYPE.EQUILATERAL);
                return TYPE.EQUILATERAL;
            } else if (a.equals(b) || b.equals(c) || a.equals(c)) {
                System.out.println(TYPE.ISOSCELES);
                return TYPE.ISOSCELES;
            } else {
                System.out.println(TYPE.SCALENE);
                return TYPE.SCALENE;
            }
        } else {
            return TYPE.NOT_TRAINGLE;
        }
    }

    public boolean isTraingle(Integer a, Integer b, Integer c) {
        if(a > 0 && b > 0 && c > 0) {
            if ((a + b > c) && (b + c > a) && (a + c > b)) {
                return true;
            }
        }
        return false;
    }

}
