package com.example.homework311;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "CALCULATOR";

	private static final String POINT = ".";

	private static final int DOT_BUTTON = R.id.buttonDot;

	private TextView textView;
	private Button[] numberButtons = new Button[11];

	private String inputString = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		textView.setText(inputString);
		View.OnClickListener padListener = this::handlePress;
		for (Button b : numberButtons)
			b.setOnClickListener(padListener);
	}

	private void initViews() {
		textView = findViewById(R.id.textView);

		numberButtons[0] = findViewById(R.id.button0);
		numberButtons[1] = findViewById(R.id.button1);
		numberButtons[2] = findViewById(R.id.button2);
		numberButtons[3] = findViewById(R.id.button3);
		numberButtons[4] = findViewById(R.id.button4);
		numberButtons[5] = findViewById(R.id.button5);
		numberButtons[6] = findViewById(R.id.button6);
		numberButtons[7] = findViewById(R.id.button7);
		numberButtons[8] = findViewById(R.id.button8);
		numberButtons[9] = findViewById(R.id.button9);

		numberButtons[10] = findViewById(DOT_BUTTON);
	}

	private void handlePress(View v) {
		switch (v.getId()) {
			case DOT_BUTTON:
				dotPress();
				break;
			default:
				numPress(v.getId());
				break;
		}

		textView.setText(inputString = trim(inputString));
	}

	private void numPress(int id) { // proceeds number press
		for (int i = 0; i < 10; i++)
			if (id == numberButtons[i].getId()) {
				inputString += i;
				return;
			}
	}

	private void dotPress() { // proceeds dot press
		if (inputString.contains(POINT))
			return;

		inputString += POINT;
	}

	// cuts leading zeros, i.e. 00012.5 -> 12.5; 000.4 -> 0.4; .1 -> 0.1
	private static String trim(String s) {
		int point = s.indexOf(POINT);

		String decimalPart = point == -1 ? "" : s.substring(point);
		String integerPart = point == -1 ? s : s.substring(0, point);

		int startingZeros = integerPart.length();
		for (int i = 0; i < integerPart.length(); i++)
			if (integerPart.charAt(i) != '0') {
				startingZeros = i;
				break;
			}
		integerPart = integerPart.substring(startingZeros);
		if (integerPart.isEmpty())
			integerPart = "0";

		return integerPart + decimalPart;
	}
}
