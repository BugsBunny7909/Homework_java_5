package Homework5;

// Интерфейс стратегии
interface Strategy {
    int execute(int a, int b);
}

// Конкретные стратегии
class ConcreteStrategyAdd implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

class ConcreteStrategySubtract implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

class ConcreteStrategyMultiply implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}

// Контекст, использующий стратегию
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// Пример использования
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new ConcreteStrategySubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new ConcreteStrategyMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}