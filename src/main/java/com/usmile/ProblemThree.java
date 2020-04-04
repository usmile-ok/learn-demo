package com.usmile;

import java.util.ArrayList;
import java.util.List;

public class ProblemThree {
}


class MyQueue<T> {



    private List<T> list = new ArrayList<>();


    public void push(T data) {
        this.list.add(data);
    }


    public T pop() {
        T t = this.list.get(this.list.size() - 1);

        list.remove(this.list.size() - 1);
        return t;
    }


    public T peek() {
        return this.list.get(this.list.size() - 1);
    }

}