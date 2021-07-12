package 破解;

/**
 * @作者: one者天下
 * @时间: 2021/4/19 21:23
 * @描述: 继承时，若是父类的构造器是带参构造器，子类必须实现自定义构造器，并且显示调用父类的带参构造器。
 *
 */
public class Practise {
    public static void main(String[] args) {
        d d = new d();
        d.print();

    }
}
class A {
    String name;
    Integer age;
    public A (String name , Integer age){
        this.name = name;
        this.age = age;
    }
}
class B extends A {
    String a;
    public B (String name,Integer age,String s) {
        super(name,age);
        this.a = s;
    }
}

 class c {
    private int x = 100;

    public c (){
        print();
    }

    public void print(){

    }
}
class d extends c {
    private int x = 200;

    public d () {
    }

    @Override
    public void print() {
        System.out.println(x);
    }
}

