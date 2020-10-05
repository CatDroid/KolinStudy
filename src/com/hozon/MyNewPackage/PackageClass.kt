package com.hozon.MyNewPackage

import SUBSYSTEM_DEPRECATED

@Deprecated(SUBSYSTEM_DEPRECATED)
class PackageClass {

    companion object {
        // private static final String ARG_PARAM1 = "param1";
        // private static final String ARG_PARAM2 = "param2";
        private val ARG_PARAM1 = "param1";
        private val ARG_PARAM2 = "param2";
    }

    internal fun dumpPackageClass() // 默认返回类型是 Unit
    {
        println("dumpPackageClass called , static final variable ${ARG_PARAM2}");
    }

    internal fun defaultArgsValue(temp: String = "default", temp1:Int = 12)
            = println("default args = ${temp},${temp1}")



}