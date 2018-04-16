package com.sample.app.util;

import java.util.Random;

public abstract class AppUtil {
	
	public static String generateId(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append("-");
        
        Random r = new Random();
        for (int j = 0; j < 5; j++) {
            if (r.nextBoolean()) {
                int i = r.nextInt(122) % 122;
                while (i < 97) {
                    i += 25;
                }
                sb.append(Character.toChars(i));
            } else {
                sb.append(r.nextInt(9));
            }
        }
        
        return sb.toString();
	}

}
