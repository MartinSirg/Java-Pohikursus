package ee.ttu.iti0202.rec;
import java.util.List;

public class Rec {

    public static int maxElement(List<Integer> nums) {
        if (nums.size() == 1) {
            return nums.get(0);
        } else {
            int a = maxElement(nums.subList(1, nums.size()));
            if (a > nums.get(0)) {
                return a;
            } else {
                return nums.get(0);
            }
        }
    }

    public static int maxElement(List<Integer> nums, int max) {
        if (nums.size() == 1) {
            if (nums.get(0) > max) {
                return nums.get(0);
            } else {
                return max;
            }
        } else {
            if (nums.get(0) > max) {
                return maxElement(nums.subList(1, nums.size()), nums.get(0));
            } else {
                return maxElement(nums.subList(1, nums.size()), max);
            }
        }
    }

    public static int maxGrowth(List<Integer> nums) {
        return maxGrowth(nums, 1, 1);
    }

    public static int maxGrowth(List<Integer> nums, int currentStreak, int longest) {
        if (nums.size() < 2) { // if list smaller than 2 is presented---------------------------------------------------
            return nums.size();
        } else if (nums.size() == 2) { // ending condition--------------------------------------------------------------
            if (nums.get(1) > nums.get(0)) currentStreak += 1;
            if (currentStreak > longest) longest = currentStreak;
            return longest;
        } else { // recursion conditions--------------------------------------------------------------------------------
            if (nums.get(1) > nums.get(0)) {                                                         // streak continues
                if (currentStreak + 1 > longest) longest = currentStreak + 1;
                return maxGrowth(nums.subList(1, nums.size()), currentStreak + 1, longest);
            } else {                                                                          // streak doesn't continue
                return maxGrowth(nums.subList(1, nums.size()), 1, longest);
            }
        }
    }

}
