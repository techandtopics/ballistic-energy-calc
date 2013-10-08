/* 	   Ballistic Energy Calculator - calculates muzzle energy and Taylor Knock Out Factor
 *         Copyright (C) 2011 George Yauneridge
 * 
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or (at
 *         your option) any later version.
 * 
 *         This program is distributed in the hope that it will be useful, but
 *         WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *         General Public License for more details.
 * 
 *         You should have received a copy of the GNU General Public License
 *         along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.blogspot.techandtopics;

import java.math.BigDecimal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Ballistic Energy Calculator Source and support can be found at
 * <http://techandtopics.blogspot.com>. Version: 1.0 Target device: HTC
 * Incredible API 8
 * 
 * This program calculates the muzzle energy and Taylor KO Factor based on the
 * mass, velocity and diameter input by the user.
 * 
 * @author George Yauneridge
 * 
 */

public class EnergyCalc extends Activity {

	private EditText velocityEntry; // gets velocity input
	private EditText massEntry; // gets mass input
	private EditText diameterEntry; // gets diameter input

	private TextView displayTKO; // displays calculated TKO
	private TextView displayME; // displays calculated Energy
	private TextView displayMomentum; // displays calculated Momentum

	/**
	 * Set layout
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		velocityEntry = (EditText) findViewById(R.id.velocityEntry);
		massEntry = (EditText) findViewById(R.id.massEntry);
		diameterEntry = (EditText) findViewById(R.id.diameterEntry);

		displayTKO = (TextView) findViewById(R.id.displayTKO);
		displayME = (TextView) findViewById(R.id.displayME);
		displayMomentum = (TextView) findViewById(R.id.displayMomentum);
	}

	/**
	 * Override to save the displayTKO and displayME textView values that were
	 * generate during runtime.
	 */
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putString("bundleValueTKO",
				(String) displayTKO.getText());
		savedInstanceState.putString("bundleValueME",
				(String) displayME.getText());
		savedInstanceState.putString("bundleValueMomentum",
				(String) displayMomentum.getText());
		super.onSaveInstanceState(savedInstanceState);
	}

	/**
	 * 
	 */
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		displayTKO.setText(savedInstanceState.getString("bundleValueTKO"));
		displayME.setText(savedInstanceState.getString("bundleValueME"));
		displayMomentum.setText(savedInstanceState
				.getString("bundleValueMomentum"));
	}

	/**
	 * Inflate the menu from R.layout.menu.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}

	/**
	 * Display content based on menu item selected.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			showAbout();
			return true;
		case R.id.menu_help:
			showHelp();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Displays a dialog box with the about message. Called by about button in
	 * the main.xml.
	 */
	public void showAbout(View view) {

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		alertbox.setMessage(R.string.about_content);

		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	/**
	 * Displays a dialog box with the about message.
	 */
	public void showAbout() {

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		alertbox.setMessage(R.string.about_content);

		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	/**
	 * Displays a dialog box with the help message. Used by help button in the
	 * main.xml.
	 */
	public void showHelp(View view) {

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		alertbox.setMessage(R.string.help_content);

		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	/**
	 * Displays a dialog box with the help message.
	 */
	public void showHelp() {

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		alertbox.setMessage(R.string.help_content);

		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	/**
	 * Displays a dialog box with the error message.
	 */
	public void showError() {

		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		alertbox.setMessage(R.string.error_content);

		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alertbox.show();
	}

	/**
	 * Closes the software keyboard, sets the variables, computes and displays
	 * the result.
	 * 
	 * @param View
	 *            v
	 * @throws NumberFormatException
	 * @throws ArithmeticException
	 * @throws IllegalArgumentException
	 */
	public void calculate(View v) throws NumberFormatException,
			ArithmeticException, IllegalArgumentException {

		InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
		// close soft keyboard
		if ((massEntry.getText().toString()).equalsIgnoreCase("")
				|| (massEntry.getText().toString()).equalsIgnoreCase(" ")) {
			showError();
			displayTKO.setText("-");
			displayME.setText("-");
			displayMomentum.setText("-");
		} else if ((velocityEntry.getText().toString()).equalsIgnoreCase("")
				|| (velocityEntry.getText().toString()).equalsIgnoreCase(" ")) {
			showError();
			displayTKO.setText("-");
			displayME.setText("-");
			displayMomentum.setText("-");
		} else {
			BigDecimal velocity = new BigDecimal(velocityEntry.getText()
					.toString());
			BigDecimal mass = new BigDecimal(massEntry.getText().toString());
			BigDecimal grainsInPound = new BigDecimal("7000");

			// mass/2 * velocity^2 * (7000*32.163) = muzzle energy
			BigDecimal result = mass.divide(new BigDecimal("2"),10,0);
			result = result.multiply(velocity.pow(2));		
			result = result.divide(new BigDecimal("225141"),3,0); // 3=scale, 0=ROUND_UP

			displayME.setText(myFormat(result.toString()));

			// (mass/7000) * velocity = momentum ft/lbs
			BigDecimal resultMomentum = mass.divide(grainsInPound,10,0);
			resultMomentum = velocity.multiply(resultMomentum);

			displayMomentum.setText(myFormat(resultMomentum.toString()));

			if ((diameterEntry.getText().toString()).equalsIgnoreCase("")
					|| (diameterEntry.getText().toString())
							.equalsIgnoreCase(" ")
					|| (diameterEntry.getText().toString())
							.equalsIgnoreCase(".")) {
				displayTKO.setText("-");
			} else {
				result = mass.multiply(velocity);
				result = result.multiply(new BigDecimal(diameterEntry.getText()
						.toString()));
				result = result.divide(new BigDecimal("7000"),3,0);

				displayTKO.setText(myFormat(result.toString()));
			}
		}
	}

	/**
	 * Formats the decimal result to two decimal places.
	 * 
	 * @param String
	 *            numberString
	 * @return String
	 */
	static public String myFormat(String numberString) {
		int indexDot = numberString.indexOf(".");
		if (numberString.contains(".")
				&& (numberString.length() - indexDot) > 2) {
			return numberString.substring(0, indexDot + 2);
		} else {
			return numberString;
		}
	}
}