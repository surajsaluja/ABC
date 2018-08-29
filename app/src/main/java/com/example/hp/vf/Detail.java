package com.example.hp.vf;

/**
 * Created by suraj on 23-08-2018.
 */
public class Detail {

    String Date,Bill_Number,Payable_Amount,Payment_status,type;
public Detail(String Date,String Bill_Number,String Payable_Amount,String Payment_status,String type){
    this.Bill_Number=Bill_Number;
    this.Date=Date;
    this.Payable_Amount=Payable_Amount;
    this.Payment_status=Payment_status;
    this.type=type;
}
    public String getDate() {
        return Date;
    }

    public String getBill_Number() {
        return "Bill Number "+Bill_Number;
    }

    public String getPayable_Amount() {

return "Rs. "+Payable_Amount;
    }

    public String getPayment_status() {
        if(Payment_status=="1") {
            return "Paid";
        }
        else if(Payment_status=="2") {
            return "Recieved";
        }
        else{
            return "Payment Pending";
        }
    }
    public String getType(){
        int amt=Integer.parseInt(Payable_Amount);
        if(type=="Sale"){
            if(amt>0)
            return "debit".toLowerCase();
            else
                return "credit".toLowerCase();
        }
        else {
            if(amt<0)
                return "debit".toLowerCase();
            else
                return "credit".toLowerCase();
        }

    }

    public void setBill_Number(String bill_Number) {
        Bill_Number = bill_Number;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setPayable_Amount(String payable_Amount) {
        Payable_Amount = payable_Amount;
    }

    public void setPayment_status(String payment_status) {
        Payment_status = payment_status;
    }
}
