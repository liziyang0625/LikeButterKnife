package top.liziyang.likebutterknife.butter_knife;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liziyang
 * @since 2019-12-03
 */
public class ButterKnife {

    public static void bind(Activity target) {

        try {
            bindView(target);
            onClick(target);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void bindView(Activity target) throws Exception {
        // 找到target里的注解
        Class cls = target.getClass();

        // 获取变量
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {

            field.setAccessible(true);

            // 变量上的注解
            BindView bindView = field.getAnnotation(BindView.class);

            if (bindView == null) {
                continue;
            }

            // 获取注解上的值，在这里就是id
            int id = bindView.value();

            // find view by id
            View view = target.findViewById(id);

            // 放入View对象
            field.set(target, view);
        }
    }

    private static void onClick(final Activity target) throws Exception {
        // 找到target里的注解
        Class cls = target.getClass();

        // 获取方法
        Method[] methods = cls.getDeclaredMethods();
        for (final Method method : methods) {
            method.setAccessible(true);

            // 变量上的注解
            OnClick onClick = method.getAnnotation(OnClick.class);

            if (onClick == null) {
                continue;
            }

            // 获取注解上的值，在这里就是id
            int id = onClick.value();

            // find view by id
            View view = target.findViewById(id);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 反射
                    // 没有参数
                    try {
                        method.invoke(target, null);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
