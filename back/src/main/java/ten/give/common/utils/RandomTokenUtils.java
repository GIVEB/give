package ten.give.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class RandomTokenUtils {

    private static int certNumLength = 4;

    public static String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());
        log.info("token Util c : {}", certNumLength);

        int range = (int)Math.pow(10,certNumLength);
        int trim = (int)Math.pow(10, certNumLength-1);
        int result = random.nextInt(range)+trim;

        if(result>range){
            result = result - trim;
        }

        log.info("token Util : {}", result);

        return String.valueOf(result);
    }

}
