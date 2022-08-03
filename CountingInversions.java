import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountingInversions {

    /*READ ARRAY NUMBERS*/
    public Integer[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<Integer> lines = new ArrayList<Integer>();
        String line = null;
        int temp;
        while ((line = bufferedReader.readLine()) != null) {
            temp = Integer.parseInt(line);
            lines.add(temp);
        }
        bufferedReader.close();
        return lines.toArray(new Integer[lines.size()]);
    }

    public int merge(Integer arr[], int l, int mid, int r)
    {
        int i,j,k, countInv = 0;
        /*creating temp arrays*/
        Integer[] firstH= Arrays.copyOfRange(arr, l, mid + 1);
        Integer[] secondH= Arrays.copyOfRange(arr, mid + 1, r + 1);
        int sizeFirst = firstH.length;
        int sizeSec = secondH.length;

        i = 0;
        j = 0;
        k = l;

        /*compare and count inversions*/
        while(i < sizeFirst && j < sizeSec)
        {
            if(firstH[i] <= secondH[j])
            {
                arr[k] = firstH[i];
                i++;
            }
            else
            {
                arr[k] = secondH[j];
                j++;
                countInv += (mid + 1) - (l + i);
            }
            k++;
        }

        while(i < sizeFirst)
        {
            arr[k] = firstH[i];
            i++;
            k++;
        }

        while(j < sizeSec)
        {
            arr[k] = secondH[j];
            j++;
            k++;
        }
        return countInv;
    }


    public int mergeAndCount(Integer arr[], int l, int r)
    {
        int count = 0;
        if (l < r)
        {
            //int mid = l + (r - l) / 2;
            int mid = l + (r-l) / 2;
            count += mergeAndCount(arr, l, mid);
            count += mergeAndCount(arr, mid +1, r);
            count += merge(arr, l, mid, r);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        
        CountingInversions obj = new CountingInversions();
        /*Read array from file */
        Integer[] nums = obj.readLines("test.txt");

        /*Initialize sizes*/
        int size = nums.length;
        int left = 0, right = size-1;
        int inversions = obj.mergeAndCount(nums, left, right);
        /*for(int i = 0; i < size; i++)
        {
            System.out.print(nums[i]);
        }*/
        //System.out.println("");
        System.out.println(inversions);
    }
}