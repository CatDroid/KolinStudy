package com.kixi.collection;

import java.util.List;

public class Utils {

    static void reset(List<String> temp)
    {
        temp.set(0, "reset"); // kotlin的val List 不可变 到这里就可变了
    }
}
