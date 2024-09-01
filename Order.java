package ASM2;

class Order {
    private int id;
    private String customerName;
    private String customerAddress;
    Queue<Book> bookCart;


    public Order(int id, String customerName, String customerAddress) {
        this.id = id;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.bookCart = new Queue<>();

    }

    public int getId() {

        return id;
    }

    public String getCustomerName() {

        return customerName;
    }

    public String getCustomerAddress() {

        return customerAddress;
    }

    @Override
    public String toString() {
        StringBuilder results = new StringBuilder();
        results.append("Order{")
                .append("id=").append(id)
                .append(", customerName='").append(customerName).append('\'')
                .append(", customerAddress='").append(customerAddress).append('\'')
                .append(", bookCart=").append(bookCart.toString());

                results.append('}');
        return results.toString();
    }
}
