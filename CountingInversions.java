import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountingInversions {

    private int overAllSize;
    CountingInversions(int s)
    {
        this.overAllSize = s;
    }
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
        int sizeFirstHalf = mid-l +1, sizeSecondHalf= r - mid;
        /*creating temp arrays*/
        Integer[] firstH = new Integer[sizeFirstHalf];
        Integer[] secondH = new Integer[sizeSecondHalf];

        /*Copying the two halfs of the array*/
        for(i = 0; i< sizeFirstHalf; i++)
        {
            firstH[i] = arr[i];
        }
        for(j = 0; j <sizeSecondHalf; j++)
        {
            secondH[j] = arr[j];
        }
        i = 0;
        j = 0;
        k = 0;

        /*compare and count inversions*/
        while(i < sizeFirstHalf && j < sizeSecondHalf)
        {
            if(firstH[i] < secondH[j])
            {
                arr[k] = firstH[i];
                i++;
            }
            else
            {
                arr[k] = secondH[j];
                j++;
                countInv += mid - i + 1;
            }
            k++;
        }

        while(i < sizeFirstHalf)
        {
            arr[k] = firstH[i];
            i++;
            k++;
        }

        while(j < sizeSecondHalf)
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
            int mid = l + (r - l) / 2;
            count += mergeAndCount(arr, l, mid);
            count += mergeAndCount(arr, mid +1, r);
            count += merge(arr, l, mid, r);
        }
        return count;
    }

    /*Setter*/
    public void setOverAllSize(int s)
    {
        this.overAllSize = s;
    }

    public static void main(String[] args) throws IOException {
        
        CountingInversions obj = new CountingInversions(0);
        /*Read array from file */
        Integer[] nums = obj.readLines("IntegerArray.txt");

        /*Initialize sizes*/
        int size = nums.length;
        obj.setOverAllSize(size);
        int left = 0, right = size-1;
        int inversions = obj.mergeAndCount(nums, left, right);
        System.out.print(inversions);
    }
}