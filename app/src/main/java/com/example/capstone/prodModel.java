package com.example.capstone;

public class prodModel {
    String Name,Quantity,Stocks,Utd;
    prodModel()
    {

    }


    public prodModel(String Name, String Quantity, String Stocks, String Utd) {
        this.Name = Name;
        this.Quantity = Quantity;
        this.Stocks = Stocks;
        this.Utd = Utd;
    }

    public String getName() {
        return Name;
    }

        public void setName(String Name) {
            this.Name = Name;
    }
    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;

    }
    public String getStocks() {
        return Stocks;
    }

    public void setStocks(String Stocks) {
        this.Stocks = Stocks;
    }



    public String getUtd() {
            return Utd;
        }

    public void setUtd(String Utd) {
        this.Utd = Utd;
    }
}
