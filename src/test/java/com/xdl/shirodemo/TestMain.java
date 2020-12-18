package com.xdl.shirodemo;

public class TestMain {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("123").append(",").append("345").append(",");
        System.out.println(builder.delete(builder.lastIndexOf(","),builder.lastIndexOf(",")+1));
    }
}
