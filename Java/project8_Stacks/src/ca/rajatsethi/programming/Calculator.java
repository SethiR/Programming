package ca.rajatsethi.programming;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

    public int evaluate(final String input)
    {
        final Deque<String> stack = new ArrayDeque<>();

        final String[] tokens = input.split(" ");

        for(String token: tokens)
        {
            stack.push(token);
        }

        while (stack.size() > 1)
        {
            int left = Integer.parseInt(stack.pop());
            String operator = stack.pop();
            int right = Integer.parseInt(stack.pop());

            int result = 0;

            switch (operator)
            {
                case "+":
                    result = left + right;
                    break;
                case "-":
                    result = left - right;
                    break;
            }
            stack.push(String.valueOf(result));
        }

        return Integer.parseInt(stack.pop());

    }
}
