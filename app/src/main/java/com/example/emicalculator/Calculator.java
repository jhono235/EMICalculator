package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    TextView monthlyPayments, amtBorrowed, interestRate, numOfYears;
    SeekBar amtBorrowedSeekBar, interestRateSeekBar, numOfYearsSeekBar;

    int progress_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);



        //binding views
        monthlyPayments = findViewById(R.id.monthlyPayments);
        amtBorrowed = findViewById(R.id.amtBorrowed);
        interestRate = findViewById(R.id.interestRate);
        numOfYears = findViewById(R.id.numOfYears);
        amtBorrowedSeekBar = findViewById(R.id.amtSeekbar);
        interestRateSeekBar = findViewById(R.id.interestSeekBar);
        numOfYearsSeekBar = findViewById(R.id.numOfYearsSeekBar);

        setAmtBorrowedSeekBar();
        setInterestRate();
        setNumOfYears();
        setMonthlyPayments();


    }

    public void setAmtBorrowedSeekBar(){
        amtBorrowed.setText("$ " +amtBorrowedSeekBar.getProgress());

        amtBorrowedSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        progress_value = progress;
                        amtBorrowed.setText("$ " +amtBorrowedSeekBar.getProgress());
                        setMonthlyPayments();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }
                }
        );
    }

    public void setInterestRate(){
        interestRate.setText(interestRateSeekBar.getProgress()+" %");

        interestRateSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        progress_value = progress;
                        interestRate.setText(interestRateSeekBar.getProgress()+" %");
                        setMonthlyPayments();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }
                }
        );
    }

    public void setNumOfYears(){
        numOfYears.setText(numOfYearsSeekBar.getProgress()+"");

        numOfYearsSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        progress_value = progress;
                        numOfYears.setText(numOfYearsSeekBar.getProgress()+"");
                        setMonthlyPayments();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                    }
                }
        );
    }

    public void setMonthlyPayments(){
        double calculation = ((amtBorrowedSeekBar.getProgress()+(amtBorrowedSeekBar.getProgress()
        *numOfYearsSeekBar.getProgress()*(interestRateSeekBar.getProgress()*.01)))/(numOfYearsSeekBar.getProgress()*12));

        String payment = String.format("%.2f",calculation);
        monthlyPayments.setText("$" + payment);
    }
}
