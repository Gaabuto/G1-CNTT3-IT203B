import example.Calc570;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCalc {
    @Test
    public void testFeatureAdd(){
        //arrestEquals
        int result = Calc570.plus(2, 3);
        Assertions.assertEquals(25, result);
    }

    @Test
    @DisplayName("Kiem thu array equals")
    public void testArrayEquals(){
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        Assertions.assertArrayEquals(arr1, arr2);
    }



}
