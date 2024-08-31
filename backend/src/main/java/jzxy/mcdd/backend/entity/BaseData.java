package jzxy.mcdd.backend.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * BaseData
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 21:59
 */
public interface BaseData {
    /**
     * 创建指定的 VO 类并将当前 DTO 对象中的所有成员变量值直接复制到 VO 对象中
     * @param clazz 指定 VO 类型
     * @param consumer 返回 VO 对象之前可以使用 Lambda 进行额外处理
     * @return 指定 VO 对象
     * @param <V> 指定 VO 类型
     */
    default <V> V asViewObject(Class<V> clazz, Consumer<V> consumer) {
        V v = this.asViewObject(clazz);
        consumer.accept(v);
        return v;
    }

    /**
     * 创建指定的 VO 类并将当前 DTO 对象中的所有成员变量值直接复制到 VO 对象中
     * @param clazz 指定 VO 类型
     * @return 指定 VO 对象
     * @param <V> 指定 VO 类型
     */
    default <V> V asViewObject(Class<V> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            Constructor<V> constructor = clazz.getConstructor();
            V v = constructor.newInstance();
            Arrays.asList(fields).forEach(field -> convert(field, v));
            return v;
        } catch (ReflectiveOperationException exception) {
            Logger logger = LoggerFactory.getLogger(BaseData.class);
            logger.error("在 VO 与 DTO 转换时出现了一些错误", exception);
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * 内部使用，快速将当前类中目标对象字段同名字段的值复制到目标对象字段上
     * @param field 目标对象字段
     * @param target 目标对象
     */
    private void convert(Field field, Object target){
        try {
            Field source = this.getClass().getDeclaredField(field.getName());
            field.setAccessible(true);
            source.setAccessible(true);
            field.set(target, source.get(this));
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }
}
