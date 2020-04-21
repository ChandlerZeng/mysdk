package com.example;

/**
 * @author zcq
 * @date 2018/2/13.
 */

public class ExamTest {
    public static void main(String[] args) {
        Invoice t = new Invoice();
        Invoice ticket;
        ticket = new HeadDecorator(new FootDecorator(t));//（4）;
        ticket.printInvoice();
        System.out.println("------------------");
        ticket = new HeadDecorator(new FootDecorator(null));//（5）;
        ticket.printInvoice();
    }
}

class Invoice {
    public void printInvoice() {
        System.out.println("This is the content of the invoice!");
    }
}

class Decorator extends Invoice {
    protected Invoice ticket;

    public Decorator(Invoice t) {
        ticket = t;
    }

    @Override
    public void printInvoice() {
        if (ticket != null) {
            //（1）;
            ticket.printInvoice();
        }
    }
}

class HeadDecorator extends Decorator {
    public HeadDecorator(Invoice t) {
        super(t);
    }

    @Override
    public void printInvoice() {
        System.out.println("This is the header of the invoice! ");
        //（2）;
        super.printInvoice();
    }
}

class FootDecorator extends Decorator {
    public FootDecorator(Invoice t) {
        super(t);
    }
    @Override
    public void printInvoice() {
        //（3）;
        super.printInvoice();
        System.out.println("This is the footnote of the invoice! ");
    }
}
