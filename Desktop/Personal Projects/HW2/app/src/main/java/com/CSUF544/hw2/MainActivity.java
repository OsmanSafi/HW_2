package com.CSUF544.hw2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.CSUF544.hw2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UI components
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
                    int[] numbers = new int[numStrings.length];
                    for (int i = 0; i < numStrings.length; i++) {
                        numbers[i] = Integer.parseInt(numStrings[i]);
                    }

                    // Perform insertion sort and collect steps
                    StringBuilder intermediateSteps = new StringBuilder();
                    insertionSort(numbers, intermediateSteps);

                    // Update UI with results
                    tvSortedOutput.setText("Sorted Result: " + arrayToString(numbers));
                    tvIntermediateSteps.setText("Intermediate Steps:\n" + intermediateSteps.toString());
                } catch (NumberFormatException e) {
                    tvSortedOutput.setText("Invalid input. Please enter integers only.");
                }
            } else {
                tvSortedOutput.setText("Input field is empty.");
            }
        });

        // Clear Button Logic
        btnClear.setOnClickListener(view -> {
            inputNumbers.setText("");
            tvSortedOutput.setText("");
            tvIntermediateSteps.setText("");
        });
    }

    // Helper Method: Perform insertion sort and record intermediate steps
    private void insertionSort(int[] arr, StringBuilder steps) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;

            // Record the current state of the array
            steps.append(arrayToString(arr)).append("\n");
        }
    }

    // Helper Method: Convert array to string
    private String arrayToString(int[] arr) {
        StringBuilder result = new StringBuilder();
        for (int num : arr) {
            result.append(num).append(" ");
        }
        return result.toString().trim();
    }
}
