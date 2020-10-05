package com.hozon.MyNewPackage

data class DataClass(var name:String, val age:Int)

    var grade:Int = 12 ; // 不会包含在自动生成的函数中 比如toString copy

    //fun directCopy(name:String = this.name):DataClass = DataClass(this.name, this.age)


/*

加上 data 之后 自动生成多了很多的函数
一个类只有属性/get和set 没有其他成员函数 可以使用数据类型 当然也可以增加属性和方法

data 会自动生成 （不会包含类体中的成员属性）
toString (可以重载)
equal (可以重载)
hashCode (可以重载)
copy
和 解剖函数component





https://www.javadecompiler.online/


import com.hozon.MyNewPackage.DataClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
    mv = {1, 1, 15},
    bv = {1, 0, 3},
    k = 1,
    d1 = {"\000 \n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\020\b\n\002\b\013\n\002\020\013\n\002\b\004\b\b\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\t\020\r\032\0020\003HÆ\003J\t\020\016\032\0020\005HÆ\003J\035\020\017\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\005HÆ\001J\023\020\020\032\0020\0212\b\020\022\032\004\030\0010\001HÖ\003J\t\020\023\032\0020\005HÖ\001J\t\020\024\032\0020\003HÖ\001R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\007\020\bR\032\020\002\032\0020\003X\016¢\006\016\n\000\032\004\b\t\020\n\"\004\b\013\020\f¨\006\025"},
    d2 = {"Lcom/hozon/MyNewPackage/DataClass;", "", "name", "", "age", "", "(Ljava/lang/String;I)V", "getAge", "()I", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "KolinStudy1"}
    )
public final class DataClass
{
	@NotNull
	private String name;

	private final int age;

    public DataClass(@NotNull String name, int age)
    {
        this.name = name;
		this.age = age;
	}

	@NotNull
	public final String getName()
	{
	    return this.name;
	}

	public final void setName(@NotNull String <set-?>)
	{
	    Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.name = <set-?>;
    }

	public final int getAge()
	{
	    return this.age;
    }

	@NotNull
	public final String component1()
	{
	    return this.name;
	}

	public final int component2()
	{
	    return this.age;
	}

	@NotNull
	public final DataClass copy(@NotNull String name, int age) {
		Intrinsics.checkParameterIsNotNull(name, "name");
		return new DataClass(name, age);
	}

	@NotNull
	public String toString()
	{
	    return "DataClass(name=" + this.name + ", age=" + this.age + ")";
	    }

	public int hashCode() {
	return ((this.name != null) ? this.name.hashCode() : 0) * 31
	    + Integer.hashCode(this.age);
	}

	public boolean equals(@Nullable Object paramObject) {
		if (this != paramObject) {
			if (paramObject instanceof DataClass) {
				DataClass dataClass = (DataClass)paramObject;
				if (Intrinsics.areEqual(this.name, dataClass.name) && ((this.age == dataClass.age)))
					return true;
			}
		} else {
			return true;
		}
		return false;
	}
}



 */