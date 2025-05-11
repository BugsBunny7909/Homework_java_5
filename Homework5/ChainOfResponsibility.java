package Homework5;

// Интерфейс обработчика
interface Handler {
    void setNext(Handler handler);

    void handleRequest(String request);
}

// Базовый класс обработчика
abstract class AbstractHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(String request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Конкретные обработчики
class AuthenticationHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.contains("authenticated")) {
            System.out.println("AuthenticationHandler: Запрос аутентифицирован");
        } else {
            System.out.println("AuthenticationHandler: Запрос не аутентифицирован, передаю дальше");
            super.handleRequest(request);
        }
    }
}

class AuthorizationHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.contains("admin")) {
            System.out.println("AuthorizationHandler: Доступ разрешен для администратора");
        } else {
            System.out.println("AuthorizationHandler: Недостаточно прав, передаю дальше");
            super.handleRequest(request);
        }
    }
}

class ValidationHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.length() > 10) {
            System.out.println("ValidationHandler: Запрос валиден");
        } else {
            System.out.println("ValidationHandler: Запрос слишком короткий");
        }
    }
}

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Handler authHandler = new AuthenticationHandler();
        Handler authzHandler = new AuthorizationHandler();
        Handler validationHandler = new ValidationHandler();

        // Строим цепочку
        authHandler.setNext(authzHandler);
        authzHandler.setNext(validationHandler);

        // Тестируем разные запросы
        System.out.println("--- Запрос 1: admin запрос ---");
        authHandler.handleRequest("authenticated admin request");

        System.out.println("\n--- Запрос 2: обычный запрос ---");
        authHandler.handleRequest("authenticated user request");

        System.out.println("\n--- Запрос 3: неаутентифицированный запрос ---");
        authHandler.handleRequest("short");
    }
}