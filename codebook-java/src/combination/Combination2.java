package combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Combination2 {
    public ArrayList<Integer[]> newChickenRecipe(int[] stuffArr, int choiceNum) {
        // TODO:
        // new스터프배열 선언 stuffArr.stream -> filter 상한재료 걸러내기 (000)
        int[] freshStuffArr = Arrays.stream(stuffArr).filter(value -> {
            String valueString = String.valueOf(value);
            int count = 0;
            for (int i = 0; i < valueString.length(); i++) {
                if (valueString.charAt(i) == '0') {
                    count++;
                }
            }
            return !(count >= 3);
        }).sorted().toArray();

        // if (스터프.length < choiceNum) null 리턴
        if (freshStuffArr.length < choiceNum) {
            return null;
        }

        // result 선언
        ArrayList<Integer[]> result = new ArrayList<>();

        // combination실행
        result = combination(result, freshStuffArr, choiceNum, new Integer[0], 0, 0);

        // result 리턴
        return result;
    }

    // 사실 리턴타입 void도 상관없음
    private ArrayList<Integer[]> combination(ArrayList<Integer[]> result, int[] stuffArr, int choiceNum,
                                             Integer[] bucket, int depth, int start) {
        // if 버캣 크기가 choiceNum과 같으면 result에 add
        if (depth == choiceNum) {
            result.add(bucket);
            // result 리턴
            return result;
        }
        // for stuffArr 중에서 start 부터 시작
        for (int i = start; i < stuffArr.length; i++) {
            // 다음 요소를 집어넣은 배열 생성
            Integer[] copyArr = Arrays.copyOf(bucket, bucket.length + 1);
            copyArr[copyArr.length - 1] = stuffArr[i];
            // 재귀 호출
            result = combination(result, stuffArr, choiceNum, copyArr, depth + 1, i + 1);
        }
        // result 리턴
        return result;
    }

    public static void main(String[] args) {
        Combination2 solution = new Combination2();
        System.out.println(solution.newChickenRecipe(new int[]{1, 10, 1100, 1111}, 2)
                .stream().map(Arrays::toString).collect(Collectors.toList()));
        // [[1, 10], [1, 1100], [1, 1111], [10, 1100], [10, 1111], [1100, 1111]]
        System.out.println(solution.newChickenRecipe(new int[]{10000, 10, 1}, 3));
        // null
        System.out.println(solution.newChickenRecipe(new int[]{11, 1, 10, 1111111111, 10000}, 4)
                .stream().map(Arrays::toString).collect(Collectors.toList()));
        // [[1, 10, 11, 1111111111]]
        System.out.println(solution.newChickenRecipe(new int[]{11, 1, 10, 1111111111, 10000}, 3)
                .stream().map(Arrays::toString).collect(Collectors.toList()));
        // [[1, 10, 11], [1, 10, 1111111111], [1, 11, 1111111111], [10, 11, 1111111111]]
    }
}
