package com.example.pos_machine;

public class Model {
    public String code;
    public String _item;
    double sum =0.0;
    public double getTotal(double total) {

        sum=sum+total;
        return total;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double total;

    public double tax;

    public double unit;
    public double unit_price;
    public int quantity;

    public double cost;

    public Model(String code, String _item, int quantity, double unit_price,double unit ) {
        this.code = code;
        this._item = _item;
        this.unit = unit;
        this.unit_price = unit_price;
        this.quantity = quantity;

    }


    public String getCode() {
        return code;
    }

    public String get_item() {
        return _item;
    }
    public double getCost() {
      cost = Model.this.unit_price*Model.this.quantity;
      this.getTotal(cost);
        return cost;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public int getQuantity() {
        return quantity;
    }
}