package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
  int quantity = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * This method is called when the order button is clicked.
   */

  public void submitOrder(View view) {
    EditText nameField = (EditText) findViewById(R.id.name_field);
    String name = nameField.getText().toString();

    CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
    boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

    CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
    boolean hasChocolate = chocolateCheckBox.isChecked();

    int price = calculatePrice(hasWhippedCream, hasChocolate);
    String priceMessage = createOrderSummary(price, name, hasWhippedCream, hasChocolate);
    displayMessage(priceMessage);
  }

  public void increment(View view) {
    if (quantity == 100) {
      Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
      return;
    }
    quantity += 1;
    displayQuantity(quantity);
  }

  public void decrement(View view) {
    if (quantity == 1) {
      Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
      return;
    }
    quantity -= 1;
    displayQuantity(quantity);
  }

  // calculates the total price of an order
  private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
    int basePrice = 5;

    if (hasWhippedCream) {
      basePrice += 1;
    }

   if (hasChocolate) {
      basePrice += 2;
    }
    return quantity * basePrice ;
  }

  private String createOrderSummary(int price, String name, boolean hasWhippedCream, boolean hasChocolate) {
    String priceMessage = getString(R.string.order_summary_name, name);
    priceMessage += "\nAdd Whipped Cream ? " + hasWhippedCream;
    priceMessage += "\nAdd Chocolate ? " + hasChocolate;
    priceMessage += "\nQuantity: " + quantity;
    priceMessage +=  "\nTotal: £" + price;
    priceMessage += "\n" + getString(R.string.thank_you);
    return priceMessage;
  }

  /**
   * This method displays the given quantity value on the screen.
   */
  private void displayQuantity(int numberOfCoffees) {
    TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
    quantityTextView.setText("" + numberOfCoffees);
  }

  private void displayMessage(String message) {
    TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
    orderSummaryTextView.setText(message);
  }
}