package edu.uco.ralhamami;




public class BookItem extends Book{
    private int quantity;
    private double subTotal;

    public BookItem() {}
    public BookItem(double subTotal,int quantity, String title, String author, double price) {
        super(title, author, price);
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        this.subTotal = quantity * getPrice();
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    

    

    public void reduceQ() {
        quantity--;
    }
    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
