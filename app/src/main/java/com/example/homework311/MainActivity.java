package com.example.homework311;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private static final String POINT = ".";

	private TextView textView;
	private Button[] numberButtons;
	private Button dotButton;

	private String inputString = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		View.OnClickListener padListener = this::handlePress;
		dotButton.setOnClickListener(padListener);
		for (Button b : numberButtons)
			b.setOnClickListener(padListener);
	}

	private void initViews() {
		textView = findViewById(R.id.textView);

		numberButtons = new Button[10];
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

		dotButton = findViewById(R.id.buttonDot);
	}

	private void handlePress(View v) {
		numPress(v.getId());
		textView.setText(inputString);
	}

	private void numPress(int id) {
		if (id == dotButton.getId()) {
			if (inputString.contains(POINT))
				return;

			if (inputString.isEmpty())
				inputString = "0" + POINT;
			else
				inputString += POINT;
			return;
		}

		for (int i = 0; i < numberButtons.length; i++) {
			if (id == numberButtons[i].getId()) {
				inputString += i;
				return;
			}
		}
	}
}
