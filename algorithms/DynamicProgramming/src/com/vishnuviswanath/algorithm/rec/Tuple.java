package com.vishnuviswanath.algorithm.rec;

/**
 * Created by vviswanath on 4/6/18.
 */

public class Tuple<T1, T2> {
    public T1 t1;
    public T2 t2;

    public Tuple(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean equals(Object other) {
        Tuple otherTuple = ((Tuple) other);
        return otherTuple.t1.equals(this.t1) && otherTuple.t2.equals(this.t2);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + t1.hashCode();
        hash = 31 * hash + t2.hashCode();
        return hash;
    }
}