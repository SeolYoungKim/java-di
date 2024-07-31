package com.interface21.beans.factory.support;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.interface21.beans.factory.annotation.Autowired;
import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BeanFactoryUtilsTest {
    private static class TestClass {
        public TestClass() {
        }

        @Autowired
        public TestClass(String ignore) {
        }
    }

    @DisplayName("@Autowired 가 설정돼 있는 생성자를 반환한다.")
    @Test
    void getAutowiredConstructor() {
        Constructor<?> autowiredConstructor = BeanFactoryUtils.getAutowiredConstructor(TestClass.class);
        assertThat(autowiredConstructor.getParameters()[0].getType()).isEqualTo(String.class);
    }

    private interface TestInterface {}
    private static class TestImpl implements TestInterface {}
    private static class TestNotImpl {}

    @DisplayName("clazz가 인터페이스인 경우 List에서 구체 클래스를 찾아서 반환")
    @Test
    void findConcreteClassInterface() {
        Class<TestInterface> testInterface = TestInterface.class;
        Set<Class<?>> classes = Set.of(TestImpl.class, TestNotImpl.class);

        Optional<Class<?>> concreteClass = BeanFactoryUtils.findConcreteClass(testInterface, classes);

        assertThat(concreteClass.get()).isEqualTo(TestImpl.class);
    }


    @DisplayName("clazz가 인터페이스가 아닌 경우 그대로 반환")
    @Test
    void findConcreteClassNotInterface() {
        Class<TestClass> testClass = TestClass.class;
        Set<Class<?>> classes = Set.of(TestImpl.class, TestNotImpl.class);

        Optional<Class<?>> concreteClass = BeanFactoryUtils.findConcreteClass(testClass, classes);

        assertThat(concreteClass.get()).isEqualTo(TestClass.class);
    }

    @DisplayName("clazz가 Null이면 예외 발생")
    @Test
    void findConcreteClassNull() {
        assertThatThrownBy(() -> BeanFactoryUtils.findConcreteClass(null, Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static class TestImplA implements TestInterface {}
    private static class TestImplB implements TestInterface {}

    @DisplayName("인터페이스를 구현한 클래스가 2개 이상인 경우 예외 발생")
    @Test
    void findConcreteClassException() {
        assertThatThrownBy(() -> BeanFactoryUtils.findConcreteClass(TestInterface.class, Set.of(TestImplA.class, TestImplB.class)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}