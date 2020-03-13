package com.daxx.abstracts;

public abstract class MyListJ<A> {
    public abstract A head();
    public abstract MyListJ<A> tail();
    public abstract Boolean isEmpty();
    public abstract MyListJ<A> add(A value);

    public abstract MyListJ<A> filter(MyPredicate<A> transformer);
}
