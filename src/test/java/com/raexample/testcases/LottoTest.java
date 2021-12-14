package com.raexample.testcases;

import com.raexample.BaseTestCase;
import com.raexample.pojo.lottoapi.Lotto;
import com.raexample.pojo.lottoapi.Winner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTest extends BaseTestCase {

    @Test
    public void getLottoDetails() {
        keyword.
                lottoApi().getLottoDetails();

        int[] number1 = {52, 3, 12, 11, 18, 22};
        int[] number2 = {2, 45, 34, 23, 3, 5};
        List<Integer> numberList1 = Arrays.stream(number1).boxed().toList();
        List<Integer> numberList2 = Arrays.stream(number2).boxed().toList();
        List<List<Integer>> numberList = new ArrayList<>();
        numberList.add(numberList1);
        numberList.add(numberList2);

        List<Integer> winnerIdList = new ArrayList<>();
        winnerIdList.add(23);
        winnerIdList.add(54);

        Lotto lottoResponse = ((Lotto) storage.whatIsTheObject("lottoResponse"));
        int lottoId = lottoResponse.getLottoId();
        Assert.assertEquals(lottoId, 5);
        int i = 0;
        for (Winner winner : lottoResponse.getWinners()) {
            int winnerId = winner.getWinnerId();
            List<Integer> numbers = winner.getNumbers();
            Assert.assertEquals(Integer.valueOf(winnerId), winnerIdList.get(i));
            Assert.assertEquals(numbers, numberList.get(i));

        }
    }
}
