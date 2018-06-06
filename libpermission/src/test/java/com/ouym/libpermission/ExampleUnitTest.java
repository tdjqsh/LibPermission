package com.ouym.libpermission;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    interface Food{
        void eat(String name);

        void make(int num);
    }

    interface Other{
        void doit(String str);
    }

    interface All extends Food, Other{

    }

    class Meat implements All{

        @Override
        public void eat(String name) {
            System.out.println("we all need eat " + name);
        }

        @Override
        public void make(int num) {
            System.out.println("so we feed " + num + " cows");
        }

        @Override
        public void doit(String str) {
            System.out.println(str);
        }
    }

    class Rice implements Other{

        @Override
        public void doit(String str) {
            System.out.println(str + "---------------------");
        }
    }

    class FoodInvoke implements InvocationHandler{

        Food fp;
        Other other;


        public FoodInvoke(Food fp, Other other) {
            this.fp = fp;
            this.other = other;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equalsIgnoreCase("doit")){
                return method.invoke(other, args);
            }
            return method.invoke(fp, args);
        }
    }

    @Test
    public void testProxy(){
        Meat m = new Meat();//fragmentActivity实现
        Rice r = new Rice();//代理部分接口
        All all = (All) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{All.class}, new FoodInvoke(m, r));
        all.eat("no-food");
        all.make(1000);
        all.doit("what the hello is that");

    }
}