import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {



    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        Long count = Long.valueOf(0);
        Long unu = Long.valueOf(1);
        HashMap <Long, Long> right = new HashMap<Long, Long>();
        HashMap <Long, Long> left = new HashMap<Long, Long>();

        
        for (Long long1 : arr) {
            if(right.containsKey(long1)){
                Long oldValue = right.get(long1);
                right.replace(long1, oldValue, oldValue + 1);
            }
            else right.put(long1, unu);
        }

        for (Long num : arr){
            Long le = left.get(num/r);
            Long ri = right.get(num * r);
            Long n = right.get(num);
            
            if (num == num * r)
                ri--;
            
            if(le != null && ri != null && le > 0 && ri > 0 && num % r == 0){
                count += le * ri;
            }

            right.replace(num, n, n - 1);
            le = left.get(num);

            if(le != null){
                left.replace(num, le, le + 1);
            }
            else left.put(num, unu);
            
        }


        return count;        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("data\\input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data\\output.txt"));

        String[] nr = bufferedReader.readLine().split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();
        // for (int i = 0;i < 10000;i++)
        //     {
        //         bufferedWriter.write(1 + " ");
        //     }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
