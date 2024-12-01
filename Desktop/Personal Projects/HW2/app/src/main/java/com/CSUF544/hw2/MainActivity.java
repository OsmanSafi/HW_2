package com.CSUF544.hw2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.CSUF544.hw2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UI components from activity_main
        EditText inputNumbers = findViewById(R.id.input_numbers);
        Button btnSort = findViewById(R.id.btn_sort);
        Button btnClear = findViewById(R.id.btn_clear);
        TextView tvSortedOutput = findViewById(R.id.tv_sorted_output);
        TextView tvIntermediateSteps = findViewById(R.id.tv_intermediate_steps);

        // Sort Button Logic
        btnSort.setOnClickListener(view -> {
            String input = inputNumbers.getText().toString().trim();
            if (!input.isEmpty()) {
                try {
                    // Parse input and perform sorting
                    String[] numStrings = input.split("\\s+");
                    int[] nums = new int[numStrings.length];

                    for (int i = 0; i < numStrings.length; i++) {
                        nums[i] = Integer.parseInt(numStrings[i]);
                    }

                    StringBuilder intermediateSteps = new StringBuilder();

                    // Perform insertion sort
                    insertionSort(nums, intermediateSteps);

                    // Update UI with results
                    tvSortedOutput.setText("Sorted Result: " + arrayToString(nums));
                    tvIntermediateSteps.setText("Intermediate Steps:\n" + intermediateSteps);

                } catch (NumberFormatException e) {
                    tvSortedOutput.setText("Invalid input. Please enter integers only.");
                }
            } else {
                tvSortedOutput.setText("Input field is empty.");
            }
        });

        // Clear Button Logic
        btnClear.setOnClickListener(view -> {
            // Clear UI components
            inputNumbers.setText("");
            tvSortedOutput.setText("Sorted Result: ");
            tvIntermediateSteps.setText("Intermediate Steps: ");
        });
    }

    // Perform insertion sort and record intermediate steps
    private void insertionSort(int[] arr, StringBuilder steps) {
        for (int i = 1; i < arr.length; i++) {

            int k = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > k) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = k;

            // Record the current state of the array
            steps.append(arrayToString(arr)).append("\n");
        }
    }

    // Convert array to string
    private String arrayToString(int[] arr) {

        StringBuilder str = new StringBuilder();

        for (int num : arr) {
            str.append(num).append("  ");
        }

        return str.toString().trim();
    }
}
