package Homework5;

// Общий интерфейс
interface Image {
    void display();
}

// Реальный объект
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Загрузка изображения: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Просмотр изображения: " + filename);
    }
}

// Прокси-объект
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Ленивая инициализация
        }
        realImage.display();
    }
}

// Пример использования
public class ProxyPattern {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Изображение загрузится только при вызове display()
        image1.display();
        image1.display(); // Повторный вызов - загрузки не будет

        image2.display();
    }
}