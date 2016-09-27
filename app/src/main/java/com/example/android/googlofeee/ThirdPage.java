package com.example.android.googlofeee;



import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

public class ThirdPage extends AppCompatActivity {

    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);
    }


    /**
     * This method is called when plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            //Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 Coffees", Toast.LENGTH_SHORT).show();
            //Exit this method early because there is nothing to do.
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0) {
            //Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 coffees", Toast.LENGTH_SHORT).show();
            //Exit this method early because there is nothing to do.
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }
    /**
     * This method is called when the next button is clicked.
     */
    public void submitOrder(View view) {

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_edit_text_view);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        // Get user's address
        EditText addressField = (EditText) findViewById(R.id.address_edit_text_view);
        Editable addressEditable = addressField.getText();
        String address = addressEditable.toString();


        // Get user's contact no.
        EditText contactField = (EditText) findViewById(R.id.contact_edit_text_view);
        Editable contactEditable = contactField.getText();
        String contact = contactEditable.toString();


        // Get user's email address
        EditText emailIdField = (EditText) findViewById(R.id.emailId_edit_text_view);
        Editable emailIdEditable = emailIdField.getText();
        String emailId = emailIdEditable.toString();



        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(name, address, contact, emailId, price, hasWhippedCream, hasChocolate);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not we should include whipped cream topping in the price
     * @param addChocolate    is whether or not we should include whipped cream topping in the price
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // First calculate the price of one cup of coffee
        int basePrice = 5;

        // If the user wants whipped cream, add $1 per cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // If the user wants chocolate, add $2 per cup
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by the quantity
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     *
     * @param name            on the order
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(String name, String address, String contact, String emailId, int price, boolean addWhippedCream,
                                      boolean addChocolate) {
        String priceMessage = getString(R.string.order_summary_name ,name);
        priceMessage += "\n" + getString(R.string.order_summary_address ,address);
        priceMessage += "\n" + getString(R.string.order_summary_contact_no ,contact);
        priceMessage += "\n" + getString(R.string.order_summary_emailId ,emailId);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price,
                NumberFormat.getCurrencyInstance().format(price));
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }




    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.update_quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
