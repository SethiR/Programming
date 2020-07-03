public class Main1 {
    public static void main(String[] args) {

        // Single dimension arrays.
        int[] arr1 = {0,2,3,4,5,6,7};

        for(int val : arr1){
            System.out.println(val);
        }

        int[] arr2 = new int[10];
        arr2[0] = 1;
        for(int val : arr2){
            System.out.println(val);
        }

        // 2-D Array
        int[][] arr3 = {{0,2},{3,4}};

        for (int[] row : arr3){
            for (int val : row){
                System.out.println(val);
            }
        }


    }
}
