package com.example.bankmenagmentsystem.Model;


public class Customer {
        private int ID;
        private String useName;
        private double balance;
       // private double amount;


        public Customer(int ID, String useName, double balance) {
            this.ID = ID;
            this.useName = useName;
            this.balance = balance;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getUseName() {
            return useName;
        }

        public void setUseName(String useName) {
            this.useName = useName;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
