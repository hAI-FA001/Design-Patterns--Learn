// want all classes to follow specific steps/main algo
// but provide flexibility to have their own logic
// e.g. no matter what way of payment, you want them all to follow some specific steps
// looks similar to Strategy but here all classes must follow a common flow

package behavioral;

public class TemplateMethodPattern {
    public static void main(String[] args) {
        PaymentFlow paymentFlow = new PayToFriend();
        paymentFlow.sendMoney();

        System.out.println();

        paymentFlow = new PayToMerchant();
        paymentFlow.sendMoney();
    }
}

abstract class PaymentFlow {
    abstract void validateRequest();

    abstract void debitAmount();

    abstract void calcFees();

    abstract void creditAmount();

    // the template method
    public final void sendMoney() {
        // the steps
        validateRequest();
        debitAmount();
        calcFees();
        creditAmount();
    }
}

class PayToFriend extends PaymentFlow {

    @Override
    void validateRequest() {
        System.out.println("validate() in PayToFriend");
    }

    @Override
    void debitAmount() {
        System.out.println("debitAmount() in PayToFriend");
    }

    @Override
    void calcFees() {
        System.out.println("calcFees() in PayToFriend - 0% fees");
    }

    @Override
    void creditAmount() {
        System.out.println("creditAmount() in PayToFriend - credit full amount");
    }
}

class PayToMerchant extends PaymentFlow {
    @Override
    void validateRequest() {
        System.out.println("validate() in PayToMerchant");
    }

    @Override
    void debitAmount() {
        System.out.println("debitAmount() in PayToMerchant");
    }

    @Override
    void calcFees() {
        System.out.println("calcFees() in PayToMerchant - 2% fees");
    }

    @Override
    void creditAmount() {
        System.out.println("creditAmount() in PayToMerchant - credit remaining amount");
    }
}